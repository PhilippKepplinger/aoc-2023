package at.pkepp

import scala.main
import at.pkepp.puzzle5.Puzzle5

@main
def main(): Unit = {
  val start = System.currentTimeMillis()

//  Puzzle("puzzle5/example-input.txt").solvePartOne
//  Puzzle("puzzle5/input.txt").solvePartOne
  Puzzle5("puzzle5/example-input.txt").solvePartTwoParallel
  Puzzle5("puzzle5/input.txt").solvePartTwoParallel

  val end = System.currentTimeMillis()
  println((end - start) / 1000)
}
