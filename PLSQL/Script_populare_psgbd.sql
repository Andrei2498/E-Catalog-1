DROP TABLE elevi CASCADE CONSTRAINTS
/
DROP TABLE parinti CASCADE CONSTRAINTS
/
DROP TABLE relatii CASCADE CONSTRAINTS
/
DROP TABLE profesori CASCADE CONSTRAINTS
/
DROP TABLE accounts CASCADE CONSTRAINTS
/
DROP TABLE materii CASCADE CONSTRAINTS
/
DROP TABLE teze CASCADE CONSTRAINTS
/
DROP TABLE activitate CASCADE CONSTRAINTS
/
drop table bursieri cascade constraints
/

CREATE TABLE elevi1 (
  id INT NOT NULL PRIMARY KEY,
  nume VARCHAR2(20) NOT NULL,
  prenume VARCHAR2(40) NOT NULL, 
  nr_matricol VARCHAR2(15) NOT NULL,
  profil VARCHAR2(25),
  clasa VARCHAR2(3) )
/

CREATE TABLE parinti1 ( 
  id INT NOT NULL PRIMARY KEY,
  id_elev INT NOT NULL,
  nume VARCHAR2(20) NOT NULL,
  prenume VARCHAR2(40) NOT NULL,
  
  CONSTRAINT fk_parinti1_id_student FOREIGN KEY (id_elev) REFERENCES elevi1(id) )
/

CREATE TABLE relatii1 ( 
  id INT NOT NULL PRIMARY KEY,
  id_elev INT NOT NULL,
  id_parinte INT NOT NULL,
  
  CONSTRAINT fk_relatie_id_elev FOREIGN KEY (id_elev) REFERENCES elevi1(id),
  CONSTRAINT fk_relatie_id_parinte FOREIGN KEY (id_parinte) REFERENCES parinti1(id) )
/

CREATE TABLE profesori1 (
  id INT NOT NULL PRIMARY KEY,
  nume VARCHAR2(20) NOT NULL,
  prenume VARCHAR2(40) NOT NULL,
  email VARCHAR2(60) NOT NULL )
/

CREATE TABLE materii1 ( 
  id INT NOT NULL PRIMARY KEY,
  nume_materie VARCHAR2(40) NOT NULL,
  clasa INT NOT NULL,
  id_profesor INT NOT NULL,
  profil VARCHAR2(4) NOT NULL,
  
  CONSTRAINT fk_materii_id_profesor FOREIGN KEY (id_profesor) REFERENCES profesori1(id) )
/

CREATE TABLE accounts (
  nume_cont VARCHAR2(65) NOT NULL,
  parola VARCHAR2(10) NOT NULL )
/

CREATE TABLE teze1 ( 
  id INT NOT NULL PRIMARY KEY,
  id_elev INT NOT NULL,
  id_profesor INT NOT NULL,
  id_materie INT NOT NULL,
  nota_teza INT NOT NULL,
  data_notare DATE NOT NULL,
  
  CONSTRAINT fk_teze1_ide_profesor FOREIGN KEY (id_elev) REFERENCES elevi1(id),
  CONSTRAINT fk_teze1_idp_profesor FOREIGN KEY (id_profesor) REFERENCES profesori1(id),
  CONSTRAINT fk_teze1_idm_profesor FOREIGN KEY (id_materie) REFERENCES materii1(id) )
/  

CREATE TABLE activitate1 (
  id_elev INT NOT NULL,
  id_materie INT NOT NULL,
  id_profesor INT NOT NULL,
  data_notare DATE NOT NULL,
  nota INT,
  
  CONSTRAINT fk_activitate_id_profesor FOREIGN KEY (id_profesor) REFERENCES profesori1(id),
  CONSTRAINT fk_activitate_id_elev FOREIGN KEY (id_elev) REFERENCES elevi1(id),
  CONSTRAINT fk_activitate_id_materie FOREIGN KEY (id_materie) REFERENCES materii1(id) )
/

set serveroutput on;
/

DECLARE 
  TYPE myString IS VARRAY(5000) OF varchar2(255);
  nume myString := myString('Pirlog','Dinescu','Puiu','Poenaru','Ionesco','Stoenescu','Boroi','Cinca','Bratianu','Florea','Olaru'
              ,'Pavel','Neagoe','Nica','Tilea','Banciu','Hila','Cristea','Dobre','Vladimirescu','Cojocaru','Gaina','Stolojan'
              ,'Dumitrescu','Martinecu','Mocanu','Nistor','Manole','Dumitru','Pop','Minea','Avramescu','Dimir','Munteanu'
              ,'Lazarescu','Babes','Goga','Dragan','Ilionescu','Antonescu','Mircea','Mihnea','Albu','Mitu','Lupei','Ghita','Ungur'
              ,'Ungureanu','Enescu','Moisuc','Lascar');
  
  prenume_baieti myString := myString('Marcel','Alexandru','Andrei','Anghel','Avram','Silviu','Eduard','Calin','Raul','Stefan','Traian'
              ,'Ioan','Ionut','Denis','Sandu','Beniamin','Petre','Ovidiu','Flavius','Ion','Mihai','Vasile','Robert','Corneliu'
              ,'Gabriel','Horatiu','Valeriu','Iuliu','Alin','Neculai','Horia','Teo','Artur','Gavril','Luca','Cristian','David'
              ,'Costi','Constantin','Marku','Laurentiu','Cosmin','Mihail','Dumitru','Ivan','Carol','Octavian','Bogdan','Martin','Ghorghe');
  
  prenume_fete myString := myString('Ana','Alexandra','Marta','Madalina','Ileana','Narcisa','Dorina','Sorana','Magdalena','Sorina'
              ,'Corina','Voileta', 'Elisabeta','Aurica','Denisa','Luminita','Florina','Tatiana','Adelina','Ioana','Mihaela'
              ,'Cami','Ecaterina','Marcela','Oana','Sanda','Ligia','Claudia','Lavinia','Victoria','Voctorita','Antanasia'
              ,'Cornelia','Valerica','Carla','Rozalia','Veronica','Marilena','Bianca','Ioanela','Magda','Georgeta','Andreea'
              ,'Cecilia','Georgiana','Sabina','Nicoleta','Delia','Marioara','Stefania');
  
  lista_materii_clasa_9_real myString := myString('Logica', 'Matematica', 'Limba_Romana','Fizica','Istorie','Geografie','Informatica','Educatie Fizica','Desen','Educatie Muzicala','Chimie','Religie','Limba Engleza','Limba Franceza','TIC','Biologie');
  lista_materii_clasa_10_real myString := myString('Psihologie', 'Matematica', 'Limba Romana','Fizica','Istorie','Geografie','Informatica','Educatie Fizica','Desen','Educatie Muzicala','Chimie','Religie','Limba Engleza','Limba Franceza','TIC','Biologie');
  lista_materii_clasa_11_real myString := myString('Filozofie', 'Matematica', 'Limba Romana','Fizica','Istorie','Geografie','Informatica','Educatie Fizica','Chimie','Religie','Limba Engleza','Limba Franceza','Biologie');
  lista_materii_clasa_12_real myString := myString('Economie', 'Matematica', 'Limba Romana','Fizica','Istorie','Geografie','Informatica','Educatie Fizica','Chimie','Religie','Limba Engleza','Limba Franceza','Biologie');
  lista_materii_clasa_9_uman myString := myString('Logica', 'Matematica', 'Limba Romana','Fizica','Istorie','Geografie','Educatie Fizica','Desen','Educatie Muzicala','Chimie','Religie','Limba Engleza','Limba Franceza','TIC','Biologie');
  lista_materii_clasa_10_uman myString := myString('Psihologie', 'Matematica', 'Limba Romana','Fizica','Istorie','Geografie','Educatie Fizica','Desen','Educatie Muzicala','Chimie','Religie','Limba Engleza','Limba Franceza','TIC','Biologie');
  lista_materii_clasa_11_uman myString := myString('Filozofie', 'Matematica', 'Limba Romana','Istorie','Geografie','Educatie Fizica','Religie','Limba Engleza','Limba Franceza','TIC','Literatura Universala');
  lista_materii_clasa_12_uman myString := myString('Economie', 'Matematica', 'Limba Romana','Istorie','Geografie','Educatie Fizica','Religie','Limba Engleza','Limba Franceza','TIC','Literatura Universala');

--DATE DESPRE ELEVI
  v_nume_elev VARCHAR2(255);
  v_prenume_elev VARCHAR2(255);
  v_prenume1_elev VARCHAR2(255);
  v_prenume2_elev VARCHAR2(255);
  v_numar_matricol VARCHAR2(15);
  v_clasa VARCHAR2(4);
  v_profil VARCHAR2(25);
  
--DATE DESPRE PARINTI
  v_nume_parinte VARCHAR2(255);
  v_prenume_parinte VARCHAR2(255);
  v_prenume1_parinte VARCHAR2(255);
  v_prenume2_parinte VARCHAR2(255);

--DATE DESPRE MATERII
  v_nume_materie VARCHAR2(255);
  v_clasa_materie INT;

--DATE DESPRE PROFESORI
  v_nume_profesor VARCHAR2(255);
  v_prenume_profesor VARCHAR2(255);
  v_prenume1_profesor VARCHAR2(255);
  v_prenume2_profesor VARCHAR2(255);
  v_email_profesor VARCHAR2(255);

--DATE DESPRE NOTE/ABSENTE  
  v_data_nastere DATE;

--DATEDESPRE CONTURI
  v_user VARCHAR2(255);
  v_parola VARCHAR2(255);

--DATE AUXILIARE
  v_auxiliar INT;
  v_auxiliar1 INT;
  v_auxiliar2 NUMBER;
  v_auxiliar3 INT;
  v_auxiliar4 INT;
  v_aux_clasa VARCHAR2(1);
  v_nr_matricol_auxiliar VARCHAR2(15);
  v_temporar INT;
  contor INT;
  v_contor_note INT;
  v_randomizare INT;
BEGIN
  contor := 1;
--  CLASELE 9-10-11-12
--  CLASE: A-B-C-D-E-F-G-H
--  30ELEVI/CLASA
--  30 x 8 = 240 x 4 = 960 elevi de inserat
  v_contor_note := 1;
  v_auxiliar := 1;
  v_auxiliar3 := 1;
 DBMS_OUTPUT.PUT_LINE('Incepem crearea profesorilor.');
  DBMS_OUTPUT.PUT_LINE('<------START------>');
  
  FOR v_index IN 1..110 LOOP
    v_nume_profesor := nume(TRUNC(DBMS_RANDOM.VALUE(0,nume.COUNT))+1);
    IF (SYS.DBMS_RANDOM.VALUE(0,100) < 50) THEN
      v_prenume1_profesor := prenume_baieti(TRUNC(SYS.DBMS_RANDOM.VALUE(0,prenume_baieti.COUNT)) + 1);
      IF ((SYS.DBMS_RANDOM.VALUE(0,100) < 20 )) THEN
        v_prenume2_profesor := prenume_baieti(TRUNC(SYS.DBMS_RANDOM.VALUE(0,prenume_baieti.COUNT)) + 1);
        WHILE ( v_prenume1_profesor = v_prenume2_profesor) LOOP
          v_prenume2_profesor := prenume_baieti(TRUNC(SYS.DBMS_RANDOM.VALUE(0,prenume_baieti.COUNT)) + 1);
        END LOOP;
      END IF;
      v_prenume_profesor := v_prenume1_profesor || ' ' || v_prenume2_profesor;
    ELSE 
      v_prenume1_profesor := prenume_fete(TRUNC(SYS.DBMS_RANDOM.VALUE(0,prenume_fete.COUNT)) + 1);
      IF ((SYS.DBMS_RANDOM.VALUE(0,100) < 20 )) THEN
        v_prenume2_profesor := prenume_fete(TRUNC(SYS.DBMS_RANDOM.VALUE(0,prenume_fete.COUNT)) + 1);
        WHILE ( v_prenume1_profesor = v_prenume2_profesor) LOOP
          v_prenume2_profesor := prenume_fete(TRUNC(SYS.DBMS_RANDOM.VALUE(0,prenume_fete.COUNT)) + 1);
        END LOOP;
      END IF;
      v_prenume_profesor := v_prenume1_profesor || ' ' || v_prenume2_profesor;
    END IF;
    
    v_email_profesor := LOWER(v_nume_profesor) || '.' || LOWER(v_prenume1_profesor);
    IF (length(v_prenume2_profesor) > 0) THEN
      v_email_profesor := v_email_profesor || '.' || LOWER(v_prenume2_profesor);
    END IF;
    v_email_profesor := v_email_profesor || '@gmail.com';
    
    
--    DBMS_OUTPUT.PUT_LINE(v_nume_profesor || ' ' ||  v_prenume1_profesor || ' ' || v_prenume2_profesor);
--    DBMS_OUTPUT.PUT_LINE(v_email_profesor);
    
    insert into profesori1 values(v_index,v_nume_profesor,v_prenume_profesor,v_email_profesor);
    
    v_user := 'P.' || LOWER(v_nume_profesor) || '.' || LOWER(v_prenume1_profesor);
  
    IF(LENGTH(v_prenume2_profesor) > 0) THEN
      v_user := v_user || '.' || LOWER(v_prenume2_profesor);
    END IF;
  
    v_parola := TRUNC(DBMS_RANDOM.VALUE(100,999)) || CHR(CEIL(SYS.DBMS_RANDOM.VALUE(64,90))) || CHR(CEIL(SYS.DBMS_RANDOM.VALUE(64,90))) || TRUNC(DBMS_RANDOM.VALUE(100,999)) || CHR(CEIL(SYS.DBMS_RANDOM.VALUE(64,90))) || CHR(CEIL(SYS.DBMS_RANDOM.VALUE(64,90)));
  
    insert into accounts values(v_user,v_parola);
    v_parola := '';
    v_user := '';
    v_nume_profesor := '';
    v_prenume_profesor :='';
    v_prenume1_profesor := '';
    v_prenume2_profesor := '';
    v_email_profesor := '';

  END LOOP;
  
  v_auxiliar := 1;
  
  FOR v_index IN 1..lista_materii_clasa_9_real.COUNT LOOP
    
    insert into materii1 values(v_auxiliar,lista_materii_clasa_9_real(v_index),9,v_auxiliar,'real');
    
    v_auxiliar := v_auxiliar + 1;
    
  END LOOP;
  FOR v_index IN 1..lista_materii_clasa_10_real.COUNT LOOP
    
    insert into materii1 values(v_auxiliar,lista_materii_clasa_10_real(v_index),10,v_auxiliar,'real');
    
    v_auxiliar := v_auxiliar + 1;
    
  END LOOP;
  FOR v_index IN 1..lista_materii_clasa_11_real.COUNT LOOP
    
    insert into materii1 values(v_auxiliar,lista_materii_clasa_11_real(v_index),11,v_auxiliar,'real');
    
    v_auxiliar := v_auxiliar + 1;
    
  END LOOP;
  FOR v_index IN 1..lista_materii_clasa_12_real.COUNT LOOP
    
    insert into materii1 values(v_auxiliar,lista_materii_clasa_12_real(v_index),12,v_auxiliar,'real');
    
    v_auxiliar := v_auxiliar + 1;
    
  END LOOP;
  FOR v_index IN 1..lista_materii_clasa_9_uman.COUNT LOOP
    
    insert into materii1 values(v_auxiliar,lista_materii_clasa_9_uman(v_index),9,v_auxiliar,'uman');
    
    v_auxiliar := v_auxiliar + 1;
    
  END LOOP;
  FOR v_index IN 1..lista_materii_clasa_10_uman.COUNT LOOP
    
    insert into materii1 values(v_auxiliar,lista_materii_clasa_10_uman(v_index),10,v_auxiliar,'uman');
    
    v_auxiliar := v_auxiliar + 1;
    
  END LOOP;
  FOR v_index IN 1..lista_materii_clasa_11_uman.COUNT LOOP
    
    insert into materii1 values(v_auxiliar,lista_materii_clasa_11_uman(v_index),11,v_auxiliar,'uman');
    
    v_auxiliar := v_auxiliar + 1;
    
  END LOOP;
  FOR v_index IN 1..lista_materii_clasa_12_uman.COUNT LOOP
    
    insert into materii1 values(v_auxiliar,lista_materii_clasa_12_uman(v_index),12,v_auxiliar,'uman');
    
    v_auxiliar := v_auxiliar + 1;
    
  END LOOP;
  DBMS_OUTPUT.PUT_LINE('<------GATA------>');
  DBMS_OUTPUT.PUT_LINE('Incercam inserarea a 960 de elevi.');
  DBMS_OUTPUT.PUT_LINE('<------START------>');
  FOR v_index IN 1..1280 LOOP
    v_nr_matricol_auxiliar := 'AB20182019';
    v_nume_elev := nume(TRUNC(DBMS_RANDOM.VALUE(0,nume.COUNT))+1);
    IF (SYS.DBMS_RANDOM.VALUE(0,100) < 50) THEN
      v_prenume1_elev := prenume_baieti(TRUNC(SYS.DBMS_RANDOM.VALUE(0,prenume_baieti.COUNT)) + 1);
      IF ((SYS.DBMS_RANDOM.VALUE(0,100) < 20 )) THEN
        v_prenume2_elev := prenume_baieti(TRUNC(SYS.DBMS_RANDOM.VALUE(0,prenume_baieti.COUNT)) + 1);
        WHILE ( v_prenume1_elev = v_prenume2_elev) LOOP
          v_prenume2_elev := prenume_baieti(TRUNC(SYS.DBMS_RANDOM.VALUE(0,prenume_baieti.COUNT)) + 1);
        END LOOP;
      END IF;
      v_prenume_elev := v_prenume1_elev || ' ' || v_prenume2_elev;
    ELSE 
      v_prenume1_elev := prenume_fete(TRUNC(SYS.DBMS_RANDOM.VALUE(0,prenume_fete.COUNT)) + 1);
      IF ((SYS.DBMS_RANDOM.VALUE(0,100) < 20 )) THEN
        v_prenume2_elev := prenume_fete(TRUNC(SYS.DBMS_RANDOM.VALUE(0,prenume_fete.COUNT)) + 1);
        WHILE ( v_prenume1_elev = v_prenume2_elev) LOOP
          v_prenume2_elev := prenume_fete(TRUNC(SYS.DBMS_RANDOM.VALUE(0,prenume_fete.COUNT)) + 1);
        END LOOP;
      END IF;
      v_prenume_elev := v_prenume1_elev || ' ' || v_prenume2_elev;
    END IF;
  
--  IF (v_auxiliar < 10 ) THEN 
--    v_nr_matricol_auxiliar := v_nr_matricol_auxiliar || '000' || v_auxiliar;
--  ELSIF ( v_auxiliar < 100 AND v_auxiliar >= 10 ) THEN
--    v_nr_matricol_auxiliar := v_nr_matricol_auxiliar || '00' || v_auxiliar;
--  ELSIF( v_auxiliar >= 100) THEN
--    v_nr_matricol_auxiliar := v_nr_matricol_auxiliar || '0' ||  v_auxiliar;
--  ELSE 
--    v_nr_matricol_auxiliar := v_nr_matricol_auxiliar || v_auxiliar;
--  END IF;

--    IF ( v_auxiliar <= 480 ) THEN
--      v_profil := 'real';
--      IF ( v_auxiliar <= 40 ) THEN 
--        v_clasa := '12A';
--        v_temporar := 12;
--      ELSIF(v_auxiliar <= 80) THEN
--        v_clasa := '12B';
--        v_temporar := 12;
--      ELSIF(v_auxiliar <= 120) THEN
--        v_clasa := '12C';
--        v_temporar := 12;
--      ELSIF(v_auxiliar <= 160) THEN
--        v_clasa := '12D';
--        v_temporar := 12;
--      ELSIF(v_auxiliar <= 200) THEN
--        v_clasa := '11A';
--        v_temporar := 11;
--      ELSIF(v_auxiliar <= 240) THEN
--        v_clasa := '11B';
--        v_temporar := 11;
--      ELSIF(v_auxiliar <= 280) THEN
--        v_clasa := '11C';
--        v_temporar := 11;
--      ELSIF(v_auxiliar <= 320) THEN
--        v_clasa := '11D';
--        v_temporar := 11;
--      ELSIF(v_auxiliar <= 360) THEN
--        v_clasa := '10A';
--        v_temporar := 10;
--      ELSIF(v_auxiliar <= 400) THEN
--        v_clasa := '10B';
--        v_temporar := 10;
--      ELSIF(v_auxiliar <= 440) THEN
--        v_clasa := '10C';
--        v_temporar := 10;
--      ELSIF(v_auxiliar <= 480) THEN
--        v_clasa := '10D';
--        v_temporar := 10;
--      ELSIF(v_auxiliar <= 520) THEN
--        v_clasa := '9A';
--        v_temporar := 9;
--      ELSIF(v_auxiliar <= 560) THEN
--        v_clasa := '9B';
--        v_temporar := 9;
--      ELSIF(v_auxiliar <= 600) THEN
--        v_clasa := '9C';
--        v_temporar := 9;
--      ELSIF(v_auxiliar <= 640) THEN
--        v_clasa := '9D';
--        v_temporar := 9;
--      END IF;
--    ELSE
--      v_profil := 'uman';
--      IF ( v_auxiliar <= 680 ) THEN 
--        v_clasa := '12E';
--        v_temporar := 12;
--      ELSIF(v_auxiliar <= 720) THEN
--        v_temporar := 12;
--        v_clasa := '12F';
--      ELSIF(v_auxiliar <= 760) THEN
--        v_clasa := '12G';
--        v_temporar := 12;
--      ELSIF(v_auxiliar <= 800) THEN
--        v_clasa := '12H';
--        v_temporar := 12;
--      ELSIF(v_auxiliar <= 840) THEN
--        v_clasa := '11E';
--        v_temporar := 11;
--      ELSIF(v_auxiliar <= 880) THEN
--        v_clasa := '11F';
--        v_temporar := 11;
--      ELSIF(v_auxiliar <= 920) THEN
--        v_clasa := '11G';
--        v_temporar := 11;
--      ELSIF(v_auxiliar <= 960) THEN
--        v_clasa := '11H';
--        v_temporar := 11;
--      ELSIF(v_auxiliar <= 1000) THEN
--        v_clasa := '10E';
--        v_temporar := 10;
--      ELSIF(v_auxiliar <= 1040) THEN
--        v_clasa := '10F';
--        v_temporar := 10;
--      ELSIF(v_auxiliar <= 1080) THEN
--        v_clasa := '10G';
--        v_temporar := 10;
--      ELSIF(v_auxiliar <= 1120) THEN
--        v_clasa := '10H';
--        v_temporar := 10;
--      ELSIF(v_auxiliar <= 1160) THEN
--        v_clasa := '9E';
--        v_temporar := 9;
--      ELSIF(v_auxiliar <= 1200) THEN
--        v_clasa := '9F';
--        v_temporar := 9;
--      ELSIF(v_auxiliar <= 1240) THEN
--        v_clasa := '9G';
--        v_temporar := 9;
--      ELSIF(v_auxiliar <= 1280) THEN
--        v_clasa := '9H';
--        v_temporar := 9;
--      END IF;
--    END IF;

  v_numar_matricol := v_nr_matricol_auxiliar; 
  v_nume_parinte := v_nume_elev;
  
--  DBMS_OUTPUT.PUT_LINE(v_index||' '|| v_nume_elev||' '||v_prenume_elev ||' '||v_numar_matricol||' ' || v_profil || ' ' ||v_clasa);  
  
  insert into elevi1 values(v_index,v_nume_elev,v_prenume_elev,v_numar_matricol,v_profil,v_clasa );
  
  IF (v_auxiliar <= 120) THEN
    FOR v_index1 IN 1..lista_materii_clasa_12_real.COUNT LOOP
      v_randomizare := CEIL(SYS.DBMS_RANDOM.VALUE(6,12));
      FOR v_index2 IN 1..v_randomizare LOOP
        insert into activitate1 values(v_index,v_index1,v_index1,sysdate,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)));
      END LOOP;   
      v_randomizare := CEIL(SYS.DBMS_RANDOM.VALUE(0,8))-1;
      IF(v_randomizare > 0) THEN
        FOR v_index2 IN 1..v_randomizare LOOP
          insert into activitate1 values(v_index,v_index1,v_index1,sysdate,null);
        END LOOP;
      END IF;
    END LOOP;
    
  ELSIF (v_auxiliar <= 240 ) THEN
    FOR v_index1 IN 1..lista_materii_clasa_12_uman.COUNT LOOP
      v_randomizare := CEIL(SYS.DBMS_RANDOM.VALUE(6,12));
      FOR v_index2 IN 1..v_randomizare LOOP
        insert into activitate1 values(v_index,v_index1,v_index1,sysdate,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)));
      END LOOP;   
      v_randomizare := CEIL(SYS.DBMS_RANDOM.VALUE(0,8))-1;
      IF(v_randomizare > 0) THEN
        FOR v_index2 IN 1..v_randomizare LOOP
          insert into activitate1 values(v_index,v_index1,v_index1,sysdate,null);
        END LOOP;
      END IF;   
    END LOOP;
  ELSIF (v_auxiliar <= 360) THEN
    FOR v_index1 IN 1..lista_materii_clasa_11_real.COUNT LOOP
      v_randomizare := CEIL(SYS.DBMS_RANDOM.VALUE(6,12));
      FOR v_index2 IN 1..v_randomizare LOOP
        insert into activitate1 values(v_index,v_index1,v_index1,sysdate,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)));
      END LOOP;    
      v_randomizare := CEIL(SYS.DBMS_RANDOM.VALUE(0,8))-1;
      IF(v_randomizare > 0) THEN
        FOR v_index2 IN 1..v_randomizare LOOP
          insert into activitate1 values(v_index,v_index1,v_index1,sysdate,null);
        END LOOP;
      END IF;  
    END LOOP;
  ELSIF (v_auxiliar <= 480) THEN
    FOR v_index1 IN 1..lista_materii_clasa_11_uman.COUNT LOOP
      v_randomizare := CEIL(SYS.DBMS_RANDOM.VALUE(6,12));
      FOR v_index2 IN 1..v_randomizare LOOP
        insert into activitate1 values(v_index,v_index1,v_index1,sysdate,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)));
      END LOOP;   
      v_randomizare := CEIL(SYS.DBMS_RANDOM.VALUE(0,8))-1;
      IF(v_randomizare > 0) THEN
        FOR v_index2 IN 1..v_randomizare LOOP
          insert into activitate1 values(v_index,v_index1,v_index1,sysdate,null);
        END LOOP;
      END IF;   
    END LOOP;
  ELSIF (v_auxiliar <= 600) THEN
    FOR v_index1 IN 1..lista_materii_clasa_10_real.COUNT LOOP
      v_randomizare := CEIL(SYS.DBMS_RANDOM.VALUE(6,12));
      FOR v_index2 IN 1..v_randomizare LOOP
        insert into activitate1 values(v_index,v_index1,v_index1,sysdate,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)));
      END LOOP;   
    END LOOP;
  ELSIF (v_auxiliar <= 720) THEN
    FOR v_index1 IN 1..lista_materii_clasa_10_uman.COUNT LOOP
      v_randomizare := CEIL(SYS.DBMS_RANDOM.VALUE(6,12));
      FOR v_index2 IN 1..v_randomizare LOOP
        insert into activitate1 values(v_index,v_index1,v_index1,sysdate,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)));
      END LOOP;   
      v_randomizare := CEIL(SYS.DBMS_RANDOM.VALUE(0,8))-1;
      IF(v_randomizare > 0) THEN
        FOR v_index2 IN 1..v_randomizare LOOP
          insert into activitate1 values(v_index,v_index1,v_index1,sysdate,null);
        END LOOP;
      END IF;   
    END LOOP;
  ELSIF ( v_auxiliar <= 840 ) THEN
    FOR v_index1 IN 1..lista_materii_clasa_9_real.COUNT LOOP
      v_randomizare := CEIL(SYS.DBMS_RANDOM.VALUE(6,12));
      FOR v_index2 IN 1..v_randomizare LOOP
        insert into activitate1 values(v_index,v_index1,v_index1,sysdate,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)));
      END LOOP;   
      v_randomizare := CEIL(SYS.DBMS_RANDOM.VALUE(0,8))-1;
      IF(v_randomizare > 0) THEN
        FOR v_index2 IN 1..v_randomizare LOOP
          insert into activitate1 values(v_index,v_index1,v_index1,sysdate,null);
        END LOOP;
      END IF;   
    END LOOP;
  ELSE
    FOR v_index1 IN 1..lista_materii_clasa_9_uman.COUNT LOOP
      v_randomizare := CEIL(SYS.DBMS_RANDOM.VALUE(6,12));
      FOR v_index2 IN 1..v_randomizare LOOP
        insert into activitate1 values(v_index,v_index1,v_index1,sysdate,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)));
      END LOOP;   
      v_randomizare := CEIL(SYS.DBMS_RANDOM.VALUE(0,8))-1;
      IF(v_randomizare > 0) THEN
        FOR v_index2 IN 1..v_randomizare LOOP
          insert into activitate1 values(v_index,v_index1,v_index1,sysdate,null);
        END LOOP;
      END IF;   
    END LOOP;
  END IF;
  v_auxiliar2 := SYS.DBMS_RANDOM.VALUE(0,1);
  IF ( v_profil = 'uman' ) THEN
    IF ( v_temporar = 9 ) THEN
      insert into teze1 values(contor,v_index,61,61,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      insert into teze1 values(contor,v_index,59,59,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      insert into teze1 values(contor,v_index,63,63,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      IF (SYS.DBMS_RANDOM.VALUE(0,1) >= 0.5) THEN
        insert into teze1 values(contor,v_index,70,70,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      ELSE
        insert into teze1 values(contor,v_index,71,71,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      END IF;
    END IF;
    IF ( v_temporar = 10 ) THEN
      insert into teze1 values(contor,v_index,76,76,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      insert into teze1 values(contor,v_index,74,74,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      insert into teze1 values(contor,v_index,78,78,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      IF (SYS.DBMS_RANDOM.VALUE(0,1) >= 0.5) THEN
        insert into teze1 values(contor,v_index,85,85,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      ELSE
        insert into teze1 values(contor,v_index,86,86,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      END IF;
    END IF;
    IF ( v_temporar = 11 ) THEN
      insert into teze1 values(contor,v_index,91,91,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      insert into teze1 values(contor,v_index,89,89,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      insert into teze1 values(contor,v_index,92,92,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      IF (SYS.DBMS_RANDOM.VALUE(0,1) >= 0.5) THEN
        insert into teze1 values(contor,v_index,96,96,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      ELSE
        insert into teze1 values(contor,v_index,97,97,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      END IF;
    END IF;
    IF ( v_temporar = 12 ) THEN
      insert into teze1 values(contor,v_index,102,102,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      insert into teze1 values(contor,v_index,100,100,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      insert into teze1 values(contor,v_index,103,103,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      IF (SYS.DBMS_RANDOM.VALUE(0,1) >= 0.5) THEN
        insert into teze1 values(contor,v_index,107,107,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      ELSE
        insert into teze1 values(contor,v_index,108,108,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      END IF;
    END IF;
  ELSE
    IF ( v_temporar = 9 ) THEN
      insert into teze1 values(contor,v_index,3,3,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      insert into teze1 values(contor,v_index,2,2,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      insert into teze1 values(contor,v_index,7,7,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      IF (v_auxiliar2 <= 0.334) THEN
        insert into teze1 values(contor,v_index,4,4,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      ELSIF (v_auxiliar2 > 0.334 AND v_auxiliar2 <= 0.667) THEN
        insert into teze1 values(contor,v_index,11,11,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);contor := contor + 1;
      ELSE
        insert into teze1 values(contor,v_index,16,16,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);
        contor := contor + 1;
      END IF;
    END IF;
    IF ( v_temporar = 10 ) THEN
      insert into teze1 values(contor,v_index,19,19,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);
      contor := contor + 1;
      insert into teze1 values(contor,v_index,18,18,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);
      contor := contor + 1;
      insert into teze1 values(contor,v_index,23,23,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);
      contor := contor + 1;
      IF (v_auxiliar2 <= 0.334) THEN
        insert into teze1 values(contor,v_index,20,20,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);
        contor := contor + 1;
      ELSIF (v_auxiliar2 > 0.334 AND v_auxiliar2 <= 0.667) THEN
        insert into teze1 values(contor,v_index,27,27,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);
        contor := contor + 1;
      ELSE
        insert into teze1 values(contor,v_index,32,32,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);
        contor := contor + 1;
      END IF;
    END IF;
    IF ( v_temporar = 11 ) THEN
      insert into teze1 values(contor,v_index,35,35,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);
      contor := contor + 1;
      insert into teze1 values(contor,v_index,34,34,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);
      contor := contor + 1;
      insert into teze1 values(contor,v_index,39,39,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);
      contor := contor + 1;
      IF (v_auxiliar2 <= 0.334) THEN
        insert into teze1 values(contor,v_index,36,36,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);
        contor := contor + 1;
      ELSIF (v_auxiliar2 > 0.334 AND v_auxiliar2 <= 0.667) THEN
        insert into teze1 values(contor,v_index,41,41,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);
        contor := contor + 1;
      ELSE
        insert into teze1 values(contor,v_index,41,41,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);
        contor := contor + 1;
      END IF;
    END IF;
    IF ( v_temporar = 12 ) THEN
      insert into teze1 values(contor,v_index,48,48,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);
      contor := contor + 1;
      insert into teze1 values(contor,v_index,47,47,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);
      contor := contor + 1;
      insert into teze1 values(contor,v_index,52,52,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);
      contor := contor + 1;
      IF (v_auxiliar2 <= 0.334) THEN
        insert into teze1 values(contor,v_index,49,49,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);
        contor := contor + 1;
      ELSIF (v_auxiliar2 > 0.334 AND v_auxiliar2 <= 0.667) THEN
        insert into teze1 values(contor,v_index,54,54,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);
        contor := contor + 1;
      ELSE
        insert into teze1 values(contor,v_index,58,58,CEIL(SYS.DBMS_RANDOM.VALUE(4,10)),sysdate);
        contor := contor + 1;
      END IF;
    END IF;
  END IF;
  v_user := 'E.' || LOWER(v_nume_elev) || '.' || LOWER(v_prenume1_elev);
  
  IF(LENGTH(v_prenume2_elev) > 0) THEN
    v_user := v_user || '.' || LOWER(v_prenume2_elev);
  END IF;
  
  v_parola := TRUNC(DBMS_RANDOM.VALUE(100,999)) || CHR(CEIL(SYS.DBMS_RANDOM.VALUE(64,90))) || CHR(CEIL(SYS.DBMS_RANDOM.VALUE(64,90))) || TRUNC(DBMS_RANDOM.VALUE(100,999)) || CHR(CEIL(SYS.DBMS_RANDOM.VALUE(64,90))) || CHR(CEIL(SYS.DBMS_RANDOM.VALUE(64,90)));
  
  insert into accounts values(v_user,v_parola);
  v_parola := '';
  v_user := '';
  
-- INTEPEM INSERAREA PARINTILOR SI A RELATIILOR DE FAMILIE
    
    v_prenume1_parinte := prenume_baieti(TRUNC(SYS.DBMS_RANDOM.VALUE(0,prenume_baieti.COUNT)) + 1);
    IF ((SYS.DBMS_RANDOM.VALUE(0,100) < 20 )) THEN
      v_prenume2_parinte := prenume_baieti(TRUNC(SYS.DBMS_RANDOM.VALUE(0,prenume_baieti.COUNT)) + 1);
      WHILE ( v_prenume1_parinte = v_prenume2_parinte) LOOP
        v_prenume2_parinte := prenume_baieti(TRUNC(SYS.DBMS_RANDOM.VALUE(0,prenume_baieti.COUNT)) + 1);
      END LOOP;
    END IF;
    v_prenume_parinte := v_prenume1_parinte || ' ' || v_prenume2_parinte;
    
    
    insert into parinti1 values(2*v_index-1,v_index,v_nume_parinte,v_prenume_parinte);
    v_user := 'Pa.' || LOWER(v_nume_parinte) || '.' || LOWER(v_prenume1_parinte);
  
    IF(LENGTH(v_prenume2_parinte) > 0) THEN
      v_user := v_user || '.' || LOWER(v_prenume2_parinte);
    END IF;
  
    v_parola := TRUNC(DBMS_RANDOM.VALUE(100,999)) || CHR(CEIL(SYS.DBMS_RANDOM.VALUE(64,90))) || CHR(CEIL(SYS.DBMS_RANDOM.VALUE(64,90))) || TRUNC(DBMS_RANDOM.VALUE(100,999)) || CHR(CEIL(SYS.DBMS_RANDOM.VALUE(64,90))) || CHR(CEIL(SYS.DBMS_RANDOM.VALUE(64,90)));
  
    insert into accounts values(v_user,v_parola);
    v_parola := '';
    v_user := '';
  
    v_prenume1_parinte := prenume_fete(TRUNC(SYS.DBMS_RANDOM.VALUE(0,prenume_fete.COUNT)) + 1);
    IF ((SYS.DBMS_RANDOM.VALUE(0,100) < 20 )) THEN
      v_prenume2_parinte := prenume_fete(TRUNC(SYS.DBMS_RANDOM.VALUE(0,prenume_fete.COUNT)) + 1);
      WHILE ( v_prenume1_parinte = v_prenume2_parinte) LOOP
        v_prenume2_parinte := prenume_fete(TRUNC(SYS.DBMS_RANDOM.VALUE(0,prenume_fete.COUNT)) + 1);
      END LOOP;
    END IF;
    v_prenume_parinte := v_prenume1_parinte || ' ' || v_prenume2_parinte;
    
    insert into parinti1 values(2*v_index,v_index,v_nume_parinte,v_prenume_parinte);
    
    v_user := 'Pa.' || LOWER(v_nume_parinte) || '.' || LOWER(v_prenume1_parinte);
  
    IF(LENGTH(v_prenume2_parinte) > 0) THEN
      v_user := v_user || '.' || LOWER(v_prenume2_parinte);
    END IF;
  
    v_parola := TRUNC(DBMS_RANDOM.VALUE(100,999)) || CHR(CEIL(SYS.DBMS_RANDOM.VALUE(64,90))) || CHR(CEIL(SYS.DBMS_RANDOM.VALUE(64,90))) || TRUNC(DBMS_RANDOM.VALUE(100,999)) || CHR(CEIL(SYS.DBMS_RANDOM.VALUE(64,90))) || CHR(CEIL(SYS.DBMS_RANDOM.VALUE(64,90)));
  
    insert into accounts values(v_user,v_parola);
    v_parola := '';
    v_user := '';
    
    insert into RELATII1 VALUES (2*v_index-1,v_index,2*v_index-1);
    insert into RELATII1 VALUES (2*v_index,v_index,2*v_index);
  v_auxiliar3 := v_auxiliar3 + 1;
  v_nr_matricol_auxiliar := '';
  v_auxiliar := v_auxiliar + 1;
  v_prenume_elev := '';
  v_prenume1_elev := '';
  v_prenume2_elev := '';
  v_nume_elev := '';
  v_profil := '';
  v_clasa := '';
  v_numar_matricol := '';
  
  END LOOP;
  DBMS_OUTPUT.PUT_LINE('<------GATA------>');
END;
/
clear screen;
/
select * from elevi1;
/
select * from relatii1;
/
select * from parinti1;
/
select * from profesori1;
/
select * from accounts;
/
select * from materii1;
/
select * from teze1;
/
select * from activitate1;
/
select count(*) from activitate1;
/
select count(*) from elevi1;
/
select count(*) from relatii1;
/
