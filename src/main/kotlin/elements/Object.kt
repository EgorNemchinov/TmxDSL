package elements

/**
 * Created by Egor Nemchinov on 29.06.17.
 * SPbU, 2017
 */

//TODO: sometimes UniTag (if inti has no body)
class Object: Tag("object") {

    var id: Int
        get() = attributes["id"]!!.toInt()
        set(value) {
            attributes["id"] = value.toString()
        }

    var name: String
        get() = attributes["name"]!!
        set(value) {
            attributes["name"] = value
        }

    var type: String
        get() = attributes["type"]!!
        set(value) {
            attributes["tyoe"] = value
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

    var rotation: Int //clockwise, defaults to 0
        get() = attributes["rotation"]!!.toInt()
        set(value) {
            attributes["rotation"] = value.toString()
        }

    var gid: Int //reference to a tile, optional
        get() = attributes["gid"]!!.toInt()
        set(value) {
            attributes["gid"] = value.toString()
        }

    var visible: Boolean
        get() = attributes["visible"]!!.toInt() == 1
        set(value) {
            attributes["visible"] = if(value) "1" else "0"
        }

    fun ellipse() {
        val ellipse = initTag(Ellipse(), {})
    }

    fun polygon(points: List<Point>) {
        val polygon = initTag(Polygon(), {})
        polygon.points = points
    }

    fun polyline(points: List<Point>) {
        val polyline = initTag(Polyline(), {})
        polyline.points = points
    }


}
