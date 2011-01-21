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
    width: 15em
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
      </ul>
      <ul>
        <li><g:link controller="question"><g:message code="navigation.question"/></g:link></li>
        <li><g:link controller="vocable"><g:message code="navigation.vocable"/></g:link></li>
        <li><g:link controller="studylist"><g:message code="navigation.studylist"/></g:link></li>
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
  <div class="padded">
    <g:link controller="review">
      <div class="activityselector" style="background-color: #90ee90; border-color: green;">
        <div style="font-size: 4em;">
          覚
        </div>
        <g:message code="home.firstSteps.study"/>
      </div>
    </g:link>
  </div>

  <div class="padded" style="margin-left: 1em;">
    <g:link controller="mastery">
      <div class="activityselector" style="background-color:#add8e6; border-color:#00008b;">
        <div style="font-size: 4em;">
          運
        </div>
        <g:message code="home.firstSteps.manage"/>
      </div>
    </g:link>
  </div>

</div>
</body>
</html>