<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<form action="selfeedback.jsp" method="post">
<table id="simple-table" class="table  table-bordered table-hover">
											<thead>
												<tr>
													<th class="center">
														Column
													</th>
													<th >type</th>
													
												</tr>
											</thead>

											<tbody>
											<% int i=0; %>
												<c:forEach var="headerv" items="${tableHeader }">
												<tr>
												<td>${headerv[0]}</td>
												<td>
												<div>
															<select class="form-control" id="form-field-select-1">
																<option value="${headerv[1]}" selected>${headerv[2]}</option>
																<option value="1">Integer</option>
																<option value="2">Float</option>
																<option value="3">String</option>
																<option value="4">Percentage</option>
																<option value="5">Ordinal</option>
																<option value="6">Date and Time</option>
																<option value="7">Location</option>
																<option value="8">Nominal</option>
																
															</select>
														</div>
												 </td>
												</tr>
										
									<% i++; %>
										</c:forEach>
		
											</tbody>
										</table>
										<label class="pos-rel">Intension</label>
										<div>
										
															<select class="form-control" id="form-field-select-1">
																<option value="" selected>Relationship</option>
																<option value="AL">Comparison</option>
																<option value="AK">Distribution</option>
																<option value="AZ">Composition</option>
															
																
															</select>
														</div>
														<div></div>
<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" type="submit">
												<i class="ace-icon fa fa-check bigger-110"></i>
												Next
											</button>
											</div>
											</form>
<%@include file="footer.jsp" %>