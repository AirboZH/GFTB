package cn.airbozh.controller;

import cn.airbozh.pojo.Shopping;
import cn.airbozh.pojo.User;
import cn.airbozh.service.ShoppingService;
import cn.airbozh.utility.Auth.Auth;
import cn.airbozh.utility.Auth.AuthException;
import cn.airbozh.utility.ResMsg;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ShoppingController {
    @Resource
    ShoppingService shoppingService;
    ResMsg<?> resMsg;

    @RequestMapping(value = "/shopping", method = RequestMethod.POST)
    @Auth
    public ResMsg<?> createShopping(@RequestBody Shopping shopping, User user) {
        if (user.getUserId() != shopping.getUserId())
            throw new AuthException(HttpStatus.UNAUTHORIZED, "没有权限");
        if (shoppingService.createShopping(shopping))
            resMsg = new ResMsg<>(1, "Add Success");
        else
            resMsg = new ResMsg<>(0, "Add Fail");
        return resMsg;
    }

    @RequestMapping(value = "/shopping/{userId}", method = RequestMethod.GET)
    @Auth
    public ResMsg<?> getShoppingByUserId(@PathVariable int userId, User user) {
        if (user.getUserId() != userId)
            throw new AuthException(HttpStatus.UNAUTHORIZED, "没有权限");
        List<Shopping> list = shoppingService.getShoppingByUserId(userId);
        if (list == null)
            resMsg = new ResMsg<>(0, "Get Fail");
        else
            resMsg = new ResMsg<>(1, "Get Success", list);
        return resMsg;
    }

    @RequestMapping(value = "/shopping/{shoppingId}", method = RequestMethod.DELETE)
    @Auth
    public ResMsg<?> deleteShopping(@PathVariable int shoppingId, User user) {

        if (user.getUserId() != shoppingService.getShoppingByShoppingId(shoppingId).getUserId())
            throw new AuthException(HttpStatus.UNAUTHORIZED, "没有权限");
        if (shoppingService.deleteShopping(shoppingId))
            resMsg = new ResMsg<>(1, "Delete Success");
        else
            resMsg = new ResMsg<>(0, "Delete Fail");
        return resMsg;
    }


}
