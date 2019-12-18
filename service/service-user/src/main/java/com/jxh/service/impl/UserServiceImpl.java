package com.jxh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jxh.dao.UserMapper;
import com.jxh.entity.MD5Utils;
import com.jxh.pojo.User;
import com.jxh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:User业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * User条件+分页查询
     * @param user 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<User> findPage(User user, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(user);
        //执行搜索
        return new PageInfo<User>(userMapper.selectByExample(example));
    }

    /**
     * User分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<User> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<User>(userMapper.selectAll());
    }

    /**
     * User条件查询
     * @param user
     * @return
     */
    @Override
    public List<User> findList(User user){
        //构建查询条件
        Example example = createExample(user);
        //根据构建的条件查询数据
        return userMapper.selectByExample(example);
    }


    /**
     * User构建查询对象
     * @param user
     * @return
     */
    public Example createExample(User user){
        Example example=new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if(user!=null){
            // 用户名
            if(!StringUtils.isEmpty(user.getUsername())){
                    criteria.andLike("username","%"+user.getUsername()+"%");
            }
            // 密码，加密存储
            if(!StringUtils.isEmpty(user.getPassword())){
                    criteria.andEqualTo("password",user.getPassword());
            }
            // 注册手机号
            if(!StringUtils.isEmpty(user.getPhone())){
                    criteria.andEqualTo("phone",user.getPhone());
            }
            // 注册邮箱
            if(!StringUtils.isEmpty(user.getEmail())){
                    criteria.andEqualTo("email",user.getEmail());
            }
            // 身份证号
            if(!StringUtils.isEmpty(user.getIDcard())){
                    criteria.andEqualTo("IDcard",user.getIDcard());
            }
            // 创建时间
            if(!StringUtils.isEmpty(user.getCreated())){
                    criteria.andEqualTo("created",user.getCreated());
            }
            // 修改时间
            if(!StringUtils.isEmpty(user.getUpdated())){
                    criteria.andEqualTo("updated",user.getUpdated());
            }
            // 会员来源：1:PC，2：H5，3：Android，4：IOS
            if(!StringUtils.isEmpty(user.getSourceType())){
                    criteria.andEqualTo("sourceType",user.getSourceType());
            }
            // 昵称
            if(!StringUtils.isEmpty(user.getNickName())){
                    criteria.andEqualTo("nickName",user.getNickName());
            }
            // 真实姓名
            if(!StringUtils.isEmpty(user.getName())){
                    criteria.andLike("name","%"+user.getName()+"%");
            }
            // 使用状态（1正常 0非正常）
            if(!StringUtils.isEmpty(user.getStatus())){
                    criteria.andEqualTo("status",user.getStatus());
            }
            // 头像地址
            if(!StringUtils.isEmpty(user.getHeadPic())){
                    criteria.andEqualTo("headPic",user.getHeadPic());
            }
            // QQ号码
            if(!StringUtils.isEmpty(user.getQq())){
                    criteria.andEqualTo("qq",user.getQq());
            }
            // 手机是否验证 （0否  1是）
            if(!StringUtils.isEmpty(user.getIsMobileCheck())){
                    criteria.andEqualTo("isMobileCheck",user.getIsMobileCheck());
            }
            // 邮箱是否检测（0否  1是）
            if(!StringUtils.isEmpty(user.getIsEmailCheck())){
                    criteria.andEqualTo("isEmailCheck",user.getIsEmailCheck());
            }
            // 性别，1男，0女
            if(!StringUtils.isEmpty(user.getSex())){
                    criteria.andEqualTo("sex",user.getSex());
            }
            // 会员等级
            if(!StringUtils.isEmpty(user.getUserLevel())){
                    criteria.andEqualTo("userLevel",user.getUserLevel());
            }
            // 积分
            if(!StringUtils.isEmpty(user.getPoints())){
                    criteria.andEqualTo("points",user.getPoints());
            }
            // 出生年月日
            if(!StringUtils.isEmpty(user.getBirthday())){
                    criteria.andEqualTo("birthday",user.getBirthday());
            }
            // 最后登录时间
            if(!StringUtils.isEmpty(user.getLastLoginTime())){
                    criteria.andEqualTo("lastLoginTime",user.getLastLoginTime());
            }
            // 门店负责人
            if(!StringUtils.isEmpty(user.getManager())){
                    criteria.andEqualTo("manager",user.getManager());
            }
        }
        return example;
    }

    /**
     * 根据ID删除(真实删除)
     * @param id:用户名
     */
    @Override
    public void delete(String id){
        if (id!=null) {
            userMapper.deleteByPrimaryKey(id);
        }
    }

    /**
     * 根据id修改User
     * @param user
     */
    @Override
    public void update(User user){
        if (user!=null){
            user.setUpdated(new Date());
            userMapper.updateByPrimaryKeySelective(user);
        }
    }

    /**
     * 增加User
     * @param user
     */
    @Override
    public void add(User user){
        if (user!=null){
            //密码加密
            user.setPassword(MD5Utils.md5(user.getPassword()));
            //创建时间
            user.setCreated(new Date());
            //使用状态(注册默认为正常)
            user.setStatus("1");
            userMapper.insert(user);
        }
    }

    /**
     * 根据ID查询User
     * @param id
     * @return
     */
    @Override
    public User findById(String id){
        if (id!=null){
            return  userMapper.selectByPrimaryKey(id);
        }
        throw new RuntimeException("");
    }

    /**
     * 查询User全部数据
     * @return
     */
    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    /***
     * 根据id删除用户信息(物理删除)
     * @param id:用户名
     */
    @Override
    public void PhysicalDelete(String id) {
        if (id != null) {
            User user = userMapper.selectByPrimaryKey(id);
            if (user != null) {
                user.setStatus("0");
                userMapper.updateByPrimaryKeySelective(user);
            }
        }
    }

    /***
     * 登录用户修改密码
     * @param username:用户名
     * @param repassword:新密码
     */
    @Override
    public void editPassword(String username, String repassword) {
        User user = userMapper.selectByPrimaryKey(username);
        user.setPassword(MD5Utils.md5(repassword));
        userMapper.updateByPrimaryKeySelective(user);
    }
}
