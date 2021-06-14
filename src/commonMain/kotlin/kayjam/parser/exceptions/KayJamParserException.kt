package kayjam.parser.exceptions

import kayjam.parser.KayJamLexer
import kayjam.parser.KayJamParser

class KayJamParserException(parser: KayJamParser, message: String):
    Exception("LexerException: $message on line ${parser.lexer.position.line}, "+
            "position ${parser.lexer.position.position} in ${parser.lexer.source}")