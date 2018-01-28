<#include '../layout/head.ftl'>
<div class="container">
    <h1>Exception:</h1>
    <h3>URL: ${url!}</h3>
    <h3>errorMessage: ${errorMessage!}</h3>
    <code>
        <#list stackTrace! as line>
            ${line}
        </#list>
    </code>
</div>
<#include '../layout/foot.ftl'>