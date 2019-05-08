package mum.pmp.mstore;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import mum.pmp.mstore.controller.profile.PasswordResetController;
import mum.pmp.mstore.model.Password;
import mum.pmp.mstore.service.ResetPasswordService;

public class TestResetPassword extends MStoreApplicationTests{

	@Test
	public void testResetPassword()
	{
		//PasswordResetController passwordReset = new PasswordResetController();
		//BindingResult bindingResult = new BeanPropertyBindingResult(null, null);
		ResetPasswordService resetPasswordService = new ResetPasswordService();
		Password password = new Password();
		password.setPassword("123");
		password.setToken("5040");
		
		String result = resetPasswordService.resetPassword(password);
		assertEquals("redirect:/login", result);
	}
}
