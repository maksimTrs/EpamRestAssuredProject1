package pojo;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonInclude(JsonInclude.Include.NON_NULL)
/*@Getter
@Setter
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_DEFAULT)
//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIgnoreProperties(allowSetters = true, value = {"collaborative"})
@JsonInclude(JsonInclude.Include.USE_DEFAULTS)*/
public class UserItem{
	private String website;
	private Address address;
	private String phone;
	private String name;
	private Company company;
	private int id;
	private String email;
	private String username;
}