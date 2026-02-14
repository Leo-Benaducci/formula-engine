package br.com.lbenaducci.formula.engine.exceptions

class LexicalException(
    invalidChar: Char,
    position: Int
) : FormulaException("Unresolved reference '$invalidChar' at position $position")
