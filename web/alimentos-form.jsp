<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Alimentos Management Application</title>
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
                    <a href="#" class="navbar-brand"> Alimentos Management App </a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/list"
                           class="nav-link">Listar Alimentos</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <c:if test="${alimento != null}">
                        <form action="update" method="post">
                        </c:if>
                        <c:if test="${alimento == null}">
                            <form action="insert" method="post">
                            </c:if>

                            <caption>
                                <h2>
                                    <c:if test="${alimento != null}">
                                        Edit Alimento
                                    </c:if>
                                    <c:if test="${alimento == null}">
                                        Add New Alimento
                                    </c:if>
                                </h2>
                            </caption>

                            <c:if test="${alimento != null}">
                                <input type="hidden" name="id_alimento" value="<c:out value='${alimento.id_alimento}' />" />
                            </c:if>

                            <fieldset class="form-group">
                                <label>Alimento</label> <input type="text"
                                                                value="<c:out value='${alimento.alimento}' />" class="form-control"
                                                                name="alimento" required="required">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Precio</label> <input type="text"
                                                                 value="<c:out value='${alimento.precio}' />" class="form-control"
                                                                 name="precio">
                            </fieldset>

                            

                            <button type="submit" class="btn btn-success">Save</button>
                        </form>
                </div>
            </div>
        </div>
    </body>
</html>
