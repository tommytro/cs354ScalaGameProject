package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.maps.tiled.{TiledMap, TmxMapLoader}
import com.badlogic.gdx.scenes.scene2d.ui.Skin

object AssetsManager{
	private val TAG = AssetsManager.getClass.getSimpleName()
	private val assetManager = new AssetManager()
	private val filePathResolver = new InternalFileHandleResolver()

	/**
	 * Unload loaded assets
	 * @param assetFilenamePath:String Path to asset
	 */
	def unloadAsset(assetFilenamePath:String){
		if(assetManager.isLoaded(assetFilenamePath)){
			assetManager.unload(assetFilenamePath)
		} else {
			Gdx.app.debug(AssetsManager.TAG, s"Asset is not loaded; Nothing to unload: $assetFilenamePath")
		}
	}

	/**
	 * Load map asset
	 * @param mapFilenamePath:String Path to asset
	 */
	def loadMapAsset(mapFilenamePath:String){
		if(filePathResolver.resolve(mapFilenamePath).exists()){
			assetManager.setLoader(classOf[TiledMap], new TmxMapLoader(filePathResolver))
			assetManager.load(mapFilenamePath, classOf[TiledMap])
			assetManager.finishLoadingAsset(mapFilenamePath)
			Gdx.app.debug(AssetsManager.TAG, s"Map loaded!: $mapFilenamePath")
		} else {
			Gdx.app.debug(AssetsManager.TAG, s"Map doesn't exist!: $mapFilenamePath")
		}
	}

	/**
	 * Get loaded map
	 * @param  mapFilenamePath  Path to asset
	 * @return Option[TiledMap]
	 */
	def getMapAsset(mapFilenamePath:String):Option[TiledMap] = {
		if(assetManager.isLoaded(mapFilenamePath)){
			Option(assetManager.get(mapFilenamePath, classOf[TiledMap]))
		} else {
			Gdx.app.debug(AssetsManager.TAG, s"Map is not loaded: $mapFilenamePath")
			Option[TiledMap](null)
		}
	}

	/**
	 * Load texture asset
	 * @param textureFilenamePath:String Path to the asset
	 */
	def loadTextureAsset(textureFilenamePath:String){
		if(filePathResolver.resolve(textureFilenamePath).exists()){
			assetManager.setLoader(classOf[Texture], new TextureLoader(filePathResolver))
			assetManager.load(textureFilenamePath, classOf[Texture])
			assetManager.finishLoadingAsset(textureFilenamePath)
		} else {
			Gdx.app.debug(AssetsManager.TAG, s"Texture doesn't exist!: $textureFilenamePath")
		}
	}

	/**
	 * Get loaded texture
	 * @param  textureFilenamePath:String Path to asset
	 * @return Option[Texture]
	 */
	def getTextureAsset(textureFilenamePath:String):Option[Texture] = {
		if(assetManager.isLoaded(textureFilenamePath)){
			Option(assetManager.get(textureFilenamePath, classOf[Texture]))
		} else {
			Gdx.app.debug(AssetsManager.TAG, s"Texture is not loaded: $textureFilenamePath")
			Option[Texture](null)
		}
	}
}