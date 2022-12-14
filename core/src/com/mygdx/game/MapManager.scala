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

    val mapTable = new Hashtable[String, String]()
    mapTable.put(MapManager.OUTSIDE_MAP, "maps/outside_map.tmx")
    val convertedUnits = new Vector2(0,0)

    var currentMap:TiledMap = null
    var currentMapName:String = null
    var previousMapName:String = null
    var collisionLayer:MapLayer = null

     /** 
     * laoadMap a specific map
     * @param mapName:String Name of map
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
     * Get current tile map for render
     * @return TiledMap
     */
    def getCurrentMap():TiledMap = {
    	if(currentMap == null){
    		currentMapName = MapManager.OUTSIDE_MAP
    		loadMap(currentMapName)
    	}
    	currentMap
    }
}

object MapManager{

	/**
	 * method for creating MapManager
     * @return New instance of MapManager
	 */
	def apply():MapManager = new MapManager

	private val TAG:String = MapManager.getClass.getSimpleName()

	val OUTSIDE_MAP:String = "OUTSIDE_MAP"

	val UNIT_SCALE:Float = 1/16f

}