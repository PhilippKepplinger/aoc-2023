package at.pkepp
package at.pkepp.puzzle5

import scala.collection.immutable.NumericRange
import scala.io.Source

class InputParser(val file: String) {

  def parse(): ParsedInput = {
    val source = Source.fromResource(file)
    val content = source.mkString
    val blocks = content.split("\\n\\s*\\n")
    source.close()

    ParsedInput(
      parseSeeds(blocks.apply(0)),
      parseSeedRanges(blocks.apply(0)),
      parseMapDescription(blocks.apply(1)),
      parseMapDescription(blocks.apply(2)),
      parseMapDescription(blocks.apply(3)),
      parseMapDescription(blocks.apply(4)),
      parseMapDescription(blocks.apply(5)),
      parseMapDescription(blocks.apply(6)),
      parseMapDescription(blocks.apply(7)),
    )
  }

  private def parseSeeds(block: String): List[Long] = {
    block
      .replace("seeds: ", "")
      .split(" ")
      .map(_.toLong)
      .toList
  }

  private def parseSeedRanges(block: String): List[SeedRange] = {
    val seeds = parseSeeds(block)
    val pairs = seeds.size / 2
    var res = List[SeedRange]()

    for (i <- 0 until pairs) {
      res = res :+ SeedRange(seeds.apply(i * 2), seeds.apply((i * 2) + 1))
    }

    res
  }

  private def parseMapDescription(block: String): List[DestinationMap] = {
    val lines = block.split("\n")
    val linesWithoutHeader = lines.toList.splitAt(1)._2
    linesWithoutHeader
      .map(
        _.split(" ")
        .map(_.toLong)
      )
      .map(line => {
        DestinationMap(line.apply(0), line.apply(1), line.apply(2))
      })
  }
}
