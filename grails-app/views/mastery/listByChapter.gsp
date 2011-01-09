<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css/kanji' file='menu.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='mastery.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='js/windows_js/themes' file='story.css'/>"/>
  <g:javascript library="prototype"/>
  <g:javascript src="chapterlist.js"/>
  <g:javascript src="protowheel.js"/>
  <g:javascript src="windows_js/effects.js"/>
  <g:javascript src="windows_js/window.js"/>
  <g:javascript src="windows_js/window_effects.js"/>
  <g:javascript>
    function scrollByWheel(e) {
      var scrollCount = Event.wheel(e);
      var down = scrollCount < 0;
      if (down){
        window.location = "${mastery.linkForNextKanji([navigation: navigation])}";
      }
      else{
        window.location = "${mastery.linkForPreviousKanji([navigation: navigation])}";
      }
    }
    Event.observe(document, "mousewheel", scrollByWheel, false);
  </g:javascript>
  <title><g:message code='mastery.current-chapter' args="${[navigation.chapterNumber]}"/></title>
</head>
<body>
<div class="nav">
  <menu:home/>
  <menu:assembleReview/>
  <menu:backToPractice/>
</div>
<div style="left: 10px; position: relative">
  <div id="navigationHeader" style="position:absolute; top: 0px; left: 15px">
    <h1 class="main-column" style="position: relative; text-align: center"><g:message code='mastery.current-chapter' args="${[navigation.chapterNumber]}"/></h1>
    <mastery:previousChapter navigation="${navigation}"/>
    <mastery:nextChapter navigation="${navigation}"/>
  </div>
  <p>&nbsp</p>
  <div class="body" style="position: relative; top: 30px">
    <div class="dialog">
      <div class="main-column main-area">
        <div style="position: absolute; left: 0px; top: 0px; width: 765px; height: 550px">
          <g:each in="${navigation.getVisibleFrames()}" status="i" var="${masteredFrame}">
            <div class="selector">
              <g:set var="storyLink" value="${createLink(controller: 'story', action: 'show', id:masteredFrame.frame.number)}"/>
              <g:set var="storyTitle" value="${message(code: 'chapterList.story.title', args: [masteredFrame.frame.keyword, masteredFrame.frame.kanji])}"/>
              <div onclick='openStoryDialog(this, "${storyTitle}", "${storyLink}")' title="${masteredFrame.frame.keyword}" class="${masteredFrame.cssClass}">${masteredFrame.frame.kanji}</div>
            </div>
          </g:each>
        </div>
        <div class="flip-up icon">
          <mastery:flipUp navigation="${navigation}"/>
        </div>
        <div class="flip-down icon">
          <mastery:flipDown navigation="${navigation}"/>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>