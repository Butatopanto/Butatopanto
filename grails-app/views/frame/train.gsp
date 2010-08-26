<%@ page import="butatopanto.kanji.Frame" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="/css/question.css"/>
  <g:javascript src="frame.js"/>
  <g:javascript src="visibility.js"/>
  <title>Kennst Du das Kanji?</title>
</head>
<body>
<div class="nav">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
  <span class="menuButton"><g:link class="show" action="train" id="${params.id}"><g:message code="question.next"/></g:link></span>
</div>
<h1><g:message code="frame.title"/></h1>
<p>&nbsp</p>
<div class="body">
  <div class="dialog">
    <p><div style='width:600px; height:550px; border:solid; border-width:thin; position:absolute; left:50px' align="center">
    <div style='width:270px; height:390px ;position:relative; top:50px; background-color:white;valign:middle' align="center" onclick='reveal("${frame.kanji}")'>
      <table height="100%">
        <tr height="10%">
          <td style="text-align: left; font-size:20px">${frame?.meaning}</td>
        </tr>
        <tr>
          <td id="character" height="100%" style="text-align: center; vertical-align:middle; font-size:100px">?</td>
        </tr>
        <tr height="10%">
          <td id="number" style="text-align: right; visibility: hidden; font-size:12px">${frame?.number}</td>
        </tr></table>
    </div>
    <div style='width:270px; height:50px; font-size: 11px; position:relative; top:50px; top-padding: 10px; background-color:white; valign:middle' align="center">
      <p style="valign:middle; top-padding: 10px; position:relative; top:5px"><g:message code="frame.revealMessage"/></p>
    </div>
  </div></p>
  </div>
</div>
</body>
</html>
