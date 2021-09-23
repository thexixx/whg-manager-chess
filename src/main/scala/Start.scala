package net.whg.manager

import com.whitehatgaming.UserInput
import com.whitehatgaming.UserInputFile
import net.whg.manager.board.{Board, ChessBoard}

object Start {
  def main(args: Array[String]): Unit = {
    val userInput = new UserInputFile(args(0))
    userInput.nextMove()
    userInput.nextMove()
  }
}
