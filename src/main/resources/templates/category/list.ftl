<#include '../layout/head.ftl'>
<div class="container">
    <input hidden value="${type}" id="type">
    <h3>
        <#switch type>
            <#case 1>
            学科分类
                <#break>
            <#case 2>
            图书分类
                <#break>
            <#case 3>
            行业分类
                <#break>
            <#case 4>
            中国产业分类
                <#break>
            <#case 5>
            编程语言分类
                <#break>
            <#default>
        </#switch>
    </h3>
    <table class="table" id="App"></table>
</div>
<script src="${rootContextPath}/app/category_list.js"></script>
<#include '../layout/foot.ftl'>