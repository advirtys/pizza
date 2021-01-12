<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib prefix="mtf" uri="http://java.sun.com/jsp/jstl/fmt" %>

<mtf:setLocale value="${locale}" scope="session"/>

<mtf:bundle basename="locale">
    <mtf:message key="home" var="home_title" />
    <mtf:message key="pizza" var="pizza_title" />
    <mtf:message key="sushi" var="sushi_title" />
    <mtf:message key="drinks" var="drinks_title" />
    <mtf:message key="contacts" var="contacts_title" />
    <mtf:message key="basket" var="basket_title" />

    <mtf:message key="slogan_header_1" var="sloganHeader1" />
    <mtf:message key="slogan_header_2" var="sloganHeader2" />

    <mtf:message key="login" var="login_title" />
    <mtf:message key="password" var="password_title" />
    <mtf:message key="enter" var="enter_title" />
    <mtf:message key="check_in" var="check_in_title" />

    <mtf:message key="price" var="price_title" />
    <mtf:message key="add_cart" var="add_cart_title" />
    <mtf:message key="composition" var="composition_title" />

    <mtf:message key="slogan_footer" var="sloganFooter" />
    <mtf:message key="marka" var="marka_title" />

    <mtf:message key="our_contacts" var="our_contacts" />
    <mtf:message key="contacts_desc" var="contacts_desk" />

    <mtf:message key="hello" var="hello" />
    <mtf:message key="orders" var="orders" />
    <mtf:message key="settings" var="settings" />
    <mtf:message key="exit" var="exit" />

    <mtf:message key="control_panel" var="control_panel" />
    <mtf:message key="users" var="users_title" />
    <mtf:message key="products" var="products_title" />
    <mtf:message key="role" var="role_title" />

    <mtf:message key="save" var="save_title" />
    <mtf:message key="delete" var="delete_title" />

</mtf:bundle>

<h:head  title="${users_title}"/>

<script src="${pageContext.request.contextPath}/js/control.js">

</script>


    <main class="content">
        <div class="nav">
            <ul>
                <li><a href="${pageContext.request.contextPath}/do/">${home_title}</a></li>
                <li><a href="${pageContext.request.contextPath}/do/users-control">${users_title}</a></li>
                <li><a href="${pageContext.request.contextPath}/do/products-control">${products_title}</a></li>
                <li><a href="${pageContext.request.contextPath}/do/orders-control">${orders}</a></li>
                <li><a href="${pageContext.request.contextPath}/do/logout">${exit}</a></li>
                <li><h:locale /></li>
            </ul>
        </div>

        <br>
        <h1 class="h1_title">${users_title}:</h1>
        <br>
        <div class="control">
            <table>
                <tr>
                    <th><p>${login_title}</p></th>
                    <th><p>Email</p></th>
                    <th><p>${role_title}</p></th>
                    <th></th>
                    <th></th>
                </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><p>${user.login}</p></td>
                    <td><p>${user.email}</p></td>
                    <form action="${pageContext.request.contextPath}/do/update-user" method="post">
                        <input type="hidden" name="update_user_id" value="${user.id}">
                        <td>
                            <select name="new_role" onchange="isSelected(this.value);">
                                <option value="${user.role}" selected><p>${user.role}</p></option>
                                <option value="admin">admin</option>
                                <option value="user">user</option>
                                <option value="guest">guest</option>
                            </select>
                        </td>
                        <td>
                            <input type="submit" value="${save_title}" />
                        </td>
                    </form>
                    <form action="${pageContext.request.contextPath}/do/delete-user" method="post">
                        <td>
                            <input type="hidden" name="delete_user" value="${user.id}">
                            <input type="submit" value="${delete_title}" />
                        </td>
                    </form>
                </tr>
            </c:forEach>
            </table>
        </div>

<h:footer company="${marka_title}" slogan="${sloganFooter}" />