package ro.pub.cs.systems.eim.practicaltest01

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import android.widget.Button
import android.widget.EditText
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01MainActivity : AppCompatActivity() {

    private lateinit var leftEditText: EditText
    private lateinit var rightEditText: EditText
    private lateinit var pressMeButton: Button
    private lateinit var pressMeTooButton: Button

    // --- ADĂUGAT PENTRU B.1: Clasa internă ascultător ---
    private val buttonClickListener = ButtonClickListener()
    private inner class ButtonClickListener : View.OnClickListener {
        override fun onClick(view: View?) {
            var leftNumberOfClicks = leftEditText.text.toString().toInt()
            var rightNumberOfClicks = rightEditText.text.toString().toInt()

            when (view?.id) {
                R.id.press_me_button -> {
                    leftNumberOfClicks++
                    leftEditText.setText(leftNumberOfClicks.toString())
                }

                R.id.press_me_too_button -> {
                    rightNumberOfClicks++
                    rightEditText.setText(rightNumberOfClicks.toString())
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_main)

        leftEditText = findViewById(R.id.left_edit_text)
        rightEditText = findViewById(R.id.right_edit_text)

        pressMeButton = findViewById(R.id.press_me_button)
        pressMeTooButton = findViewById(R.id.press_me_too_button)

        leftEditText.setText(0.toString())
        rightEditText.setText(0.toString())

        pressMeButton.setOnClickListener(buttonClickListener)
        pressMeTooButton.setOnClickListener(buttonClickListener)

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.LEFT_COUNT)) {
                leftEditText.setText(savedInstanceState.getString(Constants.LEFT_COUNT))
            } else {
                leftEditText.setText(0.toString())
            }
            if (savedInstanceState.containsKey(Constants.RIGHT_COUNT)) {
                rightEditText.setText(savedInstanceState.getString(Constants.RIGHT_COUNT))
            } else {
                rightEditText.setText(0.toString())
            }
        } else {
            leftEditText.setText(0.toString())
            rightEditText.setText(0.toString())
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putString(Constants.LEFT_COUNT, leftEditText.text.toString())
        savedInstanceState.putString(Constants.RIGHT_COUNT, rightEditText.text.toString())
    }

    // --- ADĂUGAT PENTRU B.2.b: Restaurarea valorilor din Bundle ---
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.containsKey(Constants.LEFT_COUNT)) {
            leftEditText.setText(savedInstanceState.getString(Constants.LEFT_COUNT))
        } else {
            leftEditText.setText(0.toString())
        }
        if (savedInstanceState.containsKey(Constants.RIGHT_COUNT)) {
            rightEditText.setText(savedInstanceState.getString(Constants.RIGHT_COUNT))
        } else {
            rightEditText.setText(0.toString())
        }
    }
}
