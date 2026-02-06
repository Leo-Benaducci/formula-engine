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

class DivisionTest {
    @Nested
    inner class Alias {
        @Test
        fun `then return alias`() {
            assertEquals("DIVIDED BY", Division.alias)
        }
    }

    @Nested
    inner class Matches {
        @Test
        fun `given slash char, then return true`() {
            assertTrue { Division.matches('/') }
        }

        @Test
        fun `given other char, then return false`() {
            assertFalse { Division.matches('a') }
        }
    }

    @Nested
    inner class Tokenize {
        @Test
        fun `given carriage, then return token`() {
            val carriage = mock<Carriage> {
                on { peek() } doReturn '/'
                on { position } doReturn 0
            }

            val token = Division.tokenize(carriage)

            assertIs<Division>(token.type)
            assertEquals("/", token.lexeme)
            assertEquals(0, token.position)
        }
    }
}