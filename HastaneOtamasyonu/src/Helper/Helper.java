package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {

	public static void optionPanetxt() {// Joption da buttonların içini değiştirdi
		UIManager.put("OptionPane.cancelButtonText", "İptal");
		UIManager.put("OptionPane.noButtonText", "Hayır");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
	}

	public static void showMsg(String str) {
		String msg;
		optionPanetxt();
		switch (str) {
		case "fill":
			msg = "Lütfen tüm alanlari doldurunuz.";
			break;
		case "success":
			msg = "İşlem başarılı";
			break;
		default:
			msg = str;
			break;
		}

		JOptionPane.showInternalMessageDialog(null, msg, "Mesaj", JOptionPane.DEFAULT_OPTION);
	}

	public static boolean confirm(String str) {
		String msg;
		optionPanetxt();
		switch (str) {

		case "sure":
			msg = "Bu işlemi gerçekleştirme istiyor musun ?";
			break;
		default:
			msg = str;
			break;
		}

		int res = JOptionPane.showConfirmDialog(null, msg, "Dikkat !", JOptionPane.YES_NO_OPTION);

		if (res == 0)
			return true;
		else
			return false;
	}
}
