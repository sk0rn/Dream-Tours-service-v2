package application.domain.dto;

import application.domain.transformers.Transformable;
import application.validators.LoginUnique;
import application.validators.PasswordMatches;
import application.validators.ValidPassword;
import application.validators.ValidPhone;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@PasswordMatches
public class RegistrationForm implements Transformable<RegistrationForm> {

    @NotEmpty
	@LoginUnique
    private String login;

	@Email
	@NotEmpty
	private String email;

	@NotEmpty
	@ValidPhone
	private String phone;

	@NotEmpty
    @ValidPassword
	private String password;

	@NotEmpty
	private String repeatPassword;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		RegistrationForm that = (RegistrationForm) o;

		return new EqualsBuilder()
                .append(login, that.login)
				.append(email, that.email)
				.append(password, that.password)
				.append(repeatPassword, that.repeatPassword)
				.append(phone, that.phone)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
                .append(login)
				.append(email)
				.append(password)
				.append(repeatPassword)
				.append(phone)
				.toHashCode();
	}
}
