package butatopanto.test;


import grails.test.TagLibUnitTestCase
import org.junit.After
import org.junit.Before

class TagLibJUnit4TestCase extends TagLibUnitTestCase {

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
  }

  void mockLink() {
    tagLib.class.metaClass.link { arguments, body ->
      def theOut = out
      out << "<a "
      def cssClass = arguments."class"
      if (cssClass) {
        out << "class='${cssClass}'"
      }
      out << " href = '/link/to/${arguments.action}/${arguments.id}' > "
      body()
      out << "</a>"
      return ""
    }
  }

  @After
  public void tearDown() {
    super.tearDown()
  }
}