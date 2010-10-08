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
        <g:sortableColumn property="id" title="${message(code: 'frameReview.id.label', default: 'Id')}"/>
        <th><g:message code="frameReview.frame.label" default="Frame"/></th>
        <g:sortableColumn property="passed" title="${message(code: 'frameReview.passed.label', default: 'Passed')}"/>
        <g:sortableColumn property="failed" title="${message(code: 'frameReview.failed.label', default: 'Failed')}"/>
        <g:sortableColumn property="box" title="${message(code: 'frameReview.box.label', default: 'Box')}"/>
        <g:sortableColumn property="lastUpdated" title="${message(code: 'frameReview.lastUpdated.label', default: 'Last Updated')}"/>
      </tr>
      </thead>
      <tbody>
      <g:each in="${frameReviewInstanceList}" status="i" var="frameReviewInstance">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
          <td><g:link action="show" id="${frameReviewInstance.id}">${fieldValue(bean: frameReviewInstance, field: "id")}</g:link></td>
          <td>${fieldValue(bean: frameReviewInstance, field: "frame")}</td>
          <td>${fieldValue(bean: frameReviewInstance, field: "passed")}</td>
          <td>${fieldValue(bean: frameReviewInstance, field: "failed")}</td>
          <td>${fieldValue(bean: frameReviewInstance, field: "box")}</td>
          <td><g:formatDate date="${frameReviewInstance.lastUpdated}"/></td>
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
