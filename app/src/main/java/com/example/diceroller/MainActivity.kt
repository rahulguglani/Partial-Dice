package com.example.diceroller
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * This activity allows. the user to roll a dice and view the result
 * on the screen.
 */
@Suppress("DEPRECATION")



class MainActivity : AppCompatActivity() {

    /**
     * This method is called when the Activity is created.
     */
    val code = "AABBABA"
    var d1 = 1
    var d2 = 2
    private var poss: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the Button in the layout
        val rollButton: Button = findViewById(R.id.button)
        val diceR : ImageView = findViewById(R.id.imageView2)
        val diceB : ImageView = findViewById(R.id.imageView3)
        diceR.setOnLongClickListener{ changeDice(1) }
        diceB.setOnLongClickListener { changeDice(2) }

        //when code is pressed dice should roll up 6 6

        diceR.setOnClickListener { chkPoss(1) }
        diceB.setOnClickListener { chkPoss(2) }


        // Set a click listener on the button to roll the dice when the user taps the button
        rollButton.setOnClickListener {
            if(poss==7)
            {
                if(d1==1)
                    diceR.setImageResource(R.drawable.dice_red_6)
                else
                    diceR.setImageResource(R.drawable.dice_blue_6)

                if(d2==2)
                    diceB.setImageResource(R.drawable.dice_blue_6)
                else
                    diceB.setImageResource(R.drawable.dice_red_6)

                poss=0
//                val tV : TextView = findViewById(R.id.textView)
//                tV.text = poss.toString()

            }
            else
            {
                rollDice()
            }
        }
    }

    fun chkPoss(d: Int)
    {
       vibratee(50)
        if(d==1)
        {
            when(poss)
            {
                0->poss=1
                1->poss=2
                2->poss=0
                3->poss=0
                4->poss=5
                5->poss=0
                6->poss=7
            }
        }
        if(d==2)
        {
            when(poss)
            {
                0->poss=0
                1->poss=0
                2->poss=3
                3->poss=4
                4->poss=0
                5->poss=6
                6->poss=0
            }
        }

//        val tV : TextView = findViewById(R.id.textView)
//        tV.text = poss.toString()
    }

    fun rollDice() {
//        val numSidesR: Int = findViewById(R.id.editTextNumber)

        // Create new Dice object with 6 sides and roll it
        val dice_red = Dice(6)
        val dice_blue = Dice(6)
        val diceRoll_red = dice_red.roll()
        val diceRoll_blue = dice_blue.roll()

        // Update the screen with the dice roll
        val diceImage_red : ImageView = findViewById(R.id.imageView2)
        val diceImage_blue : ImageView = findViewById(R.id.imageView3)
        if(d1==1){
        when(diceRoll_red)
        {
            1->diceImage_red.setImageResource(R.drawable.dice_red_1)
            2->diceImage_red.setImageResource(R.drawable.dice_red_2)
            3->diceImage_red.setImageResource(R.drawable.dice_red_3)
            4->diceImage_red.setImageResource(R.drawable.dice_red_4)
            5->diceImage_red.setImageResource(R.drawable.dice_red_5)
            6->diceImage_red.setImageResource(R.drawable.dice_red_6)
        }
        }
        else{
            when(diceRoll_red)
            {
                1->diceImage_red.setImageResource(R.drawable.dice_blue_1)
                2->diceImage_red.setImageResource(R.drawable.dice_blue_2)
                3->diceImage_red.setImageResource(R.drawable.dice_blue_3)
                4->diceImage_red.setImageResource(R.drawable.dice_blue_4)
                5->diceImage_red.setImageResource(R.drawable.dice_blue_5)
                6->diceImage_red.setImageResource(R.drawable.dice_blue_6)
            }
        }

        diceImage_red.contentDescription = diceRoll_red.toString()
        if(d2==2) {
            when (diceRoll_blue) {
                1 -> diceImage_blue.setImageResource(R.drawable.dice_blue_1)
                2 -> diceImage_blue.setImageResource(R.drawable.dice_blue_2)
                3 -> diceImage_blue.setImageResource(R.drawable.dice_blue_3)
                4 -> diceImage_blue.setImageResource(R.drawable.dice_blue_4)
                5 -> diceImage_blue.setImageResource(R.drawable.dice_blue_5)
                6 -> diceImage_blue.setImageResource(R.drawable.dice_blue_6)
            }
        }
        else
        {
            when (diceRoll_blue) {
                1 -> diceImage_blue.setImageResource(R.drawable.dice_red_1)
                2 -> diceImage_blue.setImageResource(R.drawable.dice_red_2)
                3 -> diceImage_blue.setImageResource(R.drawable.dice_red_3)
                4 -> diceImage_blue.setImageResource(R.drawable.dice_red_4)
                5 -> diceImage_blue.setImageResource(R.drawable.dice_red_5)
                6 -> diceImage_blue.setImageResource(R.drawable.dice_red_6)
            }
        }
        diceImage_blue.contentDescription = diceRoll_blue.toString()
    }

    private fun changeDice(d: Int): Boolean {
        vibratee(500)

        if(d==1){
        if(d1==1) {
            d1=2
        } else {
            d1=1
        }}
        else
        {
            if(d2==1) {
                d2=2
            } else {
                d2=1
            }
        }
        return true
    }

    fun vibratee(t:Long)
    {
        val v = (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator)
        // Vibrate for 500 milliseconds
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(
                VibrationEffect.createOneShot(t,
                    VibrationEffect.DEFAULT_AMPLITUDE))
        }
        else {
            v.vibrate(t)
        }
    }
}



/**
 * Dice with a fixed number of sides.
 */
class Dice(private val numSides: Int) {

    /**
     * Do a random dice roll and return the result.
     */
    fun roll(): Int {
        return (1..numSides).random()
    }
}


 