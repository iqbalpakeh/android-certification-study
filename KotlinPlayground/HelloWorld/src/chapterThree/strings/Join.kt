package chapterThree.strings

/**
 *
 */
fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

/**
 *
 */
fun <T> Collection<T>.joinToStringExt(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

/**
 *
 */
fun Collection<String>.join(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
) = joinToStringExt(separator, prefix, postfix)

/**
 *
 */
fun String.lastChar() = get(length - 1);

/**
 * Extension property for String class
 */
val String.lastChar: Char
    get() = get(length - 1)

/**
 * Extension property for StringBuilder class
 */
var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        setCharAt(length - 1, value)
    }