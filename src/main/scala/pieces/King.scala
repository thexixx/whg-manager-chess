package net.whg.manager
package pieces

import board.Board

import net.whg.manager.pieces.MoveResults.MoveResult

import scala.util.control.Breaks.break

case class King(color: Char) extends Piece {

  def underAttack(): Boolean = {
    board.getWhoIsOnBoard()
      .filter(!_.getColor().equals(this.color))
      .foldLeft(false)((a, b) => a | b.checkCheck())
  }

  override def doMove(moveFrom: (Int, Int), moveTo: (Int, Int)): MoveResult = {
    //check if move shape is correct
    val dC = (moveFrom._1 - moveTo._1).abs
    val dR = (moveFrom._2 - moveTo._2).abs
    if (dC >= 0 && dC <= 1 && dR >= 0 && dR <= 1) {

      //calculate delta
      val deltaCol: Int = if (moveFrom._1 > moveTo._1) -1 else if (moveFrom._1 == moveTo._1) 0 else 1
      val deltaRow = if (moveFrom._2 > moveTo._2) -1 else if (moveFrom._2 == moveTo._2) 0 else 1

      val col = moveFrom._1 + deltaCol
      val row = moveFrom._2 + deltaRow

      val obstacleDetected = board.getPiece((col, row)).isDefined

      if (obstacleDetected && board.getPiece(moveTo).get.getColor().equals(this.getColor())) {
        return MoveResults.ErrorMove
      }

      if(obstacleDetected) {
        //do move to target position and check if King is on under attack from other pieces
        val backup = board.getPiece(moveTo)
        board.clearPlace(moveTo)
        board.clearPlace(moveFrom)
        board.addPiece(this, moveTo)
        pos = moveTo
        if (underAttack()) {
          board.clearPlace(moveTo)
          board.clearPlace(moveFrom)
          board.addPiece(this, moveFrom)
          board.addPiece(backup.get, moveTo)
          return MoveResults.Check
        }
        board.getGrave() += backup.get
        board.getWhoIsOnBoard() -= backup.get
      } else {
        board.clearPlace(moveFrom)
        board.addPiece(this, moveTo)
        pos = moveTo
      }

      if (checkCheck()) {
        print("King do check to another King!")
      }

      MoveResults.ValidMove
    } else {
      MoveResults.ErrorMove
    }
  }

  override def checkCheck(): Boolean = {
    def doCheck(fromCol: Int, toCol: Int, fromRow: Int, toRow: Int, deltaCol: Int, deltaRow: Int): Boolean = {
      val col = fromCol
      val row = fromRow
      val pieceAtPos = board.getPiece((col, row))
      if (pieceAtPos.isDefined
        && pieceAtPos.get.toString.equals(King.toString)
        && !pieceAtPos.get.getColor().equals(getColor())) {
        return true
      }
      false
    }

    doCheck(pos._1 + 1, 7, pos._2 + 1, 7, 1, 1) ||
      doCheck(pos._1 - 1, 0, pos._2 + 1, 7, -1, 1) ||
      doCheck(pos._1 + 1, 7, pos._2 - 1, 0, 1, -1) ||
      doCheck(pos._1 - 1, 0, pos._2 - 1, 0, -1, -1)
  }

  override def getColor(): Char = color
}
