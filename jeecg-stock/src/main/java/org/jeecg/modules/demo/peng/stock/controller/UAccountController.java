package org.jeecg.modules.demo.peng.stock.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.peng.stock.entity.UAccount;
import org.jeecg.modules.demo.peng.stock.service.IUAccountService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 账户
 * @Author: jeecg-boot
 * @Date:   2022-11-28
 * @Version: V1.0
 */
@Api(tags="账户")
@RestController
@RequestMapping("/peng.stock/uAccount")
@Slf4j
public class UAccountController extends JeecgController<UAccount, IUAccountService> {
	@Autowired
	private IUAccountService uAccountService;
	
	/**
	 * 分页列表查询
	 *
	 * @param uAccount
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "账户-分页列表查询")
	@ApiOperation(value="账户-分页列表查询", notes="账户-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<UAccount>> queryPageList(UAccount uAccount,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<UAccount> queryWrapper = QueryGenerator.initQueryWrapper(uAccount, req.getParameterMap());
		Page<UAccount> page = new Page<UAccount>(pageNo, pageSize);
		IPage<UAccount> pageList = uAccountService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param uAccount
	 * @return
	 */
	@AutoLog(value = "账户-添加")
	@ApiOperation(value="账户-添加", notes="账户-添加")
	//@RequiresPermissions("peng.stock:u_account:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody UAccount uAccount) {
		uAccountService.save(uAccount);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param uAccount
	 * @return
	 */
	@AutoLog(value = "账户-编辑")
	@ApiOperation(value="账户-编辑", notes="账户-编辑")
	//@RequiresPermissions("peng.stock:u_account:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody UAccount uAccount) {
		uAccountService.updateById(uAccount);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "账户-通过id删除")
	@ApiOperation(value="账户-通过id删除", notes="账户-通过id删除")
	//@RequiresPermissions("peng.stock:u_account:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		uAccountService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "账户-批量删除")
	@ApiOperation(value="账户-批量删除", notes="账户-批量删除")
	//@RequiresPermissions("peng.stock:u_account:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.uAccountService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "账户-通过id查询")
	@ApiOperation(value="账户-通过id查询", notes="账户-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<UAccount> queryById(@RequestParam(name="id",required=true) String id) {
		UAccount uAccount = uAccountService.getById(id);
		if(uAccount==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(uAccount);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param uAccount
    */
    //@RequiresPermissions("peng.stock:u_account:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, UAccount uAccount) {
        return super.exportXls(request, uAccount, UAccount.class, "账户");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("peng.stock:u_account:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, UAccount.class);
    }

}
