declare 
    cursor elevi is select * from ELEVI;
    v_idElev int :=0;
    v_valoare number := 0;
    v_medie number := 0;
    v_iterare int := 0;
    v_abs int := 0;
begin
    for v_iterator in elevi loop
    
--        v_medie := MEDIEGENERALA(v_iterator.id);    
      select avg(nota) into v_medie from activitate where id_elev = v_iterator.id;
      select count(*) into v_abs from activitate where id_elev = v_iterator.id;
      if v_medie > 7.9 and v_abs < 130 then
          
          v_valoare := ceil(dbms_random.value(400,1000));
          insert into BURSIERI values(v_iterator.id,v_valoare);
--        DBMS_OUTPUT.put_line(v_medie || ' ' ||  );
      end if;
      
    end loop;
    
end;
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
    v_i1 int := 0;
    v_testt int := 0;
    v_test2 int := 0;

begin
  for v_indexElev in elevi loop
    v_profilElev := v_indexElev.profil;
    v_idLiceu := v_indexElev.id_liceu;
    if( substr(v_indexElev.clasa,1,1) = '9' ) then
      v_clasaElev := substr(v_indexElev.clasa,1,1);
    else 
      v_clasaElev := substr(v_indexElev.clasa,1,2);
    end if;
    for v_indexMaterii in materii loop
    
      if(v_indexMaterii.clasa = v_clasaElev and v_indexMaterii.profil = v_profilElev) then
          
          iF dbms_random.value(0,1) > 0.7 then 
          v_testt := ceil(dbms_random.value(0,3));
--        DBMS_OUTPUT.put_line(v_testt);
              if(v_testt = 1) then
                v_test2 := ceil(dbms_random.value(0,2));
                for v_i1 in 1..v_test2 loop
          
              v_luna := ceil(dbms_random.value(1,4));

              case v_luna
                when 2 then v_zi := ceil(dbms_random.value(0,28));
                when 3 then v_zi := ceil(dbms_random.value(0,31));
                else v_zi := ceil(dbms_random.value(0,30));
              end case;
        
              v_inserDate := to_date(to_char(v_zi|| ' ' || v_luna || ' 2019'),'dd.mm.yyyy');

              select id into v_idProf from Profesori where id_liceu = v_idLiceu and id_materie = v_indexMaterii.id;
          
              v_valoareNota := ceil(dbms_random.value(0,4));
          
              insert into Activitate values (v_indexElev.id,v_indexMaterii.id,v_idProf,v_valoareNota,v_inserDate);

            end loop;
          end if;
          
        elsif (v_testt = 2) then 
          v_test2 := ceil(dbms_random.value(0,2));
          for v_i1 in 1..v_test2 loop
          
            v_luna := ceil(dbms_random.value(1,4));
        
            case v_luna
              when 2 then v_zi := ceil(dbms_random.value(0,28));
              when 3 then v_zi := ceil(dbms_random.value(0,31));
              else v_zi := ceil(dbms_random.value(0,30));
            end case;
        
            v_inserDate := to_date(to_char(v_zi|| ' ' || v_luna || ' 2019'),'dd.mm.yyyy');

            select id into v_idProf from Profesori where id_liceu = v_idLiceu and id_materie = v_indexMaterii.id;
          
            v_valoareNota := ceil(dbms_random.value(7,10));
          
            insert into Activitate values (v_indexElev.id,v_indexMaterii.id,v_idProf,v_valoareNota,v_inserDate);
          end loop;
        
        end if;

      end if;
    
    end loop;
  
  end loop;

end;
/

set SERVEROUTPUT ON;
/

clear screen;
/

drop table Bursieri cascade constraints
/
create table Bursieri(
  id_elev int not null,
  valoare int not null,
  
  constraint fk_Bursieri_id_elev foreign key (id_elev) references Elevi(id)
)
/

select count(*) from activitate;
/


create index avg_info on elevi(id,clasa,profil);
/
create index materii_index on materii(id,clasa,profil);
/
create index note_elevi on activitate(id_elev,nota);
/
create index note_per_materie on activitate(id_elev,id_materie,nota);
/
create index note_de_afisat on activitate(id_elev,id_materie,nota,data_notare);
/
create index medi_pe_materie on activitate(id_materie,nota);
/

select nota from activitate where nota = 4 order by nota asc;
/