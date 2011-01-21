<%@ page import="butatopanto.vocable.Vocable" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="/css/question.css"/>
  <title>Wie heißt es auf Japanisch?</title>
</head>
<body>
<div class="nav">
  <menu:home/>
  <span class="menuButton"><g:link class="learn" action="show" id="${params.id}"><g:message code="question.next"/></g:link></span>
</div>
<h1><g:message code="question.title"/></h1>
<p>&nbsp</p>
<div class="body">
  <div class="dialog">
    <table>
      <tbody>
      <tr class="prop">
        <td valign="top" class="name" style="text-align:center"><h3>${vocable?.meaning}</h3></td>
      </tr>
      <tr class="prop">
        <td valign="top" id="kana" class="value" style="text-align:center; visibility:hidden">${vocable?.kana}</td>
      </tr>
      <tr class="prop">
        <td valign="top" id="kanji" class="value" style="text-align:center; visibility:hidden">${vocable?.kanji}</td>
      </tr>
      </tbody>
    </table>
  </div>
  <div class="buttons">
    <g:javascript src="question.js"/>
    <g:form>
      <input class="solve" type="button" value="${message(code: 'question.solveKana')}" onclick='solveKana()'>
      <input class="solve" type="button" value="${message(code: 'question.solveKanji')}" onclick='solveKanji()'>
      <input class="solve" type="button" value="${message(code: 'question.solveAll')}" onclick='solve()'>
    </g:form>
  </div>
</div>
</body>
</html>
