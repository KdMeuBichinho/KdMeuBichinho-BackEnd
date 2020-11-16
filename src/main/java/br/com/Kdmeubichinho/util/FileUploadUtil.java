package br.com.Kdmeubichinho.util;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	public static void saveFile(String uploadDir, String fileName, MultipartFile file) throws IOException {
		Path uploadPath = Paths.get(uploadDir);
		
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		try(InputStream inputStream = file.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);	
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		}
		catch (IOException ioException) {
			throw new IOException("Não foi possível salvar o arquivo: " + fileName);
		}
	}
}
