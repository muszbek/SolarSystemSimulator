package SolarSystem;
import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLayeredPane;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.SystemColor;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
 
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class gui {
		

	public JFrame frmSolarsystemsimulator;
	public Painter layeredPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	

	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
	    
		frmSolarsystemsimulator = new JFrame();
		frmSolarsystemsimulator.setTitle("Solar system simulator");
		frmSolarsystemsimulator.getContentPane().setBackground(SystemColor.control);
		frmSolarsystemsimulator.setBounds(0, 0, 1280, 720);
		frmSolarsystemsimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("Default rotation angles");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GraphicModify.horizontalRotate=0;
				GraphicModify.verticalRotate=0;
				GraphicModify.horizontalAngle=0;
				GraphicModify.verticalAngle=0;
			}
		});
		
		//JButton btnStop = new JButton("Stop");
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setOrientation(JScrollBar.HORIZONTAL);
		
		scrollBar.setMinimum(-80);	//set time boundaries here - time step per tick is according to GraphicModify.setTimeDifference()
		scrollBar.setMaximum(80);
		scrollBar.setValue(scrollBar.getMinimum());
		scrollBar.addAdjustmentListener(new AdjustmentListener(){
			public void adjustmentValueChanged(AdjustmentEvent e) {
				GraphicModify.timeScroll=e.getValue();
				//System.out.println(GraphicModify.timeScroll);
				//for testing, it works
			}
		});
		
		JLabel lblSpeed = new JLabel("Speed");
		
		JCheckBox chckbxLabels = new JCheckBox("Labels of objects");
		chckbxLabels.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				Painter.labelsDraw = e.getStateChange();
			}
		});
		
		
		//JLabel lblDetailedinformation = new JLabel("Detailed information:");
		
		//JPanel layeredPane = new JPanel();
		layeredPane = new Painter();

		layeredPane.setForeground(Color.BLACK);
		layeredPane.setBackground(Color.BLACK);
		
		//mouse rotator and zoom declared here
		MouseRotation myRotator = new MouseRotation();
		layeredPane.addMouseListener(myRotator);
		layeredPane.addMouseMotionListener(myRotator);
		
		MouseZoom myZoom = new MouseZoom();
		layeredPane.addMouseWheelListener(myZoom);
		//mouse rotator and zoom declaration end
		
		//JComboBox comboBox = new JComboBox();
		
		//comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Sun", "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto"}));
		//comboBox.setSelectedIndex(0);
			
		JLabel lblCenter = new JLabel("Center:");
			
		
		String items[] =  {"Sun", "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune","Pluto"};
		JList list = new JList(items);
		ListSelectionModel selModel = list.getSelectionModel();
		selModel.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				if (!e.getValueIsAdjusting()){
				if (lsm.isSelectionEmpty()) {
	                System.out.println(" <none>");
	            } else {
	                // Which planet is selected
	                int minIndex = lsm.getMinSelectionIndex();
	                int maxIndex = lsm.getMaxSelectionIndex();
	                for (int i = minIndex; i <= maxIndex; i++) {
	                    if (lsm.isSelectedIndex(i)) {
	                    	Main.presentArray=CenterChooser.chooseCenter(i,Main.solarArray);
	                    	//System.out.println(" " + selnum);
	                    }
	                }
	            }
			}
			}
		});
		
		
		
	    
		GroupLayout groupLayout = new GroupLayout(frmSolarsystemsimulator.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(scrollBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton)
									.addPreferredGap(ComponentPlacement.RELATED)
									//.addComponent(btnStop, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
									)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							//.addComponent(lblDetailedinformation)
							)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(list, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
								//.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblSpeed))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(chckbxLabels))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCenter)))
					.addGap(24)
					.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 1085, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						//.addComponent(btnStop)
						)
					.addGap(18)
					.addComponent(lblSpeed)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(chckbxLabels)
					.addGap(9)
					//.addComponent(lblDetailedinformation)
					.addPreferredGap(ComponentPlacement.RELATED)
					//.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblCenter)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(42))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(list)))
					.addContainerGap(286, Short.MAX_VALUE))
				.addComponent(layeredPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
		);
		frmSolarsystemsimulator.getContentPane().setLayout(groupLayout);
		frmSolarsystemsimulator.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{frmSolarsystemsimulator.getContentPane(), lblSpeed, scrollBar, layeredPane, btnNewButton, lblCenter, chckbxLabels}));
		
	}
}
