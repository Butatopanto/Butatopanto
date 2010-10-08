<%@ page import="butatopanto.kanji.FrameReview" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'frameReview.label', default: 'FrameReview')}"/>
  <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>
<body>
<div class="nav">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
</div>
<div class="body">
  <h1><g:message code="default.list.label" args="[entityName]"/></h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <div class="list">
    <table>
      <thead>
      <tr>
        <g:sortableColumn property="frame.id" title="${message(code: 'frameReview.frame.label', default: 'Frame')}"/>
        <g:sortableColumn property="frame.kanji" title="${message(code: 'frameReview.frame.kanji', default: 'Kanji')}"/>
        <g:sortableColumn property="frame.meaning" title="${message(code: 'frameReview.frame.meaning', default: 'Meaning')}"/>
        <g:sortableColumn property="passed" title="${message(code: 'frameReview.passed.label', default: 'Passed')}"/>
        <g:sortableColumn property="failed" title="${message(code: 'frameReview.failed.label', default: 'Failed')}"/>
        <g:sortableColumn property="box" title="${message(code: 'frameReview.box.label', default: 'Box')}"/>
        <g:sortableColumn property="lastUpdated" title="${message(code: 'frameReview.lastUpdated.label', default: 'Last Review')}"/>
        <g:sortableColumn property="dateCreated" title="${message(code: 'frameReview.dateCreated.label', default: 'Added')}"/>
      </tr>
      </thead>
      <tbody>
      <g:each in="${frameReviewInstanceList}" status="i" var="frameReviewInstance">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
          <td>${fieldValue(bean: frameReviewInstance.frame, field: "id")}</td>
          <td>${fieldValue(bean: frameReviewInstance.frame, field: "kanji")}</td>
          <td>${fieldValue(bean: frameReviewInstance.frame, field: "meaning")}</td>
          <td>${fieldValue(bean: frameReviewInstance, field: "passed")}</td>
          <td>${fieldValue(bean: frameReviewInstance, field: "failed")}</td>
          <td>${fieldValue(bean: frameReviewInstance, field: "box")}</td>
          <td><g:formatDate date="${frameReviewInstance.lastUpdated}" format="dd-MM-yyyy HH:mm"/></td>
          <td><g:formatDate date="${frameReviewInstance.dateCreated}" format="dd-MM-yyyy HH:mm"/></td>
        </tr>
      </g:each>
      </tbody>
    </table>
  </div>
  <div class="paginateButtons">
    <g:paginate max="20" total="${frameReviewInstanceTotal}"/>
  </div>
</div>
</body>
</html>
