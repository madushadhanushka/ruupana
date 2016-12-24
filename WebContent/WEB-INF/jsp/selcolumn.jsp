<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<form action="selcontext" method="post">

<table id="simple-table" class="table  table-bordered table-hover">
<tr>
<c:forEach var="tableDatav" items="${tableHeader }">
<td>	<label class="pos-rel">
															<input type="checkbox" class="ace" />
															<span class="lbl"></span>
														</label></td>
</c:forEach>
</tr>
<c:forEach var="tableDatav" items="${tableData }">
<tr>
<c:forEach var="tableDatav2" items="${tableDatav }">
<td> ${tableDatav2}</td>
</c:forEach>
</tr>
</c:forEach>
</table>

<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" type="submit">
												<i class="ace-icon fa fa-check bigger-110"></i>
												Next
											</button>
											</div>
											</form>
<%@include file="footer.jsp" %>