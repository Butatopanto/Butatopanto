package butatopanto.request

class ListRequester {

  static ListRequester From(List all) {
     new ListRequester(all: all)
  }

  private List all = [];
  private def sortClosure = {def a, def b -> 0}
  int offset = 0

  ListRequester sortedAscendingBy(String propertyPath) {
    sortClosure = {def a, def b ->
      getProperty(a, propertyPath) <=> getProperty(b, propertyPath)
    }
    return this
  }

  ListRequester sortedDescendingBy(String propertyPath) {
    sortClosure = ({def a, def b ->
      getProperty(b, propertyPath) <=> getProperty(a, propertyPath)
    })
    return this
  }

  ListRequester startingFromIndex(int offset) {
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
    new BeanRequester(bean).findProperty(propertyPath)
  }
}