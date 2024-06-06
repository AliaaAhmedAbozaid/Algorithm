/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project spec.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 */
public class CommandProcessor {

    // the database object to manipulate the
    // commands that the command processor
    // feeds to it
    private Database data;

    /**
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands to.
     * 
     * @param dataIn
     *            the database object to manipulate
     */
    public CommandProcessor() {
        data = new Database();
    }


    /**
     * This method identifies keywords in the line and calls methods in the
     * database as required. Each line command will be specified by one of the
     * keywords to perform the actions within the database required. These
     * actions are performed on specified objects and include insert, remove,
     * regionsearch, search, intersections, and dump. If the command in the file line is not
     * one of these, an appropriate message will be written in the console. This
     * processor method is called for each line in the file. Note that the
     * methods called will themselves write to the console, this method does
     * not, only calling methods that do.
     * 
     * @param line
     *            a single line from the text file
     */
    public void processor(String line) {
    	String[] splited = line.split("\\s+");
    	Rectangle rec_obj=new Rectangle();
    	//System.out.println(line);
    	if(splited[0].equals("insert"))
    	{
    	
    		String nameOfRectangle;
    		 nameOfRectangle=splited[1];
    		 int x=Integer.parseInt(splited[2]);
    		 int y=Integer.parseInt(splited[3]);
    		 int width=Integer.parseInt(splited[4]);
    		 int height=Integer.parseInt(splited[5]);
    		 rec_obj=new Rectangle(x,y,width,height);
    		 KVPair<String,Rectangle> KVPair_obj = new KVPair<String,Rectangle>(nameOfRectangle,rec_obj);
    		 data.insert(KVPair_obj);
    	}
    	else if(splited[0].equals("remove"))
    	{
    		String temp=splited[1];
    		if (rec_obj.isNumber(temp)) {
    		 int x=Integer.parseInt(splited[1]);
       		 int y=Integer.parseInt(splited[2]);
       		 int width=Integer.parseInt(splited[3]);
       		 int height=Integer.parseInt(splited[4]);
       		 data.remove(x, y, width, height);
    	}
    		else {
    			data.remove(temp);
    		}
    	
    	}
    	else if(splited[0].equals("regionsearch")) {
    		
    		int x=Integer.parseInt(splited[1]);
    		 int y=Integer.parseInt(splited[2]);
    		 int width=Integer.parseInt(splited[3]);
    		 int height=Integer.parseInt(splited[4]);
    		 data.regionsearch(x, y, width, height);
    		
    	}
    	else if(splited[0].equals("intersections")) {
    		data.intersections();
    	}
    	else if(splited[0].equals("search")) {
    		data.search(splited[1]);
    	}
    	else {
    		data.dump();}}
    
   

	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}