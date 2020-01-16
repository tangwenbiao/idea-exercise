package com.idea.exercise.authselection;

import lombok.Getter;

/**
 * @author: TangFenQi
 * @description: 定义一些权限枚举
 * @date：2020/1/16 15:45
 */
public enum AuthEnum {

  ADD(1 << 5),
  UPDATE(1 << 4),
  SELECT(1 << 3),
  DELETED(1 << 2);

  //权限值
  @Getter
  private long value;

  AuthEnum(long value) {
    this.value = value;
  }

}
