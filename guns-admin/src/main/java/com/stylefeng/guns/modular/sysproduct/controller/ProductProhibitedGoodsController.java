package com.stylefeng.guns.modular.sysproduct.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.sysproduct.model.ProductProhibitedGoods;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.sysproduct.service.IProductProhibitedGoodsService;

/**
 * 系统违禁品管理控制器
 *
 * @author lossdate
 * @Date 2018-10-11 13:27:51
 */
@Controller
@RequestMapping("/productProhibitedGoods")
public class ProductProhibitedGoodsController extends BaseController {

    private String PREFIX = "/sysproduct/productProhibitedGoods/";

    @Autowired
    private IProductProhibitedGoodsService productProhibitedGoodsService;

    /**
     * 跳转到系统违禁品管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "productProhibitedGoods.html";
    }

    /**
     * 跳转到添加系统违禁品管理
     */
    @RequestMapping("/productProhibitedGoods_add")
    public String productProhibitedGoodsAdd() {
        return PREFIX + "productProhibitedGoods_add.html";
    }

    /**
     * 跳转到修改系统违禁品管理
     */
    @RequestMapping("/productProhibitedGoods_update/{productProhibitedGoodsId}")
    public String productProhibitedGoodsUpdate(@PathVariable String productProhibitedGoodsId, Model model) {
        ProductProhibitedGoods productProhibitedGoods = productProhibitedGoodsService.selectById(productProhibitedGoodsId);
        model.addAttribute("item",productProhibitedGoods);
        LogObjectHolder.me().set(productProhibitedGoods);
        return PREFIX + "productProhibitedGoods_edit.html";
    }

    /**
     * 获取系统违禁品管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return productProhibitedGoodsService.selectList(null);
    }

    /**
     * 新增系统违禁品管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ProductProhibitedGoods productProhibitedGoods) {
        productProhibitedGoodsService.insert(productProhibitedGoods);
        return SUCCESS_TIP;
    }

    /**
     * 删除系统违禁品管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String productProhibitedGoodsId) {
        productProhibitedGoodsService.deleteById(productProhibitedGoodsId);
        return SUCCESS_TIP;
    }

    /**
     * 修改系统违禁品管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ProductProhibitedGoods productProhibitedGoods) {
        productProhibitedGoodsService.updateById(productProhibitedGoods);
        return SUCCESS_TIP;
    }

    /**
     * 系统违禁品管理详情
     */
    @RequestMapping(value = "/detail/{productProhibitedGoodsId}")
    @ResponseBody
    public Object detail(@PathVariable("productProhibitedGoodsId") String productProhibitedGoodsId) {
        return productProhibitedGoodsService.selectById(productProhibitedGoodsId);
    }
}
