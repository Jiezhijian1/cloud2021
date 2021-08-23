package com.qcj.springcloud.controller;

import com.qcj.springcloud.entities.CommonResult;
import com.qcj.springcloud.entities.Payment;
import com.qcj.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("$serverPort")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("結果:"+result);

        if(result > 0){
            return new CommonResult(200,"success",result);
        }else{
            return new CommonResult(400,"fail",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("結果:"+payment);

        if(payment != null){
            return new CommonResult(200,"success",payment);
        }else{
            return new CommonResult(400,"noData Id:"+id,null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discoveryClient(){
        List<String> services = discoveryClient.getServices();
        for (String element : services){
            log.info(element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance : instances){
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeOut(){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return "hi , iam paymentzipkin server fall back,welcome to qcj";
    }
}
