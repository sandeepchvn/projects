package com.ty.ams.dao;


import java.util.Optional;

import com.ty.ams.entity.Image;

public interface ImageDao {
	public Image saveImage(Image image);
	
	
	public Optional<Image> getImageById(int id);
  
	
}
