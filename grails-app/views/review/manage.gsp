<%@ page import="butatopanto.kanji.Frame" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='review.css'/>"/>
  <g:javascript library="prototype"/>
  <title>Welche Kanji willst Du lernen?</title>
</head>
<body>
<div class="nav">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
  <g:if test="${session.review}">
    <span class="menuButton"><g:link class="practice" action="practice"><g:message code="menu.returnToPractice"/></g:link></span>
  </g:if>
</div>
<h1>Welche Kanji willst Du lernen?</h1>
<p>&nbsp</p>
<div class="body">
  <div class="dialog">
    <div style='width:800px; height:550px; border:solid; border-width:thin; position:absolute; left:50px'>
      <g:set var="activeFrameCount" value="${0}"/>
      <div id="container" style="padding: 5px">
        <div><g:each in="${lessonProgress}" status="i" var="${progress}">
          <g:set var="activeFrameCount" value="${activeFrameCount + progress.activeFrameIds.size()}"/>
          <P>Lesson ${progress.lesson.number}: ${progress.activeFrameIds.size()} von ${progress.lesson.frameIds.size()}
          <g:if test="${progress.activeFrameIds.size() != progress.lesson.frameIds.size()}">
            <g:link action="addLesson" id="${progress.lesson.number}">Hinzufügen</g:link>
          </g:if>
          </P>
        </g:each></div>
      </div>
      <g:if test="${activeFrameCount > 0}">
        <div class="nav" style='position:absolute; width:775px; top:517px'>
          <span class="menuButton"><g:link class="practice" action="start">Alle</g:link></span>
        </div>
      </g:if>
    </div>
  </div>
</div>
</body>
</html>