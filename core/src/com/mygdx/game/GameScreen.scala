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
import com.mygdx.game.Character

class GameScreen(game: MyGdxGame) extends ApplicationAdapter with Screen {
  
  	//Create field names
	
	var animationFrames: Array[TextureRegion] = Array()
  	var character: Character = new Character();
	var mapRenderer = new OrthogonalTiledMapRenderer(GameScreen.mapMgr.getCurrentMap(), MapManager.UNIT_SCALE)
	val camera = new OrthographicCamera()
	val spriteBatch = new SpriteBatch()

	override def show{

		GameScreen.VIEWPORT.setupViewport(15, 15, Gdx.graphics.getWidth(), Gdx.graphics.getHeight())

		camera.setToOrtho(false, GameScreen.VIEWPORT.viewportWidth, GameScreen.VIEWPORT.viewportHeight)
		mapRenderer.setView(camera)

		Gdx.app.debug(GameScreen.TAG, s"UnitScale Value is: ${mapRenderer.getUnitScale()}")

	}

	//render assets
	override def render(delta:Float){

		Gdx.gl.glClearColor(0, 0, 0, 1)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

		camera.position.set(5, 5, 0f) // need to edit to follow player
		camera.update

		mapRenderer.setView(camera)
		mapRenderer.render()
		mapRenderer.getBatch().begin()

		character.movementController(); //Calls to character movement every frame to enable user input
		character.render()

		mapRenderer.getBatch().end()
	}

    override def hide(): Unit = {

    }

	//cleans up used assets
	override def dispose(): Unit = {

	}
}


object GameScreen{

	def apply(game:MyGdxGame):GameScreen = new GameScreen(game)

	private val TAG:String = GameScreen.getClass.getSimpleName

	var mapMgr:MapManager = MapManager()

	val farmerTex = new Texture("walk and idle.png")

	private object VIEWPORT{
		var viewportWidth:Float = 0
		var viewportHeight:Float = 0
		var virtualWidth:Float = 0
		var virtualHeight:Float = 0
		var physicalWidth:Float = 0
		var physicalHeight:Float = 0
		var aspectRatio:Float = 0

		/**
		 * Setup Viewport of the game
		 * @param  width:Int     Expected width of viewport
		 * @param  height:Int    Expected height of viewport
		 * @param  phyWidth:Int  Physical width of the window
		 * @param  phyHeight:Int Physical height of the window
		 */
		def setupViewport(width:Int, height:Int, phyWidth:Int, phyHeight:Int){
			virtualWidth = width
			virtualHeight = height

			viewportWidth = virtualWidth
			viewportHeight = virtualHeight

			physicalWidth = phyWidth
			physicalHeight = phyHeight

			aspectRatio = (virtualWidth / virtualHeight)

			if(physicalWidth / physicalHeight >= aspectRatio){
				viewportWidth = viewportHeight * (physicalWidth / physicalHeight)
				viewportHeight = virtualHeight
			} else {
				viewportWidth = virtualWidth
				viewportHeight = viewportWidth * (physicalHeight / physicalWidth)
			}
			Gdx.app.debug(GameScreen.TAG, s"WorldRenderer: virtual: (${virtualWidth},${virtualHeight})" );
			Gdx.app.debug(GameScreen.TAG, s"WorldRenderer: viewport: (${viewportWidth},${viewportHeight})" );
			Gdx.app.debug(GameScreen.TAG, s"WorldRenderer: physical: (${physicalWidth},${physicalHeight})" );
		}
	}
}