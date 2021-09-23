package net.whg.manager
package board

import pieces.{Bishop, MoveResult, Piece}

import scala.collection.immutable.HashMap

trait Board {

  val board = Array.ofDim[Option[Piece]](7, 7)
  def addPiece(p: Piece)
  def getPiece(pos: (Int, Int)): Option[Piece]
  def killPiece(pos: (Int, Int))
  def movePiece(move: Array[Int]): MoveResult
  def addPieceToGraveyard(piece: Piece)
}
