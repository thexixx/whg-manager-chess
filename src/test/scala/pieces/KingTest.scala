package net.whg.manager
package pieces

import board.ChessBoard
import pieces.Piece.{BLACK, WHITE}

import org.scalatest.flatspec.AnyFlatSpec

class KingTest extends AnyFlatSpec {

  it should "successfully move King 1" in {
    val board = new ChessBoard
    val king = King(WHITE)
    board.addPiece(king, (1,1))
    assert(board.getPiece((1,1)).isDefined)
    val move = Array[Int](1,1,2,2)
    board.movePiece(move)
    assert(board.getPiece((1,1)).isEmpty)
    assert(board.getPiece((2,2)).isDefined)
    assert(board.getPiece((2,2)).get.toString.equals(King.toString()))
  }

  it should "successfully move King 2" in {
    val board = new ChessBoard
    val king = King(WHITE)
    board.addPiece(king, (2,2))
    assert(board.getPiece((2,2)).isDefined)
    val move = Array[Int](2,2,1,2)
    board.movePiece(move)
    assert(board.getPiece((2,2)).isEmpty)
    assert(board.getPiece((1,2)).isDefined)
    assert(board.getPiece((1,2)).get.toString.equals(King.toString()))
  }

  it should "fail to move King - wrong move path" in {
    val board = new ChessBoard
    val king = King(WHITE)
    board.addPiece(king, (2,2))
    assert(board.getPiece((2,2)).isDefined)
    val move = Array[Int](2,2,1,4)
    assert(board.movePiece(move).equals(MoveResults.ErrorMove))
  }

  it should "fail to move King - check" in {
    val board = new ChessBoard
    val king = King(WHITE)
    val bishop = Bishop(BLACK)
    board.addPiece(king, (2,2))
    board.addPiece(bishop, (1,2))
    val move = Array[Int](2,2,2,3)
    assert(board.movePiece(move).equals(MoveResults.Check))
  }

  it should "fail to move King - place occupied by ally" in {
    val board = new ChessBoard
    val king = King(WHITE)
    val bishop = Bishop(WHITE)
    board.addPiece(king, (2,2))
    board.addPiece(bishop, (1,2))
    val move = Array[Int](2,2,1,2)
    assert(board.movePiece(move).equals(MoveResults.ErrorMove))
  }

  it should "fail to move King - checkmate" in {
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
    val move = Array[Int](7,1,7,0)
    assert(board.movePiece(move).equals(MoveResults.Checkmate))
  }

  it should "test if King can kill" in {
    val board = new ChessBoard
    val king = King(WHITE)
    val bishop = Bishop(BLACK)
    board.addPiece(king, (2,2))
    board.addPiece(bishop, (1,3))
    val move = Array[Int](2,2,1,3)
    board.movePiece(move)
    assert(board.getGrave.length == 1)
  }



}
