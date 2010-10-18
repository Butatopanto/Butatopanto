<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='mastery.css'/>"/>
  <g:javascript library="prototype"/>
  <title><g:message code="review.manage.title"/></title>
</head>
<body>
<div class="nav">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
</div>
<div style="left: 10px; position: relative">
  <div id="navigationHeader" style="position:absolute; top: 0px; left: 15px">
    <h1 class="overviewColumn" style="text-align: center">Kapitel ${chapterNumber}</h1>
    <a href="${chapterNumber - 1}" style="position: absolute; left: 0px; top: 15px">zurück</a>
    <a href="${chapterNumber + 1}" style="position: absolute; right: 0px; top: 10px;">weiter</a>
  </div>
  <p>&nbsp</p>
  <div class="body" style="position: relative; top: 30px">
    <div class="dialog">
      <div class="overviewColumn" style='height:550px; border:solid; border-width:thin'>
        <div id="container" style="padding: 5px">
          <div>
            <g:each in="${masteredFrameList}" status="i" var="${masteredFrame}">
              <div class="selector">
                <div class="box${masteredFrame.box} ${masteredFrame.hasStory ? 'withStory' : 'withoutStory'} kanjibox">${masteredFrame.kanji}</div>
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