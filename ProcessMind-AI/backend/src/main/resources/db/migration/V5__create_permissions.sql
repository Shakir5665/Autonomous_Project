CREATE TABLE processmind.permissions (

    id UUID PRIMARY KEY,

    resource VARCHAR(100) NOT NULL,

    action VARCHAR(100) NOT NULL,

    description VARCHAR(255),

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    created_by UUID,

    updated_by UUID,

    version BIGINT NOT NULL DEFAULT 0

);

ALTER TABLE processmind.permissions
ADD CONSTRAINT uk_permission_resource_action
UNIQUE (resource, action);

CREATE INDEX idx_permission_resource
ON processmind.permissions(resource);