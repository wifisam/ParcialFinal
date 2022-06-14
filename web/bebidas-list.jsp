<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>User Management Application</title>
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
                    <a href="#" class="navbar-brand"> Bebidas
                        Management App </a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/list"
                           class="nav-link">Listar Bebidas</a></li>
                </ul>
            </nav>
        </header>
        <br>

        <div class="row">
            <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

            <div class="container">
                <h3 class="text-center">List of Bebidas</h3>
                <hr>
                <div class="container text-left">

                    <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
                        New Bebida</a>
                </div>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Bebida</th>
                            <th>Precio</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!--   for (Todo todo: todos) {  -->
                        <c:forEach var="bebida" items="${listBebida}">
                            <tr>
                                <td><c:out value="${bebida.id_bebida}" /></td>
                                <td><c:out value="${bebida.bebida}" /></td>
                                <td><c:out value="${bebida.precio}" /></td>
                                <td><a href="edit?id=<c:out value='${bebida.id_bebida}' />">Edit</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp; <a
                                        href="delete?id=<c:out value='${bebida.id_bebida}' />">Delete</a></td>
                            </tr>
                        </c:forEach>
                        <!-- } -->
                    </tbody>

                </table>
            </div>
        </div>
    </body>
</html>
