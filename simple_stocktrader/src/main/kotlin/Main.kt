
val prices = arrayOf( -1, 30, -9, 2, 2, -1, 2, 100, -5 )

fun main() {
    calculateMaxProfit()
}

fun calculateMaxProfit() {
    var highPrice = 0
    var lowPrice = 0

    var temporaryLowDay = 0

    var lowDay = 0
    var highDay = 0

    var profit = 0

    prices.forEachIndexed() { index, price ->

        if (index == 0) {
            println("Current price: $price")
            lowPrice = price
        }

        if (price < lowPrice) {
            lowPrice = price;
            temporaryLowDay = index+1
        } else {
            if ((highPrice < highPrice - lowPrice) && (index > temporaryLowDay - 1) && (index > 0)) {
                highPrice = price

                if (profit < (highPrice - lowPrice)) {
                    profit = highPrice - lowPrice
                    highDay = index + 1
                    lowDay = temporaryLowDay
                }
            }
        }
    }
    println("To make the highest profit, buy at $lowDay and sell at $highDay for a profit of $profit")
}