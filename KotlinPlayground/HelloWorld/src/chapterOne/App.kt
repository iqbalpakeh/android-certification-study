package chapterOne

fun main() {

    // ------------------------------
    // 1
    // ------------------------------
    println("\nSection 1")

    println("Hello World!")

    // ------------------------------
    // 2
    // ------------------------------
    println("\nSection 2")

    println(sum(2, 3))

    // ------------------------------
    // 3
    // ------------------------------
    println("\nSection 3")

    var a = 1
    val s1 = "a is $a"

    a = 2
    val s2 = "${s1.replace("is", "was")}, but now is $a"

    println(s1)
    println(s2)

    // ------------------------------
    // 4
    // ------------------------------
    println("\nSection 4")

    println("Max of 2 and 4 is ${maxOf(2, 4)}")

    // ------------------------------
    // 5
    // ------------------------------
    println("\nSection 5")

    printProduct("6", "7")
    printProduct("a", "7")
    printProduct("a", "b")

    // ------------------------------
    // 6
    // ------------------------------
    println("\nSection 6")

    var aa: String? = "abc"
    aa = null // if ? removed, generate compilation error
    println(aa)

    // ------------------------------
    // 7
    // ------------------------------
    println("\nSection 7")

    val items = listOf("apple", "banana", "kiwifruit")

    for (item in items) {
        println(item)
    }

    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }

    // ------------------------------
    // 8
    // ------------------------------
    println("\nSection 8")

    println(describe(1))
    println(describe("Hello"))
    println(describe(1000L))
    println(describe(2))
    println(describe("other"))

    // ------------------------------
    // 9
    // ------------------------------
    println("\nSection 9")

    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits
        .filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.toUpperCase() }
        .forEach { println(it) }
}

fun sum(a: Int, b: Int): Int {
    return a + b
}

fun maxOf(a: Int, b: Int): Int {
    if (a > b) {
        return a
    }
    return b
}

fun parseInt(str: String): Int? {
    return str.toIntOrNull()
}

fun printProduct(arg1: String, arg2: String) {

    val x = parseInt(arg1)
    val y = parseInt(arg2)

    // Using 'x * y' yields error because they may hold nulls
    if (x != null && y != null) {
        // x and y are automatically cast to non-nullable after null check
        println(x * y)
    } else {
        println("'$arg1' or '$arg2' is not a number")
    }

}

fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        // obj is automatically cast to String in this branch
        return obj.length;
    }

    // obj is still of type 'Any' outside of the type-checked branch
    return null
}

fun describe(obj: Any): String =
    when (obj) {
        1          -> "One"
        "Hello"    -> "Greeting"
        is Long    -> "Long"
        !is String -> "Not a string"
        else       -> "Unknown"
    }


