<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='menu.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='mastery.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='flashcard.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='button.css'/>"/>
  <g:javascript library="jquery" plugin="jquery"/>
  <jqui:resources/>
  <g:javascript src="jquery-mousewheel/jquery.mousewheel.js"/>
  <g:javascript>
    function showNextKanji() {
      window.location = "${mastery.linkForNextKanji([navigation: navigation])}";
    }
    function showPreviousKanji(){
      window.location = "${mastery.linkForPreviousKanji([navigation: navigation])}";
    }
    function scrollByWheel(e, delta) {
      var down = delta < 0;
      if (down){
        showNextKanji();
      }
      else{
        showPreviousKanji();
      }
    }
    jQuery(document).mousewheel(scrollByWheel);
  </g:javascript>
  <g:javascript>
    function openStoryDialog(title, url) {
      jQuery('#currentStory').load(url).dialog({ width: 240, height: 320, zIndex: 100,title: title});
    }
  </g:javascript>
  <title><g:message code='mastery.current-chapter' args="${[navigation.chapterNumber]}"/></title>
</head>

<body>
<g:render template="/navigation"/>

<h1><g:message code="mastery.title"/></h1>

<g:render template="/flashMessage"/>

<div class="yui3-g main-column">
  <div id="previousChapters" class="yui3-u-5-12">
    <mastery:previousChapters navigation="${navigation}"/>
  </div>

  <div id="currentChapter" class="yui3-u-1-8" style="cursor:default">
    <mastery:currentChapter navigation="${navigation}"/>
  </div>

  <div id="nextChapters" class="yui3-u-5-12">
    <mastery:nextChapters navigation="${navigation}"/>
  </div>
</div>

<div class="yui3-g">
  <div class="yui-u-1">
    <div class="main-column main-area" style="margin-top:1em">
      <div>
        <g:each in="${navigation.getVisibleFrames()}" status="i" var="${masteredFrame}">
          <div class="kanjiselector selector">
            <g:set var="uriToShowAfterSave" value="${request.forwardURI - request.contextPath + '?' + request.queryString}"/>
            <g:set var="storyLink"
                   value="${createLink(controller: 'story', action: 'show', id:masteredFrame.frame.number, params:[uriToShowAfterSave:uriToShowAfterSave])}"/>
            <g:set var="storyTitle"
                   value="${message(code: 'chapterList.story.title', args: [masteredFrame.frame.keyword, masteredFrame.frame.kanji])}"/>
            <div onclick='openStoryDialog("${storyTitle}", "${storyLink}")'
                 id="${masteredFrame.frame.kanji}"
                 title="${masteredFrame.frame.keyword} (${masteredFrame.frame.number})"
                 class="${masteredFrame.cssClass} japanese selector">${masteredFrame.frame.kanji}</div>
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

<div class="yui3-g">
  <div class="yui-u-1">
    <g:form action="activate">
      <g:textField name="from" value="${g.message(code:'mastery.activation.from')}" onclick="this.select()"/>
      <g:textField name="to" value="${g.message(code:'mastery.activation.to')}" onclick="this.select()"/>
      <g:submitButton name="activate" id='activate' class="medium gray button"
                      value="${g.message(code:'mastery.activation.submit')}"/>
    </g:form>
  </div>
</div>

<div class="yui3-g">
    <div class="yui-u-1">
        <g:form action="deactivate">
            <g:textField name="from" value="${g.message(code:'mastery.deactivation.from')}" onclick="this.select()"/>
            <g:textField name="to" value="${g.message(code:'mastery.deactivation.to')}" onclick="this.select()"/>
            <g:submitButton name="activate" id='activate' class="medium gray button" value="${g.message(code:'mastery.deactivation.submit')}"/>
        </g:form>
    </div>
</div>

<div id="currentStory">
</div>
</body>
</html>