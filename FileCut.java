import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCut {
	
	final static int BUFFER_LEN = 1;
	final static int MODE_DEL_TO_START = 1;
	final static String ARG_DEL_TO_START = "deleteToStartOf";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("FileCut running... In:"+args[0]+" Out:"+args[1]+" Mode:"+args[2]+" Pattern:"+args[3]);
		
		InputStream inStream = null;
        OutputStream outStream = null;
        
        int mode;
        switch (args[2]) {
        case ARG_DEL_TO_START: mode=MODE_DEL_TO_START; break;
        default: mode = 0;
        }
        if (args[0].length() == 0) {
        	System.out.println("no input file given!");
        	return;
        }
        if (args[1].length() == 0) {
        	System.out.println("no output file given!");
        	return;
        }
        if (args[2].length() == 0) {
        	System.out.println("no mode given!");
        	return;
        }
        if (args[3].length() == 0) {
        	System.out.println("no pattern given!");
        	return;
        }
        String pattern = args[3];
        if (mode!=0) {
	        try
	        {
	
	            File afile = new File(args[0]);
	            File bfile = new File(args[1]);
	
	            inStream = new FileInputStream(afile);
	            outStream = new FileOutputStream(bfile);
	
	            byte[] buffer = new byte[BUFFER_LEN];
	            
	            byte[] patBuf = new byte[pattern.length()]
	
	            int length;
	            // copy the file content in bytes
	            while ((length = inStream.read(buffer)) > 0)
	            {
	            	
	                outStream.write(buffer, 0, length);
	
	            }
	
	            inStream.close();
	            outStream.close();
	
	            System.out.println("File is copied successful!");
	
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
        } else {
        	System.out.println("invalid mode given!");
        }

	}

}
