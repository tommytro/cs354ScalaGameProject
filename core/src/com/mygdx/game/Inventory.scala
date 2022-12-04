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
  var items = Array("hoe", "can", "seed1", "seed2", "seed3", "seed4", "seed5", "seed6", "seed7")
  var selectedItem = new String("hoe")

  //Textures
  val inventoryTexture = new Texture("Inventory/inv1.png")
  val inv1 = new Texture("Inventory/inv1.png")
  val inv2 = new Texture("Inventory/inv2.png")
  val inv3 = new Texture("Inventory/inv3.png")
  val inv4 = new Texture("Inventory/inv4.png")
  val inv5 = new Texture("Inventory/inv5.png")
  val inv6 = new Texture("Inventory/inv6.png")
  val inv7 = new Texture("Inventory/inv7.png")
  val inv8 = new Texture("Inventory/inv8.png")
  val inv9 = new Texture("Inventory/inv9.png")
  val hoeTexture = new Texture("Inventory/pxlHoe.png")
  val waterCanTexture = new Texture("Inventory/pxlCan.png")
  val seedTexture = new Texture("Inventory/pxlCan.png")
  val seed0 = new Texture("Inventory/seed1.png") //shifted to match plant.scala
  val seed1 = new Texture("Inventory/seed2.png")
  val seed2 = new Texture("Inventory/seed3.png")
  val seed3 = new Texture("Inventory/seed4.png")
  val seed4 = new Texture("Inventory/seed5.png")
  val seed5 = new Texture("Inventory/seed6.png")
  val seed6 = new Texture("Inventory/seed7.png")
  val seed7 = new Texture("Inventory/seed8.png")
  val seed8 = new Texture("Inventory/seed9.png")
  

  //Hashmap
  var hashMap =  HashMap(0->inventoryTexture)

  //Boolean values
  //var hoeTextureEquipped:Boolean = true;
  //var waterCanTextureEquipped:Boolean = true;
  //var seedTextureEquipped:Boolean = true;
  var showInventory:Boolean = true; //always on


def inventoryPosition(): Unit ={

  //character.movementController()

  hashMap = hashMap + (1 -> hoeTexture)
  hashMap = hashMap + (2 -> waterCanTexture)
  hashMap = hashMap + (3 -> seed0)
  hashMap = hashMap + (4 -> seed1)
  hashMap = hashMap + (5 -> seed2)
  hashMap = hashMap + (6 -> seed3)
  hashMap = hashMap + (7 -> seed4)
  hashMap = hashMap + (8 -> seed5)
  hashMap = hashMap + (9 -> seed6)
  //hashMap = hashMap + (10 -> seed8)

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
    selectedItem = items(0)
    hashMap = hashMap + (0 -> inv1)
  }
  if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
    //hashMap.remove(2)
    selectedItem = items(1)
    hashMap = hashMap + (0 -> inv2)
  }
  if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
    //hashMap.remove(3)
    selectedItem = items(2)
    hashMap = hashMap + (0 -> inv3)
  }
    if(Gdx.input.isKeyPressed(Input.Keys.NUM_4)){
    //hashMap.remove(1)
    selectedItem = items(3)
    hashMap = hashMap + (0 -> inv4)
  }
  if (Gdx.input.isKeyPressed(Input.Keys.NUM_5)) {
    //hashMap.remove(2)
    selectedItem = items(4)
    hashMap = hashMap + (0 -> inv5)
  }
  if (Gdx.input.isKeyPressed(Input.Keys.NUM_6)) {
    //hashMap.remove(3)
    selectedItem = items(5)
    hashMap = hashMap + (0 -> inv6)
  }
  if (Gdx.input.isKeyPressed(Input.Keys.NUM_7)) {
    //hashMap.remove(2)
    selectedItem = items(6)
    hashMap = hashMap + (0 -> inv7)
  }
  if (Gdx.input.isKeyPressed(Input.Keys.NUM_8)) {
    //hashMap.remove(3)
    selectedItem = items(7)
    hashMap = hashMap + (0 -> inv8)
  }
  if (Gdx.input.isKeyPressed(Input.Keys.NUM_9)) {
    //hashMap.remove(3)
    selectedItem = items(8)
    hashMap = hashMap + (0 -> inv9)
  }
  // if (Gdx.input.isKeyPressed(Input.Keys.NUM_0)) {
  //   //hashMap.remove(3)
  //   selectedItem = null
  // }

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

    hashMap.foreach {
      case (key, value) => batch.draw(value, (key * 36) - 36, 0)
    }

    batch.end()

  }
}
