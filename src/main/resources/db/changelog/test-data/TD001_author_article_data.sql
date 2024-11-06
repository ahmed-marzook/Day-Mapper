--liquibase formatted SQL

--changeset ahmedM:TD001
--comment: Inserting Test Data for Users and Tasks

-- Insert mock users
INSERT INTO users (user_guid, username, email, password_hash, first_name, last_name) 
VALUES
    ('052da95a-d3d0-4051-8172-cf41bf522430', 'john_doe', 'john@example.com',
     '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewFyBAHOXzxMKf.2', 'John', 'Doe'),
    ('dc05352a-5ce2-4286-86c8-106e69792c86', 'jane_smith', 'jane@example.com',
     '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewFyBAHOXzxMKf.2', 'Jane', 'Smith'),
    ('3ea91b61-6133-4aba-9c77-0bc98802fcee', 'bob_wilson', 'bob@example.com',
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
