package br.com.lbenaducci.formula.engine.exceptions

open class FormulaException(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)