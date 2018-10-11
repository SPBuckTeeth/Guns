package com.stylefeng.guns.modular.business.dao.order;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.business.model.order.EsOrderQrcode;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lossdate
 * @since 2018-09-12
 */
public interface EsOrderQrcodeMapper extends BaseMapper<EsOrderQrcode> {

    List<EsOrderQrcode> getEsOrderQrcode(Page<EsOrderQrcode> page);

    List<EsOrderQrcode> getEsOrderQrcode2(@Param("page") Page<EsOrderQrcode> page,
                                               @Param("beginTime") String beginTime,
                                               @Param("endTime") String endTime,
                                               @Param("batch") String batch,
                                               @Param("orderId") String orderId,
                                               @Param("status") String status,
                                               @Param("orderByField") String orderByField,
                                               @Param("isAsc") boolean asc);
}
