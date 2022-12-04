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
import com.mygdx.game.Plant
import com.mygdx.game.GameScreen


class Inventory {
  //Basic instance variables
  var batch = new SpriteBatch()
  var character = new Character() 
  var selectedItem = null


  //Textures
  val inventoryTexture = new Texture("Inventory/inventory.png")
  val hoeTexture = new Texture("Inventory/pxlHoe.png")
  val waterCanTexture = new Texture("Inventory/pxlCan.png")
  val seedTexture = new Texture("Inventory/pxlCan.png")
  val seed1 = new Texture("Inventory/seed1.png")
  val seed2 = new Texture("Inventory/seed2.png")
  val seed3 = new Texture("Inventory/seed3.png")
  val seed4 = new Texture("Inventory/seed4.png")
  val seed5 = new Texture("Inventory/seed5.png")
  val seed6 = new Texture("Inventory/seed6.png")
  val seed7 = new Texture("Inventory/seed7.png")
  val seed8 = new Texture("Inventory/seed8.png")
  val seed9 = new Texture("Inventory/seed9.png")


  //Hashmap
  var hashMap =  HashMap(0->inventoryTexture)

  //Boolean values
  //var hoeTextureEquipped:Boolean = true;
  //var waterCanTextureEquipped:Boolean = true;
  //var seedTextureEquipped:Boolean = true;
  var showInventory:Boolean = true;


def characterPosition(): Unit ={

  //character.movementController()

  hashMap = hashMap + (1 -> hoeTexture)
  hashMap = hashMap + (2 -> waterCanTexture)
  hashMap = hashMap + (3 -> seed1)
  hashMap = hashMap + (4 -> seed2)
  hashMap = hashMap + (5 -> seed3)
  hashMap = hashMap + (6 -> seed4)
  hashMap = hashMap + (7 -> seed5)
  hashMap = hashMap + (8 -> seed6)
  hashMap = hashMap + (9 -> seed7)

  if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
    if(!showInventory){
      showInventory=true
    } else
      {
        showInventory=true
      }
  }

  if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)){
    //hashMap.remove(1)
  }
  if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
    //hashMap.remove(2)
  }
  if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
    //hashMap.remove(3)
  }
    if(Gdx.input.isKeyPressed(Input.Keys.NUM_4)){
    //hashMap.remove(1)
  }
  if (Gdx.input.isKeyPressed(Input.Keys.NUM_5)) {
    //hashMap.remove(2)
  }
  if (Gdx.input.isKeyPressed(Input.Keys.NUM_6)) {
    //hashMap.remove(3)
  }
  if (Gdx.input.isKeyPressed(Input.Keys.NUM_7)) {
    //hashMap.remove(2)
  }
  if (Gdx.input.isKeyPressed(Input.Keys.NUM_8)) {
    //hashMap.remove(3)
  }
  if (Gdx.input.isKeyPressed(Input.Keys.NUM_9)) {
    //hashMap.remove(3)
  }

}


  def render(): Unit = {
    batch.begin()

    // if(!hoeTextureEquipped)
    // {
    //   batch.draw(hoeTexture, 200, 100)
    // }

    // if(!seedTextureEquipped)
    // {
    //   batch.draw(seedTexture,500,200)
    // }

    // if(!waterCanTextureEquipped)
    // {
    //   batch.draw(waterCanTexture,350,350)
    // }

    if(showInventory){
      hashMap.foreach {
        case (key, value) => batch.draw(value, (key * 36) - 36, 0)
      }
    }

    batch.end()

  }
}
