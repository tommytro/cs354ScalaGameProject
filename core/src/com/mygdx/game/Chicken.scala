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
    var chicken: Rectangle = new Rectangle(x, y, 0, 0)
	val chickenTex: Texture = GameScreen.chickTex
	val idleLeftTex = new Texture("chick_R.png")
	val idleRightTex = new Texture("chick_L.png")
	var idleRightFrames: Array[Array[TextureRegion]] = TextureRegion.split(idleRightTex, idleRightTex.getWidth() / 2, idleRightTex.getHeight() / 1)
	var idleLeftFrames: Array[Array[TextureRegion]] = TextureRegion.split(idleLeftTex, idleLeftTex.getWidth() / 2, idleLeftTex.getHeight() / 1)
	var walkFrames: Array[Array[TextureRegion]] = TextureRegion.split(chickenTex, chickenTex.getWidth() / 4, chickenTex.getHeight() / 2)
	var idleAnimationLeft = new Animation(.162f, idleLeftFrames(0): _*)
	var idleAnimationRight = new Animation(.162f, idleRightFrames(0): _*)
	var moveLeftAnimation = new Animation(.162f, walkFrames(0): _*)
	var moveRightAnimation = new Animation(.162f, walkFrames(1): _*)
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
		if(rand < 500){
			if(facingRight == false){
			currAnimation = moveLeftAnimation
			}else if(facingRight == true){
			currAnimation = moveRightAnimation
			}
			chicken.y = chicken.y + 175 * Gdx.graphics.getDeltaTime()
		}
		else if(rand > 4500 && rand < 5000){
			facingRight = false
			currAnimation = moveLeftAnimation
            chicken.x = chicken.x - 175 * Gdx.graphics.getDeltaTime();
        }
		else if(rand > 6500 && rand < 7000){

			if(facingRight == false){
			currAnimation = moveLeftAnimation
			}else if(facingRight == true){
			currAnimation = moveRightAnimation
			}

			chicken.y = chicken.y - 175 * Gdx.graphics.getDeltaTime();
		}
		else if(rand > 9500){
			facingRight = true
			currAnimation = moveRightAnimation
			chicken.x = chicken.x + 175 * Gdx.graphics.getDeltaTime();
		}
		else{
			if(facingRight == false){
				currAnimation = idleAnimationLeft
			}else if(facingRight == true){
				currAnimation = idleAnimationRight
			}
		}
		stateTime = stateTime + Gdx.graphics.getDeltaTime()
		currentFrame = currAnimation.getKeyFrame(stateTime, true)
		spriteBatch.begin()
		spriteBatch.draw(currentFrame, chicken.x, chicken.y, 32, 32)
		spriteBatch.end()
	}
}


