<#macro page title>
    <!DOCTYPE html>
    <html lang="en" xmlns:j="http://www.w3.org/1999/XSL/Transform" xmlns:sec="http://www.springframework.org/security/tags"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/security/tags ">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Car Rentals</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
                crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
                crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
                crossorigin="anonymous"></script>
        <link rel="stylesheet" href="/style/styleCommon.css" type="text/css">

    </head>
    <body>
    <div>

        <nav class="navbar navbar-expand navbar-dark bg-dark navbar-fixed-top">
            <a class="navbar-brand" href="/">
                <img src="/image/mainNavbar.png" type="image/png" alt="">
            </a>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/CarRentals/customer/list">Customers list</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false"> Car </a>
                        <div class="dropdown-menu bg-dark" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item alert-link" href="/CarRentals/car/list">Full list</a>
                            <a class="dropdown-item alert-link" href="/CarRentals/car/listR">Active list</a>
                            <a class="dropdown-item alert-link" href="/CarRentals/car/listSort">Sort list</a>
                            <a class="dropdown-item alert-link" href="/CarRentals/car/search">Search</a>
                            <a class="dropdown-item alert-link" href="/CarRentals/car/List">List</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/CarRentals/rentCar/list">Rent car list</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/CarRentals/returnRentCar/list">Return list</a>
                    </li>
                    <#--<li class="nav-item">
                                <a class="nav-link" href="/CarRentals/signIn">Sign In</a>
                            </li>-->
                </ul>
            </div>
            <form class="form-inline">


                <#--<#if PersonLogin.gender>
                            <#assign imageGender="/image/userMan.png" >
                        <#else>
                            <#assign imageGender="/image/userGirl.png">
                        </#if>-->
                <sec:authorize access="isAuthenticated()">
                    <a href="/CarRentals/car/listSearch">Search <img src="/image/search.png" type="image/png" alt=""> </a>
                </sec:authorize>
                <#assign imageGender="">
                <#if !isAuthenticated??>
                    <a href="/CarRentals/signIn">Sign In <img src="/image/userNo.png" type="image/png" alt=""> </a>
                <#else>
                    <#if personLogin.person.gender>
                        <#assign imageGender="/image/userBoy.png" >
                    <#else>
                        <#assign imageGender="/image/userGirl.png">
                    </#if>
                    <a href="/CarRentals/userAccount/${personLogin.id}">${personLogin.person.lastName} <img
                                src=${imageGender} type="image/png" alt=""> </a>
                </#if>

                <#--<a href="/CarRentals/signIn">   Sign In  <img src="/image/userNo.png" type="image/png" alt="">     </a>-->
            </form>
        </nav>


        <#nested/> <#-- <<<<<< Сюда вставится каждый твой файл -->

        <#-- Тут то, что должно быть на каждой странице снизу -->
    </div>
    </body>
    </html>
</#macro>