<%@ page import="butatopanto.kanji.Frame" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='review.css'/>"/>
  <g:javascript library="prototype"/>
  <g:javascript src="prototype/scriptaculous.js?load=builder,effects"/>
  <g:javascript src="livepipe/livepipe.js"/>
  <g:javascript src="livepipe/window.js"/>
  <style type="text/css">
  .storyWindow {
    background-color: #cccccc;
    padding: 10px;
    border: 1px solid #333;
  }
  </style>
  <script>
    document.observe('dom:loaded', function() {
        var story = new Control.Modal($('story'), {
        overlayOpacity: 0.75,
        className: 'storyWindow',
        fade: true
      });
    });
  </script>
</head>
<body class="control">
<p>
<table cellpadding="0" cellspacing="0" width="100%" class="api_table">
  <tbody>
  <tr class="even"><td class="name first"><b><a href="/ButatoPanto/images/buta_icon_large.png" id="story">Modal Window</a></b></td></tr>
  </tbody>
</table>
</p>
</body>
</html>
