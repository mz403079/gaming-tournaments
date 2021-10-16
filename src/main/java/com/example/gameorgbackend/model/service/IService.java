package com.example.gameorgbackend.model.service;

import java.util.Collection;

public interface IService<T> {
  T get(Long id);

  Collection<T> getAll();

  T create(T t);

  T update(T t);

  void delete(T t);
}
