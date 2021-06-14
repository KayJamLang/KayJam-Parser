package kayjam.parser.tests

import kayjam.parser.KayJamLexer
import kayjam.parser.exceptions.KayJamLexerException
import org.junit.Test

class LexerTests {

    @Test
    fun baseLex(){
        val lexer = KayJamLexer("""var test = 123;""".trimIndent())

        var token = lexer.currentToken
        while (!lexer.finished){
            if(token!=null) println("${token.type}: ${token.value} ${token.position}")
            if(!lexer.finished) token = lexer.moveAhead()
        }
    }

    @Test(expected = KayJamLexerException::class)
    fun errorLex(){
        val lexer = KayJamLexer("""%35""".trimIndent())

        var token = lexer.currentToken
        while (!lexer.finished){
            if(token!=null) println("${token.type}: ${token.value} ${token.position}")
            token = lexer.moveAhead()
        }
    }
}