
clear screen;
/

drop table Activitate cascade constraints
/

create table Activitate(
  id_elev int not null,
  id_materie int not null,
  id_profesor int not null,
  nota int,
  data_notare date not null,
  
  constraint fk_Activitate_id_elev foreign key (id_elev) references Elevi(id),
  constraint fk_Activitate_id_profesor foreign key (id_profesor) references Profesori(id),
  constraint fk_Activitate_id_materie foreign key (id_materie) references Materii(id)
)
/

declare 

    cursor materii is select * from Materii;
    cursor profesori is select * from Profesori;
    cursor elevi is select * from Elevi;
    
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
begin 

  for v_indexE in elevi loop
    
    v_profilElev := v_indexE.profil;
    v_idLiceu := v_indexE.id_liceu;
    if( substr(v_indexE.clasa,1,1) = '9' ) then
      v_clasaElev := substr(v_indexE.clasa,1,1);
    else 
      v_clasaElev := substr(v_indexE.clasa,1,2);
    end if;
    for v_indexMaterii in materii loop
    
      if(v_indexMaterii.clasa = v_clasaElev and v_indexMaterii.profil = v_profilElev) then
      
        v_random := ceil(dbms_random.value(3,6));
      
        for v_iterator in 1..v_random loop
        
          v_luna := ceil(dbms_random.value(1,4));
        
          case v_luna
            when 2 then v_zi := ceil(dbms_random.value(0,28));
            when 3 then v_zi := ceil(dbms_random.value(0,31));
            else v_zi := ceil(dbms_random.value(0,30));
          end case;
        
          v_inserDate := to_date(to_char(v_zi|| ' ' || v_luna || ' 2019'),'dd.mm.yyyy');
          
          select count(*) into v_test from Activitate where id_materie = v_indexMaterii.id and id_elev = v_indexE.id and data_notare = v_inserDate;
          
          while(v_test > 0) loop
          
            case v_luna
              when 2 then v_zi := ceil(dbms_random.value(0,28));
              when 3 then v_zi := ceil(dbms_random.value(0,31));
              else v_zi := ceil(dbms_random.value(0,30));
            end case;
          
            v_inserDate := to_date(to_char(v_zi|| ' ' || v_luna || ' 2019'),'dd.mm.yyyy');
            
            select count(*) into v_test from Activitate where id_materie = v_indexMaterii.id and id_elev = v_indexE.id and data_notare = v_inserDate;
          
          end loop;
          
          select id into v_idProf from Profesori where id_liceu = v_idLiceu and id_materie = v_indexMaterii.id;
          
          v_valoareNota := ceil(dbms_random.value(4,10));
          
          insert into Activitate values (v_indexE.id,v_indexMaterii.id,v_idProf,v_valoareNota,v_inserDate);
          
          v_test := 0;
        end loop;
        v_test := 0;
        v_random := ceil(dbms_random.value(3,6));
      
        for v_iterator in 1..v_random loop
        
          v_luna := ceil(dbms_random.value(1,4));
        
          case v_luna
            when 2 then v_zi := ceil(dbms_random.value(0,28));
            when 3 then v_zi := ceil(dbms_random.value(0,31));
            else v_zi := ceil(dbms_random.value(0,30));
          end case;
        
          v_inserDate := to_date(to_char(v_zi|| ' ' || v_luna || ' 2019'),'dd.mm.yyyy');
          
          select count(*) into v_test from Activitate where id_materie = v_indexMaterii.id and id_elev = v_indexE.id and data_notare = v_inserDate;
          
          while(v_test > 0) loop
          
            case v_luna
              when 2 then v_zi := ceil(dbms_random.value(0,28));
              when 3 then v_zi := ceil(dbms_random.value(0,31));
              else v_zi := ceil(dbms_random.value(0,30));
            end case;
          
            v_inserDate := to_date(to_char(v_zi|| ' ' || v_luna || ' 2019'),'dd.mm.yyyy');
            
            select count(*) into v_test from Activitate where id_materie = v_indexMaterii.id and id_elev = v_indexE.id and data_notare = v_inserDate;
          
          end loop;
          
          select id into v_idProf from Profesori where id_liceu = v_idLiceu and id_materie = v_indexMaterii.id;
          
          v_valoareNota := ceil(dbms_random.value(4,10));
          
          insert into Activitate values (v_indexE.id,v_indexMaterii.id,v_idProf,null,v_inserDate);
          
          v_test := 0;
        end loop;
      end if;
      
    end loop;
  
  end loop;

end;
/