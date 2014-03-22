package com.bb94.ssf

import org.lwjgl.Sys

object Framerate {
  val FRAMERATE = 60
  private var lastFrame: Long = getTime
  def getTime = {
    Sys.getTime * 1000 / Sys.getTimerResolution
  }
  def getDelta = {
    val time = getTime
    val delta = (getTime - lastFrame).toInt
    lastFrame = time
    delta
  }
  private var lastFPS = getTime
  private var _fps = 0
  private var __fps = 0
  def updateFPS() = {
    if (getTime - lastFPS > 1000) {
      __fps = _fps
      _fps = 0
      lastFPS += 1000
    }
    _fps += 1
  }
  def fps = __fps
}