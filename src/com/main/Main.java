package com.main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;


public class Main {    
	
	    public static void DownloadFile(int i) throws IOException{
	    	 String fileName = new String("filename" + i + ".json"); 
			 URL link = new URL("http://www.parkcinema.az/tickets/get_card_balance/" + i); //The file that you want to download
			
			 InputStream in = new BufferedInputStream(link.openStream());
			 ByteArrayOutputStream out = new ByteArrayOutputStream();
			 byte[] buf = new byte[1024];
			 int n = 0;
			 while (-1!=(n=in.read(buf)))
			 {
			    out.write(buf, 0, n);
			 }
			 out.close();
			 in.close();
			 byte[] response = out.toByteArray();
	 
			 FileOutputStream fos = new FileOutputStream("files/" + fileName);
			 fos.write(response);
			 fos.close();
			 System.out.println("Finished");
			 
			 
	    }
	        
	    public static void ReadJSON(int i){
	    	JSONParser parser = new JSONParser();

	    	try {

	    		Object obj = parser.parse(new FileReader("C:/Users/admin/workspace/test/files/filename" + i + ".json"));

	    		JSONObject jsonObject = (JSONObject) obj;
                
	    		List<Object> dataObject = new ArrayList<Object>();
	    		
	    		Object status =  jsonObject.get("status");
	    		 if (status.toString() == "true") {
	    		   Object data = jsonObject.get("data");
	    		     System.out.println(status.toString());
	    		     System.out.println(data.toString());
	    		     //System.out.println(dataObject.(Object[]) dataObject.toArray(new Object[collection.size()]));
	    		 }
	    	} catch (FileNotFoundException e) {
	    		e.printStackTrace();
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	} catch (ParseException e) {
	    		e.printStackTrace();
	    	}
	    }
	    
		public static void main(String[] args) throws IOException{
	    	  for (int i=1; i<=5; i++){
	    		  DownloadFile(i);
	    	  }
	    	     for (int i=1; i<=5; i++){
	    	    	 ReadJSON(i);
	    	     }
		}
}

