insert into profile(name, surname, email, password, status, role, visible, created_date)
values ('Admin', 'Adminov', 'admin@gmail.com', '25d55ad283aa400af464c76d713c07ad', 'ACTIVE', 'ROLE_ADMIN', true, now()),
       ('Moderator', 'Moderatorov', 'moderator@gmail.com', '25d55ad283aa400af464c76d713c07ad', 'ACTIVE',
        'ROLE_MODERATOR', true, now()),
       ('Publisher', 'Publisherov', 'publisher@gmail.com', '25d55ad283aa400af464c76d713c07ad', 'ACTIVE',
        'ROLE_MODERATOR', true, now());