package controller;

import connection.CreateConnection;
import entity.Liceu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LiceuController {
    public Liceu infoLiceu(int idLiceu){
        Liceu resultat = new Liceu();
        try(Statement statement = CreateConnection.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from liceu where id = " + idLiceu)){
            resultSet.next();
            resultat.setId(resultSet.getInt(1));
            resultat.setTitlu(resultSet.getString(2));
            resultat.setNume(resultSet.getString(3));
            resultat.setJudet(resultSet.getString(4));
        } catch (SQLException e){
            System.out.println("Exceptie Liceu: " + e);
        }
        return resultat;
    }
}
