package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.{Gdx, Screen, Input, Game}
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera, Texture}
import com.badlogic.gdx.graphics.g2d.{BitmapFont, Sprite, SpriteBatch, TextureRegion, Animation}
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.math.Rectangle

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

import com.mygdx.game.MyGdxGame
import com.mygdx.game.MapManager

class GameScreen(game: MyGdxGame) extends ApplicationAdapter with Screen {
  
  	//Create field names
	
	var mapRenderer = new OrthogonalTiledMapRenderer(GameScreen.mapMgr.getCurrentMap(), MapManager.UNIT_SCALE)

  	//Create asset names
	//var dirtTex: Texture = null
	var animationFrames: Array[TextureRegion] = Array()
  	//create object names
  	var character: Character = new Character();

	
	override def create(): Unit = {

		//batch = new SpriteBatch()
		
	}

	//render assets
	override def render(delta: Float): Unit = {
		ScreenUtils.clear(2f, 2f, 2f, 1)

		//render map
		mapRenderer.render()

		character.movementController(); //Calls to character movement every frame to enable user input
		character.render()

	}

    override def hide(): Unit = {

    }

    override def show(): Unit = {

    }

	//cleans up used assets
	override def dispose(): Unit = {

	}
}


object GameScreen{

	var mapMgr:MapManager = MapManager()
	val spriteBatch = new SpriteBatch()

	val farmerTex = new Texture("walk and idle.png")

}