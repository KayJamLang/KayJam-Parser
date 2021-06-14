package kayjam.parser

class Position(val line: Int = 0, val position: Int = 0){
    override fun toString(): String {
        return "$line:$position"
    }
}