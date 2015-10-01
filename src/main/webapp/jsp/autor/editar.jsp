<form ng-submit="submitForm()">
  <div class="form-group">
    <label for="nombre">Nombre</label>
    <input type="text" class="form-control" id="nombre" placeholder="Nombre" ng-model="autor.nombre" required="required">
  </div>
  <button type="submit" class="btn btn-default">Guardar</button>
</form>