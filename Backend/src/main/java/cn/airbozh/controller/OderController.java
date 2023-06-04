package cn.airbozh.controller;

import cn.airbozh.pojo.Oder;
import cn.airbozh.pojo.Shopping;
import cn.airbozh.pojo.User;
import cn.airbozh.service.OderService;
import cn.airbozh.service.ShoppingService;
import cn.airbozh.utility.Auth.Auth;
import cn.airbozh.utility.Auth.AuthException;
import cn.airbozh.utility.ResMsg;
import com.mysql.cj.log.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class OderController {

    @Resource
    OderService oderService;
    ResMsg<?> resMsg;

    @GetMapping("/oder")
    @Auth
    public ResMsg<?> getOderByCustomerId(User user) {
        List<Oder> list = oderService.getOderByCustomerId();
        if(list != null)
            resMsg = new ResMsg<>(1, "Get Success", list);
        else
            resMsg = new ResMsg<>(0, "Add Fail");
        return resMsg;
    }

    @RequestMapping(value = "/oder", method = RequestMethod.POST)
    @Auth
    public ResMsg<?> createShopping(@RequestBody Oder oder, User user) {
        oder.setCustomerId(user.getUserId());
        if (oderService.createOder(oder))
            resMsg = new ResMsg<>(1, "Add Success");
        else
            resMsg = new ResMsg<>(0, "Add Fail");
        return resMsg;
    }

    @PostMapping("/oder/{oderId}/{status}")
    @Auth
    public ResMsg<?> updateOderStatus(@PathVariable("oderId") int oderId, @PathVariable("status") int status) {
        if (oderService.updateOderStatus(oderId, status))
            resMsg = new ResMsg<>(1, "Update Success");
        else
            resMsg = new ResMsg<>(0, "Update Fail");
        return resMsg;
    }

}
