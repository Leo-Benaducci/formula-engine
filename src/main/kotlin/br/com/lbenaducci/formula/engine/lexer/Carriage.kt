package br.com.lbenaducci.formula.engine.lexer

class Carriage(
    private val content: String
) {
    var position: Int = 0
        private set

    fun peek(): Char {
        if (position >= content.length) {
            return '\u0000'
        }
        return content[position]
    }

    fun advance() {
        if (position >= content.length) {
            return
        }
        position++
    }
}