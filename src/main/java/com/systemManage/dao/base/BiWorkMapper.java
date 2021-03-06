package com.systemManage.dao.base;

import com.systemManage.pojo.base.BiWork;
import com.systemManage.pojo.base.Criteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BiWorkMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Criteria example);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String workId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(BiWork record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(BiWork record);

    /**
     * 根据条件查询记录集
     */
    List<BiWork> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    BiWork selectByPrimaryKey(String workId);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") BiWork record, @Param("example") Criteria example);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") BiWork record, @Param("example") Criteria example);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(BiWork record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(BiWork record);
}