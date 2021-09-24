package net.whg.manager
package pieces

import board.Board

import net.whg.manager.pieces.MoveResults.MoveResult

import scala.util.control.Breaks.break

case class King(color: Char) extends Piece {

  def underAttack(): Boolean = {
    var result = false
    for (row <- 0 to 7; col <- 0 to 7) {
      val piece = board.getPiece((col, row))
      if (piece.isDefined &&
        !piece.get.getColor().equals(this.color) &&
        piece.get.checkCheck()) {
        result = true
        break()
      }
    }
    result
  }

  override def doMove(moveFrom: (Int, Int), moveTo: (Int, Int)): MoveResult = {
    //check if move shape is correct
    if ((moveFrom._1 - moveTo._1).abs == 1 && (moveFrom._2 - moveTo._2).abs == 1) {

      //calculate delta
      val deltaCol: Int = if (moveFrom._1 > moveTo._1) -1 else 1
      val deltaRow = if (moveFrom._2 > moveTo._2) -1 else 1

      val col = moveFrom._1 + deltaCol
      val row = moveFrom._2 + deltaRow

      val obstacleDetected = board.getPiece((col, row)).isDefined

      if (obstacleDetected && board.getPiece(moveTo).get.getColor().equals(this.getColor())) {
        return MoveResults.ErrorMove
      } else if (obstacleDetected && !underAttack()) {
        board.addPieceToGraveyard(board.getPiece(moveTo).get)
      } else if (underAttack()) {
        return MoveResults.Check
      }

      board.clearPlace(moveFrom)
      board.addPiece(this, moveTo)
      pos = moveTo
      //check if King under attack
      if (checkCheck()) {
        print("Check detected!")
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
