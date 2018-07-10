package com.systemManage.web.controller.finance;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.PageUtil;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.pojo.base.*;
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
@RequestMapping("/faDeclare")
public class FaDeclareController {
    //查询条件
    Criteria criteria = new Criteria();

    //调研、访问与论文发表占总金额的比例
    private static final double RATIO_A =0.65;
    //学术会议占总金额的比例
    private static final double RATIO_B =0.15;
    //数据、图书及打印占总金额的比例
    private static final double RATIO_C =0.20;
    //项目金额d占总金额的比例
//    private static final double RATIO_D =0.25;

    @Autowired
    private AmProjectService amProjectService; // 项目业务层

    @Autowired
    private FaDeclareService faDeclareService;

    @Autowired
    private FaCaService faCaService;

    @RequestMapping("/toDeclareList")
    public String toDeclareList(HttpServletRequest request, HttpServletResponse response){
        return "finance/declareList";
    }

    @RequestMapping("/declareList")
    @ResponseBody
    public Object declareList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
//        criteria.put("projectType", request.getParameter("projectType"));
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
        List<FaDeclare> faDeclares = new ArrayList<>();
        for (AmProjectDto amProjectDto : amProjects){
            FaDeclare faDeclare = new FaDeclare();
            faDeclare.setProjectId(amProjectDto.getProjectId());
            faDeclare.setProjectName(amProjectDto.getProjectName());
            FaCa faCa = new FaCa();
            faCa.setBaseInfoId(baseInfoId);
            faCa.setProjectId(amProjectDto.getProjectId());
            List<FaCa> faCas = faCaService.selectByProBase(faCa);
            Double totalMoney;
            if (faCas!=null&&faCas.size()!=0){
                totalMoney = faCas.get(0).getAmount()*10000;
            }else {
                totalMoney = amProjectDto.getProjectApprovedOutlay()*10000;
            }
            faDeclare.setProjectAmount(totalMoney);
            faDeclare.setTypeAMoney(totalMoney*RATIO_A);
            faDeclare.setTypeBMoney(totalMoney*RATIO_B);
            faDeclare.setTypeCMoney(totalMoney*RATIO_C);
//            faDeclare.setTypeDMoney(totalMoney*RATIO_D);
            faDeclare.setRbAmountA(0.0);
            faDeclare.setRbAmountB(0.0);
            faDeclare.setRbAmountC(0.0);
//            faDeclare.setRbAmountD(0.0);
            faDeclare.setProjectTime(amProjectDto.getProjectTime());
            faDeclare.setEndTime(amProjectDto.getProjectKnotTime());
            List<FaDeclareRecord> faDeclareRecords = faDeclareService.selectFaDeclareList(amProjectDto.getProjectId());
            if(faDeclareRecords!=null&&faDeclareRecords.size()!=0){
                for(FaDeclareRecord faDeclareRecord:faDeclareRecords){
                    if ("A".equalsIgnoreCase(faDeclareRecord.getRecordType())){
                        Double rbAmount = faDeclareRecord.getRbAmount();
                        faDeclare.setRbAmountA(faDeclare.getRbAmountA()+rbAmount);
                    }else if ("B".equalsIgnoreCase(faDeclareRecord.getRecordType())){
                        Double rbAmount = faDeclareRecord.getRbAmount();
                        faDeclare.setRbAmountB(faDeclare.getRbAmountB()+rbAmount);
                    }else if ("C".equalsIgnoreCase(faDeclareRecord.getRecordType())){
                        Double rbAmount = faDeclareRecord.getRbAmount();
                        faDeclare.setRbAmountC(faDeclare.getRbAmountC()+rbAmount);
                    }
//                    else if (faDeclareRecord.getRecordType().equalsIgnoreCase("D")){
//                        Double rbAmount = faDeclareRecord.getRbAmount();
//                        faDeclare.setRbAmountD(faDeclare.getRbAmountD()+rbAmount);
//                    }
                }
            }
            faDeclare.setSurplusAmountA(faDeclare.getTypeAMoney()-faDeclare.getRbAmountA());
            faDeclare.setSurplusAmountB(faDeclare.getTypeBMoney()-faDeclare.getRbAmountB());
            faDeclare.setSurplusAmountC(faDeclare.getTypeCMoney()-faDeclare.getRbAmountC());
//            faDeclare.setSurplusAmountD(faDeclare.getTypeDMoney()-faDeclare.getRbAmountD());
            faDeclare.setProjectSurplusAmount(faDeclare.getSurplusAmountA()+faDeclare.getSurplusAmountB()+faDeclare.getSurplusAmountC());
            faDeclares.add(faDeclare);
        }
        // 返回的数据
        jsonMap.put("rows", faDeclares);
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
//        if(("projectStatus").equals(name)){
//            if(("在研").equals(value)){
//                value="0";
//            }else if(("结项").equals(value)){
//                value="1";
//            }else if(("延期（已申请）").equals(value)){
//                value="2";
//            }else {
//                value="3";
//            }
//        }
        return value;
    }

//    /**
//     * 方法名: roleChange
//     * 描述: 领导角色转换（领导和科研人员角色相互转换）
//     * 参数: @param amOpusDto
//     * 参数: @param request
//     * 参数: @param response
//     * 创建人: Zhang JinQiu
//     * 创建时间: 2017年7月17日 下午11:13:17
//     * 版本号: v1.0
//     * 抛出异常:
//     * 返回类型: void
//     */
//    @RequestMapping("/roleChange")
//    public void roleChange(CommonAccount commonAccount, HttpServletRequest request, HttpServletResponse response){
//        JSONObject result=null;
//        CommonAccount ca=null;
//        if(commonAccount!=null && StringUtil.isNotBlank(commonAccount.getAccountId())){
//            ca = commonAccountService.selectByPrimaryKey(commonAccount.getAccountId());
//            if(ca!=null){
//                result = commonAccountService.updateRoleChange(ca,request);
//            }
//        }
//        JsonUtils.outJsonString(result.toString(), response);
//    }
}
