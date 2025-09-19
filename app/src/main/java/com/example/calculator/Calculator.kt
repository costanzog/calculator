package com.example.calculator

import org.mariuszgromada.math.mxparser.Expression

class Calculator {
    
    private var output = "0"
    private val minusSymbol = '-'
    private val negativeSymbol = '−'

    private val numbers = listOf('1', '2', '3', '4', '5', '6', '7', '8', '9')
    private val operators = listOf('+', minusSymbol, 'x', '/')



    fun addChar(charAdd: Char): String{
        output = output + charAdd
        return output
    }

    //function to check if a character should be added, or replace the output, if the output is empty
    fun checkAddChar(charAdd: Char): String{
        if(output == "0"){
            output = charAdd.toString()
        }else{
            addChar(charAdd)
        }
        return output
    }

    fun addDecimal(): String{
        var decimalHandled = false
        var backwardsOutput = output.reversed()
        for (char in backwardsOutput){
            when(char){
                in operators -> {
                    decimalHandled = true
                    addChar('.')
                    break
                }
                '.' -> {
                    decimalHandled = true
                    break
                }
            }
        }
        if(!decimalHandled){
            addChar('.')
        }
        return output
    }

    fun clear(): String{
        output = "0"
        return output
    }

    fun handleOperatorButtons(operator: Char): String{
        if(output.reversed()[0] != operator && output != "0"){
            addChar(operator)
        }
        return output
    }

    fun handlebutton0(): String{
        var outputBackwards = output.reversed()
        //this if is the most common case, the value at the end of the output screen is not a 0,
        //this means that a zero can be placed at the end
        if(outputBackwards[0] != '0'){
            addChar('0')
        }else{
            for(value in outputBackwards){ //iterates backwards through the output
                //if a number or decimal is found then, the output looks like this ...(#)0
                //(if the 0 wasn't there then the first if would have been true),
                //this means that a 0 can be added
                if(numbers.contains(value)){
                    addChar('0')
                    break
                }
                //if an operator is found before a number, then the output looks like this
                // ...(#)+0 , (if the 0 wasn't there then the first if would have been true),
                //so don't add any more 0s
                if(operators.contains(value)){
                    break
                }
            }
        }
        return output
    }

    //removes one character from the output, if theres only 1 character in there then it turns to 0,
    //unless the last char is 0, then it does nothing, will act as a "backspacer"
    fun backspaceChar(): String{
        if(output.length == 1){
            output = "0"
        }
        if(output != "0"){
            output = output.substring(0,output.count()-1)
        }
        return output
    }

    fun addParentheses(): String{
        var returnChar: Char = '('
        var outputBackwards = output.reversed()
        for(character in outputBackwards){
            if(character == '('){
                returnChar = ')'
                break
            }
            if(character == ')'){
                returnChar = '('
                break
            }
        }
        checkAddChar(returnChar)
        return output
    }

    // NEEDS COMMENT HERE FOR NEGATIVE
    fun handleNegative(): String{
        val operations = listOf(
            '+',
            minusSymbol,
            'x',
            'd' //DIVIDE GOES HERE *********
        )
        var outputBackwards = output.reversed()
        var negativeHandled = false
        var outputSize = outputBackwards.count()
        //check if a negative needs to be removed

        for(index in 0..(outputSize-1)){
            if(operations.contains(outputBackwards[index])){    //an operation was found, theres a term before the negative
                output = output.substring(0,outputSize-index) + "(" + negativeSymbol + output.substring(outputSize-index)
                negativeHandled = true
                break
            }
            if(outputBackwards[index] == negativeSymbol && outputSize == index+1){   //there is already a negative, but theres only 1 number
                output = output.substring(1)
                negativeHandled = true
                break
            }
            if(outputBackwards[index] == negativeSymbol){    //there is already a negative, and theres more than 1 number
                output = output.substring(0,outputSize-index-2) + output.substring(outputSize-index)
                if(output == ""){
                    output = "0"
                }
                negativeHandled = true
                break
            }               //MAKE THIS INTO A SWITCH STATEMENT
        }

        if(outputSize == 1 && output[0] == '0'){  //theres nothing in the output slot
            output = "(" + negativeSymbol
            negativeHandled = true
        }
        if(!negativeHandled){ //theres 1 number and no operators
            output = negativeSymbol + output
        }

        return output
    }

    fun solve(): String{
        var answer = Expression(output.replace('x','*')).calculate().toString()
        var answerBackwards = answer.reversed().replace('x','*')
        if (answerBackwards[0] == '0' && answerBackwards[1] == '.') {
            answer = answer.substring(0, answer.length - 2)
        }
        if(answer == "NaN"){
            //change text color, and slight shake
        }else{
            output = answer
        }
        return output
    }

    fun getOutput(): String{
        return output
    }
    fun getNumbers(): List<Char>{
        return numbers
    }
    fun getOperators(): List<Char>{
        return operators
    }

}