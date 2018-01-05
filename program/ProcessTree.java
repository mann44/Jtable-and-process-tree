package cs532_hw;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

public class ProcessTree extends JApplet{
   
  private JButton jAdd = new JButton("Add Node");
  private JButton jRemove = new JButton("Remove Selected Node");
  private JTextArea jtMsg = new JTextArea();
  private JTree jTreedemo;

  public ProcessTree() {
    
    DefaultMutableTreeNode root, europe, northAmerica, us;

    europe = new DefaultMutableTreeNode("Europe");
    europe.add(new DefaultMutableTreeNode("UK"));
    europe.add(new DefaultMutableTreeNode("Germany"));
    europe.add(new DefaultMutableTreeNode("France"));
    europe.add(new DefaultMutableTreeNode("Norway"));

    northAmerica = new DefaultMutableTreeNode("North America");
    us = new DefaultMutableTreeNode("US");
    us.add(new DefaultMutableTreeNode("California"));
    us.add(new DefaultMutableTreeNode("Texas"));
    us.add(new DefaultMutableTreeNode("New York"));
    us.add(new DefaultMutableTreeNode("Florida"));
    us.add(new DefaultMutableTreeNode("Illinois"));
    northAmerica.add(us);
    northAmerica.add(new DefaultMutableTreeNode("Canada"));

    root = new DefaultMutableTreeNode("World");
    root.add(europe);
    root.add(northAmerica);

   
    JPanel p1 = new JPanel();
    p1.add(jAdd);
    p1.add(jRemove);
    JPanel p2 = new JPanel();
    p2.setLayout(new GridLayout(1,2));
    p2.add(new JScrollPane(jTreedemo = new JTree(root)));
    jtMsg.setWrapStyleWord(true);
    jtMsg.setLineWrap(true);
    getContentPane().add(p1, BorderLayout.NORTH);
    getContentPane().add(p2,BorderLayout.CENTER);
    getContentPane().add(new JSplitPane(JSplitPane.VERTICAL_SPLIT,p2,new JScrollPane(jtMsg)), BorderLayout.CENTER);
    
    
    
    jAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode)jTreedemo.getLastSelectedPathComponent();
        if (parent == null) {
          JOptionPane.showMessageDialog(null,"No node in the first tree is selected");
          return;
        }
        String nodeName = JOptionPane.showInputDialog(null, "Enter a name for this new node", "Add a Child",JOptionPane.QUESTION_MESSAGE);
        parent.add(new DefaultMutableTreeNode(nodeName));
        ((DefaultTreeModel)(jTreedemo.getModel())).reload();
      }
    });

    jRemove.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        TreePath[] paths = jTreedemo.getSelectionPaths();
        if (paths == null) {
          JOptionPane.showMessageDialog(null,"No node in the left tree is selected");
          return;
        }
        for (int i = 0; i < paths.length; i++) {
          DefaultMutableTreeNode node = (DefaultMutableTreeNode)
              (paths[i].getLastPathComponent());

          if (node.isRoot()) {
            JOptionPane.showMessageDialog(null,"Cannot remove the root");
          }
          else
            node.removeFromParent();
        }
        ((DefaultTreeModel)(jTreedemo.getModel())).reload();
       
      }
    });
    jTreedemo.addTreeSelectionListener(new TreeSelectionListener() {
        @Override
        public void valueChanged(TreeSelectionEvent e) {
             jtMsg.append("Node "+e.getPath().getLastPathComponent().toString()+" is selected"+"\n");
        }
    });
  }
  public static void main(String[] args) {
    ProcessTree applet = new ProcessTree();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(3);
    frame.setTitle("Tree Event Demo");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(650, 420);
    frame.setLocation(1200,300);
    frame.setVisible(true);
  }


}
