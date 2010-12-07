<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css/kanji' file='menu.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='review.css'/>"/>
  <g:javascript library="prototype"/>
  <title><g:message code="review.assemble.title"/></title>
</head>
<body>
<div class="nav">
  <menu:home/>
  <menu:chapterList/>
  <menu:backToPractice/>
</div>
<h1><g:message code="review.assemble.chapterselection"/></h1>
<p>&nbsp</p>
<div class="body">
  <div class="dialog">
    <div style='width:800px; height:550px; border:solid; border-width:thin; position:absolute; left:50px'>
      <div id="container" style="padding: 5px">
        <div>
          <g:each in="${session.chapters.sort({it.chapterNumber}) }" status="i" var="${chapter}">
            <div class="chapterselector">
              <heisig:chapterSelector chapter="${chapter}"/>
            </div>
          </g:each>
          <div class="nav" style='position:absolute; left:0px; bottom: 0px; width:775px'>
            <g:if test="${kanjiDue}">
              <span class="menuButton"><g:link class="practice" action="startDue"><g:message code="review.assemble.due"/></g:link></span>
            </g:if>
            <g:if test="${chaptersSelected}">
              <span class="menuButton"><g:link class="practice" action="startSelectedChapters"><g:message code="review.assemble.selected"/></g:link></span>
            </g:if>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>