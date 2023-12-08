package at.pkepp
package at.pkepp.puzzle5

class DestinationDescription(val destStart: Long, val srcStart: Long, val length: Long) {
  val srcEnd: Long = srcStart + length - 1
  val offset: Long = destStart - srcStart

  def isInSrcRange(value: Long): Boolean = {
    value >= srcStart && value <= srcEnd
  }

  def getDest(src: Long): Long = {
    if (!isInSrcRange(src)) {
      throw IndexOutOfBoundsException("src is not in description range.")
    }

    src + offset
  }
}

class ParsedInput(val seeds: List[Long],
                  val seedToSoil: List[DestinationDescription],
                  val soilToFertilizer: List[DestinationDescription],
                  val fertilizerToWater: List[DestinationDescription],
                  val waterToLight: List[DestinationDescription],
                  val lightToTemperature: List[DestinationDescription],
                  val temperatureToHumidity: List[DestinationDescription],
                  val humidityToLocation: List[DestinationDescription]) {
}
