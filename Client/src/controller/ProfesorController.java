package controller;

import connection.CreateConnection;
import entity.Elev;
import entity.Profesor;

import java.sql.*;
import java.util.ArrayList;

public class ProfesorController {
    public Profesor login(String usename, String password){
        String functionLogin = "{ ? = call LOGIN(?) }";
        Profesor profesor  = new Profesor();
        CallableStatement callableStatement = null;
        try{
            callableStatement = CreateConnection.connection.prepareCall(functionLogin);
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.setString(2,usename + ' ' + password);
            callableStatement.execute();
            functionLogin = callableStatement.getString(1);
            String[] prof = functionLogin.split("_");
            profesor.setId(Integer.parseInt(prof[0]));
            profesor.setNume(prof[1]);
            profesor.setPrenume(prof[2]);
            profesor.setGen(prof[3]);
            profesor.setVarsta(Integer.parseInt(prof[4]));
            profesor.setAdresa(prof[5]);
            profesor.setNr_telefon(prof[6]);
            profesor.setEmail(prof[7]);
            profesor.setLiceu(new LiceuController().infoLiceu(Integer.parseInt(prof[8])));
            profesor.setMaterie(new MaterieController().getMaterieById(Integer.parseInt(prof[9])));
        } catch (SQLException e){
            System.out.println("Exceptie " + e);
        } catch (NumberFormatException e){
            return profesor;
        }

        return profesor;
    }

    public ArrayList<Elev> getAllMyElevi(Profesor profesor){
        ArrayList<Elev> elevs = new ArrayList<>();
        try(Statement statement = CreateConnection.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from ELEVI where CLASA like \'" + profesor.getMaterie().getClasa() + "%\' and id_liceu = " + profesor.getLiceu().getId())){
            while (resultSet.next()){
                elevs.add(new Elev(Integer.parseInt(resultSet.getString(1)),resultSet.getString(2),resultSet.getString(3),
                        resultSet.getString(4),Integer.parseInt(resultSet.getString(5)),resultSet.getString(6),
                        resultSet.getString(7),resultSet.getString(8),new LiceuController().infoLiceu(Integer.parseInt(resultSet.getString(9))),
                        resultSet.getString(10),resultSet.getString(11)));
            }
        } catch (SQLException e){
            System.out.println("Exceptie: " + e);
        }

        return elevs;
    }

    public Profesor getProfesorById(int idProfesor){
        Profesor profesor = new Profesor();
        try(Statement statement = CreateConnection.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from profesori where id = " + idProfesor)){
                resultSet.next();
                profesor.setId(resultSet.getInt(1));
                profesor.setNume(resultSet.getString(2));
                profesor.setPrenume(resultSet.getString(3));
                profesor.setGen(resultSet.getString(4));
                profesor.setVarsta(resultSet.getInt(5));
                profesor.setAdresa(resultSet.getString(6));
                profesor.setNr_telefon(resultSet.getString(7));
                profesor.setEmail(resultSet.getString(8));
                profesor.setMaterie(new MaterieController().getMaterieById(resultSet.getInt(10)));
                profesor.setLiceu(new LiceuController().infoLiceu(resultSet.getInt(9)));
        } catch (SQLException e){
            return profesor;
        }
        return profesor;
    }
}
//    private int id;
//    private String nume;
//    private String prenume;
//    private String gen;
//    private int varsta;
//    private String adresa;
//    private String nr_telefon;
//    private String email;
//    private Materie materie;
//    private Liceu liceu;
