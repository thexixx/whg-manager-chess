package net.whg.manager
package pieces

import pieces.MoveResults.MoveResult

import net.whg.manager.board.Board


trait Piece {

  protected var pos: (Int, Int) = (0, 0)
  protected var board: Board = null

  def doMove(moveFrom: (Int, Int), moveTo: (Int, Int)): MoveResult

  final def getPos(): (Int, Int) = pos

  final def setPos(pos: (Int, Int)): Unit = this.pos = pos

  final def setBoard(board: Board): Unit = this.board = board

  def checkCheck(): Boolean

  def getColor(): Char

  override def toString: String = this.getClass.getSimpleName
}

object Piece {
  val WHITE = 'w'
  val BLACK = 'b'
}