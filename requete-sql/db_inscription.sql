#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: PERSONNE
#------------------------------------------------------------

CREATE TABLE PERSONNE(
        prenom    Varchar (25) ,
        mail      Varchar (25) ,
        id_candid Int NOT NULL ,
        PRIMARY KEY (id_candid ) ,
        UNIQUE (mail )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CANDIDAT
#------------------------------------------------------------

CREATE TABLE CANDIDAT( 
        id_candid   Int NOT NULL AUTO_INCREMENT,
        nomCandidat Varchar (25) ,
        PRIMARY KEY (id_candid ) ,
        UNIQUE (nomCandidat )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: COMPETITION
#------------------------------------------------------------

CREATE TABLE COMPETITION(
        nom_compet Varchar (25) NOT NULL ,
        dateclose  Date ,
        enEquipe   Bool ,
        PRIMARY KEY (nom_compet )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: EQUIPE
#------------------------------------------------------------

CREATE TABLE EQUIPE(
        id_candid Int NOT NULL ,
        PRIMARY KEY (id_candid )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: COMPOSER
#------------------------------------------------------------

CREATE TABLE COMPOSER(
        id_candid          Int NOT NULL ,
        id_candid_CANDIDAT Int NOT NULL ,
        PRIMARY KEY (id_candid ,id_candid_CANDIDAT )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: S'INSCRIRE
#------------------------------------------------------------

CREATE TABLE S_INSCRIRE(
        id_candid  Int NOT NULL ,
        nom_compet Varchar (25) NOT NULL ,
        PRIMARY KEY (id_candid ,nom_compet )
)ENGINE=InnoDB;

ALTER TABLE PERSONNE ADD CONSTRAINT FK_PERSONNE_id_candid FOREIGN KEY (id_candid) REFERENCES CANDIDAT(id_candid)ON DELETE CASCADE on UPDATE  CASCADE;
ALTER TABLE EQUIPE ADD CONSTRAINT FK_EQUIPE_id_candid FOREIGN KEY (id_candid) REFERENCES CANDIDAT(id_candid)ON DELETE CASCADE on UPDATE  CASCADE;
ALTER TABLE COMPOSER ADD CONSTRAINT FK_COMPOSER_id_candid FOREIGN KEY (id_candid) REFERENCES CANDIDAT(id_candid)ON DELETE CASCADE on UPDATE  CASCADE;
ALTER TABLE COMPOSER ADD CONSTRAINT FK_COMPOSER_id_candid_CANDIDAT FOREIGN KEY (id_candid_CANDIDAT) REFERENCES CANDIDAT(id_candid)ON DELETE CASCADE on UPDATE  CASCADE;
ALTER TABLE S_INSCRIRE ADD CONSTRAINT FK_S_INSCRIRE_id_candid FOREIGN KEY (id_candid) REFERENCES CANDIDAT(id_candid)ON DELETE CASCADE on UPDATE  CASCADE;
ALTER TABLE S_INSCRIRE ADD CONSTRAINT FK_S_INSCRIRE_nom_compet FOREIGN KEY (nom_compet) REFERENCES COMPETITION(nom_compet)ON DELETE CASCADE on UPDATE  CASCADE;
