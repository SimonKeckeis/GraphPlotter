package at.fhv.itb2.graphPlotter;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Class is used to create a new dialog upon choosing the corresponding menu item. It displays about information.
 * @author ske2577
 * <p>Created on: 8.6.17</p>
 * @version 1
 *
 */
public class AboutDialog extends Dialog{

	public AboutDialog(Frame owner) {
		super(owner);
		setSize(200, 200);
		setVisible(true);
		
		setLayout(new GridLayout(3,1));
		add(new Label("This is an about box."));
		add(new Label("Created by ske2577."));
		add(new Label("Date: 8.6.17"));
		
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
}
