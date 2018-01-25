<#include 'layout/head.ftl'>
<div class="container">
    <div class="col-sm-6 col-sm-offset-3">
        <div class="form-top">
            <div class="form-top-left">
                <h3>用户登录</h3>
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
            <button id="login-btn" type="button" class="btn btn-success login-btn"> 登录</button>
        </form>
    </div>
</div>
<script src="/app/login.js"></script>
<#include 'layout/foot.ftl'>