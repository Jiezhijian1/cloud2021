package com.qcj.springcloud.alibaba.controller;

import com.qcj.springcloud.alibaba.domain.CommonResult;
import com.qcj.springcloud.alibaba.domain.Order;
import com.qcj.springcloud.alibaba.service.OrderService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200,"success");
    }
}
