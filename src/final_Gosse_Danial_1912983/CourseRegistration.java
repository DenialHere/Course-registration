package final_Gosse_Danial_1912983;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class CourseRegistration {

	private JFrame frmLasalleCollge;
	private JTextField textFieldID;
	private JTextField textFieldName;
	private JTextField textFieldContact;
	private JTextField textFieldCourse;
	private Connection conn;
	private JTable table;
	private JTextField textFieldSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseRegistration window = new CourseRegistration();
					window.frmLasalleCollge.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void loadTable() {
		
		try {
			
			String query = "select ID, Name, Contact, Course from StudentInfo";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs =  pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			pst.close();
			
		} catch (Exception e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e2);
		}	
	}

	/**
	 * Create the application.
	 */
	public CourseRegistration() {
		conn = sqliteConnection.dbConnector();
		initialize();
	}
	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLasalleCollge = new JFrame();
		frmLasalleCollge.setTitle("LaSalle College - Course Registration");
		frmLasalleCollge.getContentPane().setBackground(Color.CYAN);
		frmLasalleCollge.setBounds(100, 100, 822, 487);
		frmLasalleCollge.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLasalleCollge.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(61, 146, 68, 35);
		frmLasalleCollge.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Name:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(51, 185, 131, 35);
		frmLasalleCollge.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Contact:");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(51, 228, 131, 35);
		frmLasalleCollge.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Course:");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(51, 274, 131, 35);
		frmLasalleCollge.getContentPane().add(lblNewLabel_1_3);
		
		textFieldID = new JTextField();
		textFieldID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldID.setBounds(139, 154, 144, 20);
		frmLasalleCollge.getContentPane().add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldName.setColumns(10);
		textFieldName.setBounds(139, 193, 144, 20);
		frmLasalleCollge.getContentPane().add(textFieldName);
		
		textFieldContact = new JTextField();
		textFieldContact.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldContact.setColumns(10);
		textFieldContact.setBounds(139, 236, 144, 20);
		frmLasalleCollge.getContentPane().add(textFieldContact);
		
		textFieldCourse = new JTextField();
		textFieldCourse.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldCourse.setColumns(10);
		textFieldCourse.setBounds(139, 282, 144, 20);
		frmLasalleCollge.getContentPane().add(textFieldCourse);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					String query = "INSERT INTO StudentInfo(ID, Name, Contact, Course) values(?,?,?,?)";
					PreparedStatement pst = conn.prepareStatement(query);
					
					pst.setString(1, textFieldID.getText());
					pst.setString(2, textFieldName.getText());
					pst.setString(3, textFieldContact.getText());
					pst.setString(4, textFieldCourse.getText());
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Added Sucessfully!");
					pst.close();
					loadTable();
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAdd.setBounds(51, 320, 95, 35);
		frmLasalleCollge.getContentPane().add(btnAdd);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					String query = "UPDATE StudentInfo set ID='" + textFieldID.getText() + "',  Name='" + textFieldName.getText() + "', Contact= '" + textFieldContact.getText() + "' "
							+ ", Course='" + textFieldCourse.getText() + "' Where ID='" + textFieldID.getText() + "'";
					PreparedStatement pst = conn.prepareStatement(query);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Updated sucessfully");
					pst.close();
					loadTable();

					
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnUpdate.setBounds(188, 313, 95, 35);
		frmLasalleCollge.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int action = JOptionPane.showConfirmDialog(null, "Are You Sure?", "Delete", JOptionPane.YES_NO_OPTION);
				if(action == 0) {
					
					try {
						
							String query = "delete from StudentInfo where ID='" + textFieldID.getText() + "'";
							PreparedStatement pst = conn.prepareStatement(query);
							pst.execute();
							JOptionPane.showMessageDialog(null, "Deleted Sucesfully");
							pst.close();
				
				
					} catch (Exception e2) {
						// TODO: handle exception
							JOptionPane.showMessageDialog(null, e2);
					}
					
					loadTable();
						
					}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDelete.setBounds(51, 366, 95, 35);
		frmLasalleCollge.getContentPane().add(btnDelete);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				textFieldID.setText(null);
				textFieldName.setText(null);
				textFieldCourse.setText(null);
				textFieldContact.setText(null);
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnClear.setBounds(188, 366, 95, 35);
		frmLasalleCollge.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {

		});
		scrollPane.setBounds(340, 140, 432, 241);
		frmLasalleCollge.getContentPane().add(scrollPane);
		
		table = new JTable();
		try {
			loadTable();
			
		}catch(Exception e2) {
			JOptionPane.showMessageDialog(null, e2);
		}
			
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				try {
					
					int row = table.getSelectedRow();
					String Id = table.getModel().getValueAt(row, 0).toString();
					String query = "select * from StudentInfo where ID='" + Id + "'";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs =  pst.executeQuery();
					
					while(rs.next()) {
						
						textFieldID.setText(rs.getString("ID"));
						textFieldName.setText(rs.getString("Name"));
						textFieldContact.setText(rs.getString("Contact"));
						textFieldCourse.setText(rs.getString("Course"));
						
					}
					
					pst.close();
					rs.close();
					
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
					
				}
				
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\maste\\eclipse-workspace\\final_Gosse_Danial_1912983\\Resources\\new_LaSalle_college.png"));
		lblNewLabel.setBounds(39, 23, 301, 93);
		frmLasalleCollge.getContentPane().add(lblNewLabel);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				try {
					
					String query = "select ID, Name, Contact, Course from StudentInfo where name =?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textFieldSearch.getText());
					ResultSet rs =  pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					rs.close();
					pst.close();
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2);
				}
				
			}
		});
		textFieldSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldSearch.setColumns(10);
		textFieldSearch.setBounds(628, 109, 144, 20);
		frmLasalleCollge.getContentPane().add(textFieldSearch);
		
		JLabel lblNewLabel_1_4 = new JLabel("Search by name:");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(504, 101, 115, 35);
		frmLasalleCollge.getContentPane().add(lblNewLabel_1_4);
		
	}
}
