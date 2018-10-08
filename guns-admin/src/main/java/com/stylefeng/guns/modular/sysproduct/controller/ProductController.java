package com.stylefeng.guns.modular.sysproduct.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.sysproduct.model.Product;
import com.stylefeng.guns.modular.sysproduct.service.IProductService;

/**
 * 系统产品管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-08 13:43:13
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

    private String PREFIX = "/sysproduct/product/";

    @Autowired
    private IProductService productService;

    /**
     * 跳转到系统产品管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "product.html";
    }

    /**
     * 跳转到添加系统产品管理
     */
    @RequestMapping("/product_add")
    public String productAdd() {
        return PREFIX + "product_add.html";
    }

    /**
     * 跳转到修改系统产品管理
     */
    @RequestMapping("/product_update/{productId}")
    public String productUpdate(@PathVariable String productId, Model model) {
        Product product = productService.selectById(productId);
        model.addAttribute("item",product);
        LogObjectHolder.me().set(product);
        return PREFIX + "product_edit.html";
    }

    /**
     * 获取系统产品管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return productService.selectList(null);
    }

    /**
     * 新增系统产品管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Product product) {
        productService.insert(product);
        return SUCCESS_TIP;
    }

    /**
     * 删除系统产品管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String productId) {
        productService.deleteById(productId);
        return SUCCESS_TIP;
    }

    /**
     * 修改系统产品管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Product product) {
        productService.updateById(product);
        return SUCCESS_TIP;
    }

    /**
     * 系统产品管理详情
     */
    @RequestMapping(value = "/detail/{productId}")
    @ResponseBody
    public Object detail(@PathVariable("productId") String productId) {
        return productService.selectById(productId);
    }
}
