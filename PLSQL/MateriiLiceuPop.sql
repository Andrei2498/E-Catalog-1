set serveroutput on;
/

drop table Liceu cascade constraints
/
create table Liceu(
  id int not null primary key,
  titlu varchar2(50) not null,
  nume varchar2(50) not null,
  judet varchar2(20) not null
)
/
drop table Materii cascade constraints
/
create table Materii(
  id int not null primary key,
  nume varchar2(40) not null,
  clasa int not null,
  profil varchar2(10) not null
)
/

declare
  TYPE myString IS VARRAY(5000) OF varchar2(255);
  lista_materii_clasa_9_real myString := myString('Logica', 'Matematica', 'Limba_Romana','Fizica','Istorie','Geografie','Informatica','Educatie Fizica','Desen','Educatie Muzicala','Chimie','Religie','Limba Engleza','Limba Franceza','TIC','Biologie');
  lista_materii_clasa_10_real myString := myString('Psihologie', 'Matematica', 'Limba Romana','Fizica','Istorie','Geografie','Informatica','Educatie Fizica','Desen','Educatie Muzicala','Chimie','Religie','Limba Engleza','Limba Franceza','TIC','Biologie');
  lista_materii_clasa_11_real myString := myString('Filozofie', 'Matematica', 'Limba Romana','Fizica','Istorie','Geografie','Informatica','Educatie Fizica','Chimie','Religie','Limba Engleza','Limba Franceza','Biologie');
  lista_materii_clasa_12_real myString := myString('Economie', 'Matematica', 'Limba Romana','Fizica','Istorie','Geografie','Informatica','Educatie Fizica','Chimie','Religie','Limba Engleza','Limba Franceza','Biologie');
  lista_materii_clasa_9_uman myString := myString('Logica', 'Matematica', 'Limba Romana','Fizica','Istorie','Geografie','Educatie Fizica','Desen','Educatie Muzicala','Chimie','Religie','Limba Engleza','Limba Franceza','TIC','Biologie');
  lista_materii_clasa_10_uman myString := myString('Psihologie', 'Matematica', 'Limba Romana','Fizica','Istorie','Geografie','Educatie Fizica','Desen','Educatie Muzicala','Chimie','Religie','Limba Engleza','Limba Franceza','TIC','Biologie');
  lista_materii_clasa_11_uman myString := myString('Filozofie', 'Matematica', 'Limba Romana','Istorie','Geografie','Educatie Fizica','Religie','Limba Engleza','Limba Franceza','TIC','Literatura Universala');
  lista_materii_clasa_12_uman myString := myString('Economie', 'Matematica', 'Limba Romana','Istorie','Geografie','Educatie Fizica','Religie','Limba Engleza','Limba Franceza','TIC','Literatura Universala');


  lista_titluri_liceu myString := myString('Liceu teoretic','Liceu tehnologic','Colegiu National','Liceu Pedagogic','Clegiu Economic');
  numeleLiceu myString := myString('Ion Creanga','Nicolaie Iorga','Nicolaie Balcescu','Ferdidand I','Ion Ghica','Grigore Moisil' ,'Anghel Saligny','Panait Cerna','Gheorghe Lazar','Vasile Alecsandri','Spiru Haret', 'Gheorghe Munteanu Murgoci','Ioan Mesota','Gheorghe Vranceanu','Mircea cel Batran','Stefan cel Mare','Mihai Viteazul' ,'Ovidius','Emanuil Gojdu','Unirea','Gheorghe Sincai','George Cosbuc','Lucian Blaga','Costache Negri','Petru Rares');      
  v_numeLiceu varchar2(50);
  v_titlu varchar2(50);
  v_auxiliar int := 0;
begin 
    for v_index in 1..numeleLiceu.count loop
      v_titlu := lista_titluri_liceu(TRUNC(SYS.DBMS_RANDOM.VALUE(0,lista_titluri_liceu.COUNT)) + 1);
      v_numeLiceu := numeleLiceu(v_index);
      insert into Liceu values(v_index,v_titlu,v_numeLiceu,'Iasi');
    
    end loop;
  v_auxiliar := 1;

  FOR v_index IN 1..lista_materii_clasa_9_real.COUNT LOOP

    insert into Materii values(v_auxiliar,lista_materii_clasa_9_real(v_index),9,'real');

    v_auxiliar := v_auxiliar + 1;

  END LOOP;
  FOR v_index IN 1..lista_materii_clasa_10_real.COUNT LOOP

    insert into Materii values(v_auxiliar,lista_materii_clasa_10_real(v_index),10,'real');

    v_auxiliar := v_auxiliar + 1;

  END LOOP;
  FOR v_index IN 1..lista_materii_clasa_11_real.COUNT LOOP

    insert into Materii values(v_auxiliar,lista_materii_clasa_11_real(v_index),11,'real');

    v_auxiliar := v_auxiliar + 1;

  END LOOP;
  FOR v_index IN 1..lista_materii_clasa_12_real.COUNT LOOP

    insert into Materii values(v_auxiliar,lista_materii_clasa_12_real(v_index),12,'real');

    v_auxiliar := v_auxiliar + 1;

  END LOOP;
  FOR v_index IN 1..lista_materii_clasa_9_uman.COUNT LOOP

    insert into Materii values(v_auxiliar,lista_materii_clasa_9_uman(v_index),9,'uman');

    v_auxiliar := v_auxiliar + 1;

  END LOOP;
  FOR v_index IN 1..lista_materii_clasa_10_uman.COUNT LOOP

    insert into Materii values(v_auxiliar,lista_materii_clasa_10_uman(v_index),10,'uman');

    v_auxiliar := v_auxiliar + 1;

  END LOOP;
  FOR v_index IN 1..lista_materii_clasa_11_uman.COUNT LOOP

    insert into Materii values(v_auxiliar,lista_materii_clasa_11_uman(v_index),11,'uman');

    v_auxiliar := v_auxiliar + 1;

  END LOOP;
  FOR v_index IN 1..lista_materii_clasa_12_uman.COUNT LOOP

    insert into Materii values(v_auxiliar,lista_materii_clasa_12_uman(v_index),12,'uman');

    v_auxiliar := v_auxiliar + 1;

  END LOOP;
end;
/

select * from Materii;
/
select * from Liceu;
/