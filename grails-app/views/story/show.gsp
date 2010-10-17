<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Story for ${frame.kanji}</title>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='story.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='main.css'/>"/>
  <g:javascript src="story.js"/>
</head>

<body>
<div id="storybox">
  <div id="storyedit" style="display:none;">
    <g:form name="saveStory" url="[action:'save', id: frame.id]">
      <textarea name="storyText" id="frmStory" rows="12" cols="55">${storyText}</textarea>
      <div style="float:right;">
        <input type="submit" name="doUpdate" value="Save changes" title="Save/Update story"/>
        <input type="button" id="storyedit_cancel" value="Cancel" name="cancel" title="Cancel changes" onclick="cancelEditStory()"/>
      </div>
    </g:form>
  </div>

  <div id="storyview" style="display:block;" onclick="startEditStory()">
    <div id="story" class="bookstyle empty" title="Click to edit your story" style="display:block;">
      <g:if test="${storyText}">
        ${storyText}
      </g:if>
      <g:else>
        [ click here to enter your story ]
      </g:else>
    </div>
  </div>
</div>
</body>
</html>