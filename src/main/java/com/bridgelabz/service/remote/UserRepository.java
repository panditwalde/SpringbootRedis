package com.bridgelabz.service.remote;

import com.bridgelabz.model.User;

public interface UserRepository {

  User getUser(String username) throws RepositoryException;

  User createUser(User user) throws RepositoryException;

  User updateUser(User user) throws RepositoryException;

  User delete(String username) throws RepositoryException;
}
