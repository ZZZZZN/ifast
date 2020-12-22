package com.ifast.sys.controller;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.ifast.sys.domain.ChartOperationDO;
import com.ifast.sys.service.ChartOperationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ifast.common.annotation.Log;
import com.ifast.common.base.BaseController;

import com.ifast.common.utils.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * <pre>
 * 挂图作战表
 * </pre>
 * <small> 2020-12-16 17:08:41 | zn</small>
 */
@Controller
@RequestMapping("/sys/chartOperation")
public class ChartOperationController extends BaseController {
	@Autowired
	private ChartOperationService chartOperationService;
	
	@GetMapping()
	String ChartOperation(){
	    return "sys/chartOperation/chartOperation";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public Result<Page<ChartOperationDO>> list(ChartOperationDO chartOperationDTO, HttpServletRequest request){
		Map para =new HashMap();
		para.put("name",request.getParameter("name"));
		para.put("dept",request.getParameter("searchName"));
		para.put("id",request.getParameter("id"));
        Page<ChartOperationDO> page = chartOperationService.selectPage(getPage(ChartOperationDO.class),para);
        return Result.ok(page);
	}

	@GetMapping("/add")
	@RequiresPermissions("sys:chartOperation:add")
	String add(){
		return "sys/chartOperation/add";
	}

	@Log("添加挂图作战表")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:chartOperation:save")
	public Result<String> save( ChartOperationDO chartOperation){
		chartOperationService.insert(chartOperation);
        return Result.ok();
	}

	@Log("编辑")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("sys:chartOperation:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ChartOperationDO chartOperation = chartOperationService.selectById(id);
		model.addAttribute("chartOperation", chartOperation);
		return "sys/chartOperation/edit";
	}

	@Log("修改挂图作战表")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:chartOperation:update")
	public Result<String>  update( ChartOperationDO chartOperation){
		boolean update = chartOperationService.updateById(chartOperation);
		return update ? Result.ok() : Result.fail();
	}
	
	@Log("删除挂图作战表")
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sys:chartOperation:remove")
	public Result<String>  remove( Long id){
		chartOperationService.deleteById(id);
        return Result.ok();
	}
	
	@Log("批量删除挂图作战表")
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sys:chartOperation:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		chartOperationService.deleteBatchIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
