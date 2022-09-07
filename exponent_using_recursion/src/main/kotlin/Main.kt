import kotlin.math.pow

// Assign the base and power of.
const val x = 1.001
const val n = 81920.0

fun main() {
    val rounds = 1000000
    val time = System.currentTimeMillis()

    for (i in 0..rounds) {
        // println(x.pow(n))
        println(recursive2(x, n))
        // println(recursive1(x, n))
    }

    val totalTid = System.currentTimeMillis()-time

    println("" + rounds/(totalTid) + " runder per ms")
}

fun recursive1(x: Double, n: Double): Double {
    return if (n > 0.0) {
        x * recursive1(x, n-1.0)
    } else {
        1.0
    }
}

fun recursive2(x: Double, n: Double): Double {
    return if (n == 0.0) {
        1.0
    } else if ((n % 2.0) == 0.0) {
        recursive2(x * x, n / 2.0)
    } else {
        x * recursive2(x * x, (n-1.0)/2.0)
    }
}