<%@ page import="butatopanto.kanji.Frame" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:javascript library="prototype"/>
  <title>Kennst Du das Kanji?</title>
</head>
<body>
<div class="nav">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
  <span class="menuButton"><g:remoteLink class="learn" action="next" update="container"><g:message code="question.next"/></g:remoteLink></span>
</div>
<h1><g:message code="frame.title"/></h1>
<p>&nbsp</p>
<div class="body">
  <div class="dialog">
    <p><div style='width:600px; height:550px; border:solid; border-width:thin; position:absolute; left:50px' align="center">
    <div id="container">
      <heisig:frameCard frame="${frame}" hidden="true"/>
    </div>
    <div style='width:270px; height:50px; font-size: 11px; position:relative; top:50px; background-color:white;' align="center">
      <p style='position:relative; top:5px'><g:message code="frame.revealMessage"/></p>
    </div>
  </div>
  </div>
  //<div style='position:relative; top:5px'>
  //  <g:javascript src="frame.js"/>
  //  <g:form controller="story" action="save">
  //    <g:textArea id="story" name="text" value="${message(code: 'frame.enterStory')}" class="story" onfocus="prepareForEntry('${message(code: 'frame.enterStory')}')" onblur="finishEntry('${message(code: 'frame.enterStory')}')"/>
  //    <g:submitButton name="what" value="${message(code: 'frame.saveStory')}" style="display:none" id="save"/>
  //  </g:form>
  //</div>
</div>
</body>
</html>
