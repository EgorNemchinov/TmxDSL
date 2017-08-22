package elements

import java.awt.Color

/**
 * Created by Egor Nemchinov on 14.06.17.
 * SPbU, 2017
 */
class Objectgroup: Tag("objectgroup") {
    var name: String
        get() = attributes["name"]!!
        set(value) {
            attributes["name"] = value
        }

    var color: Color
        get() = Color.decode(attributes["color"] ?: "#000000")
        set(value) {
            attributes["color"] = value.encode()
        }

    var x: Int
        get() = attributes["x"]!!.toInt()
        set(value) {
            attributes["x"] = value.toString()
        }
    var y: Int
        get() = attributes["y"]!!.toInt()
        set(value) {
            attributes["y"] = value.toString()
        }

    var width: Int
        get() = attributes["width"]!!.toInt()
        set(value) {
            attributes["width"] = value.toString()
        }
    var height: Int
        get() = attributes["height"]!!.toInt()
        set(value) {
            attributes["height"] = value.toString()
        }

    var opacity: Float
        get() = attributes["opacity"]?.toFloat() ?: 1f
        set(value) {
            attributes["opacity"] = value.toString()
        }

    var visible: Boolean
        get() = attributes["visible"]?.toInt() ?: 1 == 1
        set(value) {
            attributes["visible"] = if(value) "1" else "0"
        }

    var offsetx: Int
        get() = attributes["offsetx"]?.toInt() ?: 0
        set(value) {
            attributes["offsetx"] = value.toString()
        }

    var offsety: Int
        get() = attributes["offsety"]?.toInt() ?: 0
        set(value) {
            attributes["offsety"] = value.toString()
        }

    enum class DrawOrder {index, topdown}

    var draworder: DrawOrder
        get() = DrawOrder.valueOf(attributes["draworder"] ?: "topdown")
        set(value) {
            attributes["draworder"] = value.toString()
        }

    fun object_(name: String = "", type: String = "", x: Int = 0, y: Int = 0,
                width: Int = 0, height: Int = 0, rotation: Int = 0,
                gid: Int = -1, visible: Boolean = true, init: Object.() -> Unit = {}) {
        var object_ = initTag(Object(), init)
        object_.apply {
            this.id = Map.nextId()
            if(name.isNotEmpty()) this.name = name
            if(type.isNotEmpty()) this.type = type
            if(x != 0) this.x = x
            if(y != 0) this.y = y
            if(width != 0) this.width = width
            if(height != 0) this.height = height
            if(rotation != 0) this.rotation = rotation
            if(gid != -1) this.gid = gid
            if(!visible) this.visible = visible
        }
    }
}