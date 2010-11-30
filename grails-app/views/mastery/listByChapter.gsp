<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css/kanji' file='menu.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='mastery.css'/>"/>
  <g:javascript library="prototype"/>
  <title>Kapitel ${current}</title>
</head>
<body>
<div class="nav">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
  <span class="menuButton"><g:link class="assemble" controller="review" action="assemble"><g:message code="menu.assembleReview"/></g:link></span>
</div>
<div style="left: 10px; position: relative">
  <div id="navigationHeader" style="position:absolute; top: 0px; left: 15px">
    <h1 class="overviewColumn" style="text-align: center">Kapitel ${current}</h1>
    <g:if test="${previous}">
      <a href="${previous}" style="position: absolute; left: 0px; top: 15px">zurück</a>
    </g:if>
    <g:if test="${next}">
      <a href="${next}" style="position: absolute; right: 0px; top: 10px;">weiter</a>
    </g:if>
  </div>
  <p>&nbsp</p>
  <div class="body" style="position: relative; top: 30px">
    <div class="dialog">
      <div class="overviewColumn" style='height:550px; border:solid; border-width:thin'>
        <div id="container" style="padding: 5px">
          <div>
            <g:each in="${masteredFrames}" status="i" var="${masteredFrame}">
              <div class="selector">
                <div title="${masteredFrame.frame.keyword}" class="${masteredFrame.cssClass}">${masteredFrame.frame.kanji}</div>
              </div>
            </g:each>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>