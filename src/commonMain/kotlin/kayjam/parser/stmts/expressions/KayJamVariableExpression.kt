package kayjam.parser.stmts.expressions

import kayjam.parser.KayJamType
import kayjam.parser.stmts.KayJamExpression
import kayjam.parser.stmts.KayJamSettableExpression
import kayjam.parser.stmts.KayJamStmt

class KayJamVariableExpression(val variableName: String, type: KayJamType):
        KayJamSettableExpression(variableName, type)