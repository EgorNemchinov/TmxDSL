package elements

import java.util.*

/**
 * Created by Egor Nemchinov on 03.06.17.
 * SPbU, 2017
 */
class Data: TagWithText("data") {
    enum class Encoding { none, csv, base64 }
    enum class Compression {none, gzip, zlib}

    var encoding: Encoding
        get() = Encoding.valueOf(attributes["encoding"] ?: "none")
        set(value) {
            attributes["encoding"] = value.toString()
        }
    var compression: Compression
        get() = Compression.valueOf(attributes["compression"] ?: "none")
        set(value) {
            attributes["compression"] = value.toString()
        }
    var tiles: List<List<Int>>? = null

    operator fun List<List<Int>>.unaryPlus() {
        if (tiles != null) {
            error("Tiles are already defined")
        }
        tiles = this
    }

    //TODO: compressions & base64
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent<$tagName${renderAttributes()}>\n")
        //first compress, then encode
        if (compression != Compression.none)
            throw NotImplementedError("Compression is not implemented yet.")
        if(tiles != null) {
            val dataBuilder = StringBuilder()
            var dataString = ""
            when(encoding) {
                Encoding.base64 -> {
                    throw NotImplementedError("Base64 encoding is not implemented yet.")
                }
                Encoding.csv -> {
                    val height = tiles!!.size
                    val width = tiles!![0].size
                    tiles!!.mapIndexed{rowInd, rowValues -> rowValues.mapIndexed {
                                colInd, colValue ->
                                dataBuilder.append(colValue)
                                           .append(if (rowInd == height-1 && colInd == width-1) "" else ",")
                                           .append(if (colInd == width-1) "\n" else "" )
                        }
                    }
                    dataString = dataBuilder.toString()
                }
                Encoding.none -> {
                    throw Exception("Defined tiles twice: XML-like and by array.")
                }
            }
            builder.append(dataString)
        } else {
            children.map { builder.append("$indent  $it\n") }
        }
        builder.append("$indent</$tagName>\n")
    }

    fun tile(gid: Int): TileData {
        val tile = initTag(TileData(), {})
        tile.gid = gid
        return tile
    }
}
