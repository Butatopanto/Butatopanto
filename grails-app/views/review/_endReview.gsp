<h1><g:message code='review.finished.congratulations'/></h1>
<h4><g:message code='review.progress.legend.right' args="${[review.rightIds.size()]}"/></h4>
<h4><g:message code='review.progress.legend.wrong' args="${[review.wrongIds.size()]}"/></h4>
<br/>
<g:if test="${review.wrongIds}">
  <g:form action="repeat" name="repeatWrongForm">
    <g:hiddenField name="kanji" value="${review.wrongIds}"/>
    <g:submitButton name="repeatWrong" id='repeatWrong' class="medium gray button" value="${g.message(code:'review.finished.repeatWrong')}"/>
  </g:form>
</g:if>
<g:form action="repeat" name="repeatAllForm">
  <g:hiddenField name="kanji" value="${review.wrongIds.plus(review.rightIds)}"/>
  <g:submitButton name="repeatAll" id='repeatAll' class="medium gray button" value="${g.message(code:'review.finished.repeatAll')}"/>
</g:form>
<g:form action="startNew" name="startNewForm">
  <g:submitButton name="startNew" id='startNew' class="medium gray button" value="${g.message(code:'review.finished.startNew')}"/>
</g:form>