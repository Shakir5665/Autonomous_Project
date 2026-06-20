CREATE TABLE processmind.departments (

    id UUID PRIMARY KEY,

    organization_id UUID NOT NULL,

    name VARCHAR(100) NOT NULL,

    description VARCHAR(255),

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    created_by UUID,

    updated_by UUID,

    version BIGINT NOT NULL DEFAULT 0

);

ALTER TABLE processmind.departments
ADD CONSTRAINT fk_departments_organization
FOREIGN KEY (organization_id)
REFERENCES processmind.organizations(id);

ALTER TABLE processmind.departments
ADD CONSTRAINT uk_department_name_per_organization
UNIQUE (organization_id, name);

CREATE INDEX idx_departments_organization
ON processmind.departments(organization_id);