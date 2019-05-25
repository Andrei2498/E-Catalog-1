package controller;

import connection.CreateConnection;
import entity.Elev;
import entity.Profesor;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ElevController {

    public Elev login(String username, String password){
        String functionLogin = "{ ? = call LOGIN(?) }";
        Elev rezult = new Elev();
        CallableStatement statement = null;
        try{
            statement = CreateConnection.connection.prepareCall(functionLogin);
            statement.registerOutParameter(1, Types.VARCHAR);
            statement.setString(2,username + ' ' + password);
            statement.execute();
            functionLogin = statement.getString(1);
            String [] elev = functionLogin.split("_");
            rezult.setId(Integer.parseInt(elev[0]));
            rezult.setNume(elev[1]);
            rezult.setPrenume(elev[2]);
            rezult.setGen(elev[3]);
            rezult.setVarsta(Integer.parseInt(elev[4]));
            rezult.setAdresa(elev[5]);
            rezult.setNr_telefon(elev[6]);
            rezult.setEmail(elev[7]);
            LiceuController liceuController = new LiceuController();
            rezult.setLiceu(liceuController.infoLiceu(Integer.parseInt(elev[8])));
            rezult.setClasa(elev[9]);
            rezult.setProfil(elev[10]);
        } catch (SQLException e){
//            System.out.println("Eroare:  " + e);
            return rezult;
        } catch (NumberFormatException f){
            return rezult;
        }
        return rezult;
    }

    public List<Profesor> getAllTeachers(int idElev){
        List<Profesor> materies = new LinkedList<>();
        String function = "{ ? = call NOTEOUT.LISTPROFESORI(?) }";
        CallableStatement callableStatement = null;
        String sirRezultat = new String();
        try{
            callableStatement = CreateConnection.connection.prepareCall(function);
            callableStatement.registerOutParameter(1,Types.VARCHAR);
            callableStatement.setInt(2,idElev);
            callableStatement.execute();
            sirRezultat = callableStatement.getString(1);
//            System.out.println(sirRezultat);
            String[] lista = sirRezultat.split("_");
            for (String string : lista) {
                materies.add(new ProfesorController().getProfesorById(Integer.parseInt(string)));
//                System.out.println(new ProfesorController().getProfesorById(Integer.parseInt(string)));
            }
        } catch (SQLException e){
            return materies;
        } catch (NumberFormatException f){
            return materies;
        }
        return materies;
    }

    public String medieGenerala(int idElev) {
        String resultat = new String();
        CallableStatement statement = null;
        String functionLogin = "{ ? = call AVGPACKAGES.MEDIEGENERALA(?) }";
        try {
            statement = CreateConnection.connection.prepareCall(functionLogin);
            statement.registerOutParameter(1, Types.VARCHAR);
            statement.setInt(2, idElev);
            statement.execute();
            resultat = statement.getString(1);
        } catch (SQLException e) {
            System.out.println("Exceptie: " + e);
        }
        return resultat;
    }

    public String medieMaterie(String numeMateri, int idMaterie){
        String resultat = new String();
        CallableStatement statement = null;
        String avgMaterie = "{ ? = call AVGPACKAGES.MEDIEMATERIE(?,?) }";
        try{
            statement = CreateConnection.connection.prepareCall(avgMaterie);
            statement.registerOutParameter(1, Types.VARCHAR);
            statement.setString(2,numeMateri);
            statement.setInt(3,idMaterie);
            statement.execute();
            resultat = statement.getString(1);
        } catch (SQLException e){
            System.out.println("Exceptie: " + e);
        }
        return resultat;
    }

    public String medieCuTeza(String numeMaterii, int idMaterii){
        String resultat = new String();
        CallableStatement statement = null;
        String cuTeza = "{ ? = call AVGPACKAGES.MEDIEMATERIECUTEZA(?,?) }";
        try{
            statement = CreateConnection.connection.prepareCall(cuTeza);
            statement.registerOutParameter(1,Types.VARCHAR);
            statement.setString(2,numeMaterii);
            statement.setInt(3,idMaterii);
            statement.execute();
            resultat = statement.getString(1);
        } catch (SQLException e){
            if(e.toString().contains("La aceasta materie elevul nu a dat teza")){
                resultat = medieFaraTeza(numeMaterii,idMaterii);
            }
        }
        return resultat;
    }

    public String medieFaraTeza(String numeMaterie, int idMaterie){
        String resultat = new String();
        CallableStatement statement = null;
        String cuTeza = "{ ? = call AVGPACKAGES.MEDIEMATERIEFARATEZA(?,?) }";
        try{
            statement = CreateConnection.connection.prepareCall(cuTeza);
            statement.registerOutParameter(1,Types.VARCHAR);
            statement.setString(2,numeMaterie);
            statement.setInt(3,idMaterie);
            statement.execute();
            resultat = statement.getString(1);
        } catch (SQLException e){
            if(e.toString().contains("La aceasta materie elevul a dat teza")){
                resultat = medieCuTeza(numeMaterie,idMaterie);
            }
        }
        return resultat;
    }

    public int getId(String numeElev, String prenumeElev){
        int result = 0;
        try(Statement statement = CreateConnection.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id from elevi where nume = \'" + numeElev + "\' and  prenume = \'" + prenumeElev + "\'")){
            resultSet.next();
            result = resultSet.getInt(1);
        }catch (SQLException e){

        }
        return result;
    }

    public int getIdLiceu(String numeElev, String prenumeElev){
        int result = 0;
        try(Statement statement = CreateConnection.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id_liceu from elevi where nume = \'" + numeElev + "\' and  prenume = \'" + prenumeElev + "\'")){
            resultSet.next();
            result = resultSet.getInt(1);
        }catch (SQLException e){

        }
        return result;
    }

    public Elev getById(int idElev){
        Elev elev = new Elev();
        try{
            PreparedStatement preparedStatement = CreateConnection.connection.prepareStatement("select * from elevi where id = ?");
            preparedStatement.setInt(1,idElev);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            elev.setId(resultSet.getInt(1));
            elev.setNume(resultSet.getString(2));
            elev.setPrenume(resultSet.getString(3));
            elev.setGen(resultSet.getString(4));
            elev.setVarsta(resultSet.getInt(5));
            elev.setAdresa(resultSet.getString(6));
            elev.setNr_telefon(resultSet.getString(7));
            elev.setEmail(resultSet.getString(8));
            elev.setLiceu(new LiceuController().infoLiceu(resultSet.getInt(9)));
            elev.setClasa(resultSet.getString(10));
            elev.setProfil(resultSet.getString(11));
        } catch (SQLException e){
            System.out.println("Exceptie: " + e);
        }

        return elev;
    }

}
