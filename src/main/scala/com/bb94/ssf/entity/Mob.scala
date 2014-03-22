package com.bb94.ssf.entity

import com.bb94.ssf.shapes.Rectangle
import com.bb94.ssf.hitbox.Hitbox

trait Mob extends Entity {
  
  private var _health: Int = _
  def health = _health
  def health_=(newH: Int) = _health = newH
  def maxH: Int
  
}