package com.example.wanandroidapp.module.login.model;

import com.example.wanandroidapp.module.login.contract.Contract;

/**
 * @author: ODM
 * @date: 2019/7/13
 */
public class Model implements Contract.Model {

    @Override
    public void getLogin(String userName, String password) {
            //Post请求登录，然后将请求成功失败的结果返回到P层
    }
}
