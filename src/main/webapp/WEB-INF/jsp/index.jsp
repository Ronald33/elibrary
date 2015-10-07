<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page import="com.ronald.elibrary.helper.Helper"%>
<%
    String baseURL = Helper.getBaseURL(request);
    String title = "E-Library";
    String id = "0";
    String nombres = "";
    if(request.getSession().getAttribute("nombres") != null)
    {
        nombres = request.getSession().getAttribute("nombres").toString();
        id = request.getSession().getAttribute("id").toString();
        title = "Hola " + nombres;
    }

%>
<!DOCTYPE html>
<html lang="es">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title><%=title%></title>

        <!-- Bootstrap Core CSS -->
        <link href="<%=baseURL%>vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="<%=baseURL%>vendor/heroic/heroic-features.css" rel="stylesheet">

        <link href="<%=baseURL%>vendor/language/languages.css" rel="stylesheet">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <link href="<%=baseURL%>css/index.css" rel="stylesheet">

    </head>

    <body ng-app="app" ng-controller="ultimos">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">E-Library</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="#"><spring:message code="inicio" text="default text" /></a>
                        </li>
                        <li>
                            <a href="" ng-click="irIndice()"><spring:message code="lista" text="default text" /></a>
                        </li>
                        <li>
                            <a href="#"><spring:message code="creditos" text="default text" /></a>
                        </li>
                        <li>
                            <a href="../editor/login"><spring:message code="editor" text="default text" /></a>
                        </li>
                        <%
                            if(request.getSession().getAttribute("nombres") == null)
                            {
                        %>
                        <li>
                            <a href="../usuario/login"><spring:message code="usuario" text="default text" /></a>
                        </li>
                        <%
                        }
                        else
                        {
                        %>
                        <li>
                            <a href="../usuario/logout">Logout</a>
                        </li>
                        <%
                            }
                        %>
                    </ul>
                    <div class="col-sm-1 pull-right">
                        <div class="box-languages">
                            <a href="?language=en">
                                <span class="lang-sm" lang="en"></span>
                            </a>
                            &nbsp;
                            <a href="?language=es">
                                <span class="lang-sm" lang="es"></span>
                            </a>
                        </div>
                    </div>
                    <div class="col-sm-3 col-md-3 pull-right">
                        <form class="navbar-form" role="search">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="<spring:message code="buscar" text="default text" /> ..." name="q" ng-model="key">
                                <div class="input-group-btn">
                                    <button class="btn btn-default" type="submit" ng-click="buscar()">
                                        <i class="glyphicon glyphicon-search"></i>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>

        <!-- Page Content -->
        <div class="container" id="arriba">

            <!-- Jumbotron Header -->
            <!--            <header class="jumbotron hero-spacer">
                            <h1>Bienvenido a E-Library</h1>
                            <p>Bienvenido a E-Library un proyecto open source para el almacenamiento y distribución de material bibliografico bajo la licencia Creative Commons</p>
                            <p><a class="btn btn-primary btn-large">Buscar libros ahora!</a>
                            </p>
                        </header>-->

            <div class="row" ng-show="dlibro">
                <div class="row text-center">
                    <div class="col-lg-12">
                        <h1 class="page-header">{{dlibro.titulo}}
                            <small>{{dlibro.edit_nombre}}</small>
                        </h1>
                    </div>
                </div>
                <!-- Title -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-sm-3 text-center">
                                <div class="dportada">
                                    <a href="">
                                        <img src="../{{dlibro.portada}}" alt="">
                                    </a>
                                </div>
                                <div>
                                    <%
                                        if(request.getSession().getAttribute("id") != null)
                                        {
                                    %>
                                    <fieldset class="rating">
                                        <input type="radio" id="star5" name="rating" value="5" />
                                        <label class = "full" for="star5" title="Awesome - 5 stars"></label>

                                        <input type="radio" id="star4" name="rating" value="4" />
                                        <label class = "full" for="star4" title="Pretty good - 4 stars"></label>

                                        <input type="radio" id="star3" name="rating" value="3" />
                                        <label class = "full" for="star3" title="Meh - 3 stars"></label>

                                        <input type="radio" id="star2" name="rating" value="2" />
                                        <label class = "full" for="star2" title="Kinda bad - 2 stars"></label>

                                        <input type="radio" id="star1" name="rating" value="1" />
                                        <label class = "full" for="star1" title="Sucks big time - 1 star"></label>
                                    </fieldset>
                                    <%
                                        }
                                    %>
                                </div>
                            </div>
                            <div class="col-sm-9">
                                <h3><spring:message code="descripcion" text="default text" /></h3>
                                <p>{{dlibro.descripcion}}</p>
                                <h3><spring:message code="categoria" text="default text" /></h3>
                                <p>{{dlibro.cate_nombre}}</p>
                            </div>
                        </div>
                    </div>
                </div>
                <hr />
                <%
                    if(request.getSession().getAttribute("id") != null)
                    {
                %>
                <div class="row">
                    <div class="col-sm-12">
                        <form action="">
                            <div class="col-sm-11">
                                <textarea name="comentario" id="comentario" class="form-control" ng-model="comentario.texto"><%=nombres%></textarea>
                            </div>
                            <div class="col-sm-1">
                                <input type="hidden" name="usuario" id="usuario" ng-model="usuario" ng-value="<%=nombres%>" />
                                <button class="btn" type="button" ng-click="comentar(<%=id%>)" ng-disabled="!comentario.texto"><spring:message code="comentar" text="default text" /></button>
                            </div>
                        </form>
                    </div>
                </div>
                <%
                    }
                %>
                <hr />
                <div class="row">
                    <div class="col-lg-12">
                        <h3><spring:message code="comentarios" text="default text" /></h3>
                    </div>
                    <dl ng-repeat="comentario in comentarios">
                        <dt><a href="">{{comentario.usuario}}</a> dijo:</dt>
                        <dd>{{comentario.texto}}</dd>
                    </dl>
                </div>
            </div>

            <hr>

            <!-- Page Features -->
            <div class="row text-center" id="ultimos">
                <!-- Title -->
                <div class="row">
                    <div class="col-lg-12">
                        <h3><spring:message code="ultimos" text="default text" /></h3>
                    </div>
                </div>
                <!-- /.row -->
                <div class="col-md-4 col-sm-6 hero-feature" ng-repeat="libro in libros| filter:key">
                    <div class="thumbnail">
                        <div class="portada">
                            <img src="../{{libro.portada}}" alt="" class="portada">
                        </div>
                        <div class="caption">
                            <h3>{{libro.titulo}}</h3>
                            <!--<p>{{libro.descripcion}}</p>-->
                            <p>
                                <a href="" class="btn btn-primary" ng-click="detalles(libro.id)"><spring:message code="detalles" text="default text" /></a> 
                                <a target="_blank" href="<%=baseURL%>{{libro.link}}" class="btn btn-default"><spring:message code="descargar" text="default text" /></a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.row -->
            <hr />
            <div class="row text-center">
                <div class="col-lg-12" ng-show="glibros">
                    <h3>Libros en google</h3>
                </div>
                <div class="col-md-3 col-sm-6 hero-feature" ng-repeat="glibro in glibros| filter:key">
                    <div class="thumbnail">
                        <img src="{{glibro.volumeInfo.imageLinks.thumbnail}}" alt="" class="portada">
                        <div class="caption">
                            <h3>{{glibro.volumeInfo.title}}</h3>
                            <div>
                                <ul class="list-unstyled">
                                    <li ng-repeat="autor in glibro.volumeInfo.authors">{{autor}}</li>
                                </ul>
                            </div>
                            <p>
                                <a target="_blank" href="{{glibro.accessInfo.webReaderLink}}" class="btn btn-default">Ver producto</a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>

            <hr>

            <!-- Footer -->
            <footer>
                <div class="row">
                    <div class="col-lg-12">
                        <p>Ronald Darwin - 2015</p>
                    </div>
                </div>
            </footer>

        </div>
        <!-- /.container -->

        <!-- jQuery -->
        <script src="<%=baseURL%>vendor/jquery/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="<%=baseURL%>vendor/bootstrap/js/bootstrap.min.js"></script>

        <script type="text/javascript" src=<%=baseURL%>vendor/angular/angular.js></script>
        <script type="text/javascript" src=<%=baseURL%>vendor/angular/angular-route.js></script>
        <script type="text/javascript" src=<%=baseURL%>vendor/angular/angular-scroll.js></script>

        <script src="<%=baseURL%>js/index/index.js"></script>

    </body>

</html>
