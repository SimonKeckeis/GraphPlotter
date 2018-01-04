package at.fhv.itb2.graphPlotter;

import java.awt.*;
import java.util.Random;

/**
 * Class is used to depict the function .
 * @author ske2577
 * <p>Created on: 8.6.17</p>
 * @version 1
 *
 */
public class Graph {

	private double _a;
	private double _b;
	private double _c;
	
	//random colour
	Random rand = new Random();
	private int red = rand.nextInt(255);
	private int green = rand.nextInt(255);
	private int blue = rand.nextInt(255);
	
	public Graph(double a, double b, double c){
		_a = a;
		_b = b;
		_c = c;
	}
	
	/**
	 * Method to calculate the f(x) value.
	 * @param x is the x value
	 * @return the calculated value
	 */
	public double calcFunctionValue(double x){
		return (_a*Math.pow(x, 2) + _b*x + _c);
	}
	
	/**
	 * Method to draw the Graph.
	 * @param g Graphics object
	 * @param p is the panel to draw on
	 */
	public void drawGraph(Graphics g, PlotPanel p){
		Graphics2D g2D = (Graphics2D) p.getGraphics();
        g2D.setStroke(new BasicStroke(2));
        g2D.setColor(new Color(red, green, blue));

        for(int x = 1; x <= (p.getWidth()); x++){
        	
        	double y1 = (double) calcFunctionValue((double) p.transformXfromPixel(x-1));
        	double y2 = (double) calcFunctionValue((double) p.transformXfromPixel(x));
        	
            g2D.drawLine(x-1, p.transformYtoPixel(y1), x, p.transformYtoPixel(y2));
        }
	}
	
	/**
	 * Method to return the function data
	 * @return String data in format: f(x) = ax� + bx + c
	 */
	public String getGraphData(){
		return "f(x) =" + _a + " x� +  " + _b + " x +  " + _c;
	}
}
