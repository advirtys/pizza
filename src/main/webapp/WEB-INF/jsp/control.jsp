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

</mtf:bundle>

<h:head  title="${control_panel}"/>
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
        <h1 class="h1_title">${control_panel}:</h1>
        <br>
        <div class="content2">
            ${welcome_control}
        </div>

<h:footer company="${marka_title}" slogan="${sloganFooter}" />