<%@ page import="butatopanto.kanji.Frame" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='menu.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='review.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='story.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='button.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='js/windows_js/themes' file='story.css'/>"/>
  <g:javascript library="prototype"/>
  <g:javascript src="prototype/scriptaculous.js?load=builder,effects"/>
  <g:javascript src="livepipe/livepipe.js"/>
  <g:javascript src="livepipe/hotkey.js"/>
  <g:javascript src="practice.js"/>
  <g:javascript>
    var confirmKey= '${message(code: "frame.reviewResult.confirmKey")}';
    var declineKey= '${message(code: "frame.reviewResult.declineKey")}';
  </g:javascript>
  <g:javascript src="hotkeys.js"/>
  <g:javascript src="cardnavigation.js"/>
  <g:javascript src="protolicious/event.simulate.js"/>
  <g:javascript src="windows_js/effects.js"/>
  <g:javascript src="windows_js/window.js"/>
  <g:javascript src="windows_js/window_effects.js"/>
  <title><g:message code="frame.title"/></title>
</head>

<body>
<g:render template="/navigation"/>
<h1><g:message code="frame.title"/></h1>

<p>&nbsp</p>

<div class="yui3-g">
  <div class="yui3-u-1">
    <div class='content'>
      <div id="container" class="yui3-g">
        <heisig:practiceTablet frame="${frame}" hidden="true"/>
      </div>
    </div>
  </div>
</div>
</body>
</html>
