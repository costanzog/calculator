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
    private lateinit var calculator: Calculator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        output = binding.output

        calculator = Calculator()

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
                output.text = calculator.checkAddChar(button.text[0])
            }
        }
        //create listeners for operators and add their text to the screen, as long as theirs
        //a number preceding the operation
        for(button in operatorButtons){
            button.setOnClickListener {
                output.text = calculator.handleOperatorButtons(button.text[0])
            }
        }
        //the 0 button
        //the 0 shouldn't be stacked on a zero, meaning 000+4 shouldn't happen, otherwise add a 0
        binding.button0.setOnClickListener {
            output.text = calculator.handlebutton0()
        }

        binding.decimal.setOnClickListener {
            output.text = calculator.addDecimal()
        }
        //create listeners and functionality for all other buttons on the calculator
        binding.backspace.setOnClickListener{       //backspace
            output.text = calculator.backspaceChar()
        }
        binding.clear.setOnClickListener {          //clear
            output.text = calculator.clear()
        }

        //parentheses, should add a ) if the most recent parenthesis is an open one, otherwise, (
        binding.parentheses.setOnClickListener {    //parentheses
            output.text = calculator.addParentheses()
        }
        binding.negative.setOnClickListener {       //negative
            output.text = calculator.handleNegative()
        }

        //Uses the evaluator to calculate the answer and print it out in the output
        binding.equals.setOnClickListener {
            output.text = calculator.solve()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    //function to add a character to the end of the output

    fun getOutput(): String{
        return output.text.toString()
    }
}