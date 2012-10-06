<div id="menu">
    <g:set var="group">tabs</g:set>
    <nav:eachItem group="$group">
        <g:set var="active">${active ? '_active' : ""}</g:set>
        <g:set var="color"><g:include controller="color" params="${[controller:controller]}"/></g:set>
        <g:link controller="$controller" action="$action" params="${[nocache: new Date().time]}">
            <div class="large button ${color}${active}" style="font-weight: bold">
                <g:message code="navigation.$group.$title"/>
            </div>
        </g:link>
    </nav:eachItem>
</div>