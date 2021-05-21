import kotlin.random.Random

class Card {
    private val deck = mutableListOf<Int>()
    init {
        // TODO 検証用にトランプを10枚にしてる
        for (i in 0..10){
            deck.add(i)
        }
    }

    fun getCard(): MutableList<Int> {
        return deck
    }

    fun shuffle(): MutableList<Int> {
        val baseDeck = this.deck
        val shuffled = mutableListOf<Int>()
        var rand: Int
        while (baseDeck.size > 0){
            rand = Random.nextInt(baseDeck.size)
            shuffled += baseDeck[rand]
            baseDeck.removeAt(rand)
        }
        return shuffled
    }

    fun giveOut(playerCount: Int, cardCount: Int, shuffledDeck:MutableList<Int>): MutableList<MutableList<Int>> {
        var rand:Int
        val dist = mutableListOf<MutableList<Int>>()
        for (j in 0 until playerCount){
            dist += mutableListOf<Int>()
        }
        for (i in 0 until cardCount){
            for (j in 0 until playerCount) {
                rand = Random.nextInt(shuffledDeck.size)
                dist[j] += shuffledDeck[rand]
                shuffledDeck.removeAt(rand)
            }
        }
        // TODO giveOutメソッド実行後のshuffledDeckの確認
        println(shuffledDeck)
        return dist
    }
}