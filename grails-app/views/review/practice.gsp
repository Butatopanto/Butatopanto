<%@ page import="butatopanto.kanji.Frame" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='menu.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='review.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='story.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='button.css'/>"/>
  <g:javascript library="jquery" plugin="jquery"/>
  <g:javascript src="jquery-hotkeys/jquery.hotkeys.js"/>
  <g:javascript library="prototype"/>
  <jqui:resources/>
  <g:javascript>
    var confirmKey= '${message(code: "frame.reviewResult.confirmKey")}';
    var declineKey= '${message(code: "frame.reviewResult.declineKey")}';

    function click(elementId) {
      jQuery("#"+elementId).click();
    }

    function registerHotkey(key, elementId) {
      jQuery(document).bind("keydown", key, function(){
        click(elementId)
      });
    }

    jQuery(document).ready(function() {
      registerHotkey('space', 'card');
      registerHotkey(confirmKey, 'confirmButton');
      registerHotkey(declineKey, 'declineButton');
    });
  </g:javascript>
  <g:javascript>
    function openStoryDialog(title) {
      var offsetX = parseInt(jQuery('#showStory').css("margin-left").replace("px", ""));
      var dialogX = jQuery('#showStory').position().left + offsetX;
      var offsetY = parseInt(jQuery('#showStory').css("margin-top").replace("px", ""));
      var dialogY = jQuery('#showStory').position().top + offsetY;
      jQuery('#currentStory').load('/ButatoPanto/review/currentStory').dialog({title: title, draggable:false,width: 240, height: 320, position: [dialogX, dialogY]});
    }

    function closeStoryDialog() {
      jQuery('#currentStory').dialog('close');
    }
  </g:javascript>
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

<div id="currentStory"></div>
</body>
</html>
