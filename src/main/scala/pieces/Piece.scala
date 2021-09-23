package net.whg.manager
package pieces


trait Piece {
  def doMove(moveFrom: (Int, Int), moveTo: (Int, Int)): MoveResult
  def init(): Boolean
  def getPos(): (Int, Int)
  def checkCheck(): Boolean

  override def toString: String = this.getClass.getSimpleName
}
