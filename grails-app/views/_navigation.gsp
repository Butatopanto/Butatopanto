<nav:resources/>
<div id="menu">
    <g:set var="group">tabs</g:set>
    <nav:eachItem group="$group">
        <g:set var="active">${active ? '_active' : ""}</g:set>
        <div class="large button gray${active}">
            <g:link controller="$controller" action="$action" style="color: inherit">
                <g:message code="navigation.$group.$title"/>
            </g:link>
        </div>
    </nav:eachItem>
</div>