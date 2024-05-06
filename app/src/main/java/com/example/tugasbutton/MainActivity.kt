package com.example.tugasbutton

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNumber1 = findViewById<Button>(R.id.btnNumber1)
        val btnNumber2 = findViewById<Button>(R.id.btnNumber2)
        val tvScore = findViewById<TextView>(R.id.tvScore)


        playRound(tvScore)

        btnNumber1.setOnClickListener {
            checkAnswer(btnNumber1.text.toString().toInt(), btnNumber2.text.toString().toInt(), tvScore)
        }

        btnNumber2.setOnClickListener {
            checkAnswer(btnNumber2.text.toString().toInt(), btnNumber1.text.toString().toInt(), tvScore)
        }

        findViewById<Button>(R.id.btnReset).setOnClickListener {
            score = 0
            tvScore.text = "Score: $score"
            playRound(tvScore)
        }
    }

    private fun playRound(tvScore: TextView?) {
        val leftVal = randomNumber()
        val rightVal = randomNumber()

        findViewById<Button>(R.id.btnNumber1).text = leftVal.toString()
        findViewById<Button>(R.id.btnNumber2).text = rightVal.toString()

        if (tvScore != null) {
            tvScore.text = "Score: $score"
        }

        if (score == 10) {
            showCongratulationsDialog()
        }
    }

    private fun randomNumber(): Int {
        return Random.nextInt(1, 101)
    }

    private fun checkAnswer(selectedNumber: Int, otherNumber: Int, tvScore: TextView) {
        if (selectedNumber > otherNumber) {
            score++
            tvScore.text = "Score: $score"
        }
        playRound(tvScore)
    }

    private fun showCongratulationsDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Selamat! Kamu sudah berhasil mencapai skor 10.")
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        val alert = builder.create()
        alert.show()
    }
}