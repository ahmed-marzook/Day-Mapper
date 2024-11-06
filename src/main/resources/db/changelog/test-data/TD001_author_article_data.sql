--liquibase formatted SQL

--changeset ahmedM:TD001
--comment: Inserting Test Data for Users and Tasks

-- Insert mock users
INSERT INTO users (user_guid, username, email, password_hash, first_name, last_name) 
VALUES
    ('123e4567-e89b-12d3-a456-426614174000', 'john_doe', 'john@example.com', 
     '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewFyBAHOXzxMKf.2', 'John', 'Doe'),
    ('987fcdeb-51a2-43d8-b741-852552973001', 'jane_smith', 'jane@example.com',
     '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewFyBAHOXzxMKf.2', 'Jane', 'Smith'),
    ('456e789b-12d3-a456-426614174002', 'bob_wilson', 'bob@example.com',
     '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewFyBAHOXzxMKf.2', 'Bob', 'Wilson');

-- Insert mock tasks
INSERT INTO tasks (user_id, title, description, status, due_date)
VALUES
    -- John's tasks (user_id = 1)
    (1, 'Complete Project Proposal', 'Write and submit the Q4 project proposal', 'pending', 
     CURRENT_TIMESTAMP + INTERVAL '5 days'),
    (1, 'Weekly Team Meeting', 'Prepare agenda for weekly team sync', 'in_progress', 
     CURRENT_TIMESTAMP + INTERVAL '1 day'),
    (1, 'Code Review', 'Review pull request #123', 'completed', 
     CURRENT_TIMESTAMP + INTERVAL '2 days'),

    -- Jane's tasks (user_id = 2)
    (2, 'Client Presentation', 'Prepare slides for client meeting', 'pending', 
     CURRENT_TIMESTAMP + INTERVAL '3 days'),
    (2, 'Bug Fixes', 'Fix reported issues in login module', 'in_progress', 
     CURRENT_TIMESTAMP + INTERVAL '1 day'),
    (2, 'Documentation Update', 'Update API documentation', 'completed', 
     CURRENT_TIMESTAMP + INTERVAL '4 days'),

    -- Bob's tasks (user_id = 3)
    (3, 'Database Backup', 'Perform weekly database backup', 'pending', 
     CURRENT_TIMESTAMP + INTERVAL '2 days'),
    (3, 'Security Audit', 'Conduct monthly security review', 'in_progress', 
     CURRENT_TIMESTAMP + INTERVAL '7 days'),
    (3, 'Deploy Updates', 'Deploy latest features to production', 'completed', 
     CURRENT_TIMESTAMP + INTERVAL '1 day');

--rollback

