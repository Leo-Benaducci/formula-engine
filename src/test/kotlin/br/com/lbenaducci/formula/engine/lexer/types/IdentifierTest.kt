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

class IdentifierTest {
    @Nested
    inner class Alias {
        @Test
        fun `then return alias`() {
            assertEquals("IDENTIFIER", Identifier.alias)
        }
    }

    @Nested
    inner class Matches {
        @Test
        fun `given letter char, then return true`() {
            assertTrue { Identifier.matches('a') }
        }

        @Test
        fun `given other char, then return false`() {
            assertFalse { Identifier.matches('0') }
        }
    }

    @Nested
    inner class Tokenize {
        @Test
        fun `given carriage with simple text, then return token`() {
            val carriage = mock<Carriage> {
                on { peek() }.doReturn('s', 'u', 'm', ' ')
                on { position }.doReturn(0, 1, 2, 3)
            }

            val token = Identifier.tokenize(carriage)

            assertIs<Identifier>(token.type)
            assertEquals("sum", token.lexeme)
            assertEquals(0, token.position)
        }

        @Test
        fun `given carriage with multiple cases, then return token`() {
            val carriage = mock<Carriage> {
                on { peek() }.doReturn('v', 'B', 'c', ' ')
                on { position }.doReturn(0, 1, 2, 3)
            }

            val token = Identifier.tokenize(carriage)

            assertIs<Identifier>(token.type)
            assertEquals("vBc", token.lexeme)
            assertEquals(0, token.position)
        }
    }
}