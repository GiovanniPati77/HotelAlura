package com.java.hotel.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.google.protobuf.Internal.ListAdapter;
import com.java.hotel.clientes.LoginUsuario;
import com.java.hotel.clientes.ReservaCliente;
import com.java.hotel.productoDao.HuespedDAO;
import com.toedter.calendar.JDateChooser;

import antlr.StringUtils;
import controller.ControllerUsuarios;
import ventanasEmergentes.Exito;
import ventanasEmergentes.ValorReserva;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Reservas extends JFrame {

	public JPanel contentPane;
	public JComboBox txtFormaPago;
	public JDateChooser txtFechaE;
	public JDateChooser txtFechaS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservas frame = new Reservas();
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

	public Reservas() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(Reservas.class.getResource("/imagenes/calendario.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 540);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.text);
		panel.setBounds(0, 0, 900, 502);
		contentPane.add(panel);
		panel.setLayout(null);

		txtFechaE = new JDateChooser();
		txtFechaE.setDateFormatString("yyyy-MM-dd");
		txtFechaE.setBounds(88, 167, 235, 33);
		txtFechaE.getCalendarButton().setBackground(Color.WHITE);
		panel.add(txtFechaE);

		JLabel lblNewLabel_1 = new JLabel("Fecha de Check In");
		lblNewLabel_1.setBounds(88, 142, 133, 14);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Fecha de Check Out");
		lblNewLabel_1_1.setBounds(88, 232, 133, 14);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1);

		txtFechaS = new JDateChooser();
		txtFechaS.setDateFormatString("yyyy-MM-dd");
		txtFechaS.setBounds(88, 257, 235, 33);
		txtFechaS.getCalendarButton().setBackground(Color.WHITE);
		panel.add(txtFechaS);

		txtFormaPago = new JComboBox();
		txtFormaPago.setBackground(Color.WHITE);
		txtFormaPago.setBounds(88, 350, 235, 33);
		txtFormaPago.setFont(new Font("Arial", Font.PLAIN, 14));
		txtFormaPago.setModel(new DefaultComboBoxModel(
				new String[] { "Tarjeta de Credito", "Tarjeta de Debito", "Dinero en efectivo" }));
		panel.add(txtFormaPago);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Forma de pago");
		lblNewLabel_1_1_1_1.setBounds(88, 315, 133, 24);
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_4 = new JLabel("Sistema de Reservas");
		lblNewLabel_4.setBounds(108, 93, 199, 42);
		lblNewLabel_4.setForeground(new Color(65, 105, 225));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(lblNewLabel_4);

		JButton btnReservar = new JButton("Continuar");
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ingresarReserva();
			}

		});

		btnReservar.setForeground(new Color(0, 0, 0));
		btnReservar.setBounds(237, 435, 140, 33);
		btnReservar.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/calendario.png")));
		btnReservar.setBackground(Color.WHITE);
		btnReservar.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(btnReservar);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(399, 0, 491, 502);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, -16, 500, 539);
		panel_1.add(lblNewLabel);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/reservas-img-2.png")));

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(15, 6, 104, 107);
		panel.add(lblNewLabel_2);

	}

	private void ingresarReserva() {
		String pagoString = txtFormaPago.getSelectedItem().toString();
		String fechaE = (new SimpleDateFormat("yyyy-MM-dd")).format(txtFechaE.getDate());
		String fechaS = (new SimpleDateFormat("yyyy-MM-dd")).format(txtFechaS.getDate());
		LocalDate dateEntrada = LocalDate.parse(fechaE);
		LocalDate dateSalida = LocalDate.parse(fechaS);
		long numeroDias = ChronoUnit.DAYS.between(dateEntrada, dateSalida);
		String valorTotal = String.format("%.6g%n", numeroDias * 80.000);
		ValorReserva reserva = new ValorReserva("Valor de la Reserva es de : $" + valorTotal);
		reserva.setVisible(true);
		dispose();
		
		if (!reserva.isVisible() == true) {
			RegistroHuesped huesped = new RegistroHuesped();
			huesped.setVisible(true);
		}
		ControllerUsuarios controller = new ControllerUsuarios();
		controller.ingresarReserva(fechaE, fechaS, valorTotal, pagoString);

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

}
