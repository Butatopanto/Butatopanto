<html>
    <head>
        <title><g:layoutTitle default="豚とパンと" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>
        <p style="font-size:40px; padding-left:20px; padding-top:10px">  豚と パンと</p>
        <g:layoutBody />
    </body>
</html>