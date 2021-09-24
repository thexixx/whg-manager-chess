package net.whg.manager
package board

import pieces.Piece.{BLACK, WHITE}
import pieces.{Bishop, King}

import org.scalatest.flatspec.AnyFlatSpec

class ChessBoardTest extends AnyFlatSpec {

  it should "store piece position" in {
    val board = new ChessBoard
    val bishop = Bishop(WHITE)
    board.addPiece(bishop, (1,3))
    assert(bishop.getPos() == (1,3))
  }

  it should "add piece to the board" in {
    val board = new ChessBoard
    val bishop = Bishop(WHITE)
    board.addPiece(bishop, (1,3))
    assert(board.getPiece(1,3).isDefined)
  }

  it should "fail to add piece to outside of the board" in {
    val board = new ChessBoard
    val bishop = Bishop(WHITE)
    assertThrows[BoardException](board.addPiece(bishop, (-1,3)))
  }

  it should "fail to add piece to occupied place" in {
    val board = new ChessBoard
    val bishop1 = Bishop(WHITE)
    val bishop2 = Bishop(WHITE)
    board.addPiece(bishop1, (1,1))
    assertThrows[BoardException](board.addPiece(bishop2, (1,1)))
  }

  it should "clear place on the board" in {
    val board = new ChessBoard
    val bishop = Bishop(WHITE)
    board.addPiece(bishop, (1,3))
    board.clearPlace((1,3))
    assert(board.getPiece(1,3).isEmpty)
  }

  it should "remove piece to grave" in {
    val board = new ChessBoard
    val bishop = Bishop(WHITE)
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

  it should "draw board" in {
    val board = new ChessBoard
    val kingB = King(BLACK)
    val bishopB1 = Bishop(BLACK)
    val bishopB2 = Bishop(BLACK)
    val bishopW1 = Bishop(WHITE)
    val bishopW2 = Bishop(WHITE)
    board.addPiece(kingB, (7,1))
    board.addPiece(bishopB1, (6,0))
    board.addPiece(bishopB2, (7,2))
    board.addPiece(bishopW1, (5,3))
    board.addPiece(bishopW2, (3,4))
    board.draw()
  }
}
