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
    <table id="adoptionsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Mascota</th>
            <th>Due�o</th>
            <th>Descripci�n</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${adoptions.adoptionsList}" var="adoption">
            <tr>
                <td>
                    <c:out value="${adoption.pet}"/>
                </td>
             
             	<td>
             		<c:out value="${adoption.owner.firstName} ${adoption.owner.lastName}"/>
             	</td>
             	<td>
             		<c:out value="${adoption.text}"/>
             	</td>
             	
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
</petclinic:layout>