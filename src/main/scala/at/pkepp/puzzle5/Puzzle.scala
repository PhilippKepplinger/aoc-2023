package at.pkepp
package at.pkepp.puzzle5

import concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.*

class Puzzle(val file: String) {

  val parsedInput: ParsedInput = InputParser(file).parse()

  def solvePartOne: Long = {
    val location = parsedInput.seeds.map(seed => getLocation(seed)).min
    println(location)
    location
  }

  def solvePartTwoParallel: Long = {
    val futures = parsedInput.seedRanges.map(range =>
      Future {
        println("create future...")
        getMinLocation(range)
      }
    )

    val locations = futures.map(future => {
      println("await future...")
      val res = Await.result(future, 2.hour)
      println("Location: " + res)
      res
    })
    println(locations.min)
    locations.min
  }

  def solvePartTwo: Long = {
    var minLocation: Long = Long.MaxValue

    for (seedRange <- parsedInput.seedRanges) {
      val location = getMinLocation(seedRange)
      if (location < minLocation) {
        minLocation = location
      }
    }

    minLocation
  }

  private def getMinLocation(seedRange: SeedRange): Long = {
    var minLocation: Long = Long.MaxValue
    var currentSeed: Long = seedRange.seedStart

    while (currentSeed <= seedRange.seedEnd) {
      val location = getLocation(currentSeed)
      if (location < minLocation) {
        minLocation = location
      }
      currentSeed += 1
    }
    minLocation
  }

  def getLocation(seed: Long): Long = {
    val calculator = DestinationCalculator()
    val soil = calculator.getDestination(seed, parsedInput.seedToSoil)
    val fertilizer = calculator.getDestination(soil, parsedInput.soilToFertilizer)
    val water = calculator.getDestination(fertilizer, parsedInput.fertilizerToWater)
    val light = calculator.getDestination(water, parsedInput.waterToLight)
    val temperature = calculator.getDestination(light, parsedInput.lightToTemperature)
    val humidity = calculator.getDestination(temperature, parsedInput.temperatureToHumidity)
    calculator.getDestination(humidity, parsedInput.humidityToLocation)
  }
}
