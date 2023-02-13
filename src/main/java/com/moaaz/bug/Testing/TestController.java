package com.moaaz.bug.Testing;

import java.io.IOException;
import java.sql.Blob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class TestController {

//	@GetMapping("/uploadImage")
//	public void uploadImage(@RequestParam MultipartFile file) throws IOException {
//		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//		System.out.println(fileName);
//		System.out.println(file.getName());
//		System.out.println(file.getContentType());
//		System.out.println(file.getOriginalFilename());
//		System.out.println(file.getResource());
//		String string = String.valueOf(file.getBytes());
//		System.out.println("String == " + string);
//		Test test = new Test(0,  file);
//		repo.save(test);
//	}

//	@GetMapping("/login")
//	public String login() {
//		return "Login";
//	}
}
