<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="localizacion">

    <spring:url value="/resources/images/localizacion.png" var="petsImage"/>
    <img src="${petsImage}"/>

    <h2>Esta es la localizacion</h2>

    <p>${exception.message}</p>

</petclinic:layout>
