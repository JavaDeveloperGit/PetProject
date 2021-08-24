<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<html>
<head>
    <jsp:include page="/WEB-INF/views/client/template/head.jsp"/>
    <meta name="robots" content="index,follow">
    <meta name="google-site-verification" content=""/>
    <meta name="description"
          content="Vova Coffee - магазин вкусного и ароматного кофе для Вас и Вашех друзей. Кофе - любимый напиток цивилизованного мира."/>
    <meta name="keywords"
          content="vovacoffee, vova coffee, интернет, магазин, вкусный, аромтный, кофе, купить, куплю, в Киеве, в Украине, Киев, Украина"/>
    <meta name="title" content="Vova Coffee || Лучший магазин кофе">
    <title>Vova Coffee || Лучший магазин кофе</title>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/client/template/home_navbar.jsp"/>

<!-- MAIN PHOTO -->
<jsp:include page="/WEB-INF/views/client/template/main_photo.jsp"/>

<!-- CATEGORIES -->
<jsp:include page="/WEB-INF/views/client/template/categories.jsp"/>

<!--SOME PRODUCTS-->
<jsp:include page="/WEB-INF/views/client/template/some_products.jsp"/>

<!-- DELIVERY -->
<jsp:include page="/WEB-INF/views/client/template/delivery.jsp"/>

<!-- PAYMENTS -->
<jsp:include page="/WEB-INF/views/client/template/payments.jsp"/>

<!-- CONTACTS -->
<jsp:include page="/WEB-INF/views/client/template/contacts.jsp"/>

<!-- FOOTER -->
<jsp:include page="/WEB-INF/views/client/template/footer.jsp"/>
</body>
</html>
