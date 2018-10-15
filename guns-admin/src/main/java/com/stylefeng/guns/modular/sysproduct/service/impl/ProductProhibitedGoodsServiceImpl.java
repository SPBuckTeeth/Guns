package com.stylefeng.guns.modular.sysproduct.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.sysproduct.model.ProductProhibitedGoods;
import com.stylefeng.guns.modular.sysproduct.dao.ProductProhibitedGoodsMapper;
import com.stylefeng.guns.modular.sysproduct.service.IProductProhibitedGoodsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.sysproduct.vo.ProductProhibitedGoodsVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lossdate
 * @since 2018-10-11
 */
@Service
public class ProductProhibitedGoodsServiceImpl extends ServiceImpl<ProductProhibitedGoodsMapper, ProductProhibitedGoods> implements IProductProhibitedGoodsService {

    @Override
    public List<ProductProhibitedGoodsVO> getProductProhibitedGoods(Page<ProductProhibitedGoodsVO> page, String sysProductId, String name, String orderByField, boolean asc) {
        return this.baseMapper.getProductProhibitedGoods(page, sysProductId, name, orderByField, asc);
    }
}
