package br.com.lbenaducci.formula.engine.lexer.types

import br.com.lbenaducci.formula.engine.lexer.Carriage
import br.com.lbenaducci.formula.engine.lexer.Token

object End : TokenType {
    override val alias: String
        get() = "END"

    override fun matches(char: Char): Boolean {
        return char == '\u0000'
    }

    override fun tokenize(carriage: Carriage): Token {
        return Token(this, carriage.peek().toString(), carriage.position)
    }
}

