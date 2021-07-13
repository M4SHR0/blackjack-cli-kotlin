import kotlin.random.Random.Default.nextInt

class Game(private val playerCounts: Int) {
    private var card = Card()
    private val playerList = mutableListOf<Player>()

    fun pvp(){
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

    fun cvc() {
        var winCounts = 0
        var drawCounts = 0
        var loseCounts = 0

        println("How many loops do you want?")
        val gameCounts = readLine()!!.toInt()
        println("OK,Let's play start!")
        println("------------")

        for (i in 0 until gameCounts) {
            var flagCounts = 0
            // プレイヤーの作成
            for (i in 1 until playerCounts + 1) {
                val player = Player("player$i")
                playerList.add(player)
            }

            // ドロー
            for (i in 0 until playerList.size) {
                for (j in 0 until 2) {    // 最初に引く枚数
                    playerList[i].playerDeck.add(draw(card.deck))
                }
            }

            // player1のスコア計算
            for (j in 0 until playerList[0].playerDeck.size) {
                val point = calcScore(playerList[0].playerDeck[j], playerList[0].score)
                playerList[0].score += point
                if (playerList[0].score >= 21) {
                    playerList[0].flag = false
                    flagCounts++
                }
            }
            // player2は何もしない為flagをfalseに
            for (j in 0 until playerList[1].playerDeck.size) {
                val point = calcScore(playerList[1].playerDeck[j], playerList[1].score)
                playerList[1].score += point
            }
            playerList[1].flag = false
            flagCounts++
            scoreCheck()

            // flagがtrueのplayerはhit,standを選択し続行できる
            while (flagCounts < playerCounts){
                for (p in playerList) {
                    // stand済みのプレイヤーはパス
                    if (p.flag) {
                        // PvPの時が標準入力だったためそのままarg
                        val arg :String = if (p.score<15){
                            "hit"
                        }else{
                            "stand"
                        }
                        if (arg == "hit") {
                            println("HIT")
                            hit(p)
                            // もしhitしてバーストしてたらflagをfalseにする
                            if (!p.flag) {
                                flagCounts++
                            }
                        } else if (arg == "stand") {
                            println("STAND")
                            stand(p)
                            flagCounts++
                        }
                    }
                }
                scoreCheck()
            }

            // 勝利判定と表示
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
            when (winner) {
                "player1" -> {
                    winCounts++
                }
                "player1&player2" -> {
                    drawCounts++
                }
                else -> {
                    loseCounts++
                }
            }

            playerList.clear()
            card = Card()
            println("------------")
        }

        println("win:${winCounts},draw:${drawCounts},lose:${loseCounts}")
    }
}