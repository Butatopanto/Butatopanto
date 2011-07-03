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
    <td width="60px"><menu:createHomeLink><div class="japanese homeicon"
                                               style="padding:0.5em; padding-left: 0.5em">豚</div></menu:createHomeLink>
    </td>
    <td style="vertical-align: middle; font-size:30px;" class='japanese'>とパンと</td>
    <td>
      <sec:securityLink/>
      <g:if test="${org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils.ifAllGranted('ROLE_ADMIN')}">
        <div>
          <g:link controller="user"><g:message code="navigation.administration"/></g:link>
          |
          <a class="home" href="${createLink(uri: '/system.gsp')}"><g:message code="navigation.system"/></a>
        </div>
      </g:if>
    </td>
  </tr>
</table>
<g:layoutBody/>
<g:render template="/footer"/>
</body>
</html>