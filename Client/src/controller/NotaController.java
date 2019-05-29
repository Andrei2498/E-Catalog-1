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

    public List<Nota> getAllAlsente(int idMaterie, int idProfesor){
        List<Nota> nota = new LinkedList<>();
        Profesor profesor = new ProfesorController().getProfesorById(idProfesor);
        String functionLogin = "{ ? = call noteout.litabsente(?,?) }";
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
//    private Elev elev;
//    private Materie materie;
//    private Profesor profesor;
//    private int nota;
//    private LocalDate dataNotare;
    public  List<Nota> getAbsenteElevi(int idElev){
        List<Nota> absente = new LinkedList<>();
        try(Statement statement = CreateConnection.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id_materie,id_profesor,data_notare from activitate where id_elev = " + idElev + " and nota is null")){
            while (resultSet.next()){
                absente.add(new Nota(null,new MaterieController().getMaterieById(resultSet.getInt(1)),new ProfesorController().getProfesorById(2),0,LocalDate.now()));
            }
        } catch (SQLException e){
            System.out.println("Exceptie: " + e);
            return absente;
        }
        return absente;
    }

    public int getNumarAbsenta(int idMaterie, int idElev){
        int result = 0;
        try(Statement statement = CreateConnection.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select count(*) from activitate where id_elev = " + idElev + " and id_materie = " + idMaterie + " and nota is null")){
            resultSet.next();
            result = resultSet.getInt(1);
        } catch (SQLException e){
            return -1;
        }
        return result;
    }

    public int getTotalAbsente(int idElev){
        int result =0;
        try(Statement statement = CreateConnection.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select count(*) from activitate where id_elev = " + idElev + " and nota is null")){
            resultSet.next();
            result = resultSet.getInt(1);
        } catch (SQLException e){
            return -1;
        }
        return result;
    }

    public void insertNota(int idElev, int idMaterie, int idProfesor, int nota, boolean isTeza){
        CallableStatement callableStatement = null;
        String string;
        if (isTeza){
            string = "{ call insertNote(?,?,?,?) }";
        } else {
            string = "{ call insertTeze(?,?,?,?) }";
        }
        try{
            callableStatement = CreateConnection.connection.prepareCall(string);
            callableStatement.setInt(1,nota);
            callableStatement.setInt(2,idElev);
            callableStatement.setInt(3,idMaterie);
            callableStatement.setInt(4,idProfesor);
            callableStatement.execute();
        } catch (SQLException e){
            System.out.println("Exceptie: " + e);
        }
    }

    public void inserreazaAbsenta(int idElev, int idMaterie, int idProfesor){
        CallableStatement callableStatement = null;
        String string = "{ call insertNote(?,?,?,?) }";
        try{
            callableStatement = CreateConnection.connection.prepareCall(string);
            callableStatement.setInt(1,0);
            callableStatement.setInt(2,idElev);
            callableStatement.setInt(3,idMaterie);
            callableStatement.setInt(4,idProfesor);
            callableStatement.execute();
        } catch (SQLException e){
            System.out.println(e);
        }
    }
}
