package com.stylefeng.guns.modular.business.dao.order;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.business.model.order.EsOrderQrcode;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lossdate
 * @since 2018-09-12
 */
public interface EsOrderQrcodeMapper extends BaseMapper<EsOrderQrcode> {

    List<EsOrderQrcode> selectListPro();

    List<EsOrderQrcode> getEsOrderQrcode(Page<EsOrderQrcode> page);
}
