create or replace function MedieGenerala(p_id_elev int)
return varchar2 as
   cursor c_materii is (select id from MATERII 
      where clasa = (select ( case when substr(clasa,1,1) = '9' then substr(clasa,1,1) 
                                                                else substr(clasa,1,2) end )  
                from elevi where id = p_id_elev)
      and profil like (select profil from elevi where id = p_id_elev));
   v_medie_finala number := 0;
   v_medie_curenta number := 0;
    v_try int := 0;
    v_teza int := 0;
    v_temporar number := 0;
    v_iterator int :=0;
    r_result varchar2(10) := '';
begin
    for v_index in c_materii loop
      select count(*) into v_try from teze where id_elev = p_id_elev and id_materie = v_index.id;

      if(v_try > 0) then
        select avg(nota) into v_temporar from activitate where ID_ELEV = p_id_elev and id_materie = v_index.id and nota is not null;
        select nota into v_teza from teze where id_materie = v_index.id and ID_ELEV = p_id_elev;
    
        v_medie_curenta := ((v_temporar) * 3 + v_teza) / 4;
    
        v_medie_finala := v_medie_finala + v_medie_curenta;
                
        v_iterator := v_iterator + 1;
      else 
        select avg(nota) into v_medie_curenta from activitate where ID_ELEV = p_id_elev and id_materie = v_index.id
          and nota is not null;
        v_medie_finala := v_medie_finala + v_medie_curenta;
        v_iterator := v_iterator + 1;
      end if;      

    end loop;
    v_medie_finala := v_medie_finala / v_iterator;
    r_result := to_char(trunc(v_medie_finala,6));
    return r_result;
end;
/


create or replace function NumarAbsenteLaOMaterie(p_id_materie int, p_id_elev int)
return int as
    v_rezultat int := 0;
begin
    select count(*) into v_rezultat from ACTIVITATE where id_elev = p_id_elev and id_materie = p_id_materie and nota is null;
    
    return v_rezultat;
end;
/

create or replace function MedieMaterieCuTeza(p_nume_materie varchar2, p_id_elev int)
return number as
    v_clasa int := 0;
    v_profile varchar2(10) := '';
    v_rezultat number;
    v_id_materie int := 0;
    v_teza int := 0;
    v_temporar number;
begin
    select clasa,profil into v_clasa,v_profile from elevi where id = p_id_elev;
    select id into v_id_materie from materii where nume like p_nume_materie and clasa = v_clasa and profil like v_profile;
    select avg(nota) into v_temporar from activitate where ID_ELEV = p_id_elev and id_materie = v_id_materie
      and nota is not null;
    select nota into v_teza from teze where id_materie = v_id_materie and ID_ELEV = p_id_elev;
    
    v_rezultat := ((v_temporar) * 3 + v_teza) / 4;
    
    return v_rezultat;
end;
/

create or replace function MedieMaterie(p_nume_materie varchar2, p_id_elev int)
return number as
    v_clasa int := 0;
    v_profile varchar2(10) := '';
    v_rezultat number;
    v_id_materie int := 0;
    v_try int := 0;
    v_teza int := 0;
    v_temporar number;
begin
    select clasa,profil into v_clasa,v_profile from elevi where id = p_id_elev;
    select id into v_id_materie from materii where nume like p_nume_materie and clasa = v_clasa and profil like v_profile;
    
    select count(*) into v_try from teze where id_elev = p_id_elev and id_materie = v_id_materie;
    
    if(v_try > 0) then
      select avg(nota) into v_temporar from activitate where ID_ELEV = p_id_elev and id_materie = v_id_materie
        and nota is not null;
      select nota into v_teza from teze where id_materie = v_id_materie and ID_ELEV = p_id_elev;
    
      v_rezultat := ((v_temporar) * 3 + v_teza) / 4;
    
      return v_rezultat;
    else 
      select avg(nota) into v_rezultat from activitate where ID_ELEV = p_id_elev and id_materie = v_id_materie
        and nota is not null;
      return v_rezultat;
    end if;
    
end;
/

create or replace function Login(p_sir_car varchar2)
return varchar2 as
  v_rezultat varchar2(1000) := '';
  v_username varchar2(100) := '';
  v_password varchar2(100) := '';
  v_index int := 0;
  v_index1 int := 1;
  v_curent_char varchar2(1) := 'Z';
  v_nume varchar2(100) := '';
  v_prenume varchar2(100) := '';
  v_id int := 0;
begin
  while ( v_index <= length(p_sir_car) and v_curent_char != ' ' ) loop
    v_curent_char := substr(p_sir_car,v_index,1);
    v_index := v_index + 1;
  end loop;
  v_username := substr(p_sir_car,1,v_index - 2);
  v_password := substr(p_sir_car,v_index);
  
  select count(*) into v_index from Conturi where username like v_username and Password like v_password;
  select id_persoana into v_id from Conturi where username like v_username and Password like v_password;
  if(v_index = 1 ) then
    if (substr(v_username,1,2) = 'E.') then
      v_index := 3;
      v_curent_char := 'Z';
      v_index1 := instr(substr(v_username,3),'.');
      v_nume := upper(substr(v_username,3,1)) || substr(v_username,4,v_index1 - 2);
      v_username := substr(v_username,v_index1 + v_index);
      v_index := instr(v_username,'.');
      if(v_index > 0) then
        v_prenume := upper(substr(v_username,1,1)) || substr(v_username,2,v_index - 2) || ' '
          || upper(substr(v_username,v_index + 1,1)) || substr(v_username,v_index + 2);
      else
        v_prenume := upper(substr(v_username,1,1)) || substr(v_username,2,length(v_username) - 1);
      end if;
      select id || ' ' || nume || ' ' || prenume || ' '|| profil || 
        ' ' || clasa || ' ' || adresa || ' ' || nr_telefon into v_rezultat from ELEVI where nume like v_nume and prenume like v_prenume and id = v_id;
    elsif (substr(v_username,1,2) = 'P.') then
      v_index := 3;
      v_curent_char := 'Z';
      v_index1 := instr(substr(v_username,3),'.');
      v_nume := upper(substr(v_username,3,1)) || substr(v_username,4,v_index1 - 2);
      v_username := substr(v_username,v_index1 + v_index);
      v_index := instr(v_username,'.');
      if(v_index > 0) then
        v_prenume := upper(substr(v_username,1,1)) || substr(v_username,2,v_index - 2) || ' '
          || upper(substr(v_username,v_index + 1,1)) || substr(v_username,v_index + 2);
      else
        v_prenume := upper(substr(v_username,1,1)) || substr(v_username,2);
      end if;
      select id || ' ' || nume || ' ' || prenume || ' '  || email 
        into v_rezultat from profesori where nume like v_nume and prenume like v_prenume and id = v_id;
    else 
      v_index := 4;
      v_curent_char := 'Z';
      v_index1 := instr(substr(v_username,4),'.');
      v_nume := upper(substr(v_username,4,1)) || substr(v_username,5,v_index1 - 2);
      v_username := substr(v_username,v_index1 + v_index );
      v_index := instr(v_username,'.');
      if(v_index > 0) then
        v_prenume := upper(substr(v_username,1,1)) || substr(v_username,2,v_index - 2) || ' '
          || upper(substr(v_username,v_index + 1,1)) || substr(v_username,v_index + 2);
      else
        v_prenume := upper(substr(v_username,1,1)) || substr(v_username,2);
      end if;
      select id || ' ' || nume || ' ' || prenume || ' '  || id_elev
        into v_rezultat from parinti where nume like v_nume and prenume like v_prenume and id = v_id;
    end if;
  ELSIF (v_index = 0 ) then
    v_rezultat := 'Contul introdus este incorect.';
  else
    v_rezultat := 'Ceva nu a funtionat corect. Va rugam reincercati.';
  end if;
  dbms_output.put_line(v_index);
  return v_rezultat;
end;
/

create or replace function NumarAbsenteTotale(p_id_elev int)
return int as
    v_rezultat int := 0;
begin
    select count(*) into v_rezultat from ACTIVITATE where id_elev = p_id_elev and nota is null;
    
    return v_rezultat;
end;
/





declare
  verde varchar2(50) := '';
begin
  select AVGPACKAGES.MEDIEMATERIEFARATEZA('Mast3123ematica',1) into verde from dual;
  
  dbms_output.put_line('rez: ' || verde);
end;
/
  
  
select nota from activitate where id_elev = 1 and id_materie = 60;
/
  
clear screen;
/

set serveroutput on;
/