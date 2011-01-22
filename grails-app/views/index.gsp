<html>
<head>
  <title>Butato Panto</title>
  <meta name="layout" content="main"/>
  <style type="text/css" media="screen">

  #nav {
    margin-top: 2em;
    margin-left: 2.9em;
    width: 19.5em;
    float: left;
    border: 0.2em solid #e8e8e8;
    padding: 0.2em;
    border-radius: 0.4em 0.4em;
    box-shadow: 1px 1px 5px #222222;
    -webkit-box-shadow: 1px 1px 5px #222222; /**For Chrome/Safari*/
  }

  .activityselector ul {
    list-style-type: none;
    margin-bottom: 0.95em;
    text-align: left;
  }

  .activityselector .panelBody {
    margin: 0em;
    padding: 1.4em;
    font-size: 11px;
  }

  #pageBody {
    margin-left: 25.5em;
    margin-right: 2em;
  }

  .activityselector {
    display: table-cell;
    vertical-align: middle;
    font-size: 2em;
    border: 0.2em solid;
    border-radius: 0.7em 0.7em;
    text-align: center;
    width: 11em
  }

  .padded {
    margin-top: 1em;
    float: left;
  }
  </style>
</head>
<body>
<div id="nav">
  <div class="activityselector" style="background-color: #e8e8e8; border: 0em; border-radius: 0.2em 0.2em;">
    <div class="panelBody">
      <ul>
        <li><g:link controller="review" action="assemble"><g:message code="navigation.frameTraining"/></g:link></li>
        <li><g:link controller="mastery"><g:message code="navigation.frameManagement"/></g:link></li>
        <li><g:link controller="flashcard"><g:message code="navigation.flashcardStatus"/></g:link></li>
      </ul>
      <g:if test="${org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils.ifAllGranted('ROLE_ADMIN')}">
        <ul>
          <li><g:link controller="user"><g:message code="navigation.administration"/></g:link></li>
          <li><a class="home" href="${createLink(uri: '/system.gsp')}"><g:message code="navigation.system"/></a></li>
        </ul>
      </g:if>
    </div>
  </div>

</div>
<div id="pageBody">
  <h1>よこそう</h1>
  <p><g:message code="home.firstSteps.fun"/> <g:message code="home.firstSteps.begin"/> 楽しんで&#x3002</p>
  <g:render template="/action" model="[controller:'review', kanji:'習', message:'home.firstSteps.study', background: '#90ee90', border:'green']"/>
  <g:render template="/action" model="[controller:'mastery', kanji:'治', message:'home.firstSteps.manage', background: '#add8e6', border:'#00008b', style:'margin-left:1em']"/>
</div>
</body>
</html>