INSERT INTO "PUBLIC"."USER" VALUES
(1, FALSE, FALSE, 'liem@gmail.com', TRUE, FALSE, '$2a$10$ad3EURtYdKJJRaFDQ6bDde9mKCSH4F4KQsuTKGC6DS5UJcxjV9zj2', 'default', NULL, 'liemdoan');

CREATE USER IF NOT EXISTS "SA" SALT '9c02a46df2541a42' HASH '2c351fb6ee3232017135f756937a8125a1b057f5714f814fc2ed373820d0c3c7' ADMIN;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_FB5E4C02_0415_4A99_928F_9D0694979FF7" START WITH 2 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_A8B5E7FF_C168_40C9_880F_5928D0407409" START WITH 2 BELONGS_TO_TABLE;
CREATE MEMORY TABLE "PUBLIC"."USER"(
    "ID" INTEGER DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_A8B5E7FF_C168_40C9_880F_5928D0407409" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_A8B5E7FF_C168_40C9_880F_5928D0407409",
    "ACCOUNT_EXPIRED" BOOLEAN,
    "CREDENTIAL_EXPIRED" BOOLEAN,
    "EMAIL" VARCHAR(255),
    "ENABLED" BOOLEAN,
    "LOCKED" BOOLEAN,
    "PASSWORD" VARCHAR(255),
    "PROVIDER" VARCHAR(255),
    "UPDATED_AT" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "USERNAME" VARCHAR(255)
);

ALTER TABLE "PUBLIC"."USER" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_2" PRIMARY KEY("ID");


CREATE USER IF NOT EXISTS "SA" SALT '9c02a46df2541a42' HASH '2c351fb6ee3232017135f756937a8125a1b057f5714f814fc2ed373820d0c3c7' ADMIN;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_A8B5E7FF_C168_40C9_880F_5928D0407409" START WITH 2 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_FB5E4C02_0415_4A99_928F_9D0694979FF7" START WITH 2 BELONGS_TO_TABLE;
CREATE MEMORY TABLE "PUBLIC"."USER_PROFILE"(
    "ID" INTEGER DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_FB5E4C02_0415_4A99_928F_9D0694979FF7" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_FB5E4C02_0415_4A99_928F_9D0694979FF7",
    "DESCRIPTION" VARCHAR(255),
    "GENDER" INTEGER NOT NULL,
    "IMAGE" VARCHAR(255),
    "NICKNAME" VARCHAR(255),
    "UPDATE_AT" TIMESTAMP,
    "USER_ID" INTEGER
);
ALTER TABLE "PUBLIC"."USER_PROFILE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_C" PRIMARY KEY("ID");
INSERT INTO "PUBLIC"."USER_PROFILE" VALUES
(1, 'Hello, Let''s write your adventure here ...', 0, 'https://www.pngarts.com/files/5/User-Avatar-Transparent.png', 'Admin', TIMESTAMP '2021-05-23 13:36:22.803', 1);
ALTER TABLE "PUBLIC"."USER_PROFILE" ADD CONSTRAINT "PUBLIC"."UK_M9GA0CRHCGE7ONJ1GX9A5LNJY" UNIQUE("NICKNAME");
ALTER TABLE "PUBLIC"."USER_PROFILE" ADD CONSTRAINT "PUBLIC"."FK6KWJ5LK78PNHWOR4PGOSVB51R" FOREIGN KEY("USER_ID") REFERENCES "PUBLIC"."USER"("ID") NOCHECK;
