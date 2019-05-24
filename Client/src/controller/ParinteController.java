package controller;

import connection.CreateConnection;
import entity.Parinte;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class ParinteController {
    public Parinte login(String username, String password){
        String function = "{ ? = call LOGIN(?) }";
        Parinte parinte = new Parinte();
        CallableStatement callableStatement = null;
        try{
            callableStatement = CreateConnection.connection.prepareCall(function);
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.setString(2,username + ' ' + password);
            callableStatement.execute();
            function = callableStatement.getString(1);
            String[] parent = function.split("_");
            parinte.setId(Integer.parseInt(parent[0]));
            parinte.setNume(parent[1]);
            parinte.setPrenume(parent[2]);
            parinte.setGen(parent[3]);
            parinte.setVarsta(Integer.parseInt(parent[4]));
            parinte.setAdresa(parent[5]);
            parinte.setNr_telefon(parent[6]);
            parinte.setEmail(parent[7]);
            parinte.setElev(new ElevController().getById(Integer.parseInt(parent[8])));
        } catch (SQLException e){
            return parinte;
        } catch (NumberFormatException f){
            return parinte;
        }
        return parinte;
    }
}
