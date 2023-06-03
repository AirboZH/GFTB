create table Commodity
(
    commodityId     int auto_increment
        primary key,
    commodityName   varchar(64)  null,
    commodityPic    varchar(264) null,
    commodityTag    varchar(64)  null,
    commodityDetail varchar(64)  null,
    commodityPrice  float        null,
    commodityShow   int          null
);

create table Oder
(
    oderId      int         null,
    commodityId int         null,
    customerId  int         null,
    oderNumber   int       null,
    oderAddress varchar(64) null,
    oderStatus  int         null,
    oderShow    int         null,
    createDate  datetime    null,
    updateDate  datetime    null
);

create table Shopping
(
    shoppingId     int auto_increment
        primary key,
    commodityId    int         null,
    userId         int         null,
    commodityName  varchar(64) null,
    commodityPic   varchar(64) null,
    commodityPrice float       null,
    commodityNum   int         null
);

create table Users
(
    userId     int auto_increment
        primary key,
    userName   varchar(64) null,
    password   varchar(64) null,
    role       int         null,
    address    varchar(64) null,
    money      float       null,
    createDate datetime    null,
    updateDate datetime    null
);


