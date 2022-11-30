package com.mygdx.game

import com.mygdx.game.GameScreen
import com.mygdx.game.MenuScreen
import com.badlogic.gdx.Game


class MyGdxGame extends Game {
  
	var mainGameScreen:GameScreen = null
	val menuScreen:MenuScreen = null
	var gameUUID:Option[String] = None

	/**
	 * Execute when this class is first instantiate
	 */
	override def create() {
		setScreen(MenuScreen(this))
	}

	/**
	 * Execute when this class is close
	 */
	override def dispose() {

	}
}