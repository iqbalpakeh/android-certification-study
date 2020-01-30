import java.io.BufferedReader
import java.io.StringReader
import java.util.*

fun main() {

    // ------------------------------
    // 1
    // ------------------------------
    println("\nSection 1")

    val person = Person("Iqbal", false)
    println(person.name)
    println(person.isMarried)

    person.isMarried = true
    println(person.isMarried)

    // ------------------------------
    // 2
    // ------------------------------
    println("\nSection 2")

    val rect1 = Rectangle(2, 2)
    println(rect1.height)
    println(rect1.width)
    println(rect1.isSquare)

    val rect2 = Rectangle(2, 3)
    println(rect2.width)
    println(rect2.height)
    println(rect2.isSquare)

    // ------------------------------
    // 3
    // ------------------------------
    println("\nSection 3")

    println(Color.BLUE.rgb())
    println(getMnemonic(Color.BLUE))
    println(getWarmth(Color.BLUE))
    println(mix(Color.BLUE, Color.YELLOW))
    println(mixOptimized(Color.BLUE, Color.YELLOW))

    // ------------------------------
    // 4
    // ------------------------------
    println("\nSection 4")

    println (eval(Sum(Sum(Num(1), Num(2)), Num (4))))
    println (evalWithLogging(Sum(Sum(Num(1), Num(2)), Num (4))))

    // ------------------------------
    // 5
    // ------------------------------
    println("\nSection 5")

    for(i in 1..100) {
        println(fizzBuzz(i))
    }

    for(i in 100 downTo 1 step 2) {
        println(fizzBuzz(i))
    }

    // ------------------------------
    // 6
    // ------------------------------
    println("\nSection 6")

    val binaryReps = TreeMap<Char, String>()

    for (c in 'A'..'F') {
        val binary = Integer.toBinaryString(c.toInt())
        binaryReps[c] = binary
    }

    for ((letter, binary) in binaryReps) {
        println("$letter = $binary")
    }

    // ------------------------------
    // 7
    // ------------------------------
    println("\nSection 7")

    println(isLetter('q'))
    println(isNotDigit('x'))
    println(recognize('8'))

    // ------------------------------
    // 8
    // ------------------------------
    println("\nSection 8")

    var reader = BufferedReader(StringReader("239"))
    println(readNumber(reader))

    reader = BufferedReader(StringReader("Not a number"))
    println(readNumber(reader))


}

// ------------------------------
// 1
// ------------------------------

class Person(
    val name: String,
    var isMarried: Boolean
)

// ------------------------------
// 2
// ------------------------------

class Rectangle(
    val height: Int, val width: Int
) {
    val isSquare: Boolean
        get() = height == width
}

// ------------------------------
// 3
// ------------------------------

enum class Color(
    val r: Int, val g: Int, val b: Int
) {
    RED(255, 0, 0), ORANGE(255, 165, 0),
    YELLOW(255, 255, 0), GREEN(0, 255, 0), BLUE(0, 0, 255),
    INDIGO(75, 0, 130), VIOLET(238, 130, 238);

    fun rgb() = (r * 256 + g) * 256 + b
}

fun getMnemonic(color: Color) =
    when (color) {
        Color.RED -> "Richard"
        Color.ORANGE -> "Of"
        Color.YELLOW -> "York"
        Color.GREEN -> "Gave"
        Color.BLUE -> "Battle"
        Color.INDIGO -> "In"
        Color.VIOLET -> "Vain"
    }

fun getWarmth(color: Color) =
    when (color) {
        Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
        Color.GREEN -> "neutral"
        Color.BLUE, Color.INDIGO, Color.VIOLET -> "cold"
    }

fun mix(c1: Color, c2: Color) =
    when (setOf(c1, c2)) {
        setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
        setOf(Color.YELLOW, Color.BLUE) -> Color.GREEN
        setOf(Color.BLUE, Color.VIOLET) -> Color.INDIGO
        else -> throw Exception("Dirty Color")
    }

fun mixOptimized(c1: Color, c2: Color) =
    when {
        (c1 == Color.RED && c2 == Color.YELLOW) ||
                (c1 == Color.YELLOW && c2 == Color.RED) -> Color.ORANGE
        (c1 == Color.YELLOW && c2 == Color.BLUE) ||
                (c1 == Color.BLUE && c2 == Color.YELLOW) -> Color.GREEN
        (c1 == Color.BLUE && c2 == Color.VIOLET) ||
                (c1 == Color.VIOLET && c2 == Color.BLUE) -> Color.INDIGO
        else -> throw Exception("Dirty color")
    }

// ------------------------------
// 4
// ------------------------------

interface Expr

class Num(val value: Int): Expr

class Sum(val left: Expr, val right: Expr): Expr

fun eval(e: Expr): Int =
    when (e) {
        is Num -> e.value
        is Sum -> eval(e.right) + eval(e.left)
        else -> throw IllegalArgumentException("Unknown expression")
    }

fun evalWithLogging(e: Expr): Int =
    when(e) {
        is Num -> {
            println("Num: ${e.value}")
            e.value
        }
        is Sum -> {
            val left = evalWithLogging(e.left)
            val right = evalWithLogging(e.right)
            println("Sum: $left + $right")
            left + right
        }
        else -> throw IllegalArgumentException("Unknown expression")
    }

// ------------------------------
// 5
// ------------------------------

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz "
    i % 3 == 0 -> "Fizz "
    i % 5 == 0 -> "Buzz "
    else -> "$i "
}

// ------------------------------
// 7
// ------------------------------

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'

fun isNotDigit(c: Char) = c !in '0'..'9'

fun recognize(c: Char) = when (c) {
    in '0'..'9' -> "It's a digit!"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
    else -> "I don't know..."
}

// ------------------------------
// 8
// ------------------------------

fun readNumber(reader: BufferedReader): Int? =
    try {
        val line = reader.readLine()
        Integer.parseInt(line)
    } catch (e: NumberFormatException) {
        null
    } finally {
        reader.close()
    }
