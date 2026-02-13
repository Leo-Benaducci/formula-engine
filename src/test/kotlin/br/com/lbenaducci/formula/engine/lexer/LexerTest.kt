package br.com.lbenaducci.formula.engine.lexer

import br.com.lbenaducci.formula.engine.exceptions.LexicalException
import br.com.lbenaducci.formula.engine.lexer.types.*
import br.com.lbenaducci.formula.engine.lexer.types.Number
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNotNull

class LexerTest {
    @Nested
    inner class Constructor {
        @Test
        fun `given params, then instantiate Lexer`() {
            val content = "string"
            val tokenTypes = listOf<TokenType>()
            val lexer = Lexer(content, tokenTypes)
            assertNotNull(lexer)
        }
    }

    @Nested
    inner class Tokenize {
        @Test
        fun `given invalid content, then throw exception`() {
            val content = "invalid"
            val tokenTypes = listOf<TokenType>()
            val lexer = Lexer(content, tokenTypes)

            assertThrows<LexicalException> { lexer.tokenize() }.also {
                assertEquals("Unresolved reference 'i' at position 0", it.message)
            }
        }

        @Test
        fun `given empty content, then return end`() {
            val content = ""
            val tokenTypes = listOf<TokenType>()
            val lexer = Lexer(content, tokenTypes)

            val tokens = lexer.tokenize()

            assertNotNull(tokens)
            assertEquals(1, tokens.size)
            assertIs<End>(tokens[0].type)
        }

        @Test
        fun `given content for a simple token, then return token`() {
            val content = "+"
            val tokenTypes = listOf(Addition)
            val lexer = Lexer(content, tokenTypes)

            val tokens = lexer.tokenize()

            assertNotNull(tokens)
            assertEquals(2, tokens.size)
            assertIs<Addition>(tokens[0].type)
            assertIs<End>(tokens[1].type)
        }

        @Test
        fun `given content for a compounded token, then return token`() {
            val content = "sum"
            val tokenTypes = listOf(Identifier)
            val lexer = Lexer(content, tokenTypes)

            val tokens = lexer.tokenize()

            assertNotNull(tokens)
            assertEquals(2, tokens.size)
            assertIs<Identifier>(tokens[0].type)
            assertEquals("sum", tokens[0].lexeme)
            assertEquals(0, tokens[0].position)
            assertIs<End>(tokens[1].type)
            assertEquals(3, tokens[1].position)
        }

        @Test
        fun `given content for multiple tokens, then return tokens`() {
            val content = "sum = 10 + 5"
            val tokenTypes = listOf(Addition, Identifier, Equality, Number)
            val lexer = Lexer(content, tokenTypes)

            val tokens = lexer.tokenize()

            assertNotNull(tokens)
            assertEquals(6, tokens.size)
            assertIs<Identifier>(tokens[0].type)
            assertEquals("sum", tokens[0].lexeme)
            assertIs<Equality>(tokens[1].type)
            assertEquals(4, tokens[1].position)
            assertIs<Number>(tokens[2].type)
            assertEquals("10", tokens[2].lexeme)
            assertEquals(6, tokens[2].position)
            assertIs<Addition>(tokens[3].type)
            assertEquals(9, tokens[3].position)
            assertIs<Number>(tokens[4].type)
            assertEquals("5", tokens[4].lexeme)
            assertEquals(11, tokens[4].position)
            assertIs<End>(tokens[5].type)
            assertEquals(12, tokens[5].position)
        }

        @Test
        fun `given content for multiple tokens with spaces, then return tokens`() {
            val content = "  a    =    1.5  "
            val tokenTypes = listOf(Identifier, Equality, Number)
            val lexer = Lexer(content, tokenTypes)

            val tokens = lexer.tokenize()

            assertNotNull(tokens)
            assertEquals(4, tokens.size)
            assertIs<Identifier>(tokens[0].type)
            assertEquals("a", tokens[0].lexeme)
            assertEquals(2, tokens[0].position)
            assertIs<Equality>(tokens[1].type)
            assertEquals(7, tokens[1].position)
            assertIs<Number>(tokens[2].type)
            assertEquals("1.5", tokens[2].lexeme)
            assertEquals(12, tokens[2].position)
            assertIs<End>(tokens[3].type)
            assertEquals(17, tokens[3].position)
        }

        @Test
        fun `given content with unresolved token, then throw exception`() {
            val content = "a = 10@5"
            val tokenTypes = listOf(Identifier, Equality, Number)
            val lexer = Lexer(content, tokenTypes)

            assertThrows<LexicalException> { lexer.tokenize() }.also {
                assertEquals("Unresolved reference '@' at position 6", it.message)
            }
        }
    }
}