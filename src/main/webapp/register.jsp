<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html ng-app="categoria" lang="es">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Categorías</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
    <script src="https://code.angularjs.org/1.8.2/angular-route.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</head>

<body ng-controller="myController" style="background-color: #fc4b08; color: #003049;">
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #003049;">
    <div class="container-fluid">
        <a class="navbar-brand">Tienda</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="./index.html">Artículos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="./teacher.html">Categorías</a>
                </li>

            </ul>
        </div>
    </div>
</nav>
<h1 class="mt-3 text-center" style="color: white;">Categorías</h1>
<hr style="background-color: white;">

<div class="container">
    <div class="d-md-flex justify-content-md-end mb-3">
        <input class="form-control me-2" type="search"  placeholder="Buscar" ng-model="busqueda" aria-label="Search">
        <button class="btn btn-success" type="button" data-bs-toggle="modal"
                data-bs-target="#register"><i
                class="fas fa-plus"></i></button>
    </div>
    <div class="row text-center">
        <div class="col-12" ng-if="(data.categoryList | filter : busqueda) == '' ">
            <span class="badge rounded-pill" style="background-color: #003049; font-size: 18px;">No hay registros</span>
        </div>

        <div class="col-lg-3 col-sm-6"  ng-repeat="categorie in data.categoryList | filter : busqueda">
            <div class="card m-1" >
                <div class="card-body">
                    <h5 class="card-title">{{categorie.nombre|uppercase}}</h5>
                    <p class="card-text">{{categorie.descripcion}}</p>
                    <button type="button" ng-click="getCat(categorie.id)" data-bs-toggle="modal"
                            data-bs-target="#modify" class="btn btn-warning"><i
                            class="fas fa-edit"></i></button>
                    <button class="btn"  ng-click="delete(categorie.id)" style="background-color: #D62828; color: white;"><i
                            class="fas fa-trash"></i></button>
                </div>
            </div>
        </div>
    </div>
</div>

<form id="reg" method="post"  ng-submit="register()">
    <div class="modal fade" id="register" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header" style="background-color: #003049;">
                    <h5 class="modal-title" style="color: white;">Registrar una categoría</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="container">

                        <div class="row">
                            <div class="mb-3">
                                <label class="form-label">Nombre</label>

                                <input ng-change="validate()" type="text" ng-model="category.nombre"
                                       name="category.nombre" class="form-control" aria-describedby="emailHelp">
                                <span style="color: #D62828;" ng-show="errorNombre">El nombre es requerido</span>

                            </div>
                            <div class="mb-3">
                                <label class="form-label">Descripción</label>
                                <input ng-change="validate()" type="text" ng-model="category.descripcion"
                                       name="category.descripcion" class="form-control" aria-describedby="emailHelp">
                                <span style="color: #D62828;" ng-show="errorDescripcion">La descripción es
                                        requerida</span>
                            </div>
                        </div>


                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    <button type="submit" class="btn" style="background-color: #003049;color: white;"
                            ng-disabled="goRegister">Registrar</button>
                </div>
            </div>
        </div>
    </div>
</form>
<form ng-submit="modifyGo()" method="post">
    <div class="modal fade" id="modify" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header" style="background-color: #003049;">
                    <h5 class="modal-title" style="color: white" id="exampleModalLabel">Modificar una categoría</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="container">
                        <div class="row">
                            <div class="mb-3">
                                <label class="form-label">Nombre</label>
                                <input ng-model="category.id" name="id" type="hidden" class="form-control" aria-describedby="emailHelp">
                                <input ng-change="validateUpdate()"  disabled ng-model="nombre" name="nombre" type="text" class="form-control" aria-describedby="emailHelp">
                                <span style="color: #D62828;" ng-show="errorNombre">El nombre es requerido</span>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Descripción</label>
                                <input type="text" ng-change="validateUpdate()"  ng-model="descripcion" name="descripcion"  class="form-control" aria-describedby="emailHelp">
                                <span style="color: #D62828;" ng-show="errorDescripcion">La descripción es
                                        requerida</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    <button type="submit" ng-disabled="goModify" class="btn" style="background-color: #003049;color: white;">Modificar</button>
                </div>
            </div>
        </div>
    </div>
</form>



<script>
    let miAplicacion = angular.module("categoria", ['ngRoute'])
    miAplicacion.controller("myController", function ($scope, $http) {
        $scope.goRegister = true;
        $http({
            method: 'GET',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            url: 'http://localhost:8080/basic_struts/findAll'
        }).then(function successCallback(response) {
            $scope.data = response.data
        }, function errorCallback(response) {
            console.log("aaa");
        });

        $scope.getAll = () => {
            $http({
                method: 'GET',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                url: 'http://localhost:8080/basic_struts/findAll'
            }).then(function successCallback(response) {
                $scope.data = response.data
            }, function errorCallback(response) {
                console.log("aaa");
            });
        }
        $scope.validate = () => {
            if ($scope.category.nombre == undefined || $scope.category.nombre === "") {
                $scope.errorNombre = true;
                $scope.goRegister = true;
            } else
            if ($scope.category.descripcion == undefined || $scope.category.descripcion === "") {
                $scope.errorDescripcion = true;
                $scope.goRegister = true;
            } else {
                $scope.errorDescripcion = false;
                $scope.errorNombre = false;
                $scope.goRegister = false;
            }
        }
        $scope.validateUpdate = () => {
            if ($scope.descripcion == undefined || $scope.descripcion === "") {
                $scope.errorDescripcion = true;
                $scope.goModify = true;
            } else {
                $scope.errorDescripcion = false;
                $scope.goModify = false;
            }
        }
        $scope.register = () => {
            Swal.fire({
                title: '¿Quieres registrar la categoría?',
                showCancelButton: true,
                confirmButtonText: 'Guardar',
                cancelButtonText: 'Cancelar',
                icon: 'question'
            }).then((result) => {
                if (result.isConfirmed) {
                    $http({
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded',
                        },
                        url: 'http://localhost:8080/basic_struts/register',
                        data: "params=" + JSON.stringify($scope.category),
                    }).then(function successCallback(response) {
                        console.log(response.data);
                        $scope.errorDescripcion = false;
                        $scope.errorNombre = false;
                        $scope.category = {}
                        $scope.goRegister = true;
                        Swal.fire({
                            position: 'top-end',
                            icon: 'success',
                            title: '¡Se ha registrado la categoría!',
                            showConfirmButton: false,
                            timer: 1500
                        })
                        $scope.getAll()
                    }, function errorCallback(response) {
                        console.log("aaa");
                    });
                }
            })

        }
        $scope.modifyGo = () => {
            Swal.fire({
                title: '¿Quieres modificar la categoría?',
                showCancelButton: true,
                confirmButtonText: 'Modificar',
                cancelButtonText: 'Cancelar',
                icon: 'question'
            }).then((result) => {
                if (result.isConfirmed) {
                    let categoryModify = new Object();
                    categoryModify.id = $scope.id
                    categoryModify.nombre = $scope.nombre
                    categoryModify.descripcion = $scope.descripcion
                    $http({
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded',
                        },
                        url: 'http://localhost:8080/basic_struts/update',
                        data: "params=" + JSON.stringify(categoryModify),
                    }).then(function successCallback(response) {
                        console.log(response.data);
                        $scope.errorDescripcion = false;
                        $scope.errorNombre = false;
                        $scope.category = {}
                        $scope.goRegister = true;
                        Swal.fire({
                            position: 'top-end',
                            icon: 'success',
                            title: '¡Se ha modificado la categoría!',
                            showConfirmButton: false,
                            timer: 1500
                        })
                        $scope.getAll()
                    }, function errorCallback(response) {
                    });
                }
            })

        }

        $scope.delete = (id) => {
            let categoryDelete = new Object();
            categoryDelete.id = id
            Swal.fire({
                title: '¿Quieres eliminar la categoría?',
                showCancelButton: true,
                confirmButtonText: 'Eliminar',
                cancelButtonText: 'Cancelar',
                icon: 'question'
            }).then((result) => {
                if (result.isConfirmed) {
                    $http({
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded',
                        },
                        url: 'http://localhost:8080/basic_struts/delete',
                        data: "params=" + JSON.stringify(categoryDelete),
                    }).then(function successCallback(response) {
                        console.log(response.data);
                        $scope.errorDescripcion = false;
                        $scope.errorNombre = false;
                        $scope.category = {}
                        $scope.goRegister = true;
                        Swal.fire({
                            position: 'top-end',
                            icon: 'success',
                            title: '¡Se ha eliminado la categoría!',
                            showConfirmButton: false,
                            timer: 1500
                        })
                        $scope.getAll()
                    }, function errorCallback(response) {
                        console.log("aaa");
                    });
                }
            })

        }
        $scope.getCat = (id) => {
            for (let i = 0; i < $scope.data.categoryList.length; i++) {

                if ($scope.data.categoryList[i].id == id) {
                    $scope.id = $scope.data.categoryList[i].id
                    $scope.nombre = $scope.data.categoryList[i].nombre
                    $scope.descripcion = $scope.data.categoryList[i].descripcion
                }
            }

        }
    });


</script>
<script src="https://kit.fontawesome.com/afd8979cda.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</body>

</html>