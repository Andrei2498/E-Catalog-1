package controller;

import connection.CreateConnection;
import entity.Elev;
import entity.Materie;
import entity.Nota;
import entity.Profesor;
import jdk.nashorn.internal.codegen.CompilerConstants;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class NotaController {
    public List<Nota> getNota(int idElev, int idMaterie){
        List<Nota> nota = new LinkedList<>();

        try(Statement statement = CreateConnection.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from activitate where id_elev = " + idElev
                    + " and id_Materie = " + idMaterie + " and nota is not null")){
            while (resultSet.next()){
                nota.add(new Nota(new ElevController().getById(resultSet.getInt(1)),
                        new MaterieController().getMaterieById(resultSet.getInt(2)),
                        new ProfesorController().getProfesorById(resultSet.getInt(3)),
                        resultSet.getInt(4),
                        resultSet.getObject(5,LocalDate.class)
                        ));
            }
        } catch (SQLException e){
            System.out.println("Exceptie: " + e);
            return nota;
        } catch (NumberFormatException f){
            System.out.println("Exceptie: " + f);
            return nota;
        }
        return nota;
    }

    public List<Nota> getAbsente(int idElev, int idMaterie){
        List<Nota> nota = new LinkedList<>();

        try(Statement statement = CreateConnection.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from activitate where id_elev = " + idElev
                    + " and id_Materie = " + idMaterie + " and nota is null")){
            while (resultSet.next()){
                nota.add(new Nota(new ElevController().getById(resultSet.getInt(1)),
                        new MaterieController().getMaterieById(resultSet.getInt(2)),
                        new ProfesorController().getProfesorById(resultSet.getInt(3)),
                        resultSet.getInt(4),
                        resultSet.getObject(5,LocalDate.class)
                ));
            }
        } catch (SQLException e){
            System.out.println("Exceptie: " + e);
            return nota;
        } catch (NumberFormatException f){
            System.out.println("Exceptie: " + f);
            return nota;
        }
        return nota;
    }

    public List<Nota> getAllNotes(int idMaterie, int idProfesor){
        List<Nota> nota = new LinkedList<>();
        Profesor profesor = new ProfesorController().getProfesorById(idProfesor);
        String functionLogin = "{ ? = call noteout.listnote(?,?) }";
        CallableStatement callableStatement = null;
        try{
            callableStatement = CreateConnection.connection.prepareCall(functionLogin);
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.setInt(2,idMaterie);
            callableStatement.setInt(3,idProfesor);
            callableStatement.execute();
            functionLogin = callableStatement.getString(1);
            String[] prof = functionLogin.split(">");
            for (String string : prof ) {
                String[] aux = string.split("_");
                nota.add(new Nota(new ElevController().getById(Integer.parseInt(aux[0])),
                        profesor.getMaterie(),profesor,Integer.parseInt(aux[1]),LocalDate.now()));
            }
        } catch (SQLException e){
            System.out.println("Exceptie: " + e);
            return nota;
        } catch (NumberFormatException f){
            System.out.println("Exceptie: " + f);
            return nota;
        }
        return nota;
    }
}
