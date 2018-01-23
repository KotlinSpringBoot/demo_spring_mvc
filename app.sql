SELECT * FROM user;
show tables;
SELECT * FROM user_roles;
SELECT * FROM role;

SELECT concat(a.username, '|' , a.password, '|', a.id) from user a;