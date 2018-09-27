package com.stylefeng.guns.modular.system.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.EsOrderQrcode;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lossdate
 * @since 2018-09-12
 */
public interface IEsOrderQrcodeService extends IService<EsOrderQrcode> {
    List<EsOrderQrcode> selectListPro();

    List<EsOrderQrcode> getEsOrderQrcode(Page<EsOrderQrcode> page);
}
