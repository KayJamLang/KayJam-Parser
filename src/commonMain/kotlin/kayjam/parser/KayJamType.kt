package kayjam.parser

class KayJamType(var name: String, var nullable: Boolean = false,
                 val primitive: Boolean = false) {

    companion object {
        val STRING = KayJamType("\\string", primitive = true)
        val INT = KayJamType("\\int", primitive = true)
        val LONG = KayJamType("\\long", primitive = true)
        val DOUBLE = KayJamType("\\double", primitive = true)
        val CLASS = KayJamType("\\class", primitive = true)
        val NOTHING = KayJamType("Nothing", nullable = true, primitive = true)
    }
}