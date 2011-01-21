<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css/kanji' file='menu.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='review.css'/>"/>
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
        <div>
          <g:each in="${boxes}" status="i" var="${box}">
            <p>Box ${box.number}: ${box.dueKanji} due out of ${box.totalKanji}</p>
          </g:each>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>