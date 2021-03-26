<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error">

    <spring:url value="/resources/images/errorimage.png" var="petsImage"/>
    <img style = "width: 20%" src="${petsImage}"/>

    <h2>Something happened...</h2>

    <p>${exception.message}</p>

</petclinic:layout>
