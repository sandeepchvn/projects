package com.ty.ams.daoimp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.ams.dao.ImageDao;
import com.ty.ams.entity.Image;
import com.ty.ams.repository.ImageRepository;


@Repository
public class ImageDaoImp implements ImageDao{
	@Autowired
	ImageRepository imageRepository;

	@Override
	public Image saveImage(Image image) {
		
		return imageRepository.save(image);
				
	}

	@Override
	public Optional<Image> getImageById(int id) {
		
		return imageRepository.findById(id);
	}
	

	

	
	  
}
