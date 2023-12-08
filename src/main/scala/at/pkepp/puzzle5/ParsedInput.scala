package at.pkepp
package at.pkepp.puzzle5

import scala.collection.immutable.NumericRange


class SeedRange(val seedStart: Long, val length: Long) {
  val seedEnd: Long = seedStart + length - 1
}

class ParsedInput(val seeds: List[Long],
                  val seedRanges: List[SeedRange],
                  val seedToSoil: List[DestinationMap],
                  val soilToFertilizer: List[DestinationMap],
                  val fertilizerToWater: List[DestinationMap],
                  val waterToLight: List[DestinationMap],
                  val lightToTemperature: List[DestinationMap],
                  val temperatureToHumidity: List[DestinationMap],
                  val humidityToLocation: List[DestinationMap]) {
}
