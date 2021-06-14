package kayjam.parser.stmts

import kayjam.parser.KayJamType

abstract class KayJamSettableExpression(val valueToSet: Any, type: KayJamType): KayJamExpression(type)