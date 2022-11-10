package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.maps.MapLayer
import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.math._

import java.util.Hashtable
import scala.collection.JavaConverters._

import com.mygdx.game.AssetsManager

class MapManager{

    val playerStart = new Vector2(0, 0)
    val mapTable = new Hashtable[String, String]()

    mapTable.put(MapManager.SIMPLE_MAP, "maps/simple_map.tmx")

    val playerStartPositionRect = new Vector2(0, 0)
    val convertedUnits = new Vector2(0,0)

    var currentMap:TiledMap = null
    var currentMapName:String = null
    var previousMapName:String = null
    var collisionLayer:MapLayer = null

     /** 
     * laoadMap execute when used to load a specific map
     * @param mapName:String Name of the map
     */
    def loadMap(mapName:String){

    	val mapFullPath = Option(mapTable.get(mapName))

    	mapFullPath match{
    		case None => Gdx.app.debug(MapManager.TAG, "Map is invalid")
    			return
            case Some(x) => 
                if(currentMap != null){
                    currentMap.dispose()
                }
                AssetsManager.loadMapAsset(x)
                AssetsManager.getMapAsset(x) match{
                    case Some(y) => currentMap = y.asInstanceOf[TiledMap]
                        previousMapName = currentMapName
                        currentMapName = mapName
                    case None => 
                }
    	}
    }

    /**
     * Get current map Tiled for rendering
     * @return TiledMap Tiled of the Map
     */
    def getCurrentMap():TiledMap = {
    	if(currentMap == null){
    		currentMapName = MapManager.SIMPLE_MAP
    		loadMap(currentMapName)
    	}
    	currentMap
    }
}

object MapManager{

	/**
	 * Apply method for creating MapManager
     * @return MapManager New instance of MapManager
	 */
	def apply():MapManager = new MapManager

	private val TAG:String = MapManager.getClass.getSimpleName()

	val SIMPLE_MAP:String = "SIMPLE_MAP"
	private val MAP_COLLISION_LAYER:String = "MAP_COLLISION_LAYER"

	val UNIT_SCALE:Float = 1/16f

}