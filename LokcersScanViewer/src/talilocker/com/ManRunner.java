package talilocker.com;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;



public class ManRunner {

		
		public static void main(String[] args) {
			new ManRunner(args[0], args[1]);
		}
		
		public ManRunner(String inputfile, String outputfile) {
			
			try{
			PrintWriter pw = new PrintWriter(outputfile);
			pw.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			HashMap<String, String> closettype  = new HashMap<>(); 
			closettype.put("2", "2");
			closettype.put("12", "2");
			closettype.put("4", "4");
			closettype.put("6", "6");
			closettype.put("26", "6");
			closettype.put("16", "6");
			closettype.put("13", "3");
			closettype.put("3", "3");
			closettype.put("18", "8");
			closettype.put("8", "8");
			closettype.put("9", "9");
			
			
			HashMap<Integer, String> map = new HashMap<>();
			HashMap<String, Object> currentCloset = new HashMap<>();
			ArrayList<String> lockerlist = new ArrayList<>();
			TreeSet<Integer> lockerlistInt = new TreeSet<>();
			
			HashMap<String, String> compunds = new HashMap<>();
			
			String csvFile = inputfile;
	        BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ",";
	        int index = 0;	
	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	            
	            while ((line = br.readLine()) != null) {
	            	if(index ==0 ){
	            		index  = index  +1;
	            		continue;
	            		
	            	}		
	                // use comma as separator
	            	line = line.replace("\"","");
	                String[] country = line.split(cvsSplitBy);
	                
	                //					SCHOOLID,		LockID,			LockerID,			dept,			Sug,			 Location,			Comments,		EquipmentLetf
	                //System.err.println(country[0]+ ","+country[1] + ","+ country[2] +","+ country[3]  +","+ country[4]  +","+ country[5]  +","+ country[6]);
	                compunds .put(country[3], country[3]);
	                Integer currentLock = new Integer(country[2]);
	                lockerlistInt.add(new Integer(country[2])); 
	                map.put(currentLock , line);
	                index  = index  +1;
	                
	            }
	            Integer[] arr = new Integer[ index];
	            lockerlistInt.toArray(arr );
	            int currentIndex = 0;
	            
	            System.err.println("List of Compunds" );
	            System.err.println("===================================");
	            
	            
	            Iterator it = compunds.entrySet().iterator();
	            while (it.hasNext()) {
	                Map.Entry pair = (Map.Entry)it.next();
	                System.err.println(pair.getKey());
	                it.remove(); // avoids a ConcurrentModificationException
	            }
	            
	            
	            System.err.println("List of Closets" );
	            System.err.println("===================================");
	            while(currentIndex  < arr.length ){
	            	
	            	//System.err.println(arr [currentIndex] );
	            	String input  = map.get(arr [currentIndex ]) ;
	            	if(input   == null){
	            		
	            		break;
	            	}
		            String[] country = input.split(cvsSplitBy);
		            String  ctype = closettype.get(country[4]);
		            int numberoflockers = Integer.parseInt(ctype );
		            
		            System.err.println("Closet Type = "  + numberoflockers );
		            System.err.println("===================================");
		            for (int x =0 ; x < numberoflockers;x ++){
		            		
		            	input  = map.get(arr [currentIndex ]) ;
		            	System.err.println(input) ;
		            	currentIndex  = currentIndex  +1;
		            }
		            
	            }
	            
	            System.err.println("Output for Web Site .Copy the content btween Astricks (*) ");
	            System.err.println("*************************"); 
	            
	            currentIndex = 0;
	            String closetLine = "";
	            while(currentIndex  < arr.length ){
	            	
	            	//System.err.println(arr [currentIndex] );
	            	String input  = map.get(arr [currentIndex ]) ;
	            	if(input   == null){
	            		
	            		break;
	            	}
		            String[] country = input.split(cvsSplitBy);
		            String  ctype = closettype.get(country[4]);
		            int numberoflockers = Integer.parseInt(ctype );
		            
		            closetLine = closetLine + numberoflockers +":" + country[4] + ","  + country[3] + "," ;
		            //System.err.println("===================================");
		            for (int x =0 ; x < numberoflockers;x ++){
		            		
		            	input  = map.get(arr [currentIndex ]) ;
		            	if(input== null){
		            		
		            		continue;
		            	}
		            	String[] c = input.split(cvsSplitBy);
		            	//System.err.println(input) ;
		            	closetLine  = closetLine  + "" + c[2]+":"+c[1]  + "";
		            	if(x != (numberoflockers -1 ))
		            	{
		            		closetLine  = closetLine   + ",";
		            	}
		            	
		            	currentIndex  = currentIndex  +1;
		            	
		            	 
		            }
		            closetLine = closetLine+ ",\n" ;
		            
		            
	            }
	            
	            try {
	            	System.err.println(closetLine) ;
	            	File newfile = new File(outputfile);
	            	newfile.setWritable(true);
	            	closetLine = "F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11\n" + closetLine; 
	                Files.write(Paths.get(outputfile),closetLine.getBytes(), StandardOpenOption.APPEND);
	            }catch (IOException e) {
	                //exception handling left as an exercise for the reader
	            	e.printStackTrace();
	            }	
	            	
	            			
			
	           System.err.println("*************************"); 
	            

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	    }


}
