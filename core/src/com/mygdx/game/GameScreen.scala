package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.{Gdx, Screen}
import com.badlogic.gdx.Input
import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.mygdx.game.MyGdxGame

object GameScreen{
    val farmerTex = new Texture("walk and idle.png")
}

class GameScreen(game: MyGdxGame) extends ApplicationAdapter with Screen {
  
  	//Create field names
	var batch: SpriteBatch = null

  	//Create asset names
	var dirtTex: Texture = null
	var animationFrames: Array[TextureRegion] = Array()
  	//create object names
  	var character: Character = new Character();

	
	override def create(): Unit = {

		batch = new SpriteBatch()
		dirtTex = new Texture(("dirt.png"))
		
	}

	//render assets
	override def render(delta: Float): Unit = {
		ScreenUtils.clear(2f, 2f, 2f, 1)
		// batch.begin()
		// //a bad way to tile sprites
		// batch.draw(dirtTex, 0, 0)
		// batch.draw(dirtTex, 400, 0)
		// batch.draw(dirtTex, 0, 400)
		// batch.draw(dirtTex, 400, 400)
		// batch.draw(dirtTex, 800, 0)
		// batch.draw(dirtTex, 800, 400)
		// batch.draw(dirtTex, 1200, 0)
		// batch.draw(dirtTex, 1200, 400)   
		// batch.end()

		character.movementController(); //Calls to character movement every frame to enable user input
		character.render()
	}

    override def hide(): Unit = {

    }

    override def show(): Unit = {

    }

	//cleans up used assets
	override def dispose(): Unit = {
		batch.dispose()
		dirtTex.dispose()
	}
}