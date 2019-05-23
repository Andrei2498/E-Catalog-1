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
create or replace trigger protect_for_destroy
  before drop on ECatalog.SCHEMA
  declare
    PRAGMA AUTONOMOUS_TRANSACTION;
  begin
    RAISE_APPLICATION_ERROR(-20400, 'Aceasta actiune este strict interzisa.');
        execute immediate 'rollback';
end protect_for_destroy;
/  

create or replace trigger protect_Accounts
  before insert or delete or update on Conturi
  declare
    PRAGMA AUTONOMOUS_TRANSACTION;
  begin
    RAISE_APPLICATION_ERROR(-20100, 'Aceasta actiune este strict interzisa.');
        execute immediate 'rollback';
end protect_Accounts;
/

create or replace trigger protect_Elevi
  before insert or delete or update on Elevi
  declare
    PRAGMA AUTONOMOUS_TRANSACTION;
  begin
    RAISE_APPLICATION_ERROR(-20101, 'Aceasta actiune este strict interzisa.');
        execute immediate 'rollback';
end protect_Elevi;
/

create or replace trigger protect_Materii
  before insert or delete or update on Materii
  declare
    PRAGMA AUTONOMOUS_TRANSACTION;
  begin
    RAISE_APPLICATION_ERROR(-20102, 'Aceasta actiune este strict interzisa.');
        execute immediate 'rollback';
end protect_Materii;
/

create or replace trigger protect_Profesori
  before insert or delete or update on Profesori
  declare
    PRAGMA AUTONOMOUS_TRANSACTION;
  begin
    RAISE_APPLICATION_ERROR(-20103, 'Aceasta actiune este strict interzisa.');
        execute immediate 'rollback';
end protect_Profesori;
/

create or replace trigger protect_Parinti
  before insert or delete or update on Parinti
  declare
    PRAGMA AUTONOMOUS_TRANSACTION;
  begin
    RAISE_APPLICATION_ERROR(-20104, 'Aceasta actiune este strict interzisa.');
        execute immediate 'rollback';
end protect_Parinti;
/

create or replace trigger protect_Liceu
  before insert or delete or update on Liceu
  declare
    PRAGMA AUTONOMOUS_TRANSACTION;
  begin
    RAISE_APPLICATION_ERROR(-20105, 'Aceasta actiune este strict interzisa.');
        execute immediate 'rollback';
end protect_Liceu;
/

create or replace trigger safe_bursieri
  before insert on Bursieri
  declare
    v_medie number := 0;
    
    pragma AUTONOMOUS_TRANSACTION;
  begin
    select mediegenerala(:new.id_elev) into v_medie from dual;
end safe_bursieri;
/

alter trigger protect_for_destroy disable;
/

clear screen;
/

set serveroutput on;
/

declare
  v_result varchar2(300) := '';
begin
  select Login('E.lupei.neculai 993FAG387WYJ') into v_result from dual;
  
  dbms_output.put_line(v_result);
end;
/