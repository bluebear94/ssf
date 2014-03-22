package com.bb94.ssf

import com.bb94.ssf.event.Event

trait Card extends Event {
  
  val isSpell: Boolean
  val name: String
  val time: Double
  val isTimeLimit: Boolean
  
}