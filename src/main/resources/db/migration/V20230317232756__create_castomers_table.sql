create table if not exists public.customers
(
    id              UUID primary key,
    first_name      TEXT not null,
    last_name       TEXT not null,
    phone_number    VARCHAR(32) not null unique,
    email           VARCHAR(320) not null unique,
    app             TEXT not null,
    created_at      TIMESTAMP default now()
);

create index if not exists customers_email_idx on public.customers (email);
create index if not exists customers_phone_number_idx on public.customers (phone_number);