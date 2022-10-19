package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

object DesktopLauncher {
  def main(arg: Array[String]) = {
    val cfg = new Lwjgl3ApplicationConfiguration()
    cfg.title = "puzzleplatform"
    cfg.useGL20 = true
    cfg.width = 800
    cfg.height = 480
    cfg.resizable = false
    
    new Lwjgl3Application(new MyGdxGame(cfg.width, cfg.height), cfg)
  }
}
