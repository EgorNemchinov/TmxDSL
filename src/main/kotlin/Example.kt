import elements.*
import elements.Map
import java.awt.Color

/**
 * Created by Egor Nemchinov on 04.06.17.
 * SPbU, 2017
 */
fun main(args: Array<String>) {
    val map =
    map(version = "1.0", orientation = Map.Orientation.orthogonal, width = 14, height = 9, tileWidth = 16, tileHeight = 16) {
        tileset(firstgid = 1, name = "tiles", tilewidth = 16, tileheight = 16, tilecount = 18, columns = 9) {
            image(source = "../texturePack.png", trans = Color.BLACK, width = 100, height = 200)
        }
        layer(name = "background", width = 14, height = 9) {
            data(encoding = Data.Encoding.base64) {
                +"Adding array of IDs is coming soon."
                +"Summer 2017."
                +"On every screen."
            }
        }
        layer(name = "frontground", width = 14, height = 9) {
            data {
                tile(1)
                tile(3)
            }
        }
    }
    println(map)
}