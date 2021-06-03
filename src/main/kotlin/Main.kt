fun main() {
    println("###BLACKJACK###")
    println("Enter the 'play' and start game ...")
    val arg = readLine()!!
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

        println("--------------------")
        println("Thank you for playing!")
        println("See you again.")
    }else{
        println("Detected invalid input")
        println("When you want play, run this again.")
        println("Bye..")
    }
}