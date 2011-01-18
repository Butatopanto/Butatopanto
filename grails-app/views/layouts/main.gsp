<html>
<head>
  <title><g:layoutTitle default="豚とパンと"/></title>
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}"/>
  <link rel="shortcut icon" href="${resource(dir: 'images', file: 'buta-favicon.ico')}" type="image/x-icon"/>
  <g:layoutHead/>
  <g:javascript library="application"/>
</head>
<body>
<div id="spinner" class="spinner" style="display:none;">
  <img src="${resource(dir: 'images', file: 'spinner.gif')}" alt="Spinner"/>
</div>
<table style="border:none; width:100%">
  <tr>
    <td width="60px"><menu:createHomeLink><img src="${resource(dir: 'images', file: 'logoSmall.png')}" alt="ButatoPanto" border="0"/></menu:createHomeLink></td>
    <td style="vertical-align: middle; font-size:30px">豚とパンと</td>
    <td style="horizontal-align: right">
      <sec:securityLink/>
    </td>
  </tr>
</table>
<g:layoutBody/>
</body>
</html>