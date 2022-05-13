--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: Kullanici; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Kullanici" (
    "Id" integer NOT NULL,
    "kullaniciAdi" character varying(20) NOT NULL,
    sifre character varying(10) NOT NULL
);


ALTER TABLE public."Kullanici" OWNER TO postgres;

--
-- Data for Name: Kullanici; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Kullanici" ("Id", "kullaniciAdi", sifre) FROM stdin;
1	adem kepçe	123456
2	ali demir	159357
3	mehmet güneş	147258
4	ayşe yılmaz	abcd12
\.


--
-- Name: Kullanici Kullanici_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Kullanici"
    ADD CONSTRAINT "Kullanici_pkey" PRIMARY KEY ("Id");


--
-- Name: Kullanici unique_Kullanici_Id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Kullanici"
    ADD CONSTRAINT "unique_Kullanici_Id" UNIQUE ("Id");


--
-- Name: Kullanici unique_Kullanici_kullaniciAdi; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Kullanici"
    ADD CONSTRAINT "unique_Kullanici_kullaniciAdi" UNIQUE ("kullaniciAdi");


--
-- Name: Kullanici unique_Kullanici_sifre; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Kullanici"
    ADD CONSTRAINT "unique_Kullanici_sifre" UNIQUE (sifre);


--
-- PostgreSQL database dump complete
--

