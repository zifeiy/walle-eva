-- 福农卡资金二级去向
-- 建表
CREATE TABLE HAPPY_FARMER_CARD_SECOND_TIRE_TREND
(
    CUST_ISN    VARCHAR(50),                -- 客户内码
    CUST_ID     VARCHAR(50),                -- 客户号
    CUST_NAME   VARCHAR(50),                -- 客户姓名
    CUST_STATUS VARCHAR(50),                -- 客户状态
    CARD_ID     VARCHAR(50),                -- 卡号
    ACCT_ID     VARCHAR(50),                -- 账号
    HAPPY_FARMER_CARD_ID VARCHAR(50),       -- 福农卡卡号
    ORG_ID     VARCHAR(20),                 -- 信用卡推广机构号
    ORG_NAME     VARCHAR(100),              -- 信用卡推广机构名称
    TRAN_DATE     VARCHAR(50),              -- 交易日期
    TRAN_TIME        VARCHAR(50),           -- 交易时间
    TRAN_FLOW        VARCHAR(50),           -- 交易流水号
    TRAN_CHANNEL     VARCHAR(50),           -- 交易渠道
    PURPOSE     VARCHAR(100),               -- 用途说明
    AMOUNT      DECIMAL(15,2),              -- 交易金额
    BALANCE     DECIMAL(15,2),              -- 交易余额
    TO_ACCT_ID  VARCHAR(50),                -- 对方账号
    XZ_FLAG     VARCHAR(10),                -- 现转标志
    WHEREABOUTS     VARCHAR(50),            -- 资金去向
    LAST_GIVE_DATE  VARCHAR(50),            -- 最近一次福农卡转入日期
    LAST_GIVE_AMOUNT    VARCHAR(50),        -- 最近一次福农卡转入日期对应当天总转入金额
    DELTA_DAYS   INTEGER                    -- 距离最近一次福农卡转入日期相差天数
);

CREATE TABLE TMP_HAPPY_FARMER_CARD_SECOND_TIRE_TREND_HQAC_LIST
(
    CARD_ID VARCHAR(50),
    HAPPY_FARMER_CARD_ID    VARCHAR(50),
    THAT_DAY_AMOUNT DECIMAL(15,2),
    TRAN_DATE  VARCHAR(20)
);



