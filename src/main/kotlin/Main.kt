fun main(){
    val card = Card()
    val getcard = card.getCard()
    println("想定getcard" + getcard)
    val shuffle = card.shuffle()
    println("想定shuffle" + shuffle)
    val giveout = card.giveOut(2,2,  shuffledDeck = shuffle)
    println("想定giveout" + giveout)

    // TODO 挙動がおかしいVer.
    println("異常getcard" + getcard)
    println("異常shuffle" + shuffle)
    println("異常giveout" + giveout)

    // TODO 異常なはずの検証用
    println(shuffle)
}