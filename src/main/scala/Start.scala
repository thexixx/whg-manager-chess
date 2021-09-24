package net.whg.manager

import com.whitehatgaming.UserInput
import com.whitehatgaming.UserInputFile
import net.whg.manager.board.{Board, ChessBoard}

object Start {
  def main(args: Array[String]): Unit = {
    val userInput = new UserInputFile(args(0))

    val board = new ChessBoard
    board.init()
    board.draw()

    var nextMove = userInput.nextMove()

    while (nextMove != null) {
      val r = board.movePiece(nextMove)
      print(s"${(nextMove(0) + 97).asInstanceOf[Char]}${(56-nextMove(1)).asInstanceOf[Char]}-${(nextMove(2) + 97).asInstanceOf[Char]}${(56-nextMove(3)).asInstanceOf[Char]}")
      println(s" ${r.status}")
      board.draw()
      nextMove = userInput.nextMove()
    }

  }
}
