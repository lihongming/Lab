package com.asd.framework.gui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.FlowLayout;

import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.border.BevelBorder;

import com.asd.framework.ICustomer;

public abstract class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6030541212602618199L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	
	protected String button_1_name = "button_1";
	protected String button_2_name = "button_2";
	protected String button_3_name = "button_3";
	protected String button_4_name = "button_4";
	
	
	protected JButton btnNewButton_1 = null;
	protected JButton btnNewButton_2 = null;
	protected JButton btnNewButton_3 = null;
	protected JButton btnNewButton_4 = null;
	
	protected String[] columnNames = {"col1","col2","col3"};
	protected String [][]tableVales = {};
	
	private GuiActionFunctor btnButtonFunctor_1 = new GuiActionFunctor();
	private GuiActionFunctor btnButtonFunctor_2 = new GuiActionFunctor();
	private GuiActionFunctor btnButtonFunctor_3 = new GuiActionFunctor();
	private GuiActionFunctor btnButtonFunctor_4 = new GuiActionFunctor();
	
	private TableCellRenderer cellRenderer = new CommonTableCellRenderer();
	
	private JPanel diyPanelContainer = new JPanel();
	
	/**
	 * Create the frame.
	 */
	public MainFrame(String title,String button_1_name, String button_2_name,String button_3_name, String button_4_name) {
		
		this.button_1_name = button_1_name;
		this.button_2_name = button_2_name;
		this.button_3_name = button_3_name;
		this.button_4_name = button_4_name;
		
		
		this.setTitle(title);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//top
		JPanel panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		btnNewButton_1 = new JButton(button_1_name);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_1_actionPerformed(e);
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton(button_2_name);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_2_actionPerformed(e);
			}
		});
		panel.add(btnNewButton_2);
		
		//DIY 
		panel.add(diyPanelContainer);
		
		//table
		tableModel = new DefaultTableModel(tableVales,columnNames);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.setShowGrid(false);
		table.setRowSelectionAllowed(false);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setEnabled(false);
		//support scroll
		JScrollPane scrollPane = new JScrollPane(table);
		panel_1.add(scrollPane, BorderLayout.CENTER);
		table.setModel(tableModel);
		
		//vertical buttons
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new FlowLayout());
		
		Dimension preferredSize = new Dimension();
		preferredSize.setSize(150, 0);
		panel_3.setPreferredSize(preferredSize);
		
		panel_1.add(panel_3, BorderLayout.EAST);
		btnNewButton_3 = new JButton(button_3_name);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_3_actionPerformed(e);
			}
		});
		btnNewButton_4 = new JButton(button_4_name);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_4_actionPerformed(e);
			}
		});

		panel_3.add(btnNewButton_3);
		panel_3.add(btnNewButton_4);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel_1.add(panel_4, BorderLayout.SOUTH);
		JButton exitButton = new JButton("exit");
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				do_exitButton_actionPerformed(e);
			}
		});
		panel_4.add(exitButton);
		
		
	}
	
	
	// make table beautiful
	public class CommonTableCellRenderer extends DefaultTableCellRenderer {

		/**
		 * 
		 */
		private static final long serialVersionUID = -2275887840137069219L;

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean cellHasFocus,
				int row, int column) {
			
			JComponent comp = (JComponent) super.getTableCellRendererComponent(
					table, value, isSelected, cellHasFocus, row, column);
			if (row % 2 == 0) {
				comp.setBackground(Color.CYAN);
			} else {
				comp.setBackground(Color.LIGHT_GRAY);
			}

			return comp;
		}
	}
	
	public void updateCellRenderer(){
		//System.out.println(tableModel.getColumnCount());
		for(int i = 0 ; i < tableModel.getColumnCount(); i++){
			table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
		}
	}
	
	public void setTableHeader(String[] header){
		tableModel.setColumnIdentifiers(header);
		updateCellRenderer();
	}
	
	public void addTableRowData(String[] rowValues){
		tableModel.addRow(rowValues);
		updateCellRenderer();
	}
	
	public void removeTableRowData(int row){
		tableModel.removeRow(row);
		updateCellRenderer();
	}
	
	public void updateRowData(int row, String[] rowValues){
		for(int i = 0; i < rowValues.length; i++){
			tableModel.setValueAt(rowValues[i], row, i);
		}
		updateCellRenderer();
	}
	
	public int getSelectedRow(){
		return table.getSelectedRow();
	}
	
	protected void do_exitButton_actionPerformed(ActionEvent e){
		this.dispose();
	}
	
	public void setBtnButtonFunctor_1(GuiActionFunctor btnButtonFunctor_1) {
		this.btnButtonFunctor_1 = btnButtonFunctor_1;
	}


	public void setBtnButtonFunctor_2(GuiActionFunctor btnButtonFunctor_2) {
		this.btnButtonFunctor_2 = btnButtonFunctor_2;
	}


	public void setBtnButtonFunctor_3(GuiActionFunctor btnButtonFunctor_3) {
		this.btnButtonFunctor_3 = btnButtonFunctor_3;
	}


	public void setBtnButtonFunctor_4(GuiActionFunctor btnButtonFunctor_4) {
		this.btnButtonFunctor_4 = btnButtonFunctor_4;
	}
	
	public String[] parseCustomer(ICustomer customer) {
		return null;
	}
	/**
	 * the code below here is used for client
	 * @param e
	 */
	//button 1 click
	protected void do_btnNewButton_1_actionPerformed(ActionEvent e){
		btnButtonFunctor_1.actionPerformed(e);
	};
	//button 2 click
	protected void do_btnNewButton_2_actionPerformed(ActionEvent e){
		btnButtonFunctor_2.actionPerformed(e);
	}
	//button 3 click
	protected void do_btnNewButton_3_actionPerformed(ActionEvent e){
		btnButtonFunctor_3.actionPerformed(e);
	};
	//button 4 click
	protected void do_btnNewButton_4_actionPerformed(ActionEvent e){
		btnButtonFunctor_4.actionPerformed(e);
	}
	
	public void setDIYComponentPanel(JPanel panel){
		//DIY 
		diyPanelContainer.removeAll();
		diyPanelContainer.add(panel);
	}

	
	
}
