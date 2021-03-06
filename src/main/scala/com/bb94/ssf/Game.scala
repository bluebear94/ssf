package com.bb94.ssf
import scala.math.BigInt
import com.bb94.ssf.shapes._
import org.lwjgl.opengl._
import org.lwjgl._
import com.bb94.ssf.entity._

/**
 * A class for the whole game.
 * @author bluebear94
 */
abstract class Game(title: String = "Unnamed shooter",
  maxLife: Int = 10,
  startLife: Int = 3,
  maxSpellcard: Int = 10,
  startSpellcard: Int = 3,
  gameWindow: Rectangle = Rectangle(Point(0.0, 0.0), Point(0.7, 1.0)),
  statsWindow: Rectangle = Rectangle(Point(0.7, 0.0), Point(1.0, 1.0)),
  width: Int = 800,
  height: Int = 600,
  mainScrWidth: Double = 0.7) extends Renderable {
  
  Game.width = width
  Game.height = height
  val aspectRatio = gameWindow.height / gameWindow.width
  
  
  
  private var _difficulty: Int = _
  def difficulty = _difficulty
  def difficulty_=(newD: Int) = _difficulty = newD
  protected val difficultyNames: Array[String]
  def difficultyName(diff: Int) = difficultyNames(diff)
  def currDiffName = difficultyNames(_difficulty)

  private var _currArc: Int = _
  def currArc = _currArc
  protected def currArc_=(newS: Int) = _currArc = newS
  protected val arcs: Array[StageArc]
  private var _currStage: Int = _
  def currStage = _currStage

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
  private var _spellcard: Int = startSpellcard
  def spellcard = _spellcard
  
  /**
   * Behavior on death.
   */
  def onDeath() {
    _spellcard += 1
  }
  /**
   * Behavior on losing all lives.
   */
  def onGameOver()

  def play() = {
    import GL11._
    try {
      Display.setDisplayMode(new DisplayMode(width, height))
      Display.create()
    }
    catch {
      case e: LWJGLException => {
        e.printStackTrace()
        exit(-1)
      }
    }
    glMatrixMode(GL_PROJECTION)
    glLoadIdentity();
    glOrtho(0, width, 0, height, 1, -1)
    glMatrixMode(GL_MODELVIEW)
    while (!Display.isCloseRequested) {
      glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
      Display.sync(Framerate.FRAMERATE)
      Framerate.updateFPS()
      val delta = Framerate.getDelta
      render(Shape.WHOLE_SCN)
      Display.update()
    }
    Display.destroy()
  }
  def getCurrStage() = {
    arcs(currArc).stage(currStage)
  }
  def renderStage() = {
    getCurrStage.render(gameWindow)
  }
  def addObj(o: InGame) = {
    o.game = this
    o match {
      case o: Entity => o.addToStage(getCurrStage)
      case _ => ()
    }
  }
  def rmObj(o: InGame) = o.game = null
  def glVertex2dRelative(x: Double, y: Double) = GL11.glVertex2d(x * width, y * height)
}
@Deprecated
object Game {
  var width: Int = 800
  var height: Int = 600
  def glVertex2fRelative(x: Double, y: Double) = GL11.glVertex2d(x * width, y * height)
}

/*class TestGame extends Game {
  def render(bounds: Rectangle) = {
    
  }
  def onDeath() = {
    
  }
  def onGameOver() = {
    
  }
  protected val difficultyNames = Array("Easy", "Normal", "Hard", "Lunatic")
}

object TestGame {
  def main(args: Array[String]) = {
    (new TestGame()).play()
  }
}*/