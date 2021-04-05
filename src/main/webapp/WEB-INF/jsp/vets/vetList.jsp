<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<petclinic:layout pageName="vets">
    <h2>Veterinarios</h2>

    <table id="vetsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Especialidades</th>
            <th>Acciones</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vets.vetList}" var="vet">
            <tr>
                <td>
                    <c:out value="${vet.firstName} ${vet.lastName}"/>
                </td>
                <td>
                    <c:forEach var="specialty" items="${vet.specialties}">
                        <c:out value="${specialty.name} "/>
                    </c:forEach>
                    <c:if test="${vet.nrOfSpecialties == 0}">none</c:if>
                </td>
     
                <td>
                	<spring:url value="vets/edit/{vetId}" var="editUrl">
				    	<spring:param name="vetId" value="${vet.id}"/>
				    </spring:url>
				    <a href="${fn:escapeXml(editUrl)}">Editar Veterinario</a>
				    
				    <a style="margin-left:20px" href="/vets/${vet.id}/deleteVet">
                		<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                	</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="table-buttons">
        <tr>
            <td>

                <a href="<spring:url value="/vets.xml" htmlEscape="true" />">Ver como XML</a>

            </td>            
        </tr>
    </table>
    
    <a class="btn btn-default" href='<spring:url value="/vets/new" htmlEscape="true"/>'>Añadir Veterinario</a>
    
</petclinic:layout>
