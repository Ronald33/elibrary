<h1>Tus libros</h1>
<p>Para subir un libro: sigue los siguientes pasos: En la pestaña izquierda selecciona: <code>Libros</code> luego <code>Nuevo Libro</code></p>

<div class="row text-center">

    <div class="col-md-3 col-sm-6 hero-feature" ng-repeat="libro in libros">
        <div class="thumbnail">
            <img src="../{{libro.portada}}" alt="" style="height: 128px">
            <div class="caption">
                <h3>{{libro.titulo}}</h3>
                <!--<p>{{libro.descripcion}}</p>-->
                <p>
                    <a href="#" class="btn btn-primary">Ver</a>
                    <a href="#" class="btn btn-warning">Editar</a>
                    <a href="#" class="btn btn-danger">Eliminar</a>
                </p>
            </div>
        </div>
    </div>
</div>