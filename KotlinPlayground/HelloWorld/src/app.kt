fun main() {

    // ------------------------------
    // 1
    // ------------------------------
    println("Hello World!")

    // ------------------------------
    // 2
    // ------------------------------
    println(sum(2, 3))

    // ------------------------------
    // 3
    // ------------------------------
    var a = 1
    val s1 = "a is $a"

    a = 2
    val s2 = "${s1.replace("is", "was")}, but now is $a"

    println(s1)
    println(s2)

    // ------------------------------
    // 4
    // ------------------------------
    println("Max of 2 and 4 is ${maxOf(2, 4)}")
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