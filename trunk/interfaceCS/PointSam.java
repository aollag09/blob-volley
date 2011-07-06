package interfaceCS;

public class PointSam {

	private double x,y;
	
	public PointSam(){
		this(0d,0d);
	}
	
	public PointSam(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public String toString(){
		return "["+this.x+", "+this.y+"]";
	}
}
