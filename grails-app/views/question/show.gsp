<%@ page import="net.butatopanto.vocable.Question" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <link rel="stylesheet" href="/css/question.css" />
        <g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="show" action="show">Nächste Frage anzeigen</g:link></span>
        </div>
        <div class="body">
            <h1>Wie sagt man auf Japanisch?</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="question.meaning.label" default="Meaning" /></td>
                            <td valign="top" id="meaning" class="value">${questionInstance?.vocable?.meaning}</td>
                        </tr>
                        <tr class="prop">
                          <td valign="top" class="name"><g:message code="question.kana.label" default="Kana" /></td>
                          <td valign="top" id="kana" class="value" style="visibility:hidden">${questionInstance?.vocable?.kana}</td>
                        </tr>
                        <tr class="prop">
                          <td valign="top" class="name"><g:message code="question.kanji.label" default="Kanji" /></td>
                          <td valign="top" id="kanji" class="value" style="visibility:hidden">${questionInstance?.vocable?.kanji}</td>
                       </tr>
                     </tbody>
                </table>
            </div>
            <div class="buttons">
              <g:javascript>
                function solve() {
                   document.getElementById('kana').style.visibility = 'visible';
                   document.getElementById('kanji').style.visibility = 'visible';
                   document.getElementById('meaning').style.visibility = 'visible';
                }
              </g:javascript>
             <g:form>
                <g:hiddenField name="vocableId" value="${questionInstance?.vocable?.id}" />
                <input class="solve" type="button" value="Auflösen" onclick='solve()'>
             </g:form>
            </div>
         </div>
    </body>
</html>
