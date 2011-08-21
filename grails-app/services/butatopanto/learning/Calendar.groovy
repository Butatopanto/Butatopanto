package butatopanto.learning

class Calendar {

  Date getToday() {
    def now = new Date()
    now.clearTime()
    return now
  }
}
