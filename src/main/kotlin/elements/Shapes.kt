package elements

/**
 * Created by Egor Nemchinov on 23.08.17.
 * SPbU, 2017
 */
//I'm in love with the shape of you
class Ellipse: UnaryTag("ellipse")

class Polygon: UnaryTag("polygon") {
    var points: List<Point>
        get() = parsePoints(attributes["points"]!!)
        set(value) {
            attributes["points"] = stringOf(value)
        }
}

class Polyline: UnaryTag("polygon") {
    var points: List<Point>
        get() = parsePoints(attributes["points"]!!)
        set(value) {
            attributes["points"] = stringOf(value)
        }
}

fun parsePoints(line: String): List<Point> {
    return line.split(" ").map{it.split(",")}
            .map { Point(it.first().toFloat(), it.last().toFloat()) }
}

fun stringOf(points: List<Point>): String {
    val sb = StringBuilder()
    points.map { sb.append(it).append(" ") }
    return sb.toString()
}
