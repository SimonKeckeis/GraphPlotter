package at.fhv.itb2.graphPlotter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * Class to control the application and set it up for the user.
 * @author ske2577
 * <p>Created on: 8.6.17</p>
 * @version 1
 *
 */
public class GraphPlotter extends Frame{

	private PlotPanel _plotPanel;
	
	//menu
	private MenuBar _menu;
	private Menu _mPrefs;
	private MenuItem _mPrefsQuit;
	private Menu _mOptions;
	private MenuItem _mOptionsOptions;
	private Menu _mHelp;
	private MenuItem _mHelpAbout;
	
	//infoHeader
	private Panel _inputHeader;
	private Button _plot;
	private TextField _aValue;
	private TextField _bValue;
	private TextField _cValue;
	private double _a = 0;
	private double _b = 0;
	private double _c = 0;
	
	//removePanel
	private Panel _removePanel;
	private Choice _removeChoice;
	private Button _removeButton;
	

	
	public GraphPlotter(){
		
		setUpMenu();
		setUpWindow();
		
		
		//close window
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				terminate();
			}
		});
		
		//update size
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e){
				_plotPanel.repaint();
			}
		});
		
		
	}
	
	
	
	private void setUpWindow(){
		setTitle("GraphPlotter");
		setMinimumSize(new Dimension(600, 600)); //sets up a new window with the set size.
		setLayout(new BorderLayout());
		
		_plotPanel = new PlotPanel(10,10); //amount of markers on both axis

		add(_plotPanel, BorderLayout.CENTER);
		add(new Panel(), BorderLayout.WEST);
		add(new Panel(), BorderLayout.EAST);
		
		
		setUpInputHeader();
		setUpRemovePanel();
		
		setVisible(true);
	}
		
	
	/**
	 * Method to set up the menu bar.
	 */
	private void setUpMenu(){
		_menu = new MenuBar();
		
		_mPrefs = new Menu("Preferences");
		_mPrefsQuit = new MenuItem("Quit");
		
		_mHelp = new Menu("Help");
		_mHelpAbout = new MenuItem("About");
		
		_mOptions = new Menu("Options");
		_mOptionsOptions = new MenuItem("Options");
		
		setMenuBar(_menu);
		_menu.add(_mPrefs);
		_menu.add(_mHelp);
		_menu.add(_mOptions);
		
		
		//terminate dialog
		_mPrefs.add(_mPrefsQuit);
		_mPrefsQuit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				terminate();
				
			}
		});
		
		//open the about dialog
		_mHelp.add(_mHelpAbout);
		_mHelpAbout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AboutDialog ad = new AboutDialog(GraphPlotter.this);
			}
		});
		
		//open the option dialog
		_mOptions.add(_mOptionsOptions);
		_mOptionsOptions.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				OptionDialog od = new OptionDialog(GraphPlotter.this, _plotPanel);
				
			}
		});
		
	}
	
	
	/**
	 * Method to set up the panel to input the function values the user chooses.
	 */
	private void setUpInputHeader(){
		_inputHeader = new Panel();
		_aValue = new TextField("0");
		_bValue = new TextField("0");
		_cValue = new TextField("0");
		
		_plot = new Button("Plot");

		_inputHeader.add(new Label("f(x) =  "));
		_inputHeader.add(_aValue);
		_inputHeader.add(new Label(" x² +  "));
		_inputHeader.add(_bValue);
		_inputHeader.add(new Label(" x +  "));
		_inputHeader.add(_cValue);
		_inputHeader.add(_plot);
		
		add(_inputHeader, BorderLayout.NORTH);
		
		//extract values from text fields
		_aValue.addTextListener(new TextListener() {
			
			@Override
			public void textValueChanged(TextEvent e) {
				_a = getTextInput(_aValue.getText());
				System.out.println(_a);
			}
		});
		
		_bValue.addTextListener(new TextListener() {
			
			@Override
			public void textValueChanged(TextEvent e) {
				_b = getTextInput(_bValue.getText());
				System.out.println(_b);
			}
		});
		
		_cValue.addTextListener(new TextListener() {
			
			@Override
			public void textValueChanged(TextEvent e) {
				_c = getTextInput(_cValue.getText());
				System.out.println(_c);
			}
		});
		
		//plot graphs	
		_plot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Graph g = new Graph(_a, _b, _c);
				
				_plotPanel.addGraph(g);
				_plotPanel.repaint();
				_removeChoice.add(g.getGraphData());

			}
		});	
		

	}
	
	
	/**
	 * Method to set up the remove Panel. Enables the user to delete a drawn graph. 
	 */
	private void setUpRemovePanel(){
		_removePanel = new Panel();
		_removeChoice = new Choice();
		_removeButton = new Button("Remove");
		
		_removeChoice.add("Graphs...");
		
		_removePanel.setLayout(new GridLayout(1,3));
		_removePanel.add(_removeChoice);
		
		Panel placeholder = new Panel();
		placeholder.setLayout(new GridLayout(1,2));
		placeholder.add(new Panel());
		placeholder.add(_removeButton);
		_removePanel.add(placeholder);
		
		add(_removePanel, BorderLayout.SOUTH);
		
		//Button to remove Graph
		_removeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!(_plotPanel.getGraphList().isEmpty())){
					int userSelection = _removeChoice.getSelectedIndex();
					if(userSelection > 0){
						_removeChoice.remove(userSelection);
						_plotPanel.getGraphList().remove(userSelection-1); // -1 to compensate for the description entry of the choice
						_plotPanel.repaint();
					}
				}
			}
		});
	}
	
	
	/**
	 * Method to terminate the application. Closes the window.
	 */
	private void terminate(){
		setVisible(false);
		dispose();
		System.exit(0);
		
	}
	
	/**
	 * Method to retrieve the input value of a text field.
	 * @param input the String user input 
	 * @return the parsed value of the String. If the input is not a number, 0 is returned.
	 */
	public double getTextInput(String input){
		double value = 0;
		
		if(input.length() != 0)
		try{
			value = Double.parseDouble(input);
		}catch (Exception e) {
			//enter reaction to invalid input here
		}
		
		return value;
	}

	public static void main(String[] args) {
		GraphPlotter gp = new GraphPlotter();
	}

}
