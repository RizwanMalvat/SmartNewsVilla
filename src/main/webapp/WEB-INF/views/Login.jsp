<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>SmartSell</title>

        <link rel="shortcut icon" href="<%=request.getContextPath()%>/assets/images/logo_icon_light.png">
        <!-- Global stylesheets -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">
        <link href="<%=request.getContextPath()%>/assets/css/bootstrap.css" rel="stylesheet" type="text/css">
        <!-- /global stylesheets -->

    </head>

    <body class="login-container">

        <!-- Main navbar -->
        <div class="navbar navbar-inverse bg-indigo">
            <div class="navbar-header">
                <a class="navbar-brand" href="#"><img src="<%=request.getContextPath()%>/assets/images/muddy_logo.png" alt="Muddy"></a>

                <ul class="nav navbar-nav pull-right visible-xs-block">
                    <li><a data-toggle="collapse" data-target="#navbar-mobile"></a></li>
                </ul>
            </div>

        </div>
        <!-- /main navbar -->


        <!-- Page container -->
        <div class="page-container">

            <!-- Page content -->
            <div class="page-content">

                <!-- Main content -->
                <div class="content-wrapper">

                    <!-- Content area -->
                    <div class="content">
                        <div class="col-md-4"></div>
                        <div class="col-md-4">
                            <!-- Advanced login -->
                            <form action="<%= request.getContextPath()%>/authenticate" method="POST">
                            <!--<form name='loginForm' action="<c:url value='/login' />" method='POST'>-->   
                                <div class="panel panel-body login-form">
                                    <%if (request.getParameter("m") != null) {%>
                                    <%if (request.getParameter("m").equals("c")) {%>
                                    <div class="alert alert-success">
                                        <button type="button" class="close" data-dismiss="alert"><span>×</span><span class="sr-only">Close</span></button>
                                        Congrats,Your Account has been created successfully.
                                    </div>
                                    <%}%>
                                    <%}%>
                                    <div class="text-center">
                                        <div class="icon-object border-slate-300 text-slate-300"><img src="<%=request.getContextPath()%>/assets/images/logo_big.png"/></div>
                                        <h5 class="content-group">Login Panel</h5>
                                        <%--<c:if test="${not empty error}">
                                            <div class="error">${error}</div>
                                        </c:if>
                                        <c:if test="${not empty msg}">
                                            <div class="msg">${msg}</div>
                                        </c:if>--%>
                                        <%if (request.getAttribute("error") != null) {%>
                                        <div class="alert alert-danger">
                                            <p><%=request.getAttribute("error")%></p>
                                        </div>
                                        <%}%>
                                    </div>

                                    <div class="form-group has-feedback has-feedback-left">
                                        <input type="text" class="form-control" name="email" placeholder="Email">
                                        <div class="form-control-feedback">
                                            <i class="icon-user text-muted"></i>
                                        </div>
                                    </div>

                                    <div class="form-group has-feedback has-feedback-left">
                                        <input type="password" class="form-control" name="password" placeholder="Password">
                                        <div class="form-control-feedback">
                                            <i class="icon-lock2 text-muted"></i>
                                        </div>
                                    </div>

                                    <div class="form-group login-options">
                                        <div class="row">
                                            <!--                                        <div class="col-sm-6">
                                                                                        <label class="checkbox-inline">
                                                                                            <input type="checkbox" class="styled" checked="checked">
                                                                                            Remember
                                                                                        </label>
                                                                                    </div>-->

                                            <div class="col-sm-6 text-right">
                                                <a href="#">Forgot password?</a>
                                            </div>
                                        </div>
                                    </div>
                                    <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
                                    <div class="form-group">
                                        <button type="submit" class="btn bg-pink-400 btn-block">Login <i class="icon-arrow-right14 position-right"></i></button><br/><br/>
                                        <a class="btn bg-pink-400 btn-block" href = "<%= request.getContextPath()%>/SignUp/">Signup <i class="icon-arrow-right14 position-right"></i></a>
                                    </div>

                                </div>
                            </form>
                            <!-- /advanced login -->


                            <!-- Footer -->
                            <div class="footer text-muted text-center">
                                Powered by <a href="#" target="_blank">SmartSell Commercial</a>
                            </div>
                            <!-- /footer -->

                        </div>
                        <div class="col-lg-4"></div>
                    </div>
                    <!-- /content area -->

                </div>
                <!-- /main content -->

            </div>
            <!-- /page content -->

        </div>
        <!-- /page container -->

    </body>
</html>
