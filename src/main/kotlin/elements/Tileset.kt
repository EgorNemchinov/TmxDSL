package elements

import java.awt.Color

/**
 * Created by Egor Nemchinov on 01.06.17.
 * SPbU, 2017
 */
class Tileset: Tag("tileset") {

    var firstgid: Int
        get() = attributes["firstgid"]!!.toInt()
        set(value) {
            attributes["firstgid"] = value.toString()
        }

    var source: String
        get() = attributes["source"]!!
        set(value) {
            attributes["source"] = value
        }
    var name: String
        get() = attributes["name"]!!
        set(value) {
            attributes["name"] = value
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

    var tilewidth: Int
        get() = attributes["tilewidth"]!!.toInt()
        set(value) {
            attributes["tilewidth"] = value.toString()
        }

    var tileheight: Int
        get() = attributes["tileheight"]!!.toInt()
        set(value) {
            attributes["tileheight"] = value.toString()
        }

    var spacing: Int
        get() = attributes["spacing"]!!.toInt()
        set(value) {
            attributes["spacing"] = value.toString()
        }

    var margin: Int
        get() = attributes["margin"]!!.toInt()
        set(value) {
            attributes["margin"] = value.toString()
        }

    var tilecount: Int
        get() = attributes["tilecount"]!!.toInt()
        set(value) {
            attributes["tilecount"] = value.toString()
        }

    var columns: Int
        get() = attributes["columns"]!!.toInt()
        set(value) {
            attributes["columns"] = value.toString()
        }

    fun image(format: String? = null, source: String, trans: Color? = null,
              width: Int, height: Int): Image {
        val image = initTag(Image(), {})
        image.apply {
            if(format != null)
                this.format = format
            this.source = source
            if(trans != null)
                this.trans = trans
            this.width = width
            this.height = height
        }
        return image
    }
}