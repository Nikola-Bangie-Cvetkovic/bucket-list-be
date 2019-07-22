create table clients
(
  id       int auto_increment
    primary key,
  name     varchar(50)            not null,
  email    varchar(100)           not null,
  password varchar(512)           not null,
  created  timestamp              not null,
  updated  timestamp              not null,
  enabled  tinyint(1) default '0' not null,
  constraint clients_email_uindex
    unique (email),
  constraint clients_name_uindex
    unique (name)
);

create table buckets
(
  id        int auto_increment
    primary key,
  title     varchar(30)            not null,
  text      varchar(1000)          null,
  created   timestamp              not null,
  updated   timestamp              not null,
  finished  tinyint(1) default '0' not null,
  client_id int                    not null,
  constraint buckets_ibfk_1
    foreign key (client_id) references clients (id)
);

create index client_id
on buckets (client_id);
