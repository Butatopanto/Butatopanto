package butatopanto.test

import groovy.util.slurpersupport.GPathResult


class TagLibUtilities {

  static def GPathResult getContentAsXml(def tagLib) {
    def text = tagLib.out.getBuffer().toString()
    new XmlSlurper().parseText(text)
  }
}
