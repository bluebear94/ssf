package com.bb94.ssf.event

/**
 * Trait for event handling.
 */
trait Event {
  
  private var alarms: List[(Int, (Event) => Unit)] = List()
  
  def onCreate()
  protected def _step(delta: Int)
  def onDestroy()
  def alarm(ticks: Int, action: (Event) => Unit) = {
    alarms = (ticks, action) :: alarms
  }
  def step(delta: Int) = {
    alarms = alarms.map((a: (Int, (Event) => Unit)) => (a._1 - delta, a._2))
    alarms.filter(_._1 == 0).foreach(_._2(this))
    alarms = alarms.filter(_._1 != 0)
    _step(delta)
  }
  
}