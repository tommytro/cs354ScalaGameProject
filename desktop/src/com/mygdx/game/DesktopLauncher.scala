package com.mygdx.game

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.MyGdxGame

object DesktopLauncher extends App {
    val cfg = new Lwjgl3ApplicationConfiguration()
    cfg.setTitle("farme game")
    cfg.setWindowedMode(1280,720)
    cfg.useVsync(true)
    cfg.setForegroundFPS(60)
    new Lwjgl3Application(new MyGdxGame(), cfg)
}