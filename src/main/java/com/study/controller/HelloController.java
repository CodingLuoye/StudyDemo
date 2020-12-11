package com.study.controller;

import com.study.exception.MyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("订单管理")
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String index(){
        return "Hello world";
    }

    @RequestMapping("/hello1")
    public String hello() throws Exception {
        throw new Exception("发⽣错误");
    }

    @RequestMapping("/json")
    public String json() throws Exception {
        throw new MyException("发⽣错误2");
    }

    @ApiOperation("用户hello")
    @ApiImplicitParam(name = "name", value = "名称", dataType = "String")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello(@RequestParam String name) {
        return "Hello " + name;
    }

    class Name{
        String name;

        String idCard;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }
    }

    @GetMapping("/testReceivce")
    @ApiOperation("测试接受")
    public void testReceivce(Name name){
        System.out.println(name);
    }

}
