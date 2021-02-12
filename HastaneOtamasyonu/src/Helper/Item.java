package Helper;

public class Item {
	// Doktor secilecek ama onun id'sini bu yardımcı class'la alıcağız
	private int key;
	private String value;

	@Override
	public String toString() {
		return this.value;
	}

	public Item(int key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
