package br.com.lbenaducci.formula.engine.lexer.types

import br.com.lbenaducci.formula.engine.lexer.Carriage
import br.com.lbenaducci.formula.engine.lexer.Token

object Number : TokenType {
    override val alias: String
        get() = "NUMBER"

    override fun matches(char: Char): Boolean {
        return char.isDigit()
    }

    override fun tokenize(carriage: Carriage): Token {
        val start = carriage.position
        var peek = carriage.peek()
        val lexeme = StringBuilder()
        val consumeDigit = { digit: Char ->
            lexeme.append(digit)
            carriage.advance()
        }

        while (matches(peek)) {
            consumeDigit(peek)
            peek = carriage.peek()
        }
        if (peek == '.') {
            consumeDigit(peek)
            peek = carriage.peek()
            while (matches(peek)) {
                consumeDigit(peek)
                peek = carriage.peek()
            }
        }
        return Token(this, lexeme.toString(), start)
    }
}