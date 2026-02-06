package br.com.lbenaducci.formula.engine.lexer

import br.com.lbenaducci.formula.engine.lexer.types.CompoundedTokenType
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
        if (type is CompoundedTokenType) {
            return "$position:${type.alias}(\"$lexeme\")"
        }
        return "$position:${type.alias}"
    }
}