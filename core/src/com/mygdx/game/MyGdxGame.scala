package com.mygdx.game

<<<<<<< Updated upstream
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.{Gdx, Screen}
import com.badlogic.gdx.Input
=======
import com.mygdx.game.MenuScreen
import com.mygdx.game.GameScreen
>>>>>>> Stashed changes
import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.mygdx.game.GameScreen


class MyGdxGame extends Game {
  
<<<<<<< Updated upstream
  	override def create: Unit = {
		setScreen(new GameScreen(this))
=======
	var mainGameScreen:GameScreen = MenuScreen
	var gameUUID:Option[String] = None

	/**
	 * Execute when this class is first instantiate
	 */
	override def create() {
		setScreen(GameScreen(this))
>>>>>>> Stashed changes
	}

}