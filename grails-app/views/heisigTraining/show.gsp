<%@ page import="butatopanto.kanji.Kanji" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="/css/question.css"/>
  <g:javascript src="heisigTraining.js"/>
  <title>Kennst Du das Kanji?</title>
</head>
<body>
<div class="nav">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
  <span class="menuButton"><g:link class="show" action="show" id="${params.id}"><g:message code="question.next"/></g:link></span>
</div>
<h1><g:message code="heisigTraining.title"/></h1>
<p>&nbsp</p>
<div class="body">
  <div class="dialog">
    <table>
      <tbody>
      <tr class="prop">
        <td valign="top" class="name" style="text-align:center; font-size:20"><h3>${kanji?.meaning}</h3></td>
      </tr>
      <tr class="prop">
        <td valign="top" class="value" style="padding-top:20px; text-align:center; font-size:70">
          <button type="button" class="solve" id='character' style="width:100px; height:100px; font-size:70px" onclick='reveal("${kanji.character}")'>?</button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>
