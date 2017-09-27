package elements

import java.awt.Color
import elements.Objectgroup.DrawOrder
/**
 * Created by Egor Nemchinov on 01.06.17.
 * SPbU, 2017
 */
// Map is almost finished. Left to decide what
// to do with 'nextobjectid'
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
    //Only for hexagonal maps.
    // Determines the width or height (depending on the staggered axis) of the tile's edge, in pixels.
    var hexsidelength: Int
        get() = attributes["hexsidelength"]!!.toInt()
        set(value) {
            attributes["hexsidelength"] = value.toString()
        }

    var staggeraxis: Axis
        get() = Axis.valueOf(attributes["staggeraxis"]!!)
        set(value) {
            attributes["staggeraxis"] = value.toString()
        }
    enum class StaggerIndex { even, odd }
    var staggerindex: StaggerIndex
        get() = StaggerIndex.valueOf(attributes["staggerindex"]!!)
        set(value) {
            attributes["staggerindex"] = value.toString()
        }

    var backgroundcolor: Color
        get() = Color.decode(attributes["backgroundcolor"]!!)
        set(value) {
            attributes["backgroundcolor"] = value.encode()
        }

    //fixme: companion object to be incremented?
    companion object {
        var id: Int = 1
        fun nextId(): Int = id++
    }
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

    fun objectgroup(name: String, color: Color = Color.BLACK, x: Int = 0, y: Int = 0, width: Int? = null,
                    height: Int? = null, opacity: Float = 1f, visible: Boolean = true, offsetx: Int = 0,
                    offsety: Int = 0, draworder: DrawOrder = DrawOrder.topdown, init: Objectgroup.() -> Unit): Objectgroup {
        var objectgroup = initTag(Objectgroup(), init)
        objectgroup.apply {
            this.name = name
            this.color = color
            if(x != 0) this.x = x
            if(y != 0) this.y = y
            if(width != null) this.width = width
            if(height != null) this.height = height
            if(opacity != 1f) this.opacity = opacity
            if(!visible) this.visible = visible
            if(offsetx != 0) this.offsetx = offsetx
            if(offsety != 0) this.offsety = offsety
            if(draworder != DrawOrder.topdown) this.draworder = draworder
        }
        return objectgroup
    }

    override fun render(builder: StringBuilder, indent: String) {
        builder.insert(0, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
        this.nextobjectid = id
        super.render(builder, indent)
        val actual = builder.replace("nextobjectid=\"0\"".toRegex(), "nextobjectid=\"${Map.id}\"")
        builder.setLength(0)
        builder.append(actual)
    }
}

//TODO: other properties
fun map(version: String = "1.0", orientation: Map.Orientation, renderorder: Map.RenderOrder = Map.RenderOrder.rightDown,
        hexsidelength: Int? = null, staggeraxis: Axis? = null, staggerindex: Map.StaggerIndex? = null,
        width: Int, height: Int, tilewidth: Int, tileheight: Int,  backgroundcolor: Color? = null,
        init: Map.() -> Unit): Map {
    val map = Map()
    Map.id = 1
    map.init()
    map.apply {
        this.version = version
        this.orientation = orientation
        if(renderorder != Map.RenderOrder.rightDown)
            this.renderorder = renderorder
        if(staggeraxis != null)
            this.staggeraxis = staggeraxis
        if(staggerindex != null)
            this.staggerindex = staggerindex
        this.width = width
        this.height = height
        if(hexsidelength != null)
            this.hexsidelength = hexsidelength
        if(backgroundcolor != null)
            this.backgroundcolor = backgroundcolor
        this.tilewidth = tilewidth
        this.tileheight = tileheight
    }
    return map
}