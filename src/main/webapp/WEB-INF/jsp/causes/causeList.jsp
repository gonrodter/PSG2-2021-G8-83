<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<petclinic:layout pageName="causes">
	<h2>
		<th> Causas </th>
	</h2>
	
	<table id="causesTable" class="table table-striped">
		<thead>
			<tr>
				<th> Nombre </th>
				<th>Cantidad obtenida</th>
				<th>Cantidad solicitada</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${map}" var="entry">
				<tr>
					<td><spring:url value="/causes/{causeId}" var="causeUrl">
							<spring:param name="causeId" value="${entry.key.id}" />
						</spring:url>
						<c:out value="${entry.key.name}" />
					</td>
					<td><c:out value="${entry.value}"/></td>
						
					<td><c:out value="${entry.key.budgetTarget}"/></td>

					<td><spring:url value="causes/{causeId}/donations/new" var="createUrl">
       						<spring:param name="causeId" value="${entry.key.id}"/>
    					</spring:url>
   						<a href="${fn:escapeXml(createUrl)}" class="btn btn-default">Crear donación</a>
					</td>

					<td><spring:url value="/causes/{causeId}" var="detailsUrl">
       						<spring:param name="causeId" value="${entry.key.id}"/>
    					</spring:url>
   						<a href="${fn:escapeXml(detailsUrl)}" class="btn btn-default">Detalles de donación</a>
					</td>
				</tr>
				
		</c:forEach>
			</tbody>
	</table>

	<table class="table-buttons">
		<tr>
			<td><a class="btn btn-default" style="margin-right: 10px"
				href='<spring:url value="/causes/new" htmlEscape="true"/>'>Crear Causa</a></td>
		</tr>
	</table>
</petclinic:layout>