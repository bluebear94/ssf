package com.bb94.ssf.entity
import com.bb94.ssf.event._

trait Entity extends Event {
  private var _x: Double = _
  private var _y: Double = _
  def x = _x
  def y = _y
  def x_=(newX: Double) = _x = newX
  def y_=(newY: Double) = _y = newY
}