<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<html>
<head>
    <jsp:include page="/WEB-INF/views/client/template/head.jsp"/>
    <meta name="robots" content="noindex,nofollow">
    <meta name="title" content="Ошибка || Vova Coffee">
    <title>Ошибка || Vova Coffee</title>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/client/template/navbar.jsp"/>

<!-- ERROR -->
<div class="container-fluid width">
    <section id="error">
        <div class="row error text-shadow color-red">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                ${text_error}
            </div>
        </div>
    </section>
</div>

<jsp:include page="/WEB-INF/views/client/template/footer.jsp"/>
</body>
</html>
