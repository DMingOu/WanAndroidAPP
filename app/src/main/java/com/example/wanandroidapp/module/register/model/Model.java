package com.example.wanandroidapp.module.register.model;

import com.example.wanandroidapp.module.register.contract.Contract;

/**
 * @author: ODM
 * @date: 2019/7/13
 */
public class Model implements Contract.Model {
    @Override
    public void getRegister(String userName, String password, String rePassword) {
        //网络请求 --注册 ，将结果回调到 P层
    }
}
