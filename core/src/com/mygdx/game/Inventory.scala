package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.{Color, Texture}
import com.badlogic.gdx.graphics.g2d.{Animation, BitmapFont, SpriteBatch, TextureRegion}
import com.badlogic.gdx.utils.{Align, ScreenUtils}
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.mygdx.game.MyGdxGame

import scala.collection.mutable.HashMap


class Inventory {
  //Basic instance variables
  var batch = new SpriteBatch()
  var character = new Character()


  //Textures
  val hoeTexture = new Texture("Inventory/hoe.png")
  val inventoryTexture = new Texture("Inventory/inventory.png")
  val waterCanTexture = new Texture("Inventory/wateringcan.png")
  val seedTexture = new Texture("Inventory/seeds.png")




  //Hashmap
  var hashMap =  HashMap(0->inventoryTexture)


  //Boolean values
  var hoeTextureEquipped:Boolean = false;
  var waterCanTextureEquipped:Boolean = false;
  var seedTextureEquipped:Boolean = false;
  var showInventory:Boolean = false;


def characterPosition(): Unit ={

  character.movementController()

  //println(character.farmer.x)
  //println(character.farmer.y)

  if (character.farmer.x > 380 && character.farmer.x < 425 && character.farmer.y > 405 && character.farmer.y < 425 && !hoeTextureEquipped) {
    hashMap = hashMap + (1 -> hoeTexture)
    hoeTextureEquipped=true
  }

  if(character.farmer.x > 330 && character.farmer.x < 370 && character.farmer.y > 330 && character.farmer.y < 370 && !waterCanTextureEquipped){
    hashMap = hashMap + (2 -> waterCanTexture)
    waterCanTextureEquipped=true
  }

  if(character.farmer.x > 480 && character.farmer.x < 525 && character.farmer.y > 180 && character.farmer.y < 240 && !seedTextureEquipped){
    hashMap = hashMap + (3 -> seedTexture)
    seedTextureEquipped=true
  }

  if (Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_0)) {
    if(!showInventory){
      showInventory=true
    } else
      {
        showInventory=false
      }
  }

  if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_1)){
    hashMap.remove(1)
    hoeTextureEquipped=false
  }

  if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)) {
    hashMap.remove(2)
    waterCanTextureEquipped = false
  }

  if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_3)) {
    hashMap.remove(3)
    seedTextureEquipped = false
  }



}


  def render(): Unit = {
    batch.begin()

    if(!hoeTextureEquipped)
    {
      batch.draw(hoeTexture, 400, 400)
    }

    if(!seedTextureEquipped)
    {
      batch.draw(seedTexture,500,200)
    }

    if(!waterCanTextureEquipped)
    {
      batch.draw(waterCanTexture,350,350)
    }

    if(showInventory){
      hashMap.foreach {
        case (key, value) => batch.draw(value, (key * 36) - 36, 0)
      }
    }

    batch.end()

  }
}
