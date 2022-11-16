package com.mygdx.game
import com.badlogic.gdx.{Gdx, Screen, Input, Game}
import com.badlogic.gdx.graphics.g2d.{BitmapFont, Sprite, SpriteBatch, TextureRegion, Animation}
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.ApplicationAdapter



class MenuScreen(game: MyGdxGame) extends ApplicationAdapter with Screen {
    val background = new Texture("dirt.png");
    val playBtn = new Texture("PlayBtn.png");

    override def render(SpriteBatch: sb){
        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight())
        sb.draw(playBtn, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2)
    }
}