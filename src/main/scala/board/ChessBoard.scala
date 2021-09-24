package net.whg.manager
package board

import pieces.{Bishop, King, MoveResult, MoveResults, Piece}

import scala.collection.mutable.ListBuffer

class ChessBoard extends Board {

  override def addPiece(p: Piece, pos: (Int, Int)) = {
    val pieceOnBoard = getPiece(pos)
    if(pieceOnBoard.isEmpty) {
      board(pos._1)(pos._2) = Some(p)
      p.setPos(pos)
    } else {
      throw new Exception(s"Position '$pos' already contains '${pieceOnBoard.get}'!")
    }
  }

  override def getPiece(pos: (Int, Int)): Option[Piece] = {
    board(pos._1)(pos._2)
  }

  override def clearPlace(pos: (Int, Int)) = {
    if(getPiece(pos).isDefined) {
      board(pos._1)(pos._2) = None
    } else {
      throw new Exception(s"Nothing to kill on position '$pos'!")
    }
  }

  override def movePiece(move: Array[Int]): MoveResult = {
    val moveFrom = (move(0), move(1))
    val piece = getPiece(moveFrom)
    if(piece.isDefined) {
      piece.get.doMove(moveFrom, (move(2), move(3)))
    } else {
      MoveResults.ErrorMove
    }
  }

  override def addPieceToGraveyard(piece: Piece): Unit = {
    grave += piece
    clearPlace(piece.getPos())
  }

  def getGrave = grave

  override def init(): Unit = {
    //white
    addPiece(Bishop(this, 'w'), (2, 7))
    addPiece(Bishop(this, 'w'), (5, 7))
    addPiece(King(this, 'w'), (3, 7))

    //black
    addPiece(Bishop(this, 'b'), (2, 0))
    addPiece(Bishop(this, 'b'), (5, 0))
    addPiece(King(this, 'b'), (3, 0))
  }
}
