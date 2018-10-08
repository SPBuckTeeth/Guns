package com.stylefeng.guns.modular.sysproduct.service.impl;

import com.stylefeng.guns.modular.sysproduct.model.Product;
import com.stylefeng.guns.modular.sysproduct.dao.ProductMapper;
import com.stylefeng.guns.modular.sysproduct.service.IProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统企业表 服务实现类
 * </p>
 *
 * @author lossdate
 * @since 2018-10-08
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
