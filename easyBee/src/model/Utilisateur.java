package main;

public class Utilisateur {
	private String login;
	private String mdp;
	private int role;
	
	public Utilisateur(String login, String mdp, int role) {
		this.login = login;
		this.mdp = mdp;
		this.role = role;
	}
	
	public String getLogin() {return login;}
	public void setLogin(String login) {this.login = login;}
	public String getMdp() {return mdp;}
	public void setMdp(String mdp) {this.mdp = mdp;}
	public int getRole() {return role;}
	public void setRole(int role) {this.role = role;}
	
}
