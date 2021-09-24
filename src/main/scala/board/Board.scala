package net.whg.manager
package board

import pieces.MoveResults.MoveResult
import pieces.Piece

import scala.collection.mutable.ListBuffer

trait Board {

  protected val board = Array.fill[Option[Piece]](8, 8)(None)
  protected var grave = ListBuffer[Piece]()
  protected var onBoard = ListBuffer[Piece]()

  def init(): Unit

  def addPiece(p: Piece, pos: (Int, Int)): Unit

  def getPiece(pos: (Int, Int)): Option[Piece]

  def clearPlace(pos: (Int, Int)): Unit

  def movePiece(move: Array[Int]): MoveResult

  def addPieceToGraveyard(piece: Piece): Unit

  def getGrave(): ListBuffer[Piece] = this.grave

  def getWhoIsOnBoard(): ListBuffer[Piece] = this.onBoard
}
