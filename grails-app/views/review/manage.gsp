<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='review.css'/>"/>
  <g:javascript library="prototype"/>
  <title><g:message code="review.manage.title"/></title>
</head>
<body>
<div class="nav">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
  <g:if test="${session.review}">
    <span class="menuButton"><g:link class="practice" action="practice"><g:message code="menu.returnToPractice"/></g:link></span>
  </g:if>
</div>
<h1><g:message code="review.manage.chapterselection"/></h1>
<p>&nbsp</p>
<div class="body">
  <div class="dialog">
    <div style='width:800px; height:550px; border:solid; border-width:thin; position:absolute; left:50px'>
      <div id="container" style="padding: 5px">
        <div>
          <g:each in="${session.chapters}" status="i" var="${chapter}">
            <div class="chapterselector">
              <heisig:chapterSelector chapter="${chapter}"/>
            </div>
          </g:each>
          <div class="nav" style='position:absolute; left:0px; bottom: 0px; width:775px'>
            <g:if test="${kanjiDue}">
              <span class="menuButton"><g:link class="practice" action="startDue"><g:message code="review.manage.due"/></g:link></span>
            </g:if>
            <g:if test="${chaptersSelected}">
              <span class="menuButton"><g:link class="practice" action="startSelectedChapters"><g:message code="review.manage.selected"/></g:link></span>
            </g:if>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>