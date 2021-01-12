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

    <mtf:message key="welcome_control_panel" var="welcome_control" />
    <mtf:message key="delete" var="delete_title" />
    <mtf:message key="edit" var="edit_title" />
    <mtf:message key="add_product" var="add_product_title" />

</mtf:bundle>


<h:head  title="${products_title}"/>
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
        <h1 class="h1_title">${products_title}:</h1>
        <br>
        <div class="product">
            <img width="200" height="200" src="${pageContext.request.contextPath}/img/add.png" onclick="add();">
            <form action="${pageContext.request.contextPath}/do/add-product" method="get">
                <input class="product_btn" type="submit" value="${add_product_title}">
            </form>
        </div>
        <c:forEach var="product" items="${products}">
            <div class="product">
                <img width="200" height="200" src="${pageContext.request.contextPath}/show-img?img=${product.id}">
                <h2>"${product.title}"</h2>
                <p class="price">${price_title}: ${product.price0} тг</p>
                <p class="desc"><span>*** </span>${composition_title}<span> ***</span></p>
                <p class="desc2">${product.description}</p>
                <form action="${pageContext.request.contextPath}/do/edit-product" method="POST">
                    <input type="hidden" name="id" value="${product.id}">
                    <input class="product_btn" type="submit" value="${edit_title}">
                </form>
                <form action="${pageContext.request.contextPath}/do/delete-product" method="POST">
                    <input type="hidden" name="id" value="${product.id}">
                    <input class="product_btn" type="submit" value="${delete_title}">
                </form>
            </div>
        </c:forEach>

<h:footer company="${marka_title}" slogan="${sloganFooter}" />
