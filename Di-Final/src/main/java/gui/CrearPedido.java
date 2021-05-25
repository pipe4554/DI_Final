package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.*;
import models.*;
import models.Pedido.PedidoBuilder;
import exceptions.*;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.JButton;


public class CrearPedido extends JFrame {

	PedidoDAO pd = new PedidoDAO();
	
	private JPanel panel;
	private JTextField tf_idpedido;
	private JTextField tf_idcliente;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearPedido frame = new CrearPedido(args);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CrearPedido(final String [] args) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panel);

		JLabel lblTitulo = new JLabel("Crear Pedido");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Agency FB", Font.BOLD, 20));

		JLabel lblIDPedido = new JLabel("ID Pedido");
		lblIDPedido.setFont(new Font("Agency FB", Font.BOLD, 14));

		JLabel lblIDCliente = new JLabel("ID Cliente");
		lblIDCliente.setFont(new Font("Agency FB", Font.BOLD, 14));

		JLabel lblComentarios = new JLabel("Comentarios");
		lblComentarios.setFont(new Font("Agency FB", Font.BOLD, 14));

		final JTextArea txtrComentarios = new JTextArea();

		tf_idpedido = new JTextField();
		tf_idpedido.setColumns(15);
		tf_idpedido.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
					e.consume();
				}
			}
		});

		tf_idcliente = new JTextField();
		tf_idcliente.setColumns(10);
		tf_idcliente.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

				// Verificar si la tecla pulsada no es un digito
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /* corresponde a BACK_SPACE */)) {
					e.consume(); // ignorar el evento de teclado
				}
			}
		});

		JLabel fechaPedido = new JLabel("Fecha de creacion");
		fechaPedido.setFont(new Font("Agency FB", Font.BOLD, 14));

		JLabel fechaEsperada = new JLabel("Fecha esperada");
		fechaEsperada.setFont(new Font("Agency FB", Font.BOLD, 14));

		JLabel fechaEntrega = new JLabel("Fecha de entrega");
		fechaEntrega.setFont(new Font("Agency FB", Font.BOLD, 14));

		JLabel estado = new JLabel("Estado");
		estado.setFont(new Font("Agency FB", Font.BOLD, 14));

		JSpinner spinnerFechaPedido = new JSpinner();
		spinnerFechaPedido.setModel(
				new SpinnerDateModel(new Date(1606863600000L), new Date(1606863600000L), null, Calendar.DAY_OF_YEAR));

		JSpinner spinnerFechaEsperada = new JSpinner();
		spinnerFechaEsperada.setModel(
				new SpinnerDateModel(new Date(1606863600000L), new Date(1606863600000L), null, Calendar.DAY_OF_YEAR));

		JSpinner spinnerFechaEntrega = new JSpinner();
		spinnerFechaEntrega.setModel(
				new SpinnerDateModel(new Date(1606863600000L), new Date(1606863600000L), null, Calendar.DAY_OF_MONTH));

		JComboBox<String> comboBox_estado = new JComboBox<String>();
		comboBox_estado.setFont(new Font("Agency FB", Font.PLAIN, 14));
		comboBox_estado.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Entregado", "Facturando" }));

		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Pedido p = new  Pedido.PedidoBuilder(5).setFecha_creacion((Date) spinnerFechaPedido.getValue()).
							setFecha_entrega((Date) spinnerFechaEntrega.getValue()).build();
					pd.save(p);

					txtrComentarios.setText(null);
					tf_idcliente.setText(null);
					tf_idpedido.setText(null);

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Agency FB", Font.BOLD, 16));
		
		JButton btnNewButton_1 = new JButton("Volver al menu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Main.main(args);
			}
		});
		btnNewButton_1.setFont(new Font("Agency FB", Font.BOLD, 16));
		
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(panel);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 599, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblIDPedido, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(tf_idpedido, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblComentarios, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(txtrComentarios, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblIDCliente, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(tf_idcliente, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)))
								.addGap(40)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(fechaEntrega, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(fechaEsperada, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(fechaPedido)
												.addPreferredGap(ComponentPlacement.RELATED)))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(spinnerFechaEsperada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(spinnerFechaEntrega, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addComponent(spinnerFechaPedido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(estado, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(comboBox_estado, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton_1)
							.addPreferredGap(ComponentPlacement.RELATED, 410, Short.MAX_VALUE)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIDPedido, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(tf_idpedido, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(fechaPedido, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIDCliente, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(tf_idcliente, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinnerFechaPedido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(spinnerFechaEsperada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(fechaEsperada, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(44)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblComentarios, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtrComentarios, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox_estado, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(estado, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(fechaEntrega, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addComponent(spinnerFechaEntrega, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(21))
		);
		panel.setLayout(gl_contentPane);
	}
}