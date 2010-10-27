package butatopanto.sharedtest

import groovy.util.slurpersupport.GPathResult


class TagLibUtilities {

  static def GPathResult getContentAsXml(def tagLib) {
    new XmlSlurper().parseText(getContentAsString(tagLib))
  }

  static def GPathResult getWrappedContentAsXml(def tagLib) {
    def text = "<root>${tagLib.out.getBuffer().toString()}</root>"
    new XmlSlurper().parseText(text)
  }

  private static def getContentAsString(tagLib) {
    tagLib.out.getBuffer().toString()
  }
}
