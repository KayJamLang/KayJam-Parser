package kayjam.parser.tests

import kayjam.parser.KayJamParser
import kayjam.parser.KayJamType
import kayjam.parser.Token
import kayjam.parser.init
import kayjam.parser.stmts.expressions.KayJamSetExpression
import kayjam.parser.stmts.expressions.KayJamValueExpression
import kayjam.parser.stmts.expressions.KayJamVariableExpression
import org.junit.Assert.assertEquals
import org.junit.Test

class ParserTests {

    @Test
    fun parseIntValue(){
        val expression = KayJamParser.init("123;")
            .parseExpression(Token.Type.TK_SEMI)
        assertEquals(KayJamValueExpression::class, expression::class)

        expression as KayJamValueExpression
        assertEquals(KayJamType.INT, expression.type)
        assertEquals(123, expression.value)
    }

    @Test
    fun parseDoubleValue(){
        val expression = KayJamParser.init("1.3;")
            .parseExpression(Token.Type.TK_SEMI)
        assertEquals(KayJamValueExpression::class, expression::class)

        expression as KayJamValueExpression
        assertEquals(KayJamType.DOUBLE, expression.type)
        assertEquals(1.3, expression.value)
    }

    @Test
    fun parseLongValue(){
        val expression = KayJamParser.init("1L;")
            .parseExpression(Token.Type.TK_SEMI)
        assertEquals(KayJamValueExpression::class, expression::class)

        expression as KayJamValueExpression
        assertEquals(KayJamType.LONG, expression.type)
        assertEquals(1L, expression.value)
    }

    @Test
    fun parseStringValue(){
        val expression = KayJamParser.init("'test';")
            .parseExpression(Token.Type.TK_SEMI)
        assertEquals(KayJamValueExpression::class, expression::class)

        expression as KayJamValueExpression
        assertEquals(KayJamType.STRING, expression.type)
        assertEquals("test", expression.value)
    }

    @Test
    fun parseVariable(){
        val expression = KayJamParser.init("var test;")
            .parseExpression(Token.Type.TK_SEMI)

        assertEquals(KayJamVariableExpression::class, expression::class)

        expression as KayJamVariableExpression
        assertEquals(KayJamType.NOTHING, expression.type)
        assertEquals("test", expression.variableName)
    }

    @Test
    fun parseVariableWithType() {
        val expression = KayJamParser.init("var test: string;")
            .parseExpression(Token.Type.TK_SEMI)

        assertEquals(KayJamVariableExpression::class, expression::class)

        expression as KayJamVariableExpression
        assertEquals("\\string", expression.type.name)
        assertEquals("test", expression.variableName)
    }

    @Test
    fun parseSetVariable() {
        val expression = KayJamParser.init("var test: test = 123;")
            .parseExpression(Token.Type.TK_SEMI)

        assertEquals(KayJamSetExpression::class, expression::class)

        expression as KayJamSetExpression
        assertEquals("\\int", expression.type.name)
        assertEquals(KayJamValueExpression::class, expression.value::class)
        assertEquals(KayJamVariableExpression::class, expression.settable::class)
    }
}