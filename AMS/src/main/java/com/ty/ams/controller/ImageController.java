package com.ty.ams.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ty.ams.entity.Image;
import com.ty.ams.responsestructure.ResponseStructure;
import com.ty.ams.service.ImageService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ImageController {

	@Autowired
	ImageService imageService;

	//need to take useId also
	@PostMapping("/saveImage")
	public ResponseEntity<ResponseStructure<Image>> uploadImage(@RequestParam MultipartFile file) throws IOException {
		Image image = new Image();
		image.setImage(file.getBytes());
//		Image image = imageRepository.save(m);
		return imageService.saveImage(image);
	}

	@GetMapping(value = "/getimage/{id}")
	public ResponseEntity<ResponseStructure<Image>> getimageInfoById(@PathVariable int id) {
		ResponseEntity<ResponseStructure<Image>> image = imageService.getImageById(id);

		return image;

	}
}
