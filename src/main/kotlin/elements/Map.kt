package elements

/**
 * Created by Egor Nemchinov on 01.06.17.
 * SPbU, 2017
 */
class Map: Tag("map") {
    var version: String
        get() = attributes["version"]!!
        set(value) {
            attributes["version"] = value
        }
    enum class Orientation { orthogonal, isometric, staggered, hexagonal }
    var orientation: Orientation
        get() = Orientation.valueOf(attributes["orientation"]!!)
        set(value) {
            attributes["orientation"] = value.toString()
        }

    enum class RenderOrder(val title: String) { rightDown("right-down"),
        rightUp("right-up"), leftDown("left-down"), leftUp("left-up");
        companion object {
            fun fromString(string: String): RenderOrder = values().firstOrNull{it.title == string} ?: rightDown
        }
        override fun toString(): String = title
    }
    var renderorder: RenderOrder
        get() = RenderOrder.fromString(attributes["renderorder"] ?: "right-down")
        set(value) {
            attributes["renderorder"] = value.title
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

    var tileWidth: Int
        get() = attributes["tileWidth"]!!.toInt()
        set(value) {
            attributes["tileWidth"] = value.toString()
        }

    var tileHeight: Int
        get() = attributes["tileHeight"]!!.toInt()
        set(value) {
            attributes["tileHeight"] = value.toString()
        }
    //Only for hexagonal maps.
    // Determines the width or height (depending on the staggered axis) of the tile's edge, in pixels.
    var hexsidelength: Int
        get() = attributes["hexsidelength"]!!.toInt()
        set(value) {
            attributes["hexsidelength"] = value.toString()
        }
    //TODO: implement attributes
    var staggeraxis = 0
    var staggerindex= 0
    var backgroundcolor = 0
    //fixme: companion object to be incremented?
    var nextobjectid: Int
        get() = attributes["nextobjectid"]!!.toInt()
        set(value) {
            attributes["nextobjectid"] = value.toString()
        }

    fun tileset(firstgid: Int, source: String? = null,  name: String, tilewidth: Int, tileheight: Int,
                spacing: Int = 0, margin: Int = 0, tilecount: Int, columns: Int,
                init: Tileset.() -> Unit): Tileset {
        val tileset = initTag(Tileset(), init)
        tileset.apply {
            this.firstgid = firstgid
            if(source != null) this.source = source
            this.name = name
            this.tilewidth = tilewidth
            this.tileheight = tileheight
            if(spacing != 0) this.spacing = spacing
            if(margin != 0) this.margin = margin
            this.tilecount = tilecount
            this.columns = columns
        }
        return tileset
    }
    fun layer(name: String, width: Int, height: Int, opacity: Float = 1f,
              visible: Boolean = true, offsetx: Int = 0, offsety: Int = 0,
              init: Layer.() -> Unit): Layer {
        val layer = initTag(Layer(), init)
        layer.apply {
            this.name = name
            this.width = width
            this.height = height
            if(opacity != 1f) this.opacity = opacity
            if(!visible) this.visible = visible
            if(offsetx != 0) this.offsetx = offsetx
            if(offsety != 0) this.offsety = offsety
        }
        return layer
    }
}

//TODO: other properties
fun map(version: String = "1.0", orientation: Map.Orientation, renderorder: Map.RenderOrder = Map.RenderOrder.rightDown,
        width: Int, height: Int, tileWidth: Int, tileHeight: Int, hexsidelength: Int? = null,
        init: Map.() -> Unit): Map {
    val map = Map()
    map.init()
    map.apply {
        this.version = version
        this.orientation = orientation
        if(renderorder != Map.RenderOrder.rightDown)
            this.renderorder = renderorder
        this.width = width
        this.height = height
        if(hexsidelength != null)
            this.hexsidelength = hexsidelength
        this.tileWidth = tileWidth
        this.tileHeight = tileHeight
    }
    return map
}