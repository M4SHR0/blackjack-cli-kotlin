fun main(){
    val card = Card()
    val getcard = card.getCard()
    val shuffle = card.shuffle(getcard)
    val giveout = card.giveOut(2,2,  recieveDeck = shuffle)

    val game = Game()
    for(i in giveout){
        println("Score:" + game.calcScore(i))
    }

    // 確認用
    println("getcard$getcard")
    println("shuffle$shuffle")
    println("giveout$giveout")
}