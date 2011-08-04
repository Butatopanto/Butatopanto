<nav:resources/>
<div id="menu">
    <g:set var="group">tabs</g:set>
    <nav:eachItem group="$group">
        <g:set var="active">${active ? '_active' : ""}</g:set>
        <g:set var="color"><g:include controller="color" params="${[controller:controller]}"/></g:set>
        <div class="large button ${color}${active}">
            <g:link controller="$controller" action="$action" style="color: inherit">
                <g:message code="navigation.$group.$title"/>
            </g:link>
        </div>
    </nav:eachItem>
</div>