package br.com.lbenaducci.formula.engine.lexer.types

import br.com.lbenaducci.formula.engine.lexer.Carriage
import br.com.lbenaducci.formula.engine.lexer.Token

interface TokenType {
    val alias: String

    fun matches(char: Char): Boolean

    fun tokenize(carriage: Carriage): Token
}