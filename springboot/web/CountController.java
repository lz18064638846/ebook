package com.ebook.springboot.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import com.ebook.springboot.ejb.User;
import com.ebook.springboot.pojo.CountItem;
import com.ebook.springboot.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 统计请求的控制层
 * @author lz
 * @date 2019-5-6
 */
@RestController
public class CountController {
    @Autowired
    CountService countService;

    @Value("${name.user}")
    String userAttribute;

    @PostMapping("UserCount/{type}")
    public List<CountItem> getUserCount(@PathVariable("type")String countType,
                                    @RequestParam("start")String start,
                                    @RequestParam("end")String end,
                                    HttpServletRequest request) throws Exception {
        //处理JPA获取mysql数据的时差问题
        Timestamp startDate=Timestamp.valueOf(start+" 08:00:00");
        Timestamp endDate=Timestamp.valueOf(end+" 08:00:00");
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute(userAttribute);
        switch (countType){
            case "Buy":
                return countService.getCountByBuy(startDate,endDate,user);
            case "Author":
                return countService.getCountByAuthor(startDate,endDate,user);
            case "Type":
                return countService.getUserCountByType(startDate,endDate,user);

                default:return null;
        }
    }

    @PostMapping("/AdminCount/{type}")
    public List<CountItem> getAdminCount(@PathVariable("type")String countType,
                                         @RequestParam("start")String start,
                                         @RequestParam("end")String end){
        //处理JPA获取mysql数据的时差问题
        Timestamp startDate=Timestamp.valueOf(start+" 08:00:00");
        Timestamp endDate=Timestamp.valueOf(end+" 08:00:00");
        switch (countType){
            case "Type":
                return countService.getAdminCountByType(startDate,endDate);
            case "User":
                return countService.getAdminCountByUser(startDate,endDate);
                default:return null;
        }
    }
}
