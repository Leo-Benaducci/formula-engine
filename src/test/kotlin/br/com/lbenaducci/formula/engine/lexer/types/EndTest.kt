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

class EndTest {
    @Nested
    inner class Alias {
        @Test
        fun `then return alias`() {
            assertEquals("END", End.alias)
        }
    }

    @Nested
    inner class Matches {
        @Test
        fun `given null ascii char, then return true`() {
            assertTrue { End.matches('\u0000') }
        }

        @Test
        fun `given other char, then return false`() {
            assertFalse { End.matches('a') }
        }
    }

    @Nested
    inner class Tokenize {
        @Test
        fun `given carriage, then return token`() {
            val carriage = mock<Carriage> {
                on { peek() } doReturn '\u0000'
                on { position } doReturn 0
            }

            val token = End.tokenize(carriage)

            assertIs<End>(token.type)
            assertEquals("\u0000", token.lexeme)
            assertEquals(0, token.position)
        }
    }
}
