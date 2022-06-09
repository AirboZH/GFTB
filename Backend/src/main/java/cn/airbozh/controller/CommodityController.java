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
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@RestController
public class CommodityController {
    @Resource
    CommodityService commodityService;
    @Resource
    UserService userService;
    ResMsg<?> resMsg;

    @RequestMapping(value = "/commodity", method = RequestMethod.POST)
    @Auth
    public ResMsg<?> createCommodity(@RequestBody Commodity commodity, User user) {
        if (userService.findUserById(user.getUserId()).getRole() < 1)
            throw new AuthException(HttpStatus.UNAUTHORIZED, "没有权限");
        if (commodityService.createCommodity(commodity))
            resMsg = new ResMsg<>(1, "Create Success");
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

    @RequestMapping(value = "/commodity/{commodityId}", method = RequestMethod.GET)
    public ResMsg<?> getCommodityById(@PathVariable("commodityId") int commodityId) {
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
    public ResMsg<?> updateCommodityById(@PathVariable("commodityId") int commodityId, @RequestBody Commodity commodity, User user) {
        if (userService.findUserById(user.getUserId()).getRole() < 1)
            throw new AuthException(HttpStatus.UNAUTHORIZED, "没有权限");
        Commodity dbCommodity = commodityService.updateCommodity(commodityId, commodity);
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
            resMsg = new ResMsg<>(0, "DeleteCommodity Fail");
        }
        return resMsg;
    }

    @RequestMapping(value = "/commodity/upload", method = RequestMethod.POST)
    public ResMsg<?> upload(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest request) {
        try {
            // 判断文件是否成功上传
            if (uploadFile != null) {
                // 指定上传图片的保存路径
//                String path = request.getServletContext().getRealPath("/image/");
                String path = "C:\\Users\\airbo\\Documents\\Code\\GFTB\\backend-frontend\\public\\image\\";
                // 获取上传的文件名全称
                String fileName = uploadFile.getOriginalFilename();
                // 获取上传文件的后缀名
                String suffix = fileName.substring(fileName.lastIndexOf("."));
                // 给文件定义一个新的名称,杜绝文件重名覆盖现象
                String newFileName = UUID.randomUUID().toString() + suffix;

                // 创建File对象,注意这里不是创建一个目录或一个文件,你可以理解为是 获取指定目录中文件的管理权限(增改删除判断等 . . .)
                File tempFile = new File(path);
                // 判断File对象对应的目录是否存在
                if (!tempFile.exists()) {
                    // 创建以此抽象路径名命名的目录,注意mkdir()只能创建一级目录,所以它的父级目录必须存在
                    tempFile.mkdir();
                }
                // 在指定路径中创建一个文件(该文件是空的)
                uploadFile.transferTo(new File(path + newFileName));
                System.out.println(path + newFileName);
                resMsg = new ResMsg<>(1, "Upload Success",newFileName);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resMsg;
    }

}
