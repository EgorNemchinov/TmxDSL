package elements

import java.awt.Color

/**
 * Created by Egor Nemchinov on 03.06.17.
 * SPbU, 2017
 */

class Image: UnaryTag("image") {
    //used for embedded images in combination with data child element
    var format: String
        get() = attributes["format"]!!
        set(value) {
            attributes["format"] = value
        }

    //or path?
    var source: String
        get() = attributes["source"]!!
        set(value) {
            attributes["source"] = value
        }

    var trans: Color
        get() = Color.decode(attributes["trans"]!!)
        set(value) {
            attributes["trans"] = value.encode()
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
}