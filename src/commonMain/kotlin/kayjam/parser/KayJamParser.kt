package kayjam.parser

import kayjam.parser.exceptions.KayJamParserException
import kayjam.parser.stmts.KayJamExpression
import kayjam.parser.stmts.KayJamSettableExpression
import kayjam.parser.stmts.KayJamStmt
import kayjam.parser.stmts.expressions.KayJamSetExpression
import kayjam.parser.stmts.expressions.KayJamValueExpression
import kayjam.parser.stmts.expressions.KayJamVariableExpression
import kayjam.parser.stmts.impl.KayJamExpressionStmt
import java.lang.StringBuilder

class KayJamParser(val lexer: KayJamLexer) {
    companion object

    fun parseStmts(endToken: Token.Type): List<KayJamStmt> {
        val stmts = ArrayList<KayJamStmt>()
        while (true){
            stmts.addAll(parseStmt())
            if(lexer.moveAhead().type==endToken)
                break
        }

        return stmts
    }

    fun parseStmt(): List<KayJamStmt> {
        val stmts = ArrayList<KayJamStmt>()
        when(lexer.currentToken!!.type){
            Token.Type.OPEN_BRACKET -> {
                lexer.moveAhead()
                stmts.addAll(parseStmts(Token.Type.CLOSE_BRACKET))
            }

            else -> {
                lexer.moveAhead()
                stmts.add(KayJamExpressionStmt(parseExpression(Token.Type.TK_SEMI)))
            }
        }

        return stmts
    }

    fun parseVariable(): KayJamVariableExpression {
        val name = lexer.moveAhead().value

        var type = KayJamType.NOTHING
        if(lexer.moveAhead(Token.Type.TK_COLON).type==Token.Type.TK_COLON){
            lexer.moveAhead()
            type = parseType()
        }

        return KayJamVariableExpression(name, type)
    }

    fun requireToken(type: Token.Type): Token {
        val token = lexer.moveAhead()
        if(token.type!=type)
            throw KayJamParserException(this, "expected token ${type.name}")

        return token
    }

    fun parseExpression(endToken: Token.Type): KayJamExpression {
        var expression: KayJamExpression = when(lexer.currentToken!!.type) {
            Token.Type.STRING, Token.Type.INTEGER,
            Token.Type.LONG, Token.Type.DOUBLE -> parseValue()
            Token.Type.IDENTIFIER -> when(lexer.currentToken!!.value){
                "var" -> parseVariable()
                else -> {
                    throw Exception("придумаю название потом")
                }
            }

            else -> throw Exception("придумаю название потом")
        }

        while (lexer.currentToken!!.type!=endToken){
            println(lexer.currentToken!!.type)
            expression = when(lexer.currentToken!!.type){
                Token.Type.TK_ASSIGN -> {
                    if(expression !is KayJamSettableExpression)
                        continue

                    lexer.moveAhead()
                    val value = parseExpression(endToken)

                    lexer.moveAhead()
                    KayJamSetExpression(expression, value)
                }
                else -> break
            }
        }

        return expression
    }

    fun parseValue(): KayJamValueExpression {
        val value = when(lexer.currentToken!!.type){
            Token.Type.STRING -> KayJamValueExpression(lexer.currentToken!!.value, KayJamType.STRING)
            Token.Type.INTEGER -> KayJamValueExpression(lexer.currentToken!!.value.toInt(),
                KayJamType.INT)
            Token.Type.LONG -> KayJamValueExpression(lexer.currentToken!!.value.toLong(),
                KayJamType.LONG)
            Token.Type.DOUBLE -> KayJamValueExpression(lexer.currentToken!!.value.toDouble(),
                KayJamType.DOUBLE)
            else -> throw Exception("придумаю название потом")
        }

        lexer.moveAhead()
        return value
    }

    fun parseId(): String {
        if(lexer.currentToken!!.type==Token.Type.TK_NAMESPACE_DELIMITER)
            lexer.moveAhead(Token.Type.IDENTIFIER)

        if(lexer.currentToken!!.type!=Token.Type.IDENTIFIER)
            throw KayJamParserException(this, "expected identifier")

        val id = StringBuilder("\\"+lexer.currentToken!!.value)
        while (lexer.moveAhead(Token.Type.TK_NAMESPACE_DELIMITER).type==Token.Type.TK_NAMESPACE_DELIMITER){
            id.append("\\").append(requireToken(Token.Type.IDENTIFIER).value)
        }

        return id.toString()
    }

    fun parseType(): KayJamType {
        var nullable = false
        if(lexer.currentToken!!.type==Token.Type.TK_NULLABLE) {
            nullable = true
            lexer.moveAhead()
        }

        val typeName = parseId()

        return KayJamType(typeName, nullable)
    }
}

fun KayJamParser.Companion.init(string: String): KayJamParser = KayJamParser(KayJamLexer(string))