package com.bb94.ssf.hitbox
import com.bb94.ssf.shapes._
import com.bb94.ssf.shapes.Vector._

trait Hitbox {
  def hits(that: Hitbox): Boolean = {
    _hits(that) match {
      case Some(b) => b
      case None => that hits this
    }
  }
  protected def _hits(that: Hitbox): Option[Boolean]
}

case class CircleHitbox(c: Circle) extends Hitbox {
  protected def _hits(that: Hitbox) = {
    that match {
      case ch: CircleHitbox => Some(Shape.distancesq(c.c, ch.c.c) < Math.pow(c.r + ch.c.r, 2))
      case _ => None
    }
  }
}

case class LineHitbox(l: Line) extends Hitbox {
  protected def _hits(that: Hitbox) = {
    that match {
      case ch: CircleHitbox => {
        val ab = l.br - l.tl
        val ac = ch.c.c - l.tl
        val ad = ac proj ab
        val cd = ac - ad
        Some((cd dot cd) <= (ch.c.r * ch.c.r))
      }
      case lh: LineHitbox => {
        def cross(v: Vector, w: Vector) =
          v.x * w.y - v.y - w.x
        val p = l.tl
        val q = lh.l.tl
        val r = l.br - p
        val s = lh.l.br - q
        val rs = cross(r, s)
        val qmp = q - p
        val qmpr = cross(qmp, r)
        val t = cross(qmp, s) / rs
        val u = qmpr / rs
        if (rs == 0 && qmpr == 0) {
          Some((qmp dot r) >= 0 &&
               (qmp dot r) <= (r dot r) ||
               ((p - q) dot s) >= 0 &&
               ((p - q) dot s) <= (s dot s))
        }
        else {
          Some(rs != 0 && t >= 0 && t <= 1 &&
              u >= 0 && u <= 1)
        }
      }
      case _ => None
    }
  }
}

case class CompoundHitbox(a: Hitbox, b: Hitbox) extends Hitbox {
  protected def _hits(that: Hitbox) = {
    Some((a hits that) || (b hits that))
  }
}