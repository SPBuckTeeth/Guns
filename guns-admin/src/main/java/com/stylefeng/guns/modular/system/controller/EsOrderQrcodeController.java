package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.common.constant.state.BizLogType;
import com.stylefeng.guns.core.util.Contant;
import com.stylefeng.guns.modular.system.model.OperationLog;
import com.stylefeng.guns.modular.system.warpper.EsOrderQrcodeWarpper;
import com.stylefeng.guns.modular.system.warpper.LogWarpper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.EsOrderQrcode;
import com.stylefeng.guns.modular.system.service.IEsOrderQrcodeService;

import java.util.List;
import java.util.Map;

/**
 * 二维码生成控制器
 *
 * @author fengshuonan
 * @Date 2018-09-12 15:24:41
 */
@Controller
@RequestMapping("/esOrderQrcode")
public class EsOrderQrcodeController extends BaseController {

    private String PREFIX = "/system/esOrderQrcode/";

    @Autowired
    private IEsOrderQrcodeService esOrderQrcodeService;

    /**
     * 跳转到二维码生成首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "esOrderQrcode.html";
    }

    /**
     * 跳转到添加二维码生成
     */
    @RequestMapping("/esOrderQrcode_add")
    public String esOrderQrcodeAdd() {
        return PREFIX + "esOrderQrcode_add.html";
    }

    /**
     * 跳转到修改二维码生成
     */
    @RequestMapping("/esOrderQrcode_update/{esOrderQrcodeId}")
    public String esOrderQrcodeUpdate(@PathVariable String esOrderQrcodeId, Model model) {
        EsOrderQrcode esOrderQrcode = esOrderQrcodeService.selectById(esOrderQrcodeId);
        model.addAttribute("item",esOrderQrcode);
        LogObjectHolder.me().set(esOrderQrcode);
        return PREFIX + "esOrderQrcode_edit.html";
    }

    /**
     * 获取二维码生成列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
//        Page<OperationLog> page = new PageFactory<OperationLog>().defaultPage();
//        List<Map<String, Object>> result = operationLogService.getOperationLogs(page, beginTime, endTime, logName, BizLogType.valueOf(logType), page.getOrderByField(), page.isAsc());
//        page.setRecords((List<OperationLog>) new LogWarpper(result).warp());
//        return super.packForBT(page);

        Page<EsOrderQrcode> page = new PageFactory<EsOrderQrcode>().defaultPage();
        String orderByField = page.getOrderByField();
        if(!StringUtils.isBlank(orderByField)) {
            orderByField = Contant.commonStringUtil(orderByField);
            page.setOrderByField(orderByField);
        }
        List<EsOrderQrcode> result = esOrderQrcodeService.getEsOrderQrcode(page);
        page.setRecords(result);
        return super.packForBT(page);

        //List<EsOrderQrcode> list = esOrderQrcodeService.selectList(null);
        //List<EsOrderQrcode> list = esOrderQrcodeService.selectListPro();
        //return list;
    }

    /**
     * 新增二维码生成
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(EsOrderQrcode esOrderQrcode) {
        esOrderQrcodeService.insert(esOrderQrcode);
        return SUCCESS_TIP;
    }

    /**
     * 删除二维码生成
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String esOrderQrcodeId) {
        esOrderQrcodeService.deleteById(esOrderQrcodeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改二维码生成
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(EsOrderQrcode esOrderQrcode) {
        esOrderQrcodeService.updateById(esOrderQrcode);
        return SUCCESS_TIP;
    }

    /**
     * 二维码生成详情
     */
    @RequestMapping(value = "/detail/{esOrderQrcodeId}")
    @ResponseBody
    public Object detail(@PathVariable("esOrderQrcodeId") String esOrderQrcodeId) {
        return esOrderQrcodeService.selectById(esOrderQrcodeId);
    }
}
