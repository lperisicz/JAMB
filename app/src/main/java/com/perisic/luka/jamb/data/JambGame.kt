package com.perisic.luka.jamb.data

class JambGame(
    numberOfPlayers: Int,
    private val draw: (player: Player) -> Unit
) {

    private val players: List<Player> = createPlayers(numberOfPlayers)
    private var currentPlayer: Int = 0

    init {
        players[currentPlayer].start()
        draw(players[currentPlayer])
    }

    private fun createPlayers(numberOfPlayers: Int): List<Player> {
        val list = ArrayList<Player>()
        for (i in 0 until numberOfPlayers) {
            list.add(
                Player(
                    name = "Player ${i + 1}"
                )
            )
        }
        return list
    }

    private fun switchToNextPlayer() {
        if (currentPlayer == players.size - 1) {
            currentPlayer = 0
        } else {
            currentPlayer++
        }
        players[currentPlayer].start()
    }

    fun roll() {
        if (players[currentPlayer].roll()) {
            players[currentPlayer].lockDices()
        }
        draw(players[currentPlayer])
    }

    fun lockDice(position: Int) {
        players[currentPlayer].lockDice(position)
        draw(players[currentPlayer])
    }

    fun choose(position: Int) {
        players[currentPlayer].choose(position)
        draw(players[currentPlayer])
        switchToNextPlayer()
        draw(players[currentPlayer])
    }

}