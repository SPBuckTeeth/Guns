package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.common.exception.BasicExceptionEnum;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.util.CommonUtils;
import com.stylefeng.guns.core.util.Contant;
import com.stylefeng.guns.core.util.FontUpQiniu;
import com.stylefeng.guns.core.util.QrcodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
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

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 二维码生成控制器
 *
 * @author fengshuonan
 * @Date 2018-09-12 15:24:41
 */
@Controller
@RequestMapping("/esOrderQrcode")
public class EsOrderQrcodeController extends BaseController {
    private static final Logger logger = Logger.getLogger(EsOrderQrcodeController.class);

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
    public Object add(String qrcodeNum, HttpServletRequest request) {
        if(StringUtils.isBlank(qrcodeNum)) {
            throw new GunsException(BasicExceptionEnum.NULL_NUM);
        }
        int num;
        try {
            num = Integer.parseInt(qrcodeNum);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GunsException(BasicExceptionEnum.ERROR_PATTERN);
        }

        String batch = qrcodeFactory(num, request);


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

    /**
     * 批量生成二维码
     * @param num 数量
     */
    private String qrcodeFactory(int num, HttpServletRequest request) {
        Date d = new Date();
        String batch = "ORDERQRCODE" + d.getTime();
        String qrcode;
        String id;
        List<EsOrderQrcode> orderQrcodeList = new ArrayList<>();
        //OutputStream os = null;
        ByteArrayOutputStream bos = null;
        try {

            for(int i = 0; i < num; i ++) {
                id = CommonUtils.randomID();
                EsOrderQrcode orderQrcode = new EsOrderQrcode();
                orderQrcode.setId(id);
                orderQrcode.setBatch(batch);
                orderQrcode.setStatus(0);
                orderQrcode.setCreateTime(d);
                orderQrcode.setUpdateTime(d);

                //动态地址
                //String qrURL = String.format("http://%s:%s%s%s%s", request.getServerName(), "80", request.getContextPath(), "/orderQrcode/scanOrderQrcode?qrcodeId=", id);
                //生产地址
                String qrURL = "https://fast.mrcargo.com/app-api/orderQrcode/scanOrderQrcode?qrcodeId="+id;
                //本地地址
                //String qrURL = String.format("http://%s:%s%s%s%s", "192.168.1.31", "8489", request.getContextPath(), "/orderQrcode/scanOrderQrcode?qrcodeId=", id);
                logger.debug("###### [生成二维码]qrURL === [ " + qrURL + " ] ######");

                //生成图片
                BufferedImage qrcodeImg = QrcodeUtil.encodeQrWebImg(qrURL, true);

                //二维码图片转inputStream上传七牛
                bos = new ByteArrayOutputStream();
                ImageIO.write(qrcodeImg, "jpg", bos);
                InputStream qrcodeStream = new ByteArrayInputStream(bos.toByteArray());
                qrcode = FontUpQiniu.streamUpQiniu(qrcodeStream);
                orderQrcode.setQrcode(qrcode);
                orderQrcodeList.add(orderQrcode);
            }

            if(orderQrcodeList.size() > 0) {
                //保存空白二维码到数据库
                esOrderQrcodeService.insertBatch(orderQrcodeList);
            }

        } catch (Exception e) {
            logger.debug("###### [生成二维码]getBarCode error  ######");
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return batch;
    }

}
