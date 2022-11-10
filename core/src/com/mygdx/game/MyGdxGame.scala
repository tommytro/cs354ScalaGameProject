package com.mygdx.game

import com.mygdx.game.GameScreen
import com.badlogic.gdx.Game


class MyGdxGame extends Game {
  
	var mainGameScreen:GameScreen = null
	var gameUUID:Option[String] = None

	/**
	 * Execute when this class is first instantiate
	 */
	override def create() {
		setScreen(GameScreen(this))
	}

	/**
	 * Execute when this class is close
	 */
	override def dispose() {

	}
}