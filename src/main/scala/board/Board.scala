package net.whg.manager
package board

import pieces.MoveResults.MoveResult
import pieces.Piece

import scala.collection.mutable.ListBuffer

trait Board {

  val board = Array.fill[Option[Piece]](8, 8)(None)
  var grave = ListBuffer[Piece]()

  def init(): Unit

  def addPiece(p: Piece, pos: (Int, Int)): Unit

  def getPiece(pos: (Int, Int)): Option[Piece]

  def clearPlace(pos: (Int, Int)): Unit

  def movePiece(move: Array[Int]): MoveResult

  def addPieceToGraveyard(piece: Piece): Unit
}
