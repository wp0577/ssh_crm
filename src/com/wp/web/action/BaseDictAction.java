package com.wp.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.wp.domain.BaseDict;
import com.wp.service.BaseDictService;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;

import java.util.List;

public class BaseDictAction extends ActionSupport {

    private String dict_type_code;

    private BaseDictService baseDictService;

    @Override
    public String execute() throws Exception {
        //接收dict_type_code
        //调用service接收结果
        List<BaseDict> list = baseDictService.getByCode(dict_type_code);
        //将list转化成json格式
        String jsonArray = JSONArray.fromObject(list).toString();

        //将结果直接存入response中
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().write(jsonArray);
        return null;
    }

    public String getDict_type_code() {
        return dict_type_code;
    }

    public void setDict_type_code(String dict_type_code) {
        this.dict_type_code = dict_type_code;
    }

    public void setBaseDictService(BaseDictService baseDictService) {
        this.baseDictService = baseDictService;
    }
}
