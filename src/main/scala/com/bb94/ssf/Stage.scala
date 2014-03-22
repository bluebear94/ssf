package com.bb94.ssf
import com.bb94.ssf.event._
import com.bb94.ssf.entity.Entity
import com.bb94.ssf.shapes._

/**
 * A stage.
 * 
 * @author bluebear94
 */
abstract class Stage extends Event with Renderable {
  
  protected val _stageName = "Unnamed stage"
  private val stw: Rectangle = Rectangle(Point(0, 0), Point(0.7, 1))
  private var playerBullets: List[Entity] = Nil
  private var enemyBullets: List[Entity] = Nil
  private var player: Entity = _
  private var enemies: List[Entity] = Nil
  private var bosses: List[Entity] = Nil
  
}