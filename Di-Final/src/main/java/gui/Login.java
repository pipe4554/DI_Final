package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import javax.swing.Box;



public class Login extends JFrame{

	private JPanel jpanel;
	private boolean passState = true;

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login(args);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public Login(final String[] args) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		jpanel = new JPanel();
		jpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(jpanel);

		JLabel lbluser = new JLabel("Usuario: ");
		JTextField user =  new JTextField(10);
		
		JLabel lblpassword = new JLabel("Contraseña: ");
		JPasswordField password = new JPasswordField(10);
		
		JButton viewPass = new JButton("Mostrar contraseña");
		viewPass.addActionListener(new ActionListener() {
			//Alterna las vistas de las contraseña
			char i = password.getEchoChar();
			@Override
			public void actionPerformed(ActionEvent e) {
				if(passState) {
					password.setEchoChar((char)0);
					//System.out.println("muestra contrseña");
					passState = false;
				}else {
					password.setEchoChar(i);
					//System.out.println("escondo contraseña");
					passState = true;
				}
			}
		});
		
		JButton login = new JButton("Iniciar sesión");
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Main.main(args);
				
			}
		});
		
		Box userBox = Box.createHorizontalBox();
		userBox.add(lbluser);
		userBox.add(Box.createHorizontalStrut(10));
		userBox.add(user);
		
		Box passBox = Box.createHorizontalBox();
		passBox.add(lblpassword);
		userBox.add(Box.createHorizontalStrut(20));
		passBox.add(password);
		userBox.add(Box.createHorizontalStrut(20));
		passBox.add(viewPass);
		
		Box logBox = Box.createHorizontalBox();
		logBox.add(login);
		
		Box mainBox = Box.createVerticalBox();
		mainBox.add(userBox);
		mainBox.add(passBox);
		mainBox.add(logBox);
		
		jpanel.add(mainBox, BorderLayout.CENTER);
//		GroupLayout panel = new GroupLayout(jpanel);
//		panel.setHorizontalGroup(
//			panel.createParallelGroup(Alignment.CENTER)
//				.addGroup(
//						panel.createSequentialGroup()
//							.addGap(280)
//							.addComponent(lbluser)
//							.addPreferredGap(ComponentPlacement.RELATED, 300, Short.MAX_VALUE)
//							.addComponent(user)
//							.addGap(280)
//				.addGroup(
//						panel.createSequentialGroup()
//							.addGap(280)
//							.addComponent(lblpassword)
//							.addPreferredGap(ComponentPlacement.RELATED, 300, Short.MAX_VALUE)
//							.addComponent(password)
//							.addPreferredGap(ComponentPlacement.RELATED, 300, Short.MAX_VALUE)
//							.addComponent(viewPass)
//							.addGap(280))
//				.addGroup(
//						panel.createSequentialGroup()
//							.addGap(280)
//							.addComponent(login)
//							.addGap(280))));
//		
//
//		panel.setVerticalGroup(
//			panel.createParallelGroup(Alignment.CENTER)
//				.addGroup(
//					panel.createSequentialGroup()
//						.addGap(250)
//						.addGroup(
//								panel.createParallelGroup(Alignment.CENTER)
//								.addComponent(lbluser)
//								.addComponent(lblpassword))
//						.addContainerGap(289, Short.MAX_VALUE))
//				.addGroup(
//					panel.createSequentialGroup()
//						.addGap(250)
//						.addGroup(
//							panel.createParallelGroup(Alignment.CENTER)
//								.addComponent(user)
//								.addComponent(password)
//								.addComponent(login))
//						.addContainerGap(289, Short.MAX_VALUE))
//				.addGroup(
//					panel.createSequentialGroup()
//						.addGap(250)
//						.addGroup(
//								panel.createParallelGroup(Alignment.CENTER)
//								.addComponent(viewPass))
//						.addContainerGap(289, Short.MAX_VALUE)));
//					
//		jpanel.setLayout(panel);
		
	}

	

}
