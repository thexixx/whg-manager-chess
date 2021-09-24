package net.whg.manager
package pieces

import net.whg.manager.board.{Board, ChessBoard}
import org.scalatest.flatspec.AnyFlatSpec

class BishopTest  extends AnyFlatSpec {

  it should "get/set position" in {
    val bishop = Bishop(null, 'w')
    bishop.setPos((1,2))
    assert(bishop.getPos() == (1,2))
  }

  it should "test if Bishop can kill" in {
    val board = new ChessBoard
    val bishopW = Bishop(board, 'w')
    val bishopB = Bishop(board, 'b')
    board.addPiece(bishopW, (4,6))
    board.addPiece(bishopB, (2,4))
    val move = Array[Int](4,6,2,4)
    board.movePiece(move)
    assert(board.getPiece(4,6).isEmpty)
    assert(board.getPiece(2,4).isDefined)
    assert(board.getPiece(2,4).get.equals(bishopW))
    assert(board.getGrave.contains(bishopB))
  }

  it should "show that Bishop cannot go through" in {
    val board = new ChessBoard
    val bishopW = Bishop(board, 'w')
    val bishopB = Bishop(board, 'b')
    board.addPiece(bishopW, (4,6))
    board.addPiece(bishopW, (2,4))
    val move = Array[Int](4,6,0,2)
    assert(board.movePiece(move).equals(MoveResults.ErrorMove))
  }

  it should "show there is check to the King 1" in {
    val board = new ChessBoard
    val bishop = Bishop(board, 'w')
    val king = King(board, 'b')
    board.addPiece(bishop, (4,6))
    board.addPiece(king, (5,5))
    assert(bishop.checkCheck())
  }

  it should "show there is check to the King 2" in {
    val board = new ChessBoard
    val bishop = Bishop(board, 'w')
    val king = King(board, 'b')
    board.addPiece(bishop, (4,6))
    board.addPiece(king, (0,2))
    assert(bishop.checkCheck())
  }

  it should "show there is no check to the King" in {
    val board = new ChessBoard
    val bishop = Bishop(board, 'w')
    val king = King(board, 'b')
    board.addPiece(bishop, (4,6))
    board.addPiece(king, (1,2))
    assert(!bishop.checkCheck())
  }

  it should "move Bishop to defined position 1" in {
    val board = new ChessBoard
    val bishop = Bishop(board, 'w')
    board.addPiece(bishop, (4,6))
    val move = Array[Int](4,6,3,5)
    board.movePiece(move)
    assert(board.getPiece(4,6).isEmpty)
    val newBishop1 = board.getPiece(3,5)
    assert(newBishop1.isDefined)
    assert(newBishop1.get.equals(bishop))
  }

  it should "move Bishop to defined position 2 edge of the board" in {
    val board = new ChessBoard
    val bishop = Bishop(board, 'w')
    board.addPiece(bishop, (4,6))
    val move = Array[Int](4,6,0,2)
    board.movePiece(move)
    assert(board.getPiece(4,6).isEmpty)
    val newBishop2 = board.getPiece(0,2)
    assert(newBishop2.isDefined)
    assert(newBishop2.get.equals(bishop))
  }
}
