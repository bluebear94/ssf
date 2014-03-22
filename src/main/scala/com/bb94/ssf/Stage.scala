package com.bb94.ssf
import com.bb94.ssf.event._
import com.bb94.ssf.entity._
import com.bb94.ssf.shapes._
import scala.collection.mutable._

/**
 * A stage.
 * 
 * @author bluebear94
 */
abstract class Stage extends Event with Renderable {
  
  protected val _stageName = "Unnamed stage"
  private val stw: Rectangle = Rectangle(Point(0, 0), Point(0.7, 1))
  var playerBullets: ListBuffer[Bullet] = new ListBuffer
  var enemyBullets: ListBuffer[Bullet] = new ListBuffer
  var player: Entity = _
  var enemies: ListBuffer[Mob] = new ListBuffer
  var bosses: ListBuffer[Boss] = new ListBuffer
  protected def _step(delta: Int) = {
    playerBullets.foreach(_.step(delta))
    enemyBullets.foreach(_.step(delta))
    bosses.foreach(_.step(delta))
    enemies.foreach(_.step(delta))
    player.step(delta)
    playerBullets = playerBullets.filterNot(_.isMFR)
    enemyBullets = enemyBullets.filterNot(_.isMFR)
    bosses = bosses.filterNot(_.isMFR)
    enemies = enemies.filterNot(_.isMFR)
  }
  def render(bounds: Rectangle) = {
    playerBullets.foreach(_.render(bounds))
    enemyBullets.foreach(_.render(bounds))
    player.render(bounds)
    enemies.foreach(_.render(bounds))
    bosses.foreach(_.render(bounds))
  }
  
}