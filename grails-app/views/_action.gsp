<div class="padded">
    <g:link controller="${controller}">
        <g:set var="color"><g:include controller="color" params="${[controller:controller]}"/></g:set>
        <div class="button huge ${color}">
            <div class="japanese iconicletter">
                ${kanji}
            </div>

            <div class="descriptivetext">
                <g:message code="${message}"/>
            </div>
        </div>
    </g:link>
</div>