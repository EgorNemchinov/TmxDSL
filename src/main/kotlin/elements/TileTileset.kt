package elements

/**
 * Created by Egor Nemchinov on 03.06.17.
 * SPbU, 2017
 */
class TileTileset : Tag("tile"){
    //local id within tileset
    var id: Int
        get() = attributes["id"]!!.toInt()
        set(value) {
            attributes["id"] = value.toString()
        }

    //optional:
    var terrain: Array<String> = Array(0){"0"}

    var probability: Float
        get() = attributes["probability"]!!.toFloat()
        set(value) {
            attributes["probability"] = probability.toString()
        }
}