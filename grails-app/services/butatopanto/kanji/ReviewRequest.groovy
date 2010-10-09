package butatopanto.kanji

import butatopanto.kanji.bean.BeanRequester

class ReviewRequest {

  static From(List all) {
     new ReviewRequest(all: all)
  }

  private List all = [];
  private def sortClosure = {def a, def b -> 0}
  int offset = 0

  ReviewRequest sortedAscendingBy(String propertyPath) {
    sortClosure = {def a, def b ->
      getProperty(a, propertyPath) <=> getProperty(b, propertyPath)
    }
    return this
  }

  ReviewRequest sortedDescendingBy(String propertyPath) {
    sortClosure = ({def a, def b ->
      getProperty(b, propertyPath) <=> getProperty(a, propertyPath)
    })
    return this
  }

  ReviewRequest startingFromIndex(int offset) {
    this.offset = offset;
    return this
  }

  List getAtMostElements(int max) {
    List sortedList = getSortedList(all)
    List offsetList = getOffsetList(sortedList)
    getLimitedList(offsetList, max)
  }

  private List getSortedList(List from) {
    from.sort(sortClosure)
  }

  private List getOffsetList(List from) {
    from.subList(offset, from.size())
  }

  private List getLimitedList(List from, int max) {
    max = Math.min(max, from.size())
    from.subList(0, max)
  }

  private def getProperty(def bean, def propertyPath) {
    new BeanRequester(bean).getProperty(propertyPath)
  }
}