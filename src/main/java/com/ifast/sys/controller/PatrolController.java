package com.ifast.sys.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.JsonArray;
import com.ifast.common.base.BaseController;
import com.ifast.common.utils.Result;
import com.ifast.sys.domain.PetitionLetterDO;
import com.ifast.sys.service.PatrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * 数据接入controller
 */
@Controller
@RequestMapping("/sys/patrol")
public class PatrolController extends BaseController {
	@Autowired
	private PatrolService patrolService;

	private String preix="sys/patrol";

	@GetMapping("")
	String Patrol(){
		return preix+"/patrol1";
	}

	/**
	 * 数据列表
	 * @return
	 */
	@ResponseBody
	@GetMapping("/list")
	public Result<Page<JSONObject>> list(){
		Map para =new HashMap();

		Page<JSONObject> page = patrolService.selectList(getPage(JSONObject.class));
		return Result.ok(page);
	}

	/**
	 * 数据列名
	 * @return
	 */
	@ResponseBody
	@GetMapping("/col")
	public Result<JSONArray> col(){
		//查询列名信息

		JSONArray colinfo=patrolService.selectcol();
		JSONArray coldata=new JSONArray();
		JSONObject data = colinfo.getJSONObject(0);
		for(Map.Entry<String, Object> entry : data.entrySet()) {
			System.out.println(entry.getKey()+entry.getValue());
			JSONObject col=new JSONObject();
			col.put("COLUMN_NAME",entry.getKey());
			if("check1".equals(entry.getKey())){
				col.put("COLUMN_COMMENT","1.检查水泵运行声音是否异常");
			}else if("check2".equals(entry.getKey())){
				col.put("COLUMN_COMMENT","2.检查配电柜电流、频率显示状态");
			} else if("check3".equals(entry.getKey())){
				col.put("COLUMN_COMMENT","3.检查个连接螺栓是否正常");
			}else if("check4".equals(entry.getKey())){
				col.put("COLUMN_COMMENT","4.检查泵体及连接管道是否有漏水现象");
			}else if("check5".equals(entry.getKey())){
				col.put("COLUMN_COMMENT","5.检查压力表指针是否在正常范围");
			}else if("check6".equals(entry.getKey())){
				col.put("COLUMN_COMMENT","6.测试选项");
			}
				else {
				col.put("COLUMN_COMMENT",entry.getKey());
			}

			coldata.add(col);
		}
		return Result.ok(coldata);
	}
}
