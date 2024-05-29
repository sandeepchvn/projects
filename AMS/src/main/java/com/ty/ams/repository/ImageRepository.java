package com.ty.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.ams.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer>{
  
}
