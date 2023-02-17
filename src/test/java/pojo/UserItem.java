package pojo;

import lombok.Data;

@Data
public class UsersMainItem{
	private String website;
	private Address address;
	private String phone;
	private String name;
	private Company company;
	private int id;
	private String email;
	private String username;
}