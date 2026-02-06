package br.com.lbenaducci.formula.engine.lexer.types

import br.com.lbenaducci.formula.engine.lexer.Carriage
import br.com.lbenaducci.formula.engine.lexer.Token

object Division : TokenType {
    override val alias: String
        get() = "DIVIDED BY"

    override fun matches(char: Char): Boolean {
        return char == '/'
    }

    override fun tokenize(carriage: Carriage): Token {
        return Token(this, carriage.peek().toString(), carriage.position)
    }
}