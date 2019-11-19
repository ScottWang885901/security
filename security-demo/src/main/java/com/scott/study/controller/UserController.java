package com.scott.study.controller;

import com.scott.study.entity.QueryUserCondition;
import com.scott.study.entity.User;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {
    /**
     *
     * @param nikeName
     * @return
     * @descript: @RequestParam(name[value]="userName")用name指定參數名，不用則用形參名,value作用相同
     */
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public List<User> getUsers(@RequestParam(value = "userName" ) String nikeName){
        System.out.println(nikeName);
        List<User> users = Arrays.asList(new User(),new User(),new User());
        return users;
    }

    /**
     *
     * @param condition
     * @return     SrpingMVC 可以將參數封裝到一個對象中 ，此時不用@RequestParam修飾,如果參數名不對應則為默認值
     * @descript:
     */
    @RequestMapping(value = "/queryUserByCondition",method = RequestMethod.GET)
    public List<User> queryByCondition( QueryUserCondition condition){
        System.out.println(ReflectionToStringBuilder.toString(condition,ToStringStyle.MULTI_LINE_STYLE));
        List<User> users = Arrays.asList(new User(),new User(),new User());
        return users;
    }

    /**
     *
     * @param condition
     * @return    Pageable : SpringData API  同樣可以用@PageableDefault()指定默認值，例如 @PageableDefault(page = 2, size = 17, sort = "username,asc")
     * @descript:
     */
    @RequestMapping(value = "/queryUserAddPageable",method = RequestMethod.GET)
    public List<User> queryByCondition(QueryUserCondition condition, Pageable pageable){
        System.out.println(ReflectionToStringBuilder.toString(condition,ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(ReflectionToStringBuilder.toString(pageable,ToStringStyle.MULTI_LINE_STYLE));
        List<User> users = Arrays.asList(new User(),new User(),new User());
        return users;
    }
}
