package at.pkepp

import scala.main
import at.pkepp.puzzle5.Puzzle

@main
def main(): Unit = {
  Puzzle("puzzle5/example-input.txt").solve
  Puzzle("puzzle5/input.txt").solve
}
