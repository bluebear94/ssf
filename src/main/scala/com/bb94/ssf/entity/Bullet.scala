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
  override def step(delta: Int) = {
    super.step(delta)
    if (side) {
      var hasHit = false
      def checkHit(m: Entity) = {
        if (!hasHit && (this.hitbox hits m.hitbox)) {
          m.markForRemoval()
          this.markForRemoval()
          hasHit = true
        }
      }
      for (m <- game.getCurrStage.enemies) {
        checkHit(m)
      }
      if (!hasHit) {
        for (m <- game.getCurrStage.bosses) {
          checkHit(m)
        }
      }
    }
    else {
      if (hitbox hits game.getCurrStage.player.hitbox) {
        this.markForRemoval()
        game.die()
      }
    }
  }
  
}