show tables;

SELECT * FROM user;
SELECT * FROM role;
SELECT * FROM user_roles;
SELECT * FROM category;

SELECT concat(a.username, '|' , a.password, '|', a.id) from user a;

desc user;
desc role;
desc user_roles;
desc category;