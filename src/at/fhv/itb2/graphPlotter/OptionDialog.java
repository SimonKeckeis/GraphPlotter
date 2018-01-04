package at.fhv.itb2.graphPlotter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Class is used to create a new dialog upon choosing the corresponding menu item. It gives the possibility to access options for the application.
 * @author ske2577
 * <p>Created on: 8.6.17</p>
 * @version 1
 *
 */
public class OptionDialog extends Dialog{
	
	Button change = new Button("Change");
	TextField xValue = new TextField("1");
	TextField yValue = new TextField("1");
	int x = 2; //2 means the second marker is on the panel edge -- users sees 1 marker
	int y = 2;

	
	public OptionDialog(Frame owner, PlotPanel p) {
		super(owner);
		setSize(200, 200);
		setVisible(true);
		setLayout(new GridLayout(6,1));
		
		add(new Label("Change step amount of x axis:"));
		add(xValue);
		add(new Label("Change step amount of y axis:"));
		add(yValue);
		add(new Panel());
		add(change);
		
		//text field input
		xValue.addTextListener(new TextListener() {
			
			@Override
			public void textValueChanged(TextEvent e) {
				x = 1 + (int) getTextInput(xValue.getText());
			}
		});
		
		yValue.addTextListener(new TextListener() {
			
			@Override
			public void textValueChanged(TextEvent e) {
				y = 1 + (int) getTextInput(yValue.getText());
			}
		});
		
		//change values
		change.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(x > 0 && y > 0){
					p.changeSteps(Math.abs(x), Math.abs(y));
					p.repaint();
				}
				
			}
		});
		
		//close window
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				terminate();
			}
		});
	}

	/**
	 * Method to terminate the dialog but not the application.
	 */
	private void terminate(){
		setVisible(false);
		dispose();
	}

	/**
	 * Method to retrieve the input value of a text field.
	 * @param input the String user input 
	 * @return the parsed value of the String. If the input is not a number, 0 is returned.
	 */
	private double getTextInput(String input){
		double value = 0;
		
		if(input.length() != 0)
		try{
			value = Double.parseDouble(input);
		}catch (Exception e) {
			
		}
		
		return value;
	}
}
