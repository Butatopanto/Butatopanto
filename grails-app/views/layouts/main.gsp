<html>
<head>
  <title><g:layoutTitle default="豚とパンと"/></title>
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}"/>
  <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon"/>
  <g:layoutHead/>
  <g:javascript library="application"/>
</head>
<body>
<div id="spinner" class="spinner" style="display:none;">
  <img src="${resource(dir: 'images', file: 'spinner.gif')}" alt="Spinner"/>
</div>
<table style="border:none">
  <tr>
    <td width="60px"><a href="http://code.google.com/p/butatopanto/"><img src="${resource(dir: 'images', file: 'logoSmall.png')}" alt="ButatoPanto" border="0"/></a></td>
    <td style="vertical-align: middle; font-size:30px">豚とパンと</td>
  </tr>
</table>
<g:layoutBody/>
</body>
</html>