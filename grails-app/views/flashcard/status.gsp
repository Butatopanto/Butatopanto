<%@ page import="butatopanto.learning.FlashcardTagLib" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='menu.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='review.css'/>"/>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='flashcard.css'/>"/>
    <link rel="stylesheet" href="<g:createLinkTo dir='css' file='button.css'/>"/>
  <title><g:message code="flashcard.status.title"/></title>
  <g:javascript base="https://www.google.com" src="/jsapi"></g:javascript>
  <g:javascript>
    google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChart);
    function drawChart() {
      var data = new google.visualization.DataTable();
    ${new FlashcardTagLib().generateDataScript(boxes: boxes, dataVariable: 'data')}
    var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
chart.draw(data, {
  chartArea:{left:50,top:50,width:"710",height:"75%"},
  width:780, height: 450,
  legend:'bottom',
  colors:['#FF9900','#0FF80F'],
  hAxis: {title: '${g.message(code: "flashcard.chart.boxLegend")}'}  ,
        vAxis: {title: 'Kanji', maxValue:100}
      });
      google.visualization.events.addListener(chart, 'select', function(e) {
        var item = chart.getSelection()[0];
        var clickedDue = item.column == 1;
        if (clickedDue) {
          var clickedColumn = item.row != null;
          if (clickedColumn) {
            var boxnumber = item.row + 1;
            window.location = "${g.createLink(controller: 'flashcard', action: 'startBox')}" + "/" + boxnumber;
          }
          else { //clicked Legend
            window.location = "${g.createLink(controller: 'flashcard', action: 'startDue')}";
          }
        }
        var clickedMastered = item.column == 2;
        if (clickedMastered) {
          window.location = "${g.createLink(controller: 'flashcard', action: 'startMastered')}";
        }
      });
    }
  </g:javascript>
</head>
<body>
<g:render template="/navigation"/>
<h1><g:message code="flashcard.status.heading"/></h1>
<br/>
<div class="body">
  <g:render template="/flashMessage"/>
  <div class="dialog">
    <div class='content'>
      <div id="container" style="padding: 5px">
        <div style="width:750px;  margin-left: auto; margin-right: auto; padding-left:50px;padding-bottom:80px">
          <g:each in="${boxes}" status="i" var="${box}">
            <g:link action="startBox" id="${box.number}" elementId="box${box.number}" class="selector boxselector" title="${g.message(code: 'flashcard.status.dueTime', args: [box.daysUntilDue])}">
              <g:set var="header" value="${flashcard.romanNumber(number:box.number)}"/>
              <g:set var="comment1" value="${g.message(code:'review.assemble.dueCount', args:[box.dueKanji])}"/>
              <g:set var="comment2" value="${g.message(code:'flashcard.status.known', args:[box.masteredKanji])}"/>
              <g:render template="/selectorContent" model="${[header:header, firstComment:comment1, secondComment: comment2]}"/>
            </g:link>
          </g:each>
        </div>
        <div id="chart_div"></div>
      </div>
    </div>
  </div>
</div>
</body>
</html>