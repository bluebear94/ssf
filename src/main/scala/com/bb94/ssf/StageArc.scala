package com.bb94.ssf

/**
 * A defined set of stages to complete consecutively.
 * 
 * (e. g. for most of the Touhou games: stages 1 - 6 make up one arc; extra makes up another)
 * @author bluebear94
 */
abstract class StageArc {
  
  protected val stages: Array[Stage] = Array()
  /**
   * Gets the i'th stage.
   */
  def stage(i: Int) = stages(i)
  
}