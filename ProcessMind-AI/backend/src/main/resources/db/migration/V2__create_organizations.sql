CREATE TABLE processmind.organizations (

    id UUID PRIMARY KEY,

    name VARCHAR(150) NOT NULL,

    code VARCHAR(50) NOT NULL,

    email VARCHAR(255),

    phone VARCHAR(30),

    website VARCHAR(255),

    industry VARCHAR(100),

    status VARCHAR(20) NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    created_by UUID,

    updated_by UUID,

    version BIGINT NOT NULL DEFAULT 0

);

ALTER TABLE processmind.organizations
ADD CONSTRAINT uk_organization_code
UNIQUE (code);

ALTER TABLE processmind.organizations
ADD CONSTRAINT uk_organization_name
UNIQUE (name);

CREATE INDEX idx_organization_status
ON processmind.organizations(status);