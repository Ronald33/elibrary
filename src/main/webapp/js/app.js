var app = angular.module('app', ['ngRoute', 'ngTagsInput']);

app.config(['$routeProvider', '$locationProvider',
    function ($routeProvider, $locationProvider)
    {
//        $locationProvider.html5Mode({
//            enabled: true,
//            requireBase: false
//        });

        $routeProvider.
                when('/', {
                    templateUrl: '../editor/libros',
                    controller: 'dashboardLibros'
                }).
                when('/nuevoLibro', {
                    templateUrl: '../libro/nuevo',
                    controller: 'libroNuevo'
                }).
                when('/categoria', {
                    templateUrl: '../categoria/listar',
                    controller: 'categoria'
                }).
                when('/categoria/nueva', {
                    templateUrl: '../categoria/nuevo',
                    controller: 'categoriaNuevo'
                }).
                when('/categoria/editar/:id', {
                    templateUrl: '../categoria/editar',
                    controller: 'categoriaEditar'
                }).
                when('/editor', {
                    templateUrl: '../editor/listar',
                    controller: 'editor'
                }).
                when('/editor/nueva', {
                    templateUrl: '../editor/nuevo',
                    controller: 'editorNuevo'
                }).
                when('/editor/editar/:id', {
                    templateUrl: '../editor/editar',
                    controller: 'editorEditar'
                }).
                when('/editorial', {
                    templateUrl: '../editorial/listar',
                    controller: 'editorial'
                }).
                when('/editorial/nueva', {
                    templateUrl: '../editorial/nuevo',
                    controller: 'editorialNuevo'
                }).
                when('/editorial/editar/:id', {
                    templateUrl: '../editorial/editar',
                    controller: 'editorialEditar'
                }).
                when('/autor', {
                    templateUrl: '../autor/listar',
                    controller: 'autor'
                }).
                when('/autor/nueva', {
                    templateUrl: '../autor/nuevo',
                    controller: 'autorNuevo'
                }).
                when('/autor/editar/:id', {
                    templateUrl: '../autor/editar',
                    controller: 'autorEditar'
                }).
                otherwise({
                    redirectTo: '/'
                });

//        $locationProvider.hashPrefix('!');
    }]);

/************************** Libro **************************/
app.controller('libroNuevo', function ($scope, $http)
{
    $('form, input').on('keyup keypress', function (e) {
        var code = e.keyCode || e.which;
        if (code === 13) {
            e.preventDefault();
            return false;
        }
    });

    var req_categorias = $http.get('../api/categoria');
    req_categorias.success(function (response) {
        $scope.categorias = response;
    });

    var req_editoriales = $http.get('../api/editorial');
    req_editoriales.success(function (response) {
        $scope.editoriales = response;
    });

    var req_categorias = $http.get('../api/categoria');
    req_categorias.success(function (response) {
        $scope.categorias = response;
    });

    var req_autores = $http.get('../api/autor');
    req_autores.success(function (response) {
        $scope.autores = response;
    });

    $scope.mis_autores = [];
    $scope.autor = "";
    $scope.agregarAutor = function ()
    {
        if ($scope.autor)
        {
            $scope.mis_autores.push($scope.autor);
            var index = $scope.autores.indexOf($scope.autor);
            $scope.autores.splice(index, 1);
        }
    };

    $scope.eliminarAutor = function (autor)
    {
        $scope.autores.push(autor);
        var index = $scope.mis_autores.indexOf(autor);
        $scope.mis_autores.splice(index, 1);
    };
});

app.controller('dashboardLibros', function ($scope, $http)
{
    var request = $http.get('../api/editorLibro');
    request.success(function (response) {
        $scope.libros = response;
    });
});
/************************** Libro **************************/

/************************** Categoria **************************/
app.controller('categoria', function ($scope, $http)
{
    var req_categorias = $http.get('../api/categoria');
    req_categorias.success(function (response) {
        $scope.categorias = response;
    });

    $scope.eliminarCategoria = function (index)
    {
        var categoria = $scope.categorias[index];
        var id = categoria.id;
        var req_categorias = $http.delete('../api/categoria/' + id);
        req_categorias.success(function (response) {
            if (response.success) {
                $scope.categorias.splice(index, 1);
            }
            else {
                alert(response.message);
            }
        });
    };
});

app.controller('categoriaNuevo', function ($scope, $http, $location)
{
    $scope.categoria = undefined;
    $scope.submitForm = function ()
    {
        var request = $http.post('../api/categoria', $scope.categoria);
        request.success(function (response) {
            if (response.success) {
                $location.path('/categoria');
            }
        });
    };
});

app.controller('categoriaEditar', function ($scope, $http, $routeParams, $location)
{
    var id = $routeParams.id;

    var request = $http.get('../api/categoria/' + id);
    request.success(function (response) {
        $scope.categoria = response;
    });

    $scope.submitForm = function ()
    {
        var request = $http.put('../api/categoria', $scope.categoria);
        request.success(function (response) {
            if (response.success) {
                $location.path('/categoria');
            }
        });
    };
});
/************************** Categoria **************************/

/************************** Editor **************************/
app.controller('editor', function ($scope, $http)
{
    var req_editors = $http.get('../api/editor');
    req_editors.success(function (response) {
        $scope.editores = response;
    });

    $scope.eliminarCategoria = function (index)
    {
        var editor = $scope.editores[index];
        var id = editor.id;
        var req_editors = $http.delete('../api/editor/' + id);
        req_editors.success(function (response) {
            if (response.success) {
                $scope.editores.splice(index, 1);
            }
            else {
                alert(response.message);
            }
        });
    };
});

app.controller('editorNuevo', function ($scope, $http, $location)
{
    $scope.editor = undefined;
    $scope.submitForm = function ()
    {
        var request = $http.post('../api/editor', $scope.editor);
        request.success(function (response) {
            if (response.success) {
                $location.path('/editor');
            }
        });
    };
});

app.controller('editorEditar', function ($scope, $http, $routeParams, $location)
{
    var id = $routeParams.id;

    var request = $http.get('../api/editor/' + id);
    request.success(function (response) {
        $scope.editor = response;
    });

    $scope.submitForm = function ()
    {
        var request = $http.put('../api/editor', $scope.editor);
        request.success(function (response) {
            if (response.success) {
                $location.path('/editor');
            }
        });
    };
});
/************************** Editor **************************/

/************************** Editorial **************************/
app.controller('editorial', function ($scope, $http)
{
    var req_editoriales = $http.get('../api/editorial');
    req_editoriales.success(function (response) {
        $scope.editoriales = response;
    });

    $scope.eliminarEditorial = function (index)
    {
        var editorial = $scope.editoriales[index];
        var id = editorial.id;
        var req_editoriales = $http.delete('../api/editorial/' + id);
        req_editoriales.success(function (response) {
            if (response.success) {
                $scope.editoriales.splice(index, 1);
            }
            else {
                alert(response.message);
            }
        });
    };
});

app.controller('editorialNuevo', function ($scope, $http, $location)
{
    $scope.editorial = undefined;
    $scope.submitForm = function ()
    {
        var request = $http.post('../api/editorial', $scope.editorial);
        request.success(function (response) {
            if (response.success) {
                $location.path('/editorial');
            }
        });
    };
});

app.controller('editorialEditar', function ($scope, $http, $routeParams, $location)
{
    var id = $routeParams.id;

    var request = $http.get('../api/editorial/' + id);
    request.success(function (response) {
        $scope.editorial = response;
    });

    $scope.submitForm = function ()
    {
        var request = $http.put('../api/editorial', $scope.editorial);
        request.success(function (response) {
            if (response.success) {
                $location.path('/editorial');
            }
        });
    };
});
/************************** Editorial **************************/

/************************** Autor **************************/
app.controller('autor', function ($scope, $http)
{
    var req_autores = $http.get('../api/autor');
    req_autores.success(function (response) {
        $scope.autores = response;
    });

    $scope.eliminarAutor = function (index)
    {
        var autor = $scope.autores[index];
        var id = autor.id;
        var req_autores = $http.delete('../api/autor/' + id);
        req_autores.success(function (response) {
            if (response.success) {
                $scope.autores.splice(index, 1);
            }
            else {
                alert(response.message);
            }
        });
    };
});

app.controller('autorNuevo', function ($scope, $http, $location)
{
    $scope.autor = undefined;
    $scope.submitForm = function ()
    {
        var request = $http.post('../api/autor', $scope.autor);
        request.success(function (response) {
            if (response.success) {
                $location.path('/autor');
            }
        });
    };
});

app.controller('autorEditar', function ($scope, $http, $routeParams, $location)
{
    var id = $routeParams.id;

    var request = $http.get('../api/autor/' + id);
    request.success(function (response) {
        $scope.autor = response;
    });

    $scope.submitForm = function ()
    {
        var request = $http.put('../api/autor', $scope.autor);
        request.success(function (response) {
            if (response.success) {
                $location.path('/autor');
            }
        });
    };
});
/************************** Autor **************************/