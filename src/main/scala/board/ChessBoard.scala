package net.whg.manager
package board

import pieces.MoveResults.MoveResult
import pieces.{Bishop, King, MoveResults, Piece}

import net.whg.manager.pieces.Piece.{BLACK, WHITE}

class ChessBoard extends Board {

  override def addPiece(p: Piece, pos: (Int, Int), f: Boolean = true) = {
    if(!validatePos(pos)) throw new BoardException("Position is outside of the board!")
    val pieceOnBoard = getPiece(pos)
    if(pieceOnBoard.isEmpty) {
      board(pos._1)(pos._2) = Some(p)
      p.setPos(pos)
      p.setBoard(this)
      if(f) onBoard += p
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
    }
  }

  override def movePiece(move: Array[Int]): MoveResult = {
    val moveFrom = (move(0), move(1))
    val moveTo = (move(2), move(3))
    if(!validatePos(moveFrom) || !validatePos(moveTo)) return MoveResults.ErrorMove
    val piece = getPiece(moveFrom)
    if(piece.isDefined) {
      if(piece.get.toString.equals(King.toString()) && piece.get.asInstanceOf[King].checkCheckmate()) MoveResults.Checkmate
      else  piece.get.doMove(moveFrom, moveTo)
    } else {
      MoveResults.ErrorMove
    }
  }

  override def addPieceToGraveyard(piece: Piece): Unit = {
    grave += piece
    onBoard -= piece
    clearPlace(piece.getPos())
  }

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

  override def draw(): Unit = {
    def getRow(row: Int): String = {
      var rowStr = ""
      for(col <-0 to 7) {
        val ch = getPiece((col, row-1))
        if(ch.isDefined) rowStr += ch.get.getChar() else rowStr += " "
      }
      rowStr
    }

    println(" ----------")
    println(s"8|${getRow(1)}|")
    println(s"7|${getRow(2)}|")
    println(s"6|${getRow(3)}|")
    println(s"5|${getRow(4)}|")
    println(s"4|${getRow(5)}|")
    println(s"3|${getRow(6)}|")
    println(s"2|${getRow(7)}|")
    println(s"1|${getRow(8)}|")
    println(" ----------")
    println("  abcdefgh")
    println()
  }
}
