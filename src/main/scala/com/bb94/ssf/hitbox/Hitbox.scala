package com.bb94.ssf.hitbox
import com.bb94.ssf.shapes._

trait Hitbox {
  def hits(that: Hitbox): Boolean = {
    _hits(that) match {
      case Some(b) => b
      case None => that hits this
    }
  }
  protected def _hits(that: Hitbox): Option[Boolean]
}

case class CircleHitbox(r: Double, c: Point) extends Hitbox {
  protected def _hits(that: Hitbox) = {
    that match {
      case ch: CircleHitbox => Some(Shape.distancesq(c, ch.c) < Math.pow(r + ch.r, 2))
      case _ => None
    }
  }
}