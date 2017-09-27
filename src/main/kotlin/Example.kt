import elements.*
import elements.Map
import java.awt.Color
import java.io.File
import java.io.FileWriter
import java.util.*

/**
 * Created by Egor Nemchinov on 04.06.17.
 * SPbU, 2017
 */
fun main(args: Array<String>) {
    val randomizer = Random()
    var tiles = List(10, {List(15, {0})})
    tiles = tiles.map{it.map{ randomizer.nextInt(2) + 1} }
    val map =
    map(version = "1.0", orientation = Map.Orientation.orthogonal, width = 15, height = 10,
            tilewidth = 20, tileheight = 20) {
        tileset(firstgid = 1, name = "tiles", tilewidth = 20, tileheight = 20, tilecount = 2, columns = 2) {
            image(source = "texturePack.png", trans = Color.RED, width = 40, height = 20)
        }
        layer(name = "tiles", width = 15, height = 10) {
            data(encoding = Data.Encoding.csv) {
                +tiles
            }
        }
        objectgroup(name = "objects", color = Color.RED) {
            object_(x = 40, y = 40, width = 60, height = 60) {
                ellipse()
            }
            object_(x = 180, y = 40, width = 60, height = 60) {
                ellipse()
            }
            object_(x = 100, y = 140, width = 80, height = 40)
            object_(x = 18, y = 64) {
                polygon(listOf(Point(0f, 0f), Point(62f, -44f), Point(222f, -44f),
                               Point(262f, 16f), Point(202f, 136f), Point(62f, 136f)))
            }
            object_(x = 60, y = 20) {
                polyline(listOf(Point(0f, 0f), Point(60f, 23f), Point(120f, 20f),
                                Point(160f, -20f)))
            }
        }
    }
//    println(map)
    val output = File("${System.getProperty("user.dir")}/src/main/kotlin/resources/map.tmx")

    output.writeText(map.toString())
}