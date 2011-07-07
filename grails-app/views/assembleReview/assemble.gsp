<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <link rel="stylesheet" href="<g:createLinkTo dir='css' file='menu.css'/>"/>
    <link rel="stylesheet" href="<g:createLinkTo dir='css' file='review.css'/>"/>
    <link rel="stylesheet" href="<g:createLinkTo dir='css' file='button.css'/>"/>
    <g:javascript library="prototype"/>
    <title><g:message code="review.assemble.title"/></title>
</head>

<body>
<div class="nav">
    <menu:render without='assembleReview'/>
</div>

<h1><g:message code="review.assemble.chapterselection"/></h1>
<br/>

<div class="body">
    <g:render template="/flashMessage"/>
    <div class="dialog">
        <div class='content'>
            <div id="container" style="padding: 5px">
                <g:each in="${session.chapters.sort({it.chapterNumber}) }" status="i" var="${chapter}">
                    <heisig:chapterSelector chapter="${chapter}"/>
                </g:each>
                <div class="nav" style='position:absolute; left:0; bottom: 0; width:775px'>
                    <g:if test="${dueFrames}">
                        <span class="menuButton"><g:link class="practice" action="startDue"><g:message
                                code="review.assemble.due"/></g:link></span>
                    </g:if>
                    <g:if test="${dueSelected}">
                        <span class="menuButton"><g:link class="practice"
                                                         action="startDueFramesFromSelectedChapter"><g:message
                                    code="review.assemble.startSelectedDue"/></g:link></span>
                    </g:if>
                    <g:if test="${chaptersSelected}">
                        <span class="menuButton"><g:link class="practice" action="startSelectedChapters"><g:message
                                code="review.assemble.selected"/></g:link></span>
                    </g:if>
                </div>
            </div>

            <div style="position: absolute; left: 0; top: 560px;">
                <g:form action="startRange">
                    <g:textField name="from" value="${g.message(code:'review.practiceRange.from')}" onclick="this.select()"/>
                    <g:textField name="to" value="${g.message(code:'review.practiceRange.to')}" onclick="this.select()"/>
                    <g:submitButton name="practice" id='practice' class="medium gray button"
                                    value="${g.message(code:'review.practiceRange.submit')}"/>
                </g:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>