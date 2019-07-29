<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>babasport-list</title>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 库存管理 - 列表</div>
	<div class="clear"></div>
</div>
<div class="body-box">
<form method="post" id="tableForm">
<table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input type="checkbox" onclick="Pn.checkbox('ids',this.checked)"/></th>
			<th>商品编号</th>
			<th>商品颜色</th>
			<th>商品尺码</th>
			<th>市场价格</th>
			<th>销售价格</th>
			<th>库       存</th>
			<th>购买限制</th>
			<th>运       费</th>
			<th>是否赠品</th>
			<th>操       作</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
		<c:forEach items="${pagination.list}" var="sku">
			<tr id="${sku.id}" bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
				<td><input type="checkbox" name="ids" value="${sku.id}"/></td>
				<td>${sku.product_id}</td>
				<td align="center">${sku.name}</td>
				<td align="center">${sku.size}</td>
				<td align="center"><input type="text" id="m${sku.id}" value="${sku.market_price}" disabled="disabled" size="10"/></td>
				<td align="center"><input type="text" id="p${sku.id}" value="${sku.price}" disabled="disabled" size="10"/></td>
				<td align="center"><input type="text" id="i${sku.id}" value="${sku.stock}" disabled="disabled" size="10"/></td>
				<td align="center"><input type="text" id="l${sku.id}" value="${sku.upper_limit}" disabled="disabled" size="10"/></td>
				<td align="center"><input type="text" id="f${sku.id}" value="${sku.delive_fee}" disabled="disabled" size="10"/></td>
				<td align="center">不是</td>
				<td align="center"><a href="javascript:updateSku(${sku.id}, this)" class="pn-opt">修改</a> | <a href="javascript:addSku(${sku.id})" class="pn-opt">保存</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
	<div class="page pb15">
	<span class="r inb_a page_b">
		<c:forEach items="${pagination.pageView}" var="pageView">
			${pageView}
		</c:forEach>
	</span>
	</div>
</form>
</div>
</body>
<script>
	function updateSku(id, clk) {
		var inputs = $("#m"  + id + ",#p" + id+", #i" + id + ", #l" + id + ", #f" + id)
		clk.disabled = true
		$.each(inputs, function (idx, input) {
			input.disabled = false
		})
	}

	function addSku(ids) {
		var json = {}
		json.marketPrice = $("#m" + ids).val()
		json.price = $("#p" + ids).val()
		json.stock = $("#i" + ids).val()
		json.upperLimit = $("#l" + ids).val()
		json.deliveFee = $("#f" + ids).val()
		json.id = ids
		$.ajax({
			url: "/sku/sku_update.do",
			content: "application/json;charset=utf-8",
			type: "post",
			dataType: "json",
			data: json,
			success: function (result) {
				if(result.msg == 0){
					alert("修改成功")
					var inputs = $("#m"  + ids + ",#p" + ids +", #i" + ids + ", #l" + id + ", #f" + ids)
					$.each(inputs, function (idx, input) {
						input.disabled = true
					})
				} else {
					alert("修改失败")
				}
			}
		})
	}
</script>
</html>