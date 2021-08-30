package com.qcj.springcloud.alibaba.service.impl;

import com.qcj.springcloud.alibaba.dao.AccountDao;
import com.qcj.springcloud.alibaba.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Resource
    AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("------>account-service - start");
        accountDao.decrease(userId,money);
        LOGGER.info("------>account-service - over");
    }
}
