<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="donations">

    <jsp:body>
    <h2>
        <c:if test="${donation['new']}">Nueva donación</c:if>
    </h2>
    <form:form modelAttribute="donation" class="form-horizontal" id="add-donation-form">
        <div class="form-group has-feedback">
        
        	<div class="form-group">
                    <label class="col-sm-2 control-label">Causa: </label>
                    <div class="col-sm-10">
                        <c:out value="${cause.name}"/>
                    </div>
             </div>
             <petclinic:inputField label= "Cantidad" name="Amount"/>
            <petclinic:inputField label= "Cliente" name="client" />

      		
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${donation['new']}">
                        <button class="btn btn-default" type="submit">Añadir donación</button>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </form:form>
    </jsp:body>
</petclinic:layout>