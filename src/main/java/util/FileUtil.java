package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class FileUtil {

	public static MultipartRequest uploadFile(HttpServletRequest req, String sdir, int maxSize) {
		
		try {
			return new MultipartRequest(req, sdir, maxSize, "UTF-8");
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void download(HttpServletRequest req, HttpServletResponse resp,
			String directory, String sfileName, String ofileName) {
		String sdir = req.getServletContext().getRealPath(directory);
		try {
			File file = new File(sdir, sfileName);
		    InputStream inStream = new FileInputStream(file);
		    
		    String client = req.getHeader("User-Agent");
		    if (client.indexOf("WOW64") == -1) {
		        ofileName = new String(ofileName.getBytes("UTF-8"), "ISO-8859-1");
		    }
		    else {
		        ofileName= new String(ofileName.getBytes("KSC5601"), "ISO-8859-1");
		    }
		    resp.reset();
		    resp.setContentType("application/octet-stream");
		    resp.setHeader("Content-Disposition", 
	                       "attachment; filename=\"" + ofileName + "\"");
		    resp.setHeader("Content-Length", "" + file.length() );
		       
		    OutputStream outStream = resp.getOutputStream();
		    
		    byte b[] = new byte[(int)file.length()];
		    int readBuffer = 0;    
		    while ( (readBuffer = inStream.read(b)) > 0 ) {
		        outStream.write(b, 0, readBuffer);
		    }
		    inStream.close(); 
		    outStream.close();
		}catch(FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println("예외 발생하였습니다.");
			e.printStackTrace();
		}
	}
	
	public static void deleteFile(HttpServletRequest req, String directory, String filename) {
		String sdir = req.getServletContext().getRealPath(directory);
		File file = new File(sdir+File.separator + filename);
		if(file.exists()) {
			file.delete();
		}
	}
}
