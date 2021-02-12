package View;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.BasHekim;
import Model.Clinic;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

import Helper.*;
import javax.swing.JComboBox;

public class BasHekimGUI extends JFrame {
	static BasHekim bashekim = new BasHekim();
	Clinic clinic = new Clinic();
	private JPanel panel;
	private JTextField txtad;
	private JTextField txtTc;
	private JTextField txtSifte;
	private JTextField txtid;
	private JTable tableDoctor;

	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	private JTextField txtClinicName;
	private JTable tableClinic;
	private JTable tableWorker;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JPopupMenu clinicMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BasHekimGUI frame = new BasHekimGUI(bashekim);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public BasHekimGUI(BasHekim bashekim) throws SQLException {
		// DoctorModel
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		colDoctorName[2] = "TC No";
		colDoctorName[3] = "Sifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}

		// WorkerModel
		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "ID";
		colWorker[1] = "Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];

		// Clinic Model
		clinicModel = new DefaultTableModel();
		Object[] colClinic = new Object[2];
		colClinic[0] = "ID";
		colClinic[1] = "Poliklinik Adı";
		clinicModel.setColumnIdentifiers(colClinic);
		clinicData = new Object[2];
		for (int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);
		}

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 654);
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);

		JLabel lblHosgeldiniz = new JLabel("Hosgeldiniz, Sayın" + bashekim.getName());
		lblHosgeldiniz.setBounds(10, 10, 298, 43);
		lblHosgeldiniz.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		panel.add(lblHosgeldiniz);

		JButton btnExit = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnExit.setBounds(582, 19, 139, 43);
		btnExit.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		panel.add(btnExit);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.MAGENTA);
		tabbedPane.setBounds(10, 90, 726, 529);
		panel.add(tabbedPane);

		JPanel panelDoctor = new JPanel();
		panelDoctor.setForeground(Color.WHITE);
		panelDoctor.setBackground(Color.WHITE);
		tabbedPane.addTab("Doktor Yönetimi", null, panelDoctor, null);
		panelDoctor.setLayout(null);

		JLabel lblAd = new JLabel("Ad Soyad");
		lblAd.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		lblAd.setBounds(558, 21, 151, 27);
		panelDoctor.add(lblAd);

		txtad = new JTextField();
		txtad.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		txtad.setBounds(558, 58, 151, 27);
		panelDoctor.add(txtad);
		txtad.setColumns(10);

		JLabel lbltc = new JLabel("T.C. No");
		lbltc.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		lbltc.setBounds(558, 112, 151, 27);
		panelDoctor.add(lbltc);

		txtTc = new JTextField();
		txtTc.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		txtTc.setColumns(10);
		txtTc.setBounds(558, 149, 151, 27);
		panelDoctor.add(txtTc);

		JLabel lblSifre = new JLabel("Şifre");
		lblSifre.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		lblSifre.setBounds(558, 198, 151, 27);
		panelDoctor.add(lblSifre);

		txtSifte = new JTextField();
		txtSifte.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		txtSifte.setColumns(10);
		txtSifte.setBounds(558, 235, 151, 27);
		panelDoctor.add(txtSifte);

		JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtad.getText().length() == 0 || txtSifte.getText().length() == 0
						|| txtTc.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = bashekim.addDoctor(txtTc.getText(), txtSifte.getText(), txtad.getText());
						if (control) {
							Helper.showMsg("success");
							txtad.setText(null);
							txtTc.setText(null);
							txtSifte.setText(null);
							updateDoctorModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		btnEkle.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		btnEkle.setBounds(557, 289, 154, 40);
		panelDoctor.add(btnEkle);

		JLabel lblid = new JLabel("Kullanıcı ID");
		lblid.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		lblid.setBounds(560, 355, 151, 27);
		panelDoctor.add(lblid);

		txtid = new JTextField();
		txtid.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		txtid.setColumns(10);
		txtid.setBounds(560, 392, 151, 27);
		panelDoctor.add(txtid);

		JButton lblDelete = new JButton("Sil");
		lblDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtid.getText().length() == 0) {
					Helper.showMsg("Lütfen geçerli bir doktor şeçiniz!");
				} else {
					if (Helper.confirm("sure")) {

						int selectID = Integer.valueOf(txtid.getText());
						boolean control;
						try {
							control = bashekim.DeleteDoctor(selectID);
							if (control) {
								Helper.showMsg("success");
								txtid.setText(null);
								updateDoctorModel();
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		lblDelete.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		lblDelete.setBounds(558, 452, 151, 40);
		panelDoctor.add(lblDelete);

		JScrollPane scrolDoctor = new JScrollPane();
		scrolDoctor.setBounds(10, 10, 539, 482);
		panelDoctor.add(scrolDoctor);

		tableDoctor = new JTable(doctorModel);
		tableDoctor.setBackground(Color.WHITE);
		scrolDoctor.setViewportView(tableDoctor);

		tableDoctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					txtid.setText(tableDoctor.getValueAt(tableDoctor.getSelectedRow(), 0).toString());
				} catch (Exception e2) {
				}
			}
		});

		tableDoctor.getModel().addTableModelListener(new TableModelListener() {
			// Frame de bir kullanıcı değerini değiştirsen bunu databasede güncelliyor
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer.valueOf(tableDoctor.getValueAt(tableDoctor.getSelectedRow(), 0).toString());
					String selectName = tableDoctor.getValueAt(tableDoctor.getSelectedRow(), 1).toString();
					String selectTcno = tableDoctor.getValueAt(tableDoctor.getSelectedRow(), 2).toString();
					String selectPass = tableDoctor.getValueAt(tableDoctor.getSelectedRow(), 0).toString();

					try {
						boolean control = bashekim.updateDoctor(selectID, selectTcno, selectPass, selectName);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		});

		JPanel panelClinic = new JPanel();
		panelClinic.setBackground(Color.WHITE);
		tabbedPane.addTab("Poliklinikler", null, panelClinic, null);
		panelClinic.setLayout(null);

		JScrollPane scrollClinic = new JScrollPane();
		scrollClinic.setBounds(10, 10, 255, 482);
		panelClinic.add(scrollClinic);

		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selID = Integer.valueOf(tableClinic.getValueAt(tableClinic.getSelectedRow(), 0).toString());
				Clinic selectClinic = clinic.getFetch(selID);
				UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectClinic);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				// pencere kapatıldığında yazıyı updatele yapıyoruz
				updateGUI.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						try {
							updateClinicModel();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		});

		deleteMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("sure")) {
					int selID = Integer.valueOf(tableClinic.getValueAt(tableClinic.getSelectedRow(), 0).toString());
					try {
						if (clinic.DeleteClinic(selID)) {
							Helper.showMsg("success");
							updateClinicModel();
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e2) {
						System.out.println(e2);
					}
				}
			}
		});

		// scrolpanel de tıkladığımız yer
		tableClinic = new JTable(clinicModel);
		tableClinic.setComponentPopupMenu(clinicMenu);
		tableClinic.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = tableClinic.rowAtPoint(point);
				tableClinic.setRowSelectionInterval(selectedRow, selectedRow);

			}
		});
		scrollClinic.setViewportView(tableClinic);

		txtClinicName = new JTextField();
		txtClinicName.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		txtClinicName.setColumns(10);
		txtClinicName.setBounds(275, 47, 171, 27);
		panelClinic.add(txtClinicName);

		JLabel lblPoliAd = new JLabel("Poliklinik Adı");
		lblPoliAd.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		lblPoliAd.setBounds(275, 10, 151, 27);
		panelClinic.add(lblPoliAd);

		JButton btnEkleClinic = new JButton("Ekle");
		btnEkleClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtClinicName.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						if (clinic.addClinic(txtClinicName.getText())) {
							Helper.showMsg("success");
							txtClinicName.setText(null);
							updateClinicModel();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}

			}
		});
		btnEkleClinic.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		btnEkleClinic.setBounds(275, 84, 171, 40);
		panelClinic.add(btnEkleClinic);

		JScrollPane scrollWorker = new JScrollPane();
		scrollWorker.setBounds(456, 10, 255, 482);
		panelClinic.add(scrollWorker);

		tableWorker = new JTable();
		scrollWorker.setViewportView(tableWorker);

		JComboBox cboxSelectDoctor = new JComboBox();
		// Doktor listesini cbox'a ekledi
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			cboxSelectDoctor.addItem(
					new Item(bashekim.getDoctorList().get(i).getId(), bashekim.getDoctorList().get(i).getName()));
		}
		cboxSelectDoctor.addActionListener(e -> {
			// ComboBox'ı dinliyor eğer comboBox'ta bir değişiklik yaparsak bunu görüyor
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + ":" + item.getValue());
		});
		cboxSelectDoctor.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		cboxSelectDoctor.setBounds(275, 375, 171, 40);
		panelClinic.add(cboxSelectDoctor);

		JButton btnAddWorker = new JButton("Ekle");
		btnAddWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = tableClinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = tableClinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicId = Integer.valueOf(selClinic);
					Item doctorItem = (Item) cboxSelectDoctor.getSelectedItem();
					try {
						boolean control = bashekim.addWorker(doctorItem.getKey(), selClinicId);
						if (control) {
							Helper.showMsg("success");
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}

				} else {
					Helper.showMsg("Lütfen bir poliklinik şeçiniz!");
				}
			}

		});
		btnAddWorker.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		btnAddWorker.setBounds(275, 439, 171, 40);
		panelClinic.add(btnAddWorker);

		JLabel lblPoliAd_1 = new JLabel("Poliklinik Adı");
		lblPoliAd_1.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		lblPoliAd_1.setBounds(275, 184, 151, 27);
		panelClinic.add(lblPoliAd_1);

		JButton btnWorkerSelect = new JButton("Seç");
		btnWorkerSelect.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		btnWorkerSelect.setBounds(275, 221, 171, 40);
		panelClinic.add(btnWorkerSelect);

	}

	// Veri girdikçe datayi yeniliyor
	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) tableDoctor.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}

	}

	public void updateClinicModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) tableClinic.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);
		}
	}
}
