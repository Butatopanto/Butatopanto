package butatopanto.test;


import grails.test.TagLibUnitTestCase
import org.junit.After
import org.junit.Before
import java.text.MessageFormat

class TagLibJUnit4TestCase extends TagLibUnitTestCase {

  protected def messageCodes = [:]

  TagLibJUnit4TestCase() {
    super("TagLib")
  }

  TagLibJUnit4TestCase(Class tagLibClass) {
    super(tagLibClass)
  }

  @Before
  public void setUp() {
    super.setUp()
    mockLink()
    mockMessage()
  }

  void mockLink() {
    tagLib.class.metaClass.link { arguments, body ->
      out << "<a "
      out << "class='${arguments."class"}'"
      out << " href = '/link/to/${arguments.action}/${arguments.id}' > "
      body()
      out << "</a>"
      return ""
    }
  }

  void mockMessage() {
    tagLib.class.metaClass.message { arguments ->
      def pattern = messageCodes[arguments.code]
      if (!pattern) {
        return "unconfigured message code: ${arguments.code}"
      }
      return MessageFormat.format(pattern, arguments.args as Object[])
    }
  }

  @After
  public void tearDown() {
    super.tearDown()
  }
}