<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<html>
<head>
    <jsp:include page="/WEB-INF/views/client/template/head.jsp"/>
    <meta name="robots" content="noindex,nofollow">
    <meta name="title" content="Тестовой сайт || Vova Coffee">
    <title>Тестовой сайт || Vova Coffee</title>
</head>
<body>
<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/client/template/navbar.jsp"/>

<!-- CHECKOUT -->
<div class="container-fluid width">
    <section id="checkout">
        <div class="row checkout">
            <div class="col-xs-10 col-xs-offset-1 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                <div class="alert alert-info" role="alert">
                    Здравствуй, уважаемый пользователь!<br><br>
                    Спешим сообщить Вам, что это <b>тестовой сайт</b>! Реальными продажами кофе мы не
                    занимаемся.<br><br>
                    С уважением, команда <b>Vova Coffee</b>.<br>
                </div>
            </div>
        </div>
    </section>
</div>

<!-- FOOTER -->
<jsp:include page="/WEB-INF/views/client/template/footer.jsp"/>
</body>
</html>
