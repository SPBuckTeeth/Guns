package com.stylefeng.guns.modular.business.service.order.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.business.model.order.EsOrderQrcode;
import com.stylefeng.guns.modular.business.dao.order.EsOrderQrcodeMapper;
import com.stylefeng.guns.modular.business.service.order.IEsOrderQrcodeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lossdate
 * @since 2018-09-12
 */
@Service
public class EsOrderQrcodeServiceImpl extends ServiceImpl<EsOrderQrcodeMapper, EsOrderQrcode> implements IEsOrderQrcodeService {
    @Autowired
    private EsOrderQrcodeMapper esOrderQrcodeMapper;

    @Override
    public List<EsOrderQrcode> selectListPro() {
        return esOrderQrcodeMapper.selectListPro();
    }

    @Override
    public List<EsOrderQrcode> getEsOrderQrcode(Page<EsOrderQrcode> page) {
        return esOrderQrcodeMapper.getEsOrderQrcode(page);
    }
}
