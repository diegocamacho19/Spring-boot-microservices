INSERT INTO usuarios (username,password,enabled,nombre,apellido,email) VALUES ('diego','$2a$10$IWSi2m/CW2grMhc8kj8QVOWABnA.CRA9zEf.gDFn1ew5hZ2sm0tcK',true,'Diego Alejandro','Camacho Sossa','diegocsossa@gmail.com');
INSERT INTO usuarios (username,password,enabled,nombre,apellido,email) VALUES ('nora','$2a$10$CJ9qkwlhhajIVaYfXELdw.sfznhi/6I0NUoHpIhSDecIeak72sRGK',true,'Nora','Sossa','norasossa@gmail.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2,2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2,1);