package butatopanto.kanji.bean

class BeanRequester {
  def bean

  BeanRequester(def bean) {
    this.bean = bean
  }

  def getProperty(String propertyPath) {
    def propertyValue = bean
    for (String propertyName: propertyPath.split("\\.")) {
      propertyValue = propertyValue."${propertyName}"
    }
    return propertyValue
  }
}
