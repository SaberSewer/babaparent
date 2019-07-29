<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>babasport-list</title>
</head>
<body>
<div class="box-positon">
    <div class="rpos">当前位置: 品牌管理 - 列表</div>
    <form class="ropt">
        <input class="add" type="button" value="添加" onclick="javascript:window.location.href='/brand/jumpAdd.do'"/>
    </form>
    <div class="clear"></div>
</div>
<div class="body-box">
    <form action="/brand/brand_list.do" method="post" style="padding-top:5px;">
        品牌名称: <input type="text" name="name" value="${name}"/>
        <select name="isDisplay">
            <option value="1">是</option>
            <option value="0">否</option>
        </select>
        <input type="submit" class="query" value="查询"/>
    </form>
    <table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
        <thead class="pn-lthead">
        <tr>
            <th width="20"><input type="checkbox" onclick="checkBox()"/></th>
            <th>品牌ID</th>
            <th>品牌名称</th>
            <th>品牌图片</th>
            <th>品牌描述</th>
            <th>排序</th>
            <th>是否可用</th>
            <th>操作选项</th>
        </tr>
        </thead>
        <tbody class="pn-ltbody">
        <form id="deform">
            <c:forEach items="${pagination.list}" var="brand">
                <tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'" onmouseover="this.bgColor='#eeeeee'">
                    <td><input type="checkbox" value="${brand.id}" name="ids"/></td>
                    <td align="center">${brand.id}</td>
                    <td align="center">${brand.name}</td>
                    <td align="center"><img width="40" height="40" src="${brand.img_url}"/></td>
                    <td align="center">${brand.description}</td>
                    <td align="center">${brand.sort}</td>
                    <td align="center">
                        <c:if test="${brand.isDisplay == 1}">
                            是
                        </c:if>
                        <c:if test="${brand.isDisplay == 2}">
                            是
                        </c:if>
                    </td>
                    <td align="center">
                        <a class="pn-opt" href="/brand/toEdit.do?id=${brand.id}">修改</a> | <a class="pn-opt"
                                                                                             onclick="deleteOne(${brand.id})"
                                                                                             href="#">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </form>
        </tbody>
    </table>
    <div class="page pb15">
	<span class="r inb_a page_b">
	    <c:forEach items="${pagination.pageView}" var="pageView">
            ${pageView}
        </c:forEach>
	</span>
    </div>
    <div style="margin-top:15px;"><input class="del-button" type="button" value="删除" onclick="optDelete();"/></div>
</div>
</body>
<script>
    function checkBox() {
        var checkbox = $(":checkbox");
        if (checkbox[0].checked == true) {
            $.each(checkbox, function (idx, box) {
                box.checked = true
            })
        } else {
            $.each(checkbox, function (idx, box) {
                box.checked = false
            })
        }
    }

    function optDelete() {
        $("#deform").attr("action", "/brand/delete.do")
        $("#deform").attr("method", "post")
        $("#deform").submit()
    }

    function deleteOne(id) {
        var state = confirm("确认要删除该条记录吗？")
        if(state == true){
            location.href = "/brand/delete.do?ids=" + id
        }else {
            return
        }
    }
</script>
</html>