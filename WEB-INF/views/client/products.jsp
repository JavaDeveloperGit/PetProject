<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<html>
<head>
    <jsp:include page="/WEB-INF/views/client/template/head.jsp"/>
    <meta name="description" content="Весь ассортимент товаров интернет магазина кофе Vova Coffee"/>
    <meta name="keywords" content="Ассортимент кофе магазина Vova Coffee"/>
    <meta name="title" content="Весь ассортимент кофе || Vova Coffee">
    <title>Весь ассортимент кофе || Vova Coffee</title>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/client/template/navbar.jsp"/>

<!-- All PRODUCTS -->
<jsp:include page="/WEB-INF/views/client/template/some_products.jsp"/>

<!-- FOOTER -->
<jsp:include page="/WEB-INF/views/client/template/footer.jsp"/>
</body>
</html>
