import kotlin.random.Random

class Card(NumberOfCards:Int = 52) {
    private val deck = mutableListOf<Int>()
    init {
        for (i in 0 until NumberOfCards){
            deck.add(i)
        }
    }

    fun getCard(): MutableList<Int> {
        return deck.toMutableList()
    }

    fun shuffle(recieveDeck: MutableList<Int>): MutableList<Int> {
        val baseDeck = recieveDeck.toMutableList()
        val shuffled = mutableListOf<Int>()
        var rand: Int
        while (baseDeck.size > 0){
            rand = Random.nextInt(baseDeck.size)
            shuffled += baseDeck[rand]
            baseDeck.removeAt(rand)
        }
        return shuffled
    }

    fun giveOut(pCount: Int, cCount: Int, recieveDeck:MutableList<Int>): MutableList<MutableList<Int>> {
        var rand:Int
        val dist = mutableListOf<MutableList<Int>>()
        val shuffledDeck = recieveDeck.toMutableList()
        for (j in 0 until pCount){
            dist += mutableListOf<Int>()
        }
        for (i in 0 until cCount){
            for (j in 0 until pCount) {
                rand = Random.nextInt(shuffledDeck.size)
                dist[j] += shuffledDeck[rand]
                shuffledDeck.removeAt(rand)
            }
        }
        return dist
    }
}