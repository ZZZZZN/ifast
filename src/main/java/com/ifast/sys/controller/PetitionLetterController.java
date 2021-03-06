package com.ifast.sys.controller;


import java.text.SimpleDateFormat;
import java.util.*;
import com.ifast.common.service.DictService;
import com.ifast.common.utils.ShiroUtils;
import com.ifast.sys.domain.*;
import com.ifast.sys.mail.entity.ToEmail;
import com.ifast.sys.mail.support.ToEmailService;
import com.ifast.sys.service.AssociationService;
import com.ifast.sys.service.DeptService;
import com.ifast.sys.service.PetitionLetterService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	String PetitionLetter(){
	    return preix+"/petitionLetter";
	}


	@ResponseBody
	@GetMapping("/list")
	public Result<Page<PetitionLetterDO>> list(PetitionLetterDO petitionLetterDTO, HttpServletRequest request){
		//通过shiro工具得到用户信息
		UserDO user= ShiroUtils.getSysUser();
		String name=request.getParameter("name");
		String deptId="";
		if(!user.getUsername().equals("admin")){
			deptId=user.getDeptId().toString();
		}
		//根据用户部门特定查询有该部门的信访件
		Map para =new HashMap();
		para.put("name",name);
		para.put("deptId",deptId);
		Page<PetitionLetterDO> page = petitionLetterService.selectPage(getPage(PetitionLetterDO.class),para);
        return Result.ok(page);
	}
	@Log("添加页面")
	@GetMapping("/add")
	@RequiresPermissions("sys:petitionLetter:add")
	String add(){
		return "sys/petitionLetter/add";
	}

	@Log("添加功能")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:petitionLetter:save")
	public Result<String> save( PetitionLetterDO petitionLetter){
		//得到交办站室的id
		String deptId=petitionLetter.getServiceDept();
		String arr[]=deptId.split(",");
		String deptName="";
		//插入新的信访件信息
		petitionLetterService.insert(petitionLetter);
		for (int i=0;i<arr.length;i++){
			DeptDO deptDO=deptService.selectById(arr[i]);
			if("".equals(deptName)){
				deptName=deptDO.getName();
			}else {
				deptName+=","+deptDO.getName();
			}
			//插入与信访件信息相关的站室信息到关联表
			AssociationDO association=new AssociationDO();
			association.setDepid(Integer.valueOf(arr[i]));
			association.setPetitionid(petitionLetter.getId());
			associationService.insert(association);
		}
		String source=dictService.getName("source_ptittion",petitionLetter.getSourcepetition().toString());
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		//根据选择的站室发送邮件提醒收到信访件
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
			toEmail.setContent(content);
			try {
				toEmailService.htmlEmail(toEmail);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
        return Result.ok();
	}

	@Log("编辑页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("sys:petitionLetter:edit")
	String edit(@PathVariable("id") Long id,Model model){
		PetitionLetterNewDo petitionLetter =petitionLetterService.selectOne(id);
		model.addAttribute("petitionLetter", petitionLetter);
		return "sys/petitionLetter/edit";
	}
	
	@Log("修改功能")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:petitionLetter:update")
	public Result<String>  update( PetitionLetterNewDo petitionLetterNewDo){
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
		//修改信访件信息
		boolean update = petitionLetterService.updateById(petitionLetterDO);
		//删除相关联表部门信息
		associationService.deleteAll(petitionLetterNewDo.getId());
		String deptId=petitionLetterNewDo.getDeptno();
		//插入修改好后信访件相应部门到相关联表
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

	/**
	 * 查询限期整改在当天还没改的负责人邮箱
	 */
	@GetMapping( "/remind")
	@ResponseBody
	public void remind(){
		//查询限期整改在当天还没改的负责人邮箱
		List<InvestigationDo> remind= petitionLetterService.selectTerm();

		for (InvestigationDo item:remind) {
			if(item.getEmail()==null){
				continue;
			}
			String[] email=new String[1];
			if(item.getEmail()!=null){
				email[0]=item.getEmail();
			}
			SendEmailForRemind(email,item.getProblem());
		}
	}

	//发邮件方法
	public void SendEmailForRemind(String[] emailAddress,String problem){
		ToEmail toEmail=new ToEmail();
		toEmail.setTos(emailAddress);
		toEmail.setSubject("您有工程项目隐患问题请及时销号");
		toEmail.setContent("提醒：您有项目隐患问题仍未销号.请及时销号处理。主要问题是："+problem);
		try {
			toEmailService.commonEmail(toEmail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
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
