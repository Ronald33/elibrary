<h1>Autores</h1>
<div class="row text-center">
    <table class="table table-bordered table-hover table-responsive table-striped">
        <thead>
            <tr>
                <th>N°</th>
                <th>Nombres</th>
                <th>Apellidos</th>
                <th>Opciones</th>
            </tr>
        </thead>
        <tbody>
            <tr ng-repeat="autor in autores">
                <td>{{$index+1}}</td>
                <td>{{autor.nombres}}</td>
                <td>{{autor.apellidos}}</td>
                <td>
                    <a href="#/autor/editar/{{autor.id}}" class="btn btn-warning btn-xs"><span class="glyphicon glyphicon-pencil"></span> Editar</a>
                    <a href="" class="btn btn-danger btn-xs" ng-click="eliminarAutor($index)"><span class="glyphicon glyphicon-remove-circle"></span> Eliminar</a>
                </td>
            </tr>
        </tbody>
    </table>
</div>