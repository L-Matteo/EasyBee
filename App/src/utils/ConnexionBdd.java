package utils;

import java.sql.Connection;
import java.sql.DriverManager;

// Singleton
public class ConnexionBdd {
	
	private static ConnexionBdd instance;
	Connection con;
	
	private ConnexionBdd() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/easybee_java","root","root");
			System.out.println("connexion reussie");
		}
		catch(Exception ex) {
			System.out.println(" ERREUR | Problème de connexion à la base de données : " + ex.getMessage());
		}
	}
	
	public static ConnexionBdd getInstance() {
		if (instance == null) {
            synchronized (ConnexionBdd.class) { // Synchronisation pour éviter les conflits en multi-threading
                if (instance == null) {
                    instance = new ConnexionBdd();
                }
            }
        }
		return instance;
	}
	
	public Connection laconnexion() {
		return con;
	}
}
