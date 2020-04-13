package io.moresushant48.WhatStatus.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.moresushant48.WhatStatus.Store.StoreFile;

@RestController
public class UploadREST {
	
	@PostMapping("/upload")
	public Boolean saveImage(@RequestPart("file") MultipartFile file) {
		
		StoreFile.storeTheFile(file);
		
		return true;
	}
	
}
