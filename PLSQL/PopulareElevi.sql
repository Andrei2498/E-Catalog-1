--set serveroutput on;
--/
--set serveroutput on buffer 2560000;
--/


clear screen;
/
drop table Conturi cascade constraints
/
create table Conturi(
  username varchar2(100) not null,
  password varchar2(15) not null,
  id_persoana int not null
)
/
drop table Elevi cascade constraints
/
create table Elevi(
  id int not null primary key,
  nume varchar2(20) not null,
  prenume varchar2(50) not null,
  gen varchar2(20) not null,
  varsta int not null,
  adresa varchar2(200) not null,
  nr_telefon varchar2(12) not null,
  email varchar2(100) not null,
  id_liceu int not null,
  clasa varchar2(3) not null,
  profil varchar2(10) not null,
  
  CONSTRAINT fk_Elevi_id_liceu FOREIGN KEY (id_liceu) REFERENCES Liceu(id)
)
/


declare
  TYPE myString IS VARRAY(5000) OF varchar2(255);
  nume myString := myString('Pirlog','Dinescu','Puiu','Poenaru','Ionesco','Stoenescu','Boroi','Cinca','Bratianu','Florea','Olaru'
              ,'Pavel','Neagoe','Nica','Tilea','Banciu','Hila','Cristea','Dobre','Vladimirescu','Cojocaru','Gaina','Stolojan'
              ,'Dumitrescu','Martinecu','Mocanu','Nistor','Manole','Dumitru','Pop','Minea','Avramescu','Dimir','Munteanu'
              ,'Lazarescu','Babes','Goga','Dragan','Ilionescu','Antonescu','Mircea','Mihnea','Albu','Mitu','Lupei','Ghita','Ungur'
              ,'Ungureanu','Enescu','Moisuc','Lascar','Alexanndrei','Ayaey','Agae','Agape','Alexeyi','Alexiis','Amarghioaleici','Ambrocian','Andoneseiscu',
                'Anita','Antochian','Antoniu','Bagaene','Bejenariele','Balcescundru','Buducu','Caimanel','Carbunel','Carpel',
                'Catanescu','Cerbescu','Craciun','Damiane','Damoce','Daneliuce','Danielescu','Danilia','Eduardo','Eustache','Freraruc','Ferestraoarul',
                'Fierarulmetin','Filimondru','Filipel','Florescuol','Folrevaiter','Framosuc','Freunza','Garcea','Ghergu','Ghermansil','Ghibirdice','Giosaenu','Girtlan','Giurgilae',
                'Grozavescu','Guramare','Habascu','Haraebunagluma','Hardonoff','Harpa','Heidel','Herscovician', 'Iancuel','Ichimandrascu','Iftimeseieieie','Ilie','Insuratelunu','Ioneseiie','Ionesiscu','Ionitael',
                'Jitarriucu','Jitcaman','Joldrescuboi','Juravled','Larionuaiuardsan','Lataescu','Latuaman','Lazarescu','Leleleu', 'Lunguscurt','Lupascua','Lupu','Macaroriu','Macoveschielel','Maftei','Maganues','Mangalagiugiu',
                'Matiescu','Matranana','Maximminim','Mazareanuun','Mazilurel','Mazures','Melniciuc','Micumare', 'Minghelghel','Minuti','Mirondrei','Mitanna','Moisael','Moniry-abyaneh-ce','Morairescu',
                'Neghina','Negrus','Negruser','Negrutu','Nemtoc','Netedu','Nica', 'Palihocviciel','Pantiru','Pantiruc','Paparuz','Pascaru','Patachiel',
                'Podarue','Poenaariu','Pogjar','Popa','Popescu','Popovici','Povputoaia','Reut','Riscanu','Riza','Robue','Roman','Romanescu','Romaniuc','Rosca',
                'Sfarghiu','Silitra','Simiganoschi','Simion','Simionescu','Simionesei','Stefan','Stefanita','Stingaciu','Stiufliuc','Stoianu','Stoicael','Stoleru',
                'Teclicie','Teodorescu','Tesurel','Tifrea','Timofte','Tincu','Tirpescu','Toader', 'Ulinici','Unghianu','Ungureanu','Ursache','Ursachiu','Ursesl','Ursurel','Varlan',
                'Vinatoruel','Vladel','Voaides','Vrabianu','Vulpescu','Zamosteanu','Zazuleac');

  prenume_baieti myString := myString('Marcel','Alexandru','Andrei','Anghel','Avram','Silviu','Eduard','Calin','Raul','Stefan','Traian'
              ,'Ioan','Ionut','Denis','Sandu','Beniamin','Petre','Ovidiu','Flavius','Ion','Mihai','Vasile','Robert','Corneliu'
              ,'Gabriel','Horatiu','Valeriu','Iuliu','Alin','Neculai','Horia','Teo','Artur','Gavril','Luca','Cristian','David'
              ,'Costi','Constantin','Marku','Laurentiu','Cosmin','Mihail','Dumitru','Ivan','Carol','Octavian','Bogdan','Martin','Ghorghe'
              ,'Adrian','Bogdan','Camil','Catalin','Cezar','Ciprian','Claudiu','Codrin','Damian','Dan','Daniel','Danut','Darius','Denise'
              ,'Dimitrie','Elvis','Emil','Ervin','Eugen','Eusebiu','Fabian','Filip','Florian','Florin','Gabriel','George','Gheorghe','Giani'
              ,'Giulio','Iaroslav','Ilie','Ioan','Ion','Ionel','Ionut','Matei','Mihai','Mihail','Nicolae','Rares','Razvan','Richard','Robert'
              ,'Samuel','Sebastian','Sergiu','Silviu','Stefan','Teodor','Teofil','Theodor','Tudor','Victor','Vlad','Vladimir','Vladut');

  prenume_fete myString := myString('Ana','Alexandra','Marta','Madalina','Ileana','Narcisa','Dorina','Sorana','Magdalena','Sorina'
              ,'Corina','Voileta', 'Elisabeta','Aurica','Denisa','Luminita','Florina','Tatiana','Adelina','Ioana','Mihaela'
              ,'Cami','Ecaterina','Marcela','Oana','Sanda','Ligia','Claudia','Lavinia','Victoria','Voctorita','Antanasia'
              ,'Cornelia','Valerica','Carla','Rozalia','Veronica','Marilena','Bianca','Ioanela','Magda','Georgeta','Andreea'
              ,'Cecilia','Georgiana','Sabina','Nicoleta','Delia','Marioara','Stefania'
              ,'Adina','Alexandra','Claudia','Codrina','Cristina','Daniela','Daria','Delia','Denisa','Diana','Emma','Gabriela'
              ,'Georgiana','Ileana','Ilona','Ioana','Iolanda','Irina','Iulia','Iuliana','Larisa','Laura','Loredana','Madalina','Malina'
              ,'Manuela','Maria','Raluca','Sabina','Sanziana','Simina','Simona','Stefana','Stefania');
              
  sfarsitMail myString := myString('@yahoo.com','@gmail.com','@gmail.ro','@yahoo.ro','@yahoo.uk','@yahoo.us','@gmail.uk','@gmail.us','@ECatalog.ro','@ECatalog.com','@ECatalog.uk','@ECatalog.us');
  strazi myString := myString('Calea Independentei','B-dul. Mihai Eminescu','Str. Florilor','Str. Sinaia','Aleea Castanilor','P-ta Frasinului','Aleea Castanilor','Str. Ion Creanga','Calea Unirii','B-dul. Bega','B-dul. Frunzisului','Splaiul Crisan'
                      ,'B-dul. Florilor','P-ta Henri Coand?','B-dul. Mihai Eminescu','Aleea Constantin Brancusi','Aleea Pacurari','B-dul. Constantin','P-ta Salcâmilor','Splaiul Jiului','Splaiul Mesterilor','Aleea Jiului','Aleea Croitorilor',
                      'Splaiul Aurel Vlaicu','Aleea Memorandumului','B-dul. Eroilor','Calea Traian','P-ta Petrache Poenaru','P-ta Muncii','B-dul. Piersicului','P-ta Memorandumului','P-ta Unirii','B-dul. J.J Rousseau','P-ta Faget','Aleea Franklin Delano Rosevelt','Str. Muncii'
                      ,'Calea Mesterilor','Splaiul J.J Rousseau','P-ta Invatatorului','P-ta Ion Creanga','Calea Vlad Tepes','P-ta Castanilor','B-dul. Aurel Vlaicu','B-dul. Crisan','Aleea Ciresilor','B-dul. Somes','P-ta Ion Creanga','Str. Memorandumului','Calea Padis'
                      ,'P-ta 1 Decembrie','P-ta Mesterilor','Str. Mesterilor','Calea Decebal','Calea Traian','B-dul. J.J Rousseau','Calea Padurii','Aleea Mihai Eminescu','P-ta Sinaia','Calea Herculane','Str. Castanilor','Calea 1 Decembrie');  
  bloc myString := myString('-','1A','1B','1C','1D','1E','2A','2B','2C','2D','2E','-','3A','3B','3C','3D','3E','-','4A','4B','4C','4D','4E','-','5A','5B','5C','5D','5E');

  v_clasa_final varchar2(3) := ''; --
  v_clasa int := 0; --
  v_profil varchar2(5) := ''; --
  v_varsta int := 0; --
  v_email varchar2(80) := ''; --
  v_adresa varchar2(200) := '';  --
  v_telefon varchar2(12) := '0'; --
  v_gen varchar2(10) := ''; --
  v_nume varchar2(20) := ''; --
  v_prenume varchar2(50) := ''; --
  v_prenume1 varchar2(30) :=''; --
  v_bloc varchar2(3) := '';
  
  v_indexL int := 0; --
  v_indexE int := 0; --
  v_username varchar2(100) := 'E.'; --
  v_password varchar2(15) := ''; --
  
  v_number int := 0;
  c_nume int := nume.count;
  c_prenumeB int := prenume_baieti.count;
  c_prenumeF int := prenume_fete.count;
  c_mail int := sfarsitMail.count;
  c_strazi int := strazi.count;
  temp int := 0;
begin 
  for v_indexL in 1..25 loop
    
    for v_indexE in 1..2000 loop
      
      v_clasa := ceil(DBMS_RANDOM.VALUE(8,12));
      v_clasa_final := v_clasa || chr(ceil(dbms_random.value(64,71)));
      v_varsta := ceil(dbms_random.value(13,19));
      v_nume := nume(dbms_random.value(1,c_nume));
      v_username := v_username || lower(v_nume) || '.'; 
      v_email := lower(v_nume);
      v_telefon := v_telefon || ceil(dbms_random.value(0,9)) || ceil(dbms_random.value(0,9)) || ceil(dbms_random.value(0,9)) || ceil(dbms_random.value(0,9)) 
                             || ceil(dbms_random.value(0,9)) || ceil(dbms_random.value(0,9)) || ceil(dbms_random.value(0,9)) || ceil(dbms_random.value(0,9)) || ceil(dbms_random.value(0,9));
      
      IF (SYS.DBMS_RANDOM.VALUE(0,100) < 50) THEN
        v_prenume := prenume_baieti(TRUNC(SYS.DBMS_RANDOM.VALUE(0,c_prenumeB)) + 1);
        v_username := v_username || lower(v_prenume);
        v_email := v_email || '.' || lower(v_prenume);
        IF ((SYS.DBMS_RANDOM.VALUE(0,100) < 20 )) THEN
          v_prenume1 := prenume_baieti(TRUNC(SYS.DBMS_RANDOM.VALUE(0,c_prenumeB)) + 1);
        END IF;
        if(length(v_prenume1) > 0) then
          v_username := v_username || '.' || lower(v_prenume1);
          v_prenume := v_prenume || ' ' || v_prenume1;
          v_email := v_email || '.' || lower(v_prenume1);
        end if;
        v_gen := 'Masculin';
      ELSE
        v_prenume := prenume_fete(TRUNC(SYS.DBMS_RANDOM.VALUE(0,c_prenumeF)) + 1);
         v_username := v_username || lower(v_prenume);
        v_email := v_email || '.' || lower(v_prenume);
        IF ((SYS.DBMS_RANDOM.VALUE(0,100) < 20 )) THEN
          v_prenume1 := prenume_fete(TRUNC(SYS.DBMS_RANDOM.VALUE(0,c_prenumeF)) + 1);
        END IF;
        if(length(v_prenume1) > 0 )then
          v_prenume := v_prenume || ' ' || v_prenume1;
          v_username := v_username || '.' || lower(v_prenume1);
          v_email := v_email || '.' || lower(v_prenume1);
        end if;
        v_gen := 'Feminin';
      END IF;
      
      v_email := v_email  || v_indexL * v_indexE || sfarsitMail(ceil(dbms_random.value(0,c_mail)));
      
      if(DBMS_RANDOM.VALUE(0,1) < 0.5) then
          v_profil := 'uman';
      else
          v_profil := 'real';
      end if;
        v_password := ceil(dbms_random.value(100,999)) || chr(ceil(dbms_random.value(64,90))) || chr(ceil(dbms_random.value(64,90)))  || chr(ceil(dbms_random.value(64,90))) 
                      || ceil(dbms_random.value(100,999)) || chr(ceil(dbms_random.value(64,90)))  || chr(ceil(dbms_random.value(64,90)))  || chr(ceil(dbms_random.value(64,90)));
      
      v_adresa := strazi(ceil(dbms_random.value(0,c_strazi))) || '>>';
      
      v_bloc := bloc(ceil(dbms_random.value(0,bloc.count)));
      
      if(v_bloc = '-') then
        v_adresa := v_adresa || '->>->>' || ceil(dbms_random.value(0,400));
        
      else
        v_adresa := v_adresa || v_bloc || '>>' || ceil(dbms_random.value(0,10)) || chr(ceil(dbms_random.value(64,69))) || '>>' || ceil(dbms_random.value(0,400));
      end if;
      
--      dbms_output.put_line(v_indexL + v_indexE + temp - 1);
      insert into Elevi values (v_indexL + v_indexE + temp - 1, v_nume,v_prenume,v_gen,v_varsta,v_adresa,v_telefon,v_email,v_indexL,v_clasa_final,v_profil);
      insert into Conturi values(v_username,v_password,v_indexL + v_indexE + temp - 1);
      v_number := 0;
      v_telefon := '0';
      v_adresa := '';
      v_bloc := '';
      v_nume := '';
      v_prenume := '';
      v_prenume1 := '';
      v_gen := '';
      v_profil := ''; 
      v_username := 'E.';
      v_password := '';
      v_email := '';
    end loop;
    temp := temp + 1999;
  end loop;
end;
/


