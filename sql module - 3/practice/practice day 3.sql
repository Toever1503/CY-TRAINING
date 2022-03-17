insert into hotel(name, address) values
('melia', 'so 12 ma va thanh tri ha noi'),
('london', 'so 102 hoang mai ha noi'),
('hotel-3', 'so 91 hang ma ha noi'),
('hotel-3', 'so 98 cau giay thanh xuan ha noi');


insert into customer(fullname, address) values
('customer-1', 'son tay bac ninh'),
('customer-2', 'linh dam hoang mai'),
('customer-3', 'co nhue ha noi'),
('customer-4', 'trung van ha noi'),
('customer-5', 'phuc dien bac tu liem');

insert into room(hotel_id, type, price) values
(2, 'TB', 1500),
(2, 'R', 900),
(2, 'R', 850),
(2, 'R', 820),
(1, 'V', 900),
(1, 'V', 900),
(1, 'R', 400),
(2, 'V', 1900),
(3, 'V', 1900),
(1, 'TB', 500),
(1, 'TB', 500);

insert into reservation(hotel_Id, cus_id, date_reserved, end_date, room_number) values
(1, 2, '2022-03-13', '2022-03-20', 1),
(2, 1, '2022-001-01', '2022-05-20', 4),
(3, 3, '2022-02-13', '2022-03-30', 5),
(1, 4, '2022-03-15', '2022-03-25', 6);

#a)
select r.price, case
	when  r.type = 'V' then 'VIP'
    when r.type = 'R' then 'CHEAP'
    else 'NORMAL'
    end as type
from room as r
join hotel as h on h. hotel_id = r.hotel_id
where h.name = 'melia';

#b)
select c.fullname from reservation as r
join customer as c on c.cus_id = r.cus_id
join hotel as h on h. hotel_id = r.hotel_id
where h.name = 'melia' ;


#c)
select r.room_id, c.fullname from room as r
join hotel as h on h.hotel_id = r.hotel_id
left join reservation as res on res.room_number = r.room_id
left join customer as c on c.cus_id = res.cus_id
where h.name = 'melia';


#d)
select r.room_id from room as r
join hotel as h on h.hotel_id = r.hotel_id
left join reservation as res on res.room_number = r.room_id
where h.name = 'melia' & res.room_number is null;

#e)
select count(r.room_id) as total_room from room as r
join hotel as h on h.hotel_id = r.hotel_id
where h.name = 'london';

#f)
update room
set price = price+ (price * 0.05);

select  * from room;
select  * from reservation;