package com.realdd.medcost.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author cute_duanduan
 * @since 2020-10-23
 */
public interface UserService extends IService<User> {
    /**
     * 根据用户id_num获取对应用户
     */
    public User getUserByUsername(String username);

    /**
     * 注册功能
     */
    User register(User userParam);

    /**
     * 登录功能
     * @param username 学工号
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 根据用户名或昵称分页查询用户
     */
    Page<User> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     */
    boolean update(Long id, User user);

    /**
     * 修改用户角色关系
     */
    @Transactional
    int updateRole(Long adminId, List<Long> roleIds);


    public User insertUserIdTypeAuto(User user);

    boolean insertBatchUserDuplicateEncodePwd(List<User> userList);

    boolean insertUserByExcel(MultipartFile file);

    boolean insertUserByExcel(InputStream fileStream);


}
