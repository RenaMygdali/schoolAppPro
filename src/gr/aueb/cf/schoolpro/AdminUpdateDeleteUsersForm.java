package gr.aueb.cf.schoolpro;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolpro.security.SecUtil;
import gr.aueb.cf.schoolpro.util.DBUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JSeparator;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JPasswordField;

public class AdminUpdateDeleteUsersForm extends JFrame {
	private static final long serialVersionUID = 123456;
	private JPanel contentPane;
	private JTextField usernameTxt;
	private JLabel roleLbl;
	Connection conn;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	private JTextField idTxt;
	private JPasswordField newPassTxt;
	private JPasswordField confirmPassTxt;
	private String inputNewPass;
	private String inputConfirmPass;
	private JTextField roleTxt;
	
	public AdminUpdateDeleteUsersForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				String sql = "SELECT * FROM USERS WHERE USERNAME LIKE ?";
				
				try {
					conn = DBUtil.getConnection();
					ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ps.setString(1, Main.getUsersMenu().getUsername() + "%");
					rs = ps.executeQuery();
					
					if (!rs.next()) {
						JOptionPane.showMessageDialog(null, "No users found", "Result", JOptionPane.INFORMATION_MESSAGE);
						Main.getAdminUpdateDeleteUsersForm().setVisible(false);
						Main.getUsersMenu().setVisible(true);
						return;
					}
					
				
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						usernameTxt.setText(rs.getString("USERNAME"));
						roleTxt.setText(rs.getString("ROLE"));
				
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		setTitle("Ενημέρωση / Διαγραφή Χρήστη");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 468);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		newPassTxt = new JPasswordField();
		newPassTxt.setBounds(153, 203, 207, 20);
		newPassTxt.setColumns(10);
		contentPane.add(newPassTxt);
		
		idTxt = new JTextField();
		idTxt.setBounds(153, 40, 59, 20);
		idTxt.setEditable(false);
		idTxt.setColumns(10);
		idTxt.setBackground(new Color(252, 252, 205));
		contentPane.add(idTxt);
		
		JLabel usernameLbl = new JLabel("Username");
		usernameLbl.setBounds(61, 75, 73, 17);
		usernameLbl.setForeground(new Color(128, 0, 0));
		usernameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(usernameLbl);
		
		usernameTxt = new JTextField();
		usernameTxt.setBounds(153, 73, 207, 20);
		contentPane.add(usernameTxt);
		usernameTxt.setColumns(10);
		
		roleLbl = new JLabel("Role");
		roleLbl.setBounds(96, 106, 38, 17);
		roleLbl.setForeground(new Color(128, 0, 0));
		roleLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(roleLbl);
		
		roleTxt = new JTextField();
		roleTxt.setColumns(10);
		roleTxt.setBounds(153, 104, 207, 20);
		contentPane.add(roleTxt);
		
		JLabel idLbl = new JLabel("ID");
		idLbl.setBounds(117, 42, 17, 17);
		idLbl.setForeground(new Color(128, 0, 0));
		idLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(idLbl);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(45, 273, 450, 2);
		contentPane.add(separator);
		
		
		// Results are displayed based on the order they were stored in the DB and not based on the ascending order of the id
		
		// First Record Button
		JButton firstBtn = new JButton("");
		firstBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.first()) {
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						usernameTxt.setText(rs.getString("USERNAME"));
						roleTxt.setText(rs.getString("ROLE"));
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
						usernameTxt.setText(rs.getString("USERNAME"));
						roleTxt.setText(rs.getString("ROLE"));
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
						usernameTxt.setText(rs.getString("USERNAME"));
						roleTxt.setText(rs.getString("ROLE"));
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
						usernameTxt.setText(rs.getString("USERNAME"));
						roleTxt.setText(rs.getString("ROLE"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		lastBtn.setIcon(new ImageIcon(AdminUpdateDeleteStudentsForm.class.getResource("/resources/Last_Record.png")));
		lastBtn.setBounds(248, 300, 49, 23);
		contentPane.add(lastBtn);
		
		
		// Update Button
				
		JButton udpateBtn = new JButton("Update");
		udpateBtn.setBounds(112, 360, 90, 45);
		
		udpateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sqlSelect = "SELECT PASSWORD FROM USERS WHERE ID = ?";
				String sqlUpdate = "UPDATE USERS SET PASSWORD = ? WHERE ID = ?";
			
            	inputNewPass = String.valueOf(newPassTxt.getPassword()).trim();
				inputConfirmPass = String.valueOf(confirmPassTxt.getPassword()).trim();
				
				if (inputNewPass.equals("") || inputConfirmPass.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter a password!", "PASSWORD", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (!inputNewPass.equals(inputConfirmPass)) {
					JOptionPane.showMessageDialog(null,  "New and confirmation password are not the same. Please try again!", "PASSWORD", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try {
					PreparedStatement psSelect = conn.prepareStatement(sqlSelect);
					PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);

					int userId = Integer.parseInt(idTxt.getText());
					
		            psSelect.setInt(1, userId);
		            ResultSet rs = psSelect.executeQuery();
		            
		            if (rs.next()) {
		            	String storedPassword = rs.getString("PASSWORD");
		            	
		            	if (SecUtil.checkPassword(inputNewPass, storedPassword)) {
		            		JOptionPane.showMessageDialog(null,  "The new password you inserted is the same with your current password. Please insert a new one!", "PASSWORD", JOptionPane.WARNING_MESSAGE);
		            		newPassTxt.setText("");
							confirmPassTxt.setText("");
							return;
		            	}
				
						psUpdate.setString(1, SecUtil.hashPassword(inputNewPass));
						psUpdate.setInt(2, Integer.parseInt(idTxt.getText()));
							
						int numberOfRowsAffected = psUpdate.executeUpdate();
							
						JOptionPane.showMessageDialog(null, "Succesfull update\n" + numberOfRowsAffected + " rows affected", "UPDATE", JOptionPane.PLAIN_MESSAGE);
						newPassTxt.setText("");
						confirmPassTxt.setText("");
		            }
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		
		// Update Button
		
		udpateBtn.setForeground(new Color(26, 37, 179));
		udpateBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		udpateBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(udpateBtn);
		
		
		// Delete Button
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(207, 360, 90, 45);
		
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM USERS WHERE ID = ?";
				int response;
				
				try {
					ps = conn.prepareStatement(sql);
					ps.setInt(1,  Integer.parseInt(idTxt.getText()));
					
					response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος;", "Warning", JOptionPane.YES_NO_OPTION);
					
					if (response == JOptionPane.YES_OPTION) {
						int numberOfRowsAffected = ps.executeUpdate();
						JOptionPane.showMessageDialog(null,  numberOfRowsAffected + " rows deleted successfully", "DELETE", JOptionPane.INFORMATION_MESSAGE);
						Main.getAdminUpdateDeleteUsersForm().setVisible(false);
						Main.getUsersMenu().setVisible(true);
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
		contentPane.add(deleteBtn);
		
		// Close Button
		
		JButton closeBtn = new JButton("Close");
		closeBtn.setBounds(398, 360, 90, 45);
		
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getAdminUpdateDeleteUsersForm().setVisible(false);
				Main.getUsersMenu().setVisible(true);
			}
		});
		
		closeBtn.setForeground(new Color(26, 37, 179));
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		closeBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(closeBtn);
		
		JLabel changePassLbl = new JLabel("Change Password");
		changePassLbl.setBounds(143, 175, 136, 17);
		changePassLbl.setForeground(new Color(128, 0, 0));
		changePassLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(changePassLbl);
		
		JLabel newPassLbl = new JLabel("new Password");
		newPassLbl.setBounds(44, 205, 90, 17);
		newPassLbl.setForeground(new Color(226, 3, 26));
		newPassLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(newPassLbl);
		
		confirmPassTxt = new JPasswordField();
		confirmPassTxt.setBounds(153, 234, 207, 20);
		confirmPassTxt.setColumns(10);
		contentPane.add(confirmPassTxt);
		
		JLabel confirmPassLbl = new JLabel("confirm Password");
		confirmPassLbl.setBounds(26, 236, 110, 17);
		confirmPassLbl.setForeground(new Color(226, 3, 26));
		confirmPassLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(confirmPassLbl);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(45, 158, 450, 2);
		contentPane.add(separator_1);
	}
}