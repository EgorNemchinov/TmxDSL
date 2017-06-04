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

    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent<$tagName${renderAttributes()}>\n")
        children.map {
            it -> when(encoding) {
                Encoding.base64 ->
                    Base64.getEncoder().encodeToString(it.toString().toByteArray())
                Encoding.csv -> "" //not implemented
                else -> it.toString()
            }
        }.map { builder.append("$indent  $it\n") }
        builder.append("$indent</$tagName>\n")
    }

    fun tile(gid: Int): TileData {
        val tile = initTag(TileData(), {})
        tile.gid = gid
        return tile
    }
}
