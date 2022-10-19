package com.mygdx.game

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.MyGdxGame

object DesktopLauncher {
  def desktoplauncher(arg: Array[String]) = {
    val cfg = new Lwjgl3ApplicationConfiguration()
    
    new Lwjgl3Application(new MyGdxGame(), cfg)
  }
}