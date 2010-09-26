<%@ page import="butatopanto.kanji.Frame" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:javascript library="prototype"/>
  <title>Welche Kanji willst Du lernen?</title>
</head>
<body>
<div class="nav">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
  <g:if test="${session.review}">
    <span class="menuButton"><g:link class="return" action="practice"><g:message code="menu.returnToPractice"/></g:link></span>
  </g:if>
</div>
<h1>Welche Kanji willst Du lernen?</h1>
<p>&nbsp</p>
<div class="body">
  <div class="dialog">
    <div style='width:800px; height:550px; border:solid; border-width:thin; position:absolute; left:50px' align="center">
      <div id="container">
        <div><g:each in="${lessonProgress}" status="i" var="${progress}">
          <P>Lesson ${progress.lesson.number}: ${progress.activeFrameIds.size()} von ${progress.lesson.frameIds.size()}
          <g:link action="addLesson" id="${progress.lesson.number}">Hinzufügen</g:link></P>
        </g:each></div>
       <P><g:link action="start">Lernen starten</g:link></P> 
      </div>
    </div>
  </div>
</body>
</html>