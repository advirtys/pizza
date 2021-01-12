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

    <mtf:message key="order_description" var="order_description_title" />
    <mtf:message key="quantity" var="quantity_title" />
    <mtf:message key="order_date" var="order_date_title" />
    <mtf:message key="addressee" var="addressee_title" />
    <mtf:message key="new_order" var="new_order_title" />
    <mtf:message key="delivered_order" var="delivered_order_title" />
    <mtf:message key="all_order" var="all_order_title" />
    <mtf:message key="status_order" var="status_title" />

</mtf:bundle>


<h:head  title="${orders}"/>
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
        <h1 class="h1_title">${orders}:</h1>
        <br>
        <div class="content2">
            <form action="${pageContext.request.contextPath}/do/select-orders" method="post">
                ${new_order_title}
                <c:choose>
                    <c:when test="${!newCheck}" >
                        <input type="checkbox" name="select" value="new" onclick="this.form.submit()">
                    </c:when>
                    <c:when test="${newCheck}" >
                        <input type="checkbox" onclick="this.form.submit()" checked>
                    </c:when>
                </c:choose>
                ${delivered_order_title}
                <c:choose>
                    <c:when test="${!deliveredCheck}" >
                        <input type="checkbox" name="select" value="delivered" onclick="this.form.submit()">
                    </c:when>
                    <c:when test="${deliveredCheck}" >
                        <input type="checkbox" onclick="this.form.submit()" checked>
                    </c:when>
                </c:choose>
                ${all_order_title}
               <c:choose>
                        <c:when test="${!allCheck}" >
                            <input type="checkbox" name="select" value="all" onclick="this.form.submit()">
                        </c:when>
                        <c:when test="${allCheck}" >
                            <input type="checkbox" onclick="this.form.submit()" checked>
                        </c:when>
               </c:choose>

            </form>
            <table>
                <tr>
                    <th><p>${login_title}</p></th>
                    <th><p>${order_description_title}</p></th>
                    <th><p>${quantity_title}</p></th>
                    <th><p>${addressee_title}</p></th>
                    <th><p>${order_date_title}</p></th>
                    <th><p>${status_title}</p></th></tr>
                <c:forEach var="orderItem" items="${orderList}">
                    <tr><td>${orderItem.user.login}</td>
                        <td>${orderItem.description}</td>
                        <td>${orderItem.size}</td>
                        <td>${orderItem.address}</td>
                        <td>${orderItem.date}</td>
                        <td><form action="${pageContext.request.contextPath}/do/update-status" method="post">
                            <input type="hidden" name="orderId" value="${orderItem.id}">
                            <select name="status" onclick="this.form.submit()">
                                <c:choose>
                                    <c:when test="${orderItem.status == 1}">
                                        <option value="1">Доставлен</option>
                                    </c:when>
                                    <c:when test="${orderItem.status == 0}">
                                        <option value="0">Не доставлен</option>
                                    </c:when>
                                </c:choose>
                                        <option value="1">Доставлен</option>
                                        <option value="0">Не доставлен</option>
                            </select>
                        </form></td></tr>

                </c:forEach>
            </table>
        </div>

<h:footer company="${marka_title}" slogan="${sloganFooter}" />
