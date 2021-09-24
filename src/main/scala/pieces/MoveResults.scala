package net.whg.manager
package pieces

object MoveResults {
  case class MoveResult(status: String)
  object ErrorMove extends MoveResult("error")
  object ValidMove extends MoveResult("valid")
  object CheckMove extends MoveResult("check")
  object Checkmate extends MoveResult("checkmate")
  object Check extends MoveResult( "check")
}
