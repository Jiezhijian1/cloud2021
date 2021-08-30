package com.qcj.springcloud.alibaba.service;

public interface StorageService {
    /**
     * @param productId
     * @param count
     */
    void decrease(Long productId,Integer count);
}
