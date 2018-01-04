package at.fhv.itb2.graphPlotter;

import java.awt.*;

/**
 * Class is used to create a new cartesian coordinate system for the functions.
 * @author ske2577
 * <p>Created on: 8.6.17</p>
 * @version 1
 *
 */
public class Axis extends Component{
	private Panel _p;
	
	private int _xMiddle;
	private int _yMiddle;
	private int _xStepDistance = 30;
	private int _yStepDistance = 25;
	private int _xSteps;
	private int _ySteps;
	
	private int _markerLen = 5;
	
	
	public Axis(int xSteps, int ySteps, Panel p){
		_p = p;
		
		_xMiddle = p.getWidth()/2;
		_yMiddle = p.getHeight()/2;

		_xSteps = xSteps;
		_ySteps = ySteps;
		
		_xStepDistance = _xMiddle / xSteps;
		_yStepDistance = _yMiddle / ySteps;
	}
	
	/**
	 * Method to draw the cartesian coordinate system including markings and numbers.
	 * @param g Graphics object
	 */
	public void drawAxis(Graphics g){
		
		//x axis
		g.drawLine(0, _yMiddle, _p.getWidth(), _yMiddle);
		
		//y axis
		g.drawLine(_xMiddle, 0, _xMiddle, _p.getHeight());

		//draw markers on x axis
		for (int i = 1; i < _xSteps; i++) {
			g.drawLine(_xMiddle +_xStepDistance*i, _yMiddle, _xMiddle+_xStepDistance*i, _yMiddle +_markerLen);
			g.drawString(""+i, _xMiddle +(_xStepDistance*i)-3, _yMiddle +20); // 20 and -3 for aesthetic reasons
			
			g.drawLine(_xMiddle-_xStepDistance*i, _yMiddle, _xMiddle-_xStepDistance*i, _yMiddle +_markerLen);
			g.drawString("-"+i, _xMiddle-(_xStepDistance*i)-7, _yMiddle + 20);
		}
		
		//draw markers on y axis
		for (int j = 1; j < _ySteps; j++) {
			g.drawLine(_xMiddle,_yMiddle -_yStepDistance*j, _xMiddle -_markerLen,_yMiddle -_yStepDistance*j);
			g.drawString(""+j, _xMiddle -25, _yMiddle-(_yStepDistance*j)+4); // 25 and +4 for aesthetic reasons
			
			g.drawLine(_xMiddle,_yMiddle + _yStepDistance*j, _xMiddle -_markerLen,_yMiddle +_yStepDistance*j);
			g.drawString("-"+j, _xMiddle -25, _yMiddle +(_yStepDistance*j)+4);

		}
		

		//draw x label
		g.drawString("x", _p.getWidth()-10, _yMiddle +15);
		
		//draw y label
		g.drawString("y", _xMiddle -15, 10);
	}

}
