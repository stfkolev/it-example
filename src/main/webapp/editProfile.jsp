<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>


<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Login</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Начало <span class="sr-only">(current)</span></a>
            </li>
        </ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="register">Регистрация</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="login">Вход</a>
                </li>
            </ul>
    </div>
</nav>

<div class="container-fluid" style="margin-top: 0.725rem;">
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <div class="card">
                <h5 class="card-header">Редактиране на профила</h5>
                <div class="card-body">
                    <form action="editProfile" method="post">

                        <div class="form-group">
                            <label for="firstName">Име</label>

                            <!-- Ако името не е празно, покажи го в полето -->
                            <input type="text" class="form-control" id="firstName" name="firstName" value="<c:if test="${not empty user.getFirstName()}">${user.getFirstName()}</c:if>">
                        </div>

                        <div class="form-group">
                            <label for="lastName">Фамилия</label>

                            <!-- Ако фамилията не е празна, покажи я в полето -->
                            <input type="text" class="form-control" id="lastName" name="lastName" value="<c:if test="${not empty user.getLastName()}">${user.getLastName()}</c:if>">
                        </div>

                        <div class="form-group">
                            <label for="city">Град</label>

                            <!-- Ако градът не е празен, покажи го в полето -->
                            <input type="text" class="form-control" id="city" name="city" value="<c:if test="${not empty user.getCity()}">${user.getCity()}</c:if>">
                        </div>

                        <div class="form-group">
                            <label for="address">Адрес</label>

                            <!-- Ако адресът не е празен, покажи го в полето -->
                            <input type="text" class="form-control" id="address" name="address" value="<c:if test="${not empty user.getAddress()}">${user.getAddress()}</c:if>">
                        </div>

                        <button type="submit" name="save" class="btn btn-primary">Запазване</button>
                    </form>
                </div>
            </div>
        </div>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>