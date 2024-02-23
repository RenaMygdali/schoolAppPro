package gr.aueb.cf.schoolpro;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UsersMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameTxt;
	private String username= "";

	/**
	 * Create the frame.
	 */
	public UsersMenu() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				usernameTxt.setText("");
			}
		});
		
		setTitle("Αναζήτηση Χρήστη");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 414, 290);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(234, 234, 234));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBounds(72, 30, 268, 133);
		searchPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(searchPanel);
		searchPanel.setLayout(null);
		
		usernameTxt = new JTextField();
		usernameTxt.setBounds(60, 45, 155, 23);
		searchPanel.add(usernameTxt);
		usernameTxt.setColumns(10);
		
		JLabel userNameLbl = new JLabel("Username");
		userNameLbl.setBounds(95, 11, 84, 23);
		userNameLbl.setForeground(new Color(205, 48, 20));
		userNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 19));
		searchPanel.add(userNameLbl);
		
		
		// Search Button
		JButton searchUserBtn = new JButton("Αναζήτηση");
		searchUserBtn.setBounds(87, 89, 100, 33);
		
		searchUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getUsersMenu().setVisible(false);
				Main.getAdminUpdateDeleteUsersForm().setVisible(true);
				username = usernameTxt.getText().trim();
			}
		});
		
		searchUserBtn.setForeground(Color.BLUE);
		searchUserBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		searchPanel.add(searchUserBtn);
		
		
		// Close Button
		JButton closeBtn = new JButton("Close");
		closeBtn.setBounds(240, 192, 100, 33);
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getUsersMenu().setVisible(false);
				Main.getAdminMenu().setVisible(true);
			}
		});
		closeBtn.setForeground(Color.BLUE);
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(closeBtn);
	}
	
	public String getUsername() {
		return username;
	}
}
