import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class SkipList<K extends Comparable<? super K>, V>
    implements Iterable<KVPair<K, V>> {
    private SkipNode head; // First element of the top level
    private int size; // number of entries in the Skip List
    /**
     * Initializes the fields head, size and level
     */
    public SkipList() {
        head = new SkipNode(null, 0);
        size = 0;
    }


    /**
     * Returns a random level number which is used as the depth of the SkipNode
     * 
     * @return a random level number
     */
    int randomLevel() {
        int lev;
        Random value = new Random();
        for (lev = 0; Math.abs(value.nextInt()) % 2 == 0; lev++) {
            // Do nothing
        }
        return lev; // returns a random level
    }


    /**
     * Searches for the KVPair using the key which is a Comparable object.
     * 
     * @param key
     *            key to be searched for
     */
    public ArrayList<KVPair<K, V>> search(K key) {
    	
    	
    	ArrayList<KVPair<K, V>> result = new ArrayList<KVPair<K, V>>();
        SkipNode current=head;
    	
    	for(int i=head.level;i>=0;i--)
    	{
    		while(current.forward[i]!=null && current.forward[i].element().getKey().compareTo(key)<0) {
    			current=current.forward[i];
    		}
    	
    			
    	}
    	
    	current = current.forward[0];
		while(current!=null && current.element().getKey().equals(key))
		{
			result.add(current.pair);
			current = current.forward[0];
			
		}
    	
        return result;
    }


    /**
     * @return the size of the SkipList
     */
    public int size() {
        return size;
    }


    /**
     * Inserts the KVPair in the SkipList at its appropriate spot as designated
     * by its lexicoragraphical order.
     * 
     * @param it
     *            the KVPair to be inserted
     */
    @SuppressWarnings("unchecked")
    public void insert(KVPair<K, V> it) {
    	int newLevel=randomLevel();
    	if(newLevel > head.level )
    	{
    		adjustHead( newLevel);
    	}
    	
    	SkipNode[] update=(SkipNode[])Array.newInstance(SkipList.SkipNode.class,
    			head.level + 1);
    	SkipNode current=head;
    	for(int i=head.level;i>=0;i--)
    	{
    		while(current.forward[i]!=null && current.forward[i].element().getKey().compareTo(it.getKey()) < 0)
    		{
    			current=current.forward[i];
    		}
    		update[i]=current;
    	}
    	SkipNode newnode=new SkipNode(it,newLevel) ;
    	for(int i=0;i<=newLevel;i++)
    	{
    		newnode.forward[i]=update[i].forward[i];
    		update[i].forward[i]=newnode;
    		
    	}
    	size++;
    	//System.out.print("size "+ size +"\n");
    	//System.out.print("newLevel  "+ newLevel+"\n");
    	//System.out.print("head.level  "+ head.level+"\n");
    }


    /**
     * Increases the number of levels in head so that no element has more
     * indices than the head.
     * 
     * @param newLevel
     *            the number of levels to be added to head
     */
    @SuppressWarnings("unchecked")
    private void adjustHead(int newLevel) {
    	//System.out.println("hello adjusthead");
    	SkipNode[] tempforward=(SkipNode[])Array.newInstance(SkipList.SkipNode.class,
    			newLevel + 1);
    	for(int i=0;i<=head.level;i++)
    	{
    		tempforward[i]=head.forward[i];
        }
    	head.forward=tempforward;
    	head.level=newLevel;
    }


    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the pair was valid and false if not.
     * 
     * @param pair
     *            the KVPair to be removed
     * @return returns the removed pair if the pair was valid and null if not
     */

    @SuppressWarnings("unchecked")
    public KVPair<K, V> remove(K key) {
    	SkipNode[] update=(SkipNode[])Array.newInstance(SkipList.SkipNode.class,head.level + 1);
    	SkipNode current=head;
    	for(int i=head.level;i>=0;i--)
    	{
    		while(current.forward[i]!=null && current.forward[i].element().getKey().compareTo(key) < 0)
    		{
    			current=current.forward[i];
    		}
    		update[i]=current;
    	}
    	current = current.forward[0];
		if (current != null && current.element().getKey().compareTo(key) == 0) {
			
			for (int i = current.level; i >= 0; i--) {
				update[i].forward[i] = current.forward[i];
			}
			
			size--;
			return current.element();
		}
		else {
			return null;
		}
	}


    /**
     * Removes a KVPair with the specified value.
     * 
     * @param val
     *            the value of the KVPair to be removed
     * @return returns true if the removal was successful
     */
    @SuppressWarnings("unused")
	public KVPair<K, V> removeByValue(V val) {
    	
    
        SkipNode current=head;
        KVPair<K, V> result=null;
        while(current.forward[0]!=null)
        {
        	
        	
        	
        	current=current.forward[0];
        	if(current.element().getValue().equals(val))
        	{
        		result= remove(current.element().getKey());
        		break;
        	}
        	
        }
    	
      
        
    	return result;
    	
    	
       
      }
    	
    


    /**
     * Prints out the SkipList in a human readable format to the console.
     */
    public void dump() {
    	SkipNode current=head;
    	System.out.println("SkipList dump:");
    	for(int i=0;i <=this.size;i++)
    	{
    		if(current.pair==null) {
    			
    			System.out.println("Node has depth "+(current.level+1)+", Value (null)");
    		}
    		else {
    		    
    		    System.out.println("Node has depth "+(current.level+1)+", Value "+current.pair.toString());
    		
    		}
    		current=current.forward[0];
    	}
    	System.out.println("SkipList size is: "+this.size);
    }

    /**
     * This class implements a SkipNode for the SkipList data structure.
     * 
     * @author CS Staff
     * 
     * @version 2016-01-30
     */
    private class SkipNode {

        // the KVPair to hold
        private KVPair<K, V> pair;
        // what is this
        private SkipNode [] forward;
        // the number of levels
        private int level;

        /**
         * Initializes the fields with the required KVPair and the number of
         * levels from the random level method in the SkipList.
         * 
         * @param tempPair
         *            the KVPair to be inserted
         * @param level
         *            the number of levels that the SkipNode should have
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, V> tempPair, int level) {
            pair = tempPair;
            forward = (SkipNode[])Array.newInstance(SkipList.SkipNode.class,
                level + 1);
            this.level = level;
        }


        /**
         * Returns the KVPair stored in the SkipList.
         * 
         * @return the KVPair
         */
        public KVPair<K, V> element() {
            return pair;
        }

    }


    private class SkipListIterator implements Iterator<KVPair<K, V>> {
        private SkipNode current;

        public SkipListIterator() {
            current = head;
        }


        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
        	if(current.forward[0] !=null)
        	{
        		return true;
        	}
        	else {return false;
        	} 
        }


        @Override
        public KVPair<K, V> next() {
            // TODO Auto-generated method stub
              
           current=current.forward[0];
           
           return current.element();
         }

    }

    @Override
    public Iterator<KVPair<K, V>> iterator() {
        // TODO Auto-generated method stub
        return new SkipListIterator();
    }

}
