package com.ebook.springboot.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ebook.springboot.dao.OrdersDAO;
import com.ebook.springboot.ejb.Book;
import com.ebook.springboot.ejb.OrderItem;
import com.ebook.springboot.ejb.Orders;
import com.ebook.springboot.ejb.User;
import com.ebook.springboot.pojo.CountItem;
import com.ebook.springboot.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户和管理员进行统计的业务层实现
 *
 * @author lz
 * @date 2019-5-13
 */
@Service
public class CountServiceImpl implements CountService {
    private OrdersDAO ordersDAO;

    @Autowired
    public CountServiceImpl(OrdersDAO ordersDAO){
        this.ordersDAO=ordersDAO;
    }

    private final String[] types={"网络文学","科幻小说","科学著作","热门漫画","教辅资料","实用技能",
            "外国文学","侦探推理","人物传记","中国文学"};

    @Override
    public List<CountItem> getCountByBuy(Timestamp start, Timestamp end, User user){
        List<CountItem> itemList=new ArrayList<>();
        List<Orders> ordersList=ordersDAO.findByOrderTimeBetweenAndUser(start,end,user);
        for(Orders order:ordersList){
            for(OrderItem item:order.getOrderItems()){
                Book book=item.getBook();
                CountItem countItem=new CountItem(book.getName(),book.getType(),item.getOrderNumber(),order.getOrderTime());
                itemList.add(countItem);
            }
        }
        return itemList;
    }

    @Override
    public List<CountItem> getCountByAuthor(Timestamp start,Timestamp end,User user){
        List<CountItem> itemList=new ArrayList<>();
        HashMap<String,Integer> authorCount=new HashMap<>(20);
        List<Orders> ordersList=ordersDAO.findByOrderTimeBetweenAndUser(start,end,user);
        for(Orders order:ordersList){
            for(OrderItem item:order.getOrderItems()){
                String author=item.getBook().getAuthor();
                if(authorCount.containsKey(author)){
                    int cnt= authorCount.remove(author);
                    cnt+=item.getOrderNumber();
                    authorCount.put(author,cnt);
                }
                else{
                    authorCount.put(author,item.getOrderNumber());
                }
            }
        }
        for (Map.Entry<String, Integer> entry : authorCount.entrySet()) {
            CountItem countItem=new CountItem(entry.getKey(),entry.getValue());
            itemList.add(countItem);
        }
        return itemList;
    }

    @Override
    public List<CountItem> getUserCountByType(Timestamp start,Timestamp end,User user){
        List<Orders> ordersList=ordersDAO.findByOrderTimeBetweenAndUser(start,end,user);
        return getCountByType(ordersList);
    }

    @Override
    public List<CountItem> getAdminCountByType(Timestamp start,Timestamp end){
        List<Orders> ordersList=ordersDAO.findByOrderTimeBetween(start,end);
        return getCountByType(ordersList);
    }

    @Override
    public List<CountItem> getAdminCountByUser(Timestamp start,Timestamp end){
        List<Orders> ordersList=ordersDAO.findByOrderTimeBetween(start, end);
        List<CountItem> itemList=new ArrayList<>();
        for(Orders order:ordersList){
            int buyNumber=0;
            for (OrderItem item:order.getOrderItems()){
                buyNumber+=item.getOrderNumber();
            }
            CountItem countItem;
            if((countItem=findUser(order.getUser().getUserId(),itemList))!=null){
                itemList.remove(countItem);
                countItem.setNumber(countItem.getNumber()+buyNumber);
                countItem.setUserCost(countItem.getUserCost()+order.getCost());
                itemList.add(countItem);
            }
            else {
                countItem = new CountItem(order.getUser().getUserName(), order.getUser().getUserId(),
                        buyNumber, order.getCost());
                itemList.add(countItem);
            }
        }
        return itemList;
    }

    private List<CountItem> getCountByType(List<Orders> ordersList){
        List<CountItem> itemList=new ArrayList<>();
        for(String type:types) {
            int cnt=0;
            for (Orders order : ordersList) {
                for (OrderItem item : order.getOrderItems()) {
                    if (item.getBook().getType().contains(type)){
                        cnt+=item.getOrderNumber();
                    }
                }
            }
            CountItem countItem=new CountItem(type);
            countItem.setNumber(cnt);
            itemList.add(countItem);
        }
        return itemList;
    }

    private CountItem findUser(int userID,List<CountItem> itemList){
        for(CountItem item:itemList){
            if(userID==item.getUserID()){
                return item;
            }
        }
        return null;
    }
}
