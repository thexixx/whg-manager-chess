package net.whg.manager
package pieces

object MoveResults {
  object ErrorMove extends MoveResult("error")
  object ValidMove extends MoveResult("valid")
  object CheckMove extends MoveResult("check")
  object CheckMat extends MoveResult("check_mat")
}
