public class Rectangle {
	private int x;
	private int y;
	private int width;
	private int height;
    public Rectangle() {}
    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    
    public boolean isEquals(Rectangle rect1) {
        if(rect1.getX() == this.getX() &&
           rect1.getY() == this.getY() &&
           rect1.getWidth() == this.getWidth() &&
           rect1.getHeight() == this.getHeight()) {
            return true;
        }
        return false;
    }
    
    
    @Override
    public boolean equals(Object   rect1) {
        if(((Rectangle) rect1).getX() == this.getX() &&
           ((Rectangle) rect1).getY() == this.getY() &&
           ((Rectangle) rect1).getWidth() == this.getWidth() &&
           ((Rectangle) rect1).getHeight() == this.getHeight()) {
            return true;
        }
        return false;
    }
    
    
    
    public boolean isInvalid() {
        if( this.x < 0 || this.y < 0 || this.width <= 0 
        		|| this.height <= 0 || (this.x + this.width > 1024)||(this.y + this.height>1024))
        {
        	return true;
        }
        return false;
    }
    
    
    public boolean regionIsInvalid() {
        if( this.width <= 0 || this.height <= 0 )
        {
        	return true;
        }
        return false;
    }
    
    
    
    public boolean inRegion(Rectangle rec) {
    	
    	int X1 = rec.x + rec.width;
        int Y1 = rec.y + rec.height;
        int X2 = this.x + this.width;
        int Y2 = this.y + this.height;
        
    	if (!  (X1 <= this.getX() || X2 <= rec.x || Y1 <= this.y || Y2 <= rec.y )  )
    		return true;
    	else
    		return false;
    }
    
    
    
    
   
    		public String toString() {
        return ""+this.x+", "+this.y+", "+this.width+", "+this.height+"";
    }
    		public boolean isIntersect(Rectangle rect1) {
    		    int x1 = Math.max(rect1.x, this.x);
    		    int y1 = Math.max(rect1.y, this.y);
    		    int x2 = Math.min(rect1.x + rect1.width,this.x + this.width);
    		    int y2 = Math.min(rect1.y + rect1.height, this.y + this.height);
    		    int area = Math.max(0, x2 - x1) * Math.max(0, y2 - y1);
    		    if(area > 0)
    		    	{
    		    	return true;
    		    	}
    		    else {
    		    	return false;
    		    }
    		}
    		
    		
    		
    		
    		public boolean isNumber(String test) {

    	        try {
    	            Integer.parseInt(test);
    	            return true;
    	        } 
    	        catch (Exception e) {
    	            return false;}}
   }
