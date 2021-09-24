package net.whg.manager
package pieces

import pieces.MoveResults.MoveResult

class Queen extends Piece {
  override def doMove(moveFrom: (Int, Int), moveTo: (Int, Int)): MoveResult = ???

  override def getPos(): (Int, Int) = ???

  override def checkCheck(): Boolean = ???

  override def getColor(): Char = ???

  override def setPos(pos: (Int, Int)): Unit = ???
}
