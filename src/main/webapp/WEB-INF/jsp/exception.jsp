<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<petclinic:layout pageName="error">

    <spring:url value="/resources/images/errorimage.png" var="petsImage"/>
    <img style = "width: 20%" src="${petsImage}"/>

    <h2>Algo ha ocurrido...</h2>

    <p>${exception.message}</p>

</petclinic:layout>
