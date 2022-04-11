package kurs;

public abstract class InputText{

	private String name;

	private final String text;
	
	private static final String cannotBeEmptyMessage = "поле не может быть пустым";

	public InputText(String name, String text) {
		this.name = name;
		this.text = text;
	}

	public InputText(String text) {
		this.text = text;
	}

	protected abstract double parse() throws Exception;

	public String getFieldName() {
		return this.name;
	}

	public double getValue() throws Exception {
		if (this.text == "") {
			throw new Exception(InputText.cannotBeEmptyMessage);
		}

		return this.parse();
	}

	public String getText() {
		return text;
	}

}