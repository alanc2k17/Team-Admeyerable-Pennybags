import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Savefile{
    
    // reads file savefile.txt
    // returns String of all content in savefile.txt
    public static String readInfo(){
	try{
	    File savefile = new File("savefile.txt");
	    FileReader fileReader = new FileReader(savefile);
	    BufferedReader bufferedReader = new BufferedReader(fileReader);
	    StringBuffer contents = new StringBuffer();
	    String line;
	    while ((line = bufferedReader.readLine()) != null){
		contents.append(line);
	    }
	    fileReader.close();
	    return contents.toString();
	}
	catch (IOException e){
	    return "bad";
	}
    }
	
    // writes String line to the file savefile.txt
    // intended use:
    //     line represents all info about a certain Player
    //     info for each player is separated by a delimiter, |
    public static void writeInfo(String line){
	try{
	    File savefile = new File("savefile.txt"); // new file object
	    FileWriter writer = new FileWriter(savefile); // new writer object that will write to savefile
	    writer.write(line); // write to content
	    writer.flush(); // confirm changes
	    writer.close(); // stop further editing
	}
	catch (IOException e){
	    e.printStackTrace();
	}
    }

    public static void main(String[] args){
	System.out.println( Savefile.readInfo() );
    }
}
	    