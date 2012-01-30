<html>
<head>
    <title>Butato Panto</title>
    <meta name="robots" content="index, nofollow">
    <meta name="keywords" lang="en" content="Japanese,Heisig,Kanji,Study,Learn,Practice,Story,Leitner,Cards"/>
    <meta name="keywords" lang="de" content="Japanisch,Heisig,Kanji,Lernen,Üben,Geschichten,Lernkartei"/>
    <meta name="description" lang="en" content="Butatopanto is a browser based trainer for japanese vocabulary."/>
    <meta name="description" lang="de" content="Butatopanto ist ein Online-Vokabeltrainer für japanische Kanji."/>
    <meta name="google-site-verification" content="bT2c7HWLGGLeeOVxe1eN18ld0k_MVYR2gvmMCFo-XnE"/>
    <meta name="layout" content="main"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'index.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'button.css')}"/>
</head>

<body>
<div class="yui3-g">
    <div class="yui3-u-1-8">
        <!--empty-->
    </div>

    <div class="yui3-u-7-8" id="introText">
        <h1 class='japanese'>よこそう</h1>

        <p><g:message code="home.firstSteps.fun"/> <g:message code="home.firstSteps.begin"/> <span
                class='japanese'>楽しんで&#x3002</span></p>
    </div>
</div>

<div class="yui3-g" style="margin-top: 1em">
    <div class="yui3-u-1-3">
      <g:render template="/action" model="[controller:'mastery']"/>
    </div>
    <div class="yui3-u-1-3">
      <g:render template="/action" model="[controller:'assembleReview']"/>
    </div>
    <div class="yui3-u-1-3">
        <g:render template="/action" model="[controller:'flashcard']"/>
    </div>
</div>
</body>
</html>