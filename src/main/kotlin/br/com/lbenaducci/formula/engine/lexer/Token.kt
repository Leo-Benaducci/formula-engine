package br.com.lbenaducci.formula.engine.lexer

import br.com.lbenaducci.formula.engine.lexer.types.TokenType

data class Token(
    val type: TokenType,
    val lexeme: String,
    val position: Int
) {
    init {
        require(position >= 0) { "Position must be non-negative" }
    }

    override fun toString(): String {
        return "${type.alias}($lexeme, $position)"
    }
}