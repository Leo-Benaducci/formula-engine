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

class NumberTest {
    @Nested
    inner class Alias {
        @Test
        fun `then return alias`() {
            assertEquals("NUMBER", Number.alias)
        }
    }

    @Nested
    inner class Matches {
        @Test
        fun `given number char, then return true`() {
            assertTrue { Number.matches('0') }
        }

        @Test
        fun `given other char, then return false`() {
            assertFalse { Number.matches('a') }
        }
    }

    @Nested
    inner class Tokenize {
        @Test
        fun `given carriage with simple number, then return token`() {
            val carriage = mock<Carriage> {
                on { peek() }.doReturn('1', '0', ' ')
                on { position }.doReturn(0, 1, 2)
            }

            val token = Number.tokenize(carriage)

            assertIs<Number>(token.type)
            assertEquals("10", token.lexeme)
            assertEquals(0, token.position)
        }

        @Test
        fun `given carriage with number with decimal, then return token`() {
            val carriage = mock<Carriage> {
                on { peek() }.doReturn('1', '.', '5', ' ')
                on { position }.doReturn(0, 1, 2, 3)
            }

            val token = Number.tokenize(carriage)

            assertIs<Number>(token.type)
            assertEquals("1.5", token.lexeme)
            assertEquals(0, token.position)
        }

        @Test
        fun `given carriage with number with invalid decimal separator, then return token`() {
            val carriage = mock<Carriage> {
                on { peek() }.doReturn('1', '@', '5', ' ')
                on { position }.doReturn(0, 1, 2, 3)
            }

            val token = Number.tokenize(carriage)

            assertIs<Number>(token.type)
            assertEquals("1", token.lexeme)
            assertEquals(0, token.position)
        }

        @Test
        fun `given carriage with multiple decimal separators, then return token`() {
            val carriage = mock<Carriage> {
                on { peek() }.doReturn('1', '.', '5', '.', '2', ' ')
                on { position }.doReturn(0, 1, 2, 3, 4, 5)
            }

            val token = Number.tokenize(carriage)

            assertIs<Number>(token.type)
            assertEquals("1.5", token.lexeme)
            assertEquals(0, token.position)
        }
    }
}