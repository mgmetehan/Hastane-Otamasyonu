package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Clinic;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateClinicGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtClinicName;
	private static Clinic clinic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(clinic);
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
	public UpdateClinicGUI(Clinic clinic) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 284, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnUpdateClinic = new JButton("D\u00FCzenle");
		btnUpdateClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("sure")) {
					try {
						clinic.updateClinic(clinic.getId(), txtClinicName.getText());
						dispose();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}

			}
		});
		btnUpdateClinic.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		btnUpdateClinic.setBounds(10, 101, 244, 40);
		contentPane.add(btnUpdateClinic);

		txtClinicName = new JTextField();
		txtClinicName.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		txtClinicName.setColumns(10);
		txtClinicName.setBounds(10, 64, 244, 27);
		txtClinicName.setText(clinic.getName());
		contentPane.add(txtClinicName);

		JLabel lblPoliklinikAd = new JLabel("Poliklinik Ad\u0131");
		lblPoliklinikAd.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		lblPoliklinikAd.setBounds(10, 27, 244, 27);
		contentPane.add(lblPoliklinikAd);
	}
}
