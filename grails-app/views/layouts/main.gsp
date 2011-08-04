<html>
<head>
    <title><g:layoutTitle default="豚とパンと"/></title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}"/>
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'buta-favicon.ico')}" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/3.3.0/build/cssgrids/grids-min.css">
    <g:layoutHead/>
    <g:javascript library="application"/>
</head>

<body style="width: 1000px; margin: 10 auto">
<div class="yui3-g">
    <div class="yui3-u-1-12">
        <menu:createHomeLink>
            <div class="japanese homeicon"
                 style="text-align: center;">豚</div>
        </menu:createHomeLink>
    </div>
    <div class="yui3-u align-stub" style="height: 45px; vertical-align: middle;"></div>
    <div class="yui3-u-5-24" style="vertical-align: middle;">
        <span style="font-size:30px; margin-left: 10px" class='japanese'>とパンと</span>
    </div>
    <div class="yui3-u-13-24">
        <!--empty-->
    </div>
    <div class="yui3-u-1-6">
        <sec:securityLink/>
        <g:if test="${org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils.ifAllGranted('ROLE_ADMIN')}">
            <div>
                <g:link controller="user"><g:message code="navigation.administration"/></g:link>
                |
                <a class="home" href="${createLink(uri: '/system.gsp')}"><g:message code="navigation.system"/></a>
            </div>
        </g:if>
    </div>
</div>
<g:layoutBody/>
<g:render template="/footer"/>
</body>
</html>