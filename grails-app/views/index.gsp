<html>
<head>
  <title>Butato Panto</title>
  <meta name="google-site-verification" content="bT2c7HWLGGLeeOVxe1eN18ld0k_MVYR2gvmMCFo-XnE"/>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'index.css')}"/>
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'button.css')}"/>
</head>
<body>
<div id="pageBody">
  <div id="introText">
    <h1 class='japanese'>よこそう</h1>
    <p><g:message code="home.firstSteps.fun"/> <g:message code="home.firstSteps.begin"/> <span class='japanese'>楽しんで&#x3002</span></p>
  </div>
  <g:render template="/action" model="[controller:'assembleReview', color:'green', kanji:'学', message:'navigation.frameTraining']"/>
  <g:render template="/action" model="[controller:'mastery', color:'blue', kanji:'話', message:'navigation.frameManagement']"/>
  <g:render template="/action" model="[controller:'flashcard', color:'red', kanji:'格', message:'navigation.flashcardStatus']"/>
</div>
</body>
</html>