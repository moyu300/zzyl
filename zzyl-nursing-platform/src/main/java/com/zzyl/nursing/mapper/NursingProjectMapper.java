package com.zzyl.nursing.mapper;

import com.zzyl.nursing.domain.NursingProject;
import com.zzyl.nursing.vo.NursingProjectVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 护理项目Mapper接口
 * 
 * @author ruoyi
 * @date 2025-11-04
 */
public interface NursingProjectMapper 
{

    /**
     * 查询所有护理项目
     * @return
     */
    @Select("select id value,name label from nursing_project where status = 1 ")
    public List<NursingProjectVo> selectAll();

    /**
     * 查询护理项目
     * 
     * @param id 护理项目主键
     * @return 护理项目
     */
    public NursingProject selectNursingProjectById(Long id);

    /**
     * 查询护理项目列表
     * 
     * @param nursingProject 护理项目
     * @return 护理项目集合
     */
    public List<NursingProject> selectNursingProjectList(NursingProject nursingProject);

    /**
     * 新增护理项目
     * 
     * @param nursingProject 护理项目
     * @return 结果
     */
    public int insertNursingProject(NursingProject nursingProject);

    /**
     * 修改护理项目
     * 
     * @param nursingProject 护理项目
     * @return 结果
     */
    public int updateNursingProject(NursingProject nursingProject);

    /**
     * 删除护理项目
     * 
     * @param id 护理项目主键
     * @return 结果
     */
    public int deleteNursingProjectById(Long id);

    /**
     * 批量删除护理项目
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNursingProjectByIds(Long[] ids);

    /**
     * 根据id查询项目id和名称
     * @param id
     * @return
     */
    @Select("select name from nursing_project where id = #{id}")
    public String getProjectIdAndName(Long id);
}
