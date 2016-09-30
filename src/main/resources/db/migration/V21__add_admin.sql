ALTER TABLE users ADD is_admin BOOLEAN NOT NULL;
INSERT INTO users (name, email, password, is_admin)
 VALUES ('Administrator', 'admin@miska.lv', 'miska', TRUE );