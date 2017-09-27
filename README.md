# TmxDSL
This library allows to create .tmx files, which [Tiled Map Editor](http://www.mapeditor.org/) is able to open.

#### Why is it any better, than writing raw .tmx in Notepad?
Good question. Long story short, the reasons are:
1. **Creating maps programmatically**. 
   - Procedural level generation? Get a function that creates 2D array of tiles (indices in tileset) and another that generates objects shapes. 
   - Use built-in variables. To make orange background: pass "backgroundcolor = Color.Orange" to Map constructor. Or import libraries working with Color to make operations with them.
2. **Easy to work with TMX file structure**
   - Code creating a map looks similar to raw .tmx, which is why most of the information concerning simple .tmx files can be applied to this library.
   - Lets you look up possible parameters, their types and possible values (for Enums).

## Examples:
We all know that a block of code is worth a thousand words.
```
val tiles = List(2, {List(5, {0})}) //Generating 2D array with zeros
val map =
map(orientation = Map.Orientation.orthogonal, width = 5, height = 2, tilewidth = 20, tileheight = 20) {
    tileset(firstgid = 1, name = "tiles", tilewidth = 20, tileheight = 20, tilecount = 2, columns = 2) {
        image(source = "texturePack.png", trans = Color.RED, width = 40, height = 20)
    }
    layer(name = "tiles", width = 5, height = 2) {
        data(encoding = Data.Encoding.csv) {
            +tiles
        }
    }
}
println(map)
```
We can use the power of fields and get most of the information about the map out:
```
val mapWidth = 5
val mapHeight = 2
val tilePack = hashMapOf<String, Any>(
        "tilewidth" to 20, "tileheight" to 20, "count" to 2,
        "columns" to 2, "trans" to Color.RED, "path" to "texturePack.png")
```
Now the code is not so compact and pretty, but more flexible:
```
val tiles = List(mapHeight, {List(mapWidth, {0})})
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
}
println(map)  
```
Example above looks like corresponding xml-file. Here's output:
```
<?xml version="1.0" encoding="UTF-8"?>
<map tileheight="20" orientation="orthogonal" width="5" version="1.0" tilewidth="20" nextobjectid="1" height="2">
  <tileset tileheight="20" columns="2" name="tiles" firstgid="1" tilewidth="20" tilecount="2">
    <image width="40" source="texturePack.png" trans="#FF0000" height="20"/>
  </tileset>
  <layer name="tiles" width="5" height="2">
    <data encoding="csv">
0,0,0,0,0,
0,0,0,0,0
    </data>
  </layer>
</map>
```
Now let's make randomly-generated map, for that we'll just fill array "tiles" with values "1" or "2":
```
val randomizer = Random()
var tiles = List(mapHeight, {List(mapWidth, {0})})
tiles = tiles.map{it.map{ randomizer.nextInt(2) + 1} }
```
Then, when we open the map in Tiled, we'll see something like this:
![Screenshot from Tiled](https://user-images.githubusercontent.com/22173703/30928440-5b187480-a3c4-11e7-8098-cd25f3b3c04b.png)
#### You can see similar example containing tiles _and_ objects in the repo: [link](https://github.com/ImmortalTurtle/TmxDSL/blob/master/src/main/kotlin/Example.kt)

## Notes
Basic idea and implementation were partly taken from [Kotlinx.html](https://github.com/Kotlin/kotlinx.html).

There are a **lot** of unimplemented tags.
Also would be great to allow to parse and edit .tmx: adding and removing some objects, for example.
