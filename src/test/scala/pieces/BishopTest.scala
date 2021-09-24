package net.whg.manager
package pieces

import board.ChessBoard
import pieces.Piece.{BLACK, WHITE}

import org.scalatest.flatspec.AnyFlatSpec

class BishopTest extends AnyFlatSpec {

  it should "get/set position" in {
    val bishop = Bishop(WHITE)
    bishop.setPos((1,2))
    assert(bishop.getPos() == (1,2))
  }

  it should "test if Bishop can kill" in {
    val board = new ChessBoard
    val bishopW = Bishop(WHITE)
    val bishopB = Bishop(BLACK)
    board.addPiece(bishopW, (4,6))
    board.addPiece(bishopB, (2,4))
    assert(board.getWhoIsOnBoard().length == 2)
    val move = Array[Int](4,6,2,4)
    board.movePiece(move)
    assert(board.getPiece(4,6).isEmpty)
    assert(board.getPiece(2,4).isDefined)
    assert(board.getPiece(2,4).get.equals(bishopW))
    assert(board.getGrave.contains(bishopB))
    assert(board.getWhoIsOnBoard().length == 1)
    assert(board.getGrave().length == 1)
  }

  it should "show that Bishop cannot go through" in {
    val board = new ChessBoard
    val bishopW = Bishop(WHITE)
    board.addPiece(bishopW, (4,6))
    board.addPiece(bishopW, (2,4))
    val move = Array[Int](4,6,0,2)
    assert(board.movePiece(move).equals(MoveResults.ErrorMove))
  }

  it should "show that Bishop cannot kill allies" in {
    val board = new ChessBoard
    val bishopW = Bishop(WHITE)
    board.addPiece(bishopW, (4,6))
    board.addPiece(bishopW, (2,4))
    val move = Array[Int](4,6,2,4)
    assert(board.movePiece(move).equals(MoveResults.ErrorMove))
  }

  it should "fail to move" in {
    val board = new ChessBoard
    val bishop = Bishop(WHITE)
    board.addPiece(bishop, (4,6))
    val move = Array[Int](4,6,4,5)
    assert(board.movePiece(move).equals(MoveResults.ErrorMove))
  }

  it should "fail to move outside the board" in {
    val board = new ChessBoard
    val bishop = Bishop(WHITE)
    board.addPiece(bishop, (4,6))
    val move = Array[Int](4,6,-1,0)
    assert(board.movePiece(move).equals(MoveResults.ErrorMove))
  }

  it should "show there is check to the King 1" in {
    val board = new ChessBoard
    val bishop = Bishop(WHITE)
    val king = King(BLACK)
    board.addPiece(bishop, (4,6))
    board.addPiece(king, (5,5))
    assert(bishop.checkCheck())
  }

  it should "show there is check to the King 2" in {
    val board = new ChessBoard
    val bishop = Bishop(WHITE)
    val king = King(BLACK)
    board.addPiece(bishop, (4,6))
    board.addPiece(king, (0,2))
    assert(bishop.checkCheck())
  }

  it should "show there is no check to the King" in {
    val board = new ChessBoard
    val bishop = Bishop(WHITE)
    val king = King(BLACK)
    board.addPiece(bishop, (4,6))
    board.addPiece(king, (1,2))
    assert(!bishop.checkCheck())
  }

  it should "move Bishop to defined position 1" in {
    val board = new ChessBoard
    val bishop = Bishop(WHITE)
    board.addPiece(bishop, (4,6))
    val move = Array[Int](4,6,3,5)
    board.movePiece(move)
    assert(board.getPiece(4,6).isEmpty)
    val newBishop1 = board.getPiece(3,5)
    assert(newBishop1.isDefined)
    assert(newBishop1.get.equals(bishop))
    assert(board.getWhoIsOnBoard().length > 1)
  }

  it should "move Bishop to defined position 2 edge of the board" in {
    val board = new ChessBoard
    val bishop = Bishop(WHITE)
    board.addPiece(bishop, (4,6))
    val move = Array[Int](4,6,0,2)
    board.movePiece(move)
    assert(board.getPiece(4,6).isEmpty)
    val newBishop2 = board.getPiece(0,2)
    assert(newBishop2.isDefined)
    assert(newBishop2.get.equals(bishop))
  }
}
