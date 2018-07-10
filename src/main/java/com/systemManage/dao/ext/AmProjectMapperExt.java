package com.systemManage.dao.ext;

import java.util.List;
import com.systemManage.pojo.base.AmProject;
import com.systemManage.pojo.base.BiBaseInfo;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmProjectDto;
import org.springframework.stereotype.Repository;

@Repository
public interface AmProjectMapperExt {
    /**
     * 根据项目ID查询
     */
    AmProjectDto selectByPrimaryKeyExt(String projectId);
    
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Criteria example);

    /**
     * 根据条件查询记录集
     */
    List<AmProjectDto> selectByExample(Criteria example);


    /**
     * 根据条件查询记录集（给财务审批用）
     * @param example
     * @return
     */
    List<AmProjectDto> selectForDeclare(Criteria example);
}