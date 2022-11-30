package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.{Gdx, Screen, Input, Game}
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.MyGdxGame
import com.mygdx.game.GameScreen

class MenuScreen(game: MyGdxGame) extends ApplicationAdapter with Screen{
 
	val sb = new SpriteBatch()
	val intro = MenuScreen.intro
	var time = 0.0
	val gameScreen = new GameScreen(game: MyGdxGame)
    
	override def hide(): Unit = {

    }

	//cleans up used assets
	override def dispose(): Unit = {

	}


	override def show () {
		sb.getProjectionMatrix().setToOrtho2D(0, 0, 480, 320);
	}

	override def render(delta:Float) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		sb.draw(intro, 0, 0);
		sb.end();

		time += delta;
		if (time > 1) {
			if (Gdx.input.isKeyPressed(Keys.ANY_KEY) || Gdx.input.justTouched()) {
				game.setScreen(gameScreen)
			}
		}
	}
}

object MenuScreen{

	def apply(game:MyGdxGame):MenuScreen = new MenuScreen(game)

	private val TAG:String = GameScreen.getClass.getSimpleName
    val intro = new TextureRegion(new Texture(("PlayBtn.png")), 0, 0, 480, 320)	

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
		}
	}
}