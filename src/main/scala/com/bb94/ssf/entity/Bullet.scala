package com.bb94.ssf.entity

import com.bb94.ssf.hitbox.Hitbox
import com.bb94.ssf.Stage

trait Bullet extends Entity {
  
  def damage: Int
  def side: Boolean
  def addToStage(s: Stage) = {
    if (side) s.playerBullets += this
    else s.enemyBullets += this
  }
  
}