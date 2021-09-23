package net.whg.manager
package board

import net.whg.manager.pieces.{Bishop, King}
import org.scalatest.flatspec.AnyFlatSpec

class ChessBoardTest extends AnyFlatSpec {
  "Init" should "initialize board with pieces" in {
    val board = new ChessBoard
    board.init()
    assert(board.getPiece((2,7)).isDefined)
    assert(board.getPiece((2,6)).isEmpty)
    assert(board.getPiece((2,7)).get.toString.equals(Bishop.toString()))
    assert(board.getPiece((3,7)).get.toString.equals(King.toString()))
  }

}
