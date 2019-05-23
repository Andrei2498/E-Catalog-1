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
}
