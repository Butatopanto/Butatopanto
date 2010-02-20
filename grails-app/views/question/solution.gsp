
<%@ page import="net.butatopanto.vocable.Question" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="show" action="show">Nächste Frage anzeigen</g:link></span>
        </div>
        <div class="body">
            <h1>Genau!</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="question.meaning.label" default="Meaning" /></td>
                            <td valign="top" class="value">${questionInstance?.vocable?.meaning}</td>
                        </tr>
                           <tr class="prop">
                            <td valign="top" class="name"><g:message code="question.kana.label" default="Kana" /></td>
                            <td valign="top" class="value">${questionInstance?.vocable?.kana}</td>
                          </tr>
                          <tr class="prop">
                            <td valign="top" class="name"><g:message code="question.kanji.label" default="Kanji" /></td>
                            <td valign="top" class="value">${questionInstance?.vocable?.kanji}</td>
                          </tr>
                      </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
