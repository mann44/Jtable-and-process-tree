package cs532_hw;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class JTableDemo extends JApplet {
	
	
	  private String[] clmName =
	    {"Country", "Capital", "Population in Millions", "Democracy"};

	
	  private Object[][] rowData = {
	    {"USA", "Washington DC", 280, true},
	    {"Canada", "Ottawa", 32, true},
	    {"United Kingdom", "London", 60, true},
	    {"Germany", "Berlin", 83, true},
	    {"France", "Paris", 60, true},
	    {"Norway", "Oslo", 4.5, true},
	    {"India", "New Delhi", 1046, true}
	  };

	  // Create a table
	  private JTable jTabD = new JTable(rowData, clmName);
	  private TableRowSorter<TableModel> sorter =
	      new TableRowSorter<TableModel>(jTabD.getModel());

	 
	  

	  // Create two spinners
	  private JSpinner jsrHeigh =
	    new JSpinner(new SpinnerNumberModel(16, 1, 50, 1));
	 

	  // Create a checkbox
	  private JCheckBox jchkShowGrid = new JCheckBox("showGrid", true);

	  

	  public JTableDemo() {
	    JPanel panel1 = new JPanel();
	    panel1.add(new JLabel("rowHeight"));
	    panel1.add(jsrHeigh);
	    
	    panel1.add(jchkShowGrid);

	    jTabD.setRowSorter(sorter);

	    

	    add(panel1, BorderLayout.SOUTH);
	   // add(panel2, BorderLayout.NORTH);
	    add(new JScrollPane(jTabD));

	    // Initialize jTabD
	    jTabD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    jTabD.setGridColor(Color.BLUE);
	    jTabD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    jTabD.setSelectionBackground(Color.RED);
	    jTabD.setSelectionForeground(Color.WHITE);

	    // Register and create a listener for jsrHeigh
	    jsrHeigh.addChangeListener(new ChangeListener() {
	      public void stateChanged(ChangeEvent e) {
	        jTabD.setRowHeight(
	          ((Integer)(jsrHeigh.getValue())).intValue());
	      }
	    });
	    

	   
	    
	    // Register and create a listener for jchkShowGrid
	    jchkShowGrid.addActionListener(new ActionListener() {
	      @Override
	      public void actionPerformed(ActionEvent e) {
	        jTabD.setShowGrid(jchkShowGrid.isSelected());
	      }
	    });

	    // Register and create a listener for jcboAutoResizeMode
	  
	  }

	  public static void main(String[] args) {
	    JTableDemo applet = new JTableDemo();
	    JFrame frame = new JFrame();
	    //EXIT_ON_CLOSE == 3
	    frame.setDefaultCloseOperation(3);
	    frame.setTitle("JTableDemo");
	    frame.getContentPane().add(applet, BorderLayout.CENTER);
	    applet.init();
	    applet.start();
	    frame.setSize(400,320);
	    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	    frame.setLocation((d.width - frame.getSize().width) / 2,
	                      (d.height - frame.getSize().height) / 2);
	    frame.setVisible(true);
	  }

}
