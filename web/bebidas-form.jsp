<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Bebidas Management Application</title>
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
                    <a href="#" class="navbar-brand"> Bebidas Management App </a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/list"
                           class="nav-link">Listar Bebidas</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <c:if test="${bebida != null}">
                        <form action="update" method="post">
                        </c:if>
                        <c:if test="${bebida == null}">
                            <form action="insert" method="post">
                            </c:if>

                            <caption>
                                <h2>
                                    <c:if test="${bebida != null}">
                                        Edit Bebida
                                    </c:if>
                                    <c:if test="${bebida == null}">
                                        Add New Bebida
                                    </c:if>
                                </h2>
                            </caption>

                            <c:if test="${bebida != null}">
                                <input type="hidden" name="id_bebida" value="<c:out value='${bebida.id_bebida}' />" />
                            </c:if>

                            <fieldset class="form-group">
                                <label>Bebida</label> <input type="text"
                                                                value="<c:out value='${bebida.bebida}' />" class="form-control"
                                                                name="bebida" required="required">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Precio</label> <input type="text"
                                                                 value="<c:out value='${bebida.precio}' />" class="form-control"
                                                                 name="precio">
                            </fieldset>

                            

                            <button type="submit" class="btn btn-success">Save</button>
                        </form>
                </div>
            </div>
        </div>
    </body>
</html>
