package gr.aueb.cf.schoolpro;

import java.awt.EventQueue;

public class Main {
	
	private static Login loginForm;
	private static AdminMenu adminMenu;
	private static TeachersMenu teachersMenu;
	private static StudentsMenu studentsMenu;
	private static UsersMenu usersMenu;
	private static AdminInsertStudentsForm adminInsertStudentsForm;
	private static AdminInsertTeachersForm adminInsertTeacehrsForm;
	private static AdminUpdateDeleteTeachersForm adminUpdateDeleteTeachersForm;
	private static AdminUpdateDeleteStudentsForm adminUpdateDeleteStudentsForm;
	private static AdminUpdateDeleteUsersForm adminUpdateDeleteUsersForm;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginForm = new Login();
					loginForm.setVisible(true);
					
					adminMenu = new AdminMenu();
					adminMenu.setVisible(false);
					
					teachersMenu = new TeachersMenu();
					teachersMenu.setVisible(false);
					
					studentsMenu = new StudentsMenu();
					studentsMenu.setVisible(false);
					
					usersMenu = new UsersMenu();
					usersMenu.setVisible(false);
					
					adminInsertTeacehrsForm = new AdminInsertTeachersForm();
					adminInsertTeacehrsForm.setVisible(false);
					
					adminInsertStudentsForm = new AdminInsertStudentsForm();
					adminInsertStudentsForm.setVisible(false);
					
					adminUpdateDeleteTeachersForm = new AdminUpdateDeleteTeachersForm();
					adminUpdateDeleteTeachersForm.setVisible(false);
					
					adminUpdateDeleteStudentsForm = new AdminUpdateDeleteStudentsForm();
					adminUpdateDeleteStudentsForm.setVisible(false);
					
					adminUpdateDeleteUsersForm = new AdminUpdateDeleteUsersForm();
					adminUpdateDeleteUsersForm.setVisible(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static Login getLoginForm() {
		return loginForm;
	}

	public static AdminMenu getAdminMenu() {
		return adminMenu;
	}

	public static TeachersMenu getTeachersMenu() {
		return teachersMenu;
	}

	public static StudentsMenu getStudentsMenu() {
		return studentsMenu;
	}
	
	public static UsersMenu getUsersMenu() {
		return usersMenu;
	}
	
	public static AdminInsertStudentsForm getAdminInsertStudentsForm() {
		return adminInsertStudentsForm;
	}
	
	public static AdminInsertTeachersForm getAdminInsertTeacehrsForm() {
		return adminInsertTeacehrsForm;
	}
	
	public static AdminUpdateDeleteTeachersForm getAdminUpdateDeleteTeachersForm() {
		return adminUpdateDeleteTeachersForm;
	}

	public static AdminUpdateDeleteStudentsForm getAdminUpdateDeleteStudentsForm() {
		return adminUpdateDeleteStudentsForm;
	}
	
	public static AdminUpdateDeleteUsersForm getAdminUpdateDeleteUsersForm() {
		return adminUpdateDeleteUsersForm;
	}
	
	
}