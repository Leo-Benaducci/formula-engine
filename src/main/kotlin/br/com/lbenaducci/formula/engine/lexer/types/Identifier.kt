package br.com.lbenaducci.formula.engine.lexer.types

import br.com.lbenaducci.formula.engine.lexer.Carriage
import br.com.lbenaducci.formula.engine.lexer.Token

object Identifier : CompoundedTokenType {
    override val alias: String
        get() = "IDENTIFIER"

    override fun matches(char: Char): Boolean {
        return char.isLetter()
    }

    override fun tokenize(carriage: Carriage): Token {
        val start = carriage.position
        var peek = carriage.peek()
        val lexeme = StringBuilder()
        while (matches(peek)) {
            lexeme.append(peek)
            carriage.advance()
            peek = carriage.peek()
        }
        return Token(this, lexeme.toString(), start)
    }
}