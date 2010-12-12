<%@ page import="butatopanto.kanji.Frame" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css/kanji' file='menu.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='review.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='story.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='js/windows_js/themes' file='story.css'/>"/>
  <g:javascript library="prototype"/>
  <g:javascript src="prototype/scriptaculous.js?load=builder,effects"/>
  <g:javascript src="livepipe/livepipe.js"/>
  <g:javascript src="livepipe/window.js"/>
  <g:javascript src="livepipe/hotkey.js"/>
  <g:javascript src="practice.js"/>
  <g:javascript>
    var confirmKey= '${message(code: "frame.reviewResult.confirmKey")}';
    var declineKey= '${message(code: "frame.reviewResult.declineKey")}';
  </g:javascript>
  <g:javascript src="cardnavigation.js"/>
  <g:javascript src="protolicious/event.simulate.js"/>
  <g:javascript src="windows_js/effects.js"/>
  <g:javascript src="windows_js/window.js"/>
  <g:javascript src="windows_js/window_effects.js"/>
  <title><g:message code="frame.title"/></title>
</head>
<body>
<div class="nav">
  <menu:home />
  <menu:chapterList />
  <menu:assembleReview />
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
      <div style='position: absolute; top: 50px; left: 15px'>
        <div id="showStory" onclick="openStoryDialog(this, 'currentStory')"><b><br>Hier klicken,<br>um die Geschichte anzuzeigen</b></div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
