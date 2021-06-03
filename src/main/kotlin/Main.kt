fun main() {
    println("###BLACKJACK###")
    println("Enter the 'play' and start game ...")
    var arg = readLine()!!
    if (arg == "play"){
        println("How many players?(Enter 2 or more ) ...")
        var arg = readLine()!!.toInt()
        while (arg < 2){
            println("Enter 2 or more ...")
            arg = readLine()!!.toInt()
        }
        println("Let's start!")
        println("--------------------")

        val game = Game(arg)
        game.play()
    }else{
        println("Detected invalid input")
        println("When you want play, run this again.")
        println("Bye..")
    }
}