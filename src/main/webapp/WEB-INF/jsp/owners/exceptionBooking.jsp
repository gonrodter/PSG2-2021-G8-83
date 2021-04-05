<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<petclinic:layout pageName="errorReserva">

    <spring:url value="/resources/images/pets.png" var="petsImage"/>
    <img src="${petsImage}"/>

    <h2>Lo sentimos</h2>

    <body>Tiene una reserva activa para su mascota.</body>
    <body>Pruebe a reservar cuando su reserva haya finalizado. Muchas gracias</body>

</petclinic:layout>