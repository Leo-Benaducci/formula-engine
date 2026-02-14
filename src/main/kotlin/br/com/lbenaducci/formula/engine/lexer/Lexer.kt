package br.com.lbenaducci.formula.engine.lexer

import br.com.lbenaducci.formula.engine.exceptions.LexicalException
import br.com.lbenaducci.formula.engine.lexer.types.CompoundedTokenType
import br.com.lbenaducci.formula.engine.lexer.types.End
import br.com.lbenaducci.formula.engine.lexer.types.TokenType

class Lexer(
    content: String,
    private val types: List<TokenType>
) {
    private val carriage: Carriage = Carriage(content)

    fun tokenize(): List<Token> {
        val tokens = mutableListOf<Token>()

        while (true) {
            val peek = carriage.peek()
            if (peek.isWhitespace()) {
                carriage.advance()
                continue
            }
            val type = types.firstOrNull { it.matches(peek) }
                ?: End.takeIf { it.matches(peek) }
                ?: throw LexicalException(peek, carriage.position)

            val token = type.tokenize(carriage)
            tokens.add(token)

            if (type == End) {
                return tokens
            }
            if (type !is CompoundedTokenType) {
                carriage.advance()
            }
        }
    }
}