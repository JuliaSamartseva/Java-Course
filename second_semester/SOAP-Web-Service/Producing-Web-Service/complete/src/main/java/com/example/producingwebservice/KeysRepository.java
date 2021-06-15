package com.example.producingwebservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

@Component
public class KeysRepository {
  private static final Map<String, String> keys = new HashMap<>();

  @PostConstruct
  public void initData() {
    keys.put("Julia", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgFGVfrY4jQSoZQWWygZ83roKXWD4YeT2x2p41dGkPixe73rT2IW04glagN2vgoZoHuOPqa5and6kAmK2ujmCHu6D1auJhE2tXP+yLkpSiYMQucDKmCsWMnW9XlC5K7OSL77TXXcfvTvyZcjObEz6LIBRzs6+FqpFbUO9SJEfh6wIDAQAB");
  }

  public String findKey(String name) {
    Assert.notNull(name, "The name must not be null");
    return keys.get(name);
  }
}
