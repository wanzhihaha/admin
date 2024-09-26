create sequence request_ip_info_id_seq;

alter sequence request_ip_info_id_seq owner to "marketingStg";

create sequence article_id_seq
    maxvalue 9999999;

alter sequence article_id_seq owner to "marketingStg";

create sequence advertising_id_seq
    maxvalue 9999999;

alter sequence advertising_id_seq owner to "marketingStg";

create sequence mk_quote_history_seq;

alter sequence mk_quote_history_seq owner to "marketingStg";

create sequence mk_quote_search_record_seq
    maxvalue 999999
    cycle;

alter sequence mk_quote_search_record_seq owner to "marketingStg";

create table mk_meta
(
    meta_seq_no        serial
        constraint pk_mk_meta
            primary key,
    meta_title_nm      varchar(1024) not null,
    meta_desc          varchar(1024),
    og_img_path        varchar(300),
    og_img_org_file_nm varchar(200),
    og_img_file_nm     varchar(200),
    og_img_size        integer,
    use_yn             char default 'N'::bpchar,
    ins_dtm            timestamp,
    ins_person_id      varchar(100),
    upd_dtm            timestamp,
    upd_person_id      varchar(100),
    key_words          varchar(1024)
);

alter table mk_meta
    owner to "marketingStg";

grant select on mk_meta to celloxsel;

create table mg_video
(
    video_seq_no                serial,
    meta_seq_no                 integer      not null
        constraint fk_mk_meta_to_mg_video
            references mk_meta,
    lang_cd                     varchar(30)  not null,
    video_ccd                   varchar(30)  not null,
    title_nm                    varchar(200) not null,
    summary_info                text,
    pc_list_img_path            varchar(300),
    pc_list_img_org_file_nm     varchar(200),
    pc_list_img_file_nm         varchar(200),
    pc_list_img_size            integer,
    pc_list_img_alt             varchar(255),
    pc_detl_img_path            varchar(300),
    pc_detl_img_org_file_nm     varchar(200),
    pc_detl_img_file_nm         varchar(200),
    pc_detl_img_size            integer,
    pc_detl_img_alt             varchar(255),
    mobile_list_img_path        varchar(300),
    mobile_list_img_org_file_nm varchar(200),
    mobile_list_img_file_nm     varchar(200),
    mobile_list_img_size        integer,
    mobile_list_img_alt         varchar(255),
    mobile_detl_img_path        varchar(300),
    mobile_detl_img_org_file_nm varchar(200),
    mobile_detl_img_file_nm     varchar(200),
    mobile_detl_img_size        integer,
    mobile_detl_img_alt         varchar(255),
    contents_type               varchar(30),
    contents                    text,
    detl_info                   text,
    use_yn                      char    default 'N'::bpchar,
    srch_cnt                    integer default 0,
    ordb                        varchar(5),
    ins_dtm                     timestamp,
    ins_person_id               varchar(100),
    upd_dtm                     timestamp,
    upd_person_id               varchar(100),
    constraint pk_mg_video
        primary key (video_seq_no, meta_seq_no)
);

alter table mg_video
    owner to "marketingStg";

grant select on mg_video to celloxsel;

create table mk_adm_mng
(
    adm_mng_seq_no serial
        constraint pk_mk_adm_mng
            primary key,
    adm_user_id    varchar(100) not null,
    adm_user_nm    varchar(40)  not null,
    adm_email_addr varchar(200),
    adm_auth       varchar(2),
    adm_pw         varchar(400),
    salt_val       varchar(200),
    temp_pw_sts    varchar(30),
    use_yn         char,
    final_log_dtm  timestamp,
    ins_dtm        timestamp,
    ins_person_id  varchar(100),
    upd_dtm        timestamp,
    upd_person_id  varchar(100),
    login_cnt      integer default 0
);

alter table mk_adm_mng
    owner to "marketingStg";

grant select on mk_adm_mng to celloxsel;

create table mk_atch_file
(
    contents_seq_no  integer           not null,
    contents_ccd     varchar(30)       not null,
    ordb             integer default 1 not null,
    atch_file_path   varchar(300),
    atch_org_file_nm varchar(200),
    atch_file_nm     varchar(200),
    atch_file_size   integer,
    ins_dtm          timestamp,
    ins_person_id    varchar(100),
    upd_dtm          timestamp,
    upd_person_id    varchar(100),
    constraint pk_mk_atch_file
        primary key (contents_seq_no, contents_ccd, ordb)
);

alter table mk_atch_file
    owner to "marketingStg";

grant select on mk_atch_file to celloxsel;

create table mk_blog
(
    blog_seq_no                 serial,
    meta_seq_no                 integer      not null
        constraint fk_mk_meta_to_mk_blog
            references mk_meta,
    lang_cd                     varchar(30)  not null,
    blog_ccd1                   varchar(30)  not null,
    blog_ccd2                   varchar(255),
    title_nm                    varchar(200) not null,
    summary_info                text,
    pc_list_img_path            varchar(300),
    pc_list_img_org_file_nm     varchar(200),
    pc_list_img_file_nm         varchar(200),
    pc_list_img_size            integer,
    pc_list_img_alt             varchar(255),
    pc_detl_img_path            varchar(300),
    pc_detl_img_org_file_nm     varchar(200),
    pc_detl_img_file_nm         varchar(200),
    pc_detl_img_size            integer,
    pc_detl_img_alt             varchar(255),
    mobile_list_img_path        varchar(300),
    mobile_list_img_org_file_nm varchar(200),
    mobile_list_img_file_nm     varchar(200),
    mobile_list_img_size        integer,
    mobile_list_img_alt         varchar(255),
    mobile_detl_img_path        varchar(300),
    mobile_detl_img_org_file_nm varchar(200),
    mobile_detl_img_file_nm     varchar(200),
    mobile_detl_img_size        integer,
    mobile_detl_img_alt         varchar(255),
    detl_info                   text         not null,
    iqr_yn                      char    default 'N'::bpchar,
    use_yn                      char    default 'N'::bpchar,
    srch_cnt                    integer default 0,
    ordb                        varchar(5),
    ins_dtm                     timestamp,
    ins_person_id               varchar(100),
    upd_dtm                     timestamp,
    upd_person_id               varchar(100),
    constraint pk_mk_blog
        primary key (blog_seq_no, meta_seq_no)
);

alter table mk_blog
    owner to "marketingStg";

grant select on mk_blog to celloxsel;

create table mk_brch
(
    brch_seq_no                 serial,
    meta_seq_no                 integer      not null
        constraint fk_mk_meta_to_mk_brch
            references mk_meta,
    lang_cd                     varchar(30)  not null,
    title_nm                    varchar(200) not null,
    summary_info                text,
    pc_list_img_path            varchar(300),
    pc_list_img_org_file_nm     varchar(200),
    pc_list_img_file_nm         varchar(200),
    pc_list_img_size            integer,
    pc_list_img_alt             varchar(255),
    pc_detl_img_path            varchar(300),
    pc_detl_img_org_file_nm     varchar(200),
    pc_detl_img_file_nm         varchar(200),
    pc_detl_img_size            integer,
    pc_detl_img_alt             varchar(255),
    mobile_list_img_path        varchar(300),
    mobile_list_img_org_file_nm varchar(200),
    mobile_list_img_file_nm     varchar(200),
    mobile_list_img_size        integer,
    mobile_list_img_alt         varchar(255),
    mobile_detl_img_path        varchar(300),
    mobile_detl_img_org_file_nm varchar(200),
    mobile_detl_img_file_nm     varchar(200),
    mobile_detl_img_size        integer,
    mobile_detl_img_alt         varchar(255),
    use_yn                      char    default 'N'::bpchar,
    srch_cnt                    integer default 0,
    ordb                        varchar(5),
    ins_dtm                     timestamp,
    ins_person_id               varchar(100),
    upd_dtm                     timestamp,
    upd_person_id               varchar(100),
    constraint pk_mk_brch
        primary key (brch_seq_no, meta_seq_no)
);

alter table mk_brch
    owner to "marketingStg";

grant select on mk_brch to celloxsel;

create table mk_cd_mng
(
    grp_cd        varchar(30)  not null,
    cd            varchar(30)  not null,
    lang_cd       varchar(30)  not null,
    cd_nm         varchar(100) not null,
    cd_desc       varchar(2000),
    use_yn        char default 'N'::bpchar,
    ordb          integer,
    ins_dtm       timestamp,
    ins_person_id varchar(100),
    upd_dtm       timestamp,
    upd_person_id varchar(100),
    constraint pk_mk_cd_mng
        primary key (grp_cd, cd, lang_cd)
);

alter table mk_cd_mng
    owner to "marketingStg";

grant select on mk_cd_mng to celloxsel;

create table mk_cello_news
(
    news_seq_no                 serial,
    meta_seq_no                 integer      not null
        constraint fk_mk_meta_to_mk_cello_news
            references mk_meta,
    lang_cd                     varchar(30)  not null,
    news_ccd1                   varchar(30)  not null,
    news_ccd2                   varchar(255),
    title_nm                    varchar(200) not null,
    summary_info                text,
    pc_list_img_path            varchar(300),
    pc_list_img_org_file_nm     varchar(200),
    pc_list_img_file_nm         varchar(200),
    pc_list_img_size            integer,
    pc_list_img_alt             varchar(255),
    pc_detl_img_path            varchar(300),
    pc_detl_img_org_file_nm     varchar(200),
    pc_detl_img_file_nm         varchar(200),
    pc_detl_img_size            integer,
    pc_detl_img_alt             varchar(255),
    mobile_list_img_path        varchar(300),
    mobile_list_img_org_file_nm varchar(200),
    mobile_list_img_file_nm     varchar(200),
    mobile_list_img_size        integer,
    mobile_list_img_alt         varchar(255),
    mobile_detl_img_path        varchar(300),
    mobile_detl_img_org_file_nm varchar(200),
    mobile_detl_img_file_nm     varchar(200),
    mobile_detl_img_size        integer,
    mobile_detl_img_alt         varchar(255),
    news_detl_info              text,
    use_yn                      char    default 'N'::bpchar,
    srch_cnt                    integer default 0,
    ordb                        varchar(5),
    ins_dtm                     timestamp,
    ins_person_id               varchar(100),
    upd_dtm                     timestamp,
    upd_person_id               varchar(100),
    constraint pk_mk_cello_news
        primary key (news_seq_no, meta_seq_no)
);

alter table mk_cello_news
    owner to "marketingStg";

grant select on mk_cello_news to celloxsel;

create table mk_evt
(
    evt_seq_no                  serial,
    meta_seq_no                 integer      not null
        constraint fk_mk_meta_to_mk_evt
            references mk_meta,
    lang_cd                     varchar(30)  not null,
    evt_ccd                     varchar(30)  not null,
    title_nm                    varchar(200) not null,
    summary_info                text,
    pc_list_img_path            varchar(300),
    pc_list_img_org_file_nm     varchar(200),
    pc_list_img_file_nm         varchar(200),
    pc_list_img_size            integer,
    pc_list_img_alt             varchar(255),
    pc_detl_img_path            varchar(300),
    pc_detl_img_org_file_nm     varchar(200),
    pc_detl_img_file_nm         varchar(200),
    pc_detl_img_size            integer,
    pc_detl_img_alt             varchar(255),
    mobile_list_img_path        varchar(300),
    mobile_list_img_org_file_nm varchar(200),
    mobile_list_img_file_nm     varchar(200),
    mobile_list_img_size        integer,
    mobile_list_img_alt         varchar(255),
    mobile_detl_img_path        varchar(300),
    mobile_detl_img_org_file_nm varchar(200),
    mobile_detl_img_file_nm     varchar(200),
    mobile_detl_img_size        integer,
    mobile_detl_img_alt         varchar(255),
    evt_detl_content            text,
    evt_stat_dtm                varchar(8),
    evt_end_dtm                 varchar(8),
    new_window_link_yn          char,
    new_link                    varchar(300),
    use_yn                      char,
    srch_cnt                    integer default 0,
    ordb                        varchar(5),
    ins_dtm                     timestamp,
    ins_person_id               varchar(100),
    upd_dtm                     timestamp,
    upd_person_id               varchar(100),
    constraint pk_mk_evt
        primary key (evt_seq_no, meta_seq_no)
);

alter table mk_evt
    owner to "marketingStg";

grant select on mk_evt to celloxsel;

create table mk_faq
(
    faq_seq          serial
        constraint pk_mk_faq
            primary key,
    lang_cd          varchar(30)  not null,
    faq_ccd          varchar(30),
    title_nm         varchar(200) not null,
    faq_detl_content text,
    use_yn           char    default 'N'::bpchar,
    srch_cnt         integer default 0,
    ordb             varchar(5),
    ins_dtm          timestamp,
    ins_person_id    varchar(100),
    upd_dtm          timestamp,
    upd_person_id    varchar(100)
);

alter table mk_faq
    owner to "marketingStg";

grant select on mk_faq to celloxsel;

create table mk_lib_sub_mev
(
    lib_sub_mev_seq_no          serial
        constraint pk_mk_lib_sub_mev
            primary key,
    lang_cd                     varchar(30) not null,
    reltd_evt                   varchar(10) not null,
    pc_list_img_path            varchar(300),
    pc_list_img_org_file_nm     varchar(200),
    pc_list_img_file_nm         varchar(200),
    pc_list_img_size            integer,
    pc_list_img_alt             varchar(255),
    mobile_list_img_path        varchar(300),
    mobile_list_img_org_file_nm varchar(200),
    mobile_list_img_file_nm     varchar(200),
    mobile_list_img_size        integer,
    mobile_list_img_alt         varchar(255),
    use_yn                      char default 'N'::bpchar,
    ordb                        varchar(5),
    ins_dtm                     timestamp,
    ins_person_id               varchar(100),
    upd_dtm                     timestamp,
    upd_person_id               varchar(100)
);

alter table mk_lib_sub_mev
    owner to "marketingStg";

grant select on mk_lib_sub_mev to celloxsel;

create table mk_lib_sub_mv
(
    lib_sub_mv_seq_no serial
        constraint pk_mk_lib_sub_mv
            primary key,
    lang_cd           varchar(30) not null,
    mv_lib_ccd        varchar(30) not null,
    reltd_lib         varchar(10) not null,
    use_yn            char default 'N'::bpchar,
    ordb              varchar(5),
    ins_dtm           timestamp,
    ins_person_id     varchar(100),
    upd_dtm           timestamp,
    upd_person_id     varchar(100)
);

alter table mk_lib_sub_mv
    owner to "marketingStg";

grant select on mk_lib_sub_mv to celloxsel;

create table mk_mv_banner
(
    mv_banner_seq_no            serial
        constraint pk_mk_mv_banner
            primary key,
    lang_cd                     varchar(30)        not null,
    title_nm                    varchar(200)       not null,
    banner_open_stat_date       varchar(8)         not null,
    banner_open_end_date        varchar(8)         not null,
    banner_url                  varchar(300),
    pc_list_img_path            varchar(300),
    pc_list_img_org_file_nm     varchar(200),
    pc_list_img_file_nm         varchar(200),
    pc_list_img_size            integer,
    pc_list_img_alt             varchar(255),
    mobile_list_img_path        varchar(300),
    mobile_list_img_org_file_nm varchar(200),
    mobile_list_img_file_nm     varchar(200),
    mobile_list_img_size        integer,
    mobile_list_img_alt         varchar(255),
    bkgr_color                  varchar(30),
    use_yn                      char     default 'N'::bpchar,
    ordb                        varchar(5),
    ins_dtm                     timestamp,
    ins_person_id               varchar(100),
    upd_dtm                     timestamp,
    upd_person_id               varchar(100),
    content                     text,
    pic_type                    varchar,
    carousel_type               smallint default 0 not null
);

comment on column mk_mv_banner.carousel_type is '轮播图类型  1、首页轮播图  2、特价仓轮播图';

alter table mk_mv_banner
    owner to "marketingStg";

grant select on mk_mv_banner to celloxsel;

create table mk_mv_lib
(
    mv_lib_seq_no serial
        constraint pk_mk_mv_lib
            primary key,
    lang_cd       varchar(30) not null,
    mv_lib_ccd    varchar(30) not null,
    reltd_lib     varchar(10) not null,
    use_yn        char default 'N'::bpchar,
    ordb          varchar(5),
    ins_dtm       timestamp,
    ins_person_id varchar(100),
    upd_dtm       timestamp,
    upd_person_id varchar(100)
);

alter table mk_mv_lib
    owner to "marketingStg";

grant select on mk_mv_lib to celloxsel;

create table mk_mv_sqprd
(
    mv_sqprd_seq_no serial
        constraint pk_mk_mv_sqprd
            primary key,
    lang_cd         varchar(30) not null,
    mv_sqprd_ccd    varchar(30) not null,
    reltd_sqprd     varchar(10) not null,
    use_yn          char default 'N'::bpchar,
    ordb            varchar(5),
    ins_dtm         timestamp,
    ins_person_id   varchar(100),
    upd_dtm         timestamp,
    upd_person_id   varchar(100)
);

alter table mk_mv_sqprd
    owner to "marketingStg";

grant select on mk_mv_sqprd to celloxsel;

create table mk_mv_video
(
    mv_video_seq_no             serial
        constraint pk_mk_mv_video
            primary key,
    lang_cd                     varchar(30)  not null,
    mv_video_ccd                varchar(30)  not null,
    reltd_video                 varchar(10)  not null,
    title_nm                    varchar(200) not null,
    summary_info                text,
    wrt_info1                   varchar(255),
    wrt_info2                   varchar(255),
    pc_list_img_path            varchar(300),
    pc_list_img_org_file_nm     varchar(200),
    pc_list_img_file_nm         varchar(200),
    pc_list_img_size            integer,
    pc_list_img_alt             varchar(255),
    mobile_list_img_path        varchar(300),
    mobile_list_img_org_file_nm varchar(200),
    mobile_list_img_file_nm     varchar(200),
    mobile_list_img_size        integer,
    mobile_list_img_alt         varchar(255),
    use_yn                      char    default 'N'::bpchar,
    ordb                        varchar(5),
    ins_dtm                     timestamp,
    ins_person_id               varchar(100),
    upd_dtm                     timestamp,
    upd_person_id               varchar(100),
    short_info                  text,
    content                     text,
    meta_seq_no                 integer,
    page_view                   integer default 0
);

comment on column mk_mv_video.page_view is '浏览量';

alter table mk_mv_video
    owner to "marketingStg";

grant select on mk_mv_video to celloxsel;

create table mk_pop_mng
(
    pop_seq_no                 serial
        constraint pk_mk_pop_mng
            primary key,
    lang_cd                    varchar(30)  not null,
    title_nm                   varchar(200) not null,
    perid_stat_date            varchar(8),
    perid_end_date             varchar(8),
    link_url                   varchar(300),
    pc_pop_ccd                 varchar(30),
    pc_pop_size_wdt            varchar(10),
    pc_pop_size_hgt            varchar(10),
    pc_pop_loc_top             varchar(10),
    pc_pop_loc_left            varchar(10),
    pc_pop_img_path            varchar(300),
    pc_pop_img_org_file_nm     varchar(200),
    pc_pop_img_file_nm         varchar(200),
    pc_pop_img_size            integer,
    pc_pop_img_alt             varchar(255),
    mobile_pop_size_wdt        varchar(10),
    mobile_pop_size_hgt        varchar(10),
    mobile_pop_img_path        varchar(300),
    mobile_pop_img_org_file_nm varchar(200),
    mobile_pop_img_file_nm     varchar(200),
    mobile_pop_img_size        integer,
    mobile_pop_img_alt         varchar(255),
    use_yn                     char default 'N'::bpchar,
    ins_dtm                    timestamp,
    ins_person_id              varchar(100),
    upd_dtm                    timestamp,
    upd_person_id              varchar(100)
);

alter table mk_pop_mng
    owner to "marketingStg";

grant select on mk_pop_mng to celloxsel;

create table mk_rpt
(
    rpt_seq_no                  serial,
    meta_seq_no                 integer      not null
        constraint fk_mk_meta_to_mk_rpt
            references mk_meta,
    lang_cd                     varchar(30)  not null,
    title_nm                    varchar(200) not null,
    rpt_ccd                     varchar(30),
    summary_info                text,
    pc_list_img_path            varchar(300),
    pc_list_img_org_file_nm     varchar(200),
    pc_list_img_file_nm         varchar(200),
    pc_list_img_size            integer,
    pc_list_img_alt             varchar(255),
    pc_detl_img_path            varchar(300),
    pc_detl_img_org_file_nm     varchar(200),
    pc_detl_img_file_nm         varchar(200),
    pc_detl_img_size            integer,
    pc_detl_img_alt             varchar(255),
    mobile_list_img_path        varchar(300),
    mobile_list_img_org_file_nm varchar(200),
    mobile_list_img_file_nm     varchar(200),
    mobile_list_img_size        integer,
    mobile_list_img_alt         varchar(255),
    mobile_detl_img_path        varchar(300),
    mobile_detl_img_org_file_nm varchar(200),
    mobile_detl_img_file_nm     varchar(200),
    mobile_detl_img_size        integer,
    mobile_detl_img_alt         varchar(255),
    detl_info                   text,
    use_yn                      char,
    srch_cnt                    integer default 0,
    ordb                        varchar(5),
    ins_dtm                     timestamp,
    ins_person_id               varchar(100),
    upd_dtm                     timestamp,
    upd_person_id               varchar(100),
    constraint pk_mk_rpt
        primary key (rpt_seq_no, meta_seq_no)
);

alter table mk_rpt
    owner to "marketingStg";

grant select on mk_rpt to celloxsel;

create table mk_rsc
(
    rsc_seq_no                  serial,
    meta_seq_no                 integer      not null
        constraint fk_mk_meta_to_mk_rsc
            references mk_meta,
    lang_cd                     varchar(30)  not null,
    rsc_ccd                     varchar(30)  not null,
    title_nm                    varchar(200) not null,
    summary_info                text,
    pc_list_img_path            varchar(300),
    pc_list_img_org_file_nm     varchar(200),
    pc_list_img_file_nm         varchar(200),
    pc_list_img_size            integer,
    pc_list_img_alt             varchar(255),
    pc_detl_img_path            varchar(300),
    pc_detl_img_org_file_nm     varchar(200),
    pc_detl_img_file_nm         varchar(200),
    pc_detl_img_size            integer,
    pc_detl_img_alt             varchar(255),
    mobile_list_img_path        varchar(300),
    mobile_list_img_org_file_nm varchar(200),
    mobile_list_img_file_nm     varchar(200),
    mobile_list_img_size        integer,
    mobile_list_img_alt         varchar(255),
    mobile_detl_img_path        varchar(300),
    mobile_detl_img_org_file_nm varchar(200),
    mobile_detl_img_file_nm     varchar(200),
    mobile_detl_img_size        integer,
    mobile_detl_img_alt         varchar(255),
    detl_info                   text         not null,
    use_yn                      char    default 'N'::bpchar,
    srch_cnt                    integer default 0,
    ordb                        varchar(5),
    ins_dtm                     timestamp,
    ins_person_id               varchar(100),
    upd_dtm                     timestamp,
    upd_person_id               varchar(100),
    constraint pk_mk_rsc
        primary key (rsc_seq_no, meta_seq_no)
);

alter table mk_rsc
    owner to "marketingStg";

grant select on mk_rsc to celloxsel;

create table mk_sqprd
(
    sqprd_seq_no                 serial,
    meta_seq_no                  integer      not null
        constraint fk_mk_meta_to_mk_sqprd
            references mk_meta,
    lang_cd                      varchar(30)  not null,
    sqprd_ctgry                  varchar(30),
    sqprd_nm                     varchar(255) not null,
    sqprd_eng_nm                 varchar(255) not null,
    sqprd_attn_id                varchar(50),
    sqprd_navy                   varchar(255),
    svc_valid_stat_date          varchar(8),
    svc_valid_end_date           varchar(8),
    sqprd_summary_info           text,
    pc_list_img_path             varchar(300),
    pc_list_img_org_file_nm      varchar(200),
    pc_list_img_file_nm          varchar(200),
    pc_list_img_size             integer,
    pc_list_img_alt              varchar(255),
    pc_detl_img_path             varchar(300),
    pc_detl_img_org_file_nm      varchar(200),
    pc_detl_img_file_nm          varchar(200),
    pc_detl_img_size             integer,
    pc_detl_img_alt              varchar(255),
    mobile_list_img_path         varchar(300),
    mobile_list_img_org_file_nm  varchar(200),
    mobile_list_img_file_nm      varchar(200),
    mobile_list_img_size         integer,
    mobile_list_img_alt          varchar(255),
    mobile_detl_img_path         varchar(300),
    mobile_detl_img_org_file_nm  varchar(200),
    mobile_detl_img_file_nm      varchar(200),
    mobile_detl_img_size         integer,
    mobile_detl_img_alt          varchar(255),
    sqprd_detl_info              text,
    sqprd_noti_contents          text,
    dep_port_cd                  varchar(255),
    arrp_pot_cd                  varchar(255),
    ship_to                      varchar(255),
    sqprd_opt1                   varchar(255),
    sqprd_opt2                   varchar(255),
    sqprd_opt3                   varchar(255),
    sqprd_opt4                   varchar(255),
    sqprd_opt5                   varchar(255),
    reltd_sqprd1                 varchar(10),
    reltd_sqprd2                 varchar(10),
    reltd_sqprd3                 varchar(10),
    view_type_img                varchar(30),
    view_type_img_bkgr_color_opt varchar(30),
    view_type_img_bkgr_color_ccd varchar(30),
    view_type_img_bkgr_color_val varchar(30),
    view_type_img_from           varchar(255),
    view_type_img_ccd            varchar(30),
    view_type_img_to             varchar(255),
    view_type_img_hash_tag       varchar(255),
    view_type_img_path           varchar(300),
    view_type_img_org_file_nm    varchar(200),
    view_type_img_file_nm        varchar(200),
    view_type_img_size           integer,
    view_type_img_alt            varchar(255),
    use_yn                       char    default 'N'::bpchar,
    srch_cnt                     integer default 0,
    ordb                         varchar(5),
    ins_dtm                      timestamp,
    ins_person_id                varchar(100),
    upd_dtm                      timestamp,
    upd_person_id                varchar(100),
    constraint pk_mk_sqprd
        primary key (sqprd_seq_no, meta_seq_no)
);

alter table mk_sqprd
    owner to "marketingStg";

grant select on mk_sqprd to celloxsel;

create table mk_tos
(
    tos_seq_no    serial
        constraint pk_mk_tos
            primary key,
    lang_cd       varchar(30)   not null,
    tos_ccd       varchar(30)   not null,
    title_nm      varchar(1024) not null,
    detl_info     text,
    memo          text,
    tos_ver       varchar(256)  not null,
    req_sts       varchar(30),
    use_yn        char default 'N'::bpchar,
    ins_dtm       timestamp,
    ins_person_id varchar(100),
    upd_dtm       timestamp,
    upd_person_id varchar(100)
);

alter table mk_tos
    owner to "marketingStg";

create unique index mk_tos_tos_ccd_idx
    on mk_tos (tos_ccd, tos_ver);

grant select on mk_tos to celloxsel;

create table mk_user_info
(
    id                               varchar(50) not null
        constraint market_user_info_pk
            primary key,
    type                             varchar(50),
    seq_no                           varchar(50),
    first_name                       varchar(2000),
    last_name                        varchar(2000),
    company                          varchar(1024),
    email_address                    varchar(2000),
    mobile_phone                     varchar(256),
    title                            varchar(500),
    hq_department                    varchar(500),
    solution_of_interest             varchar(500),
    comment                          text,
    category                         varchar(500),
    trans_mode                       varchar(200),
    trans_dept_point                 varchar(200),
    trans_dest                       varchar(500),
    estimated_shipping_date          varchar(200),
    vol_and_qty                      varchar(500),
    product_url                      varchar(500),
    hq3rd_opt_in                     varchar(500),
    hq_email_opt_in                  varchar(500),
    unique_code                      varchar(50),
    create_date                      timestamp default CURRENT_TIMESTAMP,
    estimated_monthly_logistics_cost varchar(64),
    name                             varchar(1024),
    source                           varchar(1)  not null
);

comment on column mk_user_info.estimated_monthly_logistics_cost is '预计月物流费';

comment on column mk_user_info.name is '姓名';

comment on column mk_user_info.source is '来源:1、pc端 2、百度推广 3、小程序';

alter table mk_user_info
    owner to "marketingStg";

grant select on mk_user_info to celloxsel;

create table mk_email_history
(
    id            varchar(50) not null,
    email_status  varchar(10) not null,
    email_content text,
    email_html    text,
    create_date   timestamp
);

alter table mk_email_history
    owner to "marketingStg";

grant select on mk_email_history to celloxsel;

create table mk_operation_log
(
    id            varchar(50) not null
        primary key,
    download_type varchar(500),
    start_date    timestamp,
    end_date      timestamp,
    success_yn    char,
    create_date   timestamp,
    create_user   varchar(100),
    update_date   timestamp,
    update_user   varchar(100)
);

alter table mk_operation_log
    owner to "marketingStg";

grant select on mk_operation_log to celloxsel;

create table record_ip_info
(
    id          bigint    default nextval('request_ip_info_id_seq'::regclass) not null
        constraint request_ip_info_pkey
            primary key,
    ip          varchar(20)                                                   not null,
    create_date timestamp default CURRENT_TIMESTAMP,
    str_time    varchar(20)
);

alter table record_ip_info
    owner to "marketingStg";

grant select on record_ip_info to celloxsel;

create table mk_hot_product
(
    id            serial
        constraint pk_mk_hot_product
            primary key,
    title         varchar(128) not null,
    lang_cd       varchar(30)  not null,
    link          varchar(512),
    type          varchar(16),
    use_yn        char    default 'N'::bpchar,
    ordb          varchar(5),
    srch_cnt      integer default 0,
    ins_dtm       timestamp,
    ins_person_id varchar(100),
    upd_dtm       timestamp,
    upd_person_id varchar(100)
);

comment on column mk_hot_product.title is '标题';

comment on column mk_hot_product.link is '链接';

comment on column mk_hot_product.ordb is '排序';

comment on column mk_hot_product.ins_dtm is '创建时间';

alter table mk_hot_product
    owner to "marketingStg";

grant select on mk_hot_product to celloxsel;

create table mk_products
(
    product_seq_no              serial
        constraint pk_mk_product
            primary key,
    meta_seq_no                 integer      not null,
    lang_cd                     varchar(30)  not null,
    product_ctgry               varchar(30),
    product_nm                  varchar(255) not null,
    svc_valid_stat_date         varchar(8),
    svc_valid_end_date          varchar(8),
    product_summary_info        text,
    pc_list_img_path            varchar(300),
    pc_list_img_org_file_nm     varchar(200),
    pc_list_img_file_nm         varchar(200),
    pc_list_img_size            integer,
    pc_list_img_alt             varchar(255),
    pc_detl_img_path            varchar(300),
    pc_detl_img_org_file_nm     varchar(200),
    pc_detl_img_file_nm         varchar(200),
    pc_detl_img_size            integer,
    pc_detl_img_alt             varchar(255),
    mobile_list_img_path        varchar(300),
    mobile_list_img_org_file_nm varchar(200),
    mobile_list_img_file_nm     varchar(200),
    mobile_list_img_size        integer,
    mobile_list_img_alt         varchar(255),
    mobile_detl_img_path        varchar(300),
    mobile_detl_img_org_file_nm varchar(200),
    mobile_detl_img_file_nm     varchar(200),
    mobile_detl_img_size        integer,
    mobile_detl_img_alt         varchar(255),
    use_yn                      char       default 'N'::bpchar,
    srch_cnt                    integer    default 0,
    ordb                        varchar(5),
    ins_dtm                     timestamp,
    ins_person_id               varchar(100),
    upd_dtm                     timestamp,
    upd_person_id               varchar(100),
    product_contents            text,
    enable_flag                 varchar(2) default 0,
    ack_question                varchar(128)
);

comment on column mk_products.product_ctgry is '类型  mk_cd_mng 表里的数据';

comment on column mk_products.product_nm is '产品名称';

comment on column mk_products.product_summary_info is '产品描述';

comment on column mk_products.product_contents is '内容';

comment on column mk_products.enable_flag is '是否有效1是0否';

comment on column mk_products.ack_question is '常见问题id';

alter table mk_products
    owner to "marketingStg";

create unique index mk_products_product_nm_idx
    on mk_products (product_nm);

grant select on mk_products to celloxsel;

create table mk_article
(
    id                   bigint       default nextval('article_id_seq'::regclass) not null
        primary key,
    article_type         varchar(8),
    article_title        varchar(128),
    article_digest       varchar(512),
    article_tag          varchar(255),
    enable_flag          varchar(2)   default 0,
    create_date          timestamp(6) default CURRENT_TIMESTAMP,
    create_persion_id    varchar(64),
    update_date          timestamp(6),
    update_persion_id    varchar(64),
    stick_type           varchar(8)   default 40,
    article_content      text         default ''::text,
    meta_seq_no          bigint,
    use_yn               char,
    ordb                 varchar(10),
    page_view            bigint       default 0,
    article_pic_big      varchar(255),
    article_pic_as       varchar(255),
    article_pic_tb       varchar(255),
    article_pic_big_name varchar(255),
    article_pic_as_name  varchar(255),
    article_pic_tb_name  varchar(255),
    link_yn              integer      default 0
);

comment on column mk_article.id is '主键id';

comment on column mk_article.article_type is '文章类型：10公司动态、20跨境资讯、30物流动态、40物流政策';

comment on column mk_article.article_title is '文章标题';

comment on column mk_article.article_digest is '文章摘要';

comment on column mk_article.article_tag is '文章标签';

comment on column mk_article.enable_flag is '是否有效1是0否';

comment on column mk_article.create_date is '创建时间';

comment on column mk_article.create_persion_id is '创建人id';

comment on column mk_article.update_date is '修改时间';

comment on column mk_article.update_persion_id is '修改人id';

comment on column mk_article.stick_type is '置顶类型10置顶1，20置顶2，30置顶3';

comment on column mk_article.article_content is '文章内容';

comment on column mk_article.meta_seq_no is 'seo表id';

comment on column mk_article.use_yn is '是否使用';

comment on column mk_article.ordb is '排序';

comment on column mk_article.page_view is '浏览量';

comment on column mk_article.article_pic_big is '文章大图url';

comment on column mk_article.article_pic_as is '文章辅图url';

comment on column mk_article.article_pic_tb is '文章缩略图url';

comment on column mk_article.article_pic_big_name is '文章大图名称';

comment on column mk_article.article_pic_as_name is '文章辅图名称';

comment on column mk_article.article_pic_tb_name is '文章缩略图名称';

alter table mk_article
    owner to "marketingStg";

grant select on mk_article to celloxsel;

create table mk_advertising
(
    id                 bigint     default nextval('advertising_id_seq'::regclass) not null
        primary key,
    ad_name            varchar(50),
    ad_location        varchar(16),
    ad_url             varchar(500),
    use_yn             char,
    ordb               varchar(10),
    ad_keyword         varchar(1000),
    enable_flag        varchar(2) default 0,
    create_date        timestamp(6),
    create_persion_id  varchar(64),
    update_date        timestamp(6),
    update_persion_id  varchar(64),
    meta_seq_no        bigint,
    effective_time     timestamp(6),
    dead_time          timestamp(6),
    ad_pic_url         varchar(500),
    ad_pic_name        varchar(500),
    ad_mobile_pic_url  varchar(500),
    ad_mobile_pic_name varchar(500)
);

comment on column mk_advertising.id is '主键id';

comment on column mk_advertising.ad_name is '广告名';

comment on column mk_advertising.ad_location is '广告位置:10菜单栏20产品页30资源页';

comment on column mk_advertising.ad_url is '广告跳转链接';

comment on column mk_advertising.use_yn is '使用状态';

comment on column mk_advertising.ordb is '排序';

comment on column mk_advertising.ad_keyword is '关键词';

comment on column mk_advertising.enable_flag is '是否有效0是1否';

comment on column mk_advertising.create_date is '创建时间';

comment on column mk_advertising.create_persion_id is '创建人id';

comment on column mk_advertising.update_date is '修改时间';

comment on column mk_advertising.update_persion_id is '修改人id';

comment on column mk_advertising.meta_seq_no is 'seo表id';

comment on column mk_advertising.effective_time is '生效时间';

comment on column mk_advertising.dead_time is '失效时间';

comment on column mk_advertising.ad_pic_url is '图片地址';

comment on column mk_advertising.ad_pic_name is '图片名称';

comment on column mk_advertising.ad_mobile_pic_url is '移动端图片';

alter table mk_advertising
    owner to "marketingStg";

grant select on mk_advertising to celloxsel;

create table mk_quote_cd
(
    grp_cd          varchar(20) not null,
    lang_cd         varchar(10) not null,
    cd              varchar(20) not null,
    default_cd_name varchar(100),
    nation_cd       varchar(10),
    use_yn          varchar(1) default 'Y'::character varying,
    ord_id          integer,
    ins_dtm         timestamp,
    ins_persion_id  varchar(100),
    upd_dtm         timestamp,
    upd_persion_id  varchar(100),
    constraint mk_quote_cd_pk
        primary key (grp_cd, lang_cd, cd)
);

alter table mk_quote_cd
    owner to "marketingStg";

grant select on mk_quote_cd to celloxsel;

create table mk_quote_search_record
(
    sq_no            varchar(20) not null,
    quote_result_ord integer     not null,
    quote_cnt        integer,
    carrier_name     varchar(100),
    quote_fare       varchar(100),
    quote_currency   varchar(10),
    remark           varchar(2000),
    ins_persion_id   varchar(50),
    ins_dtm          timestamp default CURRENT_TIMESTAMP,
    upd_persion_id   varchar(50),
    upd_dtm          timestamp,
    constraint mk_quote_search_record_pk
        primary key (sq_no, quote_result_ord)
);

alter table mk_quote_search_record
    owner to "marketingStg";

grant select on mk_quote_search_record to celloxsel;

create table mk_quote_mail_history
(
    mail_id          varchar(20) default nextval('mk_quote_history_seq'::regclass) not null
        constraint mk_quote_mail_history_pk
            primary key,
    sq_no            varchar(20),
    api_request_body text,
    url              varchar(100),
    api_send_date    timestamp,
    mail_status      varchar(10),
    ins_persion_id   varchar(50),
    ins_dtm          timestamp   default CURRENT_TIMESTAMP,
    upd_persion_id   varchar(50),
    upd_dtm          timestamp,
    result           text
);

comment on column mk_quote_mail_history.result is '返回值';

alter table mk_quote_mail_history
    owner to "marketingStg";

grant select on mk_quote_mail_history to celloxsel;

create table mk_quote_api_history
(
    sq_no             varchar(20) default (
            ('SQ-'::text || to_char(timezone('Asia/Seoul'::text, CURRENT_TIMESTAMP), 'yymmdd'::text)) ||
            lpad(((nextval('mk_quote_search_record_seq'::regclass))::character varying)::text, 6, '0'::text)) not null
        constraint mk_quote_api_history_pk
            primary key,
    api_url           varchar(100),
    api_request_body  text,
    api_response_body text,
    send_date         timestamp,
    received_date     timestamp,
    svc_med_ctgry_cd  varchar(20),
    svc_class_cd      varchar(20),
    arrp_nm           varchar(100),
    depp_nm           varchar(100),
    search_value      varchar(20),
    search_unit       varchar(20),
    search_ga         varchar(50),
    api_status        varchar(10),
    ins_persion_id    varchar(50),
    ins_dtm           timestamp   default CURRENT_TIMESTAMP,
    upd_persion_id    varchar(50),
    upd_dtm           timestamp,
    return_reason     varchar(256),
    access_ip         varchar(32),
    product_type      varchar(512)
);

comment on column mk_quote_api_history.return_reason is '退出原因 关联了mk_return_reason表的数据';

comment on column mk_quote_api_history.access_ip is '访问者ip';

comment on column mk_quote_api_history.product_type is '中技产品类型';

alter table mk_quote_api_history
    owner to "marketingStg";

grant select on mk_quote_api_history to celloxsel;

create table mk_help_support_menu
(
    id            bigserial
        constraint pk_mk_help_support_menu
            primary key,
    p_id          bigint       not null,
    menu_name     varchar(128) not null,
    is_child      bigint default 0,
    description   varchar(256),
    use_yn        char   default 'N'::bpchar,
    ordb          integer,
    ins_dtm       timestamp,
    ins_person_id varchar(100),
    upd_dtm       timestamp,
    upd_person_id varchar(100)
);

alter table mk_help_support_menu
    owner to "marketingStg";

grant select on mk_help_support_menu to celloxsel;

create table mk_help_support
(
    id                 bigserial
        constraint pk_mk_help_support
            primary key,
    top_id             bigint               not null,
    child_menu_id      bigint               not null,
    meta_seq_no        integer              not null,
    name               varchar(128),
    summary_info       text,
    contents           text,
    use_yn             char       default 'N'::bpchar,
    srch_cnt           integer    default 0,
    is_nice            integer    default 0 not null,
    un_help            integer    default 0 not null,
    complex_content    integer    default 0 not null,
    unresolved_issues  integer    default 0 not null,
    photo_link_invalid integer    default 0 not null,
    ugly_page          integer    default 0 not null,
    is_set_link        smallint   default 0,
    ordb               integer,
    ins_dtm            timestamp,
    ins_person_id      varchar(100),
    upd_dtm            timestamp,
    upd_person_id      varchar(100),
    source             char(2)    default 1 not null,
    enable_flag        varchar(2) default 0
);

comment on column mk_help_support.source is '来源 1、新增  2、导入';

comment on column mk_help_support.enable_flag is '是否有效1是0否';

alter table mk_help_support
    owner to "marketingStg";

grant select on mk_help_support to celloxsel;

create table mk_logistics_qa
(
    id                   bigserial
        constraint pk_mk_logistics_qa
            primary key,
    meta_seq_no          integer              not null,
    name                 varchar(128),
    summary_info         text,
    contents             text,
    is_nice              integer    default 0 not null,
    use_yn               char       default 'N'::bpchar,
    srch_cnt             integer    default 0,
    list_img_path        varchar(300),
    list_img_org_file_nm varchar(200),
    list_img_file_nm     varchar(200),
    is_set_link          smallint   default 0,
    ordb                 varchar(5),
    ins_dtm              timestamp,
    ins_person_id        varchar(100),
    upd_dtm              timestamp,
    upd_person_id        varchar(100),
    enable_flag          varchar(2) default 0
);

comment on column mk_logistics_qa.enable_flag is '是否有效1是0否';

alter table mk_logistics_qa
    owner to "marketingStg";

grant select on mk_logistics_qa to celloxsel;

create table mk_corporate_activities
(
    id                   bigserial
        constraint pk_mk_corporate_activities
            primary key,
    meta_seq_no          integer   not null,
    type                 char      not null,
    name                 varchar(128),
    summary_info         text,
    contents             text,
    srch_cnt             integer    default 0,
    use_yn               char       default 'N'::bpchar,
    list_img_path        varchar(300),
    list_img_org_file_nm varchar(200),
    list_img_file_nm     varchar(200),
    effective_time       timestamp not null,
    dead_time            timestamp not null,
    is_set_link          smallint   default 0,
    ordb                 varchar(5),
    ins_dtm              timestamp,
    ins_person_id        varchar(100),
    upd_dtm              timestamp,
    upd_person_id        varchar(100),
    enable_flag          varchar(2) default 0
);

comment on column mk_corporate_activities.enable_flag is '是否有效1是0否';

alter table mk_corporate_activities
    owner to "marketingStg";

grant select on mk_corporate_activities to celloxsel;

create table mk_activities_registration
(
    id               bigserial
        constraint pk_mk_activities_registration
            primary key,
    activities_id    bigint not null,
    name             varchar(128),
    phone            varchar(64),
    company          varchar(128),
    product_category varchar(64),
    transport_type   varchar(64),
    use_yn           char      default 'N'::bpchar,
    create_date      timestamp default CURRENT_TIMESTAMP
);

alter table mk_activities_registration
    owner to "marketingStg";

grant select on mk_activities_registration to celloxsel;

create table mk_antistop
(
    id               serial
        constraint pk_mk_antistop
            primary key,
    terminology_name varchar(256) not null,
    description      text,
    use_yn           char       default 'Y'::bpchar,
    srch_cnt         integer    default 0,
    ordb             varchar(5),
    ins_dtm          timestamp,
    ins_person_id    varchar(100),
    upd_dtm          timestamp,
    upd_person_id    varchar(100),
    meta_seq_no      bigint,
    enable_flag      varchar(2) default 0
);

comment on column mk_antistop.enable_flag is '是否有效1是0否';

alter table mk_antistop
    owner to "marketingStg";

grant select on mk_antistop to celloxsel;

create table mk_antistop_relation
(
    id           serial
        constraint pk_mk_antistop_relation
            primary key,
    term_id      bigint,
    article_id   bigint,
    article_type varchar(10),
    ins_dtm      timestamp
);

alter table mk_antistop_relation
    owner to "marketingStg";

grant select on mk_antistop_relation to celloxsel;

create table mk_seo_inner_chain
(
    id            serial
        constraint mk_seo_inner_chain_pk
            primary key,
    key_word      varchar(1000),
    link          varchar(500),
    count         integer,
    type          integer,
    start_time    timestamp,
    end_time      timestamp,
    ordb          varchar,
    ins_person_id varchar,
    upd_dtm       timestamp,
    upd_person_id varchar,
    use_yn        varchar,
    ins_dtm       timestamp
);

comment on table mk_seo_inner_chain is 'seo内链';

comment on column mk_seo_inner_chain.id is '主键id';

comment on column mk_seo_inner_chain.key_word is '关键词';

comment on column mk_seo_inner_chain.link is '链接';

comment on column mk_seo_inner_chain.count is '关联数量';

comment on column mk_seo_inner_chain.type is '文章类型';

comment on column mk_seo_inner_chain.start_time is '开始时间';

comment on column mk_seo_inner_chain.end_time is '结束时间';

comment on column mk_seo_inner_chain.ordb is '排序';

comment on column mk_seo_inner_chain.ins_person_id is '创建人';

comment on column mk_seo_inner_chain.upd_dtm is '修改时间';

comment on column mk_seo_inner_chain.upd_person_id is '修改人';

comment on column mk_seo_inner_chain.use_yn is '有效否';

alter table mk_seo_inner_chain
    owner to "marketingStg";

grant select on mk_seo_inner_chain to celloxsel;

create table mk_terminology
(
    id               serial
        constraint pk_mk_terminology
            primary key,
    terminology_name varchar(256) not null,
    description      text,
    use_yn           char       default 'Y'::bpchar,
    srch_cnt         integer    default 0,
    ordb             varchar(5),
    ins_dtm          timestamp,
    ins_person_id    varchar(100),
    upd_dtm          timestamp,
    upd_person_id    varchar(100),
    meta_seq_no      bigint,
    enable_flag      varchar(2) default 0,
    show_flag        varchar(1)
);

comment on column mk_terminology.terminology_name is '标题';

comment on column mk_terminology.enable_flag is '是否有效1是0否';

comment on column mk_terminology.show_flag is '是否展示';

alter table mk_terminology
    owner to "marketingStg";

create unique index mk_terminology_terminology_name_idx
    on mk_terminology (terminology_name);

grant select on mk_terminology to celloxsel;

create table mk_term_relation
(
    id           serial
        constraint pk_mk_term_relation
            primary key,
    term_id      bigint,
    article_id   bigint,
    article_type varchar(10),
    ins_dtm      timestamp
);

alter table mk_term_relation
    owner to "marketingStg";

grant select on mk_term_relation to celloxsel;

create table mk_dic
(
    id       serial,
    dic_name varchar
);

comment on column mk_dic.id is '主键id';

comment on column mk_dic.dic_name is '字典名';

alter table mk_dic
    owner to "marketingStg";

grant select on mk_dic to celloxsel;

create table mk_register_button
(
    id                   bigserial
        constraint pk_mk_register_button
            primary key,
    ip                   varchar(32)                   not null,
    source               char                          not null,
    is_push_baidu        char      default '0'::bpchar not null,
    is_push_gio          char      default '0'::bpchar not null,
    unique_id            varchar(128),
    url                  varchar(1024),
    create_time          timestamp default CURRENT_TIMESTAMP,
    identifier           varchar(64),
    channel              varchar(128),
    name                 varchar(128),
    bargain_product_name varchar,
    bargain_line_name    varchar,
    share_link           varchar(512)
);

comment on column mk_register_button.ip is '点击者ip';

comment on column mk_register_button.source is '来源 1.百度 2.GROWING_io 3、其他';

comment on column mk_register_button.is_push_baidu is '0未推送 1已推送';

comment on column mk_register_button.is_push_gio is '0未推送 1已推送';

comment on column mk_register_button.unique_id is '唯一标识';

comment on column mk_register_button.url is '请求的url 上级url';

comment on column mk_register_button.create_time is '点击时间';

comment on column mk_register_button.bargain_product_name is '特价舱产品名称';

comment on column mk_register_button.bargain_line_name is '特价舱线路名';

alter table mk_register_button
    owner to "marketingStg";

grant select on mk_register_button to celloxsel;

create table mk_quote_air_port
(
    node_cd          varchar(30) not null
        constraint pk_mk_quote_air_port
            primary key,
    nation_cd        varchar(30) not null,
    air_port_city_nm varchar(100),
    air_port_nm      varchar(100),
    upd_dtm          timestamp
);

alter table mk_quote_air_port
    owner to "marketingStg";

grant select on mk_quote_air_port to celloxsel;

create table mk_quote_ocean_port
(
    node_cd   varchar(30) not null
        constraint pk_mk_quote_ocean_port
            primary key,
    nation_cd varchar(30) not null,
    city_nm   varchar(100),
    upd_dtm   timestamp
);

alter table mk_quote_ocean_port
    owner to "marketingStg";

grant select on mk_quote_ocean_port to celloxsel;

create table mk_quote_nation
(
    nation_seq_no   bigserial
        constraint pk_mk_quote_nation
            primary key,
    nation_cd       varchar(5)           not null,
    nation_nm       varchar(50)          not null,
    nation_cn_nm    varchar(50),
    continent_cd    varchar(5),
    search_key_word varchar(300),
    use_yn          char       default 'Y'::bpchar,
    express_use_yn  char       default 'Y'::bpchar,
    img_file_path   varchar(300),
    img_org_file_nm varchar(200),
    img_file_nm     varchar(200),
    img_size        integer,
    ordb            varchar(5),
    enable_flag     varchar(2) default 0,
    ins_dtm         timestamp,
    ins_person_id   varchar(100),
    upd_dtm         timestamp,
    upd_person_id   varchar(100),
    is_hot          smallint   default 0 not null
);

comment on column mk_quote_nation.is_hot is '是否热门  0否  1是';

alter table mk_quote_nation
    owner to "marketingStg";

create index mk_quote_nation_nation_cd_idx
    on mk_quote_nation (nation_cd);

create index mk_quote_nation_continent_cd_idx
    on mk_quote_nation (continent_cd);

grant select on mk_quote_nation to celloxsel;

create table mk_quote_node
(
    id              bigserial
        constraint pk_mk_quote_node
            primary key,
    node_cd         varchar(30)                    not null,
    nation_seq_no   bigint,
    node_eng_nm     varchar(100),
    node_cn_nm      varchar(100),
    product_mode    varchar(16)                    not null,
    city_eng_nm     varchar(100),
    city_cn_nm      varchar(100),
    search_key_word varchar(300),
    node_status     char       default 'N'::bpchar not null,
    node_source     char       default 'A'::bpchar not null,
    use_yn          char       default 'Y'::bpchar,
    enable_flag     varchar(2) default 0,
    ins_dtm         timestamp,
    ins_person_id   varchar(100),
    upd_dtm         timestamp,
    upd_person_id   varchar(100),
    is_hot          smallint   default 0,
    ordb            varchar(5)
);

alter table mk_quote_node
    owner to "marketingStg";

create unique index mk_quote_node_node_cd_idx
    on mk_quote_node (node_cd);

create index mk_quote_node_nation_seq_no_idx
    on mk_quote_node (nation_seq_no);

grant select on mk_quote_node to celloxsel;

create table mk_quote_route
(
    route_seq_no  bigserial
        constraint pk_mk_quote_route
            primary key,
    nation_cd     varchar(5)                     not null,
    product_mode  varchar(50)                    not null,
    product_id    varchar(50),
    product_nm    varchar(200),
    from_node     varchar(30)                    not null,
    to_node       varchar(30)                    not null,
    route_source  char       default 'A'::bpchar not null,
    new_flag      char       default 'Y'::bpchar not null,
    use_yn        char       default 'Y'::bpchar,
    enable_flag   varchar(2) default 0,
    ins_dtm       timestamp,
    ins_person_id varchar(100),
    upd_dtm       timestamp,
    upd_person_id varchar(100),
    from_node_id  bigint,
    to_node_id    bigint
);

comment on column mk_quote_route.from_node_id is 'node表的id';

comment on column mk_quote_route.to_node_id is 'node表的id';

alter table mk_quote_route
    owner to "marketingStg";

grant select on mk_quote_route to celloxsel;

create table mk_route_api_history
(
    id                bigserial
        constraint pk_mk_route_api_history
            primary key,
    result_ord        integer,
    api_url           varchar(100),
    api_request_body  text,
    api_response_body text,
    send_date         timestamp,
    received_date     timestamp,
    api_status        varchar(10)
);

alter table mk_route_api_history
    owner to "marketingStg";

grant select on mk_route_api_history to celloxsel;

create table mk_quote_return_reason
(
    id            bigserial
        constraint pk_mk_quote_return_reason
            primary key,
    type          integer not null,
    reason        varchar(100),
    use_yn        char       default 'Y'::bpchar,
    ordb          varchar(10),
    enable_flag   varchar(2) default 0,
    ins_dtm       timestamp,
    ins_person_id varchar(100),
    upd_dtm       timestamp,
    upd_person_id varchar(100)
);

alter table mk_quote_return_reason
    owner to "marketingStg";

create unique index mk_quote_return_reason_type_idx
    on mk_quote_return_reason (type, reason);

grant select on mk_quote_return_reason to celloxsel;

create table mk_register_app_success
(
    id                     bigserial
        constraint pk_mk_register_app_success
            primary key,
    ip                     varchar(32)                   not null,
    from_source            char                          not null,
    is_push_baidu          char      default '0'::bpchar not null,
    is_push_gio            char      default '0'::bpchar not null,
    source_val             varchar(128),
    url                    varchar(1024),
    status                 varchar(64),
    organization_id        varchar(256),
    member1_approval_date  varchar(64),
    from_source_id         bigint                        not null,
    start_time             varchar(64),
    consume_time           varchar(16),
    create_time            timestamp default CURRENT_TIMESTAMP,
    app_success_identifier varchar(64),
    channel                varchar(128),
    name                   varchar(128),
    bargain_product_name   varchar,
    bargain_line_name      varchar,
    share_link             varchar(512)
);

comment on column mk_register_app_success.bargain_product_name is '特价舱产品名称';

comment on column mk_register_app_success.bargain_line_name is '特价舱线路名称';

alter table mk_register_app_success
    owner to "marketingStg";

grant select on mk_register_app_success to celloxsel;

create table mk_from_source
(
    id                     bigserial
        constraint pk_mk_from_source
            primary key,
    type                   char not null,
    source_val             varchar(256),
    identifier             varchar(64),
    app_success_identifier varchar(64),
    remark                 varchar(256),
    ins_dtm                timestamp,
    ins_person_id          varchar(100),
    upd_dtm                timestamp,
    upd_person_id          varchar(100),
    name                   varchar(128),
    share_link             varchar(512)
);

alter table mk_from_source
    owner to "marketingStg";

create unique index mk_from_source_source_val_idx
    on mk_from_source (source_val);

grant select on mk_from_source to celloxsel;

create table mk_api_history
(
    id            bigserial
        constraint pk_mk_api_history
            primary key,
    type          char      default 1                 not null,
    url           varchar(100),
    request_body  text,
    response_body text,
    exception_msg text,
    create_date   timestamp default CURRENT_TIMESTAMP not null,
    status        smallint  default 1                 not null
);

comment on column mk_api_history.status is '1成功  0失败';

alter table mk_api_history
    owner to "marketingStg";

grant select on mk_api_history to celloxsel;

create table mk_bargain
(
    id                   bigserial
        constraint mk_bargain_pk
            primary key,
    origin               varchar,
    destination          varchar,
    aging                varchar,
    remark               varchar,
    hot_flag             varchar,
    use_yn               char       default 'Y'::bpchar,
    srch_cnt             integer    default 0,
    ordb                 varchar(5),
    ins_dtm              timestamp,
    ins_person_id        varchar(100),
    upd_dtm              timestamp,
    upd_person_id        varchar(100),
    meta_seq_no          bigint,
    enable_flag          varchar(2) default 0,
    list_img_path        varchar(300),
    list_img_org_file_nm varchar(200),
    list_img_file_nm     varchar(200),
    identifier           varchar,
    product_id           bigint,
    currency             varchar,
    effective_date       timestamp,
    expiry_date          timestamp
);

comment on table mk_bargain is '特价舱';

comment on column mk_bargain.id is '主键';

comment on column mk_bargain.origin is '始发地';

comment on column mk_bargain.destination is '目的地';

comment on column mk_bargain.aging is '参考时效';

comment on column mk_bargain.remark is '备注';

comment on column mk_bargain.hot_flag is '是否热卖';

comment on column mk_bargain.identifier is '埋点标识符';

comment on column mk_bargain.product_id is '产品类型';

comment on column mk_bargain.currency is '币种';

comment on column mk_bargain.effective_date is '生效日期';

comment on column mk_bargain.expiry_date is '失效日期';

alter table mk_bargain
    owner to "marketingStg";

grant select on mk_bargain to celloxsel;

create table mk_bargain_price
(
    id        bigserial
        constraint mk_bargain_price_pk
            primary key,
    unit      varchar,
    price     varchar,
    parent_id integer
);

comment on table mk_bargain_price is '特价舱价格表';

comment on column mk_bargain_price.id is '主键id';

comment on column mk_bargain_price.unit is '单位';

comment on column mk_bargain_price.price is '价格';

comment on column mk_bargain_price.parent_id is '线路id';

alter table mk_bargain_price
    owner to "marketingStg";

grant select on mk_bargain_price to celloxsel;

create table mk_bargain_product
(
    id                          bigserial
        constraint mk_bargain_product_pk
            primary key,
    product_name                varchar,
    list_img_path               varchar(300),
    list_img_org_file_nm        varchar(200),
    list_img_file_nm            varchar(200),
    use_yn                      char       default 'Y'::bpchar,
    srch_cnt                    integer    default 0,
    ordb                        varchar(5),
    ins_dtm                     timestamp,
    ins_person_id               varchar(100),
    upd_dtm                     timestamp,
    upd_person_id               varchar(100),
    meta_seq_no                 bigint,
    enable_flag                 varchar(2) default 0,
    multi_flag                  varchar,
    mobile_list_img_path        varchar,
    mobile_list_img_org_file_nm varchar,
    mobile_list_img_file_nm     varchar
);

comment on table mk_bargain_product is '特价舱产品';

comment on column mk_bargain_product.id is '主键id';

comment on column mk_bargain_product.product_name is '产品名称';

comment on column mk_bargain_product.multi_flag is '是否多个价格';

alter table mk_bargain_product
    owner to "marketingStg";

grant select on mk_bargain_product to celloxsel;

create table mk_products_menu
(
    id            bigserial
        primary key,
    product_ctgry varchar(30),
    name          varchar(255) not null,
    use_yn        char         default 'N'::bpchar,
    ordb          integer,
    ins_dtm       timestamp,
    ins_person_id varchar(100) default NULL::character varying,
    upd_dtm       timestamp,
    upd_person_id varchar(100) default NULL::character varying,
    contents      text,
    enable_flag   varchar(2)   default 0
);

alter table mk_products_menu
    owner to "marketingStg";

create unique index mk_products_menu_product_ctgry_idx
    on mk_products_menu (product_ctgry);

grant select on mk_products_menu to celloxsel;

create table mk_continent
(
    id            bigserial
        primary key,
    name          varchar(255) not null,
    use_yn        char default 'N'::bpchar,
    fcl_ordb      integer,
    lcl_ordb      integer,
    air_ordb      integer,
    express_ordb  integer,
    ins_dtm       timestamp,
    ins_person_id varchar(100),
    upd_dtm       timestamp,
    upd_person_id varchar(100),
    remark        text,
    cd            varchar(16)  not null
);

alter table mk_continent
    owner to "marketingStg";

grant select on mk_continent to celloxsel;

create table mk_click_statistics
(
    id          bigserial
        constraint pk_mk_click_statistics
            primary key,
    ip          varchar(32) not null,
    source      char        not null,
    url         varchar(1024),
    create_time timestamp default CURRENT_TIMESTAMP
);

comment on column mk_click_statistics.source is '1、chatbot点击';

alter table mk_click_statistics
    owner to "marketingStg";

grant select on mk_click_statistics to celloxsel;

create table mk_mv_video_upload_history
(
    id            serial
        constraint mk_mv_video_upload_history_pk
            primary key,
    upload_status varchar,
    video_name    varchar,
    video_tittle  varchar,
    upload_man    varchar,
    upload_date   varchar,
    video_link    varchar,
    fail_info     text
);

alter table mk_mv_video_upload_history
    owner to "marketingStg";

create function fn_getcodenm(in_grp_cd character varying, in_cd character varying, in_lang_cd character varying) returns character varying
    language plpgsql
as
$$
DECLARE
  RE_TXT VARCHAR(100);
BEGIN
  SELECT CD_NM INTO RE_TXT FROM MK_CD_MNG WHERE GRP_CD=IN_GRP_CD AND CD=IN_CD AND LANG_CD=IN_LANG_CD;
  RETURN RE_TXT;
END; $$;

alter function fn_getcodenm(varchar, varchar, varchar) owner to "marketingStg";

create function fn_getmanagernm(in_data character varying) returns character varying
    language plpgsql
as
$$
DECLARE
  RE_TXT VARCHAR(40);
BEGIN
  SELECT ADM_USER_NM INTO RE_TXT FROM MK_ADM_MNG WHERE ADM_USER_ID = IN_DATA;
  RETURN RE_TXT;
END; $$;

alter function fn_getmanagernm(varchar) owner to "marketingStg";


