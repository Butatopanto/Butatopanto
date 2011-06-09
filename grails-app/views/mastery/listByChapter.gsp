<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <link rel="stylesheet" href="<g:createLinkTo dir='css' file='menu.css'/>"/>
    <link rel="stylesheet" href="<g:createLinkTo dir='css' file='mastery.css'/>"/>
    <link rel="stylesheet" href="<g:createLinkTo dir='css' file='flashcard.css'/>"/>
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
    <menu:render without='chapterList'/>
</div>

<div style="left:10px; position: relative">
    <div class='main-column' style="position: absolute; left: 10px; top: 0px">
        <div id="previousChapters" style="width:350px;position:absolute;">
            <mastery:previousChapters navigation="${navigation}"/>
        </div>
        <div id="currentChapter" style="width:100px; position:absolute; top: 0px; left:355px; cursor:default">
            <div class="selector boxselector chapter">
                <p>${navigation.chapterNumber}</p>
            </div>
        </div>
        <div id="nextChapters" style="width:350px; position:absolute; top: 0px; left: 450px">
            <mastery:nextChapters navigation="${navigation}"/>
        </div>
    </div>
    <br/>

    <div class="body" style="position: absolute; top: 100px">
        <g:render template="/flashMessage"/>
        <div class="dialog">
            <div class="main-column main-area">
                <div style="position: absolute; left: 0px; top: 0px; width: 765px; height: 550px">
                    <g:each in="${navigation.getVisibleFrames()}" status="i" var="${masteredFrame}">
                        <div class="kanjiselector selector">
                            <g:set var="storyLink"
                                   value="${createLink(controller: 'story', action: 'show', id:masteredFrame.frame.number)}"/>
                            <g:set var="storyTitle"
                                   value="${message(code: 'chapterList.story.title', args: [masteredFrame.frame.keyword, masteredFrame.frame.kanji])}"/>
                            <div onclick='openStoryDialog(this, "${storyTitle}", "${storyLink}")'
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

                <div style="position: absolute; left: 0px; top: 560px;">
                    <g:form action="activate">
                        <g:textField name="from" value="${g.message(code:'mastery.activation.from')}"/>
                        <g:textField name="to" value="${g.message(code:'mastery.activation.to')}"/>
                        <g:submitButton name="activate" id='activate'
                                        value="${g.message(code:'mastery.activation.submit')}"/>
                    </g:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>