package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

import Helper.*;//Tum helper class'inin icindekileri kullanmak icin
import Model.BasHekim;

public class LoginGUI extends JFrame {

	private JPanel panel;
	private JTextField HtxtTC;
	private JTextField DtxtTC;
	private JPasswordField HtxtSifre;
	private JPasswordField DtxtSifre;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setTitle("Hastane Otamasyonu");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 504);
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.WHITE);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);

		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(
				"C:\\Users\\mgmet\\OneDrive\\Masa\u00FCst\u00FC\\Anything\\-\\Uyg-resim\\Java_VeriTabani\\logo.png"));
		lblIcon.setBounds(244, 10, 111, 105);
		panel.add(lblIcon);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(10, 151, 566, 305);
		panel.add(tabbedPane);

		JPanel HastaLogIn = new JPanel();
		HastaLogIn.setBackground(Color.WHITE);
		HastaLogIn.setLocation(249, 24);
		tabbedPane.addTab("Hasta Giris", null, HastaLogIn, null);
		HastaLogIn.setLayout(null);

		JLabel HlblTC = new JLabel("T.C. Numaraniz:");
		HlblTC.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		HlblTC.setBounds(37, 37, 141, 33);
		HastaLogIn.add(HlblTC);

		JLabel HlblSifre = new JLabel("Sifre:");
		HlblSifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		HlblSifre.setBounds(37, 128, 141, 33);
		HastaLogIn.add(HlblSifre);

		HtxtTC = new JTextField();
		HtxtTC.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		HtxtTC.setBounds(211, 37, 312, 33);
		HastaLogIn.add(HtxtTC);
		HtxtTC.setColumns(10);

		JButton btnKayit = new JButton("Kayit Ol");
		btnKayit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnKayit.setBounds(34, 180, 232, 44);
		HastaLogIn.add(btnKayit);

		JButton btnGiris = new JButton("Giris Yap");
		btnGiris.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnGiris.setBounds(291, 180, 232, 44);
		HastaLogIn.add(btnGiris);

		HtxtSifre = new JPasswordField();
		HtxtSifre.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		HtxtSifre.setBounds(211, 128, 312, 33);
		HastaLogIn.add(HtxtSifre);

		JPanel DoctorLogIn = new JPanel();
		DoctorLogIn.setBackground(Color.WHITE);
		tabbedPane.addTab("Doktor Giris", null, DoctorLogIn, null);
		DoctorLogIn.setLayout(null);

		JLabel DlblTC = new JLabel("T.C. Numaraniz:");
		DlblTC.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		DlblTC.setBounds(37, 37, 141, 33);
		DoctorLogIn.add(DlblTC);

		JLabel DlblSifre = new JLabel("Sifre:");
		DlblSifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		DlblSifre.setBounds(37, 128, 141, 33);
		DoctorLogIn.add(DlblSifre);

		DtxtTC = new JTextField();
		DtxtTC.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		DtxtTC.setColumns(10);
		DtxtTC.setBounds(211, 37, 312, 33);
		DoctorLogIn.add(DtxtTC);

		JButton btnGiris_1 = new JButton("Giris Yap");
		btnGiris_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (DtxtSifre.getText().length() == 0 || DtxtTC.getText().length() == 0) {
					// JOptionPane.showMessageDialog(null, "Lütfen Kullanici Bilgilerinizi Kontrol
					// Ediniz");
					Helper.showMsg("fill");
				} else {
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while (rs.next()) {
							if (DtxtTC.getText().equals(rs.getString("tcno"))
									&& DtxtSifre.getText().equals(rs.getString("password")))
								;
							BasHekim bhekim = new BasHekim();
							bhekim.setId(rs.getInt("id"));
							bhekim.setName(rs.getString("name"));
							bhekim.setPassword(rs.getString("password"));
							bhekim.setTcno(rs.getString("tcno"));
							bhekim.setType(rs.getString("type"));
							BasHekimGUI bGUI = new BasHekimGUI(bhekim);
							bGUI.setVisible(true);
							dispose();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnGiris_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnGiris_1.setBounds(37, 181, 486, 44);
		DoctorLogIn.add(btnGiris_1);

		DtxtSifre = new JPasswordField();
		DtxtSifre.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		DtxtSifre.setBounds(211, 128, 312, 33);
		DoctorLogIn.add(DtxtSifre);

		JLabel lblHosGeldin = new JLabel("Hastane Y\u00F6netim Sistemine Ho\u015Fgeliniz");
		lblHosGeldin.setHorizontalAlignment(SwingConstants.CENTER);
		lblHosGeldin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblHosGeldin.setBounds(63, 102, 473, 50);
		panel.add(lblHosGeldin);
	}
}
