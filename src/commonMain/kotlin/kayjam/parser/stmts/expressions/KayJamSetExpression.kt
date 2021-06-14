package kayjam.parser.stmts.expressions

import kayjam.parser.stmts.KayJamExpression
import kayjam.parser.stmts.KayJamSettableExpression

class KayJamSetExpression(val settable: KayJamSettableExpression,
                          val value: KayJamExpression): KayJamExpression(value.type)