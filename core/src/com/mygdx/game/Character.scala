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

class Character{
    
    var moveSpeed : Float = 4
    var attackSpeed: Float = 4
    var useSpeed: Float = 4
    var farmer: Rectangle = new Rectangle(1280/2, 720/3, 0, 0)
	val farmerTex: Texture = GameScreen.farmerTex
	val idleRightTex = new Texture("idle_R (1).png")
	val idleLeftTex = new Texture("idle_L (1).png")
	var idleRightFrames: Array[Array[TextureRegion]] = TextureRegion.split(idleRightTex, idleRightTex.getWidth() / 2, idleRightTex.getHeight() / 1)
	var idleLeftFrames: Array[Array[TextureRegion]] = TextureRegion.split(idleLeftTex, idleLeftTex.getWidth() / 2, idleLeftTex.getHeight() / 1)
	var walkFrames: Array[Array[TextureRegion]] = TextureRegion.split(farmerTex, farmerTex.getWidth() / 8, farmerTex.getHeight() / 3)
	var idleAnimationLeft = new Animation(.086f, idleLeftFrames(0): _*)
	var idleAnimationRight = new Animation(.086f, idleRightFrames(0): _*)
	var moveLeftAnimation = new Animation(.086f, walkFrames(1): _*)
	var moveRightAnimation = new Animation(.086f, walkFrames(2): _*)
	var currAnimation = moveLeftAnimation
	val spriteBatch = new SpriteBatch()
	var currentFrame: TextureRegion = null
	var stateTime = 0f
	var facingRight = false

    def movementController(): Unit = {

		if(facingRight == false){
			currAnimation = idleAnimationLeft
			}else if(facingRight == true){
			currAnimation = idleAnimationRight
			}

        if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){

			if(facingRight == false){
			currAnimation = moveLeftAnimation
			}else if(facingRight == true){
			currAnimation = moveRightAnimation
			}

			farmer.y = farmer.y + 175 * Gdx.graphics.getDeltaTime();

		}
		if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			facingRight = false
            farmer.x = farmer.x - 175 * Gdx.graphics.getDeltaTime();
        }
		if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){

			if(facingRight == false){
			currAnimation = moveLeftAnimation
			}else if(facingRight == true){
			currAnimation = moveRightAnimation
			}

			farmer.y = farmer.y - 175 * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			facingRight = true
			farmer.x = farmer.x + 175 * Gdx.graphics.getDeltaTime();
		}
	}

	def render(): Unit = {
		stateTime = stateTime + Gdx.graphics.getDeltaTime()
		currentFrame = currAnimation.getKeyFrame(stateTime, true)
		spriteBatch.begin()
		spriteBatch.draw(currentFrame, farmer.x, farmer.y, 64, 64)
		spriteBatch.end()
	}
}


