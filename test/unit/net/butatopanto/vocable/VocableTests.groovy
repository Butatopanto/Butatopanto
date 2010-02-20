package net.butatopanto.vocable

import grails.test.*

class VocableTests extends GrailsUnitTestCase {
  protected void setUp() {
    super.setUp()
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testSomething() {
    assertEquals Vocable.constraints(0), "Meaning"
  }
}
