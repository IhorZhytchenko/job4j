--1. Написать запрос получение всех продуктов с типом "СЫР"
select p.name, p.price, p.expired_date from product as p, type as t 
where p.type_id = t.id and t.name = 'СЫР';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select * from product as p 
where p.name like '%мороженное%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце
select * from product as p 
where p.expired_date between '2018-06-01' and '2018-06-30';

--4. Написать запрос, который вывод самый дорогой продукт.
select * from product as p
where p.price = (select max(price) from product);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
select t.name, count(p.id) from product as p, type as t
where p.type_id = t.id
group by t.name;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select p.name, p.price, p.expired_date from product as p, type as t 
where p.type_id = t.id and t.name in ('СЫР', 'МОЛОКО');

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select t.name, count(p.id) from product as p, type as t
where p.type_id = t.id
group by t.name
having count(p.id) < 10;

--8. Вывести все продукты и их тип.
select p.id, p.name, t.name as type, p.expired_date, p.price from product as p, type as t
where p.type_id = t.id;
 
 