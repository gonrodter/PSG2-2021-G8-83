<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<petclinic:layout pageName="causes">
    <h2>
        <c:if test="${cause['new']}">Nueva Causa</c:if>
    </h2>
    <form:form modelAttribute="cause" class="form-horizontal" id="add-cause-form">
        <div class="form-group has-feedback">
            <form:hidden path="isClosed"/>
             
            <petclinic:inputField label= "Nombre" name="name"/>
            <petclinic:inputField label= "Descripcion" name="description"/>
            <!-- <petclinic:inputField label= "Cantidad Solicitada" name="budgetTarget"/> -->
            <petclinic:inputField label="Organizacion" name="organization"/>
            <div class="form-group">
                <label class="col-sm-2 control-label">Cantidad Solicitada </label>
                <div class="col-sm-10">
                    <input type="number" step="any" id="budgetTarget" name="budgetTarget" value="${cause.budgetTarget }">
                </div>
            </div>
            
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${cause['new']}">
                        <button class="btn btn-default" type="submit">Añadir Causa</button>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>