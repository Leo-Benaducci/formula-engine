package br.com.lbenaducci.formula.engine.lexer.types

import br.com.lbenaducci.formula.engine.lexer.Carriage
import br.com.lbenaducci.formula.engine.lexer.Token

object Number : CompoundedTokenType {
    override val alias: String
        get() = "NUMBER"

    override fun matches(char: Char): Boolean {
        return char.isDigit()
    }

    override fun tokenize(carriage: Carriage): Token {
        val start = carriage.position
        var peek = carriage.peek()
        val lexeme = StringBuilder()
        var hasDecimal = false
        while (peek.isDigit() || (peek == '.' && !hasDecimal)) {
            if (peek == '.') {
                hasDecimal = true
            }
            lexeme.append(peek)
            carriage.advance()
            peek = carriage.peek()
        }
        return Token(this, lexeme.toString(), start)
    }
}
