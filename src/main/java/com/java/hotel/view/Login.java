package com.java.hotel.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.invoke.StringConcatFactory;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.java.hotel.clientes.LoginUsuario;
import com.java.hotel.clientes.ReservaCliente;
import com.java.hotel.productoDao.HuespedDAO;

import controller.ControllerUsuarios;
import ventanasEmergentes.Bienvenido;

import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Color;

public class Login extends JFrame {

	public JPanel contentPane;
	public JTextField txtUsuario;
	public JPasswordField txtContrasena;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/imagenes/perfil-del-usuario.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 538);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/imagenes/hotel.png")));
		lblNewLabel.setBounds(-53, 0, 422, 499);
		contentPane.add(lblNewLabel);

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(409, 181, 234, 33);
		contentPane.add(txtUsuario);

		JLabel lblNewLabel_1_1_1 = new JLabel("Usuário");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(409, 156, 57, 14);
		contentPane.add(lblNewLabel_1_1_1);

		txtContrasena = new JPasswordField();
		txtContrasena.setBounds(409, 261, 234, 33);
		contentPane.add(txtContrasena);
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setIcon(new ImageIcon(Login.class.getResource("/imagenes/persona.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reservas reservas = new Reservas();
				reservas.setVisible(true);
				dispose();
				ControllerUsuarios loginController = new ControllerUsuarios();
				loginController.ingrearCliente(txtUsuario.getText(), txtContrasena.getText());
				limpiarFormulario();
			}
		});
		btnNewButton.setBounds(540, 322, 117, 33);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Contraseña");
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(409, 236, 133, 14);
		contentPane.add(lblNewLabel_1_1_1_1);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setIcon(new ImageIcon(Login.class.getResource("/imagenes/perfil-del-usuario.png")));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerUsuarios loginController = new ControllerUsuarios();
				if (loginController.recuperarId(txtUsuario.getText(), txtContrasena.getText()) == true) {
					Bienvenido bienvenido = new Bienvenido("Bienvenido a Hotel Alura");
					bienvenido.setVisible(true);
					dispose();
					
				} else {
					JOptionPane.showMessageDialog(null, "Nombre de Usuario o Contraseña incorrecta");
				}
			}
		});
		btnLogin.setBounds(395, 322, 121, 33);
		contentPane.add(btnLogin);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setIcon(new ImageIcon(Login.class.getResource("/imagenes/cerrar-24px.png")));
		btnCancelar.setBounds(470, 388, 103, 33);
		contentPane.add(btnCancelar);

		JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setForeground(new Color(0, 191, 255));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 35));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Genesys\\Documents\\imagenesAluraHotel\\Ha-100px.png"));
		lblNewLabel_1.setBounds(470, 46, 103, 94);
		contentPane.add(lblNewLabel_1);

	}

	private void limpiarFormulario() {
		this.txtUsuario.setText("");
		this.txtContrasena.setText("");
	}

	private void verificarLogin() {
		if (txtUsuario.getText().isBlank() || txtContrasena.getText().isBlank()) {
			JOptionPane.showMessageDialog(this, "Los campos Nombre y Descripción son requeridos.");
			return;
		}
		LoginUsuario loginUsuario = new LoginUsuario();
		loginUsuario.setUsuarioLogin(txtUsuario.getText());
		loginUsuario.setContraseniaLogin(txtContrasena.getText());

	}
}
