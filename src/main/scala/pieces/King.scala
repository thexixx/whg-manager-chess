package net.whg.manager
package pieces

import board.Board

case class King(board: Board, color: Char, var pos: (Int, Int)) extends Piece {
  override def doMove(moveFrom: (Int, Int), moveTo: (Int, Int)): MoveResult = ???

  override def getPos(): (Int, Int) = pos

  override def checkCheck(): Boolean = ???

  override def getColor(): Char = color
}
