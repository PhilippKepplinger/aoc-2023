package at.pkepp
package at.pkepp.puzzle5

class DestinationMap(val destStart: Long, val srcStart: Long, val length: Long) {
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


class DestinationCalculator {
  def getDestination(seed: Long,
                     descriptions: List[DestinationMap]): Long = {
    var selectedDesc: DestinationMap = null

    for (desc <- descriptions) {
      if (desc.isInSrcRange(seed)) {
        selectedDesc = desc
      }
    }

    if selectedDesc != null
    then selectedDesc.getDest(seed)
    else seed
  }
}
