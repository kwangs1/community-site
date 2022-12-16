package com.spring.community;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.community.common.AttachFileDTO;

import net.coobird.thumbnailator.Thumbnailator;


@Controller
public class UploadController {
	private static Logger log = Logger.getLogger(UploadController.class.getName());
	
	//��/��/�� ���� ����
	private String getFolder() {
		//SimpleDateFormat Ŭ���� ��ü�� ����(yyyy-MM-dd)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//sdf ��ü�� ���� date��ü ����
		Date date = new Date();
		//str ���� ���� �� ������ �������� ��ȯ
		String str = sdf.format(date);
		// str�� �� ���ϵ��� ������ '-' ���
		return str.replace("-", File.separator);
	}
	
	//Ư���� ������ �̹��� Ÿ�������� �˻��ϴ� �޼���
	private boolean checkImageType(File file) {

		try {
			//Files.probeContentType �޼���� ���� ������ ������ �ƴ϶� ������ Ȯ���ڸ� �̿��Ͽ� MimeType�� �Ǵ���
			//Ȯ���ڰ� ���� ������ null ��ȯ , ���� ������ ���� ���� �ʾƵ� MimeType�� ��ȯ
			//file.toPath�� ���� �־ ������ ��η� ��ȯ �մϴ�.
			String contentType = Files.probeContentType(file.toPath());
			//string ���� �Լ� startsWith �Լ��� ����Ͽ� ������ ��ο� �ִ� ���� image���� Ȯ��
			//������ true��ȯ ,�ƴ� �� false��ȯ
			return contentType.startsWith("image");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	
	//���ε�Ǵ� ������ �����ϴ� �޼���
	//MediaType.APPLICATION_JSON_UTF8_VALUE �� �������� �Ű����� ���� utf-8 Ư�����ڸ� �ùٸ��� �ؼ��ϰ� �ϱ� ���ؼ�
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	//list<attachfileDTO>�� ���䵥���ͷ� ����
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		log.info("uploadAjaxAction Post..........");
		
		//�̹����� ���� ������ ���� arraylist��ü ����
		List<AttachFileDTO> list = new ArrayList<>();
		//����� ��� ����
		String uploadFolder = "C:\\upload";
		//getfolder�޼��带 uploadFolderPath�� ����
		String uploadFolderPath = getFolder();
		// ���� ����(��ο� ��,��,�� �� �����ϰ� ��) --------
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		
		//.exists�Լ��� ��� �Ͽ� ���� ��ο� ������ �������� �ʴ´ٸ�
		//.mkdirs �Լ��� ��� �Ͽ� ���� ����
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		//uploadFile �迭�� multipartFile������ ��Ƽ� ���
		for (MultipartFile multipartFile : uploadFile) {
			log.info("-----------------------");
			log.info("Upload File Name:" + multipartFile.getOriginalFilename());
			log.info("Upload File size:" + multipartFile.getSize());
			//�̹��� ����DTO ��ü ����(new�����ڸ� ����)
			AttachFileDTO attachDTO = new AttachFileDTO();
			//getOriginalFilename �޼��带 ����ؼ� ���� �̸��� �����ͼ� uploadFileName �� ����ִ´�.
			String uploadFileName = multipartFile.getOriginalFilename();

			// ���ε�� ���� �̸��� ã������ substring�Լ��� ����ؼ� ���ϴ� ��ġ���� �ڸ������� ����
			//lastIndexOf�� ����ؼ� �ڿ��� ���� ã�� ����, \\ ���Ŀ� ���ڿ��� uploadFileName�� ����ִ´�.
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			log.info("only file name: " + uploadFileName);
			//uploadFileName���ڿ��� attachDTO.setFileName�� ���ý�����
			attachDTO.setFileName(uploadFileName);
			
			//uuidŬ����(������ �ĺ��ڸ� ������ �� �־ ���)
			UUID uuid = UUID.randomUUID();
			//�����̸� �� uuid�� �����ϱ����� ���̿� '_'�� �߰�
			uploadFileName = uuid.toString() + "_" + uploadFileName;

			try {
				//������ ��� �� �̸��� saveFile��ü�� ����
				File saveFile = new File(uploadPath, uploadFileName);
				//������ �����ؼ� savefile�� ����
				multipartFile.transferTo(saveFile);
				
				//���� �� ���� uuid,��� �� ����(attachDTO��)
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);

				//���� check image type�޼��忡 saveFile�� ���� �ִٸ�?
				if (checkImageType(saveFile)) {
					//�̹��� ���ð��� true
					attachDTO.setImage(true);
					//������̶�� ��ü�� fileoutputstream�� ���� ���( ���� ���� �����̸����� 's_'�� �ٿ� ����� �̹��� ����
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					//����� �̹��� ����(�� ����� ��ü�� �°� ����, �� ������� 100,100)
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					//���� ��Ʈ�� �ݾ��ش�
					thumbnail.close();
				}

				// ���� �̹����� ������ ���� list�� �߰� �����ش�
				list.add(attachDTO);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} // end for
		return new ResponseEntity<>(list, HttpStatus.OK);
				//ResponseEntity<>(t body , HttpStatus status)
				//���� ���� responseentity<list , httpstatus.ok> list�� ���� status code ���� ����
	}
	
	//����� �̹��� �����ֱ�
	@GetMapping("/display")
	@ResponseBody
	//byte[] �迭 ���� ���䵥���ͷ� ����
	public ResponseEntity<byte[]> getFile(String fileName) {

		log.info("fileName: " + fileName);
		//���� ��� + ���� �̸��� ���� file��ü�� ����
		File file = new File("c:\\upload\\" + fileName);

		log.info("file: " + file);
		//���� ������(byte[] �迭��) = null ����
		ResponseEntity<byte[]> result = null;

		try {
			HttpHeaders header = new HttpHeaders();
			//���� headers ���� content-type ���� ���ϰ�� ������
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			//���� ������ ���� file�� ������ byte�� ��� ������ , ����� ����ƮŸ�� , statuscode ���� ����
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//÷������ �ٿ�ε� �޼���
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName) {
		//FileSystemResource Ŭ������ ����ؼ� ������ ���� ����
		Resource resource = new FileSystemResource("c:\\upload\\" + fileName);
		
		//resource ��ü�� ��ο� ���� �� ������ �������� ������ 404 statuscode ���
		if (resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		//������ �����Ͽ� ������ ��ο� ���� �̸� ��ȯ
		String resourceName = resource.getFilename();

		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);

		HttpHeaders headers = new HttpHeaders();
		try {
			//userAgent�� http��û�� ������ ����̽��� ������ �� ����� ����Ʈ������ �ĺ������� ��� �ִ� request header�� ������
			//userAgent������ �ľ�, ���� 'MSIE' , 'Trident'�� ��� �ٸ� ������� ó���ϵ��� ��(IE �������� ���� �̸� - IE11ó��)
			boolean checkIE = (userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1);

			String downloadName = null;

			if (checkIE) {
				//replaceAll�Լ� ������� ��� ���ڿ��� ���ϴ� ���� ������ ��ȯ
				downloadName = URLEncoder.encode(resourceOriginalName, "UTF8").replaceAll("\\+", " ");
			} else {
				//resourceOriginalName �� ���ڿ� ���� ����Ʈ �迭�� ��ȯ �� , �ش� ����Ʈ �迭�� ������ �ٽ� 'ISO-8859-1' �� ���ڿ��� �����ϴ� ����
				downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
			}

			headers.add("Content-Disposition", "attachment; filename=" + downloadName);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	
	//���� ���� �޼���
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type) {

		log.info("deleteFile: " + fileName);

		File file;

		try {
			//���� ��ü ����(��� + (filename, utf-8)�� decode ���� => ���ڿ��� �������̴� ������ ���� �� �־ ���)
			file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));

			file.delete();

			if (type.equals("image")) {
				//getAbsolutePath => File�� �Էµ� ���� ��� �����ϴ� �Լ�
				//'s_' �� '' �ƹ��͵� ���� �͵��� ���� largeFileName�� ����
				String largeFileName = file.getAbsolutePath().replace("s_", "");

				log.info("largeFileName: " + largeFileName);
				//file ��ü�� ��� ������ ���� �ְ� .delete()�� ����ؼ� ����
				file = new File(largeFileName);

				file.delete();
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			//���� ���� ���µ� ����?�� �Ϸ��ϸ� 404���� �߰� ����
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<String>("deleted", HttpStatus.OK);

	}
}
