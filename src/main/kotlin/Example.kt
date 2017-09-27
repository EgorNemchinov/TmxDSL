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
    val mapWidth = 15
    val mapHeight = 10  
    val tilePack = hashMapOf<String, Any>(
            "tilewidth" to 20,"tileheight" to 20, "count" to 2,
            "columns" to 2, "trans" to Color.RED, "path" to "texturePack.png")

    val randomizer = Random()
    var tiles = List(mapHeight, {List(mapWidth, {0})})
    tiles = tiles.map{it.map{ randomizer.nextInt(2) + 1} }

    val map =
    map(orientation = Map.Orientation.orthogonal, width = mapWidth, height = mapHeight,
            tilewidth = tilePack["tilewidth"] as Int, tileheight = tilePack["tileheight"] as Int) {
        tileset(firstgid = 1, name = "tiles", tilewidth = tilePack["tilewidth"] as Int,
                tileheight = tilePack["tilewidth"] as Int, tilecount = tilePack["count"] as Int,
                columns = tilePack["count"] as Int) {
            image(source = tilePack["path"] as String, trans = tilePack["trans"] as Color,
                    width = (tilePack["tilewidth"] as Int) * (tilePack["count"] as Int),
                    height = tilePack["tileheight"] as Int)
        }
        layer(name = "tiles", width = mapWidth, height = mapHeight) {
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
    println(map)
    val output = File("${System.getProperty("user.dir")}/src/main/resources/map.tmx")

    output.writeText(map.toString())
}