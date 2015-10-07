var app = angular.module("app", ['duScroll']);

app.controller('ultimos', function ($scope, $http, $document) {
    var request = $http.get("../api/alibro");
    request.success(function (response) {
        $scope.libros = response;
    });

    $scope.buscar = function ()
    {
        var request = $http.get('https://www.googleapis.com/books/v1/volumes?q=' + $scope.key);

        request.success(function (response) {
            $scope.glibros = response.items;
        });
    };
    
    $scope.detalles = function (id)
    {
        var request = $http.get("../api/alibro/" + id);
        request.success(function (response) {
            $scope.dlibro = response;
        });
        
        var request_comentarios = $http.get("../api/alibro/"+id+"/comentario");
        request_comentarios.success(function (response) {
            $scope.comentarios = response;
        });
        
        var someElement = angular.element(document.getElementById('arriba'));
        $document.scrollToElementAnimated(someElement, 40);
    };
    
    $scope.irIndice = function()
    {
        var someElement = angular.element(document.getElementById('ultimos'));
        $document.scrollToElementAnimated(someElement, 40);
    };
    
    $scope.comentarios = [];
    $scope.comentario = {};
    $scope.comentar = function(usua_id)
    {
        $scope.comentario.usuario = {};
        $scope.comentario.usuario.id = usua_id;
        
        $scope.comentario.libro = {};
        $scope.comentario.libro.id = $scope.dlibro.id;
        
        var request = $http.post("../api/comentario/", $scope.comentario);
        request.success(function (response) {
            var comentario = 
                    {
                        texto: $scope.comentario.texto, 
                        usuario: "Yo"
                    };
                    
            $scope.comentarios.unshift(comentario);
            $scope.comentario.texto = "";
        });
    };
});