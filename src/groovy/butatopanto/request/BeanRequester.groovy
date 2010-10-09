package butatopanto.request

class BeanRequester {
  def bean

  BeanRequester(def bean) {
    this.bean = bean
  }

  def findProperty(String propertyPath) {
    def propertyValue = bean
    for (String propertyName: propertyPath.split("\\.")) {
      propertyValue = propertyValue."${propertyName}"
    }
    return propertyValue
  }
}
