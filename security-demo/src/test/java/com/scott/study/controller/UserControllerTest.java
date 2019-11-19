package com.scott.study.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * JsonPath 詳情 :  https://github.com/json-path/JsonPath
 *     Operator	                        Description
 *      $	                            The root element to query. This starts all path expressions.
 *      @	                            The current node being processed by a filter predicate.
 *      *	                            Wildcard. Available anywhere a name or numeric are required.
 *      ..	                            Deep scan. Available anywhere a name is required.
 *      .<name>	                        Dot-notated child
 *      ['<name>' (, '<name>')]	        Bracket-notated child or children
 *      [<number> (, <number>)]	        Array index or indexes
 *      [start:end]	                    Array slice operator
 *      [?(<expression>)]	            Filter expression. Expression must evaluate to a boolean value.
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc ;
    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @Test
    public void  testWhenQueryUsers() throws Exception {
        mockMvc.perform(get("/user")
                .param("name","scott")   // 該請求用@RequestParam 修飾，必須傳該參數名
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    public void  testQueryUsersByCondition() throws Exception {
        mockMvc.perform(get("/queryUserByCondition")
                .param("name","scott")   // 該請求用@RequestParam 修飾，必須傳該參數名
                .param("gaf","18")
                .param("ageTo","80")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    public void  testQueryUsersAddPageable() throws Exception {
        mockMvc.perform(get("/queryUserAddPageable")
                .param("name","scott")   // 該請求用@RequestParam 修飾，必須傳該參數名
                .param("gaf","18")
                .param("ageTo","80")
                .param("size","80")    //
                .param("page","3")
                .param("sort","age,desc")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }
}
