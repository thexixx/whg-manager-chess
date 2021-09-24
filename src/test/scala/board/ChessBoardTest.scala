package net.whg.manager
package board

import net.whg.manager.pieces.{Bishop, King}
import org.scalatest.flatspec.AnyFlatSpec

class ChessBoardTest extends AnyFlatSpec {

  it should "store piece position" in {
    val board = new ChessBoard
    val bishop = Bishop(board, 'w')
    board.addPiece(bishop, (1,3))
    assert(bishop.getPos() == (1,3))
  }

  it should "add piece to the board" in {
    val board = new ChessBoard
    val bishop = Bishop(board, 'w')
    board.addPiece(bishop, (1,3))
    assert(board.getPiece(1,3).isDefined)
  }

  it should "clear place on the board" in {
    val board = new ChessBoard
    val bishop = Bishop(board, 'w')
    board.addPiece(bishop, (1,3))
    board.clearPlace((1,3))
    assert(board.getPiece(1,3).isEmpty)
  }

  it should "remove piece to grave" in {
    val board = new ChessBoard
    val bishop = Bishop(board, 'w')
    board.addPiece(bishop, (1,3))
    board.addPieceToGraveyard(bishop)
    assert(board.getPiece(1,3).isEmpty)
    assert(board.getGrave.length > 0)
  }


  it should "initialize board with pieces" in {
    val board = new ChessBoard
    board.init()
    assert(board.getPiece((2,7)).isDefined)
    assert(board.getPiece((2,6)).isEmpty)
    assert(board.getPiece((2,7)).get.toString.equals(Bishop.toString()))
    assert(board.getPiece((3,7)).get.toString.equals(King.toString()))
  }

}
