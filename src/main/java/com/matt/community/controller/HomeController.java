package com.matt.community.controller;

import com.matt.community.entity.DiscussPost;
import com.matt.community.entity.Page;
import com.matt.community.entity.User;
import com.matt.community.service.DiscussPostService;
import com.matt.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    //将前十个帖子信息与对应的用户信息封装至List<Map>中
    public String getIndexPage(Model model, Page page) {

        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");

        List<DiscussPost> discussPosts =  discussPostService.findDiscussPosts(0, page.getLimit(), page.getOffset());
        List<Map<String,Object>> maps = new ArrayList<>();

        if(discussPosts != null) {
            for(DiscussPost discussPost : discussPosts) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", discussPost);
                User user = userService.findUserById(discussPost.getUserId());
                map.put("user", user);
                maps.add(map);
            }
        }

        model.addAttribute("discussPosts", maps);

        return "/index";

    }

}
