package net.whg.manager
package pieces

class Rook extends Piece {
  override def doMove(moveFrom: (Int, Int), moveTo: (Int, Int)): MoveResult = ???

  override def getPos(): (Int, Int) = ???

  override def checkCheck(): Boolean = ???

  override def getColor(): Char = ???
}
