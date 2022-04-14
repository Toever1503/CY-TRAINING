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
                jQuery('#addDepartment .modal-title').html('Add Staff');
                jQuery('#addDepartment #form_action').html('Add');
                jQuery("#formDepartment input[name='id']").val(null);
                jQuery("#formDepartment input[name='name']").val(null);
                jQuery("#formDepartment input[name='age']").val(null);
                jQuery("#formDepartment input[name='salary']").val(null);
                jQuery("#formDepartment select[name='pos_id']").val(null);
                jQuery("#formDepartment select[name='depart_id']").val(null);
                jQuery("#formDepartment img").attr('src',null);
            });
            jQuery('.editDepartment').click(function (e) {
                jQuery('#addDepartment .modal-title').html('Edit Staff');
                jQuery('#addDepartment #form_action').html('Edit');
                jQuery('#addDepartment').modal("show");
                const data = JSON.parse(e.target.dataset.edit.replaceAll("'", '"'));
                console.log(data);

                jQuery("#formDepartment input[name='id']").val(data.id);
                jQuery("#formDepartment input[name='name']").val(data.name);
                jQuery("#formDepartment input[name='age']").val(data.age);
                jQuery("#formDepartment input[name='salary']").val(data.salary);
                jQuery("#formDepartment select[name='pos_id']").val(data.staffPos != null ? data.staffPos.id : 0);
                jQuery("#formDepartment select[name='depart_id']").val(data.staffDepartment != null ? data.staffDepartment.id : 0);
                jQuery("#formDepartment img").attr('src', data.image != null ? data.image : 0);
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
                    <form action="/practice_2022_04_06_war_exploded/admin/staffs" id="formDepartment"
                          method="post" enctype="multipart/form-data">
                        <input type="text" class="form-control d-none" name="id">
                        <div class="input-group input-group-sm mb-3">
                            <span class="input-group-text">Staff name</span>
                            <input type="text" class="form-control" required name="name">
                        </div>
                        <div class="input-group input-group-sm mb-3">
                            <span class="input-group-text">Age</span>
                            <input type="number" class="form-control" required name="age">
                        </div>
                        <div class="input-group input-group-sm mb-3">
                            <span class="input-group-text">Salary</span>
                            <input type="number" step="0.1" class="form-control" required name="salary">
                        </div>
                        <div class="input-group input-group-sm mb-3">
                            <span class="input-group-text">Position</span>
                            <select class="form-control" name="pos_id">
                                <c:forEach var="pos" items="${positions}">
                                    <option value="${pos.id}">${pos.posName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-group input-group-sm mb-3">
                            <span class="input-group-text">Department</span>
                            <select class="form-control" name="depart_id">
                                <c:forEach var="department" items="${departments}">
                                    <option value="${department.id}">${department.departmentName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-group input-group-sm mb-3">
                            <span class="input-group-text">Avatar</span>
                            <input type="file" class="form-control" name="avatar">
                            <img src="" width="100" height="120" alt="">
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
                <span class="staffId">Staff Id</span>
            </th>
            <th></th>
            <th>
                <span class="staffName">Name</span>
            </th>
            <th>
                <span class="staffAge">Age</span>
            </th>
            <th>
                <span class="staffWorkDates">Total work dates</span>
            </th>
            <th>
                <span class="staffSalary">Salary</span>
            </th>
            <th>
                <span class="staffSalary">Department</span>
            </th>
            <th>
                <span class="staffSalary">Position</span>
            </th>
            <th>
                <span>Action</span>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${staffs}">
            <tr>
                <td>
                    <span class="staffId">${item.id}</span>
                </td>
                <th>
                    <img class="staffImage" src="${item.image}" height="50" width="50" alt="">
                </th>
                <td>
                    <span class="staffName">${item.name}</span>
                </td>
                <td>
                    <span class="staffAge">${item.age}</span>
                </td>
                <td>
                    <span class="staffWorkDates">${item.workDates}</span>
                </td>
                <td>
                    <span class="staffSalary">${item.salary}</span>
                </td>
                <th>
                    <span class="staffDepartment">${item.staffDepartment == null ? 'Not have' : item.staffDepartment.departmentName}</span>
                </th>
                <th>
                    <span class="staffPosition">${item.staffPos == null ? 'Not have' : item.staffPos.posName}</span>
                </th>
                <td>
                    <span data-edit="${item.toJson()}" class="btn btn-outline-primary editDepartment">Edit</span>
                    <a href="/practice_2022_04_06_war_exploded/admin/staffs/delete?id=${item.getId()}" class="btn btn-outline-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</body>
</html>
