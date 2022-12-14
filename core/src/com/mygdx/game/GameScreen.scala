package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.{Gdx, Screen, Input, Game}
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera, Texture}
import com.badlogic.gdx.graphics.g2d.{BitmapFont, Sprite, SpriteBatch, TextureRegion, Animation}
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.math.Rectangle

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer
import scala.util.Random

import com.mygdx.game.MyGdxGame
import com.mygdx.game.MapManager
import com.mygdx.game.Character
import com.mygdx.game.Chicken
import com.mygdx.game.Plant
import com.mygdx.game.Inventory


class GameScreen(game: MyGdxGame) extends ApplicationAdapter with Screen {
	
	var animationFrames: Array[TextureRegion] = Array()
  	var character: Character = new Character();
	var chicken1: Chicken = new Chicken(1280/2,720/3);
	var chicken2: Chicken = new Chicken(1280/3,720/3);
	
	var soilGroup: Array[Soil] = Array() 
	var soilCount: Int = 1
	var currSoilNum: Int = 0

	var mapRenderer = new OrthogonalTiledMapRenderer(GameScreen.mapMgr.getCurrentMap(), MapManager.UNIT_SCALE)
	val camera = new OrthographicCamera()
	val spriteBatch = new SpriteBatch()
	var inventory: Inventory = new Inventory()
	val rand = new Random()
	var randInt = 0
	var top_left = Array(14,24,0)
	var top_right = Array(42,24,0)
	var bot_left = Array(14,8,0)
	var bot_right = Array(42,8,0)
	var next_pos = top_right //default to top_right
	var curr_pos = next_pos 

	override def show(): Unit = {

		GameScreen.VIEWPORT.setupViewport(15, 15, Gdx.graphics.getWidth(), Gdx.graphics.getHeight())

		camera.setToOrtho(false, GameScreen.VIEWPORT.viewportWidth, GameScreen.VIEWPORT.viewportHeight)
		mapRenderer.setView(camera)

		Gdx.app.debug(GameScreen.TAG, s"UnitScale Value is: ${mapRenderer.getUnitScale()}")

	}

	//render assets
	override def render(delta:Float): Unit = {

		Gdx.gl.glClearColor(0, 0, 0, 1)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

		if(character.farmer.x >= 1280 && curr_pos == top_left){
		next_pos = top_right //change to top_right cam
		curr_pos = next_pos //update curr_pos
		character.farmer.x = 0
		}
		else if(character.farmer.x <= 0 && curr_pos == top_right){
		next_pos = top_left //change to top_left cam
		curr_pos = next_pos //update curr_pos
		character.farmer.x = 1280
		}
		else if(character.farmer.x >= 1280 && curr_pos == bot_left){
		next_pos = bot_right //change to bot_right cam
		curr_pos = next_pos //update curr_pos
		character.farmer.x = 0
		}
		else if(character.farmer.x <= 0 && curr_pos == bot_right){
		next_pos = bot_left //change to bot_left cam
		curr_pos = next_pos //update curr_pos
		character.farmer.x = 1280
		}
		else if(character.farmer.y <= 0 && curr_pos == top_left){
		next_pos = bot_left //change to bot_left cam
		curr_pos = next_pos //update curr_pos
		character.farmer.y = 720
		}
		else if(character.farmer.y <= 0 && curr_pos == top_right){
		next_pos = bot_right //change to bot_right cam
		curr_pos = next_pos //update curr_pos
		character.farmer.y = 720
		}
		else if(character.farmer.y >= 720 && curr_pos == bot_left){
		next_pos = top_left //change to top_left cam
		curr_pos = next_pos //update curr_pos
		character.farmer.y = 0
		}
		else if(character.farmer.y >= 720 && curr_pos == bot_right){
		next_pos = top_right //change to top_right cam
		curr_pos = next_pos //update curr_pos
		character.farmer.y = 0
		}

		randInt = rand.nextInt(10000)
		chicken1.setRand(randInt)
		randInt = rand.nextInt(10000)
		chicken2.setRand(randInt)

		camera.position.set(next_pos(0),next_pos(1),next_pos(2)) // update camera to next_pos
		camera.update 

		mapRenderer.setView(camera)
		mapRenderer.render()
		mapRenderer.getBatch().begin()

		if(curr_pos == bot_left){ //only render plants when on garden screen
			if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){ //if space pressed
				if(inventory.selectedItem == inventory.items(0)){ //hoe
							//find a way to place at nearest square
							if(character.facingRight){
								var soil = new Soil()
								var soilPlant: Plant = new Plant(9, character.farmer.x + 56, character.farmer.y)
								soil.setPlant(soilPlant)
								soilGroup :+= soil
								currSoilNum += 1
							}
							else{
								var soil = new Soil()
								var soilPlant: Plant = new Plant(9, character.farmer.x - 32, character.farmer.y)
								soil.setPlant(soilPlant)
								soilGroup :+= soil
								currSoilNum += 1
							}
							
				}
				else if(character.farmer.y >= 220 && character.farmer.y <= 650){ //check inside dirt patch y
					if(character.farmer.x >= 240 && character.farmer.x <= 960){ //check inside dirt patch x
						if(inventory.selectedItem == inventory.items(2)){ //seed1n
							if(character.facingRight){
								var soil = new Soil()
								var soilPlant: Plant = new Plant(0, character.farmer.x + 56, character.farmer.y)
								soil.setPlant(soilPlant)
								soilGroup :+= soil
								currSoilNum += 1
							}
							else{
								var soil = new Soil()
								var soilPlant: Plant = new Plant(0, character.farmer.x - 32, character.farmer.y)
								soil.setPlant(soilPlant)
								soilGroup :+= soil
								currSoilNum += 1
							}
						}
						else if(inventory.selectedItem == inventory.items(3)){ //seed2
							if(character.facingRight){
								var soil = new Soil()
								var soilPlant: Plant = new Plant(1, character.farmer.x + 56, character.farmer.y)
								soil.setPlant(soilPlant)
								soilGroup :+= soil
								currSoilNum += 1
							}
							else{
								var soil = new Soil()
								var soilPlant: Plant = new Plant(1, character.farmer.x - 32, character.farmer.y)
								soil.setPlant(soilPlant)
								soilGroup :+= soil
								currSoilNum += 1
							}
						}
						else if(inventory.selectedItem == inventory.items(4)){ //seed3
							if(character.facingRight){
								var soil = new Soil()
								var soilPlant: Plant = new Plant(2, character.farmer.x + 56, character.farmer.y)
								soil.setPlant(soilPlant)
								soilGroup :+= soil
								currSoilNum += 1
							}
							else{
								var soil = new Soil()
								var soilPlant: Plant = new Plant(2, character.farmer.x - 32, character.farmer.y)
								soil.setPlant(soilPlant)
								soilGroup :+= soil
								currSoilNum += 1
							}
						}
						else if(inventory.selectedItem == inventory.items(5)){ //seed4
							if(character.facingRight){
								var soil = new Soil()
								var soilPlant: Plant = new Plant(3, character.farmer.x + 56, character.farmer.y)
								soil.setPlant(soilPlant)
								soilGroup :+= soil
								currSoilNum += 1
							}
							else{
								var soil = new Soil()
								var soilPlant: Plant = new Plant(3, character.farmer.x - 32, character.farmer.y)
								soil.setPlant(soilPlant)
								soilGroup :+= soil
								currSoilNum += 1
							}
						}
						else if(inventory.selectedItem == inventory.items(6)){ //seed5
							if(character.facingRight){
								var soil = new Soil()
								var soilPlant: Plant = new Plant(4, character.farmer.x + 56, character.farmer.y)
								soil.setPlant(soilPlant)
								soilGroup :+= soil
								currSoilNum += 1
							}
							else{
								var soil = new Soil()
								var soilPlant: Plant = new Plant(4, character.farmer.x - 32, character.farmer.y)
								soil.setPlant(soilPlant)
								soilGroup :+= soil
								currSoilNum += 1
							}
						}
						else if(inventory.selectedItem == inventory.items(7)){ //seed6
							if(character.facingRight){
								var soil = new Soil()
								var soilPlant: Plant = new Plant(5, character.farmer.x + 56, character.farmer.y)
								soil.setPlant(soilPlant)
								soilGroup :+= soil
								currSoilNum += 1
							}
							else{
								var soil = new Soil()
								var soilPlant: Plant = new Plant(5, character.farmer.x - 32, character.farmer.y)
								soil.setPlant(soilPlant)
								soilGroup :+= soil
								currSoilNum += 1
							}
						}
						else if(inventory.selectedItem == inventory.items(8)){ //seed7
							if(character.facingRight){
								var soil = new Soil()
								var soilPlant: Plant = new Plant(6, character.farmer.x + 56, character.farmer.y)
								soil.setPlant(soilPlant)
								soilGroup :+= soil
								currSoilNum += 1
							}
							else{
								var soil = new Soil()
								var soilPlant: Plant = new Plant(6, character.farmer.x - 32, character.farmer.y)
								soil.setPlant(soilPlant)
								soilGroup :+= soil
								currSoilNum += 1
							}
						}
						else{
							//can currently does nothing
						}
					}
				}
			}
			
			for(currSoil <- soilGroup) {
				currSoil.render()
				currSoil.plant.render()
			}
		}

		if(curr_pos == top_left){ //only render animals on top left
			chicken1.render()
			chicken2.render()
		}

		character.movementController() //Calls to character movement every frame to enable user input
		character.render()

		inventory.inventoryPosition()
		inventory.render()

		mapRenderer.getBatch().end()

		spriteBatch.begin()
		if(Gdx.input.isKeyPressed(Input.Keys.H)){ //show controls
			spriteBatch.draw(GameScreen.controlScreen, 0, 0)
		}
			spriteBatch.draw(GameScreen.helpKey, 1088, 0)
		spriteBatch.end()
	}

    override def hide(): Unit = {

    }

	//cleans up used assets
	override def dispose(): Unit = {

	}
}


object GameScreen{

	def apply(game:MyGdxGame):GameScreen = new GameScreen(game)

	private val TAG:String = GameScreen.getClass.getSimpleName

	var mapMgr:MapManager = MapManager()

	val farmerTex = new Texture("walk and idle.png")
	val chickTex = new Texture("chicken.png")
	val allPlantsTex = new Texture("plants.png")
	val controlScreen = new Texture("controls.png")
	val helpKey = new Texture("H_key.png")
	val soilTex = new Texture("TinyWonderFarm/tilemaps/summer farm tilemap.png")

	private object VIEWPORT{
		var viewportWidth:Float = 0
		var viewportHeight:Float = 0
		var virtualWidth:Float = 0
		var virtualHeight:Float = 0
		var physicalWidth:Float = 0
		var physicalHeight:Float = 0
		var aspectRatio:Float = 0

		/**
		 * Setup Viewport
		 * @param  width:Int     Expected width
		 * @param  height:Int    Expected height
		 * @param  phyWidth:Int  Physical width
		 * @param  phyHeight:Int Physical height
		 */
		def setupViewport(width:Int, height:Int, phyWidth:Int, phyHeight:Int): Unit = {
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
			Gdx.app.debug(GameScreen.TAG, s"WorldRenderer: virtual: (${virtualWidth},${virtualHeight})" );
			Gdx.app.debug(GameScreen.TAG, s"WorldRenderer: viewport: (${viewportWidth},${viewportHeight})" );
			Gdx.app.debug(GameScreen.TAG, s"WorldRenderer: physical: (${physicalWidth},${physicalHeight})" );
		}
	}
}