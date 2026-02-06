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

class AssignTest {
    @Nested
    inner class Alias {
        @Test
        fun `then return alias`() {
            assertEquals("ASSIGN", Assign.alias)
        }
    }

    @Nested
    inner class Matches {
        @Test
        fun `given equal char, then return true`() {
            assertTrue { Assign.matches('=') }
        }

        @Test
        fun `given other char, then return false`() {
            assertFalse { Assign.matches('a') }
        }
    }

    @Nested
    inner class Tokenize {
        @Test
        fun `given lexer, then return token`() {
            val carriage = mock<Carriage> {
                on { peek() } doReturn '='
                on { position } doReturn 0
            }

            val token = Assign.tokenize(carriage)

            assertIs<Assign>(token.type)
            assertEquals("=", token.lexeme)
            assertEquals(0, token.position)
        }
    }
}