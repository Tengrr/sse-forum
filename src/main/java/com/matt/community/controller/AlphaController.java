package com.matt.community.controller;

import com.matt.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "hello springboot";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData() {
        return alphaService.find();
    }

    //处理get请求
    //students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "Some students";
    }

    //处理get请求
    // student/{id}
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "A student";
    }

    //处理Post请求
    // student
    // 参数的名称需和表单中的名称一致，name,age
    // 需要在student.html中提交表单以发送post请求
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //响应HTML数据
    //属性名需与模板中的名字一致
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getTeacher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "Matt");
        mav.addObject("age", 21);
        mav.setViewName("/demo/view");
        return mav;
    }

    //响应HTML数据的第2种方法
    //当没有响应体注解时会返回路径对应的html
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model) {
        model.addAttribute("name","BJUT");
        model.addAttribute("age",62);
        return "/demo/view";
    }

    //响应Json数据
    //服务端在响应时自动将返回的对象转为Json格式
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp() {
        Map<String, Object> empMap = new HashMap<String, Object>();
        empMap.put("name", "Matt");
        empMap.put("age", 21);
        empMap.put("salary", 100000);
        return empMap;
    }

    //响应Json数组
    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps() {
        List<Map<String, Object>> empList = new ArrayList<>();
        Map<String, Object> empMap = new HashMap<String, Object>();

        empMap.put("name", "Matt");
        empMap.put("age", 21);
        empMap.put("salary", 100000);
        empList.add(empMap);

        empMap.put("name", "Mark");
        empMap.put("age", 22);
        empMap.put("salary", 120000);
        empList.add(empMap);

        empMap.put("name", "Jerry");
        empMap.put("age", 21);
        empMap.put("salary", 80000);
        empList.add(empMap);


        return empList;
    }

}
