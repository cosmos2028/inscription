/*  Trigger sur table personne pour contrôler l'insertion  */

DROP TRIGGER if exists before_insert_personne;


DELIMITER |
 
CREATE TRIGGER before_insert_personne BEFORE INSERT 
ON personne
FOR EACH ROW

BEGIN
	IF ( NEW.id_candid is not null  and NEW.prenom is not null and NEW.mail is not null) THEN

		IF EXISTS (SELECT id_candid
            FROM  EQUIPE
            WHERE  id_candid = NEW.id_candid) THEN
			SIGNAL SQLSTATE'65000'SET MESSAGE_TEXT = 'le nom se trouve déjà dans la table equipe. Insertion dans personne impossible.';
		end if;
		 /*  c'est pour personnaliser l'erreur(parce que la contrainte sur la clé etrangére le fait déjà) */
		IF NOT EXISTS (SELECT id_candid
            FROM  candidat
            WHERE id_candid = NEW.id_candid)THEN
			SIGNAL SQLSTATE'65000' SET MESSAGE_TEXT  = 'le nom inconnu dans la table candidat. Insertion dans personne impossible.';
		end if;

	ELSE
			SIGNAL SQLSTATE'65000'SET MESSAGE_TEXT = 'il faut remplir les champs demandés';
	end if;

END; 
|

************************************************
/*  Trigger sur table equipe pour contrôler l'insertion  */

DROP TRIGGER if exists before_insert_equipe;


DELIMITER |
 
CREATE TRIGGER before_insert_equipe BEFORE INSERT 
ON equipe
FOR EACH ROW

BEGIN
	IF (NEW.id_candid is not null) THEN 

		IF EXISTS (SELECT id_candid
            FROM  personne
            WHERE id_candid = NEW.id_candid)THEN
			SIGNAL SQLSTATE'65000'SET MESSAGE_TEXT = 'identifiant se trouve déjà dans la table personne. Insertion dans equipe impossible.';
		end if;
		 /*  c'est pour personnaliser l'erreur(parce que la contrainte sur la clé etrangére le fait déjà) */
		IF NOT EXISTS (SELECT id_candid
            FROM  candidat
            WHERE id_candid = NEW.id_candid)THEN
		SIGNAL SQLSTATE'65000' SET MESSAGE_TEXT  = 'identifiant inconnu dans la table candidat. Insertion dans equipe impossible.';
		end if;

	ELSE
			SIGNAL SQLSTATE'65000'SET MESSAGE_TEXT = 'il faut remplir les champs demandés';
	end if;

END; 
|
*********************************************
/*  Trigger sur table composé pour eviter l'insertion  de id identique 
provenant de table personne et equipe*/

DROP TRIGGER if exists before_insert_composer;

DELIMITER |
 
CREATE TRIGGER before_insert_composer BEFORE INSERT 
ON COMPOSER
FOR EACH ROW

BEGIN
	IF (NEW.id_candid is null or NEW.id_candid_CANDIDAT is null) THEN
		SIGNAL SQLSTATE'65000'SET MESSAGE_TEXT = 'il faut remplir les champs demandés';

	ELSEIF (NEW.id_candid = NEW.id_candid_CANDIDAT)THEN
		SIGNAL SQLSTATE'65000' SET MESSAGE_TEXT  = 'insertion impossible car les deux identifiant sont identique.';	
	end if;

END; 
|
********************procedure*************************
DROP PROCEDURE if exists GetAllPersonne;
DELIMITER |
CREATE PROCEDURE GetAllPersonne()      
BEGIN
    SELECT prenom,mail,nomCandidat
    FROM personne,CANDIDAT
    where personne.id_candid = CANDIDAT.id_candid;  
END|   

******************************************************
DROP PROCEDURE if exists GetoneCandidat;
DELIMITER |
CREATE PROCEDURE GetoneCandidat(in nomCand varchar(25) )      
BEGIN
    select * from CANDIDAT where nomCandidat =nomCand;
END| 

******************************************************
DROP PROCEDURE if exists addPersonne;
DELIMITER |
CREATE PROCEDURE addPersonne(in prenom Varchar (25) ,email Varchar (25) ,nom Varchar (25) )

		  
BEGIN
	Declare  idCandid  int;

	Insert into CANDIDAT(nomCandidat) 
	values(nom);

	select id_candid into idCandid
	from   CANDIDAT
	where  nomCandidat = nom;

	Insert into personne(prenom,mail,id_candid) 
	values(prenom,email,idCandid);
END|       

******************************************************
DROP PROCEDURE if exists addEquipe;
DELIMITER |
CREATE PROCEDURE addEquipe(in nomCandid Varchar (25) )      
BEGIN
	Declare  idCandid  int;

	Insert into CANDIDAT(nomCandidat) 
	values(nomCandid);

	select id_candid into idCandid
	from   CANDIDAT
	where  nomCandidat = nomCandid;

	Insert into equipe(id_candid) 
	values(idCandid);
END|       
******************************************************
DROP PROCEDURE if exists addCandidat;
DELIMITER |
CREATE PROCEDURE addCandidat(in nomCandidat Varchar (25) )      
BEGIN
Insert into candidat(nomCandidat) 
values(nomCandidat);
END|       
******************************************************
DROP PROCEDURE if exists addCompetition;
DELIMITER |
CREATE PROCEDURE addCompetition(in nom_compet Varchar (25),dateclose Date ,enEquipe Bool)      
BEGIN
Insert into COMPETITION(nom_compet,dateclose,enEquipe)
 values(nom_compet,dateclose,enEquipe);
END|       

******************************************************
DROP PROCEDURE if exists addInscrit;
DELIMITER |
CREATE PROCEDURE addInscrit(in nomCandid Varchar (25),nom_compet Varchar (25))      
BEGIN
	Declare  idCandid  int;

	select id_candid into idCandid
	from   CANDIDAT
	where  nomCandidat = nomCandid;

	Insert into S_INSCRIRE(id_candid,nom_compet)
 	values(idCandid,nom_compet);
END| 

******************************************************
DROP PROCEDURE if exists addPersonneInEquipe;
DELIMITER |
CREATE PROCEDURE addPersonneInEquipe(in nomCandid Varchar (25),nomCandid2 Varchar (25))      
BEGIN
	Declare  idCandid,idCandid2   int;

	select id_candid into idCandid
	from   CANDIDAT
	where  nomCandidat = nomCandid;

	select id_candid into idCandid2
	from   CANDIDAT
	where  nomCandidat = nomCandid2;

	Insert into COMPOSER(id_candid,id_candid_CANDIDAT)
 	values(idCandid,idCandid2);
END|
call addPersonneInEquipe("kk","foot");