package simulation.cdr.business;

public class LoginChecker {
	
	public boolean checkLoginPass(String login, String pass, StringBuffer textLogin, StringBuffer textPass) {
		textLogin.delete(0, textLogin.length());
		textPass.delete(0, textPass.length());
		
		if(!login.equals("admin")) {
			textLogin.append("login failed");	
		}
		if(login.isEmpty()) {
			textLogin.delete(0, textLogin.length());
			textLogin.append("login cannot be empty");
		}
		if(!pass.equals("admin")) {
			textPass.append("password failed");
		}
		if(pass.isEmpty()) {
			textPass.delete(0, textPass.length());
			textPass.append("password cannot be empty");
		}
		if(login.equals("admin") && pass.equals("admin")) {
			return true;
		}
		else {
			return false;
		}
		
	}

}
