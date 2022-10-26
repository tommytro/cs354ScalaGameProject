package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
// import com.mygdx.game.MyGdxGame

class Character{
    
	
    var moveSpeed : Float = 4
    var attackSpeed: Float = 4
    var useSpeed: Float = 4
    var farmer: Rectangle = new Rectangle(1280/2, 720/2, 0, 0)
	val farmerTex: Texture = GameScreen.farmerTex
	var tmpFrames: Array[Array[TextureRegion]] = TextureRegion.split(farmerTex, farmerTex.getWidth() / 8, farmerTex.getHeight() / 3)
	var animation = new Animation(.086f, tmpFrames(1): _*)
	val spriteBatch = new SpriteBatch()
	var currentFrame: TextureRegion = null
	var stateTime = 0f

    def movementController(): Unit = {

        if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){
			farmer.y = farmer.y + 500 * Gdx.graphics.getDeltaTime();
			//if(farmer.y < 0) {farmer.y = 0}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            farmer.x = farmer.x - 500 * Gdx.graphics.getDeltaTime();
			if(farmer.x < 0) {farmer.x = 0}
        }
		if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			farmer.y = farmer.y - 500 * Gdx.graphics.getDeltaTime();
			//if(farmer.y > 400) {farmer.y = 400}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			farmer.x = farmer.x + 500 * Gdx.graphics.getDeltaTime();
			if(farmer.x > 1200) { farmer.x = 1200}
		}
	}

	def render(){
		stateTime = stateTime + Gdx.graphics.getDeltaTime()
		currentFrame = animation.getKeyFrame(stateTime, true)
		spriteBatch.begin()
		spriteBatch.draw(currentFrame, farmer.x, farmer.y)
		spriteBatch.end()
	}
}


