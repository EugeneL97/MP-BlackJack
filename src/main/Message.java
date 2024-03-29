package main;
import java.io.Serial;
import java.io.Serializable;

public class Message implements Serializable {
	private String type;
    private String status;
    private String text;
    @Serial
	private static final long serialVersionUID = 363945L;
	
	public Message() {
		this("undefined", "undefined", "undefined");
	}
	
	public Message(Message message) {
		this(message.getType(), message.getStatus(), message.getText());
	}
	
	public Message(String type, String status, String text) {
		this.type = type;
		this.status = status;
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
