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

//Argument determines the type of plant
class Plant(pType:Int, x:Float, y:Float){
    //0 for pumpkin, 1 for berry, 2 for carrot, 3 for potato, 4 for lettuce,
    //5 for wheat, 6 for strawberry, 7 for grapefruit, 8 for flower
    val plantType = pType
    var plantTexLocs = Array(0, 16, 32, 48, 64, 80, 96, 112, 128)
    var plant: Rectangle = new Rectangle(x, y, 0, 0)

	val allPlantsTex: Texture = GameScreen.allPlantsTex
    var plantTex: TextureRegion = new TextureRegion(allPlantsTex, 0, plantTexLocs(plantType), 16, 16)

	val spriteBatch = new SpriteBatch()
	var stateTime = 0f
    var currState = 0 
    var growthRate = 5f

    //Increments the plant growth to the next stage
    def changeStage(): Unit = {
        currState+= 16
        plantTex = new TextureRegion(allPlantsTex, currState, plantTexLocs(plantType), 16, 16)
	}

    def changeType(pType:Float): Unit = {
        //plantType = pType
    }

	def render(): Unit = {
		stateTime = stateTime + Gdx.graphics.getDeltaTime()

        //determines if the plant is ready to change states
        if(stateTime >= growthRate && currState <= 48){
            changeStage()
            stateTime = 0
        }

		spriteBatch.begin()
		spriteBatch.draw(plantTex, plant.x, plant.y, 48, 48)
		spriteBatch.end()
	}
}


