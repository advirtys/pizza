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

    <mtf:message key="order_description" var="order_description_title" />
    <mtf:message key="quantity" var="quantity_title" />
    <mtf:message key="order_date" var="order_date_title" />

</mtf:bundle>

<h:head  title="${orders}"/>

<header class="header">
    <div class="logo"></div>
    <div class="slogan"><p class="slogan2">${sloganHeader1}</p>
        <p class="slogan3">${sloganHeader2}</p></div>
    <div class="login">
        <c:choose>
            <c:when test="${user.role == 'guest'}">
                <form action="${pageContext.request.contextPath}/do/login" method="POST">
                    <c:if test="${welcome}" >
                        <p class="validate_r">${login_in}</p>
                    </c:if>
                    <c:if test="${validate}" >
                        <p class="validate_r">${registerValidate}</p>
                    </c:if>
                    <p class="login_p">${login_title}:</p>
                    <input type="text" name="login" />
                    <p class="login_p">${password_title}:</p>
                    <input type="password" name="password" />
                    <br/>
                    <p class="login_p"><input type="submit" value="${enter_title}" /> <a href="${pageContext.request.contextPath}/do/register">${check_in_title}</a></p>
                </form>
            </c:when>
            <c:when test="${user.role == 'user'}">
                <p class="login_p">${hello} ${user.login}!!!</p><br>
                <a href="${pageContext.request.contextPath}/do/user-orders">${orders}</a><br>
                <a href="${pageContext.request.contextPath}/do/profile">${settings}</a><br>
                <a href="${pageContext.request.contextPath}/do/logout">${exit}</a>
            </c:when>
            <c:when test="${user.role == 'admin'}">
                <p class="login_p">${hello} ${user.login}!!!</p><br>
                <a href="${pageContext.request.contextPath}/do/user-orders">${orders}</a><br>
                <a href="${pageContext.request.contextPath}/do/profile">${settings}</a><br>
                <a href="${pageContext.request.contextPath}/do/control">${control_panel}</a><br>
                <a href="${pageContext.request.contextPath}/do/logout">${exit}</a>
            </c:when>
        </c:choose>

        <h:locale />
    </div>
    <div class="nav">
        <ul>
            <li><a href="${pageContext.request.contextPath}/do/">${home_title}</a></li>
            <li><a href="${pageContext.request.contextPath}/do/product?type=pizza">${pizza_title}</a></li>
            <li><a href="${pageContext.request.contextPath}/do/product?type=sushi">${sushi_title}</a></li>
            <li><a href="${pageContext.request.contextPath}/do/product?type=drink">${drinks_title}</a></li>
            <li><a href="${pageContext.request.contextPath}/do/contacts">${contacts_title}</a></li>
            <li><a href="${pageContext.request.contextPath}/do/basket">${basket_title}(${order.size})</a></li>
        </ul>
    </div>

</header><!-- .header-->
    <main class="content">
        <br>
        <h1 class="h1_title">${orders}:</h1>
        <br>
        <div class="content2">
            <table>
                <tr><th><p>${order_description_title}</p></th><th><p>${quantity_title}</p></th><th><p>${order_date_title}</p></th></tr>
            <c:forEach var="orderItem" items="${orderList}">
                <tr><td>${orderItem.description}</td><td>${orderItem.size}</td><td><p>${orderItem.date}</p></td></tr>
            </c:forEach>
            </table>
        </div>

<h:footer company="${marka_title}" slogan="${sloganFooter}" />