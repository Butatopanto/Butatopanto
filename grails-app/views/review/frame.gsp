<%@ page import="butatopanto.kanji.Frame" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:javascript library="prototype"/>
  <title>Kennst Du das Kanji?</title>
</head>
<body>
<div class="nav">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
</div>
<h1><g:message code="frame.title"/></h1>
<p>&nbsp</p>
<div class="body">
  <div class="dialog">
    <div style='width:800px; height:550px; border:solid; border-width:thin; position:absolute; left:50px' align="center">
      <div id="container">
        <heisig:frameCard frame="${frame}" hidden="true"/>
        <heisig:interaction frame="${frame}" hidden="true"/>
      </div>
      <div style='position:absolute; right:7px; bottom:108px'>
        <g:javascript src="frame.js"/>
        <g:formRemote name="saveStory" url="[ controller: 'story', action: 'save', params: [ kanji:frame.kanji ]]" after="finishEntry('${message(code: 'frame.enterStory')}')">
          <g:textArea id="story" name="story" value="${message(code: 'frame.enterStory')}" class="story" onfocus="prepareForEntry('${message(code: 'frame.enterStory')}')"/>
          <input type="submit" name="what" value="${message(code: 'frame.saveStory')}" style="display:none" id="save"/>
        </g:formRemote>
      </div>
    </div>
  </div>
</div>
</body>
</html>
