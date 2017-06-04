package elements

/**
 * Created by Egor Nemchinov on 01.06.17.
 * SPbU, 2017
 */

class Layer: Tag("layer") {
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
    var opacity: Float
        get() = attributes["opacity"]!!.toFloat()
        set(value) {
            attributes["opacity"] = value.toString()
        }
    var visible: Boolean
        get() = attributes["visible"]!!.toInt() == 1
        set(value) {
            attributes["visible"] = if(value) "1" else "0"
        }
    var offsetx: Int
        get() = attributes["offsetx"]!!.toInt()
        set(value) {
            attributes["offsetx"] = value.toString()
        }
    var offsety: Int
        get() = attributes["offsety"]!!.toInt()
        set(value) {
            attributes["offsety"] = value.toString()
        }

    fun data(encoding: Data.Encoding = Data.Encoding.none, compression: Data.Compression = Data.Compression.none,
             init: Data.() -> Unit): Data {
        val data = initTag(Data(), init)
        data.apply {
            if(compression != Data.Compression.none)
                this.compression = compression
            if(encoding != Data.Encoding.none)
                this.encoding = encoding
        }
        return data
    }
}

