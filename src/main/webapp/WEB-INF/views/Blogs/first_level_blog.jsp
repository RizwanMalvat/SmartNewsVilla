
<%@page import="com.smartnewsvillaadmin.utilities.CheckInput"%>
<%@page import="com.smartnewsvillaadmin.entities.Blogs"%>
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
                    <i class="fa fa-edit"></i> First Level Blog
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
            <% Long menuid = 0L;
                if (request.getAttribute("menuid") != null) {
                    menuid = (Long) request.getAttribute("menuid");
                }%>
            <form role="form"id="form-validation-simple" action="<%= request.getContextPath()%>/BlogsController/firstlevelmenu/addblog/<%= menuid%>" name="form-validation-simple" method="POST" enctype="multipart/form-data">
                <div class="col-lg-12"> 
                    <div class="col-lg-6"> 
                        <% CheckInput checkInput = new CheckInput();%>
                        <div class="form-group">
                            <div class="col-lg-4">
                                <label>Blog Title</label>
                            </div>
                            <div class="col-lg-8">
                                <input class="form-control" name="blogname" <%= checkInput.checkValue(request.getParameter("blogname"))%> placeholder="Enter blog title">
                            </div>
                        </div>
                        <div class="form-group"><br/><br/>
                            <div class="col-lg-4">
                                <label>Blog Image</label>
                            </div>
                            <div class="col-lg-8">
                                <input type="file" name="fileUpload">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <div class="col-lg-4">
                                <label>Blog Description</label>
                            </div>
                            <div class="col-lg-8">
                                <textarea  name="blogdescription"  rows="4" id="blogdescription"  class="form-control"><%= checkInput.checkValue(request.getParameter("blogdescription"))%></textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-12"><br/>
                    <div class="form-group">
                        <div class="col-lg-2">
                            <label>Blog Details</label>
                        </div>
                        <div class="col-lg-10">
                            <textarea cols="5" rows="5" class="form-control" id="editor-inline" name="content" placeholder="Enter Blog Content"><%=checkInput.checkValue(request.getParameter("content"))%></textarea>
                        </div>
                    </div>
                </div>
                <div class="col-lg-12 text-center"><br/>
                    <button type="submit" class="btn btn-default">Add</button>
                </div>
            </form> 
        </div>
        <div class="row"><br/><br/><hr/><br/>

            <table id="first_level_blog" class="display" cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th>Blog Id</th>
                        <th>Blog Title</th>
                        <th>Blog Description</th>
                        <th>Blog Image</th>
                        <!--<th>Blog Details</th>-->
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>Blog Id</th>
                        <th>Blog Title</th>
                        <th>Blog Description</th>
                        <th>Blog Image</th>
                        <!--<th>Blog Details</th>-->
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </tfoot>
                <tbody>

                    <%
                        if (request.getAttribute("blogs") != null) {
                            List<Blogs> blogss = (List<Blogs>) request.getAttribute("blogs");
                            if (!blogss.isEmpty()) {
                                for (Blogs blogs : blogss) {%>
                    <tr>
                        <td><%= checkInput.checkValue(blogs.getBlogid())%></td>
                        <td><%= checkInput.checkValue(blogs.getBlogtitle())%></td>
                        <td><%= checkInput.checkValue(blogs.getBlogdescription())%></td>
                        <td><%= checkInput.checkValue(blogs.getBlogimage())%></td>
                        <td><%= checkInput.checkValue(blogs.getStatus())%></td>
                        <%--<td><%= checkInput.checkValue(blogs.getBlogdetails())%></td>--%>
                        <td>
                            <a class="btn btn-default" target="_blank" href="<%=request.getContextPath()%>/BlogsController/view/<%=blogs.getBlogid()%>">  View Blogs</a>
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
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/ckeditor/ckeditor.js"></script>
<script>
    $(document).ready(function () {
        $('#first_level_blog').DataTable();
    });
    $(function () {
        CKEDITOR.replace('editor-inline');
        CKEDITOR.disableAutoInline = true;

//        CKEDITOR.inline('editor-inline');

        $('#priority').select2({
            minimumResultsForSearch: Infinity,
            width: '100%'
        });
    });
</script>