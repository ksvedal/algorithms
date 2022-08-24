
val prices = arrayOf( -12, 30, -1, 2, 100, -1000, 2222, 1 )

fun main() {
    calculateMaxProfit()
}

fun calculateMaxProfit() {

    var temporaryLowDay = 1
    var lowDay = 1
    var highDay = 1

    var currentPrice = 0
    var lowPrice = 0
    var highPrice = 0
    var profit = 0

    prices.forEachIndexed() { day, priceChange ->

        // Set low and high price of the first day.
        if (day == 0) {
            lowPrice = priceChange
            highPrice = priceChange
        }

        // Change current price of stock.
        currentPrice += priceChange

        // Sets temporary low day if current price is less than the lowest recorded price.
        if (currentPrice < lowPrice) {
            lowPrice = currentPrice;
            temporaryLowDay = day + 1
        } else {

            if ((highPrice < highPrice - lowPrice) && (day > temporaryLowDay - 1) && (day > 0)) {

                // Sets high price if it's less than current price
                if (highPrice < currentPrice) highPrice = currentPrice

                // Sets profit, low day and high day if profit is higher than currently registered
                if (profit < (highPrice - lowPrice)) {
                    profit = highPrice - lowPrice
                    highDay = day + 1
                    lowDay = temporaryLowDay
                }
            }
        }
    }
    println("To make the highest profit, buy at $lowDay and sell at $highDay for a profit of $profit")
}