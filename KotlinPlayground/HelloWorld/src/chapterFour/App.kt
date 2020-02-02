package chapterFour

fun main() {

    // ------------------------------
    // Section 1
    // ------------------------------

    val button = Button()
    button.click()
    button.showOff()
    button.setFocus(true)

    val richButton = RichButton()
    richButton.animate()
    richButton.disable()
    richButton.click()
    richButton.showOff()

    // ------------------------------
    // Section 2
    // ------------------------------

    val text = Text()
    text.animate()
    text.animateTwice()
    text.stopAnimating()

}

// ------------------------------
// Section 1
// ------------------------------

interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable!")
}

interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus.")
    fun showOff() = println("I'm focusable!")
}

class Button: Clickable, Focusable {

    override fun click() = println("I was clicked")

    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }

}

open class RichButton : Clickable {

    fun disable() = println("RichButton was disabled!")

    open fun animate() = println("RichButton was animated")

    final override fun click() = println("RichButton was clicked!")

}

// ------------------------------
// Section 2
// ------------------------------

abstract class Animated {

    abstract fun animate()

    open fun stopAnimating() {
        println("Stop animating")
    }

    fun animateTwice() {
        println("Animate twice")
    }

}

class Text: Animated() {

    override fun animate() {
        println("Start animating")
    }

}