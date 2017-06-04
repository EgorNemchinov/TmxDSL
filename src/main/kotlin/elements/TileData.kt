package elements

/**
 * Created by Egor Nemchinov on 03.06.17.
 * SPbU, 2017
 */
class TileData: UnaryTag("tile") {
    var gid: Int
        get() = attributes["gid"]!!.toInt()
        set(value) {
            attributes["gid"] = value.toString()
        }
}
