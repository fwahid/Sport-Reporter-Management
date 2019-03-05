insert ignore into sec_group(groupname, groupdesc) values('SPORT_REPORTERS','This group contains sport reporters.');
insert ignore into sec_group(groupname, groupdesc) values('LISTENERS','This group contains listeners.');

insert ignore into sec_user(username, password, enabled) values('sr1', SHA2('sr1', 256), true); 
insert ignore into sec_user(username, password, enabled) values('sr2', SHA2('sr2', 256), true); 
insert ignore into sec_user(username, password, enabled) values('listener1', SHA2('listener1', 256), true); 
insert ignore into sec_user(username, password, enabled) values('listener2', SHA2('listener2', 256), true); 
insert ignore into sec_user(username, password, enabled) values('fahad', SHA2('fahad', 256), true);

insert ignore into sec_user_groups(username, groupname) values('sr1','SPORT_REPORTERS');
insert ignore into sec_user_groups(username, groupname) values('sr2','SPORT_REPORTERS');
insert ignore into sec_user_groups(username, groupname) values('fahad','SPORT_REPORTERS');
insert ignore into sec_user_groups(username, groupname) values('listener1','LISTENERS');
insert ignore into sec_user_groups(username, groupname) values('listener2','LISTENERS');
insert ignore into sec_user_groups(username, groupname) values('fahad','LISTENERS');

insert into listener(id, name, username) values(98, 'Listener One', 'listener1');
insert into listener(id, name, username) values(99, 'Listener Two', 'listener2');
insert into listener(id, name, username) values(100, 'Listener Fahad', 'fahad');

insert into sportchannel(id, date_founded, name) values (98,'1934-08-05','NBC');
insert into sportchannel(id, date_founded, name) values (99,'1965-07-10','FOX');

insert into sportreporter(id, datehired, email, name, sr_id, username) values (98, '1996-09-02', 'test@test.com', 'Sport Reporter 1', 98, 'sr1');
insert into sportreporter(id, datehired, email, name, sr_id, username) values (99, '2015-06-05', 'fwahid@test.com', 'Sport Reporter 2', 99, 'sr2');
insert into sportreporter(id, datehired, email, name, sr_id, username) values (100, '1978-11-14', 'fahad@hawk.iit.edu', 'Sport Reporter Fahad', 99, 'fahad');

insert into sport_show(id, name) values (98,'Football');
insert into sport_show(id, name) values (99,'Baseball');
insert into sport_show(id, name) values (100,'Soccer');
insert into sport_show(id, name) values (101,'Bowling');
insert into sport_show(id, name) values (102,'Swimming');
insert into sport_show(id, name) values (103,'F1');
insert into sport_show(id, name) values (104,'Nascar');

insert into sr_show(sr_id, show_id) values (98,99);
insert into sr_show(sr_id, show_id) values (99,98);
insert into sr_show(sr_id, show_id) values (100,98);
insert into sr_show(sr_id, show_id) values (100,99);
insert into sr_show(sr_id, show_id) values (100,100);
insert into sr_show(sr_id, show_id) values (100,101);
insert into sr_show(sr_id, show_id) values (100,102);
insert into sr_show(sr_id, show_id) values (100,103);
insert into sr_show(sr_id, show_id) values (100,104);
