package com.bb94.ssf.shapes

trait Shape
case class Point(x: Double, y: Double) extends Shape
case class Line(tl: Point, br: Point) extends Shape
case class Rectangle(tl: Point, br: Point) extends Shape
case class Polygon(ps: List[Point]) extends Shape
case class Circle(c: Point, r: Double) extends Shape
object Shape {
  val WHOLE_SCN = Rectangle(Point(0.0, 0.0), Point(1.0, 1.0))
  def mapPoint(p: Point, r: Rectangle): Point = {
    val rtl = r.tl
    val rbr = r.br
    Point(rtl.x + (rbr.x - rtl.x) * p.x,
          rtl.y + (rbr.y - rtl.y) * p.y)
  }
  def applyToShapes(s: Shape, r: Rectangle, f: (Point, Rectangle) => Point): Shape = {
    s match {
      case p: Point => f(p, r)
      case l: Line => Line(f(l.tl, r), f(l.br, r))
      case r: Rectangle => Rectangle(f(r.tl, r), f(r.br, r))
      case p: Polygon => Polygon(p.ps.map(f(_, r)))
      case c: Circle => Circle(f(c.c, r), c.r)
    }
  }
}