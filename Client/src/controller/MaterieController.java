package controller;

import connection.CreateConnection;
import entity.Materie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MaterieController {
    public Materie getMaterieById(int idMaterie){
        Materie materie = new Materie();
        try(Statement statement = CreateConnection.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from materii where id = " + idMaterie)){
            resultSet.next();
            materie.setId(resultSet.getInt(1));
            materie.setNume(resultSet.getString(2));
            materie.setClasa(Integer.parseInt(resultSet.getString(3)));
            materie.setProfil(resultSet.getString(4));
        } catch (SQLException e){
            System.out.println("Exceptie: " + e);
        }
        return materie;
    }

    public int getSpecificMateire(int clasaElev, String numeMaterie, String profil){
        int idMaterie = 0;
        try(Statement statement = CreateConnection.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id from materii where clasa = " + clasaElev + " and nume like \'" + numeMaterie + "\' and profil like \'" + profil + "\'")){
            resultSet.next();
            idMaterie = resultSet.getInt(1);
        } catch (SQLException e){
            return 0;
        } catch (NumberFormatException f){
            return 0;
        }
        return idMaterie;
    }
}
