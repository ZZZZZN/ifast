package com.ifast.sys.controller;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ifast.common.domain.DictDO;
import com.ifast.common.service.DictService;
import com.ifast.sys.domain.AssociationDO;
import com.ifast.sys.domain.PetitionLetterDO;
import com.ifast.sys.domain.PetitionLetterNewDo;
import com.ifast.sys.service.AssociationService;
import com.ifast.sys.service.PetitionLetterService;
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

import com.baomidou.mybatisplus.plugins.Page;
import com.ifast.common.annotation.Log;
import com.ifast.common.base.BaseController;

import com.ifast.common.utils.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * <pre>
 * 
 * </pre>
 * <small> 2020-12-07 09:14:51 | zn</small>
 */
@Controller
@RequestMapping("/sys/petitionLetter")
public class PetitionLetterController extends BaseController {
	@Autowired
	private PetitionLetterService petitionLetterService;
	@Autowired
	private AssociationService associationService;
	@Autowired
	private DictService dictService;

	private String preix="sys/petitionLetter";
	
	@GetMapping("/")
//	@RequiresPermissions("sys:petitionLetter:petitionLetter")
	String PetitionLetter(){
	    return preix+"/petitionLetter";
	}
	
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("sys:petitionLetter:petitionLetter")
	public Result<Page<PetitionLetterDO>> list(PetitionLetterDO petitionLetterDTO, HttpServletRequest request){
//        Wrapper<PetitionLetterDO> wrapper = new EntityWrapper<>(petitionLetterDTO).orderBy("id", false);
//        Page<PetitionLetterDO> page = petitionLetterService.selectPage(getPage(PetitionLetterDO.class), wrapper);
		String name=request.getParameter("name");
		String deptId=request.getParameter("deptId");
        Page<PetitionLetterDO> page = petitionLetterService.selectPage(getPage(PetitionLetterDO.class));
		List<PetitionLetterDO> list=petitionLetterService.selectAllList(name,deptId);
		page.setRecords(list);
//        page.setCondition(petitionLetterService.selectAllList());
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sys:petitionLetter:add")
	String add(){
	    return "sys/petitionLetter/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("sys:petitionLetter:edit")
	String edit(@PathVariable("id") Long id,Model model){
//		PetitionLetterDO petitionLetter = petitionLetterService.selectById(id);
		PetitionLetterNewDo petitionLetter =petitionLetterService.selectOne(id);
		model.addAttribute("petitionLetter", petitionLetter);
	    return "sys/petitionLetter/edit";
	}
	
	@Log("添加")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:petitionLetter:add")
	public Result<String> save( PetitionLetterDO petitionLetter){
		String deptId=petitionLetter.getServiceDept();
		String arr[]=deptId.split(",");
		petitionLetterService.insert(petitionLetter);

		for (int i=0;i<arr.length;i++){
			AssociationDO association=new AssociationDO();
			association.setDepid(Integer.valueOf(arr[i]));
			association.setPetitionid(petitionLetter.getId());
			associationService.insert(association);
		}
        return Result.ok();
	}
	
	@Log("修改")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:petitionLetter:edit")
	public Result<String>  update( PetitionLetterNewDo petitionLetterNewDo){
//		System.out.println("petitionLetterNewDo = " + petitionLetterNewDo);
		//查询相关联子表
		PetitionLetterDO petitionLetterDO=new PetitionLetterDO();
		petitionLetterDO.setId(petitionLetterNewDo.getId());
		petitionLetterDO.setSourcepetition(Integer.valueOf(petitionLetterNewDo.getSourcepetition()));
		petitionLetterDO.setPetitiontime(petitionLetterNewDo.getPetitiontime());
		petitionLetterDO.setLettertitle(petitionLetterNewDo.getLettertitle());
		petitionLetterDO.setContent(petitionLetterNewDo.getContent());
		petitionLetterDO.setReceiptno(petitionLetterNewDo.getReceiptno());
		petitionLetterDO.setReceiver(petitionLetterNewDo.getReceiver());
		petitionLetterDO.setStatus(petitionLetterNewDo.getStatus());
		petitionLetterDO.setReceivetime(petitionLetterNewDo.getReceivetime());
		petitionLetterDO.setProcesstime(petitionLetterNewDo.getProcesstime());
		petitionLetterDO.setActualreplytime(petitionLetterNewDo.getActualreplytime());
		petitionLetterDO.setRemindertime(petitionLetterNewDo.getRemindertime());
		petitionLetterDO.setIsrecover(petitionLetterNewDo.getIsrecover());
		petitionLetterDO.setRecovertime(petitionLetterNewDo.getRecovertime());
		petitionLetterDO.setRemark(petitionLetterNewDo.getRemark());
		boolean update = petitionLetterService.updateById(petitionLetterDO);
		associationService.deleteAll(petitionLetterNewDo.getId());
		String deptId=petitionLetterNewDo.getDeptno();
		if(deptId==null||deptId==""){

		}else {
			String arr[]=deptId.split(",");
			for (int i=0;i<arr.length;i++){
				AssociationDO associationDO=new AssociationDO();
				associationDO.setPetitionid(petitionLetterDO.getId());
				associationDO.setDepid(Integer.valueOf(arr[i]));
				associationService.insert(associationDO);
			}
		}
		return update ? Result.ok() : Result.fail();

	}
	
	@Log("删除")
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sys:petitionLetter:remove")
	public Result<String>  remove( Long id){
		petitionLetterService.deleteById(id);
		associationService.deleteAll(id);
        return Result.ok();
	}
	
//	@Log("批量删除")
//	@PostMapping( "/batchRemove")
//	@ResponseBody
//	@RequiresPermissions("sys:petitionLetter:batchRemove")
//	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
//		petitionLetterService.deleteBatchIds(Arrays.asList(ids));
//		return Result.ok();
//	}


	/**
	 *信访件添加和修改操作时选择部门功能
	 */
	@GetMapping("/treeViews")
	String treeViews() {
		return "/sys/petitionLetter/deptTrees";
	}
	
}
