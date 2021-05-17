insert into user(username, password, first_name, last_name, email, birth_date, gender, registration_date, role)
values ('owner','lala','Francesco','Franco','owner@yahoo.com','1996-07-01','MALE','2019-01-01','OWNER');


insert into hotel(hotel_name, location, description, employee_number, availability, earnings, owner_id)
values ('Burj Al Arab','UAE','The best',3,1,999,1);

insert into room(room_number, size, smoking, pet_friendly, reserved, price, hotel_id)
values ('69aaa',3,1,1,1,999,1);

insert into user(username, password, first_name, last_name, email, birth_date, gender, registration_date, role)
values ('hele','lala','Tudor','Helesteanu','tudor.helesteanu@softvision.com','1996-07-01','MALE','2019-01-01','CLIENT');

insert into reservation(start_date, end_date, room_id, user_id)
values ('2019-7-1','2019-7-3',1,2)
