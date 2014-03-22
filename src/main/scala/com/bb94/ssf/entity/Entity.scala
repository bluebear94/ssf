package com.bb94.ssf.entity
import com.bb94.ssf.event._
import com.bb94.ssf.hitbox._
import com.bb94.ssf._

trait Entity extends Event with Renderable {
  
  private var _x: Double = _
  private var _y: Double = _
  def x = _x
  def y = _y
  def x_=(newX: Double) = _x = newX
  def y_=(newY: Double) = _y = newY
  def hitbox: Hitbox
  private var _mfr = false
  def markForRemoval() = _mfr = true
  def isMFR = _mfr
  
}