package kayjam.parser.stmts.expressions

import kayjam.parser.KayJamType
import kayjam.parser.stmts.KayJamExpression

class KayJamValueExpression(val value: Any?, type: KayJamType): KayJamExpression(type)