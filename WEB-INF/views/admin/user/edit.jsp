<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<html>
<head>
    <jsp:include page="/WEB-INF/views/admin/template/head.jsp"/>
    <meta name="robots" content="noindex,nofollow">
    <meta name="title" content="${user.name} || Vova Coffee">
    <title>${user.name} || Vova Coffee</title>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="/WEB-INF/views/admin/template/admin_navbar.jsp"/>

<!-- EDIT PERSON -->
<div class="container-fluid width">
    <section id="user">
        <div class="row admin-page">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                <div class="row section-name text-shadow">
                    <b><span class="color-green">${user.name}</span></b>
                </div>
            </div>

            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                <form action="/admin/update_user" method="post">
                    <input type="hidden" name="id" value="${user.id}">
                    <table class="table">
                        <tr>
                            <th>Имя:</th>
                            <td>
                                <input class="input-order" type="text" name="name"
                                       placeholder=" Введите имя" value="${user.name}"
                                       minlength="2" maxlength="50" required>
                            </td>
                        </tr>
                        <tr>
                            <th>Роль:</th>
                            <td>
                                <select class="input-order" name="role">
                                    <option value="${user.role.id}">${user.role.description}</option>
                                    <c:forEach items="${roles}" var="role">
                                        <c:if test="${role ne user.role}">
                                            <option value="${role.id}">${role.description}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>Логин:</th>
                            <td>
                                <input class="input-order" type="text" name="username" pattern="[A-Za-z0-9_]{5,50}"
                                       placeholder=" Введите логин, формат (A-Z, a-z, 0-9, _)" value="${user.username}"
                                       minlength="5" maxlength="50" required>
                            </td>
                        </tr>
                        <tr>
                            <th>Пароль:</th>
                            <td>
                                <input class="input-order" type="text" name="password" pattern="[A-Za-z0-9]{6,50}"
                                       placeholder=" Введите пароль, формат (A-Z, a-z, 0-9)" value="${user.password}"
                                       minlength="6" maxlength="50" required>
                            </td>
                        </tr>
                        <tr>
                            <th>Email:</th>
                            <td>
                                <input class="input-order" type="email" name="email" pattern="[A-Za-z0-9_.@]{5,50}"
                                       placeholder=" Введите электронную почту, формат (A-Z, a-z, 0-9, _, ., @)"
                                       value="${user.email}" minlength="5" maxlength="50" required>
                            </td>
                        </tr>
                        <tr>
                            <th>Телефон:</th>
                            <td>
                                <input id="phone" class="input-order" type="text" name="phone"
                                       placeholder=" Введите телефон" value="${user.phone}" required>
                            </td>
                        </tr>
                        <tr>
                            <th>ВКонтакте:</th>
                            <td>
                                <input class="input-order" type="text" name="vkontakte" pattern="[a-z0-9_/.]{5,50}"
                                       placeholder=" Введите адрес ВКонтакте, формат (a-z, 0-9, _, /, .)"
                                       value="${user.vkontakte}" minlength="5" maxlength="50">
                            </td>
                        </tr>
                        <tr>
                            <th>Facebook:</th>
                            <td>
                                <input class="input-order" type="text" name="facebook" pattern="[a-z0-9_/.]{5,50}"
                                       placeholder=" Введите адрес Facebook, формат (a-z, 0-9, _, /, .)"
                                       value="${user.facebook}" minlength="5" maxlength="50">
                            </td>
                        </tr>
                        <tr>
                            <th>Skype:</th>
                            <td>
                                <input class="input-order" type="text" name="skype" pattern="[A-Za-z0-9_.]{5,50}"
                                       placeholder=" Введите логин Skype, формат (A-Z, a-z, 0-9, _, .)"
                                       value="${user.skype}" minlength="5" maxlength="50">
                            </td>
                        </tr>
                        <tr>
                            <th>Описание:</th>
                            <td>
                                <textarea class="input-order textarea" type="text" name="description"
                                          placeholder=" Введите описание работника"
                                          maxlength="250">${user.description}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <button class="btn btn-success" type="submit"
                                        title="Обновить информацию о пользователи">Сохранить</button>
                                <button class="btn btn-info" type="reset" title="Сбросить введенные даные">Сброс
                                </button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </section>
</div>
</body>
</html>
