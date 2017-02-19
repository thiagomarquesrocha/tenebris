-- phpMyAdmin SQL Dump
-- version 4.1.4
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 19-Fev-2017 às 22:19
-- Versão do servidor: 5.6.15-log
-- PHP Version: 5.5.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tenebris2016`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `area`
--

CREATE TABLE IF NOT EXISTS `area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome_2` (`nome`),
  KEY `nome` (`nome`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=28 ;

--
-- Extraindo dados da tabela `area`
--

INSERT INTO `area` (`id`, `nome`) VALUES
(21, 'computaÃ§Ã£o e informÃ¡tica'),
(23, 'computação'),
(2, 'computação e informática'),
(27, 'computação e informática 2'),
(19, 'educação'),
(15, 'engenharia'),
(1, 'engenharia da computação'),
(20, 'informática'),
(17, 'inteligência artificial');

-- --------------------------------------------------------

--
-- Estrutura da tabela `autor`
--

CREATE TABLE IF NOT EXISTS `autor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=18 ;

--
-- Extraindo dados da tabela `autor`
--

INSERT INTO `autor` (`id`, `nome`) VALUES
(3, 'André'),
(7, 'Andrew S. Tanembaum'),
(8, 'Belmiro N. João'),
(1, 'Cazella'),
(5, 'Deitel'),
(12, 'fabio'),
(2, 'Fabio Santos Silva'),
(10, 'George F. Lucas'),
(4, 'H.l Capron e J.A Johson'),
(6, 'Iam Sommerville'),
(13, 'thiago'),
(17, 'thiago m.'),
(9, 'Thiago Marques');

-- --------------------------------------------------------

--
-- Estrutura da tabela `avaliacao`
--

CREATE TABLE IF NOT EXISTS `avaliacao` (
  `obra` int(11) NOT NULL,
  `usuario` int(11) NOT NULL,
  `avaliacao` float NOT NULL,
  PRIMARY KEY (`obra`,`usuario`),
  KEY `usuario` (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `avaliacao`
--

INSERT INTO `avaliacao` (`obra`, `usuario`, `avaliacao`) VALUES
(21, 2, 3),
(21, 5, 4),
(21, 34, 5),
(24, 34, 5),
(26, 2, 3),
(27, 2, 3),
(30, 34, 2),
(34, 2, 5),
(35, 2, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `instituicao`
--

CREATE TABLE IF NOT EXISTS `instituicao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Extraindo dados da tabela `instituicao`
--

INSERT INTO `instituicao` (`id`, `nome`) VALUES
(2, 'ens'),
(3, 'esa'),
(1, 'est');

-- --------------------------------------------------------

--
-- Estrutura da tabela `obra`
--

CREATE TABLE IF NOT EXISTS `obra` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` int(11) DEFAULT NULL,
  `instituicao` int(11) NOT NULL,
  `area` int(11) NOT NULL,
  `autor` int(11) DEFAULT NULL,
  `cadastradoEm` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `titulo` varchar(255) NOT NULL,
  `data` date DEFAULT NULL,
  `resumo` text,
  `imagem` varchar(255) DEFAULT NULL,
  `usuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `instituicao` (`instituicao`),
  KEY `area` (`area`),
  KEY `instituicao_2` (`instituicao`),
  KEY `area_2` (`area`),
  KEY `usuario` (`usuario`),
  KEY `autor` (`autor`),
  KEY `autor_2` (`autor`),
  KEY `tipo` (`tipo`),
  KEY `tipo_2` (`tipo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=37 ;

--
-- Extraindo dados da tabela `obra`
--

INSERT INTO `obra` (`id`, `tipo`, `instituicao`, `area`, `autor`, `cadastradoEm`, `titulo`, `data`, `resumo`, `imagem`, `usuario`) VALUES
(18, 1, 1, 17, 10, '2016-06-21 22:14:44', 'Inteligência Artificial', '2014-12-02', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integera mollis nulla. Nam pellentesqu e eu lacus a mattis. Maecenas ornare nibh in lacus feugiat con dimentum. Duis viverra nisl mi, vitae tincidunt augue\r\nfringilla.', NULL, NULL),
(19, 1, 1, 17, 2, '2016-06-21 22:14:44', 'PersonalTVware: Uma\r\nInfraestrutura de Suporte a\r\nSistemas de Recomendação\r\nSensíveis ao Contexto para TV\r\nDigital Personalizada', '2013-10-12', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integera mollis nulla. Nam pellentesqu e eu lacus a mattis. Maecenas ornare nibh in lacus feugiat con dimentum. Duis viverra nisl mi, vitae tincidunt augue\r\nfringilla.', NULL, NULL),
(20, 1, 1, 19, 9, '2016-06-21 22:14:44', 'NOTA 10 - Um Objeto de\r\nAprendizagem em Dispositivos\r\nMóveis Voltado Para\r\nMatemática Básica', '2015-12-12', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integera mollis nulla. Nam pellentesqu e eu lacus a mattis. Maecenas ornare nibh in lacus feugiat con dimentum. Duis viverra nisl mi, vitae tincidunt augue\r\nfringilla.', NULL, NULL),
(21, 1, 1, 1, 8, '2016-06-21 22:14:44', 'Informática Aplicada', '2014-06-06', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integera mollis nulla. Nam pellentesqu e eu lacus a mattis. Maecenas ornare nibh in lacus feugiat con dimentum. Duis viverra nisl mi, vitae tincidunt augue\r\nfringilla.', NULL, NULL),
(22, 1, 1, 17, 2, '2016-06-21 22:14:44', 'Desenvolvimento de um\r\nSistema de Recomendação de\r\nDocumentos Acadêmicos\r\nUtilizando Técnicas de\r\nFiltragem de Informação e\r\nAprendizagem de Máquina', '2015-06-06', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integera mollis nulla. Nam pellentesqu e eu lacus a mattis. Maecenas ornare nibh in lacus feugiat con dimentum. Duis viverra nisl mi, vitae tincidunt augue\r\nfringilla.', NULL, NULL),
(24, 1, 1, 2, 7, '2016-06-21 22:14:44', 'Organização e Estrutura de Computadores', '2007-01-01', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integera mollis nulla. Nam pellentesqu e eu lacus a mattis. Maecenas ornare nibh in lacus feugiat con dimentum. Duis viverra nisl mi, vitae tincidunt augue\r\nfringilla.', NULL, NULL),
(26, 1, 1, 2, 6, '2016-06-21 22:14:44', 'Engenharia de Software', '2006-06-06', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integera mollis nulla. Nam pellentesqu e eu lacus a mattis. Maecenas ornare nibh in lacus feugiat con dimentum. Duis viverra nisl mi, vitae tincidunt augue\r\nfringilla.', NULL, NULL),
(27, 1, 1, 2, 5, '2016-06-21 22:14:44', 'Sistemas Operacionais', '2007-01-01', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integera mollis nulla. Nam pellentesqu e eu lacus a mattis. Maecenas ornare nibh in lacus feugiat con dimentum. Duis viverra nisl mi, vitae tincidunt augue\r\nfringilla.', NULL, NULL),
(28, 1, 1, 1, 5, '2016-06-21 22:14:44', 'Java como programar', '2007-01-01', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integera mollis nulla. Nam pellentesqu e eu lacus a mattis. Maecenas ornare nibh in lacus feugiat con dimentum. Duis viverra nisl mi, vitae tincidunt augue\r\nfringilla.', NULL, NULL),
(29, 1, 1, 20, 4, '2016-06-21 22:14:44', 'Introdução a informática', '2008-12-31', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integera mollis nulla. Nam pellentesqu e eu lacus a mattis. Maecenas ornare nibh in lacus feugiat con dimentum. Duis viverra nisl mi, vitae tincidunt augue\r\nfringilla.', NULL, NULL),
(30, 1, 1, 2, 9, '2016-06-21 22:14:44', 'nova obra 31', '2014-06-23', 'olá', 'C:\\Users\\Thiago\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp2\\wtpwebapps\\tenebris_github\\obras\\21_5_2016_17_1_1466542903225.pdf', 2),
(33, 3, 1, 2, 13, '2016-07-03 19:17:18', 'teste', '2016-07-03', 'asdsadas', 'C:\\Users\\Thiago\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp2\\wtpwebapps\\tenebris_github\\obras\\3_6_2016_15_17_1467573438582.pdf', 2),
(34, 4, 1, 2, 13, '2016-07-03 20:52:21', 'um estudo de caso', '2016-07-25', 'dasdasdasasd', 'C:\\Users\\Thiago\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp2\\wtpwebapps\\tenebris_github\\obras\\3_6_2016_16_52_1467579141262.pdf', 2),
(35, 2, 3, 2, 17, '2016-07-03 20:53:42', 'teste', '2010-07-06', 'dasdas', 'C:\\Users\\Thiago\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp2\\wtpwebapps\\tenebris_github\\obras\\3_6_2016_16_53_1467579222386.pdf', 2),
(36, 1, 1, 2, 9, '2016-10-01 05:01:06', 'desenvolvimento de um jogo de dominó android', '2016-10-03', 'dominó em android', 'C:\\Users\\Thiago\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\tenebris\\obras\\1_9_2016_1_1_1475298065899.pdf', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=52 ;

--
-- Extraindo dados da tabela `tag`
--

INSERT INTO `tag` (`id`, `nome`) VALUES
(44, 'a.i'),
(51, 'computação'),
(48, 'filtragem'),
(50, 'informática'),
(49, 'inteligência artificial'),
(4, 'java'),
(3, 'jdk'),
(2, 'paper'),
(1, 'program'),
(46, 'reconhecimento'),
(47, 'tv');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipo_obra`
--

CREATE TABLE IF NOT EXISTS `tipo_obra` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Extraindo dados da tabela `tipo_obra`
--

INSERT INTO `tipo_obra` (`id`, `nome`) VALUES
(2, 'Artigo'),
(3, 'Dissertação'),
(4, 'Tese'),
(1, 'Trabalho de conclusão de curso (TCC)');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) NOT NULL,
  `area` int(11) DEFAULT NULL,
  `cadastradoEm` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  KEY `area` (`area`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=36 ;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `login`, `nome`, `senha`, `area`, `cadastradoEm`) VALUES
(1, 'salomao', 'salomao', '123', 1, '2016-01-04 20:19:03'),
(2, 'thiago', 'thiagô', '202cb962ac59075b964b07152d234b70', 2, '2016-03-31 00:29:27'),
(3, 'oi', 'oi', '1', 17, '2016-04-01 00:25:33'),
(4, 'oih', 'omjhhgi', '1', 1, '0000-00-00 00:00:00'),
(5, 'ohfi', 'bnoi', '1', 1, '0000-00-00 00:00:00'),
(6, 'oib', 'sdfoi', '1', 1, '0000-00-00 00:00:00'),
(7, 'ohji', 'ouyti', '1', 1, '0000-00-00 00:00:00'),
(8, 'oid', 'oopi', '1', 1, '0000-00-00 00:00:00'),
(9, 'hhhoi', 'okli', '1', 1, '0000-00-00 00:00:00'),
(10, 'nmoi', 'okki', '1', 1, '0000-00-00 00:00:00'),
(11, 'ohhhhi', 'ohhi', '1', 1, '0000-00-00 00:00:00'),
(12, 'noi', 'ohhi', '1', 1, '0000-00-00 00:00:00'),
(13, 'mgoi', 'oggi', '1', 1, '0000-00-00 00:00:00'),
(14, 'oigfd', 'offi', '1', 1, '0000-00-00 00:00:00'),
(15, 'oghsdfi', 'oddi', '1', 1, '0000-00-00 00:00:00'),
(16, 'ohhgfdddji', 'ossi', '1', 1, '0000-00-00 00:00:00'),
(17, 'lucas', 'lucas gabriel', 'eng', 1, '0000-00-00 00:00:00'),
(18, 'ojki', 'oaai', '1', 1, '0000-00-00 00:00:00'),
(19, 'oqwi', 'ozzi', '1', 1, '0000-00-00 00:00:00'),
(20, 'ozxi', 'oxxi', '1', 1, '0000-00-00 00:00:00'),
(34, 'iris', NULL, 'f75c9af2f9523fcaa0c029651ffe814b', NULL, '2016-04-01 03:24:22'),
(35, 'thiago2', NULL, '202cb962ac59075b964b07152d234b70', NULL, '2016-06-20 18:07:19');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario_autores`
--

CREATE TABLE IF NOT EXISTS `usuario_autores` (
  `usuario` int(11) NOT NULL,
  `autor` int(11) NOT NULL,
  PRIMARY KEY (`usuario`,`autor`),
  KEY `autor` (`autor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuario_autores`
--

INSERT INTO `usuario_autores` (`usuario`, `autor`) VALUES
(2, 2),
(2, 9);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario_instituicoes`
--

CREATE TABLE IF NOT EXISTS `usuario_instituicoes` (
  `usuario` int(11) NOT NULL,
  `instituicao` int(11) NOT NULL,
  PRIMARY KEY (`usuario`,`instituicao`),
  KEY `instituicao` (`instituicao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuario_instituicoes`
--

INSERT INTO `usuario_instituicoes` (`usuario`, `instituicao`) VALUES
(2, 1),
(34, 1),
(35, 1),
(2, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario_tags`
--

CREATE TABLE IF NOT EXISTS `usuario_tags` (
  `usuario` int(11) NOT NULL,
  `tag` int(11) NOT NULL,
  PRIMARY KEY (`usuario`,`tag`),
  KEY `tag` (`tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuario_tags`
--

INSERT INTO `usuario_tags` (`usuario`, `tag`) VALUES
(2, 50),
(2, 51);

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `avaliacao`
--
ALTER TABLE `avaliacao`
  ADD CONSTRAINT `avaliacao_ibfk_1` FOREIGN KEY (`obra`) REFERENCES `obra` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `avaliacao_ibfk_2` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `obra`
--
ALTER TABLE `obra`
  ADD CONSTRAINT `obra_ibfk_1` FOREIGN KEY (`instituicao`) REFERENCES `instituicao` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `obra_ibfk_2` FOREIGN KEY (`area`) REFERENCES `area` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `obra_ibfk_3` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `obra_ibfk_4` FOREIGN KEY (`autor`) REFERENCES `autor` (`id`) ON DELETE SET NULL,
  ADD CONSTRAINT `obra_ibfk_5` FOREIGN KEY (`tipo`) REFERENCES `tipo_obra` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Limitadores para a tabela `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`area`) REFERENCES `area` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Limitadores para a tabela `usuario_autores`
--
ALTER TABLE `usuario_autores`
  ADD CONSTRAINT `usuario_autores_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usuario_autores_ibfk_2` FOREIGN KEY (`autor`) REFERENCES `autor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `usuario_instituicoes`
--
ALTER TABLE `usuario_instituicoes`
  ADD CONSTRAINT `usuario_instituicoes_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usuario_instituicoes_ibfk_2` FOREIGN KEY (`instituicao`) REFERENCES `instituicao` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `usuario_tags`
--
ALTER TABLE `usuario_tags`
  ADD CONSTRAINT `usuario_tags_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usuario_tags_ibfk_2` FOREIGN KEY (`tag`) REFERENCES `tag` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;