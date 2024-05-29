package com.ty.ams.serviceimp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ty.ams.dao.ImageDao;
import com.ty.ams.entity.Image;
import com.ty.ams.exceptionclasses.user.ImageNotFound;
import com.ty.ams.responsestructure.ResponseStructure;
import com.ty.ams.service.ImageService;

@Component
public class ImageServiceImp implements ImageService {
	@Autowired
	ImageDao imageDao;

	@Override
	public ResponseEntity<ResponseStructure<Image>> saveImage(Image image) {
		ResponseStructure<Image> responseStructure = new ResponseStructure<Image>();
		responseStructure.setBody(imageDao.saveImage(image));
		responseStructure.setMessage("image saved succefully");
		responseStructure.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<Image>>(responseStructure, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<Image>> getImageById(int id) {
		Optional<Image> image = imageDao.getImageById(id);
		if (image.isPresent()) {
			ResponseStructure<Image> responseStructure = new ResponseStructure<Image>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Found");
			responseStructure.setBody(image.get());
			return new ResponseEntity<ResponseStructure<Image>>(responseStructure, HttpStatus.OK);
		}
		throw new ImageNotFound();
	}

}
