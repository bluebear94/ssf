package com.bb94.ssf.event

/**
 * Trait for event handling.
 */
trait Event {
  
  private var alarms: List[(Int, () => Unit)] = List()
  
  def onCreate()
  protected def _step()
  def onDestroy()
  def alarm(ticks: Int, action: () => Unit) = {
    alarms = (ticks, action) :: alarms
  }
  def step() = {
    alarms = alarms.map((a: (Int, () => Unit)) => (a._1 - 1, a._2))
    alarms.filter(_._1 == 0).foreach(_._2)
    alarms = alarms.filter(_._1 != 0)
    _step()
  }
  
}