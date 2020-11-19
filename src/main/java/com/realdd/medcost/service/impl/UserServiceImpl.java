package com.realdd.medcost.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.common.exception.ApiException;
import com.realdd.medcost.common.utils.JwtTokenUtil;
import com.realdd.medcost.entity.User;
import com.realdd.medcost.entity.UserRoleRelation;
import com.realdd.medcost.mapper.UserMapper;
import com.realdd.medcost.service.UserRoleRelationService;
import com.realdd.medcost.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author cute_duanduan
 * @since 2020-10-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private static final Logger LOGGER =LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleRelationService userRoleRelationService;

    @Override
    public User getUserByUsername(String username) {
        User user;
        List<User> userList=getUserListByUsername(username);
        if(userList!=null && userList.size()>0){
            user=userList.get(0);
            return user;
        }
        return null;
    }

    @Override
    public User register(User userParam) {
        User user=new User();
        BeanUtils.copyProperties(userParam,user);
      //  user.setCreateTime(new Date());
        //user.setUpdateTime(user.getCreateTime());
        //查询是否有相同用户名的用户
        List<User> userList=getUserListByUsername(user.getUsername());
        if(userList.size()>0){
            return null;
        }
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userMapper.insert(user);
        return user;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public Page<User> list(String keyword, Integer pageSize, Integer pageNum) {
//        PageHelper.startPage(pageNum, pageSize);
////        UserExample example = new UserExample();
////        UserExample.Criteria criteria = example.createCriteria();
////        if (!StringUtils.isEmpty(keyword)) {
////            criteria.andUsernameLike("%" + keyword + "%");
////            example.or(example.createCriteria().andNickNameLike("%" + keyword + "%"));
////        }
////        return userMapper.selectByExample(example);
        Page<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<User> lambda = wrapper.lambda();
        if(StrUtil.isNotEmpty(keyword)){
            lambda.like(User::getUsername,keyword);
            lambda.or().like(User::getRealname,keyword);
        }
        return (Page<User>) page(page,wrapper);
    }

    private List<User> getUserListByUsername(String username){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.lambda().eq(User::getUsername,username);
        return list(wrapper);
    }

    @Override
    public boolean update(Long id, User user) {
        user.setId(id);
        User rawUser = getById(id);
        if(rawUser.getPassword().equals(user.getPassword())){
            //与原加密密码相同的不需要修改
            user.setPassword(null);
        }else{
            //与原加密密码不同的需要加密修改
            if(StrUtil.isEmpty(user.getPassword())){
                user.setPassword(null);
            }else{
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        }
        boolean success = updateById(user);
        return success;
    }

    @Override
    public User insertUserIdTypeAuto(User user){
        LOGGER.info("inserUserId执行");
        saveOrUpdate(user);
        return user;
    }

    @Override
    public boolean insertBatchUserDuplicateEncodePwd(List<User> userList) {
        for(User user:userList){
            if(!StrUtil.isEmpty(user.getPassword())){
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        }
        return userMapper.insertBatchDuplicate(userList);
    }

    //需要返回值怎么办??try catch
    @Override
    public boolean insertUserByExcel(MultipartFile file) {
        ImportParams importParams = new ImportParams();
        // 数据处理
        importParams.setHeadRows(1);
        importParams.setTitleRows(1);

        // 需要验证
        importParams.setNeedVerfiy(true);
        boolean success=false;
        try {
            ExcelImportResult<User> result = ExcelImportUtil.importExcelMore(file.getInputStream(), User.class,
                    importParams);

            List<User> successList = result.getList();
            for (User demoExcel : successList) {
                System.out.println(demoExcel);
            }
            success=insertBatchUserDuplicateEncodePwd(successList);
        }  catch (Exception e) {
            throw new ApiException(e.getMessage());
        }finally {
            return success;
        }
    }

    @Override
    public int updateRole(Long userId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        QueryWrapper<UserRoleRelation> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserRoleRelation::getUserId,userId);
        userRoleRelationService.remove(wrapper);
        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<UserRoleRelation> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                UserRoleRelation roleRelation = new UserRoleRelation();
                roleRelation.setUserId(userId);
                roleRelation.setRoleId(roleId);
                list.add(roleRelation);
            }
            userRoleRelationService.saveBatch(list);
        }
        return count;
    }

//    @Override
//    public boolean saveOrUpdate(User entity, Wrapper<User> updateWrapper) {
//        LOGGER.info("saveOrUpdate执行");
//        super.saveOrUpdate(entity);
//        return false;
//    }
}
