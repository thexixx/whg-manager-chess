package net.whg.manager
package board

import pieces.MoveResults.MoveResult
import pieces.{Bishop, King, MoveResults, Piece}

import net.whg.manager.pieces.Piece.{BLACK, WHITE}

class ChessBoard extends Board {

  override def addPiece(p: Piece, pos: (Int, Int)) = {
    if(pos._1 < 0 || pos._1 > 7 || pos._2 < 0 || pos._2 > 7) throw new BoardException("Position is outside of the board!")
    val pieceOnBoard = getPiece(pos)
    if(pieceOnBoard.isEmpty) {
      board(pos._1)(pos._2) = Some(p)
      p.setPos(pos)
      p.setBoard(this)
    } else {
      throw new BoardException(s"Position '$pos' already contains '${pieceOnBoard.get}'!")
    }
  }

  override def getPiece(pos: (Int, Int)): Option[Piece] = {
    board(pos._1)(pos._2)
  }

  override def clearPlace(pos: (Int, Int)) = {
    if(getPiece(pos).isDefined) {
      board(pos._1)(pos._2) = None
    } else {
      throw new BoardException(s"Nothing to kill on position '$pos'!")
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
    addPiece(Bishop(WHITE), (2, 7))
    addPiece(Bishop(WHITE), (5, 7))
    addPiece(King(WHITE), (3, 7))

    //black
    addPiece(Bishop(BLACK), (2, 0))
    addPiece(Bishop(BLACK), (5, 0))
    addPiece(King(BLACK), (3, 0))
  }
}
