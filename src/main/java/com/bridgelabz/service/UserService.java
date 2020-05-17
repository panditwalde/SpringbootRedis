package com.bridgelabz.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bridgelabz.exception.ResourceNotFound;
import com.bridgelabz.model.User;
import com.bridgelabz.service.remote.UserRepository;


@Service
public class UserService {

  private final UserRepository remoteClientService;

  public UserService(UserRepository remoteClientService) {
    this.remoteClientService = remoteClientService;
  }

  @Cacheable(value = "single-user", key = "#username")
  public User getUser(String username) {
    final User user = remoteClientService.getUser(username);
    if (user == null) throw new ResourceNotFound(User.class, username);
    return user;
  }

  @CachePut(value = "single-user", key = "#user.username")
  public User updateUser(User user) {
    return remoteClientService.updateUser(user);
  }

  @CachePut(value = "single-user", key = "#user.username")
  public User createUser(User user) {
    return remoteClientService.createUser(user);
  }

  @CacheEvict(value = "single-user", key = "#username")
  public User deleteUser(String username) {
    return remoteClientService.delete(username);
  }
}
