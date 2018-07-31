//selectName, 和selectId是为了当从客户列表进行客户修改跳转到该页面时能够显示当前的客户信息
function loadSelect(positionId, typeCode, selectName, selectId) {
    //DOM对象，即是我们用传统的方法(javascript)获得的对象，jQuery对象即是用jQuery类库的选择器获得的对象;
    //复制代码 代码如下:
    //var domObj = document.getElementById("id"); //DOM对象
    //var $obj = $("#id"); //jQuery对象;
    //jQuery对象就是通过jQuery包装DOM对象后产生的对象，它是jQuery独有的。如果一个对象是jQuery对象，那么就可以使用jQuery里的方法，例:
    var $select = $("<select name=" + selectName + " ></select>");
    $select.append("<option value = ''>-------please select-------</option>");

    $.post("${pageContext.request.contextPath}/baseDictAction", {dict_type_code: typeCode},
        function (data) {
            $.each(data, function (i, n) {
                $option = $("<option value =" + n.dict_id + ">" + n.dict_item_name + "</option>");
                if (selectId == n.dict_id) {
                    $option.attr("selected", "selected");
                }
                $select.append($option);
            });
        }, "json");
    //该处不能直接用"#positionId"，因为这会直接索引整个#positionId，而不是我们要的参数"
    $("#" + positionId).append($select);

}