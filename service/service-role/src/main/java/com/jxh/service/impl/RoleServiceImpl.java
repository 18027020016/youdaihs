package com.jxh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jxh.dao.RoleMapper;
import com.jxh.pojo.Role;
import com.jxh.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Role业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    /**
     * Role条件+分页查询
     * @param role 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Role> findPage(Role role, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(role);
        //执行搜索
        return new PageInfo<Role>(roleMapper.selectByExample(example));
    }

    /**
     * Role分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Role> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Role>(roleMapper.selectAll());
    }

    /**
     * Role条件查询
     * @param role
     * @return
     */
    @Override
    public List<Role> findList(Role role){
        //构建查询条件
        Example example = createExample(role);
        //根据构建的条件查询数据
        return roleMapper.selectByExample(example);
    }


    /**
     * Role构建查询对象
     * @param role
     * @return
     */
    public Example createExample(Role role){
        Example example=new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        if(role!=null){
            // 管理者
            if(!StringUtils.isEmpty(role.getRolename())){
                    criteria.andLike("rolename","%"+role.getRolename()+"%");
            }
            // 密码，加密存储
            if(!StringUtils.isEmpty(role.getPassword())){
                    criteria.andEqualTo("password",role.getPassword());
            }
            // 真实姓名
            if(!StringUtils.isEmpty(role.getName())){
                    criteria.andLike("name","%"+role.getName()+"%");
            }
            // 创建时间
            if(!StringUtils.isEmpty(role.getCreated())){
                    criteria.andEqualTo("created",role.getCreated());
            }
            // 修改时间
            if(!StringUtils.isEmpty(role.getUpdated())){
                    criteria.andEqualTo("updated",role.getUpdated());
            }
            // 	使用状态（1正常 0非正常）注册默认是1
            if(!StringUtils.isEmpty(role.getStatus())){
                    criteria.andEqualTo("status",role.getStatus());
            }
            // 注册手机号
            if(!StringUtils.isEmpty(role.getPhone())){
                    criteria.andEqualTo("phone",role.getPhone());
            }
            // 注册邮箱
            if(!StringUtils.isEmpty(role.getEmail())){
                    criteria.andEqualTo("email",role.getEmail());
            }
            // 手机是否验证 （0否  1是）
            if(!StringUtils.isEmpty(role.getIsMobileCheck())){
                    criteria.andEqualTo("isMobileCheck",role.getIsMobileCheck());
            }
            // 邮箱是否验证 （0否  1是）
            if(!StringUtils.isEmpty(role.getIsEmailCheck())){
                    criteria.andEqualTo("isEmailCheck",role.getIsEmailCheck());
            }
            // 性别，1男，0女
            if(!StringUtils.isEmpty(role.getSex())){
                    criteria.andEqualTo("sex",role.getSex());
            }
            // 最后登录时间
            if(!StringUtils.isEmpty(role.getLastLoginTime())){
                    criteria.andEqualTo("lastLoginTime",role.getLastLoginTime());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(String id){
        roleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Role
     * @param role
     */
    @Override
    public void update(Role role){
        roleMapper.updateByPrimaryKey(role);
    }

    /**
     * 增加Role
     * @param role
     */
    @Override
    public void add(Role role){
        roleMapper.insert(role);
    }

    /**
     * 根据ID查询Role
     * @param id
     * @return
     */
    @Override
    public Role findById(String id){
        return  roleMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Role全部数据
     * @return
     */
    @Override
    public List<Role> findAll() {
        return roleMapper.selectAll();
    }
}
