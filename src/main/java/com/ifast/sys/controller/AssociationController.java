package com.ifast.sys.controller;


import java.util.Arrays;

import com.ifast.sys.domain.AssociationDO;
import com.ifast.sys.service.AssociationService;
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

/**
 * 
 * <pre>
 * 
 * </pre>
 * <small> 2020-12-09 16:55:03 | zn</small>
 */
@Controller
@RequestMapping("/association")
public class AssociationController extends BaseController {
	@Autowired
	private AssociationService associationService;
	
	@GetMapping()
	@RequiresPermissions("wxmp:association:association")
	String Association(){
	    return "wxmp/association/association";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("wxmp:association:association")
	public Result<Page<AssociationDO>> list(AssociationDO associationDTO){
        Wrapper<AssociationDO> wrapper = new EntityWrapper<>(associationDTO).orderBy("id", false);
        Page<AssociationDO> page = associationService.selectPage(getPage(AssociationDO.class), wrapper);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("wxmp:association:add")
	String add(){
	    return "wxmp/association/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("wxmp:association:edit")
	String edit(@PathVariable("id") Long id,Model model){
		AssociationDO association = associationService.selectById(id);
		model.addAttribute("association", association);
	    return "wxmp/association/edit";
	}
	
	@Log("添加")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("wxmp:association:add")
	public Result<String> save( AssociationDO association){
		associationService.insert(association);
        return Result.ok();
	}
	
	@Log("修改")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("wxmp:association:edit")
	public Result<String>  update( AssociationDO association){
		boolean update = associationService.updateById(association);
		return update ? Result.ok() : Result.fail();
	}
	
	@Log("删除")
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("wxmp:association:remove")
	public Result<String>  remove( Long id){
		associationService.deleteById(id);
        return Result.ok();
	}
	
	@Log("批量删除")
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("wxmp:association:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		associationService.deleteBatchIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
