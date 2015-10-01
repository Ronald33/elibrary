<%@page import="com.ronald.elibrary.helper.Helper"%>
<%
    String baseURL = Helper.getBaseURL(request);
%>
<!DOCTYPE HTML>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Sin gae</title>
        <link rel="stylesheet" href="<%=baseURL%>vendor/bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" href="<%=baseURL%>css/estilos.css" />
        <link rel="stylesheet" href="<%=baseURL%>css/loader.css" />
        <base href="<%=baseURL%>" />
    </head>
    <body>
        <div id="loading" class="oculto">Cargando ...</div>
        <div class="container">
            <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
                <div class="panel panel-info" >
                    <div class="panel-heading">
                        <div class="panel-title">Identificate</div>
                        <div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="#">¿Olvidaste la contraseña?</a></div>
                    </div>

                    <div style="padding-top:30px" class="panel-body" >

                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>

                        <form id="loginform" class="form-horizontal ajax" role="form" method="POST" action="api/editor/login">

                            <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input id="login-username" type="text" class="form-control" name="usuario" value="" placeholder="username or email" autofocus="autofocus" required="required" />
                            </div>

                            <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                <input id="login-password" type="password" class="form-control" name="contrasena" placeholder="password" required="required" />
                            </div>

                            <div class="input-group">
                                <div class="checkbox">
                                    <label>
                                        <input id="login-remember" type="checkbox" name="remember" value="1"> Recuerdame
                                    </label>
                                </div>
                            </div>

                            <div style="margin-top:10px" class="form-group">
                                <!-- Button -->

                                <div class="col-sm-12 controls">
                                    <button id="btn-login" class="btn btn-success">Entrar  </button>
                                    <!--<a id="btn-fblogin" href="#" class="btn btn-primary">Login with Facebook</a>-->
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-12 control">
                                    <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
                                        ¿No tienes una cuenta?
                                        <a href="#" id="sign-up">
                                            Crea una nueva cuenta aqui
                                        </a>
                                    </div>
                                </div>
                            </div>    
                        </form>
                    </div>                     
                </div>  
            </div>
            <div id="signupbox" style="display:none; margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <div class="panel-title">Registrarse</div>
                        <div style="float:right; font-size: 85%; position: relative; top:-10px"><a id="signinlink" href="#">Ya tengo una cuenta</a></div>
                    </div>  
                    <div class="panel-body" >
                        <form id="signupform" class="form-horizontal ajax" role="form" method="POST" action="api/editor">

                            <div id="signupalert" style="display:none" class="alert alert-danger">
                                <p>Error:</p>
                                <span></span>
                            </div>
                            <div class="form-group">
                                <label for="nombres" class="col-md-3 control-label">Nombres</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" name="nombres" placeholder="Nombres" id="nombres">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="apellidos" class="col-md-3 control-label">Apellidos</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" name="apellidos" placeholder="Apellidos" id="apellidos">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="email" class="col-md-3 control-label">Email</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" name="email" placeholder="Email" id="email">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="usuario" class="col-md-3 control-label">Usuario</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" name="usuario" placeholder="Usuario" id="usuario">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="contrasenha" class="col-md-3 control-label">Contraseña</label>
                                <div class="col-md-9">
                                    <input type="password" class="form-control" name="contrasena" placeholder="Contraseña" id="contrasenha">
                                </div>
                            </div>

                            <!--                            <div class="form-group">
                                                            <label for="icode" class="col-md-3 control-label">Invitation Code</label>
                                                            <div class="col-md-9">
                                                                <input type="text" class="form-control" name="icode" placeholder="">
                                                            </div>
                                                        </div>-->

                            <div class="form-group">
                                <!-- Button -->                                        
                                <div class="col-md-offset-3 col-md-9">
                                    <button id="btn-signup" class="btn btn-info"><i class="icon-hand-right"></i> &nbsp Registrarse</button>
                                    <!--<span style="margin-left:8px;">or</span>-->  
                                </div>
                            </div>

                            <!--                            <div style="border-top: 1px solid #999; padding-top:20px"  class="form-group">
                            
                                                            <div class="col-md-offset-3 col-md-9">
                                                                <button id="btn-fbsignup" type="button" class="btn btn-primary"><i class="icon-facebook"></i>   Sign Up with Facebook</button>
                                                            </div>
                                                        </div>-->

                        </form>
                    </div>
                </div>
            </div> 
        </div>

        <!-- Scripts -->
        <script type="text/javascript" src="<%=baseURL%>vendor/jquery/jquery.js"></script>
        <script type="text/javascript" src="<%=baseURL%>js/funciones.js"></script>
        <script type="text/javascript" src="<%=baseURL%>vendor/bootstrap/js/bootstrap.js"></script>
        <script type="text/javascript" src="<%=baseURL%>js/editor/login.js"></script>
    </body>
</html>