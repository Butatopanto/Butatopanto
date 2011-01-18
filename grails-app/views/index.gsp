<html>
<head>
  <title>Butato Panto</title>
  <meta name="layout" content="main"/>
  <style type="text/css" media="screen">

  #nav {
    margin-top: 2em;
    margin-left: 2.9em;
    width: 20.5em;
    float: left;
  }

  .homePagePanel * {
    margin: 0em;
  }

  .homePagePanel .panelBody ul {
    list-style-type: none;
    margin-bottom: 0.95em;
  }

  .homePagePanel .panelBody h1 {
    text-transform: uppercase;
    font-size: 1.1em;
    margin-bottom: 1em;
  }

  .homePagePanel .panelBody {
    background: url(images/leftnav_midstretch.png) repeat-y top;
    margin: 0em;
    padding: 1.4em;
  }

  .homePagePanel .panelBtm {
    background: url(images/leftnav_btm.png) no-repeat top;
    height: 2em;
    margin: 0em;
  }

  .homePagePanel .panelTop {
    background: url(images/leftnav_top.png) no-repeat top;
    height: 1em;
    margin: 0em;
  }

  h2 {
    margin-top: 1.5em;
    margin-bottom: 1.5em;
    font-size: 1.2em;
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
    margin-top: 1em
  }
  </style>
</head>
<body>
<div id="nav">
  <div class="homePagePanel">
    <div class="panelTop">
    </div>
    <div class="panelBody">
      <ul>
        <li class="controller"><g:link controller="review" action="assemble"><g:message code="navigation.frameTraining"/></g:link></li>
        <li class="controller"><g:link controller="mastery"><g:message code="navigation.frameManagement"/></g:link></li>
      </ul>
      <ul>
        <li class="controller"><g:link controller="question"><g:message code="navigation.question"/></g:link></li>
        <li class="controller"><g:link controller="vocable"><g:message code="navigation.vocable"/></g:link></li>
        <li class="controller"><g:link controller="studylist"><g:message code="navigation.studylist"/></g:link></li>
      </ul>
      <g:if test="${org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils.ifAllGranted('ROLE_ADMIN')}">
        <ul>
          <li><g:link controller="user"><g:message code="navigation.administration"/></g:link></li>
          <li><a class="home" href="${createLink(uri: '/system.gsp')}"><g:message code="navigation.system"/></a></li>
        </ul>
      </g:if>
    </div>
    <div class="panelBtm">
    </div>
  </div>

</div>
<div id="pageBody">
  <h1>よこそう</h1>

  <p><g:message code="home.firstSteps.fun"/> <g:message code="home.firstSteps.begin"/> 楽しんで&#x3002</p>

  <div class="padded">
    <g:link controller="review">
      <div class="activityselector" style="background-color: #90ee90; border-color: green; height: 10em;">
        <g:message code="home.firstSteps.study"/>
      </div>
    </g:link>
  </div>

  <div class="padded">
    <g:link controller="mastery">
      <div class="activityselector" style="background-color:#add8e6; border-color:#00008b; height: 3em;">
        <g:message code="home.firstSteps.manage"/>
      </div>
    </g:link>
  </div>

</div>
</body>
</html>