CREATE TABLE processmind.refresh_tokens (

    id UUID PRIMARY KEY,

    user_id UUID NOT NULL,

    token VARCHAR(512) NOT NULL,

    device_name VARCHAR(100),

    ip_address VARCHAR(45),

    user_agent VARCHAR(500),

    expires_at TIMESTAMP NOT NULL,

    revoked BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMP NOT NULL

);

ALTER TABLE processmind.refresh_tokens
ADD CONSTRAINT fk_refresh_tokens_user
FOREIGN KEY (user_id)
REFERENCES processmind.users(id);

CREATE INDEX idx_refresh_tokens_user
ON processmind.refresh_tokens(user_id);

CREATE UNIQUE INDEX idx_refresh_token_value
ON processmind.refresh_tokens(token);