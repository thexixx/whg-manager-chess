package net.whg.manager
package pieces


trait Piece {
  def doMove(moveFrom: (Int, Int), moveTo: (Int, Int)): MoveResult
  def getPos(): (Int, Int)
  def checkCheck(): Boolean
  def getColor(): Char

  override def toString: String = this.getClass.getSimpleName
}
