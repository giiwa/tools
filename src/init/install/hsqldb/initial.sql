#if exists table gi_rsakey
create table gi_rsakey
(
	id	bigint,
	created bigint,
	updated bigint,
	length int,
	memo varchar(1024),
	prikey varchar(8196),
	pubkey varchar(8196)
);
create index gi_rsakey_id on gi_rsakey(id);

