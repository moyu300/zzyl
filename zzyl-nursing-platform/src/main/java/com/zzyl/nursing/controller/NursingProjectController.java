package com.zzyl.nursing.controller;
// 添加Swagger相关注解导入
import com.zzyl.common.annotation.Log;
import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.common.core.domain.R;
import com.zzyl.common.core.page.TableDataInfo;
import com.zzyl.common.enums.BusinessType;
import com.zzyl.common.utils.poi.ExcelUtil;
import com.zzyl.nursing.domain.NursingProject;
import com.zzyl.nursing.service.INursingProjectService;
import com.zzyl.nursing.vo.NursingProjectVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 护理项目Controller
 *
 * @author ruoyi
 * @date 2025-11-04
 */
@Api(tags = "护理项目管理")
@RestController
@RequestMapping("/serve/project")
public class NursingProjectController extends BaseController
{
    @Autowired
    private INursingProjectService nursingProjectService;

    /**
     * 查询护理项目列表
     */
    @PreAuthorize("@ss.hasPermi('serve:project:list')")
    @GetMapping("/list")
    @ApiOperation("查询护理项目列表")
    public TableDataInfo<List<NursingProject>> list(@ApiParam("护理项目查询条件") NursingProject nursingProject)
    {
        startPage();
        List<NursingProject> list = nursingProjectService.selectNursingProjectList(nursingProject);
        return getDataTable(list);
    }
    /**
     * 查询护理项目列表
     */
    @GetMapping("/all")
    @ApiOperation(value = "查询所有护理项目")
    public AjaxResult listAll()
    {
        List<NursingProjectVo> list = nursingProjectService.selectAll();
        return success(list);
    }
    /**
     * 导出护理项目列表
     */
    @PreAuthorize("@ss.hasPermi('serve:project:export')")
    @Log(title = "护理项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出护理项目列表")
    public void export(HttpServletResponse response,
                       @ApiParam("护理项目查询条件") NursingProject nursingProject)
    {
        List<NursingProject> list = nursingProjectService.selectNursingProjectList(nursingProject);
        ExcelUtil<NursingProject> util = new ExcelUtil<NursingProject>(NursingProject.class);
        util.exportExcel(response, list, "护理项目数据");
    }

    /**
     * 获取护理项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('serve:project:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取护理项目详细信息")
    public R<NursingProject> getInfo(@ApiParam("护理项目ID") @PathVariable("id") Long id)
    {
        NursingProject nursingProject = nursingProjectService.selectNursingProjectById(id);
        return R.ok(nursingProject);
    }

    /**
     * 新增护理项目
     */
    @PreAuthorize("@ss.hasPermi('serve:project:add')")
    @Log(title = "护理项目", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增护理项目")
    public AjaxResult add(@ApiParam("护理项目信息") @RequestBody NursingProject nursingProject)
    {
        return toAjax(nursingProjectService.insertNursingProject(nursingProject));
    }

    /**
     * 修改护理项目
     */
    @PreAuthorize("@ss.hasPermi('serve:project:edit')")
    @Log(title = "护理项目", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改护理项目")
    public AjaxResult edit(@ApiParam("护理项目信息") @RequestBody NursingProject nursingProject)
    {
        return toAjax(nursingProjectService.updateNursingProject(nursingProject));
    }

    /**
     * 删除护理项目
     */
    @PreAuthorize("@ss.hasPermi('serve:project:remove')")
    @Log(title = "护理项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation("删除护理项目")
    public AjaxResult remove(@ApiParam("护理项目ID数组") @PathVariable Long[] ids)
    {
        return toAjax(nursingProjectService.deleteNursingProjectByIds(ids));
    }
}
