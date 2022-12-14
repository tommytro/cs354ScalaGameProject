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


class Soil(){

    var soil: Rectangle = new Rectangle(-100 + 1280/2, 720/2, 0, 0)

	val soilTexMap: Texture = GameScreen.soilTex
    var soilTex: TextureRegion = new TextureRegion(soilTexMap, 100, 350, 16, 16) //176 -> 350 for transparent soil (sorry)

	val spriteBatch = new SpriteBatch()
	var stateTime = 0f
    var currState = 0 
    var unwateredRate = 5f
    var plant: Plant = _
    
    def setPlant(seed:Plant){
        plant = seed
    }

    def becomeTilled(): Unit = {
        currState+= 16
        soilTex = new TextureRegion(soilTexMap, 0, 0, 16, 16)
	}

    def becomeWatered(): Unit = {
        currState+= 16
        soilTex = new TextureRegion(soilTexMap, 0, 0, 16, 16)
	}

    def setPos(x:Float, y:Float): Unit = {
        soil.x = x
        soil.y = y
    }

	def render(): Unit = {
		stateTime = stateTime + Gdx.graphics.getDeltaTime()

		spriteBatch.begin()
        
		spriteBatch.draw(soilTex, soil.x, soil.y, 48, 48)
        //plant.render()
		spriteBatch.end()
	}
}


