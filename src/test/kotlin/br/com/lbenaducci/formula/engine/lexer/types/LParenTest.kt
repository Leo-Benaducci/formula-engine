package br.com.lbenaducci.formula.engine.lexer.types

import br.com.lbenaducci.formula.engine.lexer.Carriage
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class LParenTest {
    @Nested
    inner class Alias {
        @Test
        fun `then return alias`() {
            assertEquals("LPAREN", LParen.alias)
        }
    }

    @Nested
    inner class Matches {
        @Test
        fun `given left parenthesis char, then return true`() {
            assertTrue { LParen.matches('(') }
        }

        @Test
        fun `given other char, then return false`() {
            assertFalse { LParen.matches('a') }
        }
    }

    @Nested
    inner class Tokenize {
        @Test
        fun `given carriage, then return token`() {
            val carriage = mock<Carriage> {
                on { peek() } doReturn '('
                on { position } doReturn 0
            }

            val token = LParen.tokenize(carriage)

            assertIs<LParen>(token.type)
            assertEquals("(", token.lexeme)
            assertEquals(0, token.position)
        }
    }
}