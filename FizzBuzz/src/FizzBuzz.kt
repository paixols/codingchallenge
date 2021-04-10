/*
 * Print in a new line:
 * - FizzBuzz if the number is a multiple of 3 and 5
 * - Buzz if the number is a multiple of 5
 * - Fizz if the number is a multiple of 3
 * - The value of the number if it's not multiple of 3 or 5
 */

fun main() {
    fizzBuzz(15)
}

fun fizzBuzz(n: Int): Unit {

    (1..n).map { i ->
        mapOf(0 to i, i % 3 to "Fizz", i % 5 to "Buzz", i % 15 to "FizzBuzz")[0]
    }.forEach{ solution -> print("$solution \n") }

}