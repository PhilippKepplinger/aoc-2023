package at.pkepp
package at.pkepp.puzzle5

class DestinationCalculator {
  def getDestination(seed: Long, descriptions: List[DestinationDescription]): Long = {
    val dest = descriptions.find(desc => desc.isInSrcRange(seed))

    if dest.isDefined
    then dest.get.getDest(seed)
    else seed
  }
}
