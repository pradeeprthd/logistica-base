-- usuario admin
INSERT INTO logistica.usuario(id, contrasenia, enable, usuario) VALUES (9999999, '7c4a8d09ca3762af61e59520943dc26494f8941b', true, 'admin2');
INSERT INTO logistica.rolenum(usuario_id, rolenumlist) VALUES (9999999, 'ADMIN_USER');
INSERT INTO logistica.rolenum(usuario_id, rolenumlist)VALUES (9999999, 'ROLE_USER');