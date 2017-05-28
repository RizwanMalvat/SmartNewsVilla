
<%@page import="com.smartnewsvillaadmin.utilities.CheckInput"%>
<%@page import="com.smartnewsvillaadmin.entities.FirstLevelMenu"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<jsp:include page="../Template/Header.jsp"></jsp:include>
    <div id="page-wrapper">

        <link href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css" rel="stylesheet">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    Menus
                </h1>
                <ol class="breadcrumb">
                    <li>
                        <i class="fa fa-dashboard"></i>  <a href="<%= request.getContextPath()%>/Menus/">Menus</a>
                </li>
                <li class="active">
                    <i class="fa fa-edit"></i> Second Level Menu
                </li>
            </ol>
        </div>
    </div>
    <!-- /.row -->

    <div class="container-fluid">
        <%if (request.getParameter("m") != null) {%>
        <%if (request.getParameter("m").equals("c")) {%>
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert"><span>×</span><span class="sr-only">Close</span></button>
            Record has been successfully created.
        </div>
        <%} else if (request.getParameter("m").equals("e")) {%>
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert"><span>×</span><span class="sr-only">Close</span></button>
            Record has been successfully updated.
        </div>
        <%} else if (request.getParameter("m").equals("d")) {%>
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert"><span>×</span><span class="sr-only">Close</span></button>
            Record has been successfully deleted.
        </div>
        <%} else if (request.getParameter("m").equals("notfound")) {%>
        <div class="alert alert-danger">
            <button type="button" class="close" data-dismiss="alert"><span>×</span><span class="sr-only">Close</span></button>
            Please provide proper Productid.
        </div> 
        <%} else if (request.getParameter("m").equals("pub")) {%>
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert"><span>×</span><span class="sr-only">Close</span></button>
            Product status has been changed to Online successfully.
        </div> 
        <%} else if (request.getParameter("m").equals("wid")) {%>
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert"><span>×</span><span class="sr-only">Close</span></button>
            Product status has been changed to Offline successfully.
        </div> 
        <%}%>
        <%}%>
        <%if (request.getAttribute("errors") != null) {
                List<String> error = (List<String>) request.getAttribute("errors");
                if (!error.isEmpty()) {
                    Iterator itr = error.iterator();
        %>
        <div class="alert alert-danger">
            <button type="button" class="close" data-dismiss="alert"><span>×</span><span class="sr-only">Close</span></button>
            <%                 while (itr.hasNext()) {%>
            <%=itr.next()%><br/>
            <%}%>
        </div>
        <%}%>
        <%}%>
        <div class="row">
            <% Long parentmenuid = 0L;
                if (request.getAttribute("parentmenuid") != null) {
                    parentmenuid = (Long) request.getAttribute("parentmenuid");%>
            <form role="form"id="form-validation-simple" action="<%= request.getContextPath()%>/Menus/secondlevelmenu/addmenu/<%=  parentmenuid  %>" name="form-validation-simple" method="POST">
                <div class="col-lg-6"> 
                    <div class="form-group">
                        <div class="col-lg-4">
                            <label>Second level menu name</label>
                        </div>
                        <div class="col-lg-8">
                            <input class="form-control" name="menuname" placeholder="Enter second level menu name">
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-group">
                        <div class="col-lg-4">
                            <label>Second level menu path</label>
                        </div>
                        <div class="col-lg-8">
                            <input class="form-control" name="menupath" placeholder="Enter second level menu path">
                        </div>
                    </div>
                </div>
                <div class="col-lg-12 text-center"><br/>
                    <button type="submit" class="btn btn-default">Add</button>
                </div>
            </form> 
            <%}%>
        </div>
        <div class="row"><br/><br/><hr/><br/>

            <table id="second_level_menu" class="display" cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th>Menu Name</th>
                        <th>Menu Path</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>Menu Name</th>
                        <th>Menu Path</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </tfoot>
                <tbody>

                    <%  CheckInput checkInput = new CheckInput();
                        if (request.getAttribute("firstlevelmenu") != null) {
                            List<FirstLevelMenu> secondlevelmenus = (List<FirstLevelMenu>) request.getAttribute("firstlevelmenu");
                            if (!secondlevelmenus.isEmpty()) {
                                for (FirstLevelMenu secondlevelmenu : secondlevelmenus) {%>
                    <tr>
                        <td><%= checkInput.checkValue(secondlevelmenu.getMenuname())%></td>
                        <td><%= checkInput.checkValue(secondlevelmenu.getMenuname())%></td>
                        <td><%= checkInput.checkValue(secondlevelmenu.getMenuname())%></td>
                        <td>
                            <a class="btn btn-default" href="<%=request.getContextPath()%>/Menus/thirdmenu/<%=secondlevelmenu.getFirstmenuid()%>">  Add Menu</a>
                            <a class="btn btn-default" href="<%=request.getContextPath()%>/Developer/BusinessProduct/bid/<%=secondlevelmenu.getFirstmenuid()%>">  Add Blog</a>
                        </td>
                    </tr>
                    <%
                                }
                            }
                        }%>
                </tbody>
            </table>
        </div>
        <!-- /.row -->

    </div>
    <!-- /.container-fluid -->

</div>
<!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<jsp:include page="../Template/Footer.jsp"></jsp:include>
<script src="//code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script>
    $(document).ready(function () {
        $('#second_level_menu').DataTable();
    });
</script>