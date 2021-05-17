delete from reservation;
ALTER TABLE reservation ALTER COLUMN id RESTART WITH 1;
delete from room;
ALTER TABLE room ALTER COLUMN id RESTART WITH 1;
delete from hotel;
ALTER TABLE hotel ALTER COLUMN id RESTART WITH 1;
delete from user;
ALTER TABLE user ALTER COLUMN id RESTART WITH 1;