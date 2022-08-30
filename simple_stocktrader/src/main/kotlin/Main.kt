val prices = arrayOf( -1, 3, -9, 2, 2, -1, -5 )

fun main() {
    var rounds = 0.0
    val time = System.currentTimeMillis()

    do {
        calculateMaxProfit()
        rounds++
    } while (System.currentTimeMillis() - time <= 1000)

    val msPerRound = 1000/rounds
    println("Milliseconds used per round: $msPerRound")
}

fun calculateMaxProfit() {

    var temporaryLowDay = 1
    var temporaryHighDay = 1
    var lowDay = 1
    var highDay = 1

    var currentPrice = 0
    var lowPrice = 0
    var highPrice = 0
    var profit = 0

    prices.forEachIndexed { day, priceChange ->

        // Change current price of stock.
        currentPrice += priceChange

        // Set low and high price of the first day.
        if (day == 0) {
            lowPrice = priceChange
            highPrice = priceChange
        }

        // Sets temporary low day if current price is less than the lowest recorded price.
        if (currentPrice < lowPrice) {
            lowPrice = currentPrice
            temporaryLowDay = day + 1
        } else {

            // Sets temporary high day if current price is more than the highest recorded price.
            if ((currentPrice - lowPrice) > profit) {
                highPrice = currentPrice
                temporaryHighDay = day + 1
            }

            // Sets profit, low day and high day if profit is higher than currently registered
            if ((profit < (highPrice - lowPrice)) && (temporaryHighDay > temporaryLowDay)) {
                profit = highPrice - lowPrice
                highDay = temporaryHighDay
                lowDay = temporaryLowDay
            }
        }
    }
    println("To make the highest profit, buy at $lowDay and sell at $highDay for a profit of $profit")
}