package com.bb94.ssf
import scala.math.BigInt
import com.bb94.ssf.shapes._

/**
 * A class for the whole game.
 * @author bluebear94
 */
abstract class Game(title: String = "Unnamed shooter",
                    maxLife: Int = 10,
                    startLife: Int = 3,
                    gameWindow: Rectangle,
                    statsWindow: Rectangle) extends Renderable {
  
  private var _difficulty: Int = _
  def difficulty = _difficulty
  def difficulty_=(newD: Int) = _difficulty = newD
  protected val difficultyNames: Array[String]
  def difficultyName(diff: Int) = difficultyNames(diff)
  def currDiffName = difficultyNames(_difficulty)
  
  private var _currArc: Int = _
  def currArc = _currArc
  protected def currArc_=(newS: Int) = _currArc = newS
  
  private var _score: BigInt = 0
  def score = _score
  def score_=(newS: BigInt) = _score = newS
  def addScore(s: BigInt) = _score += s
  
  private var _continues: Int = 0
  def continues = _continues
  def continue() = _continues += 1
  
  private var _life: Int = startLife
  def life = _life
  def extend() = _life = Math.max(_life + 1, maxLife)
  def die() = {
    _life -= 1
    onDeath()
    if (_life == 0) onGameOver()
  }
  /**
   * Behavior on death.
   */
  def onDeath()
  /**
   * Behavior on losing all lives.
   */
  def onGameOver()
  
}