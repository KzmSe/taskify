INSERT INTO task(title, description, deadline, status, organization_id) VALUES
(
    'task1',
    'task1',
    '2022-12-05T17:50:17.256541',
    'OPEN',
    1
),
(
    'task2',
    'task2',
    '2022-12-05T17:50:17.256541',
    'COMPLETED',
    1
);

INSERT INTO task_account_collection(fk_task_id, account_id) VALUES
(
    1,
    1
),
(
    2,
    1
);
