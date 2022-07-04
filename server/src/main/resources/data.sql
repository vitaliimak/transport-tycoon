INSERT INTO role (id, role) VALUES (1, 'ADMIN') ON CONFLICT DO NOTHING;
INSERT INTO role (id, role) VALUES (2, 'USER') ON CONFLICT DO NOTHING;
INSERT INTO role (id, role) VALUES (3, 'MANAGER') ON CONFLICT DO NOTHING;
INSERT INTO app_user (id, email, first_name, last_name, password) VALUES (1, 'admin@home.com', 'Admin', 'Admin', '$2a$10$CUnKV.gZCtbwB5H8H/bJZ.FEnefPsV3MtSGIno62gSiIU68vJWvWG') ON CONFLICT DO NOTHING;
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1) ON CONFLICT DO NOTHING;
