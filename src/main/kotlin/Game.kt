import kotlin.random.Random.Default.nextInt

class Game(private val playerCounts: Int) {
    private val card = Card()
    private val playerList = mutableListOf<Player>()

    fun play(){
        // プレイヤーの作成
        for (i in 0 until playerCounts){
            val player = Player("player$i")
            playerList.add(player)
        }

        // ドロー
        for(i in 0 until playerList.size){ // 枚数
            for(j in 0 until 2){
                playerList[j].playerDeck.add(draw(card.deck))
            }
        }

        // スコアの計算
        for (i in 0 until playerList.size){
            for (j in 0 until playerList[i].playerDeck.size){
                val point = calcScore(playerList[i].playerDeck[j],playerList[i].score)
                playerList[i].score += point
            }
        }

        // TODO　動作検証用
        testCheck()
    }

    private fun draw(cardDeck:MutableList<Int>): Int {
        val rand = nextInt(cardDeck.size)
        val drewCard = cardDeck[rand]
        cardDeck.removeAt(rand)
        return drewCard
    }

    private fun calcScore(card:Int, score:Int): Int {
        return when (card % 13) {
            in 1..9 -> card % 13 + 1
            in 10..12 -> 10
            else -> if (score + 11 > 21) 1 else 11
        }
    }

    // 確認用
    private fun testCheck(){
        for (p in playerList){
            println("playerName:${p.name}")
            println("playerDeck:${p.playerDeck}")
            println("playerScore:${p.score}\n")
        }
    }


}