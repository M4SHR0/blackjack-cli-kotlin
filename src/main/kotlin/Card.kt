import kotlin.random.Random

class Card(NumberOfCards:Int = 52) {
    var deck = mutableListOf<Int>()
    init {
        createTrump(NumberOfCards)
        shuffle()
    }

    // 1..52のトランプデッキを作成
    private fun createTrump(NumberOfCards:Int){
        for (i in 0 until NumberOfCards){
            deck.add(i)
        }
    }

    // トランプデッキをシャッフル
    private fun shuffle(){
        val baseDeck = deck.toMutableList()
        val shuffled = mutableListOf<Int>()
        var rand: Int
        while (baseDeck.size > 0){
            rand = Random.nextInt(baseDeck.size)
            shuffled += baseDeck[rand]
            baseDeck.removeAt(rand)
        }
        deck = shuffled.toMutableList()
    }
}