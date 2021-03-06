package com.bb94.ssf.shapes

trait Shape
case class Point(x: Double, y: Double) extends Shape
case class Line(tl: Point, br: Point) extends Shape
case class Rectangle(tl: Point, br: Point) extends Shape {
  def width = br.x - tl.x
  def height = br.y - tl.y
}
case class Polygon(ps: List[Point]) extends Shape
case class Circle(c: Point, r: Double) extends Shape
object Shape {
  val WHOLE_SCN = Rectangle(Point(0.0, 0.0), Point(1.0, 1.0))
  def mapPoint(p: Point, r: Rectangle): Point = {
    val rtl = r.tl
    val rbr = r.br
    val w = r.width
    Point(rtl.x + w * p.x,
      rtl.y + w * p.y)
  }
  def unmapPoint(p: Point, r: Rectangle): Point = {
    val rtl = r.tl
    val rbr = r.br
    val w = r.width
    Point((p.x - rtl.x) / w,
      (p.y - rtl.y) / w)
  }
  def applyToShapes(s: Shape, r: Rectangle, f: (Point, Rectangle) => Point): Shape = {
    s match {
      case p: Point => f(p, r)
      case l: Line => Line(f(l.tl, r), f(l.br, r))
      case r: Rectangle => Rectangle(f(r.tl, r), f(r.br, r))
      case p: Polygon => Polygon(p.ps.map(f(_, r)))
      case c: Circle => Circle(f(c.c, r), c.r * r.width)
    }
  }
  def distancesq(a: Point, b: Point) = {
    Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2)
  }
}
case class Vector(x: Double, y: Double) {
  def +(that: Vector) = Vector(x + that.x, y + that.y)
  def -(that: Vector) = Vector(x - that.x, y - that.y)
  def *(that: Double) = Vector(that * x, that * y)
  def dot(that: Vector) = x * that.x + y * that.y
  def proj(that: Vector) = {
    that * ((this dot that) / (that dot that))
  }
  def abs = Math.sqrt(this dot this)
}
object Vector {
  implicit def p2v(p: Point) = Vector(p.x, p.y)
}