package at.pkepp
package puzzle5

import at.pkepp.puzzle5.InputParser
import at.pkepp.puzzle5.DestinationMap
import at.pkepp.puzzle5.DestinationCalculator
import at.pkepp.puzzle5.Puzzle

import org.scalatest.funspec.AnyFunSpec

class Puzzle5Test extends AnyFunSpec {

  describe("Puzzle Input Reader") {
    it("should parse file correctly") {
      val parsedInput = InputParser("puzzle5/test-input-1.txt").parse()
      assert(parsedInput.seeds.size == 4)
      assert(parsedInput.seedRanges.size == 2)
      assert(parsedInput.seedToSoil.size == 2)
      assert(parsedInput.soilToFertilizer.size == 3)
      assert(parsedInput.fertilizerToWater.size == 4)
      assert(parsedInput.waterToLight.size == 2)
      assert(parsedInput.lightToTemperature.size == 3)
      assert(parsedInput.temperatureToHumidity.size == 2)
      assert(parsedInput.humidityToLocation.size == 2)
    }
  }

  describe("Destination Description") {
    val destDescription = DestinationMap(50, 98, 2)

    it("should be in range") {
      assert(destDescription.isInSrcRange(98))
      assert(destDescription.isInSrcRange(99))
    }

    it("should not be in range") {
      assert(!destDescription.isInSrcRange(100))
      assert(!destDescription.isInSrcRange(97))
    }

    it("should get destination from range") {
      assert(destDescription.getDest(98) == 50)
      assert(destDescription.getDest(99) == 51)
    }
  }

  describe("Destination Calculator") {
    val descriptions = List(
      DestinationMap(50, 98, 2),
      DestinationMap(52, 50, 48),
    )

    it("should not find any destination before range") {
      val seed = 49
      val dest = DestinationCalculator().getDestination(seed, descriptions)
      assert(dest === seed)
    }

    it("should not find any destination after range") {
      val seed = 100
      val dest = DestinationCalculator().getDestination(seed, descriptions)
      assert(dest === seed)
    }

    it("should find from first description") {
      val seed = 99
      val dest = DestinationCalculator().getDestination(seed, descriptions)
      assert(dest === 51)
    }

    it("should find from second description start") {
      val seed = 50
      val dest = DestinationCalculator().getDestination(seed, descriptions)
      assert(dest === 52)
    }

    it("should find from second description end") {
      val seed = 97
      val dest = DestinationCalculator().getDestination(seed, descriptions)
      assert(dest === 99)
    }
  }

  describe("Puzzle") {
    val puzzle = Puzzle("puzzle5/test-input-1.txt")

    it("should map seeds correctly") {
      assert(puzzle.getLocation(79) == 82)
      assert(puzzle.getLocation(14) == 43)
      assert(puzzle.getLocation(55) == 86)
      assert(puzzle.getLocation(13) == 35)
    }

    it("should solve part one") {
      assert(puzzle.solvePartOne == 35)
    }

    it("should solve part two") {
      val res = puzzle.solvePartTwo
      assert(res == 46)
    }
  }
}
