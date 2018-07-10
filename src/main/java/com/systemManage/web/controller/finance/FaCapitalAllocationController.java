package com.systemManage.web.controller.finance;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.PageUtil;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.pojo.base.CommonAccount;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.FaDeclare;
import com.systemManage.pojo.base.FaDeclareRecord;
import com.systemManage.pojo.dto.AmProjectDto;
import com.systemManage.pojo.dto.CommonAccountDto;
import com.systemManage.service.AmProjectService;
import com.systemManage.service.CommonAccountService;
import com.systemManage.service.FaCaService;
import com.systemManage.service.FaDeclareService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/faCa")
public class FaCapitalAllocationController {
    //查询条件
    Criteria criteria = new Criteria();

    @Autowired
    private AmProjectService amProjectService; // 项目业务层

    @Autowired
    private FaCaService faCaService;

    @RequestMapping("/toCaList")
    public String toCaList(HttpServletRequest request, HttpServletResponse response){
        return "finance/caList";
    }

    @RequestMapping("/caList")
    @ResponseBody
    public Object caList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("projectDel", request.getParameter("projectDel"));
        String baseInfoId=request.getParameter("baseInfoId");
        if(StringUtil.isNotBlank(baseInfoId)){
            criteria.put("baseInfoId",baseInfoId);
        }

        //全文检索数据
        //责任人姓名
        String qBaseInfoName=request.getParameter("qBaseInfoName");
        if(StringUtil.isNotBlank(qBaseInfoName)){
            criteria.put("baseInfoName3",qBaseInfoName);
        }
        //责任人类别
        String baseInfoType=request.getParameter("baseInfoType");
        if(StringUtil.isNotBlank(baseInfoType)){
            criteria.put("baseInfoType3", request.getParameter("baseInfoType"));
        }
        //责任人职称
        String baseInfoPositionalTitles=request.getParameter("baseInfoPositionalTitles");
        if(StringUtil.isNotBlank(baseInfoPositionalTitles)){
            criteria.put("baseInfoPositionalTitles3", request.getParameter("baseInfoPositionalTitles"));
        }

        //责任人级别
        String baseInfoLevel=request.getParameter("baseInfoLevel");
        if(StringUtil.isNotBlank(baseInfoLevel)){
            criteria.put("baseInfoLevel3", request.getParameter("baseInfoLevel"));
        }

        //责任人年龄区间
        String baseInfoStartAge=request.getParameter("baseInfoStartAge");
        if(StringUtil.isNotBlank(baseInfoStartAge)){
            criteria.put("baseInfoStartAge", request.getParameter("baseInfoStartAge"));
        }
        String baseInfoEndAge=request.getParameter("baseInfoEndAge");
        if(StringUtil.isNotBlank(baseInfoEndAge)){
            criteria.put("baseInfoEndAge", request.getParameter("baseInfoEndAge"));
        }

        //成果/项目名称
        String projectName=request.getParameter("projectName");
        if(StringUtil.isNotBlank(projectName)){
            criteria.put("projectName3", request.getParameter("projectName"));
        }

        //成果时间区间
        String projectStartTime=request.getParameter("projectStartTime");
        if(StringUtil.isNotBlank(projectStartTime)){
            criteria.put("projectStartTime", request.getParameter("projectStartTime"));
        }
        String projectEndTime=request.getParameter("projectEndTime");
        if(StringUtil.isNotBlank(projectEndTime)){
            criteria.put("projectEndTime", request.getParameter("projectEndTime"));
        }
        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
            if(("projectName").equals(getName)){
                criteria.put("projectName2", getValue);
            }else if(("projectNumber").equals(getName)) {
                criteria.put("projectNumber2", getValue);
            }else if(("projectTime").equals(getName)) {
                criteria.put("projectTime2", getValue);
            }else if(("baseInfoName").equals(getName)) {
                criteria.put("baseInfoName2", getValue);
            } else{
                getValue = getValue(getName, getValue);
                criteria.put(getName, getValue);
            }
        }

        // 获取当前分页页号和每页显示条数
        int page = PageUtil.getPageOrRows(request.getParameter("page"));
        int rows = PageUtil.getPageOrRows(request.getParameter("rows"));
        if(page != 0 && rows != 0){
            criteria.setMysqlOffset(PageUtil.getStartRecord(page, rows));
            criteria.setMysqlLength(rows);
            // 获取sort：排序列字段名、order：排序方式，可以是 'asc' 或者 'desc'，默认值是 'asc'。
            String sort=request.getParameter("sort");
            String order=request.getParameter("order");
            if(StringUtil.isNotBlank(sort) && StringUtil.isNotBlank(order)){
                sort=getName(sort);
                criteria.setOrderByClause("CONVERT("+sort+"  USING gbk) "+order);
            }else{
                criteria.setOrderByClause(" project_create_time desc");
            }
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = amProjectService.countByExample(criteria);
        List<AmProjectDto> amProjects=amProjectService.selectByExample(criteria);
        // 返回的数据
        for (AmProjectDto amProjectDto : amProjects) {
            int i = faCaService.countByPid(amProjectDto.getProjectId());
            if (i>0){
                amProjectDto.setCaStatus("已分配");
            }else {
                amProjectDto.setCaStatus("未分配");
            }
        }
        jsonMap.put("rows", amProjects);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }

    // 页面列表页字段排序
    public String getName(String name){
        if(("projectName").equals(name)){
            name="project_name";
        }else if(("projectApprovedOutlay").equals(name)){
            name="project_approved_outlay";
        }else if(("projectTime").equals(name)){
            name="project_time";
        }else if(("projectKnotTime").equals(name)){
            name="project_knot_time";
        }
        return name;
    }
    //根据值获取数据库对应字段的值
    public String getValue(String name ,String value){
        return value;
    }
}
