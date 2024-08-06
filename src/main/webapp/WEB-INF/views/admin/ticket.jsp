<%@page import="com.laptrinhjavaweb.entity.UserEntity"%>
<%@page import="com.laptrinhjavaweb.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dec"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Quản lý vé</title>
</head>

<body>

	<div class="dashboard-wrapper">
		<div class="dashboard-ecommerce">
			<div class="container-fluid dashboard-content ">
				<div class="row">
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="page-header">
							<h2 class="pageheader-title">Quản lý vé xem phim</h2>
							<div class="page-breadcrumb">
								<nav aria-label="breadcrumb">
									<ol class="breadcrumb">
										<li class="breadcrumb-item"><a href="#"
											class="breadcrumb-link">Home</a></li>
										<li class="breadcrumb-item active" aria-current="page">Quản
											lý vé xem phim</li>
									</ol>
								</nav>
							</div>
						</div>
					</div>
				</div>
				<div class="ecommerce-widget">
					<div class="box box-primary">
						<div class="box-body">
							<form action='<c:url value = '/admin/ticket'/>' method="get">
								<div class="form-row d-flex align-items-center">
									<div class="form-group col-sm-3">

										<!-- Lấy trạng thái từ request -->
										<c:set var="searchStatus" value="${requestScope.searchStatus}" />

										<select class="form-control" id="status" name="status">
											<option value=""
												<c:if test="${empty searchStatus}">selected</c:if>>--Trạng
												thái--</option>
											<option value="1"
												<c:if test="${searchStatus == '1'}">selected</c:if>>Thành
												công</option>
											<option value="0"
												<c:if test="${searchStatus == '0'}">selected</c:if>>Đã
												hủy</option>
											<option value="2"
												<c:if test="${searchStatus == '2'}">selected</c:if>>Không
												thành công</option>
										</select>
									</div>
									<!-- <div class="form-group col-sm-3">
                                            <input type="text" name="daterange" class="form-control daterange" value="" placeholder="" />
                                        </div> -->
									<div class="form-group col-sm-9">
										<div class="input-group">
											<input type="text" id="name" value="${key}" name="txtsearch"
												class="form-control"
												placeholder="Nhập tên khách hàng hoặc phim cần tìm"
												autofocus>
											<div class="input-group-append">
												<button name="but1" class="btn btn-info" type="submit">
													<i class="fa fa-search"></i>
												</button>

											</div>
										</div>
									</div>
								</div>
							</form>
							<div>
								<c:if test="${not empty listticket}">
									<p style="margin: 10px 0 10px 0">
										Có <strong>${totalrow }</strong> vé trong tổng số <strong>${totalPages}</strong>
										trang
									</p>
								</c:if>
								<div class="table-responsive">
									<table class="table table-bordered table-hover table-striped">
										<thead>
											<tr class="bg-primary" style="text-align: center;">
												<th>Khách hàng</th>
												<th>Phim</th>
												<th>Suất chiếu</th>
												<th>Ngày đặt</th>
												<!-- <th>Combo</th> -->
												<th>Tổng tiền</th>
												<th>Trạng thái</th>
												<!-- <th>Thanh toán</th> -->
												<th style="width: 6%"></th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${empty listticket}">
												<p>Danh sách vé trống.</p>
											</c:if>
											<%
											int id = 1;
											%>
											<c:if test="${not empty listticket}">
												<c:forEach items="${listticket}" var="ticket">

													<c:if test="${ticket.getId_Ticket() != id}">
														<%
														id = id++;
														%>
														<tr style="text-align: center;">

															<td>${ticket.getFullName()}</td>
															<td>${ticket.getMovieName()}</td>
															<td><fmt:formatDate value="${ticket.getBeginTime()}"
																	pattern="hh:mm" /></td>
															<td><fmt:formatDate
																	value="${ticket.getBookingTime()}" pattern="dd/MM/yyyy" /></td>

															<%-- <c:if test="${ticket.getComboName() eq null}">
																<td>Không có</td>
															</c:if> --%>

															<%-- <c:if test="${ticket.getComboName() ne null}">
																<td>${ticket.getComboName()}</td>
															</c:if> --%>


															<td><fmt:formatNumber
																	value="${ticket.getTotalAmount()}" type="currency"
																	currencySymbol="đ" /></td>
															<td><c:choose>
																	<c:when test="${ticket.getStatus() == 1 }">Thành công</c:when>
																	<c:when test="${ticket.getStatus() == 0 }">Đã hủy</c:when>
																	<c:when test="${ticket.getStatus() == 2 }">Thất bại</c:when>
																</c:choose></td>
															<%--<td> <c:choose>
																	<c:when test="${ticket.getRefund() == false}">Đã thanh toán</c:when>
																	<c:when test="${ticket.getRefund() == true }">Chưa thanh toán</c:when>
																	<c:when test="${ticket.getStatus() == 1 && ticket.getRefund() == false }">Đã hoàn tiền</c:when>
																</c:choose> </td>--%>
															<td class="text-center"><a href='#'
																data-toggle="modal"
																data-target="#myModal${ticket.getId_Ticket() }"
																class="btn btn-xs"
																style="width: 35px; background-color: rgb(106, 167, 237); border-radius: 5px; color: aliceblue;">
																	<i class="fa fa-list"></i>
															</a></td>
														</tr>
													</c:if>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
									<!-- The Modal -->
									<c:forEach items="${listticket}" var="ticket">
										<div class="modal fade" id="myModal${ticket.getId_Ticket()}">
											<div class="modal-dialog">
												<div class="modal-content">
													<!-- Modal Header -->
													<div class="modal-header">
														<h4 class="modal-title ">Chi tiết hóa đơn bán vé</h4>
														<button type="button" class="close" data-dismiss="modal">×</button>
													</div>

													<!-- Modal body -->
													<div class="modal-body">
														<table class="table text-left">
															<tbody>
																<tr>
																	<th scope="row">Người đặt :</th>
																	<td>${ticket.getFullName()}</td>
																</tr>
																<!-- <tr>
																		<th scope="row">Ngày đặt:</th>
																		<td>12/23/2023 6:04:15 PM</td>
																	</tr> -->
																<tr>
																	<th scope="row">Ngày đặt vé:</th>
																	<td><fmt:formatDate
																			value="${ticket.getBookingTime()}"
																			pattern="dd/MM/yyyy" /></td>
																</tr>
																<tr>
																	<th scope="row">Tổng tiền:</th>
																	<td><fmt:formatNumber
																			value="${ticket.getTotalAmount()}" type="currency"
																			currencySymbol="đ" /></td>
																</tr>
																<tr>
																	<th scope="row">Mã giao dịch:</th>
																	<td>${ticket.getTicketCode()}</td>
																</tr>
																<tr>
																	<th scope="row">Tên phim:</th>
																	<td>${ticket.getMovieName()}</td>
																</tr>
																<tr>
																	<th scope="row">Ghế:</th>
																	<td>
																		<%-- <c:set var="temp" value="false"/>
																			<c:forEach items="${getdetail}" var="detail">
																			    <c:if test="${detail.getId_Ticket() eq ticket.getId_Ticket()}">
																			        <c:if test="${temp}">,</c:if>
																			        ${detail.getChairCode()}
																			        <c:set var="temp" value="true"/>
																			    </c:if>
																			</c:forEach><c:set var="temp" value="false" /> --%> <%-- <c:forEach items="${chairTicket}" var="chairTicket">
																		 	<c:forEach items="${chairtime}" var="chairtime">
																		 		<c:forEach items="${chair}" var="chair">
																					<c:if test="${chairTicket.getIdTicket() eq ticket.getId_Ticket()}">
																						<c:if test="${chairTicket.getIdChairTime() eq chairtime.getIdChairTime()}">
																							<c:if test="${chairtime.getChair() eq chair.getIdCinemaChair()}">
																								<c:if test="${temp}">,</c:if>
																			                        ${chair.getChairCode()}
																			                        <c:set var="temp" value="true" />
																								</c:if>
																							</c:if>
																						</c:if>
																				</c:forEach>
																			</c:forEach>
																		</c:forEach> --%> 
																		<c:set var="temp" value="false" /> <c:forEach
																			items="${uniqueChair}" var="chair">
																			<c:if
																				test="${chair.getId_Ticket() eq ticket.getId_Ticket()}">
																				<c:if test="${temp}">,</c:if>
																		        	${chair.getChairCode()}
																		        <c:set var="temp" value="true" />
																			</c:if>
																		</c:forEach>
																	</td>
																</tr>
																<tr>
																	<th scope="row">Combo:</th>
																	<td><c:set var="temp" value="false" /> <c:forEach
																			items="${comboTicket }" var="comboTicket">
																			<c:if
																				test="${comboTicket.getIdTicket() eq ticket.getId_Ticket() }">
																				<c:forEach items="${combo }" var="combo">
																					<c:if
																						test="${comboTicket.getIdCombo() eq combo.getIdcombo() }">
																						<c:if test="${temp}">,</c:if>
																						<c:choose>
																							<c:when test="${combo.getComboname() eq null}">
			                    																	Không có
			                																</c:when>
																										<c:otherwise>
			                    																			${combo.getComboname()}
			                																			</c:otherwise>
																									</c:choose>
																						<c:set var="temp" value="true" />
																					</c:if>
																					
																				</c:forEach>
																			</c:if>
																		</c:forEach></td>
																</tr>
																<tr>
																	<th scope="row">Trạng Thái:</th>
																	<td><c:choose>
																			<c:when test="${ticket.getStatus() == 1 }">Đặt vé thành công</c:when>
																			<c:when test="${ticket.getStatus() == 0 }">Đã hủy vé</c:when>
																			<c:when test="${ticket.getStatus() == 2 }">Đặt vé thất bại</c:when>
																		</c:choose></td>
																</tr>
																<%--<tr>
																	<th scope="row">Thanh toán:</th>
																	 <td><c:choose>
																			<c:when test="${ticket.getRefund() == true}">Đã hoàn tiền</c:when>
																			<c:when
																				test="${ticket.getRefund() == false && ticket.getStatus() == 0 }">Chưa hoàn tiền</c:when>
																			<c:when test="${ticket.getStatus() == 0 }">Chưa thanh toán</c:when>
																		</c:choose></td> 
																</tr>--%>
															</tbody>
														</table>
													</div>
													<!-- Modal footer -->
													<div class="modal-footer">
														<button type="button" class="btn btn-danger"
															data-dismiss="modal">Close</button>
													</div>

												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
							<c:choose>
								<c:when test="${not empty listticket}">
									<div class="row"
										style="justify-content: center; margin-top: 24px;">
										<ul class="pagination">
											<li class="page-item"><a class="page-link"
												href="?page=0&amp;status=${param.status}&amp;txtsearch=${param.txtsearch}"><i
													class="fas fa-angle-double-left"></i></a></li>
											<c:if test="${currentPage - 1 >= 0}">
												<li class="page-item"><a class="page-link"
													href="?page=${currentPage - 1}&amp;status=${param.status}&amp;txtsearch=${param.txtsearch}"><i
														class="fas fa-angle-left"></i></a></li>
											</c:if>

											<c:set var="startPage" value="${currentPage - 1}" />
											<c:if test="${startPage lt 0}">
												<c:set var="startPage" value="0" />
											</c:if>
											<c:set var="endPage" value="${currentPage + 1}" />
											<c:if test="${endPage ge totalPages}">
												<c:set var="endPage" value="${totalPages - 1}" />
											</c:if>

											<c:if test="${totalPages >= 3}">
												<c:choose>
													<c:when test="${currentPage == 0}">
														<c:set var="endPage" value="${startPage + 2}" />
													</c:when>
													<c:when test="${currentPage == totalPages - 1}">
														<c:set var="startPage" value="${endPage - 2}" />
													</c:when>
												</c:choose>
											</c:if>

											<c:forEach var="pageIndex" begin="${startPage}"
												end="${endPage}" step="1">
												<c:set var="pageNumber" value="${pageIndex + 1}" />
												<li
													class="page-item ${pageIndex == currentPage ? 'active' : ''}">
													<a class="page-link"
													href="?page=${pageIndex}&amp;status=${param.status}&amp;txtsearch=${param.txtsearch}">${pageNumber}</a>
												</li>
											</c:forEach>

											<c:if test="${currentPage + 1 < totalPages}">
												<li class="page-item"><a class="page-link"
													href="?page=${currentPage + 1}&amp;status=${param.status}&amp;txtsearch=${param.txtsearch}"><i
														class="fas fa-angle-right"></i></a></li>
											</c:if>
											<li class="page-item"><a class="page-link"
												href="?page=${totalPages - 1}&amp;status=${param.status}&amp;txtsearch=${param.txtsearch}"><i
													class="fas fa-angle-double-right"></i></a></li>
										</ul>
									</div>
								</c:when>
								<c:otherwise>
									<div class="text-center" role="alert">Không có kết quả
										nào được tìm thấy.</div>
								</c:otherwise>
							</c:choose>


							<%-- <div class="row" style="justify-content: center; margin-top: 24px;">
                                    <ul class="pagination">
                                        <li class="page-item">
                                            <a class="page-link" href="?page=0&amp;txtsearch=${param.txtsearch}"><i class="fas fa-angle-double-left"></i></a>
                                        </li>
                                        <li class="page-item">
                                        	<c:if test="${currentPage -1 == 0 or totalPages - 1 <= totalPages}">
                                            <a class="page-link" href="?page=${currentPage - 1}&amp;txtsearch=${param.txtsearch}"><i class="fas fa-angle-left"></i></a>
                                        	</c:if>
                                        </li>
                                        <c:forEach var="pageIndex" begin="1" end="${totalPages}" step="1">
                                            <c:set var="pageNumber" value="${pageIndex}" />
                                            <li class="page-item ${pageIndex == currentPage + 1 ? 'active' : ''}">
                                                <a class="page-link" href="?page=${pageIndex-1}&amp;txtsearch=${param.txtsearch}">${pageNumber}</a>
                                            </li>
                                        </c:forEach>
                                        <li class="page-item">
                                        <c:if test="${currentPage -1 < totalPages -2}">
                                            <a class="page-link" href="?page=${currentPage + 1}&amp;txtsearch=${param.txtsearch}"><i class="fas fa-angle-right"></i></a>
                                        </c:if>
                                        </li>
                                        <li class="page-item">
                                            <a class="page-link" href="?page=${totalPages - 1}&amp;txtsearch=${param.txtsearch}"><i class="fas fa-angle-double-right"></i></a>
                                        </li>
                                    </ul>
                                </div> --%>

							<%-- <div class="row" style="justify-content: center; margin-top: 24px;">
			                        <ul class="pagination">
			                            <li class="page-item ${currentPage == 0 ? 'disabled' : ''}">
			                                <a class="page-link" href="?page=0">&laquo;</a>
			                            </li>
			                            <c:forEach var="i" begin="1" end="${totalPages}" step="1">
			                                <li class="page-item ${i - 1 == currentPage ? 'active' : ''}">
			                                    <a class="page-link" href="?page=${i - 1}">${i}</a>
			                                </li>
			                            </c:forEach>
			                            <li class="page-item ${currentPage == totalPages - 1 ? 'disabled' : ''}">
			                                <a class="page-link" href="?page=${totalPages - 1}">&raquo;</a>
			                            </li>
			                        </ul>
			                    </div> --%>
						</div>
					</div>
				</div>
			</div>
		</div>


	</div>
	<!-- jquery 3.3.1 -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

	<script
		src="<c:url value='/template/assets/vendor/jquery/jquery-3.3.1.min.js'/>"></script>
	<script
		src="<c:url value='/template/assets/vendor/bootstrap/js/bootstrap.bundle.js'/>"></script>
	<script
		src="<c:url value='/template/assets/vendor/slimscroll/jquery.slimscroll.js'/>"></script>
	<script src="<c:url value='/template/assets/libs/js/main-js.js'/>"></script>
	<script
		src="<c:url value='/template/assets/vendor/charts/chartist-bundle/chartist.min.js'/>"></script>
	<script
		src="<c:url value='/template/assets/vendor/charts/sparkline/jquery.sparkline.js'/>"></script>
	<script
		src="<c:url value='/template/assets/vendor/charts/morris-bundle/raphael.min.js'/>"></script>
	<script
		src="<c:url value='/template/assets/vendor/charts/morris-bundle/morris.js'/>"></script>
	<script
		src="<c:url value='/template/assets/vendor/charts/c3charts/c3.min.js'/>"></script>
	<script
		src="<c:url value='/template/assets/vendor/charts/c3charts/d3-5.4.0.min.js'/>"></script>
	<script
		src="<c:url value='/template/assets/vendor/charts/c3charts/C3chartjs.js'/>"></script>
	<script
		src="<c:url value='/template/assets/libs/js/dashboard-ecommerce.js'/>"></script>

	<script>
		$(function() {
			$('input[name="daterange"]').daterangepicker({
				timePicker : true,
				startDate : moment().subtract(1, 'months').startOf('hour'),
				endDate : moment().startOf('hour'),
				locale : {
					format : 'DD/MM/YYYY'
				}
			});
		});
	</script>
</body>

</html>