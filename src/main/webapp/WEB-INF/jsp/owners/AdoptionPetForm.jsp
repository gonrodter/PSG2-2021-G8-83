<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<petclinic:layout pageName="owners">
    <h2>Dar en adopción a <c:out value="${pet.name}"/></h2>
        <form:form modelAttribute="adoption" class="form-horizontal">
            <input type="hidden" name="id" value="${pet.id}"/>
            <div class="form-group has-feedback">
                <h5 style = "margin-left: 4%;">Escriba aqui una breve descripción de las necesidades de su mascota:</h5>
                <petclinic:inputField label="Necesidades de la mascota" name="text"/>
            </div>
            <button class="btn btn-default" type="submit">Confirmar</button>
        </form:form>
</petclinic:layout>
