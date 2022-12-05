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

import scala.util.Random

class Chicken(x:Float, y:Float){
    
    var moveSpeed : Float = 4
    var farmer: Rectangle = new Rectangle(x, y, 0, 0)
	val farmerTex: Texture = GameScreen.farmerTex
	val idleLeftTex = new Texture("idle_R.png")
	val idleRightTex = new Texture("idle_L.png")
	var idleRightFrames: Array[Array[TextureRegion]] = TextureRegion.split(idleRightTex, idleRightTex.getWidth() / 2, idleRightTex.getHeight() / 1)
	var idleLeftFrames: Array[Array[TextureRegion]] = TextureRegion.split(idleLeftTex, idleLeftTex.getWidth() / 2, idleLeftTex.getHeight() / 1)
	var walkFrames: Array[Array[TextureRegion]] = TextureRegion.split(farmerTex, farmerTex.getWidth() / 8, farmerTex.getHeight() / 3)
	var idleAnimationLeft = new Animation(.162f, idleLeftFrames(0): _*)
	var idleAnimationRight = new Animation(.162f, idleRightFrames(0): _*)
	var moveLeftAnimation = new Animation(.086f, walkFrames(1): _*)
	var moveRightAnimation = new Animation(.086f, walkFrames(2): _*)
	var currAnimation = moveLeftAnimation
	val spriteBatch = new SpriteBatch()
	var currentFrame: TextureRegion = null
	var stateTime = 0f
	var facingRight = false
	var rand: Float = 0

	def setRand(x:Float){
        rand = x
    }

	def render(): Unit = {
		if(rand < 10){

			if(facingRight == false){
			currAnimation = moveLeftAnimation
			}else if(facingRight == true){
			currAnimation = moveRightAnimation
			}

			farmer.y = farmer.y + 175 * Gdx.graphics.getDeltaTime();

		}
		if(rand > 40 && rand < 50){
			facingRight = false
			currAnimation = moveLeftAnimation
            farmer.x = farmer.x - 175 * Gdx.graphics.getDeltaTime();
        }
		if(rand > 60 && rand < 70){

			if(facingRight == false){
			currAnimation = moveLeftAnimation
			}else if(facingRight == true){
			currAnimation = moveRightAnimation
			}

			farmer.y = farmer.y - 175 * Gdx.graphics.getDeltaTime();
		}
		if(rand > 90){
			facingRight = true
			currAnimation = moveRightAnimation
			farmer.x = farmer.x + 175 * Gdx.graphics.getDeltaTime();
		}
		stateTime = stateTime + Gdx.graphics.getDeltaTime()
		currentFrame = currAnimation.getKeyFrame(stateTime, true)
		spriteBatch.begin()
		spriteBatch.draw(currentFrame, farmer.x, farmer.y, 64, 64)
		spriteBatch.end()
	}
}


