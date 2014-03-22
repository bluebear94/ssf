package com.bb94.ssf.entity

import com.bb94.ssf.shapes.Rectangle
import com.bb94.ssf.hitbox.Hitbox
import com.bb94.ssf.Card
import com.bb94.ssf.Stage

trait Boss extends Mob {
  
  val name: String
  var currTimeOnSpell: Double
  def card: Card
  def nextCard: Card
  override def addToStage(s: Stage) = s.bosses += this
  
}