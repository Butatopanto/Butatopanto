<g:link controller="$controller">
  <g:set var="color"><g:include controller="color" params="${[controller:controller]}"/></g:set>
  <div class="button huge ${color} mainpagebutton">
    <div class="japanese iconicletter">
      <g:message code="navigation.welcome.${controller}.kanji"/>
    </div>

    <div class="descriptivetext">
      <g:message code="navigation.welcome.${controller}.text"/>
    </div>
  </div>
</g:link>