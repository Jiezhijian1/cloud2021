package com.qcj.springcloud.alibaba.service.impl;

import com.qcj.springcloud.alibaba.dao.OrderDao;
import com.qcj.springcloud.alibaba.domain.Order;
import com.qcj.springcloud.alibaba.service.AccountService;
import com.qcj.springcloud.alibaba.service.OrderService;
import com.qcj.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;

    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("----->add");

        orderDao.createOrder(order);

        log.info("----->-Count");

        storageService.decrease(order.getProductId(),order.getCount());
        log.info("----->-end");

        log.info("----->-Money");

        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("----->-end");

        log.info("----->changeStart");
        orderDao.update(order.getUserId(),0);
        log.info("----->changeOver");

        log.info("----->over");
    }
}
