package com.example.calculator

import android.util.Log
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding
import android.widget.TextView
import org.mariuszgromada.math.mxparser.Expression



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var output: TextView
    private lateinit var answer: String
    private val minusSymbol = '-'
    private val negativeSymbol = '−'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        output = binding.output

        val numberButtons = listOf(
            binding.button1,
            binding.button2,
            binding.button3,
            binding.button4,
            binding.button5,
            binding.button6,
            binding.button7,
            binding.button8,
            binding.button9,
            binding.decimal
        )
        val operatorButtons = listOf(
            binding.plus,
            binding.minus,
            binding.multiply,
            binding.divide
        )
        //create listeners for the decimal and numbered buttons and add their text to the
        // output screen
        for(button in numberButtons){
            button.setOnClickListener {
                checkAddChar(button.text[0])
            }
        }
        //create listeners for operators and add their text to the screen, as long as theirs
        //a number preceding the operation
        for(button in operatorButtons){
            button.setOnClickListener {
                if(output.text.toString() != "0"){
                    addChar(button.text[0])
                }
            }
        }
        //the 0 button
        //the 0 shouldn't be stacked on a zero, meaning 000+4 shouldn't happen, otherwise add a 0
        binding.button0.setOnClickListener {
            var outputBackwards = output.text.toString().reversed()
            //this if is the most common case, the value at the end of the output screen is not a 0,
            //this means that a zero can be placed at the end
            if(outputBackwards[0] != '0'){
                addChar(binding.button0.text[0])
            }else{
                for(value in outputBackwards){ //iterates backwards through the output
                    //if a number or decimal is found then, the output looks like this ...(#)0
                    //(if the 0 wasn't there then the first if would have been true),
                    //this means that a 0 can be added
                    if(numberButtons.any({it.text[0] == value})){
                        addChar(binding.button0.text[0])
                        break
                    }
                    //if an operator is found before a number, then the output looks like this
                    // ...(#)+0 , (if the 0 wasn't there then the first if would have been true),
                    //so don't add any more 0s
                    if(operatorButtons.any({it.text[0] == value})){
                        break
                    }
                }
            }
        }

        //create listeners and functionality for all other buttons on the calculator
        binding.backspace.setOnClickListener{       //backspace
            if(output.text.length == 1){
                output.text = "0"
            }
            if(output.text.toString() != "0"){
                output.text = output.text.toString().substring(0,output.text.count()-1)
            }
        }
        binding.clear.setOnClickListener {          //clear
            output.text = "0"
        }

        //parentheses, should add a ) if the most recent parenthesis is an open one, otherwise, (
        binding.parentheses.setOnClickListener {    //parentheses
            var returnChar: Char = '('
            var outputBackwards = output.text.toString().reversed()
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
            addChar(returnChar)
        }
        binding.negative.setOnClickListener {       //negative
            val operations = listOf(
                '+',
                minusSymbol,
                'x',
                'd' //DIVIDE GOES HERE *********
                )
            var outputBackwards = output.text.toString().reversed()
            var negativeHandled = false
            var outputSize = outputBackwards.count()
            //check if a negative needs to be removed

            for(index in 0..(outputSize-1)){
                if(operations.contains(outputBackwards[index])){    //an operation was found, theres a term before the negative
                    output.text = output.text.toString().substring(0,outputSize-index) + "(" + negativeSymbol + output.text.toString().substring(outputSize-index)
                    negativeHandled = true
                    break
                }
                if(outputBackwards[index] == negativeSymbol && outputSize == index+1){   //there is already a negative, but theres only 1 number
                    output.text = output.text.toString().substring(1)
                    negativeHandled = true
                    break
                }
                if(outputBackwards[index] == negativeSymbol){    //there is already a negative, and theres more than 1 number
                    output.text = output.text.toString().substring(0,outputSize-index-2) + output.text.toString().substring(outputSize-index)
                    negativeHandled = true
                    break
                }               //MAKE THIS INTO A SWITCH STATEMENT
            }

            if(outputSize == 1 && output.text[0] == '0'){  //theres nothing in the output slot
                output.text = "(" + negativeSymbol
                negativeHandled = true
            }
            if(!negativeHandled){ //theres 1 number and no operators
                output.text = negativeSymbol + output.text.toString()
            }
        }

        //Uses the evaluator to calculate the answer and print it out in the output
        binding.equals.setOnClickListener {
            answer = Expression(output.text.toString()).calculate().toString()
            var answerBackwards = answer.reversed()
            if (answerBackwards[0] == '0' && answerBackwards[1] == '.') {
                answer = answer.substring(0, answer.length - 2)
            }
            if(answer == "NaN"){
                //change text color, and slight shake
            }else{
                output.text = answer
            }
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    //function to add a character to the end of the output
    fun addChar(charAdd: Char){
        output.text = output.text.toString() + charAdd
    }

    //function to check if a character should be added, or replace the output, if the output is empty
    fun checkAddChar(charAdd: Char){
        if(output.text.toString() == "0"){
            output.text = charAdd.toString()
        }else{
            addChar(charAdd)
        }
    }

}