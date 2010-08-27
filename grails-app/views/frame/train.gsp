<%@ page import="butatopanto.kanji.Frame" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="/css/question.css"/>
  <g:javascript src="frame.js"/>
  <g:javascript src="visibility.js"/>
  <g:javascript library="prototype"/>
  <title>Kennst Du das Kanji?</title>
</head>
<body>
<div class="nav">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
  <span class="menuButton"><g:remoteLink class="learn" action="train" id="${params.id}"><g:message code="question.next"/></g:remoteLink></span>
</div>
<h1><g:message code="frame.title"/></h1>
<p>&nbsp</p>
<div class="body">
  <div class="dialog">
    <p><div style='width:600px; height:550px; border:solid; border-width:thin; position:absolute; left:50px' align="center">
    <heisig:frameCard frame="${frame}"/>
    <div style='width:270px; height:50px; font-size: 11px; position:relative; top:50px; top-padding: 10px; background-color:white; valign:middle' align="center">
      <p style="valign:middle; top-padding: 10px; position:relative; top:5px"><g:message code="frame.revealMessage"/></p>
    </div>
  </div></p>
  </div>
</div>
</body>
</html>
