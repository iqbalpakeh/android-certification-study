fun main() {

    // ------------------------------
    // 1
    // ------------------------------

    val person = Person("Iqbal", false)
    println(person.name)
    println(person.isMarried)

    person.isMarried = true
    println(person.isMarried)

    // ------------------------------
    // 2
    // ------------------------------

    val rect1 = Rectangle(2, 2)
    println(rect1.height)
    println(rect1.width)
    println(rect1.isSquare)

    val rect2 = Rectangle(2, 3)
    println(rect2.width)
    println(rect2.height)
    println(rect2.isSquare)
}

class Person(
    val name: String,
    var isMarried: Boolean
)

class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() = height == width
}
