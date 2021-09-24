package net.whg.manager
package pieces

import board.Board

import net.whg.manager.pieces.MoveResults.MoveResult


case class Bishop(color: Char) extends Piece {

  override def doMove(moveFrom: (Int, Int), moveTo: (Int, Int)): MoveResult = {
    //check if move shape is correct
    if ((moveFrom._1 - moveTo._1).abs == (moveFrom._2 - moveTo._2).abs) {
      //check if no other pieces between moveFrom and moveTo
      val deltaCol: Int = if (moveFrom._1 > moveTo._1) -1 else 1
      val deltaRow = if (moveFrom._2 > moveTo._2) -1 else 1
      var col = moveFrom._1 + deltaCol
      var row = moveFrom._2 + deltaRow
      var obstacleDetected = false
      while (col != moveTo._1 && !obstacleDetected) {
        obstacleDetected |= board.getPiece((col, row)).isDefined
        col += deltaCol
        row += deltaRow
      }
      obstacleDetected |= board.getPiece((col, row)).isDefined

      if (obstacleDetected && moveTo != (col, row)) {
        return MoveResults.ErrorMove
      }

      if (board.getPiece(moveTo).isDefined &&
        !board.getPiece(moveTo).get.getColor().equals(this.getColor())) {
        board.addPieceToGraveyard(board.getPiece(moveTo).get)
      } else if (board.getPiece(moveTo).isDefined &&
        board.getPiece(moveTo).get.getColor().equals(this.getColor())) {
        return MoveResults.ErrorMove
      }

      board.clearPlace(moveFrom)
      board.addPiece(this, moveTo, false)
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
      var col = fromCol
      var row = fromRow
      while (col * deltaCol <= toCol && row * deltaRow <= toRow) {
        val pieceAtPos = board.getPiece((col, row))
        if (pieceAtPos.isDefined
          && pieceAtPos.get.toString.equals(King.toString)
          && !pieceAtPos.get.getColor().equals(getColor())) {
          return true
        }
        col += deltaCol
        row += deltaRow
      }
      false
    }

    doCheck(pos._1 + 1, 7, pos._2 + 1, 7, 1, 1) ||
      doCheck(pos._1 - 1, 0, pos._2 + 1, 7, -1, 1) ||
      doCheck(pos._1 + 1, 7, pos._2 - 1, 0, 1, -1) ||
      doCheck(pos._1 - 1, 0, pos._2 - 1, 0, -1, -1)
  }

  override def getColor(): Char = color

  override def hashCode(): Int = super.hashCode() + Math.random().toInt
}