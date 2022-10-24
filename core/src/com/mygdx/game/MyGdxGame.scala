package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input;
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

  //create object names
  private[game] var farmer: Rectangle = null

	override def create(): Unit = {

		//Assign field names to their function
		batch = new SpriteBatch()

		//Assign asset names to their sources
		dirtTex = new Texture("dirt.png")
		farmerTex = new Texture("farmer.png")

		//Assign object names to their values
		farmer = new Rectangle()
		farmer.x = 1280 / 2
		farmer.y = 720 / 2
		farmer.width = 64
		farmer.height = 64
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
		//spawn farmer at middle of screen
		batch.draw(farmerTex, farmer.x, farmer.y)
		batch.end()

		//basic form of movement
		//need to add delay frame or single press function
		// since holding a key makes him zoom
		var speedConstant = 500;
		if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){
			farmer.y = farmer.y + 500 * Gdx.graphics.getDeltaTime();
			if(farmer.y < 0) {farmer.y = 0}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			farmer.x = farmer.x - 500 * Gdx.graphics.getDeltaTime();
			if(farmer.x < 0) {farmer.x = 0}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			farmer.y = farmer.y - 500 * Gdx.graphics.getDeltaTime();
			if(farmer.y > 400) {farmer.y = 400}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			farmer.x = farmer.x + 500 * Gdx.graphics.getDeltaTime();
			if(farmer.x > 1200) { farmer.x = 1200}
		}
	}

	//cleans up used assets
	override def dispose(): Unit = {
		batch.dispose()
		dirtTex.dispose()
	}
}