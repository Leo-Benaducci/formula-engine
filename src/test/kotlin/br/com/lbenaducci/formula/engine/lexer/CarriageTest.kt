package br.com.lbenaducci.formula.engine.lexer

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

class CarriageTest {
    @Nested
    inner class Constructor {
        @Test
        fun `given content, then instantiate Carriage`() {
            val content = "string"
            val carriage = Carriage(content)
            assertNotNull(carriage)
            assertEquals(0, carriage.position)
        }
    }

    @Nested
    inner class Peek {
        @Test
        fun `given carriage, then return peeked char`() {
            val carriage = Carriage("string")
            assertEquals('s', carriage.peek())
        }

        @Test
        fun `given empty carriage, then return null ascii`() {
            val carriage = Carriage("")
            assertEquals('\u0000', carriage.peek())
        }
    }

    @Nested
    inner class Advance {
        @Test
        fun `given carriage, then advance position`() {
            val carriage = Carriage("string")
            assertEquals('s', carriage.peek())
            assertEquals(0, carriage.position)
            carriage.advance()
            assertEquals(1, carriage.position)
            assertEquals('t', carriage.peek())
            carriage.advance()
            assertEquals(2, carriage.position)
            assertEquals('r', carriage.peek())
            carriage.advance()
            assertEquals(3, carriage.position)
            assertEquals('i', carriage.peek())
            carriage.advance()
            assertEquals(4, carriage.position)
            assertEquals('n', carriage.peek())
            carriage.advance()
            assertEquals(5, carriage.position)
            assertEquals('g', carriage.peek())
            carriage.advance()
            assertEquals(6, carriage.position)
            assertEquals('\u0000', carriage.peek())
            carriage.advance()
            assertEquals(6, carriage.position)
            assertEquals('\u0000', carriage.peek())
        }
    }
}