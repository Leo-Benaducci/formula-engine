package br.com.lbenaducci.formula.engine.lexer.types

import br.com.lbenaducci.formula.engine.lexer.Carriage
import br.com.lbenaducci.formula.engine.lexer.Token

object Equal : TokenType {
    override val alias: String
        get() = "EQUALS"

    override fun matches(char: Char): Boolean {
        return char == '='
    }

    override fun tokenize(carriage: Carriage): Token {
        return Token(this, carriage.peek().toString(), carriage.position)
    }
}
