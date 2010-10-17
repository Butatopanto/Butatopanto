<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Story for ${frame.kanji}</title></head>
<body>
<div id="storybox">
  <div id="storyedit" style="display:none;">
    <textarea name="txtStory" id="frmStory" rows="12" cols="55"></textarea>
    <div class="controls valign">
      <div style="float:right;">
        <input type="submit" name="doUpdate" value="Save changes" title="Save/Update story"/>
        <input type="button" id="storyedit_cancel" value="Cancel" name="cancel" title="Cancel changes"/>
      </div>
      <div class="clear"></div>
    </div>
  </div>

  <div id="storyview">
    <div id="sv-textarea" class="bookstyle empty" title="Click to edit your story" style="display:block;">
      [ click here to enter your story ]</div>
    <div class="controls">
      <input type="image" name="doLearned" id="doLearned" value="" src="/images/2.0/study/restudy-button.gif" alt="Add to restudied list" title="Add kanji that you have relearned to a list for review later"/></div>
  </div>
</div>
</body>
</html>