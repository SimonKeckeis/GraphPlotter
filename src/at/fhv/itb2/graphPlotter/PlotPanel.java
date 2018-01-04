package at.fhv.itb2.graphPlotter;

import java.awt.*;
import java.util.ArrayList;


/**
 * Class is used to limit the space to draw.
 * @author ske2577
 * <p>Created on: 8.6.17</p>
 * @version 1
 *
 */
public class PlotPanel extends Panel{

	private Axis _axis;
	private ArrayList<Graph> _graphList = new ArrayList<>();
	private int _xSteps;
	private int _ySteps;
	
	
	public PlotPanel(int xSteps, int ySteps){
		setBackground(new Color(200, 200, 200));
		
		_xSteps = xSteps;
		_ySteps = ySteps;
	}
	
	/**
	 * Method to add a new Graph to the existing list of graphs.
	 * @param g is the Graph to add
	 */
	public void addGraph(Graph g){
        _graphList.add(g);
    }
	
	/**
	 * Method to change the scaling.
	 * @param x is the amount of x axis steps
	 * @param y is the amount of y axis steps
	 */
	public void changeSteps(int x, int y){
		_xSteps = x;
		_ySteps = y;
	}
	
	public ArrayList<Graph> getGraphList(){
		return _graphList;
	}
	
		
	@Override
	public void paint(Graphics g){

		_axis = new Axis(_xSteps, _ySteps, this); 
		_axis.drawAxis(g);
	
		for (Graph graph : _graphList) {
			graph.drawGraph(g, this);
		}
	}
	

	/**
	 * Method to transform a x value to the correct graph ratio.
	 * @param x is the value transform
	 * @return the transformed value
	 */
	public double transformXfromPixel(int x){
		return (((double) _xSteps/(getWidth()/2)) * (x)) -  _xSteps;
	}
	
	/**
	 * Method to transform a y value to the correct pixel value.
	 * @param y is the value transform
	 * @return the transformed value
	 */
	public int transformYtoPixel(double y){
        return (int) ((getHeight()/2) - (((getHeight()/2)/ _ySteps) * y));
    }

	
}
