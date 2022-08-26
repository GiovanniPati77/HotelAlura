package com.java.hotel.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.crypto.Data;

import com.java.hotel.clientes.Huesped;
import com.java.hotel.clientes.ReservaCliente;
import com.java.hotel.productoDao.HuespedDAO;
import com.java.hotel.productoDao.ReservasDAO;

import antlr.collections.List;
import controller.ControllerUsuarios;
import ventanasEmergentes.DatosBusqueda;

import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbReservas;
	private JTable tbHuespedes;
	private DefaultTableModel listarHuespedes;
	private DefaultTableModel listarReservas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void cargarHuesped() {
		java.util.List<Object[]> listaHuespeList = ControllerUsuarios.listarHuesList();
		listaHuespeList.forEach(huesped -> listarHuespedes
				.addRow(new Object[] { huesped[0], huesped[1], huesped[2], huesped[3], huesped[4], huesped[5]}));
		listarHuespedes.getDataVector().remove(listaHuespeList);
	}
	
	private void cargarReserva() {
		java.util.List<Object[]> datosObjects = ControllerUsuarios.listarReservas();
		datosObjects.forEach(cliente -> listarReservas
				.addRow(new Object[] { cliente[0], cliente[1], cliente[2], cliente[3], cliente[4] }));
		
	}

	ControllerUsuarios controller = new ControllerUsuarios();

	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 516);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		txtBuscar = new JTextField();
		txtBuscar.setColumns(10);

		JButton btnBuscar = new JButton("");
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarHuesped();
			}
		});

		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/lupa2.png")));

		JButton btnEditar = new JButton("");
		btnEditar.setForeground(Color.WHITE);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tbReservas.getSelectedColumnCount() != 0) {
					actualizarReservas();
				} else if (tbHuespedes.getSelectedColumnCount() != 0) {
					actualizarHuesped();
				}

			}
		});
		btnEditar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/editar-texto.png")));
		btnEditar.setBackground(Color.WHITE);

		JLabel lblNewLabel_4 = new JLabel("Sistema de Búsqueda");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 24));

		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
		});

		btnSalir.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/cerrar-sesion 32-px.png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.WHITE);

		JButton btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] opciones = { "Aceptar", "Cancelar" };
				int eleccion = JOptionPane.showOptionDialog(rootPane, "Seguro que desea Eliminar",
						"Eliminar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,
						opciones, "Aceptar");
				if (eleccion == JOptionPane.YES_OPTION) {
					if (tbReservas.getSelectedColumnCount() != 0) {
						deleteReservas();
						limpiarTabla(tbReservas, listarReservas);
					} 
					if (tbHuespedes.getSelectedColumnCount() != 0) {
						deleteHuesped();
						limpiarTabla(tbHuespedes, listarHuespedes);
					} 
				}
			}
		});
			
		btnEliminar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/deletar.png")));
		btnEliminar.setBackground(Color.WHITE);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		tbHuespedes = new JTable();
		listarHuespedes = (DefaultTableModel) tbHuespedes.getModel();
		listarHuespedes.addColumn("id");
		listarHuespedes.addColumn("Nombre");
		listarHuespedes.addColumn("Apellido");
		listarHuespedes.addColumn("Fecha Nacimiento");
		listarHuespedes.addColumn("Nacionalidad");
		listarHuespedes.addColumn("Telefono");
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/persona.png")), tbHuespedes,
				null);
		
		cargarHuesped();
		
		tbReservas = new JTable();
		listarReservas = (DefaultTableModel) tbReservas.getModel();
		listarReservas.addColumn("id");
		listarReservas.addColumn("Fecha Entrada");
		listarReservas.addColumn("Fecha Salida");
		listarReservas.addColumn("Valor");
		listarReservas.addColumn("Forma Pago");
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		tbReservas.setFont(new Font("Arial", Font.PLAIN, 14));
		
		cargarReserva();

		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/calendario.png")), tbReservas,
				null);
		panel.setEnabledAt(1, true);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(20)
				.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE).addGap(26)
				.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE).addGap(234)
				.addComponent(txtBuscar, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE).addGap(10)
				.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(5).addComponent(panel,
						GroupLayout.PREFERRED_SIZE, 874, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(666)
						.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(5)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(32).addComponent(lblNewLabel_4,
								GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(75).addComponent(txtBuscar,
								GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(65).addComponent(btnBuscar)))
				.addGap(10).addComponent(panel, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE).addGap(24)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(btnEditar)
						.addComponent(btnEliminar).addComponent(btnSalir))));
		contentPane.setLayout(gl_contentPane);
		setResizable(false);
	}
	
	

	// Funciones

	private boolean tieneFilasElegidas(JTable nombre) {
		return nombre.getSelectedColumnCount() == 0 || nombre.getSelectedRowCount() == 0;
	}

	// Funcion para actualizar huespedes
	private void actualizarHuesped() {
		if (tieneFilasElegidas(tbHuespedes)) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item");
			return;
		}
		Optional.ofNullable(listarHuespedes.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					int idHuesped = (int) listarHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 0);
					String nombre = (String) listarHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 1);
					String apellido = (String) listarHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 2);
					String fechaNacimiento = (String) listarHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 3)
							.toString();
					String nacionalidad = (String) listarHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 4);
					String telefono = (String) listarHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 5);
					String updateHuesped = new HuespedDAO().ActualizarHuesped(idHuesped, nombre, apellido,
							fechaNacimiento, nacionalidad, telefono);

				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));

	}
	

	// Funcion para actualizar Reservas
	private void actualizarReservas() {
		if (tieneFilasElegidas(tbReservas)) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item");
			return;
		}
		Optional.ofNullable(listarReservas.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					int idReserva = (int) listarReservas.getValueAt(tbReservas.getSelectedRow(), 0);
					String fechaEntrada = (String) listarReservas.getValueAt(tbReservas.getSelectedRow(), 1).toString();
					String fechaSalida = (String) listarReservas.getValueAt(tbReservas.getSelectedRow(), 2).toString();
					Integer valor = (Integer) listarReservas.getValueAt(tbReservas.getSelectedRow(), 3);
					String valorString = valor.toString();
					String formaPago = (String) listarReservas.getValueAt(tbReservas.getSelectedRow(), 4);
					String updateReserva = new ReservasDAO().ActualizarReserva(idReserva, fechaEntrada, fechaSalida,
							valorString, formaPago);
				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	}

	// funcion eliminar Reserva
	private void deleteReservas() {
		if (tieneFilasElegidas(tbReservas)) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item");
			return;
		}
		Optional.ofNullable(listarReservas.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					int idReserva = (int) listarReservas.getValueAt(tbReservas.getSelectedRow(), 0);
					String deleteReserva = new ReservasDAO().deleteReserva(idReserva);
				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		
	}
	
	// Funcion para eliminar Huespedes
	private void deleteHuesped() {
		if (tieneFilasElegidas(tbHuespedes)) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item");
			return;
		}
		Optional.ofNullable(listarHuespedes.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
		.ifPresentOrElse(fila -> {
			int idReserva = (int) listarHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 0);
			String deleteHuesped = new HuespedDAO().deleteHuesped(idReserva);
			java.util.List<Object[]> listaHuespeList = ControllerUsuarios.listarHuesList();
			
		}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	}
	
	
	private void limpiarTabla(JTable table,DefaultTableModel model) {
			   int[] rows = table.getSelectedRows();
			   for(int i=0;i<rows.length;i++){
			     model.removeRow(rows[i]-i);
			   }
			}


	private void buscarHuesped() {
		listarHuespedes.getDataVector().removeAllElements();
		listarHuespedes.fireTableDataChanged();

		java.util.List<Object[]> buscar = new ControllerUsuarios().buscarHuesped(txtBuscar.getText());
		buscar.forEach(buscarHuesped -> listarHuespedes.addRow(new Object[] { buscarHuesped[0], buscarHuesped[1],
				buscarHuesped[2], buscarHuesped[3], buscarHuesped[4], buscarHuesped[5] }));

	}

}
