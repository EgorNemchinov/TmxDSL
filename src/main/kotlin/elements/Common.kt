package elements

/**
 * Created by Egor Nemchinov on 01.06.17.
 * SPbU, 2017
 */
interface Element {
    fun render(builder: StringBuilder, indent: String)
}

class TextElement(val text: String) : Element {
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent$text\n")
    }

    override fun toString(): String {
        return text
    }
}

@DslMarker
annotation class TmxTagMarker

@TmxTagMarker
abstract class Tag(val tagName: String) : Element {
    val children = arrayListOf<Element>()
    val attributes = hashMapOf<String, String>()

    protected fun <T : Element> initTag(tag: T, init: T.() -> Unit): T {
        tag.init()
        children.add(tag)
        return tag
    }

    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent<$tagName${renderAttributes()}>\n")
        for (c in children) {
            c.render(builder, indent + "  ")
        }
        builder.append("$indent</$tagName>\n")
    }

    protected fun renderAttributes(): String {
        val builder = StringBuilder()
        for ((attr, value) in attributes) {
            builder.append(" $attr=\"$value\"")
        }
        return builder.toString()
    }

    override fun toString(): String {
        val builder = StringBuilder()
        render(builder, "")
        return builder.dropLast(1).toString()
    }
}

abstract class UnaryTag(tagName: String) : Tag(tagName) {
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent<$tagName${renderAttributes()}/>\n")
        return
    }
}

abstract class TagWithText(tagName: String) : Tag(tagName) {
    operator fun String.unaryPlus() {
        children.add(TextElement(this))
    }
}

enum class Axis {
    x, y;
}

data class Point(var x: Float, var y: Float) {
    override fun toString(): String {
        return "$x,$y"
    }
}