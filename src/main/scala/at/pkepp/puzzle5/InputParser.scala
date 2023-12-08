package at.pkepp
package at.pkepp.puzzle5

import scala.io.Source

class InputParser(val file: String) {

  def parse(): ParsedInput = {
    val source = Source.fromResource(file)
    val content = source.mkString
    val blocks = content.split("\\n\\s*\\n")
    source.close()

    ParsedInput(
      parseSeeds(blocks.apply(0)),
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

  private def parseMapDescription(block: String): List[DestinationDescription] = {
    val lines = block.split("\n")
    val linesWithoutHeader = lines.toList.splitAt(1)._2
    linesWithoutHeader
      .map(
        _.split(" ")
        .map(_.toLong)
      )
      .map(line => {
        DestinationDescription(line.apply(0), line.apply(1), line.apply(2))
      })
  }
}
