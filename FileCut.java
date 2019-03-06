import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer; 

public class FileCut {
	
	final static int BUFFER_LEN = 1;
	final static int MODE_DEL_TO_START = 1;
	final static String ARG_DEL_TO_START = "deleteToStartOf";

	public static void main(String[] args) {
				InputStream inStream = null;
				OutputStream outStream = null;
				
				if (args.length < 4) {
					System.out.println("not enough parameter given!");
					System.out.println("Usage:");
					System.out.println("FileCut <inputFile> <outputFile> deleteToStartOf <pattern>");
					return;
				}
						
				// System.out.println("FileCut running... In:"+args[0]+" Out:"+args[1]+" Mode:"+args[2]+" Pattern:"+args[3]);

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
					try {
							File afile = new File(args[0]);
							File bfile = new File(args[1]);
	
							inStream = new FileInputStream(afile);
							outStream = new FileOutputStream(bfile);
	
							BufferByte patBuf = new BufferByte(pattern.length());

							int charRead;
							boolean writeFlag = false;
							// copy the file content in bytes
							while ((charRead = inStream.read()) > 0)
							{
								patBuf.push((byte)charRead);
								
								if (patBuf.toString().equals(pattern)) {
									writeFlag = true;
									
									// write content of Buffer to file
									outStream.write(patBuf.toBytes());
								} else { // use else to not write when written buffer due to char duplication
									if (writeFlag) {
										outStream.write(charRead);
									}
								}
							}	
							inStream.close();
							outStream.close();	
							// System.out.println("File is copied successful!");
					}
					catch (IOException e) {
							e.printStackTrace();
					}
				} else {
					System.out.println("invalid mode given!");
				}

	}

}
class BufferByte {
	
	byte[] buffer;
	int pos = 0;
	int max = 0;
	
	public BufferByte(int len) {
		buffer = new byte[len];
		this.max = len-1;
	}
	
	public void push(byte c) {
		//System.out.println(toString()+" pos"+pos+" max"+max+" c"+c);
		if (pos < max) {
			buffer[pos] = c;
			pos++;
		} else {
			for (int i=0; i<max; i++) {
				buffer[i] = buffer[i+1];
			}
			buffer[pos] = c;
		}
		
	}
	
	public String toString() {
		return new String(buffer);
	}
	
	public byte[] toBytes() {
		return buffer;
	}
	
}
