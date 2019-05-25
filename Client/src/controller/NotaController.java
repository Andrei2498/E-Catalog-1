package controller;

import connection.CreateConnection;
import entity.Elev;
import entity.Nota;
import entity.Profesor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
