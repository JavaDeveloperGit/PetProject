<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<html>
<head>
    <jsp:include page="/WEB-INF/views/client/template/head.jsp"/>
    <meta name="robots" content="noindex,nofollow">
    <meta name="title" content="Авторизация || Vova Coffee">
    <title>Авторизация || Vova Coffee</title>
</head>
<body>

<!-- NAVBAR -->
<div class="nav-bar">
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <div class="navbar-brand text-shadow">
                    <a href="/">
                        <span class="nav-text-label color-green">Vova</span>
                        <span class="nav-text-label color-brown">Coffee</span>
                        <img class="nav-label" src="/resources/img/main_icon.png">
                    </a>
                </div>
            </div>
        </div>
    </nav>
</div>

<!-- AUTHORIZATION -->
<div class="container-fluid width">
    <section id="login">
        <div class="row login">
            <div class="col-xs-6 col-xs-offset-4 col-sm-4 col-sm-offset-4 col-md-4 col-md-offset-4 col-lg-4 col-lg-offset-4 col-xl-4 col-xl-offset-4 text-center">
                <div class="text-shadow">
                    <span class="authorization">Авторизация</span>
                </div>

                <c:if test="${param.error ne null}">
                    <div class="alert alert-info" role="alert">
                        Ошибка авторизации
                    </div>
                </c:if>

                <c:if test="${param.logout ne null}">
                    <div class="alert alert-info" role="alert">
                        Вы вышли из системы
                    </div>
                </c:if>

                <form class="form-signin" action="/login" method="post">
                    <input id="username" class="form-control" type="text" name="username" pattern="[A-Za-z0-9_]{5,50}"
                           placeholder="Введите логин, формат (A-Z, a-z, 0-9, _)" style="margin-top: 25px"
                           minlength="5" maxlength="50" autofocus required>
                    <input id="password" class="form-control" type="password" name="password"
                           pattern="[A-Za-z0-9]{6,50}" placeholder="Введите пароль, формат (A-Z, a-z, 0-9)"
                           style="margin-top: 25px" minlength="6" maxlength="50" required>
                    <button class="btn btn-success" type="submit" style="margin-top: 25px">Войти</button>
                </form>
            </div>
        </div>
    </section>
</div>

</body>
</html>
