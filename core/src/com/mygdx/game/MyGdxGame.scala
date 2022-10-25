package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.math.Rectangle


class MyGdxGame extends ApplicationAdapter {
  
  //Create field names
  private[game] var batch: SpriteBatch = null

  //Create asset names
  private[game] var dirtTex: Texture = null
  private[game] var farmerTex: Texture = null
//   var farmerAnim: Animation = null

  //create object names
  var character: Character = new Character();

	override def create(): Unit = {

		//Assign field names to their function
		batch = new SpriteBatch()

		//Assign asset names to their sources
		dirtTex = new Texture(("dirt.png"))
		farmerTex = new Texture("walk and idle.png")
		// farmerAnim = new Animation(0.033f, atlas.findRegions(walk and idle.png), PlayMode.LOOP)

	}

	//render assets
	override def render(): Unit = {
		ScreenUtils.clear(2f, 2f, 2f, 1)
		batch.begin()
		//a bad way to tile sprites
		batch.draw(dirtTex, 0, 0)
		batch.draw(dirtTex, 400, 0)
		batch.draw(dirtTex, 0, 400)
		batch.draw(dirtTex, 400, 400)
		batch.draw(dirtTex, 800, 0)
		batch.draw(dirtTex, 800, 400)
		batch.draw(dirtTex, 1200, 0)
		batch.draw(dirtTex, 1200, 400)
		batch.draw(farmerTex, character.farmer.x, character.farmer.y) //spawn farmer at middle of screen
		batch.end()

		
		character.movementController(); //Calls to character movement every frame to enable user input
		
	}

	//cleans up used assets
	override def dispose(): Unit = {
		batch.dispose()
		dirtTex.dispose()
	}
}