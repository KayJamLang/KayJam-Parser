package kayjam.parser

class Token(val type: Type, val value: String, val position: Position) {

    enum class Type(regex: String) {
        COMMENT ("//(.+)"),

        TK_NAMESPACE_DELIMITER("\\\\"),
        TK_REF ("->"),
        TK_ANNOTATION ("\\@"),
        TK_NEW_LINE ("\\R"),
        TK_COMPANION_ACCESS ("::"),
        TK_COLON (":"),
        TK_OPEN ("\\("),
        TK_CLOSE ("\\)"),
        TK_SEMI (";"),
        TK_COMMA (","),
        TK_NULLABLE ("\\?"),
        TK_OPEN_SQUARE_BRACKET("\\["),
        TK_CLOSE_SQUARE_BRACKET("\\]"),
        OPEN_BRACKET ("\\{"),
        CLOSE_BRACKET ("\\}"),
        DIFFERENT ("<>"),

        BOOL ("true|false"),
        NULL ("null"),
        STRING ("\"([^\"]+)\"|\"()\"|\'([^\']+)\'|\'()\'"),
        DOUBLE ("\\d*\\.\\d+"),
        LONG ("(\\d+)[lL]"),
        INTEGER ("\\d+"),
        IDENTIFIER ("(\\w+)"),

        //Short operations
        TK_PLUS_ONE ("\\+\\+"),
        TK_MINUS_ONE ("\\-\\-"),

        //Binary operations
        TK_MINUS ("-"),
        TK_PLUS ("\\+"),
        TK_MUL ("\\*"),
        TK_DIV ("/"),

        TK_AND ("&&"),
        TK_OR ("\\|\\|"),
        TK_EQUALS ("=="),
        TK_NOT_EQUALS ("!="),

        TK_LESS_SIGN ("<"),
        TK_LESS_EQUALS_SIGN ("<="),
        TK_GREATER_SIGN (">"),
        TK_GREATER_EQUALS_SIGN (">="),

        TK_RANGE ("\\.\\."),

        TK_NOT ("!"),
        TK_ASSIGN ("="),
        TK_ACCESS ("\\.");

        private val regex = Regex("^$regex")

        fun match(s: String): MatchResult? {
            return regex.find(s)
        }
    }
}