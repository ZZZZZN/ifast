package com.ifast.sys.controller;


import java.text.SimpleDateFormat;
import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ifast.api.service.AppUserService;
import com.ifast.common.domain.DictDO;
import com.ifast.common.service.DictService;
import com.ifast.common.utils.ShiroUtils;
import com.ifast.sys.domain.*;
import com.ifast.sys.mail.entity.ToEmail;
import com.ifast.sys.mail.support.ToEmailService;
import com.ifast.sys.service.AssociationService;
import com.ifast.sys.service.DeptService;
import com.ifast.sys.service.PetitionLetterService;
import org.apache.shiro.SecurityUtils;
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

import javax.mail.MessagingException;
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
	private DeptService deptService;
	@Autowired
	private ToEmailService toEmailService;
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

		UserDO user= ShiroUtils.getSysUser();
		String name=request.getParameter("name");
		String deptId="";
		if(!user.getUsername().equals("admin")){
			deptId=user.getDeptId().toString();
		}
         Map para =new HashMap();
		para.put("name",name);
		para.put("deptId",deptId);
		Page<PetitionLetterDO> page = petitionLetterService.selectPage(getPage(PetitionLetterDO.class),para);
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
		String deptName="";
		petitionLetterService.insert(petitionLetter);
		for (int i=0;i<arr.length;i++){
			DeptDO deptDO=deptService.selectById(arr[i]);
			if("".equals(deptName)){
				deptName=deptDO.getName();
			}else {
				deptName+=","+deptDO.getName();
			}
			AssociationDO association=new AssociationDO();
			association.setDepid(Integer.valueOf(arr[i]));
			association.setPetitionid(petitionLetter.getId());
			associationService.insert(association);
		}
		String source=dictService.getName("source_ptittion",petitionLetter.getSourcepetition().toString());
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		for (int i=0;i<arr.length;i++){
			String[] to= new String[1];
			ToEmail toEmail=new ToEmail();
			DeptDO deptDO=deptService.selectById(arr[i]);
			//处理业务逻辑，获取需要发送邮件的事项和邮箱地址
			String time=simpleDateFormat.format(petitionLetter.getPetitiontime());
			if(deptDO.getEmail()!=null){
				to[0]=deptDO.getEmail();
			}else {
				continue;
			}
			toEmail.setTos(to);
			toEmail.setSubject("您收到编号为:("+petitionLetter.getReceiptno()+")的新信访件请及时查看");
			String content ="<b>【南康住建智慧政务平台提醒】,您收到来自:"+source+"的信访件</b>"
							+"</br><b>交办站室：</b>"+deptName+";</br><b>信访日期:</b>"+time
							+";"+"</br><b>规定回复日期:</b>"+simpleDateFormat.format(petitionLetter.getProcesstime())
							+";</br>请您在规定回复日期前尽快登录您的政务平台处理。";
//			String content="请参照上一份邮件进行日常工作，完成后返回处理意见";
			toEmail.setContent(content);
			try {
				toEmailService.htmlEmail(toEmail);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
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
		petitionLetterDO.setContent(petitionLetterNewDo.getContent());
		petitionLetterDO.setReceiptno(petitionLetterNewDo.getReceiptno());
		petitionLetterDO.setStatus(petitionLetterNewDo.getStatus());
		petitionLetterDO.setReceivetime(petitionLetterNewDo.getReceivetime());
		petitionLetterDO.setProcesstime(petitionLetterNewDo.getProcesstime());
		petitionLetterDO.setActualreplytime(petitionLetterNewDo.getActualreplytime());
		petitionLetterDO.setAcceptancetime(petitionLetterNewDo.getAcceptancetime());
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
		return "sys/petitionLetter/deptTrees";
	}
	
}
