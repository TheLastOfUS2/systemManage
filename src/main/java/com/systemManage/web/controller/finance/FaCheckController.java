package com.systemManage.web.controller.finance;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.PageUtil;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.FaDeclareRecord;
import com.systemManage.pojo.base.UploadFileInfo;
import com.systemManage.pojo.dto.AmProjectDto;
import com.systemManage.pojo.dto.FaDeclareRecordDto;
import com.systemManage.service.AmProjectService;
import com.systemManage.service.FaDeclareService;
import com.systemManage.service.UploadFileInfoService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/faCheck")
public class FaCheckController {

    //查询条件
    Criteria criteria = new Criteria();

    @Autowired
    private AmProjectService amProjectService; // 项目业务层

    @Autowired
    private FaDeclareService faDeclareService;

    @Autowired
    private UploadFileInfoService uploadFileInfoService;

    @RequestMapping("/toCheckList")
    public String toCheckList(HttpServletRequest request, HttpServletResponse response){
        return "finance/checkList";
    }

    @RequestMapping("/checkList")
    @ResponseBody
    public Object checkList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("recordDel", request.getParameter("recordDel"));
        String baseInfoId=request.getParameter("baseInfoId");
//        if(StringUtil.isNotBlank(baseInfoId)){
//            criteria.put("baseInfoId",baseInfoId);
//        }

        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
            if(("projectName").equals(getName)){
                criteria.put("projectName", getValue);
            }else if(("baseInfoName").equals(getName)) {
                criteria.put("baseInfoName", getValue);
            }else if(("recordType").equals(getName)) {
                criteria.put("recordType", getValue);
            }else{
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
                criteria.setOrderByClause(" record_status desc");
            }
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = faDeclareService.countByExample(criteria);
        List<FaDeclareRecordDto> faDeclareRecordDtos = faDeclareService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", faDeclareRecordDtos);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }

    @RequestMapping("/updateStatus")
    public void updateStatus(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        //判断是删除还是恢复
        String recordId=request.getParameter("recordId");
        //返回多个id
        String recordStatus=request.getParameter("recordStatus");
        if(StringUtil.isNotBlank(recordId) && StringUtil.isNotBlank(recordStatus)) {
            FaDeclareRecord faDeclareRecord = new FaDeclareRecord();
            faDeclareRecord.setRecordId(recordId);
            faDeclareRecord.setRecordStatus(recordStatus);
            faDeclareService.updateStatusById(faDeclareRecord);
            json = JsonUtils.setSuccess();
            json.put("text", "操作成功");
        }else {
            json = JsonUtils.setError();
            json.put("text", "操作失败");
        }
        JsonUtils.outJsonString(json.toString(), response);
    }

    @RequestMapping("/updateAmount")
    public void updateAmount(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        //判断是删除还是恢复
        String recordId=request.getParameter("recordId");
        //返回多个id
        String rbAmount=request.getParameter("rbAmount");
        if(StringUtil.isNotBlank(recordId) && StringUtil.isNotBlank(rbAmount)) {
            FaDeclareRecord faDeclareRecord = new FaDeclareRecord();
            faDeclareRecord.setRecordId(recordId);
            faDeclareRecord.setRbAmount(Double.parseDouble(rbAmount));
            faDeclareService.updateAmountById(faDeclareRecord);
            FaDeclareRecord faDeclareRecord1 = faDeclareService.selectByPrimaryKey(recordId);
            UploadFileInfo uploadFileInfo = new UploadFileInfo();
            uploadFileInfo.setFileId(faDeclareRecord1.getFileId());
            uploadFileInfo.setFileContent(rbAmount);
            uploadFileInfoService.updateByPrimaryKeySelective(uploadFileInfo);
            json = JsonUtils.setSuccess();
            json.put("text", "操作成功");
        }else {
            json = JsonUtils.setError();
            json.put("text", "操作失败");
        }
        JsonUtils.outJsonString(json.toString(), response);
    }

    // 页面列表页字段排序
    public String getName(String name){
        if(("projectName").equals(name)){
            name="projectName";
        }else if(("baseInfoName").equals(name)) {
            name="baseInfoName";
        }else if(("recordType").equals(name)) {
            name="recordType";
        }else if (("rbAmount").equals(name)){
            name = "rbAmount";
        }
        return name;
    }
    //根据值获取数据库对应字段的值
    public String getValue(String name ,String value){
        return value;
    }

    @RequestMapping("/deleteRecord")
    public void deleteRecord(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        //判断是删除还是恢复
        String recordDel=request.getParameter("recordDel");
        //返回多个id
        String recordId=request.getParameter("recordId");
        if(StringUtil.isNotBlank(recordId) && StringUtil.isNotBlank(recordDel)) {
            String[] strRecordId = recordId.split(",");
            for(int i=0;i<strRecordId.length;i++){
                FaDeclareRecord faDeclareRecord = faDeclareService.selectByPrimaryKey(strRecordId[i]);
                if(faDeclareRecord != null) {
                    faDeclareService.updateDelByPrimaryKey(faDeclareRecord,recordDel);
                    json = JsonUtils.setSuccess();
                    json.put("text", "操作成功");
                }else {
                    json = JsonUtils.setError();
                    json.put("text", "操作失败");
                }
            }
        }else {
            json = JsonUtils.setError();
            json.put("text", "系统异常, 请刷新后重试");
        }
        JsonUtils.outJsonString(json.toString(), response);
    }
}


