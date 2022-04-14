<%--
  Created by IntelliJ IDEA.
  User: haunv
  Date: 06/04/2022
  Time: 1:27 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="/views/component/head.jsp"/>
<body>
<jsp:include page="/views/component/jqueryToast.jsp"/>
<div class="main-content container m-5 p-3">
    <button type="button" id="addNew" class="btn btn-primary mb-2" data-bs-toggle="modal"
            data-bs-target="#addDepartment">
        Add new
    </button>
    <a class="btn btn-primary mb-2">Admin dashboard</a>

    <script>
        $(document).ready(function () {
            jQuery('#addNew').click(function () {
                jQuery('#addDepartment .modal-title').html('Add department');
                jQuery('#addDepartment #form_action').html('Add');
                jQuery("#formDepartment input[name='departmentName']").val(null);
                jQuery("#formDepartment select[name='manager_id']").val(0);
            });
            jQuery('.editDepartment').click(function (e) {
                jQuery('#addDepartment .modal-title').html('Edit department');
                jQuery('#addDepartment #form_action').html('Edit');
                jQuery('#addDepartment').modal("show");

                const data = JSON.parse(e.target.dataset.edit.replaceAll("'", '"'));
                console.log(data);
                jQuery("#formDepartment input[name='id']").val(data.id);
                jQuery("#formDepartment input[name='departmentName']").val(data.departmentName);
                jQuery("#formDepartment select[name='manager_id']").val(data.manager != null ? data.manager.id : 0);
            });
        });

    </script>
    <!-- The Modal -->
    <div class="modal fade" id="addDepartment">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Add department</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <form action="/practice_2022_04_06_war_exploded/admin/departments" id="formDepartment"
                          method="post">
                        <input type="text" class="form-control d-none" name="id">
                        <div class="input-group input-group-sm mb-3">
                            <span class="input-group-text">Department name</span>
                            <input type="text" class="form-control" required name="departmentName">
                        </div>
                        <div class="input-group input-group-sm mb-3">
                            <span class="input-group-text">Manager</span>
                            <select class="form-control" name="manager_id">
                                <option value="0">None</option>
                                <c:forEach var="staff" items="${staffs}">
                                    <option value="${staff.id}">${staff.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button class="btn btn-outline-primary p-1" id="form_action">Add</button>
                        <span type="button" class="btn btn-outline-danger p-1 float-right"
                              data-bs-dismiss="modal">Close</span>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <table class="table table-hover table-bordered text-center">
        <thead style="background: azure;">
        <tr>

            <th>
                <span class="title">Department Id</span>
            </th>
            <th>
                <span class="price">Name</span>
            </th>
            <th>
                <span class="quantity">Manager</span>
            </th>
            <th>
                <span>Action</span>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${departments}">
            <tr>
                <td>
                    <span class="title">${item.id}</span>
                </td>
                <td>
                    <span class="title">${item.departmentName}</span>
                </td>
                <td>
                    <span class="price">${item.manager == null ? 'Chưa có': item.manager.name}</span>
                </td>
                <td>
                    <span data-edit="${item.toJson()}" class="btn btn-outline-primary editDepartment">Edit</span>
                    <a href="/practice_2022_04_06_war_exploded/admin/departments/delete?id=${item.getId()}" class="btn btn-outline-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</body>
</html>
