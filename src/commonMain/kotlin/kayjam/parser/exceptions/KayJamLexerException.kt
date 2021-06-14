package kayjam.parser.exceptions

import kayjam.parser.KayJamLexer

class KayJamLexerException(lexer: KayJamLexer, message: String):
    Exception("LexerException: $message on line ${lexer.position.line}, "+
            "position ${lexer.position.position} in ${lexer.source}")