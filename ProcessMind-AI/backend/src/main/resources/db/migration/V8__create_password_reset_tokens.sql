CREATE TABLE processmind.password_reset_tokens (

    id UUID PRIMARY KEY,

    user_id UUID NOT NULL,

    token VARCHAR(512) NOT NULL,

    expires_at TIMESTAMP NOT NULL,

    used BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMP NOT NULL

);

ALTER TABLE processmind.password_reset_tokens
ADD CONSTRAINT fk_password_reset_user
FOREIGN KEY (user_id)
REFERENCES processmind.users(id);

CREATE UNIQUE INDEX idx_password_reset_token
ON processmind.password_reset_tokens(token);

CREATE INDEX idx_password_reset_user
ON processmind.password_reset_tokens(user_id);