package br.com.lbenaducci.formula.engine.lexer

import br.com.lbenaducci.formula.engine.lexer.types.TokenType
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import kotlin.test.Test
import kotlin.test.assertEquals

class TokenTest {
    @Nested
    inner class Constructor {
        @Test
        fun `given negative position, then throw exception`() {
            val type = mock<TokenType>()
            val lexeme = "lexeme"
            val position = -1
            val exception = assertThrows<IllegalArgumentException> { Token(type, lexeme, position) }
            assertEquals("Position must be non-negative", exception.message)
        }

        @Test
        fun `given valid arguments, then create token`() {
            val type = mock<TokenType>()
            val lexeme = "lexeme"
            val position = 0
            val token = Token(type, lexeme, position)
            assertEquals(type, token.type)
            assertEquals(lexeme, token.lexeme)
            assertEquals(position, token.position)
        }
    }

    @Nested
    inner class ToString {
        @Test
        fun `given token, then return string representation`() {
            val type = mock<TokenType> {
                on { alias } doReturn "mock"
            }
            val lexeme = "lexeme"
            val position = 0
            val token = Token(type, lexeme, position)
            assertEquals("mock(lexeme, 0)", token.toString())
        }
    }
}