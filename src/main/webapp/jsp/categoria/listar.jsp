<h1>Categorias</h1>
<div class="row text-center">
    <table class="table table-bordered table-hover table-responsive table-striped">
        <thead>
            <tr>
                <th>N°</th>
                <th>Nombre</th>
                <th>Opciones</th>
            </tr>
        </thead>
        <tbody>
            <tr ng-repeat="categoria in categorias">
                <td>{{$index+1}}</td>
                <td>{{categoria.nombre}}</td>
                <td>
                    <a href="#/categoria/editar/{{categoria.id}}" class="btn btn-warning btn-xs"><span class="glyphicon glyphicon-pencil"></span> Editar</a>
                    <a href="" class="btn btn-danger btn-xs" ng-click="eliminarCategoria($index)"><span class="glyphicon glyphicon-remove-circle"></span> Eliminar</a>
                </td>
            </tr>
        </tbody>
    </table>
</div>