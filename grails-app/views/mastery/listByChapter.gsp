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
  <menu:home/>
  <menu:assembleReview/>
  <menu:backToPractice/>
</div>
<div style="left: 10px; position: relative">
  <div id="navigationHeader" style="position:absolute; top: 0px; left: 15px">
    <h1 class="overviewColumn" style="text-align: center">Kapitel ${navigation.chapterNumber}</h1>
    <mastery:previousChapter navigation="${navigation}" />
    <mastery:nextChapter navigation="${navigation}" />
  </div>
  <p>&nbsp</p>
  <div class="body" style="position: relative; top: 30px">
    <div class="dialog">
      <div class="overviewColumn" style='height:550px; border:solid; border-width:thin'>
        <div id="container" style="padding: 5px">
          <div>
            <g:each in="${navigation.getVisibleFrames()}" status="i" var="${masteredFrame}">
              <div class="selector">
                <div title="${masteredFrame.frame.keyword}" class="${masteredFrame.cssClass}">${masteredFrame.frame.kanji}</div>
              </div>
            </g:each>
            <mastery:overrun navigation="${navigation}"/>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>