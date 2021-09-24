package net.whg.manager
package pieces

import board.Board

import net.whg.manager.pieces.MoveResults.MoveResult
import net.whg.manager.pieces.Piece.WHITE

import scala.util.control.Breaks.break

case class King(color: Char) extends Piece {

  def underAttack(): Boolean = {
    board.getWhoIsOnBoard()
      .filter(!_.getColor().equals(this.color))
      .foldLeft(false)((a, b) => a | b.checkCheck())
  }

  override def doMove(moveFrom: (Int, Int), moveTo: (Int, Int), v: Boolean = false): MoveResult = {
    //check if move shape is correct
    val dC = (moveFrom._1 - moveTo._1).abs
    val dR = (moveFrom._2 - moveTo._2).abs

    if (dC >= 0 && dC <= 1 && dR >= 0 && dR <= 1) {

      //calculate delta
      val deltaCol: Int = if (moveFrom._1 > moveTo._1) -1 else if (moveFrom._1 == moveTo._1) 0 else 1
      val deltaRow = if (moveFrom._2 > moveTo._2) -1 else if (moveFrom._2 == moveTo._2) 0 else 1

      val col = moveFrom._1 + deltaCol
      val row = moveFrom._2 + deltaRow

      val obstacleDetected = board.getPiece((col, row)).isDefined

      val allyDetected = obstacleDetected && board.getPiece(moveTo).get.getColor().equals(this.getColor())
      if (allyDetected) {
        return MoveResults.ErrorMove
      }
      val enemyDetected = obstacleDetected && !allyDetected

      val backup = board.getPiece(moveTo)
      board.clearPlace(moveTo)
      board.clearPlace(moveFrom)
      board.addPiece(this, moveTo, false)
      pos = moveTo
      val mightMoveTo = !underAttack()

      def redo(backup: Option[Piece]) = {
        board.clearPlace(moveTo)
        board.addPiece(this, moveFrom, false)
        if (backup.isDefined) board.addPiece(backup.get, moveTo, false)
        pos = moveFrom
      }

      // if place to move occupied by ally - cannot move
      // if place to move is under attack - cannot move
      if (allyDetected || !mightMoveTo) {
        redo(backup)
        return if (allyDetected) MoveResults.ErrorMove else MoveResults.Check
      }

      if(v) redo(backup)

      // if place to move occupied by enemy and not under attack - kill
      if (enemyDetected && mightMoveTo && !v) {
        board.addPieceToGraveyard(backup.get)
      }

      // if place to move empty - can move

      MoveResults.ValidMove
    } else {
      MoveResults.ErrorMove
    }
  }

  override def checkCheck(): Boolean = {
    def doCheck(fromCol: Int, toCol: Int, fromRow: Int, toRow: Int, deltaCol: Int, deltaRow: Int): Boolean = {
      if(!board.validatePos((fromCol, fromRow))) return false
      val col = fromCol
      val row = fromRow
      val pieceAtPos = board.getPiece((col, row))
      if (pieceAtPos.isDefined
        && pieceAtPos.get.toString.equals(King.toString)
        && !pieceAtPos.get.getColor().equals(getColor())) {
        return true
      }
      false
    }

    doCheck(pos._1 + 1, 7, pos._2 + 1, 7, 1, 1) ||
      doCheck(pos._1 - 1, 0, pos._2 + 1, 7, -1, 1) ||
      doCheck(pos._1 + 1, 7, pos._2 - 1, 0, 1, -1) ||
      doCheck(pos._1 - 1, 0, pos._2 - 1, 0, -1, -1)
  }

  def checkCheckmate(): Boolean = {

    def m(moveFrom: (Int, Int), moveTo: (Int, Int)): Boolean = {
      val b1 = board.validatePos(moveTo)
      if(b1) b1 && !doMove(moveFrom, moveTo, true).equals(MoveResults.ValidMove)
      else true
    }

    var result = true

    result &= m(pos, (pos._1 + 1, pos._2))
    result &= m(pos, (pos._1, pos._2 + 1))
    result &= m(pos, (pos._1 - 1, pos._2))
    result &= m(pos, (pos._1, pos._2 - 1))
    result &= m(pos, (pos._1 + 1, pos._2 + 1))
    result &= m(pos, (pos._1 - 1, pos._2 - 1))
    result &= m(pos, (pos._1 + 1, pos._2 - 1))
    result &= m(pos, (pos._1 - 1, pos._2 + 1))

    result
  }

  override def getColor(): Char = color

  override def getChar(): Char = {
    if(color.equals(WHITE)) 'K' else 'k'
  }

}
