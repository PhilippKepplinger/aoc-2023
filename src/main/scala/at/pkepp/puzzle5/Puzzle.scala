package at.pkepp
package at.pkepp.puzzle5

class Puzzle(val file: String) {

  val parsedInput: ParsedInput = InputParser(file).parse()

  def solve: Unit = {
    println(getNearestLocation)
  }

  def getNearestLocation: Long = {
    getLocations.min
  }

  def getLocations: List[Long] = {
    parsedInput.seeds.map(seed => getLocation(seed))
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
