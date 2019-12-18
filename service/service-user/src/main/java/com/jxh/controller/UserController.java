package com.jxh.controller;

import com.github.pagehelper.PageInfo;
import com.jxh.entity.*;
import com.jxh.pojo.User;
import com.jxh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;



    /***
     * 登录用户修改密码
     * @param username  用户名
     * @param password : 旧密码
     * @param repassword   新密码
     * @return
     */
    @RequestMapping(value = "/editpassword")
    public Result editPassword(@RequestParam("username") String username,@RequestParam("password") String password, @RequestParam("repassword") String repassword) {
        try {
            //判断用户是否存在,
            User user = userService.findById(username);
            if (user!=null){
                //判断旧密码是否一致
                if (user.getPassword().equalsIgnoreCase(MD5Utils.md5(password))){
                    userService.editPassword(username, repassword);
                    return new Result(true, StatusCode.OK, "密码修改成功！");
                }
                return new Result(false, StatusCode.ERROR, "原密码不一致！");
            }
            return new Result(true, StatusCode.ERROR, "用户名不存在！");
        } catch (Exception e) {

        }
        return new Result(true, StatusCode.OK, "密码修改失败！");
    }

    /**
     * 处理退出登录的请求
     * @param request
     */
    @RequestMapping(value = "/logout")
    private Result logout(HttpServletRequest request){
        //退出登录其实就是销毁session对象
        try {
            request.getSession().invalidate();
            return new Result(true,StatusCode.OK,"退出成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"退出失败");
        }
    }

    /**
     * 处理登录请求的方法
     * @param request
     * @param username:用户名
     * @param password:加密密码
     */
    @RequestMapping(value = "/login")
    private Result login(String username,String password,HttpServletRequest request){
        //查询用户信息
        User user = userService.findById(username);
        //传入密码加密
        String pwd = MD5Utils.md5(password);
        if (user!=null && pwd.equalsIgnoreCase(user.getPassword())){
            //登录成功后将用户信息存入session
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            //设置session过期时间
            request.getSession().setMaxInactiveInterval(1440*60);
            return new Result(true,StatusCode.OK,"登录成功",user);
        }
        return new Result(false,StatusCode.LOGINERROR,"账号或者密码错误");
    }

    /***
     * User分页条件搜索实现
     * @param user
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  User user, @PathVariable  int page, @PathVariable  int size){
        //调用UserService实现分页条件查询User
        try {
            PageInfo<User> pageInfo = userService.findPage(user, page, size);
            return new Result(true, StatusCode.OK,"查询成功",pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR,"查询失败");
        }
    }

    /***
     * User分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用UserService实现分页查询User
        try {
            PageInfo<User> pageInfo = userService.findPage(page, size);
            return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<PageInfo>(false,StatusCode.ERROR,"查询失败");
        }
    }

    /***
     * 多条件搜索
     * @param user
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<User>> findList(@RequestBody(required = false)  User user){
        //调用UserService实现条件查询User
        try {
            List<User> list = userService.findList(user);
            return new Result<List<User>>(true,StatusCode.OK,"查询成功",list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<List<User>>(false,StatusCode.ERROR,"查询失败");
        }
    }

    /***
     * 根据ID删除(真实删除)
     * @param id:用户名
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable String id){
        //调用UserService实现根据主键删除
        try {
            userService.delete(id);
            return new Result(true,StatusCode.OK,"删除成功");
        } catch (Exception e) {
            return new Result(false,StatusCode.ERROR,"删除失败");
        }
    }

    /***
     * 根据id修改User数据
     * @param user:用户信息
     * @param id:用户名
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  User user,@PathVariable String id){
        try {
            //设置主键值
            user.setUsername(id);
            //调用UserService实现修改User
            userService.update(user);
            return new Result(true,StatusCode.OK,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"修改失败");
        }
    }

    /***
     * 新增User数据
     * @param user
     * @return
     */
    @PostMapping
    public Result add(@RequestBody  User user){
        //调用UserService实现添加User
        try {
            userService.add(user);
            return new Result(true,StatusCode.OK,"添加成功");
        } catch (Exception e) {
            return new Result(false,StatusCode.ERROR,"添加失败");
        }
    }

    /***
     * 根据id删除用户信息(物理删除)
     * @param id:用户名
     * @return
     */
    @PutMapping(value="/physical/{id}")
    public Result PhysicalDelete(@PathVariable String id){
        //调用UserService实现修改User
        try {
            userService.PhysicalDelete(id);
            return new Result(true,StatusCode.OK,"修改成功");
        } catch (Exception e) {
            return new Result(false,StatusCode.ERROR,"修改失败");
        }
    }


    /***
     * 根据ID查询User数据
     * @param id:用户名
     * @return
     */
    @GetMapping("/{id}")
    public Result<User> findById(@PathVariable String id){
            //调用UserService实现根据主键查询User
        try {
           User user = userService.findById(id);
            return new Result<User>(true,StatusCode.OK,"查询成功",user);
        } catch (Exception e) {
            return new Result<User>(false,StatusCode.ERROR,"查询失败");
        }

    }

    /***
     * 查询User全部数据
     * @return
     */
    @GetMapping
    public Result<List<User>> findAll(){
        //调用UserService实现查询所有User
        try {
            List<User>  list = userService.findAll();
            return new Result<List<User>>(true, StatusCode.OK,"查询成功",list) ;
        } catch (Exception e) {
            return new Result<List<User>>(false, StatusCode.ERROR,"查询失败") ;
        }
    }
}
