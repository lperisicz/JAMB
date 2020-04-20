package com.perisic.luka.jamb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.perisic.luka.jamb.adapters.CellAdapter
import com.perisic.luka.jamb.adapters.HeaderAdapter
import com.perisic.luka.jamb.adapters.TitleAdapter
import com.perisic.luka.jamb.data.Header
import com.perisic.luka.jamb.data.Title
import com.perisic.luka.jamb.data.JambGame
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val titleAdapter = TitleAdapter()
    private val headerAdapter = HeaderAdapter()
    private lateinit var game: JambGame
    private val cellAdapter = CellAdapter {
        game.choose(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()
        fillData()
    }

    private fun setupUi() {
        recyclerViewHeader.adapter = headerAdapter
        recyclerViewTitle.adapter = titleAdapter
        recyclerViewCell.adapter = cellAdapter
        fabRoll.setOnClickListener {
            game.roll()
        }
    }

    private fun fillData() {
        headerAdapter.submitData(
            listOf(
                Header(image = R.drawable.ic_arrows)
            )
        )
        titleAdapter.submitData(
            listOf(
                Title(text = "Ones"),
                Title(text = "Twos"),
                Title(text = "Threes"),
                Title(text = "Fours"),
                Title(text = "Fives"),
                Title(text = "Sixes"),
                Title(text = "Sum"),
                Title(text = "Min"),
                Title(text = "Max"),
                Title(text = "Difference"),
                Title(text = "Two pairs"),
                Title(text = "Three of kind"),
                Title(text = "Full house"),
                Title(text = "Small straight"),
                Title(text = "Large straight"),
                Title(text = "Poker"),
                Title(text = "Yahtzee"),
                Title(text = "Sum")
            )
        )
        val dices = arrayOf(
            R.drawable.ic_dice_one,
            R.drawable.ic_dice_two,
            R.drawable.ic_dice_three,
            R.drawable.ic_dice_four,
            R.drawable.ic_dice_five,
            R.drawable.ic_dice_six
        )
        game = JambGame(1) {
            cellAdapter.submitData(it.cells)
            it.dices.forEachIndexed { index, dice ->
                if (dice.locked) {
                    when (index) {
                        0 -> diceOne
                        1 -> diceTwo
                        2 -> diceThree
                        3 -> diceFour
                        4 -> diceFive
                        else -> diceSix
                    }.apply {
                        setBackgroundResource(dices[dice.value - 1])
                        text = "locked"
                    }.setOnClickListener { }
                } else {
                    when (index) {
                        0 -> diceOne
                        1 -> diceTwo
                        2 -> diceThree
                        3 -> diceFour
                        4 -> diceFive
                        else -> diceSix
                    }.apply {
                        setBackgroundResource(dices[dice.value - 1])
                        setText("")
                    }.setOnClickListener {
                        game.lockDice(index)
                    }
                }
            }
            fabRoll.isEnabled = it.buttonEnabled
        }
    }
}
