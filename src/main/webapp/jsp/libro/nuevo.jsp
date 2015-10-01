<%
    String editor = request.getSession().getAttribute("id").toString();
%>
<h1>Nuevo libro</h1>
<form method="POST" action="../api/libro" enctype="multipart/form-data" >
    <input type="hidden" name="editor" value="<%=editor%>" />
    <div class="form-group">
        <label for="titulo">Titulo</label>
        <input type="text" class="form-control" id="titulo" placeholder="Titulo" name="nombre" >
    </div>
    <div class="form-group">
        <label for="descripcion">Descripcion</label>
        <textarea class="form-control" rows="3" name="descripcion" id="descripcion"></textarea>
    </div>
    <div class="form-group">
        <label for="categoria">Categoria</label>
        <select class="form-control" name="categoria" id="categoria">
            <option value="">Seleccionar</option>
            <option ng-repeat="categoria in categorias" value="{{categoria.id}}">{{categoria.nombre}}</option>
        </select>
    </div>
    <div class="form-group">
        <label for="editorial">Editorial</label>
        <select class="form-control" name="editorial" id="editorial">
            <option value="">Seleccionar</option>
            <option ng-repeat="editorial in editoriales" value="{{editorial.id}}">{{editorial.nombre}}</option>
        </select>
    </div>
    <div>
        <table class="table table-bordered table-condensed table-hover table-responsive table-striped">
            <thead>
                <tr>
                    <th>Autor</th>
                    <th>Opciones</th>
                </tr>
            </thead>
            <tbody>
                <tr ng-repeat="mi_autor in mis_autores">
                    <td>{{mi_autor.apellidos}}, {{mi_autor.nombres}}</td>
                    <td>
                        <button type="button" class="btn btn-xs btn-danger" ng-click="eliminarAutor(mi_autor)">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Eliminar
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <input type="hidden" name="autores[]" value="{{mi_autor.id}}" ng-repeat="mi_autor in mis_autores" />
    <div class="form-group">
        <label for="editorial">Autor</label>
        <div class="row">
            <div class="col-lg-10">
                <select class="form-control" id="autores" ng-model="autor" ng-options="autor as autor.apellidos + ', ' + autor.nombres for autor in autores">
                    <option value="">Seleccionar</option>
                </select>
            </div>
            <div class="col-lg-2">
                <button type="button" class="btn btn-info btn-block" ng-click="agregarAutor()" ng-disabled="!autor">Agregar Autor</button>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="tags">Tags</label>
        <tags-input ng-model="tags" id="tags" placeholder="Ingrese tags relacionados" ng-model="tags"></tags-input>
        <input type="hidden" name="tags[]" value="{{tag.text}}" ng-repeat="tag in tags" />
    </div>
    <div class="form-group">
        <label for="portada">Portada</label>
        <input type="file" id="portada" name="portada" accept="image/*">

    </div>
    <div class="form-group">
        <label for="pdf">PDF</label>
        <input type="file" id="pdf" name="pdf" accept="application/pdf">
    </div>
    <button type="submit" class="btn btn-default">Guardar</button>
</form>