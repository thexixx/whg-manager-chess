package net.whg.manager
package pieces
import board.Board


case class Bishop(board: Board, color: Char, var pos: (Int, Int)) extends Piece {

  override def doMove(moveFrom: (Int, Int), moveTo: (Int, Int)): MoveResult = {
    //check if move shape is correct
    if((moveFrom._1-moveTo._1).abs == (moveFrom._2-moveTo._2).abs) {
      //check if no other pieces between moveFrom and moveTo
      val deltaCol:Int = if(moveFrom._1 > moveTo._1) -1 else 1
      val deltaRow = if(moveFrom._2 > moveTo._2) -1 else 1
      var col = moveFrom._1 + deltaCol
      var row = moveFrom._2 + deltaRow
      while (col<moveTo._1) {
        if(board.getPiece((col, row)).isEmpty) {
          col = moveFrom._1 + deltaCol
          row = moveFrom._2 + deltaRow
        } else {
          throw new Exception("Bishop cannot move through another piece!")
        }
      }
      if(board.getPiece(moveTo).isDefined) {
        board.addPieceToGraveyard(board.getPiece(moveTo).get)
      }
      pos = moveTo
      //check if King under attack
      if(checkCheck()) {
        print("Check detected!")
      }
      MoveResults.ValidMove
    } else {
      MoveResults.ErrorMove
    }

  }

  override def getPos = pos

  override def checkCheck(): Boolean = {
    def doCheck(fromCol:Int, toCol: Int, fromRow:Int, toRow: Int, deltaCol: Int, deltaRow: Int): Boolean = {
      var col = fromCol
      var row = fromRow
      while (col <= toCol && row <= toRow) {
        val pieceAtPos = board.getPiece((col, row))
        if (pieceAtPos.isDefined
          && pieceAtPos.get.getClass.toString.equals(King.getClass.toString)
          && !pieceAtPos.get.getColor().equals(getColor())) {
          return true
        }
        col += deltaCol
        row += deltaRow
      }
      false
    }

    doCheck(pos._1, 7, pos._2, 7, 1, 1) ||
      doCheck(pos._1, 0, pos._2, 7, -1, 1) ||
      doCheck(pos._1, 7, pos._2, 0, 1, -1) ||
      doCheck(pos._1, 0, pos._2, 0, -1, -1)
  }

  override def getColor(): Char = color
}
