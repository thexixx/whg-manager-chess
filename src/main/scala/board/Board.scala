package net.whg.manager
package board

import pieces.{MoveResult, Piece}

import scala.collection.mutable.ListBuffer

trait Board {

  val board = Array.fill[Option[Piece]](8,8)(None)
  var grave = ListBuffer[Piece]()
  def init()
  def addPiece(p: Piece, pos: (Int, Int))
  def getPiece(pos: (Int, Int)): Option[Piece]
  def clearPlace(pos: (Int, Int))
  def movePiece(move: Array[Int]): MoveResult
  def addPieceToGraveyard(piece: Piece)
}
