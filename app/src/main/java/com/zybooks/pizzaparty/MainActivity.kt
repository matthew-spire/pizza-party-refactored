package com.zybooks.pizzaparty

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

/**
 * The MainActivity class contains the Kotlin code that interacts with the UI.
 *
 * MainActivity inherits the AppCompatActivity class, which is the superclass for all activities and
 * provides the latest Android functionality for devices running older versions of Android.
 *
 * AppCompatActivity inherits the Activity class, which is a class that provides methods that form
 * the activity lifecycle.
 */
class MainActivity : AppCompatActivity() {

    /**
     * Defines the properties of the widgets.
     * Uses lateinit to define the properties without setting a value directly, allowing the values
     * to be set later.
     */
    private lateinit var numAttendEditText: EditText
    private lateinit var numPizzasTextView: TextView
    private lateinit var howHungryRadioGroup: RadioGroup

    /**
     * The onCreate() method loads the activity's XML layout and performs other initialization
     * logic.
     *
     * @param savedInstanceState Bundle that may contain information about the app's previous state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        /**
         * super.onCreate(savedInstanceState) extends the parent class' method, allowing code from
         * the previous state and the current state to run.
         *
         * Activity method setContentView() sets the activity's content to the given layout file.
         *
         * Activity method findViewById() returns a View from the layout file that matches the
         * given ID.
         */
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#000000")))
    }

    /**
     * The function calculateClick gets the number of people and how hungry they are, then calls
     * PizzaCalculator. PizzaCalculator calculates the number of pizzas needed to feed the given
     * number of people based on how hungry they are. The result is displayed by assigning a new
     * string using the TextView text setter.
     *
     * @param view View that was clicked, i.e., the button.
     */
    fun calculateClick(view: View) {

        // Get the text that was typed into the EditText for the number of people
        val numAttendStr = numAttendEditText.text.toString()

        // Convert the text into an integer
        val numAttend = numAttendStr.toIntOrNull() ?: 0

        // Get hunger level selection
        val hungerLevel = when (howHungryRadioGroup.checkedRadioButtonId) {
            R.id.light_radio_button -> PizzaCalculator.HungerLevel.LIGHT
            R.id.medium_radio_button -> PizzaCalculator.HungerLevel.MEDIUM
            else -> PizzaCalculator.HungerLevel.RAVENOUS
        }

        // Get the number of pizzas needed
        val calc = PizzaCalculator(numAttend, hungerLevel)
        val totalPizzas = calc.totalPizzas

        // Place totalPizzas into the string resource and display
        val totalText = getString(R.string.total_pizzas, totalPizzas)
        numPizzasTextView.text = totalText
    }
}