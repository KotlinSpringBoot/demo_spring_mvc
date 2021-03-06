<#include 'layout/head.ftl'>
<div class="container">
    <div class="col-sm-6 col-sm-offset-3">
        <div class="form-top">
            <div class="form-top-left">
                <h3>用户注册</h3>
                <p>请输入用户名密码:</p>
            </div>
        </div>
        <form id="login-form" role="form" class="form">
            <div class="form-group form-username">
                <input type="text"
                       name="username"
                       placeholder="用户名"
                       class="form-control col-sm-5"
                       id="form-username">
            </div>
            <div class="form-group form-password">
                <input type="password"
                       name="password"
                       placeholder="密码"
                       class="form-control col-sm-5"
                       id="form-password">
            </div>
            <#--将button的type从submit改为 button，因为submit会默认提交表单，而点击事件又绑定ajax，于是ajax请求就被cancel了。-->
            <button id="login-btn" type="button" class="btn btn-success login-btn">提交注册</button>
        </form>
    </div>
</div>
<script src="/app/register.js"></script>
<#include 'layout/foot.ftl'>