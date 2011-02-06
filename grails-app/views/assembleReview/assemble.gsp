<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='menu.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='review.css'/>"/>
  <g:javascript library="prototype"/>
  <title><g:message code="review.assemble.title"/></title>
</head>
<body>
<div class="nav">
  <menu:home/>
  <menu:chapterList/>
  <menu:backToPractice/>
  <menu:status/>
</div>
<h1><g:message code="review.assemble.chapterselection"/></h1>
<br/>
<div class="body">
  <g:render template="/flashMessage"/>
  <div class="dialog">
    <div style='width:800px; height:550px; border:solid; border-width:thin; position:absolute; left:50px'>
      <div id="container" style="padding: 5px">
        <g:each in="${session.chapters.sort({it.chapterNumber}) }" status="i" var="${chapter}">
          <heisig:chapterSelector chapter="${chapter}"/>
        </g:each>
        <div class="nav" style='position:absolute; left:0px; bottom: 0px; width:775px'>
          <g:if test="${dueFrames}">
            <span class="menuButton"><g:link class="practice" action="startDue"><g:message code="review.assemble.due"/></g:link></span>
          </g:if>
          <g:if test="${dueSelected}">
            <span class="menuButton"><g:link class="practice" action="startDueFramesFromSelectedChapter"><g:message code="review.assemble.startSelectedDue"/></g:link></span>
          </g:if>
          <g:if test="${chaptersSelected}">
            <span class="menuButton"><g:link class="practice" action="startSelectedChapters"><g:message code="review.assemble.selected"/></g:link></span>
          </g:if>
        </div>
      </div>

      <div style="position: absolute; left: 0px; top: 560px;">
        <g:form action="startRange">
          <g:textField name="from" value="${g.message(code:'review.practiceRange.from')}"/>
          <g:textField name="to" value="${g.message(code:'review.practiceRange.to')}"/>
          <g:submitButton name="practice" id='practice' value="${g.message(code:'review.practiceRange.submit')}"/>
        </g:form>
      </div>
    </div>
  </div>
</div>
</body>
</html>