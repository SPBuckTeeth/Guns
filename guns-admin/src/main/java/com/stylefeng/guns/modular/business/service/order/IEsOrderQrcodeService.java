package com.stylefeng.guns.modular.business.service.order;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.business.model.order.EsOrderQrcode;
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

    List<EsOrderQrcode> getEsOrderQrcode(Page<EsOrderQrcode> page);

    List<EsOrderQrcode> getEsOrderQrcode2(Page<EsOrderQrcode> page, String beginTime, String endTime, String batch, String orderId, String status, String orderByField, boolean asc);
}
