package com.bb94.ssf
import com.bb94.ssf.event._
import com.bb94.ssf.entity.Entity
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
  var playerBullets: ListBuffer[Entity] = new ListBuffer
  var enemyBullets: ListBuffer[Entity] = new ListBuffer
  var player: Entity = _
  var enemies: ListBuffer[Entity] = new ListBuffer
  var bosses: ListBuffer[Entity] = new ListBuffer
  
}