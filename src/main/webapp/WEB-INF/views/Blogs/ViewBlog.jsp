<%@page import="com.smartnewsvillaadmin.utilities.Constant"%>
<%@page import="com.smartnewsvillaadmin.entities.Blogs"%>
<%@page import="com.smartnewsvillaadmin.utilities.DateUtils"%>
<%@page import="com.smartnewsvillaadmin.utilities.CheckInput"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@include file="../Template/Header.jsp" %>

<section class="page-content">
    <div class="page-content-inner">
        <div class="margin-bottom-20">
<!--            <ul class="list-unstyled breadcrumb">
                <li>
                    <a href="<%=request.getContextPath()%>/Dashboard/">Dashboard</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/Blogs/">Blogs</a>
                </li>
                <li>
                    <span>View</span>
                </li>
            </ul>-->
        </div>
        <!-- Modal -->
        <section class="panel">
            <%  DateUtils dateUtils = new DateUtils();
                CheckInput checkInput = new CheckInput();
                Blogs blogs = null;
                if (request.getAttribute("blogs") != null) {
                    blogs = (Blogs) request.getAttribute("blogs");
                    if (blogs != null) {
            %>
            <div class="panel-body">
                <div class="row">
                    <div class="col-xl-12">
                        <section class="panel profile-user-content">
                            <div class="panel-body">
                                <div class="nav-tabs-horizontal">
                                    <div class="tab-pane active" id="settings" role="tabpanel">


                                        <br />
                                        <h5>Blog Details</h5>
                                        <hr/>
                                        <div class="row"><br/>
                                            <div class="col-lg-12">
                                                <div class="form-group">
                                                    <label class="form-control-label" for="l0">Blog Title: </label>
                                                    <label class="form-control-label font-weight-300" for="l0"><%= checkInput.checkValue(blogs.getBlogtitle())%></label>

                                                </div>
                                                <hr>
                                            </div>
                                        </div>
                                        <div class="row"><br/>
                                            <div class="col-lg-12">
                                                <div class="form-group">
                                                    <label class="form-control-label" for="l0">Blog Details: </label>
                                                    <label class="form-control-label font-weight-300" for="l0"><%= checkInput.checkValue(blogs.getBlogdescription())%></label>

                                                </div>
                                                <hr>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <div class="col-lg-4">
                                                    <img style="max-height:200px !important ; max-width: 300px !important;" src="<%= request.getContextPath()%>/ImageAccess/public/<%= blogs.getBlogimage()%>/" />
                                                </div>
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="row"><br/>
                                            <div class="col-lg-12">
                                                <div class="form-group">
                                                    <label class="form-control-label" for="l1">Blog Content: </label>
                                                    <label class="form-control-label font-weight-300" for="l0"><%= checkInput.checkValue(blogs.getBlogdetails())%></label>
                                                </div>
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="row">
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <label class="form-control-label" for="l1">Created By: </label>
                                                    <label class="form-control-label font-weight-300" for="l0"><%= checkInput.checkValue(blogs.getCreatedby())%></label>
                                                </div>
                                                <hr>
                                            </div>
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <label class="form-control-label" for="l1">Status: </label>
                                                    <label class="form-control-label font-weight-300" for="l0"><%= checkInput.checkValue(blogs.getStatus())%></label>
                                                </div>
                                                <hr>
                                            </div>
                                        </div>
                                        <br />
                                        <h5> Audit Fields</h5>
                                        <div class="row">
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <label class="form-control-label" for="l3">Created Date: </label>
                                                    <label class="form-control-label font-weight-300" for="l0"><%= blogs.getCreateddate() != null ? dateUtils.dateWithFormat(blogs.getCreateddate(), Constant.FULL_DATE_FORMAT) : ""%></label>
                                                </div>
                                                <hr>
                                            </div>
                                            <div class="col-lg-6">
                                                <div class="form-group">
                                                    <label class="form-control-label" for="l3">Modified Date: </label>
                                                    <label class="form-control-label font-weight-300" for="l0"><%= blogs.getModifieddate() != null ? dateUtils.dateWithFormat(blogs.getModifieddate(), Constant.FULL_DATE_FORMAT) : ""%></label>
                                                </div>
                                                <hr>
                                            </div>
                                        </div>
                                        <div class="form-actions">
                                            <div class="form-group">
                                                <a href="<%=request.getContextPath()%>/Blogs/" class="btn btn-default">Back</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
        </section>
    </div>
    <%}%>
    <%}%>
</section>
<%@include file="../Template/Footer.jsp" %>
