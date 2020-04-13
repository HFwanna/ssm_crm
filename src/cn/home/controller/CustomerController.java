package cn.home.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.home.pojo.BaseDict;
import cn.home.pojo.Customer;
import cn.home.pojo.QueryVo;
import cn.home.service.BaseDictServiceImpl;
import cn.home.utils.Page;

@Controller
public class CustomerController {
	
	@Resource
	private BaseDictServiceImpl baseDictService;
	
	//在springmvc中配置了读取文件位置context:property-placeholder即可
	@Value(value="${industryType.code}")
	private String industryTypeCode;
	/*@Value("${fromType.code}")
	private String fromTypeCode;
	@Value("${levelType.code}")
	private String levelTypeCode;*/
	
	//入口
	@RequestMapping("/customer/list")
	public String list(Model model,QueryVo vo) {
		
		
		List<BaseDict> industryType = baseDictService.selectByCode(industryTypeCode);
		List<BaseDict> fromType = baseDictService.selectByCode("002");
		List<BaseDict> levelType = baseDictService.selectByCode("006");
		model.addAttribute("industryType", industryType);
		model.addAttribute("fromType", fromType);
		model.addAttribute("levelType", levelType);
		
		//查询条件
		Page<Customer> page = baseDictService.selectByQueryVo(vo);
		model.addAttribute("page", page);
		model.addAttribute("customerName", vo.getCustName());
		model.addAttribute("customerFrom", vo.getCustSource());
		model.addAttribute("custIndustry", vo.getCustIndustry());
		model.addAttribute("custLevel", vo.getCustLevel());
		
		return "customer";
	}
	
	@RequestMapping("/customer/edit.action")
	public @ResponseBody Customer edit(Integer id) {
		return baseDictService.selectById(id);
	};
	
	@RequestMapping("/customer/update.action")
	public @ResponseBody String saveCustomer(Customer customer){
		baseDictService.saveCustomer(customer);
		return "OK";
	}
	
	@RequestMapping("/customer/delete.action")
	public @ResponseBody String deleteCustomer(Integer id){
		baseDictService.deleteCustomer(id);
		return "OK";
	}
}
