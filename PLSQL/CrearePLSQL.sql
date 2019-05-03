set SERVEROUTPUT ON;
/





drop table Activitate cascade constraints
/
drop table Bursieri cascade constraints
/
drop table Teze cascade constraints
/


create table Bursieri(
  id int not null primary key,
  id_elev int not null,
  valoare int not null,
  
  constraint fk_Bursieri_id_elev foreign key (id_elev) references Elevi(id)
)
/

create table Activitate(
  id_elev int not null,
  id_materie int not null,
  id_profesor int not null,
  nota int not null,
  data_notare date not null,
  
  constraint fk_Activitate_id_elev foreign key (id_elev) references Elevi(id),
  constraint fk_Activitate_id_profesor foreign key (id_profesor) references Profesori(id),
  constraint fk_Activitate_id_materie foreign key (id_materie) references Materii(id)
)
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

