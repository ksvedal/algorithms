import kotlin.math.pow

fun main() {
    println(recursive1(2.0,5.0))
    println(recursive2(2.0, 12.0))
    println(2.0.pow(12.0))
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