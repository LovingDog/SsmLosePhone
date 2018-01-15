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
//     * 上传文件 用@RequestParam注解来指定表单上的file为MultipartFile 
//     *  
//     * @param file 
//     * @return 
//     */  
//	HttpServletRequest mRequest;
//    @RequestMapping("fileUpload")  
//    public String fileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request) {  
//        // 判断文件是否为空  
//    	mRequest = mRequest;
//        if (!file.isEmpty()) {  
//            try {  
//                // 文件保存路径  
//                String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"  
//                        + file.getOriginalFilename();  
//               // 转存文件  
//               file.transferTo(new File(filePath));  
//            } catch (Exception e) {  
//                e.printStackTrace();  
//            }  
//        }  
//        // 重定向  
//       return "redirect:/list.html";  
//    }  
//  
//    /***
//	 * 读取上传文件中得所有文件并返回
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
//			// 打印出文件名
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
	        //获取上传文件的原始名称  
		        String ori_file_name = file.getOriginalFilename();  
		        // 重新命名后的图片文件名称  
		        String newFileName ="";  
		        // 图片路径+图片文件名称字符串  
		        String nameString="";  
		        // 上传图片  
		        if ( ori_file_name != null && ori_file_name.length() > 0) {  
		            //获取Tomcat服务器所在的路径  
		            String tomcat_path = request.getSession().getServletContext().getRealPath("/");
		            //获取Tomcat服务器所在路径的最后一个文件目录  
		            String bin_path = tomcat_path.substring(tomcat_path.lastIndexOf("\\")+1,tomcat_path.length());  
		           //若最后一个文件目录为bin目录，则服务器为手动启动  
		            if(("bin").equals(bin_path)){//手动启动Tomcat时获取路径为：D:\Software\Tomcat-8.5\bin  
		             //获取保存上传图片的文件路径  
		                String pic_path=tomcat_path.substring(0,tomcat_path.lastIndexOf("\\")) +"\\webapps"+"\\pic_file\\";  
		                //图片重命名  
		                if (!new File(pic_path).exists()) {
							new File(pic_path).exists();
						}
		               newFileName =UUID.randomUUID() + ori_file_name.substring(ori_file_name.lastIndexOf("."));  
		                nameString=newFileName.substring(0, 22)+".jpg";  
		                //创建图片文件  
		                File newFile = new File(pic_path +"\\"+ nameString);  
		                if (!newFile.exists()) {
							newFile.mkdirs();
						}
		                // 将内存中的数据写入磁盘  
		                file.transferTo(newFile);  
		                return pic_path +"\\"+ nameString;
		            }else{//服务中自启动Tomcat时获取路径为：D:\Software\Tomcat-8.5  
		                String pic_path = tomcat_path+"\\upload\\";  
		                if (!new File(pic_path).exists()) {
							new File(pic_path).exists();
						}
		                // 新的图片名称  
		                newFileName =UUID.randomUUID() + ori_file_name.substring(ori_file_name.lastIndexOf("."));  
		               nameString=newFileName.substring(0, 22)+".jpg";  
		                // 新图片  
		                File newFile = new File(pic_path +"\\"+ nameString);  
		                if (!newFile.exists()) {
							newFile.mkdirs();
						}
		                // 将内存中的数据写入磁盘  
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
//		// 封装图片显示路径
//		String img_name = request_path + "/pic_file/" + notice.getNotice_img();
//		mv.addObject("img_name", img_name);
//		return mv;
//	}
//	private String writeToFileSystem(String imgName, byte[] bytes,MultipartFile file) {
		// TODO Auto-generated method stub
		//获取item中的上传文件的输入流
		//处理获取到的上传文件的文件名的路径部分，只保留文件名部分
		
			

//		String filename = file.getName();
//        filename = filename.substring(filename.lastIndexOf("\\")+1);
//        InputStream in = file.getInputStream();
//        //得到文件保存的名称
//        String saveFilename = makeFileName(filename);
//        //得到文件的保存目录
//        String realSavePath = makePath(saveFilename);
//        System.out.println(realSavePath);
//        //创建一个文件输出流
//        FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);
//        //创建一个缓冲区
//        byte buffer[] = new byte[1024];
//        //判断输入流中的数据是否已经读完的标识
//        int len = 0;
//        //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
//        while((len=in.read(buffer))>0){
//            //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
//            out.write(buffer, 0, len);
//        }
//        //关闭输入流
//        in.close();
//        //关闭输出流
//        out.close();
//		return null;
//	}
	
	 /**
     * @Method: makeFileName
     * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
     * @Anthor:孤傲苍狼
     * @param filename 文件的原始名称
     * @return uuid+"_"+文件的原始名称
     */ 
     private String makeFileName(String filename){  //2.jpg
         //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
         return UUID.randomUUID().toString() + "_" + filename;
     }
     
     /**
      * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
     * @Method: makePath
     * @Description: 
     * @Anthor:孤傲苍狼
     *
     * @param filename 文件名，要根据文件名生成存储目录
     * @param savePath 文件存储路径
     * @return 新的存储目录
     */ 
     private String makePath(String filename,String savePath){
         //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
         int hashcode = filename.hashCode();
         int dir1 = hashcode&0xf;  //0--15
         int dir2 = (hashcode&0xf0)>>4;  //0-15
         //构造新的保存目录
         String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
         //File既可以代表文件也可以代表目录
         File file = new File(dir);
         //如果目录不存在
         if(!file.exists()){
             //创建目录
             file.mkdirs();
         }
         return dir;
     }


}
