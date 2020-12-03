package com.ifast.sys.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ifast.common.annotation.Log;
import com.ifast.common.base.BaseController;
import com.ifast.common.domain.DictDO;
import com.ifast.common.domain.Tree;
import com.ifast.common.service.DictService;
import com.ifast.common.utils.Result;
import com.ifast.demo.controller.PoiUtils;
import com.ifast.sys.domain.DeptDO;
import com.ifast.sys.domain.RoleDO;
import com.ifast.sys.domain.UserDO;
import com.ifast.sys.service.RoleService;
import com.ifast.sys.service.UserService;
import com.ifast.sys.vo.UserVO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * </pre>
 * 
 * <small> 2018年3月23日 | Aron</small>
 */
@RequestMapping("/sys/user")
@Controller
public class UserController extends BaseController {
    private String prefix = "sys/user";
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    DictService dictService;
    
    @RequiresPermissions("sys:user:user")
    @GetMapping("")
    String user(Model model) {
        return prefix + "/user";
    }
    
    @GetMapping("/list")
    @ResponseBody
    public Result<Page<UserDO>> list(UserDO userDTO) {
        // 查询列表数据
        Page<UserDO> page = userService.selectPage(getPage(UserDO.class), userService.convertToEntityWrapper("name", userDTO.getName(), "deptId", userDTO.getDeptId()));
        return Result.ok(page);
    }

    
    @RequiresPermissions("sys:user:add")
    @GetMapping("/add")
    String add(Model model) {
        List<RoleDO> roles = roleService.selectList(null);
        model.addAttribute("roles", roles);
        return prefix + "/add";
    }

    @RequiresPermissions("sys:user:edit")
    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable("id") Long id) {
        UserDO userDO = userService.selectById(id);
        model.addAttribute("user", userDO);
        List<RoleDO> roles = roleService.findListStatusByUserId(id);
        model.addAttribute("roles", roles);
        return prefix + "/edit";
    }

    @RequiresPermissions("sys:user:add")
    @Log("保存用户")
    @PostMapping("/save")
    @ResponseBody
    Result<String> save(UserDO user) {
        userService.insert(user);
        return Result.ok();
    }

    @RequiresPermissions("sys:user:edit")
    @Log("更新用户")
    @PostMapping("/update")
    @ResponseBody
    Result<String> update(UserDO user) {
        userService.updateById(user);
        return Result.ok();
    }

    @RequiresPermissions("sys:user:edit")
    @Log("更新用户")
    @PostMapping("/updatePeronal")
    @ResponseBody
    Result<String> updatePeronal(UserDO user) {
        userService.updatePersonal(user);
        return Result.ok();
    }

    @RequiresPermissions("sys:user:remove")
    @Log("删除用户")
    @PostMapping("/remove")
    @ResponseBody
    Result<String> remove(Long id) {
        userService.deleteById(id);
        return Result.ok();
    }

    @RequiresPermissions("sys:user:batchRemove")
    @Log("批量删除用户")
    @PostMapping("/batchRemove")
    @ResponseBody
    Result<String> batchRemove(@RequestParam("ids[]") Long[] userIds) {
        userService.deleteBatchIds(Arrays.asList(userIds));
        return Result.ok();
    }
    
    @PostMapping("/exist")
    @ResponseBody
    boolean exist(@RequestParam Map<String, Object> params) {
        // 存在，不通过，false
        return !userService.exist(params);
    }


    @GetMapping("/export/{name}/{deptId}")
    @ResponseBody
    public void testExport(HttpServletResponse response, @PathVariable("name") String name,  @PathVariable("deptId")String predeptId) throws IOException {
        PoiUtils.Export(response,name,predeptId);
    }

    @RequiresPermissions("sys:user:resetPwd")
    @GetMapping("/resetPwd/{id}")
    String resetPwd(@PathVariable("id") Long userId, Model model) {
        UserDO userDO = new UserDO();
        userDO.setId(userId);
        model.addAttribute("user", userDO);
        return prefix + "/reset_pwd";
    }

    @Log("提交更改用户密码")
    @PostMapping("/resetPwd")
    @ResponseBody
    Result<String> resetPwd(UserVO userVO) {
        userService.resetPwd(userVO, getUser());
        return Result.ok();
    }

    @RequiresPermissions("sys:user:resetPwd")
    @Log("admin提交更改用户密码")
    @PostMapping("/adminResetPwd")
    @ResponseBody
    Result<String> adminResetPwd(UserVO userVO) {
        userService.adminResetPwd(userVO);
        return Result.ok();

    }


    @GetMapping("/tree")
    @ResponseBody
    public Tree<DeptDO> tree() {
        Tree<DeptDO> tree = new Tree<DeptDO>();
        tree = userService.getTree();
        return tree;
    }
    
    @GetMapping("/treeView")
    String treeView() {
        return prefix + "/userTree";
    }
    
    @GetMapping("/personal")
    String personal(Model model) {
        UserDO userDO = userService.selectById(getUserId());
        model.addAttribute("user", userDO);
        List<DictDO> hobbyList = dictService.getHobbyList(userDO);
        model.addAttribute("hobbyList", hobbyList);
        List<DictDO> sexList = dictService.getSexList();
        model.addAttribute("sexList", sexList);
        //获取人员信息字典-职级
        List<DictDO> rankList =dictService.selectRankList();
        //获取人员信息字典-民族
        List<DictDO> nationList =dictService.selectNationList();
        //获取人员信息字典-职级
        List<DictDO> politicalList =dictService.selectpoliticalList();
        //获取人员信息字典-编制所在单位
        List<DictDO> bzszdwList =dictService.selectbzszdwList();
        //获取人员信息字典-编制状态
        List<DictDO> bzlbList =dictService.selectbzlbList();
        //获取人员信息字典-编制状态
        List<DictDO> education_topList =dictService.selecteducation_topList();
        //获取人员信息字典-编制状态
        List<DictDO> education_typeList =dictService.selecteducation_typeList();
        //获取人员信息字典-编制状态
        List<DictDO> degree_topList =dictService.selectdegree_topList();
        //获取人员信息字典-进入单位形式
        List<DictDO> jrdwxsList =dictService.selectjrdwxsList();
        //获取人员信息字典-人员状态
        List<DictDO> ryztList =dictService.selectryztList();
        model.addAttribute("rankList", rankList);
        model.addAttribute("nationList", nationList);
        model.addAttribute("politicalList", politicalList);
        model.addAttribute("bzszdwList", bzszdwList);
        model.addAttribute("bzlbList", bzlbList);
        model.addAttribute("education_topList", education_topList);
        model.addAttribute("education_typeList", education_typeList);
        model.addAttribute("degree_topList", degree_topList);
        model.addAttribute("jrdwxsList", jrdwxsList);
        model.addAttribute("ryztList", ryztList);
        return prefix + "/personal";
    }
    
    @Log("上传头像")
    @ResponseBody
    @PostMapping("/uploadImg")
    Result<?> uploadImg(@RequestParam("avatar_file") MultipartFile file, String avatar_data, HttpServletRequest request)
            throws Exception {
        Map<String, Object> result = new HashMap<>();
        result = userService.updatePersonalImg(file, avatar_data, getUserId());
        return Result.ok(result);
    }




}
