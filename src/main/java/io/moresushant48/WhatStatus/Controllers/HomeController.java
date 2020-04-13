package io.moresushant48.WhatStatus.Controllers;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.moresushant48.WhatStatus.Store.StoreFile;

@Controller
public class HomeController {

	@GetMapping("/")
	public ModelAndView status() {
		return new ModelAndView("index");
	}
	
	@GetMapping("/viewall")
	public ModelAndView viewAll() {
		ModelAndView mv= new ModelAndView("viewall");
		mv.addObject("names", new StoreFile().loadAll());
		return mv;
	}
	
	@GetMapping("/uploads/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws IOException {

        Resource file = new StoreFile().loadAsResource(filename);
        return ResponseEntity.ok()
        		.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + file.getFilename() + "\"")
        		.contentType(MediaType.APPLICATION_OCTET_STREAM)
        		.contentLength(file.contentLength())
        		.body(file);
    }
	
	@GetMapping("/deleteall")
	public ModelAndView deleteAllFiles() {
		new StoreFile().deleteAll();
		return new ModelAndView("redirect:/viewall");
	}
}
