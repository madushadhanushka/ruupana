package com.tutorialspoint;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.ui.ModelMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tutorialspoint.contextextractor.ContextIdentifier;
import com.tutorialspoint.contextextractor.FileReaderCSV;
import com.tutorialspoint.contextextractor.Table;
import com.tutorialspoint.model.User;
@Controller
public class HelloController{
	@RequestMapping(value = "/login")
	   public String userLogin(HttpServletRequest request,ModelMap model) {
	      model.addAttribute("message", "Hello Spring MVC Framework!");
	      User user = new User(request); // create new user and authenticate by
			// compare database username and
			// password
	      /*
	      	if (user.authenticate()) {
	      		user.setAsTrusted(); // put username into the session as logged user
	      		return "main";
	      	} else {
	      		return "signin";
	      	}
	      	*/
	      return "index";
	   }
	@RequestMapping(value = "/main")
	   public String userMain(HttpServletRequest request,ModelMap model) {
		
		return "main";
	}
	@RequestMapping(value = "/accounts")
	   public String userAccount(HttpServletRequest request,ModelMap model) {
		
		return "accounts";
	}
	@RequestMapping(value = "/newproject")
	   public String userNewProject(HttpServletRequest request,ModelMap model) {
		
		return "newproject";
	}
	@RequestMapping(value = "/project")
	   public String userProject(HttpServletRequest request,ModelMap model) {
		
		return "project";
	}
	@RequestMapping(value = "/selcontext")
	   public String userSelectContext(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        String path ="C:/wamp/www/uni/data/fileUploadAndSelect/example.csv";
        FileReaderCSV reader = new FileReaderCSV();
        ContextIdentifier con = new ContextIdentifier();
        reader.filePath = path;
        ArrayList<String[]> temp = reader.GetCSVData();
        Table table=null;
        try{
        	table = con.Initialize(temp);
        }catch(Exception e){
        	System.out.println(e);
        }
        
        int numbColumn=temp.size();
        String[][] tableHeader = new String[numbColumn][3];
        for (int j = 0; j < numbColumn; j++) {
                tableHeader[j][0]=temp.get(j)[0];    
                
                tableHeader[j][1]=String.valueOf(table.Data[j].DataType) ; 
                if(table.Data[j].DataType==1){
                	tableHeader[j][2]="Integer";
                }else if(table.Data[j].DataType==2){
                	tableHeader[j][2]="Float";
                }else if(table.Data[j].DataType==3){
                	tableHeader[j][2]="String";
                }
        }
      
        
        model.addAttribute("tableHeader", tableHeader);
        
        
		return "selcontext";
	}
	@RequestMapping(value = "/selcolumn")
	   public String userSelectColumn(HttpServletRequest request,ModelMap model) {
		String path ="C:/wamp/www/uni/data/fileUploadAndSelect/example.csv";
        FileReaderCSV reader = new FileReaderCSV();
        reader.filePath = path;
        ArrayList<String[]> temp_h = reader.GetCSVDataHorizontal(10);
        
        
        ArrayList<String[]> temp_v = reader.GetCSVData();
        String tableHeader[]= new String[temp_v.size()];
        for (int j = 0; j < temp_v.size(); j++) {
            tableHeader[j]=String.valueOf(j);    
        }
        
        model.addAttribute("tableData", temp_h);
        model.addAttribute("tableHeader", tableHeader);
        
		return "selcolumn";
	}
	/*
	@RequestMapping(value = "/uploadMyFile", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(MultipartHttpServletRequest request)
            throws Exception {
        Iterator<String> itrator = request.getFileNames();
        MultipartFile multiFile = request.getFile(itrator.next());
                try {
            // just to show that we have actually received the file
            System.out.println("File Length:" + multiFile.getBytes().length);
            System.out.println("File Type:" + multiFile.getContentType());
            String fileName=multiFile.getOriginalFilename();
            System.out.println("File Name:" +fileName);
            String path=request.getServletContext().getRealPath("/");
                   
            //making directories for our required path.
            byte[] bytes = multiFile.getBytes();
            File directory=    new File(path+ "/uploads");
            directory.mkdirs();
            // saving the file
            String destFilename=directory.getAbsolutePath()+System.getProperty("file.separator");
            System.out.println(destFilename);
            File file=new File(destFilename);
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(file));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception("Error while loading the file");
        }
                return "success";
        //return toJson("File Uploaded successfully.")
    }
    */
	
	
   @RequestMapping(value = "/hello",method = RequestMethod.GET)
   public String printHello(ModelMap model) {

	   
	   model.addAttribute("message", "MYSQL_DB_URL");
	   return "hello";
   }
   
   @RequestMapping(value = "/test",method = RequestMethod.GET)
   public String printTest(ModelMap model) {
      model.addAttribute("message", "Hello Sprin!");

      return "hello";
   }
   @RequestMapping(value = "/customers",method = RequestMethod.GET)
	public ResponseEntity createCustomer(@RequestBody Chart customer) {

		

		return new ResponseEntity(customer, HttpStatus.OK);
	}
   /*
   public String toJson(Object data)
   {
       ObjectMapper mapper=new ObjectMapper();
       StringBuilder builder=new StringBuilder();
       try {
           builder.append(mapper.writeValueAsString(data));
       } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       return builder.toString();
   }
*/
}