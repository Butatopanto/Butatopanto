<%@ page import="net.butatopanto.vocable.Question" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <link rel="stylesheet" href="/css/question.css" />
        <g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
        <title>Wie heißt es auf Japanisch?</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="show" action="show">Nächste Frage anzeigen</g:link></span>
        </div>
        <h1>Wie heißt auf Japanisch?</h1>
        <p>&nbsp</p>
        <div class="body">
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top"class="name" style="text-align:center"><h3>${questionInstance?.vocable?.meaning}</h3></td>
                        </tr>
                        <tr class="prop">
                          <td valign="top" id="kana" class="value" style="text-align:center; visibility:hidden">${questionInstance?.vocable?.kana}</td>
                        </tr>
                        <tr class="prop">
                          <td valign="top" id="kanji" class="value" style="text-align:center; visibility:hidden">${questionInstance?.vocable?.kanji}</td>
                       </tr>
                     </tbody>
                </table>
            </div>
            <div class="buttons">
              <g:javascript>
                function solveKana() {
                   document.getElementById('kana').style.visibility = 'visible';
                }
                function solveKanji() {
                   document.getElementById('kanji').style.visibility = 'visible';
                }
                function solve() {
                    solveKana();
                    solveKanji();
                }
              </g:javascript>
             <g:form>
                <input class="solve" type="button" value="Zeige Kana" onclick='solveKana()'>
                <input class="solve" type="button" value="Zeige Kanji" onclick='solveKanji()'>
                <input class="solve" type="button" value="Zeige alles" onclick='solve()'>
             </g:form>
            </div>
         </div>
    </body>
</html>
