<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<petclinic:layout pageName="Application">
    <h2>Acoger en adopción a <c:out value="${adoption.pet.name}"/></h2>
        <form:form modelAttribute="application" class="form-horizontal">
            <div class="form-group has-feedback">
                <h5 style = "margin-left: 4%;">Escriba aquí una breve descripción de los cuidados que le dará su nueva mascota:</h5>
                <petclinic:inputField label="Cuidados de la mascota" name="description"/>
            </div>
            <button class="btn btn-default" type="submit">Enviar solicitud</button>
        </form:form>
</petclinic:layout>