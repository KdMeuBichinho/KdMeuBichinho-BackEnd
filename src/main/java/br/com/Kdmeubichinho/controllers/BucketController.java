package br.com.Kdmeubichinho.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.Kdmeubichinho.services.AmazonClient;


@RestController
@RequestMapping("/storage")
@CrossOrigin
public class BucketController {

	    private AmazonClient amazonClient;

	    @Autowired
	    BucketController(AmazonClient amazonClient) {
	        this.amazonClient = amazonClient;
	    }

	    @PostMapping("/upload")
	    @ResponseStatus(HttpStatus.CREATED)
	    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
	        return this.amazonClient.uploadFile(file);
	    }

	    @DeleteMapping("/delete")
	    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
	        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
	    }
	
	/*@CrossOrigin
	@PostMapping("/upload")
	public String saveImg(@RequestParam("image") MultipartFile multipartFile) {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		Date date = new Date();
		String filePrefix = date.getTime() + "-";
		String uploadDir = "files";
		fileName = filePrefix + fileName;
		try {
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("O arquivo não foi salvo");
			return "Error";
		}
		System.out.println("O arquivo foi salvo!");
		return "http://localhost:8080/" + uploadDir + "/" + fileName;
	}*/
	
}
