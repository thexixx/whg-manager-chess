package net.whg.manager
package pieces

import pieces.MoveResults.MoveResult


trait Piece {
  def doMove(moveFrom: (Int, Int), moveTo: (Int, Int)): MoveResult
  def getPos(): (Int, Int)
  def setPos(pos: (Int, Int)): Unit
  def checkCheck(): Boolean
  def getColor(): Char

  override def toString: String = this.getClass.getSimpleName
}
