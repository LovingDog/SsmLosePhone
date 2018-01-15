package com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.List;
import java.util.UUID;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.parsing.ReaderContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
@Controller
@RequestMapping("/fileUploadController")
public class FileUploadController {
  
//    /*** 
//     * �ϴ��ļ� ��@RequestParamע����ָ�����ϵ�fileΪMultipartFile 
//     *  
//     * @param file 
//     * @return 
//     */  
//	HttpServletRequest mRequest;
//    @RequestMapping("fileUpload")  
//    public String fileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request) {  
//        // �ж��ļ��Ƿ�Ϊ��  
//    	mRequest = mRequest;
//        if (!file.isEmpty()) {  
//            try {  
//                // �ļ�����·��  
//                String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"  
//                        + file.getOriginalFilename();  
//               // ת���ļ�  
//               file.transferTo(new File(filePath));  
//            } catch (Exception e) {  
//                e.printStackTrace();  
//            }  
//        }  
//        // �ض���  
//       return "redirect:/list.html";  
//    }  
//  
//    /***
//	 * ��ȡ�ϴ��ļ��е������ļ�������
//	 * 
//	 * @return
//	 */
//	@RequestMapping("list")
//	public ModelAndView list() {
//		String filePath = mRequest.getSession().getServletContext()
//				.getRealPath("/")
//				+ "upload/";
//		ModelAndView mav = new ModelAndView("list");
//		File uploadDest = new File(filePath);
//		String[] fileNames = uploadDest.list();
//		for (int i = 0; i < fileNames.length; i++) {
//			// ��ӡ���ļ���
//			System.out.println(fileNames[i]);
//		}
//		return mav;
//	}
//}

//@Controller
//@RequestMapping("/fileUploadController")
//public class FileUploadController {
//
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFile(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
	 try {
	  String filename = file.getOriginalFilename();
	  if (filename.endsWith("jpg") || filename.endsWith("png") || filename.endsWith("gif")) {
	   String prefix = filename.substring(filename.lastIndexOf("."));
	   return uploadFile1(file,request);
//	   String imgUri = writeToFileSystem(imgName, file.getBytes(),file);
//	   uploadFile1(file);
//	   return "123123123123";
	  }
	 } catch (Exception e) {
		 return "Exception";
	 }
	 return "adbbddsfsdfsdfsdf";
	}

	private String writeToFileSystem(String imgName, byte[] bytes,
			MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}
	

	public static String uploadFile1(MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException{  
		 if(file!=null){  
	        //��ȡ�ϴ��ļ���ԭʼ����  
		        String ori_file_name = file.getOriginalFilename();  
		        // �����������ͼƬ�ļ�����  
		        String newFileName ="";  
		        // ͼƬ·��+ͼƬ�ļ������ַ���  
		        String nameString="";  
		        // �ϴ�ͼƬ  
		        if ( ori_file_name != null && ori_file_name.length() > 0) {  
		            //��ȡTomcat���������ڵ�·��  
		            String tomcat_path = request.getSession().getServletContext().getRealPath("/");
		            //��ȡTomcat����������·�������һ���ļ�Ŀ¼  
		            String bin_path = tomcat_path.substring(tomcat_path.lastIndexOf("\\")+1,tomcat_path.length());  
		           //�����һ���ļ�Ŀ¼ΪbinĿ¼���������Ϊ�ֶ�����  
		            if(("bin").equals(bin_path)){//�ֶ�����Tomcatʱ��ȡ·��Ϊ��D:\Software\Tomcat-8.5\bin  
		             //��ȡ�����ϴ�ͼƬ���ļ�·��  
		                String pic_path=tomcat_path.substring(0,tomcat_path.lastIndexOf("\\")) +"\\webapps"+"\\pic_file\\";  
		                //ͼƬ������  
		                if (!new File(pic_path).exists()) {
							new File(pic_path).exists();
						}
		               newFileName =UUID.randomUUID() + ori_file_name.substring(ori_file_name.lastIndexOf("."));  
		                nameString=newFileName.substring(0, 22)+".jpg";  
		                //����ͼƬ�ļ�  
		                File newFile = new File(pic_path +"\\"+ nameString);  
		                if (!newFile.exists()) {
							newFile.mkdirs();
						}
		                // ���ڴ��е�����д�����  
		                file.transferTo(newFile);  
		                return pic_path +"\\"+ nameString;
		            }else{//������������Tomcatʱ��ȡ·��Ϊ��D:\Software\Tomcat-8.5  
		                String pic_path = tomcat_path+"\\upload\\";  
		                if (!new File(pic_path).exists()) {
							new File(pic_path).exists();
						}
		                // �µ�ͼƬ����  
		                newFileName =UUID.randomUUID() + ori_file_name.substring(ori_file_name.lastIndexOf("."));  
		               nameString=newFileName.substring(0, 22)+".jpg";  
		                // ��ͼƬ  
		                File newFile = new File(pic_path +"\\"+ nameString);  
		                if (!newFile.exists()) {
							newFile.mkdirs();
						}
		                // ���ڴ��е�����д�����  
		               file.transferTo(newFile);  
		               return pic_path +"\\"+ nameString;
		            }  
		       }  
		    }
		return null;  
	}
	
//	public ModelAndView GetById(int nid, HttpServletRequest request)
//			throws SocketException {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("jsp/pic_show_test");
//		Notice notice = noticeService.GetById(nid);
//		mv.addObject("person_name", notice.getPerson_name());
//		mv.addObject("title", notice.getNotice_title());
//		mv.addObject("content", notice.getNotice_content());
//		Date notice_time = notice.getNt();
//		mv.addObject("notice_time",
//				new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(notice_time));
//		String request_path = request.getScheme() + "://"
//				+ request.getServerName() + ":" + request.getServerPort();
//		// ��װͼƬ��ʾ·��
//		String img_name = request_path + "/pic_file/" + notice.getNotice_img();
//		mv.addObject("img_name", img_name);
//		return mv;
//	}
//	private String writeToFileSystem(String imgName, byte[] bytes,MultipartFile file) {
		// TODO Auto-generated method stub
		//��ȡitem�е��ϴ��ļ���������
		//�����ȡ�����ϴ��ļ����ļ�����·�����֣�ֻ�����ļ�������
		
			

//		String filename = file.getName();
//        filename = filename.substring(filename.lastIndexOf("\\")+1);
//        InputStream in = file.getInputStream();
//        //�õ��ļ����������
//        String saveFilename = makeFileName(filename);
//        //�õ��ļ��ı���Ŀ¼
//        String realSavePath = makePath(saveFilename);
//        System.out.println(realSavePath);
//        //����һ���ļ������
//        FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);
//        //����һ��������
//        byte buffer[] = new byte[1024];
//        //�ж��������е������Ƿ��Ѿ�����ı�ʶ
//        int len = 0;
//        //ѭ�������������뵽���������У�(len=in.read(buffer))>0�ͱ�ʾin���滹������
//        while((len=in.read(buffer))>0){
//            //ʹ��FileOutputStream�������������������д�뵽ָ����Ŀ¼(savePath + "\\" + filename)����
//            out.write(buffer, 0, len);
//        }
//        //�ر�������
//        in.close();
//        //�ر������
//        out.close();
//		return null;
//	}
	
	 /**
     * @Method: makeFileName
     * @Description: �����ϴ��ļ����ļ������ļ����ԣ�uuid+"_"+�ļ���ԭʼ����
     * @Anthor:�°�����
     * @param filename �ļ���ԭʼ����
     * @return uuid+"_"+�ļ���ԭʼ����
     */ 
     private String makeFileName(String filename){  //2.jpg
         //Ϊ��ֹ�ļ����ǵ���������ҪΪ�ϴ��ļ�����һ��Ψһ���ļ���
         return UUID.randomUUID().toString() + "_" + filename;
     }
     
     /**
      * Ϊ��ֹһ��Ŀ¼�������̫���ļ���Ҫʹ��hash�㷨��ɢ�洢
     * @Method: makePath
     * @Description: 
     * @Anthor:�°�����
     *
     * @param filename �ļ�����Ҫ�����ļ������ɴ洢Ŀ¼
     * @param savePath �ļ��洢·��
     * @return �µĴ洢Ŀ¼
     */ 
     private String makePath(String filename,String savePath){
         //�õ��ļ�����hashCode��ֵ���õ��ľ���filename����ַ����������ڴ��еĵ�ַ
         int hashcode = filename.hashCode();
         int dir1 = hashcode&0xf;  //0--15
         int dir2 = (hashcode&0xf0)>>4;  //0-15
         //�����µı���Ŀ¼
         String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
         //File�ȿ��Դ����ļ�Ҳ���Դ���Ŀ¼
         File file = new File(dir);
         //���Ŀ¼������
         if(!file.exists()){
             //����Ŀ¼
             file.mkdirs();
         }
         return dir;
     }


}
