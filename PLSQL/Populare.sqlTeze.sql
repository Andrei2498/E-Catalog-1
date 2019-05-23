set SERVEROUTPUT ON;
/

clear screen;
/

drop table Teze cascade constraints
/

create table Teze(
  id_elev int not null,
  id_materie int not null,
  id_profesor int not null,
  nota int not null,
  data_notare date not null,
  
  constraint fk_Teze_id_elev foreign key (id_elev) references Elevi(id),
  constraint fk_Teze_id_profesor foreign key (id_profesor) references Profesori(id),
  constraint fk_Teze_id_materie foreign key (id_materie) references Materii(id)
)
/

declare 
    cursor profesori is select * from Profesori;
    cursor elevi is select * from Elevi;
    TYPE myString IS VARRAY(5000) OF varchar2(255);
    
    lista_materii_real myString := myString('Matematica', 'Limba_Romana','Informatica');
    lista_materii_optionale_real myString := myString('Fizica','Chimie','Biologie');
    
    lista_materii_uman myString := myString('Limba Romana','Limba Engleza','Istorie');
    
    c_nume9 varchar2(30) := 'Logica';
    c_nume10 varchar2(40) := 'Psihologie';
    c_nume11 varchar2(40) := 'Filozofie';
    c_nume12 varchar2(60) := 'Economie';
    
    v_clasaElev int := 0;
    v_profilElev varchar2(20) := '';
    v_idProf int := 0;
    v_idMaterie int := 0;
  
    v_inserDate date;
    v_zi int :=0;
    v_luna int := 0;
    v_idLiceu int :=0;
    
    v_valoareNota int := 0;
    v_random int := 0;
    v_iterator int := 0;
    
    v_test int := 0;
    v_numeMaterie varchar2(100) :='';
begin 
  for v_indexE in elevi loop
    v_profilElev := v_indexE.profil;
    v_idLiceu := v_indexE.id_liceu;
    if( substr(v_indexE.clasa,1,1) = '9' ) then
      v_clasaElev := substr(v_indexE.clasa,1,1);
    else 
      v_clasaElev := substr(v_indexE.clasa,1,2);
    end if;
    if(v_profilElev = 'real') then 
      
      for v_iterare in 1..lista_materii_real.count loop
        
        select id into v_test from Materii where nume like lista_materii_real(v_iterare) and clasa = v_clasaElev and profil like 'real';
        
        select id into v_idProf from Profesori where id_materie = v_test and id_liceu = v_idLiceu;
        
        v_luna := ceil(dbms_random.value(1,4));
        
        case v_luna
          when 2 then v_zi := ceil(dbms_random.value(0,28));
          when 3 then v_zi := ceil(dbms_random.value(0,31));
          else v_zi := ceil(dbms_random.value(0,30));
        end case;
        
        v_valoareNota := ceil(dbms_random.value(5,10));
        
        v_inserDate := to_date(to_char(v_zi|| ' ' || v_luna || ' 2019'),'dd.mm.yyyy');
        
        insert into Teze values(v_indexE.id,v_test,v_idProf,v_valoareNota,v_inserDate);
        
      end loop;
      
      v_numeMaterie := lista_materii_optionale_real(trunc(dbms_random.value(0,lista_materii_optionale_real.count)) + 1);
      
      select id into v_test from Materii where nume like v_numeMaterie and clasa = v_clasaElev and profil like 'real';
      
      select id into v_idProf from Profesori where id_materie = v_test and id_liceu = v_idLiceu;
        
      v_luna := ceil(dbms_random.value(1,4));
        
      case v_luna
        when 2 then v_zi := ceil(dbms_random.value(0,28));
        when 3 then v_zi := ceil(dbms_random.value(0,31));
        else v_zi := ceil(dbms_random.value(0,30));
      end case;
        
      v_valoareNota := ceil(dbms_random.value(5,10));
        
      v_inserDate := to_date(to_char(v_zi|| ' ' || v_luna || ' 2019'),'dd.mm.yyyy');
      
      insert into Teze values(v_indexE.id,v_test,v_idProf,v_valoareNota,v_inserDate);
      
    else 
      
      for v_iterare in 1..lista_materii_uman.count loop
      
        select id into v_test from Materii where nume like lista_materii_uman(v_iterare) and clasa = v_clasaElev and profil like 'uman';
        
        select id into v_idProf from Profesori where id_materie = v_test and id_liceu = v_idLiceu;
        
        v_luna := ceil(dbms_random.value(1,4));
        
        case v_luna
          when 2 then v_zi := ceil(dbms_random.value(0,28));
          when 3 then v_zi := ceil(dbms_random.value(0,31));
          else v_zi := ceil(dbms_random.value(0,30));
        end case;
        
        v_valoareNota := ceil(dbms_random.value(5,10));
        
        v_inserDate := to_date(to_char(v_zi|| ' ' || v_luna || ' 2019'),'dd.mm.yyyy');
        
        insert into Teze values(v_indexE.id,v_test,v_idProf,v_valoareNota,v_inserDate);
        
      end loop;
      
      case v_clasaElev
        when 9 then select id into v_test from Materii where nume like c_nume9  and clasa = v_clasaElev and profil like 'uman';
        when 10 then select id into v_test from Materii where nume like c_nume10  and clasa = v_clasaElev and profil like 'uman';
        when 11 then select id into v_test from Materii where nume like c_nume11  and clasa = v_clasaElev and profil like 'uman';
        else select id into v_test from Materii where nume like c_nume12  and clasa = v_clasaElev and profil like 'uman';
      end case;
      select id into v_idProf from Profesori where id_materie = v_test and id_liceu = v_idLiceu;
        
      v_luna := ceil(dbms_random.value(1,4));
        
      case v_luna
        when 2 then v_zi := ceil(dbms_random.value(0,28));
        when 3 then v_zi := ceil(dbms_random.value(0,31));
        else v_zi := ceil(dbms_random.value(0,30));
      end case;
        
      v_valoareNota := ceil(dbms_random.value(5,10));
        
      v_inserDate := to_date(to_char(v_zi|| ' ' || v_luna || ' 2019'),'dd.mm.yyyy');
      
      insert into Teze values(v_indexE.id,v_test,v_idProf,v_valoareNota,v_inserDate);
      
    end if;
  
  end loop;
  
end;
/