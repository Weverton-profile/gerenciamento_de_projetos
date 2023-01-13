-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema gerenciamento_de_projetos
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gerenciamento_de_projetos
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gerenciamento_de_projetos` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `gerenciamento_de_projetos` ;

-- -----------------------------------------------------
-- Table `gerenciamento_de_projetos`.`log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gerenciamento_de_projetos`.`log` (
  `id_projeto` INT NOT NULL,
  `descricao` VARCHAR(150) NOT NULL,
  `data_log` DATETIME NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `gerenciamento_de_projetos`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gerenciamento_de_projetos`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_usuario` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `senha` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `gerenciamento_de_projetos`.`projeto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gerenciamento_de_projetos`.`projeto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `descricao` VARCHAR(255) NOT NULL,
  `gerente_id` INT NOT NULL,
  `andamento` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `gerente_id_idx` (`gerente_id` ASC) VISIBLE,
  CONSTRAINT `gerente_id`
    FOREIGN KEY (`gerente_id`)
    REFERENCES `gerenciamento_de_projetos`.`usuario` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 30
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `gerenciamento_de_projetos`.`membros_projeto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gerenciamento_de_projetos`.`membros_projeto` (
  `id_projeto` INT NOT NULL,
  `membros_id` INT NOT NULL,
  INDEX `id_projeto_idx` (`id_projeto` ASC) VISIBLE,
  INDEX `membros_id_idx` (`membros_id` ASC) VISIBLE,
  CONSTRAINT `id_projeto`
    FOREIGN KEY (`id_projeto`)
    REFERENCES `gerenciamento_de_projetos`.`projeto` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `membros_id`
    FOREIGN KEY (`membros_id`)
    REFERENCES `gerenciamento_de_projetos`.`usuario` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `gerenciamento_de_projetos`.`tarefa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gerenciamento_de_projetos`.`tarefa` (
  `id_projeto_tarefa` INT NOT NULL,
  `membro_id` INT NULL DEFAULT NULL,
  `andamento` VARCHAR(50) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `tempo_estimado` INT NOT NULL,
  `data_fazendo` DATETIME NULL DEFAULT NULL,
  `data_feito` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_projeto_idx` (`id_projeto_tarefa` ASC) VISIBLE,
  CONSTRAINT `id_projeto_tarefa`
    FOREIGN KEY (`id_projeto_tarefa`)
    REFERENCES `gerenciamento_de_projetos`.`projeto` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `gerenciamento_de_projetos` ;

-- -----------------------------------------------------
-- procedure criar_projeto
-- -----------------------------------------------------

DELIMITER $$
USE `gerenciamento_de_projetos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `criar_projeto`(nome_projeto varchar(50), descricao varchar(255), gerente_id int)
BEGIN
	INSERT INTO PROJETO (nome, descricao, gerente_id, andamento) VALUES (nome_projeto, descricao, gerente_id, 'EM ANDAMENTO');
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure deletar_projeto
-- -----------------------------------------------------

DELIMITER $$
USE `gerenciamento_de_projetos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deletar_projeto`(id_projeto int)
BEGIN
	DELETE FROM projeto WHERE id = id_projeto;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure excluir_membro_do_projeto
-- -----------------------------------------------------

DELIMITER $$
USE `gerenciamento_de_projetos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `excluir_membro_do_projeto`(membro_excluir int, projeto_id int)
BEGIN
	DELETE FROM membros_projeto WHERE membros_id = membro_excluir AND id_projeto = projeto_id;
    DELETE FROM tarefa WHERE membro_id = membro_excluir AND id_projeto_tarefa = projeto_id;
END$$

DELIMITER ;
USE `gerenciamento_de_projetos`;

DELIMITER $$
USE `gerenciamento_de_projetos`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `gerenciamento_de_projetos`.`ADD_FIRST_USER`
AFTER INSERT ON `gerenciamento_de_projetos`.`projeto`
FOR EACH ROW
BEGIN
	INSERT INTO membros_projeto(id_projeto, membros_id)
    VALUES (NEW.id, NEW.gerente_id);
END$$

USE `gerenciamento_de_projetos`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `gerenciamento_de_projetos`.`TG_GERAR_LOG_PROJETO`
AFTER UPDATE ON `gerenciamento_de_projetos`.`projeto`
FOR EACH ROW
BEGIN
	INSERT INTO log(id_projeto, descricao, data_log) VALUES (OLD.id, concat('Projeto atualizado para ', NEW.andamento), now()); 
END$$

USE `gerenciamento_de_projetos`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `gerenciamento_de_projetos`.`TG_GERAR_LOG_MEMBROPROJETO_DELETE`
BEFORE DELETE ON `gerenciamento_de_projetos`.`membros_projeto`
FOR EACH ROW
BEGIN
	DECLARE email_usuario VARCHAR(45);
    SET email_usuario = (SELECT DISTINCT email FROM membros_projeto left join usuario on(membros_projeto.membros_id = usuario.id) WHERE usuario.id = OLD.membros_id);
	INSERT INTO log(id_projeto, descricao, data_log) VALUES (OLD.id_projeto, concat('O Usuario de EMAIL: ', email_usuario , ' foi deletada do projeto'), now()); 
END$$

USE `gerenciamento_de_projetos`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `gerenciamento_de_projetos`.`TG_GERAR_LOG_MEMBROPROJETO_INSERT`
AFTER INSERT ON `gerenciamento_de_projetos`.`membros_projeto`
FOR EACH ROW
BEGIN
	DECLARE email_usuario VARCHAR(45);
    SET email_usuario = (SELECT DISTINCT email FROM membros_projeto left join usuario on(membros_projeto.membros_id = usuario.id) WHERE usuario.id = NEW.membros_id);
	INSERT INTO log(id_projeto, descricao, data_log) VALUES (NEW.id_projeto, concat('O Usuario de EMAIL: ', email_usuario , ' foi adicionado ao projeto'), now()); 
END$$

USE `gerenciamento_de_projetos`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `gerenciamento_de_projetos`.`TG_GERAR_LOG_TAREFA_DELETE`
BEFORE DELETE ON `gerenciamento_de_projetos`.`tarefa`
FOR EACH ROW
BEGIN
	INSERT INTO log(id_projeto, descricao, data_log) VALUES (OLD.id_projeto_tarefa, concat('A tarefa ', OLD.nome, ' foi deletada'), now()); 
END$$

USE `gerenciamento_de_projetos`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `gerenciamento_de_projetos`.`TG_GERAR_LOG_TAREFA_INSERT`
AFTER INSERT ON `gerenciamento_de_projetos`.`tarefa`
FOR EACH ROW
BEGIN
	INSERT INTO log(id_projeto, descricao, data_log) VALUES (NEW.id_projeto_tarefa, concat('A tarefa ', NEW.nome, ' foi criada'), now()); 
END$$

USE `gerenciamento_de_projetos`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `gerenciamento_de_projetos`.`TG_GERAR_LOG_TAREFA_UPDATE`
AFTER UPDATE ON `gerenciamento_de_projetos`.`tarefa`
FOR EACH ROW
BEGIN
	DECLARE email_usuario VARCHAR(45);
    SET email_usuario = (SELECT DISTINCT email FROM tarefa left join usuario on(tarefa.membro_id = usuario.id) WHERE usuario.id = NEW.membro_id);
	INSERT INTO log(id_projeto, descricao, data_log) VALUES (OLD.id_projeto_tarefa, concat('A tarefa ', NEW.nome, ' foi movida para ', NEW.andamento, ' por ', email_usuario), now()); 
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
