class Game {
    fun calcScore(deck:MutableList<Int>): Int {
        var score = 0
        for(essential in deck){
            when(essential%13){
                in 1..8 -> score += essential%13+1
                in 10..12 -> score += 10
                0 -> if(score + 11 <= 21) score += 11 else score += 1
                9 -> if(score + 11 <= 21) score += 11 else score += 1
            }
        }
        return score
    }
}