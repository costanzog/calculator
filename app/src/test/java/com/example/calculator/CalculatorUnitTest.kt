package com.example.calculator

import androidx.appcompat.widget.AppCompatButton
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class CalculatorUnitTest {
    //Simple number buttons
    private lateinit var calculator: Calculator


    @Before
    fun setup(){
        calculator = Calculator()
    }

    @Test
    fun numbers1(){
        calculator.checkAddChar(calculator.getNumbers()[0])
        assertEquals(calculator.getOutput(),"1")
    }
    @Test
    fun number2(){
        calculator.checkAddChar(calculator.getNumbers()[7])
        calculator.checkAddChar(calculator.getNumbers()[2])
        calculator.checkAddChar(calculator.getNumbers()[3])
        assertEquals(calculator.getOutput(), "834")
    }

    //Operation buttons

    fun operation1(operator: Char){
        calculator.handleOperatorButtons(operator)
    }
    fun operation2(operator: Char){
        calculator.checkAddChar(calculator.getNumbers()[0])
        calculator.handleOperatorButtons(operator)
    }
    fun operation3(operator: Char){
        calculator.checkAddChar(calculator.getNumbers()[0])
        calculator.handleOperatorButtons(operator)
        calculator.handleOperatorButtons(operator)
    }


    @Test
    fun plus1(){
        operation1(calculator.getOperators()[0])
        assertEquals(calculator.getOutput(), "0")
    }
    @Test
    fun plus2(){
        operation2(calculator.getOperators()[0])
        assertEquals(calculator.getOutput(), "1+")
    }
    @Test
    fun plus3(){
        operation3(calculator.getOperators()[0])
        assertEquals(calculator.getOutput(), "1+")
    }

    @Test
    fun minus1(){
        operation1(calculator.getOperators()[1])
        assertEquals(calculator.getOutput(), "0")
    }
    @Test
    fun minus2(){
        operation2(calculator.getOperators()[1])
        assertEquals(calculator.getOutput(), "1-")
    }
    @Test
    fun minus3(){
        operation3(calculator.getOperators()[1])
        assertEquals(calculator.getOutput(), "1-")
    }

    @Test
    fun multiply1(){
        operation1(calculator.getOperators()[2])
        assertEquals(calculator.getOutput(), "0")
    }
    @Test
    fun multiply2(){
        operation2(calculator.getOperators()[2])
        assertEquals(calculator.getOutput(), "1x")
    }
    @Test
    fun multiply3(){
        operation3(calculator.getOperators()[2])
        assertEquals(calculator.getOutput(), "1x")
    }

    @Test
    fun divide1(){
        operation1(calculator.getOperators()[3])
        assertEquals(calculator.getOutput(), "0")
    }
    @Test
    fun divide2(){
        operation2(calculator.getOperators()[3])
        assertEquals(calculator.getOutput(), "1/")
    }
    @Test
    fun divide3(){
        operation3(calculator.getOperators()[3])
        assertEquals(calculator.getOutput(), "1/")
    }

    
    //Complicated/Miscellaneous buttons
    @Test
    fun backspace(){
        calculator.backspaceChar()
        assertEquals(calculator.getOutput(), "0")
    }
    @Test
    fun backspace2(){
        calculator.checkAddChar(calculator.getNumbers()[0])
        calculator.backspaceChar()
        assertEquals(calculator.getOutput(), "0")
    }
    @Test
    fun backspace3(){
        calculator.checkAddChar(calculator.getNumbers()[0])
        calculator.checkAddChar(calculator.getNumbers()[0])
        calculator.backspaceChar()
        assertEquals(calculator.getOutput(), "1")
    }
    @Test
    fun clear(){
        calculator.clear()
        assertEquals(calculator.getOutput(),"0")
    }
    @Test
    fun clear2(){
        calculator.checkAddChar(calculator.getNumbers()[3])
        calculator.checkAddChar(calculator.getNumbers()[4])
        calculator.clear()
        assertEquals(calculator.getOutput(), "0")
    }
    @Test
    fun clear3(){
        calculator.checkAddChar(calculator.getNumbers()[3])
        calculator.checkAddChar(calculator.getNumbers()[4])
        calculator.clear()
        calculator.checkAddChar(calculator.getNumbers()[2])
        assertEquals(calculator.getOutput(), "3")
    }
    @Test
    fun decimal(){
        calculator.addDecimal()
        assertEquals(calculator.getOutput(), "0.")
    }
    @Test
    fun decimal2(){
        calculator.addDecimal()
        calculator.addDecimal()
        assertEquals(calculator.getOutput(), "0.")
    }
    @Test
    fun decimal3(){
        calculator.checkAddChar(calculator.getNumbers()[3])
        calculator.addDecimal()
        calculator.checkAddChar(calculator.getNumbers()[5])
        assertEquals(calculator.getOutput(), "4.6")
    }
    @Test
    fun decimal4(){
        calculator.checkAddChar(calculator.getNumbers()[4])
        calculator.addDecimal()
        calculator.addDecimal()
        assertEquals(calculator.getOutput(), "5.")
    }
    @Test
    fun decimal5(){
        calculator.checkAddChar(calculator.getNumbers()[0])
        calculator.addDecimal()
        calculator.checkAddChar(calculator.getNumbers()[3])
        calculator.addDecimal()
        assertEquals(calculator.getOutput(), "1.4")
    }
    @Test
    fun decimal6(){
        calculator.checkAddChar(calculator.getNumbers()[0])
        calculator.handleOperatorButtons(calculator.getOperators()[1])
        calculator.addDecimal()
        assertEquals(calculator.getOutput(), "1-.")
    }
    @Test
    fun zero1(){
        calculator.handlebutton0()
        assertEquals(calculator.getOutput(), "0")
    }
    @Test
    fun zero2(){
        calculator.checkAddChar(calculator.getNumbers()[6])
        calculator.handlebutton0()
        assertEquals(calculator.getOutput(), "70")
    }
    @Test
    fun zero3(){
        calculator.checkAddChar(calculator.getNumbers()[8])
        calculator.addDecimal()
        calculator.handlebutton0()
        calculator.handlebutton0()
        assertEquals(calculator.getOutput(), "9.00")
    }
    @Test
    fun zero4(){
        calculator.checkAddChar(calculator.getNumbers()[8])
        calculator.handlebutton0()
        calculator.handlebutton0()
        assertEquals(calculator.getOutput(), "900")
    }
    @Test
    fun zero5(){
        calculator.checkAddChar(calculator.getNumbers()[3])
        calculator.handleOperatorButtons(calculator.getOperators()[0])
        calculator.handlebutton0()
        calculator.handlebutton0()
        assertEquals(calculator.getOutput(), "4+0")
    }
    @Test
    fun parentheses(){
        calculator.addParentheses()
        assertEquals(calculator.getOutput(), "(")
    }
    @Test
    fun parentheses2(){
        calculator.addParentheses()
        calculator.addParentheses()
        assertEquals(calculator.getOutput(), "()")
    }
    @Test
    fun parentheses3(){
        calculator.addParentheses()
        calculator.checkAddChar(calculator.getNumbers()[4])
        calculator.addParentheses()
        assertEquals(calculator.getOutput(), "(5)")
    }
    @Test
    fun parentheses4(){
        calculator.addParentheses()
        calculator.backspaceChar()
        calculator.addParentheses()
        assertEquals(calculator.getOutput(), "(")
    }
    @Test
    fun negative1(){
        calculator.handleNegative()
        assertEquals(calculator.getOutput(), "(−")
    }
    @Test
    fun negative2(){
        calculator.handleNegative()
        calculator.handleNegative()
        assertEquals(calculator.getOutput(), "0")
    }
    @Test
    fun negative3(){
        calculator.checkAddChar(calculator.getNumbers()[4])
        calculator.checkAddChar(calculator.getNumbers()[5])
        calculator.handleNegative()
        assertEquals(calculator.getOutput(), "−56")
    }
    @Test
    fun negative4(){
        calculator.checkAddChar(calculator.getNumbers()[3])
        calculator.checkAddChar(calculator.getNumbers()[8])
        calculator.handleNegative()
        calculator.handleNegative()
        assertEquals(calculator.getOutput(), "49")
    }
    @Test
    fun negative5(){
        calculator.checkAddChar(calculator.getNumbers()[3])
        calculator.checkAddChar(calculator.getNumbers()[8])
        calculator.handleOperatorButtons(calculator.getOperators()[2])
        calculator.handleNegative()
        assertEquals(calculator.getOutput(), "49x(−")
    }
    @Test
    fun negative6(){
        calculator.checkAddChar(calculator.getNumbers()[3])
        calculator.checkAddChar(calculator.getNumbers()[8])
        calculator.handleOperatorButtons(calculator.getOperators()[2])
        calculator.handleNegative()
        assertEquals(calculator.getOutput(), "49x(−")
    }
    @Test
    fun negative7(){
        calculator.checkAddChar(calculator.getNumbers()[1])
        calculator.checkAddChar(calculator.getNumbers()[7])
        calculator.handleOperatorButtons(calculator.getOperators()[2])
        calculator.handleNegative()
        calculator.handleNegative()
        assertEquals(calculator.getOutput(), "28x")
    }
    @Test
    fun equals(){
        calculator.solve()
        assertEquals(calculator.getOutput(), "0")
    }
    @Test
    fun equals2(){
        calculator.checkAddChar(calculator.getNumbers()[5])
        calculator.solve()
        assertEquals(calculator.getOutput(), "6")
    }
    @Test
    fun equals3(){
        calculator.checkAddChar(calculator.getNumbers()[5])
        calculator.handleOperatorButtons(calculator.getOperators()[0])
        calculator.solve()
        assertEquals(calculator.getOutput(), "6")
    }
    @Test
    fun equals4(){
        calculator.checkAddChar(calculator.getNumbers()[5])
        calculator.handleOperatorButtons(calculator.getOperators()[0])
        calculator.checkAddChar(calculator.getNumbers()[5])
        calculator.solve()
        assertEquals(calculator.getOutput(), "12") //6+6
    }
    @Test
    fun equals5(){
        calculator.checkAddChar(calculator.getNumbers()[5])
        calculator.handleOperatorButtons(calculator.getOperators()[1])
        calculator.checkAddChar(calculator.getNumbers()[5])
        calculator.solve()
        assertEquals(calculator.getOutput(), "0") //6-6
    }
    @Test
    fun equals6(){
        calculator.checkAddChar(calculator.getNumbers()[5])
        calculator.handleOperatorButtons(calculator.getOperators()[2])
        calculator.checkAddChar(calculator.getNumbers()[5])
        calculator.solve()
        assertEquals(calculator.getOutput(), "36") //6*6
    }
    @Test
    fun equals7(){
        calculator.checkAddChar(calculator.getNumbers()[5])
        calculator.handleOperatorButtons(calculator.getOperators()[3])
        calculator.checkAddChar(calculator.getNumbers()[5])
        calculator.solve()
        assertEquals(calculator.getOutput(), "1") //6/6
    }












}