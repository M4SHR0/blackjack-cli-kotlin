fun main() {
    println("###BLACKJACK###")
    println("Enter the 'play' and start game ...")
    val playArg = readLine()!!
    var playerCountsArg : Int

    if (playArg == "play"){
        println("Start CvC mode")
        /* this code block  use by PvP mode only
        println("How many players?(Enter 2 or more ) ...")

        playerCountsArg = readLine()!!.toInt()
        while (playerCountsArg < 2){
            println("Enter 2 or more ...")
            println("When you play PvC mode, you have to Enter 2")
            playerCountsArg = readLine()!!.toInt()
        }
        */
        playerCountsArg = "2".toInt()
        println("Let's start!")
        println("--------------------")

        val game = Game(playerCountsArg)
        //game.pvp()
        game.cvc()

        println("--------------------")
        println("Thank you for playing!")
        println("See you again.")
    }else{
        println("Detected invalid input")
        println("When you want play, run this again.")
        println("Bye..")
    }
}