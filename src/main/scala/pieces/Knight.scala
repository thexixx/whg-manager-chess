package net.whg.manager
package pieces

import pieces.MoveResults.MoveResult

import net.whg.manager.board.Board

class Knight extends Piece {
  override def doMove(moveFrom: (Int, Int), moveTo: (Int, Int), v:Boolean = false): MoveResult = ???

  override def checkCheck(): Boolean = ???

  override def getColor(): Char = ???

  override def getChar(): Char = ???
}
