package br.com.lbenaducci.formula.engine.lexer

interface Carriage {
    val position: Int

    fun advance()

    fun peek(): Char
}