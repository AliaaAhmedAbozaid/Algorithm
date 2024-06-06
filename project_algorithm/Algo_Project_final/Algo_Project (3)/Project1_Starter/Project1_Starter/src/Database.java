import java.util.ArrayList;
import java.util.Iterator;

//import java.awt.Rectangle;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 */

public class Database {

    // this is the SkipList object that we are using
    // a string for the name of the rectangle and then
    // a rectangle object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, Rectangle> list;

    /**
     * The constructor for this class initializes a SkipList object with String
     * and Rectangle a its parameters.
     */
    public Database() {
        list = new SkipList<String, Rectangle>();
    }


    /**
     * Inserts the KVPair in the SkipList if the rectangle has valid coordinates
     * and dimensions, that is that the coordinates are non-negative and that
     * the rectangle object has some area (not 0, 0, 0, 0). This insert will
     * insert the KVPair specified into the sorted SkipList appropriately
     * 
     * @param pair
     *            the KVPair to be inserted
     */
    
    public void insert(KVPair<String, Rectangle> pair) {
    	char first_char_inrectangle=(pair.getKey().charAt(0)); 
    	if((!pair.getValue().isInvalid())&&(((int)first_char_inrectangle>=65)
    			&&((int)first_char_inrectangle<=90))||((!pair.getValue().isInvalid())
    			&&((int)first_char_inrectangle>=97)&&((int)first_char_inrectangle<=122)))
    	{
    		list.insert(pair);
            System.out.println("Rectangle inserted: "+pair.toString());
    	}
    	else
    	{
    		System.out.println("Rectangle rejected: "+pair.toString());
    	}
    	
    }


    /**
     * Removes a rectangle with the name "name" if available. If not an error
     * message is printed to the console.
     * 
     * @param name
     *            the name of the rectangle to be removed
     */
    
    	public void remove(String name) {
        	ArrayList <KVPair<String, Rectangle>> result=new ArrayList<KVPair<String, Rectangle>>();
        	result=(ArrayList<KVPair<String, Rectangle>>)list.search(name);
        	if(result.isEmpty())
        	{
        		System.out.println("Rectangle not removed: ("+name+")");
        	}
        	else
        	{
        		list.remove(name);
               System.out.print("Rectangle removed: ");
               
       		System.out.println(result.get(0));}}
    
    /**
     * Removes a rectangle with the specified coordinates if available. If not
     * an error message is printed to the console.
     * 
     * @param x
     *            x-coordinate of the rectangle to be removed
     * @param y
     *            x-coordinate of the rectangle to be removed
     * @param w
     *            width of the rectangle to be removed
     * @param h
     *            height of the rectangle to be removed
     */
    @SuppressWarnings("null")
    public void remove(int x, int y, int w, int h) {
    	Rectangle rect=new Rectangle(x,y,w,h);
    	KVPair<String, Rectangle> result=list.removeByValue(rect);
    	
    	if(result==null) {
    		//System.out.println("Rectangle not found: "+"("+x+", "+y+", "+w+", "+h+")");
    		System.out.println("Rectangle not found: "+"("+rect.toString()+")");

    	}
    	else
    	{
    		System.out.println("Rectangle removed: " +result.toString());}
    	
  
  }


    /**
     * Displays all the rectangles inside the specified region. The rectangle
     * must have some area inside the area that is created by the region,
     * meaning, Rectangles that only touch a side or corner of the region
     * specified will not be said to be in the region. You will need a SkipList Iterator for this 
     * 
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
public void regionsearch(int x, int y, int w, int h) {
    	
    	Rectangle rec=new Rectangle(x,y,w,h);
    	
    	if(rec.regionIsInvalid()) {
    		System.out.println("Rectangle rejected: (" + x + ", " + y + ", " +
                    w + ", " + h + ")");
    	}
    	else {
    		
    		System.out.print("Rectangles intersecting region (");
            System.out.println(x + ", " + y + ", " + w + ", " + h + "):");
            
            Iterator<KVPair<String, Rectangle>> itr=list.iterator();
            
            while(itr.hasNext()) {
            	KVPair<String, Rectangle> t = itr.next();
            	Rectangle t_rec= t.getValue();
   
                
            	if (t_rec.inRegion(rec))
                    System.out.println(t.toString());
            	
            }
    	}

    }



    /**
     * Prints out all the rectangles that Intersect each other by calling the
     * SkipList method for intersections. You will need to use two SkipList Iterators for this
     */
    public void intersections() {
System.out.println("Intersection pairs:");
    	
    	Iterator<KVPair<String, Rectangle>> itr1=list.iterator();
    		
    	while(itr1.hasNext()) {
    		
    		KVPair<String, Rectangle> t1= itr1.next();
    		
    		Iterator<KVPair<String, Rectangle>> itr2=list.iterator();
    		while(itr2.hasNext()) {
    			KVPair<String, Rectangle> t2= itr2.next();
    			if(t1.getValue().isIntersect(t2.getValue()) && t1!=t2) {
    				
    				System.out.println( t1.toString() + " | " + 
                            t2.toString());
    			}
    		}
    	}

    }


    /**
     * Prints out all the rectangles with the specified name in the SkipList.
     * This method will delegate the searching to the SkipList class completely.
     * 
     * @param name
     *            name of the Rectangle to be searched for
     */
    public void search(String name) {
    	ArrayList <KVPair<String, Rectangle>> result=new ArrayList<KVPair<String, Rectangle>>();
    	result=(ArrayList<KVPair<String, Rectangle>>)list.search(name);
    	
    	if(result.isEmpty())
    	{
    		System.out.println("Rectangle not found: "+name);
    		
    	}
    	else
    	{
    		
    		System.out.println("Rectangle found: ");
    		for(int i=0;i<result.size();i++)
    		{
    		System.out.println(result.get(i));
    		}
    		
    	
    	}
    	

    }

    /**
     * Prints out a dump of the SkipList which includes information about the
     * size of the SkipList and shows all of the contents of the SkipList. This
     * will all be delegated to the SkipList.
     */
    public void dump() {
        list.dump();
    }

}
