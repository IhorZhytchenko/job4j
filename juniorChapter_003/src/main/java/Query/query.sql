--1. �������� ������ ��������� ���� ��������� � ����� "���"
select p.name, p.price, p.expired_date from product as p, type as t 
where p.type_id = t.id and t.name = '���';

--2. �������� ������ ��������� ���� ���������, � ���� � ����� ���� ����� "����������"
select * from product as p 
where p.name like '%����������%';

--3. �������� ������, ������� ������� ��� ��������, ���� �������� ������� ������������� � ��������� ������
select * from product as p 
where p.expired_date between '2018-06-01' and '2018-06-30';

--4. �������� ������, ������� ����� ����� ������� �������.
select * from product as p
where p.price = (select max(price) from product);

--5. �������� ������, ������� ������� ���������� ���� ��������� ������������� ����.
select t.name, count(p.id) from product as p, type as t
where p.type_id = t.id
group by t.name;

--6. �������� ������ ��������� ���� ��������� � ����� "���" � "������"
select p.name, p.price, p.expired_date from product as p, type as t 
where p.type_id = t.id and t.name in ('���', '������');

--7. �������� ������, ������� ������� ��� ���������, ������� �������� ������ 10 ����.
select t.name, count(p.id) from product as p, type as t
where p.type_id = t.id
group by t.name
having count(p.id) < 10;

--8. ������� ��� �������� � �� ���.
select p.id, p.name, t.name as type, p.expired_date, p.price from product as p, type as t
where p.type_id = t.id;
 
 