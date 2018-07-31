﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <TITLE>添加客户</TITLE>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
    <LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
          rel=stylesheet>
    <script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
    <%--将ajax功能封装到my.js文件，便于之后其他页面调用--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/my.js"></script>
    <script type="text/javascript">

        $(document).ready(function () {
            //不能直接传001等数字，需要传入字符串"001"等
            //用#customer.cust_level.dict_id取值的原因是，在customer只是让外键cust_level关联了dict_id，所以还需要从dict_id去取值。
            //"cust_level.dict_id"s:if test="#customer.cust_level!=null">,s:property value="#customer.cust_level.dict_id" /的写法：
            //这种写法是判断当cust_level==null时只有selectName属性，不为空时则两个属性都有
            //并且selectName参数只是为了让option有一个name=”cust_level.dict_id“方便后面保存数据
            loadSelect("levelPosition", "006", "cust_level.dict_id" <s:if test="#customer.cust_level!=null">, <s:property value="#customer.cust_level.dict_id" /></s:if>);
            loadSelect("industryPosition", "001", "cust_industry.dict_id" <s:if test="#customer.cust_industry!=null">, <s:property value="#customer.cust_industry.dict_id" /></s:if>);
            loadSelect("sourcePosition", "002", "cust_source.dict_id" <s:if test="#customer.cust_source!=null">, <s:property value="#customer.cust_source.dict_id" /></s:if>);
        });

    </script>
    <META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
<FORM id=form1 name=form1
      action="${pageContext.request.contextPath }/customerAction_save"
      method=post enctype="multipart/form-data">

    <%--使用隐藏域将cust_id保存--%>
    <input type="hidden" name="cust_id" value=<s:property value="#customer.cust_id"/>>

    <TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
        <TBODY>
        <TR>
            <TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
                              border=0></TD>
            <TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
                height=20></TD>
            <TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
                              border=0></TD>
        </TR>
        </TBODY>
    </TABLE>
    <TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
        <TBODY>
        <TR>
            <TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
                    src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
            <TD vAlign=top width="100%" bgColor=#ffffff>
                <TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
                    <TR>
                        <TD class=manageHead>current page：customer &gt; <s:property
                                value="#customer==null?'add':'edit'"/> customer
                        </TD>
                    </TR>
                    <TR>
                        <TD height=2></TD>
                    </TR>
                </TABLE>

                <TABLE cellSpacing=0 cellPadding=5 border=0>


                    <TR>
                        <td>Customer Name：</td>
                        <td>
                            <INPUT class=textbox id=sChannel2
                                   style="WIDTH: 180px" maxLength=50 name="cust_name" value=<s:property
                                    value="#customer.cust_name"/>>
                        </td>
                        <td>Customer level ：</td>
                        <td id="levelPosition">

                        </td>
                    </TR>

                    <TR>

                        <td>source ：</td>
                        <td id="sourcePosition">
                        </td>
                        <td>industry：</td>
                        <td id="industryPosition">
                        </td>
                    </TR>

                    <TR>


                        <td>linkman ：</td>
                        <td>
                            <INPUT class=textbox id=sChannel3
                                   style="WIDTH: 180px" maxLength=50 name="cust_linkman" value=<s:property
                                    value="#customer.cust_linkman"/>>
                        </td>
                        <td>phone ：</td>
                        <td>
                            <INPUT class=textbox id=sChannel4
                                   style="WIDTH: 180px" maxLength=50 name="cust_mobile" value=<s:property
                                    value="#customer.cust_mobile"/>>
                        </td>
                    </TR>
                    <TR>
                        <td>file upload ：</td>
                        <td>
                            <input type="file" name="photo">
                        </td>
                    </TR>
                    <tr>
                        <td rowspan=2>
                            <INPUT class=button id=sButton2 type=submit
                                   value=" save " name=sButton2>
                        </td>
                    </tr>
                </TABLE>


            </TD>
            <TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
                <IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
        </TR>
        </TBODY>
    </TABLE>
    <TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
        <TBODY>
        <TR>
            <TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
                              border=0></TD>
            <TD align=middle width="100%"
                background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
            <TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
                              border=0></TD>
        </TR>
        </TBODY>
    </TABLE>
</FORM>
</BODY>
</HTML>
