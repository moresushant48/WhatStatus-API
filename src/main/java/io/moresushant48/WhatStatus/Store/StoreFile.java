package io.moresushant48.WhatStatus.Store;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class StoreFile {

	public static File path = new File("\\storedfiles");
	public static Path rootloc = Paths.get(path.toURI());
	
	public static void storeTheFile(MultipartFile file) {
		
		System.out.println("Inside StoreFile.");
		try(InputStream inputStream = file.getInputStream()) {
			Files.copy(inputStream, rootloc.resolve(StringUtils.cleanPath(file.getOriginalFilename())),
                    StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Path load(String filename) {
        return rootloc.resolve(filename);
    }
	
	public Resource loadAsResource(String filename) {
		Resource resource = null;
		try {
            Path file = load(filename);
            resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                
            	System.out.println("Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
        	System.out.println("Could not read file: " + e);
        }
        return resource;
    }
	
	public List<Path> loadAll() {
        Stream<Path> stream = null;
		try {
            stream = Files.walk(rootloc, 1)
                .filter(path -> !path.equals(rootloc))
                .map(rootloc::relativize);
        }
        catch (IOException e) {
            System.out.println(e);
        }
		
		List<Path> arr = stream.collect(Collectors.toList());
		
		return arr;        
    }

}
