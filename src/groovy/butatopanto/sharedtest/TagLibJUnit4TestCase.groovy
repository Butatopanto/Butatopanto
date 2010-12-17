package butatopanto.sharedtest;


import grails.test.TagLibUnitTestCase
import java.text.MessageFormat
import org.junit.After
import org.junit.Before

class TagLibJUnit4TestCase extends TagLibUnitTestCase {

  protected def messageCodes = [:]
  def remoteFunctions = []
  def submitsToRemote = []
  def formArguments = []

  TagLibJUnit4TestCase() {
    super("TagLib")
  }

  TagLibJUnit4TestCase(Class tagLibClass) {
    super(tagLibClass)
  }

  @Before
  public void setUp() {
    super.setUp()
    registerMetaClass tagLib.class
    mockLink()
    mockCreateLink()
    mockForm()
    mockMessage()
    mockRemoteFunction()
    mockSubmitToRemote()
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

  void mockCreateLink() {
    tagLib.class.metaClass.createLink { arguments ->
      out << "/$arguments.controller/$arguments.action/$arguments.id"
      if (arguments.params) {
        out << '?'
        arguments.params.each {
          key, value ->
          out << "$key=$value"
        }
      }
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

  void mockForm() {
    tagLib.class.metaClass.form {  arguments, body ->
      formArguments.add(arguments)
      "<form>" + body() + "</form>"
    }
  }

  void mockRemoteFunction() {
    tagLib.class.metaClass.remoteFunction { arguments ->
      def index = remoteFunctions.size()
      remoteFunctions.add arguments
      "remoteFunction(${index})"
    }
  }

  private mockSubmitToRemote() {
    tagLib.class.metaClass.submitToRemote {  arguments ->
      def index = submitsToRemote.size()
      submitsToRemote.add arguments
      "submitToRemote(${index})"
    }
  }

  @After
  public void tearDown() {
    super.tearDown()
  }
}