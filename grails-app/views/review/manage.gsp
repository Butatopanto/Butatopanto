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
            <div class="lessonselector">
              <heisig:chapterSelector chapter="${chapter}"/>
            </div>
          </g:each>
          <g:if test="${canContinue}">
            <div class="nav" style='position:absolute; width:775px; top:517px'>
              <span class="menuButton"><g:link class="practice" action="start">Alle</g:link></span>
            </div>
          </g:if>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>