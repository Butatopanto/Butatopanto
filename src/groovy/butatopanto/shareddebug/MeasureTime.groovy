package butatopanto.shareddebug

class MeasureTime {

  def toSystemError(Closure closure) {
    def start = System.currentTimeMillis()
    def result = closure.call()
    def end = System.currentTimeMillis()
    def duration = end - start
    System.err.println("Time to perform task: $duration ms.")
    result
  }
}
