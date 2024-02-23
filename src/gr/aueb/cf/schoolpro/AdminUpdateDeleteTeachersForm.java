package gr.aueb.cf.schoolpro;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolpro.util.DBUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JSeparator;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class AdminUpdateDeleteTeachersForm extends JFrame {
	private static final long serialVersionUID = 123456;
	private JPanel contentPane;
	private JTextField firstnameTxt;
	private JLabel ssnLbl;
	private JTextField ssnTxt;
	private JLabel lastnameLbl;
	private JTextField lastnameTxt;
	private JLabel specialityLbl;
	private JComboBox<String> specialityComboBox;
	private JComboBox<String> userComboBox;
	private Map<Integer, String> usernames;
	private Map<Integer, String> specialities;
	private DefaultComboBoxModel<String> specialitiesModel;
	private DefaultComboBoxModel<String> usernamesModel;
	Connection conn;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	private JTextField idTxt;

	public AdminUpdateDeleteTeachersForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				String sql = "SELECT * FROM TEACHERS WHERE LASTNAME LIKE ?";
				
				try {
					conn = DBUtil.getConnection();
					ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ps.setString(1, Main.getTeachersMenu().getLastname() + "%");
					rs = ps.executeQuery();
					
					if (!rs.next()) {
						JOptionPane.showMessageDialog(null, "No teachers found", "Result", JOptionPane.INFORMATION_MESSAGE);
						Main.getAdminUpdateDeleteTeachersForm().setVisible(false);
						Main.getTeachersMenu().setVisible(true);
						return;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			    
			    // usernameComboBox
			    PreparedStatement psUsers;
			    ResultSet rsUsers;
			    
			    try {
		    	    String sqlUsers = "SELECT ID, USERNAME FROM USERS";
				    psUsers = conn.prepareStatement(sqlUsers);
		    		rsUsers = psUsers.executeQuery();
			    	usernames = new HashMap<>();
			    	usernamesModel = new DefaultComboBoxModel<>();
			    	
			    	while (rsUsers.next()) {
			    		String username = rsUsers.getString("USERNAME");
			    		int id = rsUsers.getInt("ID");
			    		usernames.put(id, username);
			    		usernamesModel.addElement(username);
			    	}
			    	userComboBox.setModel(usernamesModel);
			    	userComboBox.setMaximumRowCount(5);
			    	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			    
			    try {
					idTxt.setText(Integer.toString(rs.getInt("ID")));
					firstnameTxt.setText(rs.getString("FIRSTNAME"));
					lastnameTxt.setText(rs.getString("LASTNAME"));
					userComboBox.setSelectedItem(usernames.get(rs.getInt("USER_ID")));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			    
			    
			 // specialityComboBox
			    PreparedStatement psSpeciality;
			    ResultSet rsSpeciality;
			    
			    try {
		    	    String sqlSpeciality = "SELECT ID, SPECIALITY FROM SPECIALITIES";
		    	    psSpeciality = conn.prepareStatement(sqlSpeciality);
		    	    rsSpeciality = psSpeciality.executeQuery();
			    	specialities = new HashMap<>();
			    	specialitiesModel = new DefaultComboBoxModel<>();
			    	
			    	while (rsSpeciality.next()) {
			    		String speciality = rsSpeciality.getString("SPECIALITY");
			    		int id = rsSpeciality.getInt("ID");
			    		specialities.put(id, speciality);
			    		specialitiesModel.addElement(speciality);
			    	}
			    	specialityComboBox.setModel(specialitiesModel);
			    	specialityComboBox.setMaximumRowCount(5);
			    	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			    
			    try {
					idTxt.setText(Integer.toString(rs.getInt("ID")));
					firstnameTxt.setText(rs.getString("FIRSTNAME"));
					lastnameTxt.setText(rs.getString("LASTNAME"));
					ssnTxt.setText(rs.getString("SSN"));
					userComboBox.setSelectedItem(usernames.get(rs.getInt("USER_ID")));
					specialityComboBox.setSelectedItem(specialities.get(rs.getInt("SPECIALITY_ID")));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
					
			}
		});
		setTitle("Ενημέρωση / Διαγραφή Εκπαιδευτή");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 468);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setColumns(10);
		idTxt.setBackground(new Color(252, 252, 205));
		idTxt.setBounds(129, 40, 59, 20);
		contentPane.add(idTxt);
		
		JLabel firstnameLbl = new JLabel("Όνομα");
		firstnameLbl.setBounds(68, 75, 49, 17);
		firstnameLbl.setForeground(new Color(128, 0, 0));
		firstnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(firstnameLbl);
		
		firstnameTxt = new JTextField();
		firstnameTxt.setBounds(129, 73, 207, 20);
		contentPane.add(firstnameTxt);
		firstnameTxt.setColumns(10);
		
		ssnLbl = new JLabel("Μητρώο");
		ssnLbl.setBounds(62, 133, 64, 17);
		ssnLbl.setForeground(new Color(128, 0, 0));
		ssnLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(ssnLbl);
		
		ssnTxt = new JTextField();
		ssnTxt.setBounds(129, 133, 207, 20);
		ssnTxt.setColumns(10);
		contentPane.add(ssnTxt);
		
		lastnameLbl = new JLabel("Επώνυμο");
		lastnameLbl.setBounds(52, 105, 64, 17);
		lastnameLbl.setForeground(new Color(128, 0, 0));
		lastnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lastnameLbl);
		
		lastnameTxt = new JTextField();
		lastnameTxt.setBounds(129, 103, 207, 20);
		lastnameTxt.setColumns(10);
		contentPane.add(lastnameTxt);
		
		specialityLbl = new JLabel("Ειδικότητα");
		specialityLbl.setBounds(43, 195, 81, 17);
		specialityLbl.setForeground(new Color(128, 0, 0));
		specialityLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(specialityLbl);
		
		specialityComboBox = new JComboBox<>();
		specialityComboBox.setBounds(129, 194, 207, 20);
		contentPane.add(specialityComboBox);
		
		userComboBox = new JComboBox<>();
		userComboBox.setBounds(129, 225, 207, 20);
		contentPane.add(userComboBox);
		
		JLabel usernameLbl = new JLabel("Username");
		usernameLbl.setForeground(new Color(128, 0, 0));
		usernameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		usernameLbl.setBounds(43, 226, 72, 17);
		contentPane.add(usernameLbl);
	
		
		// Results are displayed based on the order they were stored in the DB and not based on the ascending order of the id
		
		// First Record Button
		
		JButton firstBtn = new JButton("");
		firstBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.first()) {
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						ssnTxt.setText(rs.getString("SSN"));
						userComboBox.setSelectedItem(usernames.get(rs.getInt("USER_ID")));
						specialityComboBox.setSelectedItem(specialities.get(rs.getInt("SPECIALITY_ID")));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		firstBtn.setBounds(108, 300, 49, 23);
		firstBtn.setIcon(new ImageIcon(AdminUpdateDeleteStudentsForm.class.getResource("/resources/First record.png")));
		contentPane.add(firstBtn);
		
		
		// Previous Record Button
		
		JButton prevBtn = new JButton("");
		prevBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.previous()) {
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						ssnTxt.setText(rs.getString("SSN"));
						userComboBox.setSelectedItem(usernames.get(rs.getInt("USER_ID")));
						specialityComboBox.setSelectedItem(specialities.get(rs.getInt("SPECIALITY_ID")));
					} else {
						rs.first();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		prevBtn.setBounds(155, 300, 49, 23);
		prevBtn.setIcon(new ImageIcon(AdminUpdateDeleteStudentsForm.class.getResource("/resources/Previous_record.png")));
		contentPane.add(prevBtn);
		
		
		// Next Record Button
		
		JButton nextBtn = new JButton("");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.next()) {
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						ssnTxt.setText(rs.getString("SSN"));
						userComboBox.setSelectedItem(usernames.get(rs.getInt("USER_ID")));
						specialityComboBox.setSelectedItem(specialities.get(rs.getInt("SPECIALITY_ID")));
					} else {
						rs.last();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		nextBtn.setIcon(new ImageIcon(AdminUpdateDeleteStudentsForm.class.getResource("/resources/Next_track.png")));
		nextBtn.setBounds(200, 300, 49, 23);
		contentPane.add(nextBtn);
		
		
		// Last Record Button
		
		JButton lastBtn = new JButton("");
		lastBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.last()) {
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						ssnTxt.setText(rs.getString("SSN"));
						userComboBox.setSelectedItem(usernames.get(rs.getInt("USER_ID")));
						specialityComboBox.setSelectedItem(specialities.get(rs.getInt("SPECIALITY_ID")));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		lastBtn.setIcon(new ImageIcon(AdminUpdateDeleteStudentsForm.class.getResource("/resources/Last_Record.png")));
		lastBtn.setBounds(248, 300, 49, 23);
		contentPane.add(lastBtn);
		
		JLabel idLbl = new JLabel("ID");
		idLbl.setForeground(new Color(128, 0, 0));
		idLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		idLbl.setBounds(100, 42, 17, 17);
		contentPane.add(idLbl);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(44, 273, 303, 2);
		contentPane.add(separator);
		
		
		// Update Button
		
		JButton udpateBtn = new JButton("Update");
		udpateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		udpateBtn.setForeground(new Color(26, 37, 179));
		udpateBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		udpateBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		udpateBtn.setBounds(112, 350, 90, 45);
		contentPane.add(udpateBtn);
		
		
		// Delete Button
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM TEACHERS WHERE ID = ?";
				int response;
				
				try {
					ps = conn.prepareStatement(sql);
					ps.setInt(1,  Integer.parseInt(idTxt.getText()));
					
					response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος;", "Warning", JOptionPane.YES_NO_OPTION);
					
					if (response == JOptionPane.YES_OPTION) {
						int numberOfRowsAffected = ps.executeUpdate();
						JOptionPane.showMessageDialog(null,  numberOfRowsAffected + " rows deleted successfully", "DELETE", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} 
			}
		});
		deleteBtn.setForeground(new Color(26, 37, 179));
		deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		deleteBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		deleteBtn.setBounds(207, 350, 90, 45);
		contentPane.add(deleteBtn);
		
		
		// Close Button
		
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getAdminUpdateDeleteTeachersForm().setVisible(false);
				Main.getTeachersMenu().setVisible(true);
			}
		});
		closeBtn.setForeground(new Color(26, 37, 179));
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		closeBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		closeBtn.setBounds(398, 350, 90, 45);
		contentPane.add(closeBtn);
	}
}