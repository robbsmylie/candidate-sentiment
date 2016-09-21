-- Table: candidate

CREATE TABLE candidate
(
  candidate_key integer NOT NULL,
  first_name character varying(40),
  last_name character varying(40),
  search_term character varying(40),
  CONSTRAINT candidate_pkey PRIMARY KEY (candidate_key)
);

-- Table: sentiment_measurement

CREATE TABLE sentiment_measurement
(
  sentiment_measurement_key integer,
  site_key integer,
  candidate_key integer,
  measurement_date timestamp without time zone,
  sentiment character varying(20),
  url character varying(100),
  sentiment_type character varying(20)
);

-- Table: public.website

CREATE TABLE public.website
(
  site_key integer,
  name character varying(40),
  url character varying(100)
);