fun main(){
    val card = Card()
    val getcard = card.getCard()
    println(getcard)
    val shuffle = card.shuffle()
    println(shuffle)
    val giveout = card.giveOut(2,2,  shuffledDeck = shuffle)
    println(giveout)

    // TODO 挙動がおかしいVer.
    println(getcard)
    println(shuffle)
    println(giveout)
}