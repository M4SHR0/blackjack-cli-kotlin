class Game(playerCount: Int,cardCount: Int) {
    val card = Card()
    val getcard = card.getCard()
    val shuffle = card.shuffle(getcard)
    val giveout = card.giveOut(pCount = playerCount,cCount = cardCount,  recieveDeck = shuffle)

    fun calcScore(){
        var score = 0
        for(oneDeck in giveout){
            for(essential in oneDeck){
                when(essential%13){
                    in 1..8 -> score += essential%13+1
                    in 10..12 -> score += 10
                    0 -> if(score + 11 <= 21) score += 11 else score += 1
                    9 -> if(score + 11 <= 21) score += 11 else score += 1
                }
            }
            println("Score:$score")
        }

    }

    fun checkDeck(){
        // 確認用
        println("getcard$getcard")
        println("shuffle$shuffle")
        println("giveout$giveout")
    }
}