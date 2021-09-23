package net.whg.manager
package board

import pieces.{Bishop, MoveResult, Piece}

import scala.collection.immutable.HashMap

trait Board {

  val board = Array.fill[Option[Piece]](8,8)(None)
  def init()
  def addPiece(p: Piece)
  def getPiece(pos: (Int, Int)): Option[Piece]
  def killPiece(pos: (Int, Int))
  def movePiece(move: Array[Int]): MoveResult
  def addPieceToGraveyard(piece: Piece)
}
