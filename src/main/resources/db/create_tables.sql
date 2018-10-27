create table if not exists duration
(
	id serial not null
		constraint duration_pkey
			primary key,
	number_days integer default 0 not null,
	name varchar(250)
)
;

create unique index if not exists duration_id_uindex
	on duration (id)
;

create table if not exists place
(
	id serial not null
		constraint place_pkey
			primary key,
	name varchar(250) not null
		constraint place_name_key
			unique,
	descr text
)
;

create unique index if not exists place_id_uindex
	on place (id)
;

create table if not exists subject
(
	id serial not null
		constraint subject_pkey
			primary key,
	name varchar(250) not null
		constraint subject_name_key
			unique,
	descr text
)
;

create unique index if not exists subject_id_uindex
	on subject (id)
;

create table if not exists tour
(
	id serial not null
		constraint tour_pkey
			primary key,
	name varchar(250) not null
		constraint tour_name_key
			unique,
	album_guid char(60) not null,
	youtube_url varchar(50),
	descr text
)
;

create unique index if not exists tour_id_uindex
	on tour (id)
;

create table if not exists tour_place
(
	tour_id integer not null
		constraint tour_place_tour_id_fk
			references tour
				on update cascade,
	place_id integer not null
		constraint tour_place_place_id_fk
			references place
				on update cascade
)
;

create table if not exists tour_release
(
	id serial not null
		constraint tour_release_pkey
			primary key,
	tour_id integer not null
		constraint tour_release_tour_id_fk
			references tour
				on update cascade,
	duration_id integer not null
		constraint tour_release_duration_id_fk
			references duration
				on update cascade,
	begin_time timestamp not null,
	capacity integer not null
)
;

create table if not exists tour_cost
(
	id serial not null
		constraint tour_cost_pkey
			primary key,
	tour_release_id integer not null
		constraint tour_cost_tour_release_id_fk
			references tour_release
				on update cascade,
	kind boolean default true not null,
	cost double precision not null,
	clipping_age integer,
	is_participant boolean default true not null
)
;

create unique index if not exists tour_cost_id_uindex
	on tour_cost (id)
;

create unique index if not exists tour_release_id_uindex
	on tour_release (id)
;

create table if not exists tour_subject
(
	tour_id integer not null
		constraint tour_subject_tour_id_fk
			references tour
				on update cascade,
	subject_id integer not null
		constraint tour_subject_subject_id_fk
			references subject
				on update cascade
)
;

create table if not exists users
(
	id serial not null
		constraint users_pkey
			primary key,
	login varchar(250) not null
		constraint users_login_key
			unique,
	pass varchar(60) not null,
	fio varchar(250),
	call_time timestamp,
	subscribe boolean default false,
	bonus double precision default 0,
	album_guid varchar(60),
	is_active boolean
)
;

create table if not exists contact
(
	id serial not null
		constraint contact_pkey
			primary key,
	client_id integer not null
		constraint contact_users_id_fk
			references users
				on update cascade,
	value varchar(300) not null
)
;

create unique index if not exists contact_id_uindex
	on contact (id)
;

create table if not exists orders
(
	id serial not null
		constraint "Order_pkey"
			primary key,
	user_id integer not null
		constraint order_users_id_fk
			references users
				on update cascade,
	tour_release_id integer not null
		constraint order_tour_release_id_fk
			references tour_release
				on update cascade,
	coast double precision,
	status integer default 0 not null
)
;

create unique index if not exists order_id_uindex
	on orders (id)
;

create table if not exists participant
(
	id serial not null
		constraint participant_pkey
			primary key,
	order_id integer not null
		constraint participant_order_id_fk
			references orders
				on update cascade,
	clipping_age integer,
	quantity integer default 1 not null
)
;

create unique index if not exists participant_id_uindex
	on participant (id)
;

create unique index if not exists users_id_uindex
	on users (id)
;

create table if not exists wishlist
(
	client_id integer not null
		constraint wishlist_users_id_fk
			references users
				on update cascade,
	tour_id integer not null
		constraint wishlist_tour_id_fk
			references tour
				on update cascade
)
;

create table if not exists role
(
	id bigint default nextval('role_id_seq'::regclass) not null
		constraint role_pkey
			primary key,
	name varchar(250) not null
)
;

create table if not exists user_roles
(
	user_id bigint not null
		constraint fkhfh9dx7w3ubf1co1vdev94g3f
			references users,
	role_id bigint not null
		constraint fkrhfovtciq1l558cw6udg0h0d3
			references role,
	constraint user_roles_pkey
		primary key (role_id, user_id)
)
;

