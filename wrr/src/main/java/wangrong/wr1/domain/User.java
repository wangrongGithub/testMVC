package wangrong.wr1.domain;

public class User {
	private int id;
	private String name;
	public User() {
		// TODO Auto-generated constructor stub
	
	}
	
	
	public User(int i, String string) {
		// TODO Auto-generated constructor stub
		id=i;
		name=string;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "么么哒id"+id+"name"+name;
	}
	
	

}
