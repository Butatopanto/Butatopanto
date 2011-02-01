<h1><g:message code='review.finished.congratulations'/></h1>
<h4><g:message code='review.progress.legend.right' args="${[review.rightIds.size()]}"/></h4>
<h4><g:message code='review.progress.legend.wrong' args="${[review.wrongIds.size()]}"/></h4>
<br/>
<%
flash.wrongKanji = review.wrongIds
flash.allKanji = review.wrongIds.plus(review.rightIds)
%>
<g:link action="repeatWrong">Repeat wrong Kanji</g:link>
<br/>
<g:link action="repeatAll">Repeat entire review</g:link>
<br/>
<g:link action="newReview">Start another review</g:link>