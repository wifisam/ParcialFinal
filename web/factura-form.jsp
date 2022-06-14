<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Factura Management Application</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: tomato">
                <div>
                    <a href="#" class="navbar-brand"> Factura Management App </a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/list"
                           class="nav-link">Listar Facturas</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <c:if test="${factura != null}">
                        <form action="update" method="post">
                        </c:if>
                        <c:if test="${factura == null}">
                            <form action="insert" method="post">
                            </c:if>

                            <caption>
                                <h2>
                                    <c:if test="${factura != null}">
                                        Edit Alimento
                                    </c:if>
                                    <c:if test="${factura == null}">
                                        Add New Factura
                                    </c:if>
                                </h2>
                            </caption>

                            <c:if test="${factura != null}">
                                <input type="hidden" name="id_factura" value="<c:out value='${factura.id_factura}' />" />
                            </c:if>


                            <fieldset class="form-group">
                                <label>Alimento</label> <input type="text"
                                                               value="<c:out value='${factura.id_alimento}' />" class="form-control"
                                                               name="alimento" required="required">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Bebida</label> <input type="text"
                                                             value="<c:out value='${factura.id_bebida}' />" class="form-control"
                                                             name="bebida">
                            </fieldset>
                                                             
                            <fieldset class="form-group">
                                <label>Subtotal</label> <input type="text"
                                                             value="<c:out value='${factura.subtotal}' />" class="form-control"
                                                             name="subtotal">
                            </fieldset>


                            <button type="submit" class="btn btn-success">Save</button>
                        </form>
                </div>
            </div>
        </div>
    </body>
</html>
