<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css/kanji' file='menu.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='review.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='flashcard.css'/>"/>
  <title><g:message code="flashcard.status.title"/></title>
</head>
<body>
<div class="nav">
  <menu:home/>
  <menu:chapterList/>
  <menu:assembleReview/>
</div>
<h1><g:message code="flashcard.status.heading"/></h1>
<p>&nbsp;</p>
<div class="body">
  <div class="dialog">
    <div style='width:800px; height:550px; border:solid; border-width:thin; position:absolute; left:50px'>
      <div id="container" style="padding: 5px">
        <div style="padding-left: 30px">
          <g:each in="${boxes}" status="i" var="${box}">
            <div class="boxselector">
              <p style="font-size:20px"><flashcard:romanNumber number="${box.number}"/></p>
              <p><g:message code="review.assemble.dueCount" args='["${box.dueKanji}"]'/></p>
              <p><g:message code="flashcard.status.known" args='["${box.totalKanji- box.dueKanji}"]'/></p>
            </div>
          </g:each>
        </div>
        <flashcard:renderStatus boxes="${boxes}"/>
      </div>
    </div>
  </div>
</div>
</body>
</html>