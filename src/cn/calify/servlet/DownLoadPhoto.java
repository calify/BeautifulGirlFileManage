package cn.calify.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownLoadPhoto
 */
public class DownLoadPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownLoadPhoto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filename = request.getParameter("filename");
		if(null != filename){
			String photo_path = this.getServletContext().getInitParameter("photopath");  
			String imagePath = photo_path + filename;           
	        FileInputStream fis = new FileInputStream(imagePath);  
	        int size =fis.available(); //得到文件大小   
	        byte data[]=new byte[size];   
	        fis.read(data);  //读数据   
	        fis.close();   
	        String photo_prex = filename.substring(filename.length() - 3, filename.length());
	        photo_prex = photo_prex.toLowerCase();
	        if(photo_prex.equals("jpg")){
	        	response.setContentType("image/jpeg");
	        }else if(photo_prex.equals("png")){
	        	response.setContentType("image/png");
	        }
	        //response.setContentType("image/jpeg"); //设置返回的文件类型   
	        OutputStream os = response.getOutputStream();  
	        os.write(data);  
	        os.flush();  
	        os.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
