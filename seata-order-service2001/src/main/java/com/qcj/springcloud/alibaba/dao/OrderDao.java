package com.qcj.springcloud.alibaba.dao;

import com.qcj.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {
    void createOrder(Order order);

    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
