package com.bb94.ssf.entity

import com.bb94.ssf.shapes.Rectangle
import com.bb94.ssf.hitbox.Hitbox
import com.bb94.ssf.Card

trait Boss extends Mob {
  
  val name: String
  var currTimeOnSpell: Double
  def card: Card
  def nextCard: Card
  
}