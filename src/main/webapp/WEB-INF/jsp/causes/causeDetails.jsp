<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="cause">

    <h2>
        <th>Informacion de la causa</th>
    </h2>

    <table id="causesDetails" class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${cause.name}" /></b></td>
        </tr>
        <tr>
            <th>Descripcion</th>
            <td><c:out value="${cause.description}" /></td>
        </tr>
        <tr>
            <th>Cantidad obtenida</th>
            <td><c:out value="${cause.budgetTarget}" /></td>
        </tr>
        <tr>
            <th>Organizacion</th>
            <td><c:out value="${cause.organization}" /></td>
        </tr>
        <tr>
            <th>Estado</th>
            <td>
                <c:if test="${cause.isClosed }">
                    <c:out value="Cerrada"></c:out>
                </c:if>
                <c:if test="${!cause.isClosed }">
                 	<c:out value="Abierta"></c:out>
                </c:if>
            </td>
        </tr>
    </table>

    <br />
    <h2>
        <th>Donaciones</th>
    </h2>

    <table id="donationsTable" class="table table-striped">
        <thead>
            <tr>
                <th>Nombre donante</th>
                <th>Fecha donacion</th>
                <th>Cantidad donada</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${donations}" var="donation">
                <tr>
                    <td><c:out value="${donation.client}" /></td>
                    <td><c:out value="${donation.date}" /></td>
                    <td><c:out value="${donation.amount}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</petclinic:layout>