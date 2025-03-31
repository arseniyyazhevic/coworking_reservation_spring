CREATE TABLE IF NOT EXISTS public.bookings
(

    customer_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    date date NOT NULL,
    time_interval character varying(20) COLLATE pg_catalog."default" NOT NULL,
    coworking_space_id bigint NOT NULL,
    id bigint NOT NULL,
    CONSTRAINT bookings_pkey PRIMARY KEY (id),
    CONSTRAINT coworking_space_fk FOREIGN KEY (coworking_space_id)
        REFERENCES public.coworking_spaces (id) MATCH SIMPLE
);


CREATE TABLE IF NOT EXISTS public.coworking_spaces
(
    id bigint NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    type_of_workspace character varying(20) COLLATE pg_catalog."default" NOT NULL,
    price_dollars numeric NOT NULL,
    availability_status boolean NOT NULL,
    CONSTRAINT coworking_spaces_pkey PRIMARY KEY (id)
)


