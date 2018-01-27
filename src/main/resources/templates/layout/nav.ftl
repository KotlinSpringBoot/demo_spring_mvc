<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${rootContextPath}/index">Wiki</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        百科分类 <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${rootContextPath}/category/list/1">学科分类</a></li>
                        <li><a href="${rootContextPath}/category/list/2">图书分类</a></li>
                        <li><a href="${rootContextPath}/category/list/3">行业分类</a></li>
                        <li><a href="${rootContextPath}/category/list/4">中国产业分类</a></li>
                        <li><a href="${rootContextPath}/category/list/5">编程语言分类</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${rootContextPath}/user/list">用户管理</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${rootContextPath}/about">关于</a>
                </li>
                <li class="divider"></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                    ${(Session["currentUser"].username)!} <b
                            class="caret"></b>
                    </a>

                    <ul class="dropdown-menu">
                        <li class="divider"></li>
                        <li><a href="${rootContextPath}/register">注册</a></li>
                        <li><a href="${rootContextPath}/login">登录</a></li>
                        <li><a href="${rootContextPath}/logout">退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>