CREATE TABLE processmind.roles (

    id UUID PRIMARY KEY,

    organization_id UUID NOT NULL,

    name VARCHAR(50) NOT NULL,

    description VARCHAR(255),

    system_role BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    created_by UUID,

    updated_by UUID,

    version BIGINT NOT NULL DEFAULT 0

);

ALTER TABLE processmind.roles
ADD CONSTRAINT fk_roles_organization
FOREIGN KEY (organization_id)
REFERENCES processmind.organizations(id);

ALTER TABLE processmind.roles
ADD CONSTRAINT uk_role_name_per_organization
UNIQUE (organization_id, name);

CREATE INDEX idx_roles_organization
ON processmind.roles(organization_id);