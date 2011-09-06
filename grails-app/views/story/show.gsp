<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Story for ${frame.kanji}</title>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='story.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='main.css'/>"/>
  <g:javascript src="story.js"/>
</head>

<body>
<div id="storyBox">
  <div id="storyEdit" style="display:none;">
    <g:form name="saveStory" url="[action:'save', id: frame.id]">
      <textarea name="storyText" rows="12" cols="55">${storyText}</textarea>
      <g:hiddenField name="uriToShowAfterSave" value="$uriToShowAfterSave"/>
      <div class="control">
        <div class="buttonBar">
          <input type="submit" id="storyEdit_save" value="${g.message(code: 'story.save-button.text')}" title="${g.message(code: 'story.save-button.tooltip')}"/>
          <input type="button" id="storyEdit_cancel" value="${g.message(code: 'story.cancel-button.text')}" name="cancel" title="${g.message(code: 'story.cancel-button.tooltip')}" onclick="cancelEditStory()"/>
        </div>
      </div>
    </g:form>
  </div>

  <div id="storyView" style="display:block;" onclick="startEditStory()">
    <g:set var="initialText" value="${storyText ?: g.message(code: 'story.dialog.clickToEdit')}"/>
    <g:set var="boxTitle" value="${g.message(code: 'story.dialog.clickToEdit')}"/>
    <textarea id="storyDisplay" title="${boxTitle}" rows="12" cols="55">${initialText}</textarea>
  </div>
</div>
</body>
</html>