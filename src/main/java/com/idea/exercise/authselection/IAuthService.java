package com.idea.exercise.authselection;

import java.util.List;

/**
 * @author: TangFenQi
 * @description: 权限服务定义
 * @date：2020/1/16 15:48
 */
public interface IAuthService {

  /**
   * 添加用户权限
   *
   * @param id 用户编号
   * @param authEnum 权限类型
   */
  void add(Long id, AuthEnum authEnum);

  /**
   * 批量添加用户权限
   *
   * @param id 用户编号
   * @param authEnums 权限集合
   */
  void add(Long id, List<AuthEnum> authEnums);

  /**
   * 添加所有权限
   *
   * @param id 用户编号
   */
  void addAll(Long id);

  /**
   * 删除指定用户的权限
   *
   * @param id 用户编号
   * @param authEnum 权限类型
   */
  void deleted(Long id, AuthEnum authEnum);

  /**
   * 删除指定用户的权限
   *
   * @param id 用户编号
   * @param authEnumList 权限类型集合
   */
  void deleted(Long id, List<AuthEnum> authEnumList);

  /**
   * 清除指定用户的所有权限
   */
  void clear(Long id);

  /**
   * 查询用户权限信息
   *
   * @param id 用户编号
   * @return 权限信息集合
   */
  List<AuthEnum> find(Long id);

  /**
   * 判断用户是否具有某项权限
   *
   * @param id 用户编号
   * @param authEnum 权限信息
   * @return true具有权限，false不具有权限
   */
  boolean contain(Long id, AuthEnum authEnum);

  /**
   * 判断用户是否具有某一组权限
   *
   * @param id 用户编号
   * @param authEnums 权限集合
   * @return true具有权限，false不具有权限
   */
  boolean contain(Long id, List<AuthEnum> authEnums);

}
