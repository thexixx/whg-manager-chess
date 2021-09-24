package net.whg.manager
package pieces

import board.Board

case class King(board: Board, color: Char) extends Piece {

  private var pos: (Int, Int) = (0, 0)

  override def doMove(moveFrom: (Int, Int), moveTo: (Int, Int)): MoveResult = ???

  override def getPos(): (Int, Int) = pos

  override def checkCheck(): Boolean = ???

  override def getColor(): Char = color

  override def setPos(pos: (Int, Int)): Unit = {this.pos = pos}
}
