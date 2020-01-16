package com.idea.exercise.authselection;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: TangFenQi
 * @description:
 * @date：2020/1/16 17:27
 */

@SpringBootTest(classes = {AuthTestApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class AuthBaseOperation {

  @Test
  public void add() {
    AuthService authService = new AuthService();
    long userId = 1;
    AuthEnum authEnum = AuthEnum.ADD;
    authService.add(userId, authEnum);

    Assert.assertTrue(authService.contain(userId, authEnum));
    List<AuthEnum> authEnums = authService.find(userId);
    Assert.assertEquals(1, authEnums.size());
    Assert.assertEquals(authEnums.get(0), authEnum);

    AuthEnum authEnum1 = AuthEnum.UPDATE;
    authService.add(userId, authEnum1);
    authEnums = authService.find(userId);
    Assert.assertEquals(2, authEnums.size());
    Assert.assertTrue(authEnums.contains(authEnum));
    Assert.assertTrue(authEnums.contains(authEnum1));
  }

  @Test
  public void addList() {
    AuthService authService = new AuthService();
    long userId = 1;
    List<AuthEnum> authEnumList = new ArrayList<>();
    authEnumList.add(AuthEnum.ADD);
    authEnumList.add(AuthEnum.UPDATE);
    authEnumList.add(AuthEnum.DELETED);
    authService.add(userId, authEnumList);

    List<AuthEnum> authEnums = authService.find(userId);

    Assert.assertEquals(authEnums.size(), authEnumList.size());
    for (AuthEnum authEnum : authEnumList) {
      Assert.assertTrue(authEnums.contains(authEnum));
    }
  }

  @Test
  public void deleted() {
    AuthService authService = new AuthService();
    long userId = 1;
    List<AuthEnum> authEnumList = new ArrayList<>();
    authEnumList.add(AuthEnum.ADD);
    authEnumList.add(AuthEnum.UPDATE);
    authEnumList.add(AuthEnum.DELETED);
    authService.add(userId, authEnumList);

    authService.deleted(userId, AuthEnum.ADD);
    authEnumList.remove(AuthEnum.ADD);
    List<AuthEnum> authEnums = authService.find(userId);
    Assert.assertEquals(authEnumList.size(), authEnums.size());
    for (AuthEnum authEnum : authEnumList) {
      Assert.assertTrue(authEnums.contains(authEnum));
    }
  }

  @Test
  public void deleteList() {
    AuthService authService = new AuthService();
    long userId = 1;
    List<AuthEnum> authEnumList = new ArrayList<>();
    authEnumList.add(AuthEnum.ADD);
    authEnumList.add(AuthEnum.UPDATE);
    authEnumList.add(AuthEnum.DELETED);
    int length = authEnumList.size();
    authService.add(userId, authEnumList);

    List<AuthEnum> deletedAuth = new ArrayList<>();
    deletedAuth.add(AuthEnum.ADD);
    deletedAuth.add(AuthEnum.UPDATE);
    authService.deleted(userId, deletedAuth);
    authEnumList.remove(AuthEnum.ADD);
    authEnumList.remove(AuthEnum.UPDATE);
    List<AuthEnum> authEnums = authService.find(userId);
    Assert.assertEquals(length - deletedAuth.size(), authEnums.size());
    for (AuthEnum authEnum : authEnumList) {
      Assert.assertTrue(authEnums.contains(authEnum));
    }
  }

  @Test
  public void clear() {
    AuthService authService = new AuthService();
    long userId = 1;
    List<AuthEnum> authEnumList = new ArrayList<>();
    authEnumList.add(AuthEnum.ADD);
    authEnumList.add(AuthEnum.UPDATE);
    authEnumList.add(AuthEnum.DELETED);
    authService.add(userId, authEnumList);

    authService.clear(userId);
    List<AuthEnum> authEnums = authService.find(userId);
    Assert.assertEquals(0, authEnums.size());
  }


}


