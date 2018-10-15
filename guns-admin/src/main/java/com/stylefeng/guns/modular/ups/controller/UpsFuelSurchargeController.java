package com.stylefeng.guns.modular.ups.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.util.Contant;
import com.stylefeng.guns.modular.ups.model.UpsFuelSurcharge;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.ups.service.IUpsFuelSurchargeService;

import java.util.List;
import java.util.Map;

/**
 * 燃油附加费管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-15 14:10:23
 */
@Controller
@RequestMapping("/upsFuelSurcharge")
public class UpsFuelSurchargeController extends BaseController {

    private String PREFIX = "/ups/upsFuelSurcharge/";

    @Autowired
    private IUpsFuelSurchargeService upsFuelSurchargeService;

    /**
     * 跳转到燃油附加费管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "upsFuelSurcharge.html";
    }

    /**
     * 跳转到添加燃油附加费管理
     */
    @RequestMapping("/upsFuelSurcharge_add")
    public String upsFuelSurchargeAdd() {
        return PREFIX + "upsFuelSurcharge_add.html";
    }

    /**
     * 跳转到修改燃油附加费管理
     */
    @RequestMapping("/upsFuelSurcharge_update/{upsFuelSurchargeId}")
    public String upsFuelSurchargeUpdate(@PathVariable String upsFuelSurchargeId, Model model) {
        UpsFuelSurcharge upsFuelSurcharge = upsFuelSurchargeService.selectById(upsFuelSurchargeId);
        model.addAttribute("item",upsFuelSurcharge);
        LogObjectHolder.me().set(upsFuelSurcharge);
        return PREFIX + "upsFuelSurcharge_edit.html";
    }

    /**
     * 获取燃油附加费管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String effectiveDate,
                       @RequestParam(required = false) String surcharge) {
        Page<UpsFuelSurcharge> page = new PageFactory<UpsFuelSurcharge>().defaultPage();
        String orderByField = page.getOrderByField();
        if(!StringUtils.isBlank(orderByField)) {
            orderByField = Contant.commonStringUtil(orderByField);
            page.setOrderByField(orderByField);
        }

        List<UpsFuelSurcharge> result = upsFuelSurchargeService.getUpsFuelSurcharge(page, effectiveDate, surcharge, page.getOrderByField(), page.isAsc());
        page.setRecords(result);
        return super.packForBT(page);
    }

    /**
     * 新增燃油附加费管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(UpsFuelSurcharge upsFuelSurcharge) {
        upsFuelSurchargeService.insert(upsFuelSurcharge);
        return SUCCESS_TIP;
    }

    /**
     * 删除燃油附加费管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String upsFuelSurchargeId) {
        upsFuelSurchargeService.deleteById(upsFuelSurchargeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改燃油附加费管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(UpsFuelSurcharge upsFuelSurcharge) {
        upsFuelSurchargeService.updateById(upsFuelSurcharge);
        return SUCCESS_TIP;
    }

    /**
     * 燃油附加费管理详情
     */
    @RequestMapping(value = "/detail/{upsFuelSurchargeId}")
    @ResponseBody
    public Object detail(@PathVariable("upsFuelSurchargeId") String upsFuelSurchargeId) {
        return upsFuelSurchargeService.selectById(upsFuelSurchargeId);
    }
}
