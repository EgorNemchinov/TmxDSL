package elements

import java.awt.Color

/**
 * Created by Egor Nemchinov on 14.06.17.
 * SPbU, 2017
 */

fun String.zeroesPrefix(requiredLength: Int): String{
    val difference = requiredLength - this.length
    return when {
        difference <= 0 -> this
        else -> buildString { (0..difference-1).map {append('0')}} + this
    }
}
fun Color.encode(): String = "#${Integer.toHexString(red.shl(16)+green.shl(8)+blue).toUpperCase().zeroesPrefix(6)}"
