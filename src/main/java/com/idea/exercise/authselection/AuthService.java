package com.idea.exercise.authselection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * @author: TangFenQi
 * @description: 权限服务
 * @date：2020/1/16 15:46
 */
@Slf4j
@Component
public class AuthService implements IAuthService {

  private Map<Long, Long> AUTH_MAP = new ConcurrentHashMap<Long, Long>();

  /**
   * 添加用户权限
   *
   * @param id 用户编号
   * @param authEnum 权限类型
   */
  public void add(Long id, AuthEnum authEnum) {
    Assert.notNull(id, "id is empty!");
    Assert.notNull(authEnum, "auth is empty!");
    add(id, authEnum.getValue());
  }

  /**
   * 批量添加用户权限
   *
   * @param id 用户编号
   * @param authEnums 权限集合
   */
  public void add(Long id, List<AuthEnum> authEnums) {
    Assert.notNull(id, "id is empty!");
    Assert.notEmpty(authEnums, "auth list is empty!");
    add(id, calculateValue(id, authEnums));
  }

  /**
   * 添加所有权限
   *
   * @param id 用户编号
   */
  public void addAll(Long id) {
    Assert.notNull(id, "id is empty!");
    if (!AUTH_MAP.containsKey(id)) {
      log.warn("not found user in auth map by id ! userId:{}", id);
      return;
    }
    add(id, calculateValue(id, CollectionUtils.arrayToList(AuthEnum.values())));
  }

  /**
   * 删除指定用户的权限
   *
   * @param id 用户编号
   * @param authEnum 权限类型
   */
  public void deleted(Long id, AuthEnum authEnum) {
    Assert.notNull(id, "id is empty!");
    Assert.notNull(authEnum, "auth enum is empty!");
    if (!AUTH_MAP.containsKey(id)) {
      log.warn("not found user in auth map by id ! userId:{}", id);
      return;
    }
    deleted(id, authEnum.getValue());
  }

  /**
   * 删除指定用户的权限
   *
   * @param id 用户编号
   * @param authEnumList 权限类型集合
   */
  public void deleted(Long id, List<AuthEnum> authEnumList) {
    Assert.notNull(id, "id is empty!");
    Assert.notEmpty(authEnumList, "auth list is emtpy!");
    if (!AUTH_MAP.containsKey(id)) {
      log.warn("not found user in auth map by id ! userId:{}", id);
      return;
    }
    deleted(id, calculateValue(id, authEnumList));
  }

  /**
   * 清除指定用户的所有权限
   */
  public void clear(Long id) {
    Assert.notNull(id, "id is empty!");
    if (!AUTH_MAP.containsKey(id)) {
      log.warn("not found user in auth map by id ! userId:{}", id);
      return;
    }
    deleted(id, calculateValue(id, CollectionUtils.arrayToList(AuthEnum.values())));
  }

  /**
   * 查询用户权限信息
   *
   * @param id 用户编号
   * @return 权限信息集合
   */
  public List<AuthEnum> find(Long id) {
    Assert.notNull(id, "id is empty!");
    if (!AUTH_MAP.containsKey(id)) {
      log.warn("not found user in auth map by id ! userId:{}", id);
      return new ArrayList<>();
    }
    return findAuth(id);
  }

  private List<AuthEnum> findAuth(long id) {
    Long value = AUTH_MAP.get(id);
    List<AuthEnum> authEnums = new ArrayList<>();
    for (AuthEnum authEnum : AuthEnum.values()) {
      if ((authEnum.getValue() & value) == authEnum.getValue()) {
        authEnums.add(authEnum);
      }
    }
    return authEnums;
  }

  /**
   * 判断用户是否具有某项权限
   *
   * @param id 用户编号
   * @param authEnum 权限信息
   * @return true具有权限，false不具有权限
   */
  public boolean contain(Long id, AuthEnum authEnum) {
    Assert.notNull(id, "id is empty!");
    Assert.notNull(authEnum, "auth enum is empty!");
    if (!AUTH_MAP.containsKey(id)) {
      log.warn("not found user in auth map by id ! userId:{}", id);
      return false;
    }
    return contain(id, authEnum.getValue());
  }

  /**
   * 判断用户是否具有某一组权限
   *
   * @param id 用户编号
   * @param authEnums 权限集合
   * @return true具有权限，false不具有权限
   */
  public boolean contain(Long id, List<AuthEnum> authEnums) {
    Assert.notNull(id, "id is empty!");
    Assert.notEmpty(authEnums, "auth enum is empty!");
    if (!AUTH_MAP.containsKey(id)) {
      log.warn("not found user in auth map by id ! userId:{}", id);
      return false;
    }
    return contain(id, calculateValue(id, authEnums));
  }

  private boolean contain(long id, long value) {
    long userValue = AUTH_MAP.get(id);
    long compareValue = userValue & value;
    return compareValue == value;
  }

  private synchronized void add(long id, long addValue) {
    if (!AUTH_MAP.containsKey(id)) {
      AUTH_MAP.put(id, addValue);
    } else {
      long userValue = AUTH_MAP.get(id);
      userValue = userValue | addValue;
      AUTH_MAP.put(id, userValue);
    }
  }

  private long calculateValue(long id, List<AuthEnum> authEnumList) {
    long value = 0;
    for (AuthEnum authEnum : authEnumList) {
      value += authEnum.getValue();
    }
    return value;
  }

  private synchronized void deleted(long id, long removeValue) {
    long userValue = AUTH_MAP.get(id);
    userValue = (removeValue & userValue) ^ userValue;
    AUTH_MAP.put(id, userValue);
  }

}
