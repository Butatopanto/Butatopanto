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
      <div class="control">
        <div class="buttonBar">
          <input type="submit" id="storyEdit_save" value="Save changes" title="Save/Update story"/>
          <input type="button" id="storyEdit_cancel" value="Cancel" name="cancel" title="Cancel changes" onclick="cancelEditStory()"/>
        </div>
      </div>
    </g:form>
  </div>

  <div id="storyView" style="display:block;" onclick="startEditStory()">
    <g:set var="initialText" value="${storyText ?: '[click here to enter your story]'}"/>
    <g:set var="boxTitle" value="Click to edit your story"/>
    <textarea id="storyDisplay" title="${boxTitle}" rows="12" cols="55">${initialText}</textarea>
  </div>
</div>
</body>
</html>