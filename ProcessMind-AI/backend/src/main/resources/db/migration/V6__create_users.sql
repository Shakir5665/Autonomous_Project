CREATE TABLE processmind.users (

    id UUID PRIMARY KEY,

    organization_id UUID NOT NULL,

    department_id UUID,

    employee_number VARCHAR(50),

    first_name VARCHAR(100) NOT NULL,

    last_name VARCHAR(100) NOT NULL,

    email VARCHAR(255) NOT NULL,

    password_hash VARCHAR(255) NOT NULL,

    phone VARCHAR(30),

    job_title VARCHAR(100),

    status VARCHAR(20) NOT NULL,

    last_login TIMESTAMP,

    failed_login_attempts INTEGER NOT NULL DEFAULT 0,

    account_locked BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    created_by UUID,

    updated_by UUID,

    version BIGINT NOT NULL DEFAULT 0

);

ALTER TABLE processmind.users
ADD CONSTRAINT fk_users_organization
FOREIGN KEY (organization_id)
REFERENCES processmind.organizations(id);

ALTER TABLE processmind.users
ADD CONSTRAINT fk_users_department
FOREIGN KEY (department_id)
REFERENCES processmind.departments(id);

ALTER TABLE processmind.users
ADD CONSTRAINT uk_user_email_per_organization
UNIQUE (organization_id, email);

CREATE INDEX idx_users_organization
ON processmind.users(organization_id);

CREATE INDEX idx_users_department
ON processmind.users(department_id);

CREATE INDEX idx_users_email
ON processmind.users(email);