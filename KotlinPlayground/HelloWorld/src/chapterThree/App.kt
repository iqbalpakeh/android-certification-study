package chapterThree

import chapterThree.strings.join
import chapterThree.strings.joinToString
import chapterThree.strings.joinToStringExt
import chapterThree.strings.lastChar as last

fun main() {
    // section1()
    // section2()
    // section3()
    // section4()
    // section5()
    section6()
}

fun section6() {

    class User(val id: Int, val name: String, val address: String)

    fun User.validateBeforeSave() {
        fun validate(value: String, fieldName: String) {
            if (value.isEmpty()) {
                throw IllegalArgumentException(
                    "Can't save user $id: empty $fieldName")
            }
        }
        validate(name, "Name")
        validate(address, "Address")
    }

    fun saveUser(user: User) {
        user.validateBeforeSave()
        // Save user to the database
    }

    saveUser(User(1, "", ""))
}

fun section5() {

    val args: Array<String> = arrayOf("one", "two", "three")
    val list = listOf("args: ", *args)
    println(list)

}

fun section4() {

    open class View {
        open fun click() = println("View clicked")
    }

    class Button: View() {
        override fun click() = println("Button clicked")
    }

    val view: View = Button()
    view.click()

    fun View.showOff() = println("I'm a view!")
    fun Button.showOff() = println("I'm a button!")

    view.showOff()

    println("Kotlin".last)

    val sb = StringBuilder("Kotlin?")
    sb.last = '!'
    println(sb)
}

fun section3() {
    println("Kotlin".last())

    val list = listOf(1, 2, 3)
    println(list.joinToStringExt(separator = "; ", prefix = "(", postfix = ")"))
    println(list.joinToString(" "))
    println(listOf("one", "two", "three").join(" "))
}

fun section2() {

    val list = listOf(1, 2, 3)
    println(list)

    println(joinToString(list, "; ", "(", ")"))
    println(joinToString(list))
    println(joinToString(list, ", ", "[", "]"))
    println(joinToString(list, separator = " - ", prefix = "# "))
}

fun section1() {

    val set = hashSetOf(1, 7, 53)
    val list = arrayListOf(1, 7, 53)
    val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")

    println(set.javaClass)
    println(list.javaClass)
    println(map.javaClass)

    val strings = listOf("first", "second", "fourteenth")
    println(strings.last())

    val numbers = setOf(1, 14, 2)
    println(numbers.max())

}