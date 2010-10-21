<html>
<head>
  <title>Butato Panto</title>
  <meta name="layout" content="main"/>
  <style type="text/css" media="screen">

  #nav {
    margin-top: 20px;
    margin-left: 30px;
    width: 228px;
    float: left;

  }

  .homePagePanel * {
    margin: 0px;
  }

  .homePagePanel .panelBody ul {
    list-style-type: none;
    margin-bottom: 10px;
  }

  .homePagePanel .panelBody h1 {
    text-transform: uppercase;
    font-size: 1.1em;
    margin-bottom: 10px;
  }

  .homePagePanel .panelBody {
    background: url(images/leftnav_midstretch.png) repeat-y top;
    margin: 0px;
    padding: 15px;
  }

  .homePagePanel .panelBtm {
    background: url(images/leftnav_btm.png) no-repeat top;
    height: 20px;
    margin: 0px;
  }

  .homePagePanel .panelTop {
    background: url(images/leftnav_top.png) no-repeat top;
    height: 11px;
    margin: 0px;
  }

  h2 {
    margin-top: 15px;
    margin-bottom: 15px;
    font-size: 1.2em;
  }

  #pageBody {
    margin-left: 280px;
    margin-right: 20px;
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
        <li class="controller"><g:link controller="review" action="manage"><g:message code="navigation.frameTraining"/></g:link></li>
        <li class="controller"><g:link controller="question"><g:message code="navigation.question"/></g:link></li>
      </ul>
      <ul>
        <li class="controller"><g:link controller="mastery" action="listByChapter" id="1"><g:message code="navigation.frameManagement"/></g:link></li>
        <li class="controller"><g:link controller="vocable"><g:message code="navigation.vocable"/></g:link></li>
        <li class="controller"><g:link controller="studylist"><g:message code="navigation.studylist"/></g:link></li>
      </ul>
      <ul>
        <li><a class="home" href="${createLink(uri: '/system.gsp')}"><g:message code="navigation.system"/></a></li>
      </ul>
    </div>
    <div class="panelBtm">
    </div>
  </div>

</div>
<div id="pageBody">
  <h1>よこそう</h1>

  <p>Japanisch ist leicht. Japanisch macht Spaß. Sieh selbst!</p>

  <p>Hier kannst Du Deine Vokabeln <g:link controller="vocable">verwalten</g:link> und <g:link controller="question">lernen</g:link>.</P>

  <P>楽しんで&#x3002</p>
</div>
</body>
</html>