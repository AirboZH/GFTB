package cn.airbozh.controller;

import cn.airbozh.pojo.Commodity;
import cn.airbozh.pojo.User;
import cn.airbozh.service.CommodityService;
import cn.airbozh.service.UserService;
import cn.airbozh.utility.Auth.Auth;
import cn.airbozh.utility.Auth.AuthException;
import cn.airbozh.utility.ResMsg;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CommodityController {
    @Resource
    CommodityService commodityService;
    @Resource
    UserService userService;
    ResMsg<?> resMsg;

    @RequestMapping(value = "/commodity",method = RequestMethod.POST)
    @Auth
    public ResMsg<?> createCommodity(@RequestBody Commodity commodity,User user){
        if (userService.findUserById(user.getUserId()).getRole() < 1)
            throw new AuthException(HttpStatus.UNAUTHORIZED, "没有权限");
        if(commodityService.createCommodity(commodity))
            resMsg = new ResMsg<>(1,"Create Success");
        else
            resMsg = new ResMsg<>(0, "Create Fail");
        return resMsg;
    }

    @RequestMapping(value = "/commodity", method = RequestMethod.GET)
    public ResMsg<?> getCommodities() {
        List<Commodity> commodityList = commodityService.getCommodities();
        if (commodityList == null) {
            resMsg = new ResMsg<>(0, "GetUsers Fail");
        } else {
            resMsg = new ResMsg<>(1, "GetUsers Success", commodityList);
        }
        return resMsg;
    }

    @RequestMapping(value = "/commodity/{commodityId}",method = RequestMethod.GET)
    public ResMsg<?> getCommodityById(@PathVariable("commodityId") int commodityId){
        Commodity commodity = commodityService.findCommodityById(commodityId);
        if (commodity == null) {
            resMsg = new ResMsg<>(0, "No Result");
        } else {
            resMsg = new ResMsg<>(1, "Find Success", commodity);
        }
        return resMsg;
    }

    @RequestMapping(value = "/commodity/{commodityId}", method = RequestMethod.PATCH)
    @Auth
    public ResMsg<?> updateCommodityById(@PathVariable("commodityId") int commodityId, @RequestBody Commodity commodity,User user) {
        if (userService.findUserById(user.getUserId()).getRole() < 1)
            throw new AuthException(HttpStatus.UNAUTHORIZED, "没有权限");
        Commodity dbCommodity = commodityService.updateCommodity(commodityId,commodity);
            if (dbCommodity != null)
                resMsg = new ResMsg<>(1, "Update Success", dbCommodity);
            else {
                resMsg = new ResMsg<>(0, "Update Fail");
            }
            return resMsg;
    }

    @RequestMapping(value = "/commodity/{commodityId}", method = RequestMethod.DELETE)
    @Auth
    public ResMsg<?> deleteCommodity(@PathVariable("commodityId") int commodityId, User user) {
        if (userService.findUserById(user.getUserId()).getRole() < 1)
            throw new AuthException(HttpStatus.UNAUTHORIZED, "没有权限");
        if (commodityService.deleteCommodity(commodityId)) {
            resMsg = new ResMsg<>(1, "DeleteCommodity Success");
        } else {
            resMsg = new ResMsg<>(0, "DeleteCommodityFail");
        }
        return resMsg;
    }


}
