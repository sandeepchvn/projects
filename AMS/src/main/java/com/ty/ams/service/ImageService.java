package com.ty.ams.service;

import org.springframework.http.ResponseEntity;

import com.ty.ams.entity.Image;
import com.ty.ams.responsestructure.ResponseStructure;

public interface ImageService {

	public ResponseEntity<ResponseStructure<Image>> saveImage(Image image);

	public ResponseEntity<ResponseStructure<Image>> getImageById(int id);
}
