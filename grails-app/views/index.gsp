<html>
<head>
  <title>Butato Panto</title>
  <meta name="google-site-verification" content="bT2c7HWLGGLeeOVxe1eN18ld0k_MVYR2gvmMCFo-XnE"/>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'index.css')}"/>
</head>
<body>
<div id="pageBody">
  <div id="introText">
    <h1 class='japanese'>よこそう</h1>
    <p><g:message code="home.firstSteps.fun"/> <g:message code="home.firstSteps.begin"/> <span class='japanese'>楽しんで&#x3002</span></p>
  </div>
  <g:render template="/action" model="[controller:'assembleReview', kanji:'習', message:'navigation.frameTraining', background: '#9BFF9B', border:'#007D00']"/>
  <g:render template="/action" model="[controller:'mastery', kanji:'治', message:'navigation.frameManagement', background: '#C2B5FF', border:'#220D80']"/>
  <g:render template="/action" model="[controller:'flashcard', kanji:'見', message:'navigation.flashcardStatus', background: '#FCCFAC', border:'#7F3B06']"/>
  <g:if test="${org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils.ifAllGranted('ROLE_ADMIN')}">
    <div>
      <g:link controller="user"><g:message code="navigation.administration"/></g:link>
      |
      <a class="home" href="${createLink(uri: '/system.gsp')}"><g:message code="navigation.system"/></a>
    </div>
  </g:if>
</div>
</body>
</html>