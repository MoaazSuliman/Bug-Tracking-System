package com.moaaz.bug.Testing;//package com.moaaz.bug.Testing;
//
//import java.sql.Blob;
//
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.moaaz.bug.model.types.Gender;
//
//import jakarta.annotation.Generated;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//
//@Entity
//public class Test {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//	private MultipartFile source;
//
//	public Test() {
//
//	}
//
//	public Test(int id, MultipartFile source) {
//		super();
//		this.id = id;
//		this.source = source;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public MultipartFile getSource() {
//		return source;
//	}
//
//	public void setSource(MultipartFile source) {
//		this.source = source;
//	}
//}
