insert into roles(name) values ('������������'), ('���������'), ('�����');

insert into rules(description) values 
('��������� ������'), ('�������� ������ ������'), 
('�������� ���� �������������');

insert into roles_rules(role_id, rule_id) values 
(1,1), (2,1), (2,2), (3,1), (3,2), (3,3);

insert into users(login, password, role_id) values
('user1', '12345', 3), ('user2', '11111', 2), ('user3', '33333', 1), ('user4', '99999', 1);

insert into categories(name) values ('��������� 1'), ('��������� 2'), ('��������� 3');

insert into states(name) values ('�����'), ('� ������'), ('�������');

insert into items(description, user_id, category_id, state_id) values
('������ 1', 3, 2, 1), ('������ 2', 4, 2, 1);

insert into comments(description, item_id) values 
('���������� 1 � ������ 1', 1), ('���������� 2 � ������ 1', 1), ('���������� 1 � ������ 2', 2);