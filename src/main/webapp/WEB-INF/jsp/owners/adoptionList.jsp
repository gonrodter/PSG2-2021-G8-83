<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<petclinic:layout pageName="adoptions">
    <h2>Adopciones</h2>
    
    <c:choose>
    
    	<c:when test="${fn:length(adoptions.adoptionsList)==0}">
    		<h4>No hay propuestas de adopción disponibles</h4>
    	</c:when>
    	
    	<c:otherwise>
    
    <table id="adoptionsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Mascota</th>
            <th>Especie</th>
            <th>Dueño</th>
            <th>Descripción</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${adoptions.adoptionsList}" var="adoption">
            <tr>
                <td>
                    <c:out value="${adoption.pet}"/>
                </td>
                
                <td>
                    <c:out value="${adoption.pet.type.name}"/>
                </td>
             
             	<td>
             		<c:out value="${adoption.owner.firstName} ${adoption.owner.lastName}"/>
             	</td>
             	<td>
             		<c:out value="${adoption.text}"/>
             	</td>
             	
             	<td>
             	<c:if test="${ownerActivo.user.username!=adoption.owner.user.username }">
             	<sec:authorize access="hasAnyAuthority('owner')"  >
             		<spring:url value="/adoption/{adoptionId}/application" var="apply">
				    	<spring:param name="adoptionId" value="${adoption.id}"/>
				    </spring:url>
				    <a href="${fn:escapeXml(apply)}">Solicitar adopción</a>
				 </sec:authorize>
				 </c:if>
             	</td>
             	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
    
    </c:otherwise>
    
    </c:choose>
    
</petclinic:layout>