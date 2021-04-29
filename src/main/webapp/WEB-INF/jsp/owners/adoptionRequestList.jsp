<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<petclinic:layout pageName="adoptionRequestList">
    <h2>Solicitudes de adopción para la mascota de nombre ${pet.name}</h2>
    
    
    <c:choose>
    
    	<c:when test="${fn:length(apps)==0}">
    		<h4>No hay solicitudes de adopción todavía</h4>
    	</c:when>
    	
    	<c:otherwise>
    		
    		<table id="adoptionsTable" class="table table-striped">
		        <thead>
		        <tr>
		            <th>Nombre del solicitante</th>
		            <th>Descripción</th>
		            <th></th>
		        </tr>
		        </thead>
		        <tbody>
		        <c:forEach items="${apps}" var="app">
		            <tr>
		                <td>
		                    <c:out value="${app.applicant.firstName} ${app.applicant.lastName}"/>
		                </td>
		                
		                <td>
		                    <c:out value="${app.description}"/>
		                </td>
		             	
		             	<td>
		             		<spring:url value="/application/{applicationId}/apply" var="apply">
						    	<spring:param name="applicationId" value="${app.id}"/>
						    </spring:url>
						    <a href="${fn:escapeXml(apply)}">Confirmar adopción</a>
		             	</td>
		             	
		            </tr>
		        </c:forEach>
		        
		        </tbody>
		    </table>
    		
    	</c:otherwise>
    
    </c:choose>
    
    
</petclinic:layout>