import kotlin.random.Random.Default.nextInt

class Game(private val playerCounts: Int) {
    private val card = Card()
    private val playerList = mutableListOf<Player>()

    fun play(){
        var flagCounts = 0

        // プレイヤーの作成
        for (i in 1 until playerCounts+1){
            val player = Player("player$i")
            playerList.add(player)
        }

        // ドロー
        for(i in 0 until playerList.size){
            for(j in 0 until 2){    // 最初に引く枚数
                playerList[i].playerDeck.add(draw(card.deck))
            }
        }

        // スコアの計算
        for (i in 0 until playerList.size){
            for (j in 0 until playerList[i].playerDeck.size){
                val point = calcScore(playerList[i].playerDeck[j],playerList[i].score)
                playerList[i].score += point
                if(playerList[i].score >= 21){
                    playerList[i].flag = false
                }
            }
        }
        scoreCheck()

        // プレイヤー全員が操作不能になるまで　hit　と　stand　の繰り返し
        while (flagCounts < playerCounts){
            for (p in playerList) {
                // stand済みのプレイヤーはパス
                if (p.flag) {
                    println("${p.name}, which do you enter 'hit' or 'stand' ....")
                    var arg = readLine()!!
                    // hit か stand　じゃない入力の時は無限ループ
                    while (arg != "hit" && arg != "stand") {
                        println("Enter 'hit' or 'stand' ....")
                        arg = readLine()!!
                    }
                    if (arg == "hit") {
                        hit(p)
                        // もしhitしてバーストしてたらflagをfalseにする
                        if (!p.flag) {
                            flagCounts++
                        }
                    } else if (arg == "stand") {
                        stand(p)
                        flagCounts++
                    }
                }
            }
            scoreCheck()
        }

        var winner = "not exist"
        var topScore = 0
        for (p in playerList){
            if (p.score <= 21) {
                if (p.score > topScore) {
                    topScore = p.score
                    winner = p.name
                } else if (p.score == topScore) {
                    winner += "&${p.name}"
                }
            }
        }

        println("Winner is ${winner}!")
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
    private fun scoreCheck(){
        for (p in playerList){
            println("playerName:${p.name}")
            println("playerDeck:${p.playerDeck}")
            println("playerScore:${p.score}")
            println("playerFlag:${p.flag}\n")
        }
    }

    private fun hit(player:Player){
        val drawed = draw(card.deck)
        player.playerDeck.add(drawed)
        player.score += calcScore(drawed,player.score)
        if (player.score >= 21){
            player.flag = false
        }
    }

    private fun stand(player:Player){
        player.flag = false
    }
}