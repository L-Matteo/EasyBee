package main;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionBdd {
	Connection con;
	public ConnexionBdd() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/easybee","user_easybee","mdp");
			System.out.println("connexion reussie");
		}
		catch(Exception ex) {
			System.out.println(" ERREUR | Problème de connexion à la base de données : " + ex.getMessage());
		}
	}
	public Connection laconnexion() {
		return con;
	}
}
