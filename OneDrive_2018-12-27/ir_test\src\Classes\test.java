package Classes;
import java.io.*;
public class test {
	public static void main(String[] args) throws IOException {
		RandomAccessFile raf = new RandomAccessFile("C://Users//YUG37//eclipse-workspace//ir_test//WebContent//data//title.txt", "r");
//		FileWriter wr = new FileWriter("title_index.txt");
		raf.seek(628);
		String line = raf.readLine();
//		String line = "";
		String test = "abc";
		System.out.println(line);
		test = test.replace("abc", line);
		System.out.println(test);
//		while (line != null) {
//			line = raf.readLine();
//			wr.write(line + ":" + raf.getFilePointer() + "\n");
////			System.out.println(raf.getFilePointer());
//			line = raf.readLine();
////			System.out.println(line);
//		}
//		raf.close();
//		wr.close();
	}
}
