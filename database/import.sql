-- phpMyAdmin SQL Dump
-- version 4.1.4
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 23-Jul-2017 às 03:07
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=41 ;

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
(17, 'inteligência artificial'),
(28, 'metodologia'),
(29, 'sistemas de informação');

-- --------------------------------------------------------

--
-- Estrutura da tabela `autor`
--

CREATE TABLE IF NOT EXISTS `autor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=116 ;

--
-- Extraindo dados da tabela `autor`
--

INSERT INTO `autor` (`id`, `nome`) VALUES
(67, 'abner j.v. matos , ricardo m. guimarães , renata da e. onety'),
(75, 'abner j.v. matos, ricardo m. guimarães, renata da e. onety'),
(96, 'alexsandro guerreiro de lima, walter c. s. simões'),
(92, 'allex lima, amanda bastos, nicoli araújo, walter simões, marlene faria'),
(93, 'allex lima, daniel bispo, edson brilhante, luan serra, walter simões'),
(87, 'anderson t. de araujo, walter c.s. simões, leonardo s. valcácio, neustlan a.  de a. junior'),
(3, 'André'),
(50, 'andré luiz do canto portela'),
(7, 'Andrew S. Tanembaum'),
(23, 'antónio oliveira e sousa, antónio mortal'),
(8, 'Belmiro N. João'),
(51, 'bruno mendes'),
(64, 'carlos a. m. da silva filho, adriano m. gil, jucimar m. da silva junior'),
(66, 'carlos a. m. da silva filho, adriano m. gil, jucimar m. da silva juniot'),
(18, 'carolina brandão gonçalves'),
(1, 'Cazella'),
(99, 'charles m. dos s. santa rosa, walter c. s. seiffert simões'),
(52, 'clarice de souza santos'),
(97, 'daivson dos s. melo, walter c. s. simões, leonardo s. valcácio'),
(63, 'danúbia ramos lustosa'),
(40, 'Daniel Henrique Braz de Aquino'),
(39, 'danúbia ramos lustoza'),
(33, 'darlisson marinho de jesus'),
(31, 'darlisson marinho jesus'),
(5, 'Deitel'),
(41, 'diego de azevedo barros'),
(57, 'dioneide leal sales'),
(77, 'edgard l. o. silva, leandro galvão, edjair mota, yuzo iano'),
(53, 'edson soares borges'),
(25, 'emiliano carlos de moraes firmino'),
(83, 'erick a. bogarin, kelen a. vieira, carlos a. mar, katia c. n.  da silva, sérgio r. c. vieira'),
(12, 'fabio'),
(82, 'fábio cruz, anand subramanian'),
(2, 'Fabio Santos Silva'),
(74, 'felipe augusto vilar de almeira, marcela sávia picanço pessoa, sergio  cleger tamayo'),
(54, 'george da rocha medeireos nunes'),
(10, 'George F. Lucas'),
(4, 'H.l Capron e J.A Johson'),
(42, 'helder cunha batista'),
(6, 'Iam Sommerville'),
(30, 'iasmin souza da cunha'),
(79, 'igor f. s. r. carneiro, alice a. f. menezes, carlos a. dos a. mar'),
(88, 'janderson bruno benchimol silva, marcos filipe alves salame, josé olenilson  costa pinheiro'),
(58, 'janderson de melo antunes'),
(55, 'joão guilherme alves martinez'),
(29, 'joelton dos santos matos'),
(43, 'josiane rodrigues da silva'),
(94, 'josiel w. v. dos santos, kaleb d. da silva, luis s. o. de castro'),
(24, 'juliana martins figueira'),
(59, 'karie keuma batista da silva'),
(20, 'karine keuma batista da silva'),
(56, 'lanier menezes dos santos'),
(28, 'leandro maurício auraújo bentes'),
(91, 'leonardo s. valcácio, walter c. s. simões, neustlan a. de a. junior, anderson  t. de araujo, daivson dos s. melo'),
(44, 'lia alessandra da silva martins'),
(45, 'lídia lizziane serejo de carvalho'),
(19, 'luana lopes casas'),
(90, 'lucas gabriel coimbra evangelista, salomão vilaça, fábio santos da silva'),
(70, 'márcio a.da c. alencar, walter c. s. simões, vandermi j. da silva, vicente f.  lucena jr.'),
(73, 'marcos filipe alves salame, flavia matos salame'),
(69, 'maria a. c. meireles, jorge y. kanda, bruno a. bonifácio, homero f. cruz'),
(27, 'mario angel praia garcia'),
(95, 'mario ferreira de almeida neto, walter c. s. s. simões'),
(86, 'nayara ribeiro, walter simões'),
(101, 'neustlan a. de a. junior, walter c. s. simões, leonardo s. valcácio, anderson  t. de araujo'),
(65, 'nicoli pinheiro de araújo, elloá b. guedes'),
(81, 'patrick magalhães de lima, elloá b. guedes'),
(98, 'priscilla b. do nascimento, elaine h. teixeira de oliveira'),
(72, 'randerson s. queiroz, anna beatriz marques'),
(22, 'raphael maquiné marinho'),
(100, 'raquel r. sobrinho, walter charles s. s. simões'),
(89, 'rikson pereira de oliveira, walter c. s.s. simões'),
(46, 'rodrigo barros bernadino'),
(84, 'rolando salazar-hernández, clarisa pérez-jasso, j. omar padrón g., rodrigo salazar-pérez'),
(102, 'ronan ripasy s. soares, walter simões'),
(85, 'rubson cardoso mota, almir de oliveira costa junior'),
(47, 'silvia oliveira da costa'),
(71, 'tarcia b. borges, marcos c. araújo, anna beatriz marques'),
(34, 'teste'),
(13, 'thiago'),
(104, 'thiago e fábio'),
(17, 'thiago m.'),
(9, 'Thiago Marques'),
(21, 'victor kaleb leite gomes'),
(48, 'victor matheus de souza pereira'),
(76, 'wanderlan carvalho de albuquerque, walter c.s. simões, wollace picanço,  antonio da f. de lira, josé p. de queiroz neto'),
(26, 'william bremgartner belleza'),
(49, 'yasmine gabriele da silva souza'),
(78, 'yasnalla. rivero, leydis. lamoth, maira.moreno, sergio.cleger'),
(80, 'yugo m. alencar, lucídio a. f. cabral, felipe o. m. cunha');

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
(36, 2, 5),
(36, 54, 3),
(36, 55, 3),
(36, 56, 3),
(36, 57, 4),
(36, 60, 5),
(36, 61, 5),
(36, 62, 5),
(36, 63, 4),
(36, 65, 1),
(36, 66, 1),
(36, 67, 3),
(36, 74, 5),
(37, 2, 5),
(37, 54, 4),
(37, 55, 3),
(37, 56, 4),
(37, 57, 4),
(37, 60, 5),
(37, 61, 5),
(37, 62, 4),
(37, 63, 4),
(37, 65, 1),
(37, 66, 5),
(37, 67, 4),
(39, 2, 4),
(39, 54, 2),
(39, 55, 1),
(39, 56, 5),
(39, 57, 4),
(39, 60, 3),
(39, 61, 2),
(39, 63, 2),
(39, 65, 1),
(39, 66, 1),
(39, 67, 3),
(40, 2, 3),
(40, 54, 2),
(40, 55, 4),
(40, 56, 4),
(40, 57, 3),
(40, 60, 4),
(40, 61, 1),
(40, 62, 4),
(40, 63, 3),
(40, 65, 1),
(40, 66, 1),
(40, 67, 4),
(40, 74, 4),
(41, 2, 5),
(41, 54, 1),
(41, 55, 5),
(41, 56, 3),
(41, 57, 2),
(41, 60, 4),
(41, 61, 1),
(41, 62, 2),
(41, 63, 1),
(41, 65, 3),
(41, 66, 1),
(41, 67, 2),
(41, 69, 3),
(41, 74, 2),
(42, 2, 5),
(42, 54, 4),
(42, 55, 3),
(42, 56, 1),
(42, 57, 3),
(42, 60, 3),
(42, 61, 1),
(42, 62, 1),
(42, 63, 2),
(42, 65, 1),
(42, 66, 1),
(42, 67, 2),
(44, 2, 2),
(44, 54, 4),
(44, 55, 3),
(44, 56, 4),
(44, 57, 3),
(44, 60, 3),
(44, 61, 5),
(44, 62, 1),
(44, 63, 1),
(44, 65, 1),
(44, 66, 1),
(44, 67, 1),
(44, 74, 1),
(45, 2, 5),
(45, 54, 4),
(45, 55, 4),
(45, 56, 4),
(45, 57, 5),
(45, 60, 5),
(45, 61, 5),
(45, 62, 5),
(45, 63, 4),
(45, 65, 1),
(45, 66, 5),
(45, 67, 3),
(46, 2, 3),
(46, 54, 3),
(46, 55, 5),
(46, 56, 4),
(46, 57, 2),
(46, 60, 3),
(46, 61, 5),
(46, 62, 3),
(46, 63, 2),
(46, 65, 5),
(46, 66, 5),
(46, 67, 4),
(46, 69, 4),
(48, 2, 4),
(48, 54, 4),
(48, 55, 2),
(48, 56, 5),
(48, 57, 3),
(48, 60, 2),
(48, 62, 5),
(48, 63, 5),
(48, 65, 1),
(48, 66, 5),
(48, 67, 3),
(52, 2, 5),
(52, 54, 3),
(52, 55, 3),
(52, 56, 3),
(52, 57, 3),
(52, 60, 4),
(52, 61, 5),
(52, 62, 3),
(52, 63, 4),
(52, 65, 1),
(52, 66, 4),
(52, 67, 3),
(52, 69, 3),
(52, 74, 4),
(54, 2, 4),
(54, 54, 3),
(54, 55, 4),
(54, 56, 2),
(54, 60, 2),
(54, 62, 1),
(54, 63, 3),
(54, 66, 1),
(54, 67, 1),
(55, 2, 5),
(55, 54, 3),
(55, 55, 3),
(55, 56, 3),
(55, 60, 2),
(55, 61, 5),
(55, 62, 1),
(55, 63, 4),
(55, 66, 1),
(55, 67, 1),
(56, 2, 4),
(56, 54, 4),
(56, 55, 3),
(56, 56, 4),
(56, 57, 5),
(56, 60, 5),
(56, 62, 4),
(56, 63, 3),
(56, 65, 1),
(56, 66, 1),
(56, 67, 3),
(57, 2, 5),
(57, 54, 4),
(57, 55, 3),
(57, 56, 2),
(57, 61, 2),
(57, 62, 1),
(57, 63, 2),
(58, 54, 4),
(58, 55, 3),
(58, 56, 2),
(58, 57, 3),
(58, 61, 5),
(58, 63, 2),
(59, 2, 3),
(59, 54, 5),
(59, 55, 4),
(59, 56, 2),
(59, 57, 5),
(59, 60, 4),
(59, 63, 4),
(59, 65, 5),
(59, 66, 1),
(59, 67, 2),
(60, 2, 3),
(60, 54, 1),
(60, 55, 5),
(60, 56, 4),
(60, 61, 5),
(60, 62, 5),
(60, 63, 1),
(60, 69, 4),
(60, 76, 4),
(61, 2, 5),
(61, 54, 2),
(61, 55, 4),
(61, 56, 3),
(61, 61, 5),
(61, 62, 3),
(61, 63, 5),
(61, 65, 5),
(62, 2, 5),
(62, 54, 2),
(62, 55, 5),
(62, 56, 4),
(62, 57, 4),
(62, 61, 5),
(62, 63, 3),
(62, 65, 5),
(63, 2, 5),
(63, 57, 5),
(64, 2, 5),
(64, 57, 2),
(65, 2, 5),
(65, 57, 4),
(65, 61, 5),
(65, 76, 3),
(66, 2, 5),
(66, 57, 5),
(66, 60, 5),
(66, 62, 4),
(67, 2, 5),
(67, 57, 2),
(68, 2, 5),
(68, 57, 3),
(68, 60, 3),
(68, 62, 1),
(69, 2, 5),
(69, 57, 2),
(69, 60, 3),
(69, 61, 3),
(70, 2, 5),
(70, 57, 4),
(70, 60, 4),
(70, 61, 3),
(70, 62, 2),
(71, 2, 1),
(71, 54, 1),
(71, 57, 2),
(71, 60, 1),
(71, 61, 3),
(71, 62, 3),
(71, 63, 1),
(72, 2, 5),
(72, 57, 5),
(72, 60, 5),
(72, 61, 5),
(73, 2, 5),
(73, 57, 2),
(73, 63, 2),
(74, 2, 4),
(74, 62, 4),
(74, 63, 5),
(75, 60, 3),
(75, 61, 3),
(75, 63, 2),
(77, 2, 5),
(77, 54, 3),
(77, 62, 5),
(77, 63, 3),
(78, 2, 1),
(78, 54, 5),
(78, 62, 5),
(78, 63, 5),
(79, 2, 5),
(79, 61, 5),
(80, 2, 5),
(80, 54, 5),
(80, 55, 3),
(80, 57, 3),
(81, 2, 5),
(81, 76, 4),
(82, 2, 5),
(82, 55, 4),
(83, 2, 5),
(83, 61, 3),
(84, 2, 5),
(84, 62, 5),
(85, 2, 5),
(85, 54, 4),
(85, 55, 4),
(85, 57, 5),
(85, 61, 2),
(85, 62, 5),
(86, 2, 3),
(86, 60, 5),
(87, 2, 2),
(87, 63, 4),
(88, 2, 5),
(88, 61, 2),
(88, 63, 2),
(89, 2, 4),
(89, 56, 2),
(89, 57, 1),
(89, 62, 1),
(89, 63, 3),
(90, 2, 5),
(90, 56, 4),
(90, 60, 4),
(90, 63, 2),
(91, 2, 5),
(91, 56, 3),
(91, 60, 4),
(91, 61, 5),
(91, 62, 4),
(91, 63, 4),
(92, 2, 1),
(92, 60, 5),
(92, 61, 5),
(92, 62, 5),
(92, 63, 5),
(93, 2, 5),
(93, 55, 5),
(93, 60, 5),
(93, 63, 3),
(94, 2, 5),
(94, 60, 3),
(94, 61, 5),
(94, 62, 3),
(94, 63, 2),
(95, 2, 4),
(95, 56, 3),
(95, 57, 1),
(95, 60, 3),
(95, 63, 3),
(96, 2, 5),
(96, 54, 3),
(96, 55, 4),
(96, 56, 4),
(96, 60, 5),
(96, 62, 1),
(96, 63, 2),
(97, 2, 5),
(97, 54, 3),
(97, 60, 5),
(97, 62, 4),
(97, 63, 2),
(98, 2, 1),
(99, 2, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `idpalavrachave`
--

CREATE TABLE IF NOT EXISTS `idpalavrachave` (
  `idpchave` int(11) NOT NULL AUTO_INCREMENT,
  `palavrachave` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`idpchave`),
  UNIQUE KEY `palavrachave` (`palavrachave`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=34 ;

--
-- Extraindo dados da tabela `idpalavrachave`
--

INSERT INTO `idpalavrachave` (`idpchave`, `palavrachave`) VALUES
(13, ' machine learning'),
(20, 'algoritmo'),
(1, 'biologia'),
(2, 'computação'),
(27, 'desenvolvimento'),
(26, 'engenharia'),
(21, 'informática'),
(19, 'inteligência artificial'),
(16, 'machine learning');

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
  `usuario` int(11) DEFAULT NULL,
  `area` int(11) NOT NULL,
  `autor` int(11) DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  `instituicao` int(11) NOT NULL,
  `cadastradoEm` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `titulo` varchar(255) NOT NULL,
  `data` date DEFAULT NULL,
  `resumo` text,
  `imagem` varchar(255) DEFAULT NULL,
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=100 ;

--
-- Extraindo dados da tabela `obra`
--

INSERT INTO `obra` (`id`, `usuario`, `area`, `autor`, `tipo`, `instituicao`, `cadastradoEm`, `titulo`, `data`, `resumo`, `imagem`) VALUES
(36, 2, 2, 18, 1, 2, '2016-07-05 16:58:03', 'educação a distância: a aprender online', '2016-07-01', 'problematiza-se a educação a distância sob o que alguns autores\nacreditam ser a sua inovação tecnológica mais recente caracterizada pela\nperspectiva online. para início da tarefa achamos por bem começar pela definição e\norigem do termo online. embora seu significado já esteja suficientemente\npopularizado ainda persistem algumas polêmicas em torno do assunto, as quais\nanalisaremos no âmbito de uma breve contextualização da ead no mundo. em\nseguida refletiremos sobre os tempos e espaços diferentes instituídos pela internet,\nsuas repercussões no ensino e a aprendizagem e como desenvolver modelos de\ncursos mais adequados a essa nova perspectiva na educação.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_16_48_1468356497985.pdf'),
(37, 2, 2, 19, 1, 2, '2016-07-05 16:59:53', 'Contribuições do jogo didático no ensino de embriologia', '2016-07-01', 'Os materiais didáticos são meios necessários para o processo ensinoaprendizagem\r\ne os jogos didáticos tornam-se uma alternativa lúdica para auxiliar neste\r\nprocesso. Com base nessa visão, neste trabalho apresenta-se a confecção, aplicação e\r\nanálise de um jogo didático com o objetivo de colaborar na compreensão e aprendizagem\r\ndo conteúdo de embriologia, mais especificamente o desenvolvimento embrionário humano.\r\no jogo foi elaborado utilizando-se materiais de baixo custo, tais como isopor, tinta guache e\r\nmassa de modelar caseira. Após a confecção do jogo, procedeu-se a sua aplicação em uma\r\nturma do 2º ano do ensino médio do instituto federal de educação, ciência e tecnologia do\r\namazonas – ifam. A partir da aplicação, os resultados evidenciaram que a maioria dos\r\nalunos aprendeu os conceitos básicos do desenvolvimento embrionário humano, pois\r\nevidenciaram que os materiais utilizados na fabricação do material oportunizaram a\r\nvisualização correspondente à fase embrionária estudada. Isso permite afirmar que a\r\nutilização de jogos didáticos pode contribuir para otimizar o processo ensino-aprendizagem\r\nem ciências, possibilitando uma participação intensa dos alunos nas atividades propostas.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_16_48_1468356518252.pdf'),
(39, 2, 2, 21, 1, 1, '2016-07-05 17:15:38', 'desenvolvimento de um jogo de memorização luminosa na plataforma arduino', '2012-12-17', 'este trabalho descreve o desenvolvimento de um jogo de memorização baseada no\r\ngenius simon, jogo de memorização famoso na dácada de 80. o jogo utiliza-se dos recursos\r\nda plataforma arduino para se tornar funcional, tem como objetivo a memorização da\r\nsequência luminosa gerada aleatoriamente toda vez que o jogo se inicia.o produto consiste\r\nna geração de uma sequência luminosa, que será repetida pelo seu usuário ate o numero de\r\nvezes correta, para seu fim.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_16_51_1468356681571.pdf'),
(40, 2, 2, 22, 1, 1, '2016-07-05 17:17:08', 'Desenvolvimento de um sistema de pré-seleção de disciplina com princípios de heurística de usabilidade', '2016-07-09', 'Devido diversidade de dispositivos e formas de interação, a necessidade de\r\ndesenvolver sistemas com base na usabilidade ergonomia e experiência do usuário\r\nse torna necessária, pois todos os dispositivos tem um modo de interagir diferente\r\ncomo celulares, palmtops, netbooks, cada um com uma forma de interação. Neste\r\ntrabalho desenvolvemos um sistema de inscrição de disciplinas para os alunos da\r\nest – uea com foco na usabilidade para interação em um dispositivo com tela\r\nsensível ao toque.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\13_6_2016_11_4_1468422248266.pdf'),
(41, 2, 2, 23, 2, 1, '2016-07-05 17:20:53', 'Estágios curriculares do bacharelato em eng. mecânica', '2016-07-11', 'Os planos curriculares da globalidade dos cursos de\r\nbacharelato ministrados na escola superior de\r\ntecnologia (est) contêm uma disciplina designada\r\npor estágio, de carácter obrigatório, a qual se\r\ndesenvolve, em geral, no período compreendido entre\r\no final do 2º semestre e o início do 1º semestre do\r\nano lectivo seguinte.\r\nO seu objectivo primordial é o de permitir a\r\nintegração dos alunos em situações de actividade\r\nprofissional concretas, num ambiente real e externo à\r\nescola, como complemento da sua formação\r\nacadémica.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\13_6_2016_11_3_1468422211139.pdf'),
(42, 2, 2, 24, 1, 1, '2016-07-05 17:24:04', 'analise de desempenho de algoritmos de programação distribuída em ooerlang com intel r mpi benchmarks e esqueletos algorítmicos em ooerlang', '2013-11-11', 'este artigo apresenta um estudo realizado com algoritmos escritos nas linguagens de\r\nprogramação erlang e ooerlang. primeiramente são apresentados os algoritmos de testes\r\nerlang seguindo a intel \r\nr mpi benchmarks com objetivo de medir o desempenho da\r\nlinguagem. e então, pequenos exemplos de programação distribuída com esqueletos de\r\nalgoritmos em ooerlang para estudos sobre esses conceitos e a extensão da aplicabilidade\r\ndesta extensão para erlang recentemente publicada.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_16_50_1468356634449.pdf'),
(44, 2, 2, 26, 1, 1, '2016-07-05 17:27:37', 'padrões de projeto em ooerlang', '2013-12-18', 'padrões de projetos são métodos de modelagem para vários tipos de problemas com\r\nfoco na programação orientada a objetos. estes padrões foram descobertos e documentados\r\npara serem corretamente utilizados quando for preciso, sendo aplicáveis a qualquer\r\nlinguagem orientada a objetos, baseados nos princípios de design para programação orientada\r\na objetos. ooerlang é uma extensão da linguagem ooerlang, com suporte à modelagem\r\norientada a objetos. com a utilização da extensão ooerlang, pode-se modelar problemas\r\nutilizando-se dos vários tipos de padrões de projetos existentes.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\13_6_2016_11_2_1468422120715.pdf'),
(45, 2, 2, 27, 1, 1, '2016-07-05 17:28:49', 'filtros de imagens para ios', '2013-11-26', 'este trabalho tem como finalidade apresentar o desenvolvimento de três filtros, com\r\no enfoque em processamento digital de imagens utilizando a plataforma ios. ios é um\r\nsistema operacional móvel da apple,inc. utilizado em iphone, ipads, ipods touch e apple\r\ntv. os algoritmos desenvolvidos neste projeto tem como objetivo manipular imagens e\r\nvídeos.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_16_49_1468356566698.pdf'),
(46, 2, 2, 28, 1, 1, '2016-07-05 17:31:59', 'sistema de segurança veicular com uso de gps baseado em arduino', '2013-12-01', 'este trabalho apresenta o desenvolvimento de um sistema voltado a segurança veicular\r\ncom uso de gps, o que permite o rastreamento, em tempo real, do automóvel por\r\nmeio de uma aplicação web. além da função de rastreamento, o sistema permite realizar\r\no bloqueio, ou seja, é possível desligar o automóvel remotamente e de modo irrevogável,\r\nimpedindo que este sofra danos causados pela condução agressiva de meliantes em tentativas\r\nde fuga ou mesmo na realiza¸c˜ao de novos crimes. a combinação da localização\r\ngeográfica com o desligamento remoto reduz as chances de danos ao patrimônio e evita\r\nque o proprietário tente reagir no momento de uma abordagem, pois poder´a contar com\r\nauxílio do sistema.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_16_38_1468355922117.pdf'),
(48, 2, 2, 30, 1, 1, '2016-07-05 17:40:51', 'análise comparativa de algoritmos que computam transformada discreta de fourier e hartley', '2013-11-26', 'este trabalho apresentará um estudo comparativo entre os algoritmos que computam as\r\ntransformada rápidas de fourier e hartley. tais algoritmos são essenciais em aplicações dos\r\nramos de processamento de sinais e imagens. conhecer o desempenho dessas transformadas\r\nrápidas é importante no desenvolvimento das aplicações que a utilizam. o ponto central\r\ndeste estudo é a comparação de eficiência em termos de tempo de processamento e mem´oria\r\nnecessária para computar ambas às transformadas. além disso, esta monografia apresenta\r\ngráficos ilustrando o desempenho de cada algoritmo implementado.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_16_36_1468355806204.pdf'),
(52, 2, 2, 40, 1, 1, '2016-07-05 17:53:06', 'Comparação do desempenho da programação concorrente entre java, erlang, scala, python, ruby e ooerlang utilizando intel mpi benchmark', '2016-07-18', 'Este trabalho de conclusão de curso apresenta um estudo do desempenho da programação\r\nconcorrente entre as linguagens java, erlang, scala, python e ruby, utilizadas para\r\no desenvolvimento de back-ends na web 2.0 e ooerlang, uma extensão orientada a objetos\r\npara erlang. Utiliza-se um conjunto de testes disponibilizados pela intel, conhecido\r\ncomo imb (intel mpi benchmark) para manter um caráter idôneo na execução deste trabalho,\r\nvisando avaliar o desempenho das linguagens. Existe uma descrição do imb e uma\r\ndescrição específica para cada benchmarck utilizado. estes benchmarcks foram projetados\r\npara avaliar o desempenho de máquinas; os mesmos foram reescritos para avaliar o\r\ndesempenho e o comportamento das linguagens.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\13_6_2016_11_2_1468422167875.pdf'),
(54, 2, 2, 42, 1, 1, '2016-07-06 14:04:30', 'implementação de suporte a subprogramas, módulos e pacotes da linguagem java para o compilador jaraki', '2012-11-23', 'este trabalho apresenta a implementação de suporte a subprogramas, módulos e pacotes\r\nque estão presentes na linguagem de programação java para o compilador jaraki, no qual é\r\num projeto que tem como entrada códigos-fonte java e compila para gerar um código-fonte\r\nsemelhante em erlang, por fim este é executado na máquina virtual do erlang (evm).', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_16_57_1468357027890.pdf'),
(55, 2, 2, 43, 1, 1, '2016-07-06 14:07:50', 'implementação das estruturas de controle da linguagem java para o compilador jaraki', '2012-11-23', 'este trabalho apresenta a implementação das estruturas de controle, presentes na linguagem\r\nde programação java para o compilador jaraki, um projeto que compila códigos-fonte\r\njava para a máquina virtual do erlang. utiliza as ferramentas leex e yecc para\r\ngerar as análises léxica e sintática, além de módulos em erlang e bibliotecas que facilitam\r\na geração de códigos das estruturas desenvolvidas.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_16_58_1468357102234.pdf'),
(56, 2, 2, 44, 1, 1, '2016-07-06 14:09:32', 'dominó adaptativo', '2012-12-10', 'este trabalho tem o objetivo de apresentar uma tecnologia inovadora para solucionar\r\nproblemas, tanto clássicos como atuais, utilizando técnicas adaptativas. esta ideia será\r\naplicada em um jogo de dominó, que inicialmente comporta-se como o jogo convencional,\r\nporém, no decorrer de uma partida, as regras do jogo podem ser modificadas por\r\nmeio de ações adaptativas impostas pelos jogadores. propondo uma nova direção para o\r\ndesenvolvimento de jogos.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_16_52_1468356757953.pdf'),
(57, 2, 2, 45, 1, 1, '2016-07-06 14:11:36', 'implementação do tratamento de variáveis e erros em erlang no compilador jaraki', '2012-11-23', 'esse trabalho mostra a implementação do módulo de manipulação de variáveis e tipos\r\nde dados e o tratamento de exceções para o compilador jaraki através do uso de dicionários.\r\no jaraki é um compilador que executa códigos-fonte da linguagem java na máquina virtual\r\ndo erlang.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_17_0_1468357207610.pdf'),
(58, 2, 2, 46, 1, 1, '2016-07-06 14:13:43', 'implementação do suporte a orientação a objetos no compilador jaraki', '2012-11-23', 'este trabalho apresenta a implementação do suporte a diversos recursos e funcionalidades\r\nrelacionados à orientação a objetos (oo) da linguagem java no compilador jaraki.\r\neste compilador recebe como entrada um código na linguagem de programação java e gera\r\num outro com mesmo comportamento na linguagem erlang. a grande diferença é que o\r\nprograma escrito pelo usuário em java poderá utilizar as mesmas vantagens da máquina\r\nvirtual do erlang.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_17_0_1468357256373.pdf'),
(59, 2, 2, 47, 1, 1, '2016-07-06 14:18:36', 'esquema integrado de proteção compartilhada de caminho e restauração aplicado à redes ópticas transparentes', '2012-11-27', 'este trabalho apresenta um estudo de integração entre duas estratégias que garantem\r\nresiliência a uma rede óptica. uma é conhecida como proteção compartilhada de caminho\r\ne a outra como restauração de caminho. esta proposta, chamada de proteçã híbrida,\r\ntem o objetivo de prover maior sobrevivência em redes ópticas na eventual ocorrência de\r\numa falha considerando as penalidades da camada física. as implementações e simulações\r\nsão realizadas no simulador de redes ópticas simton, cedido pelo grupo de fotônica da\r\nuniversidade federal de pernambuco desenvolvido em c++ e que simulam cenários em\r\nque ocorrem falha simples e falha dupla de enlaces da topologia de rede. são utilizados\r\nainda, para avaliação e análise dos resultados obtidos, o teste comparativo de wilcoxon e\r\no gráfico boxplot.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_16_53_1468356801258.pdf'),
(60, 2, 2, 48, 1, 1, '2016-07-06 14:20:55', 'desenvolvimento de um jogo multiusuário utilizando android', '2012-07-23', 'este trabalho apresenta o desenvolvimento de um jogo da velha multiusuário usando a\r\nplataforma android. o jogo, nomeado de ox game, permite que dois usuários joguem,\r\num contra o outro, partidas utilizando as regras do jogo da velha tradicional. a plataforma\r\nandroid é uma plataforma aberta voltada para dispositivos móveis e que possui um kit de\r\ndesenvolvimento próprio, utilizando a linguagem de programação java. o jogo ox game\r\nimplementa a arquitetura cliente-servidor e a comunicação existente entre os jogadores é\r\nrealizada a partir de fluxos de entrada e saída de dados conhecidos como sockets.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_17_2_1468357335379.pdf'),
(61, 2, 2, 49, 1, 1, '2016-07-06 14:23:32', 'proteção compartilhada por enlace para redes ópticas transparentes', '2012-06-23', 'a interrupção dos serviçoos fornecidos pelas redes de alta capacidade ou perda de dados\r\npode gerar impactos tanto produtivos quanto econômicos. mantê-las funcionando e\r\ngarantir a integridade dos dados transportados por meio delas é importante, portanto é necessário\r\nprovê-las de mecanismos de sobrevivência, que garantem a recuperação dos dados\r\nque trafegam pela rede, em caso de falhas, diminuindo a probabilidade de perda de dados.\r\nnesta pesquisa é proposto um algoritmo que implementa a técnica de proteção compartilhada\r\npor enlace para redes ópticas transparentes, utilizando métricas que medem a taxa\r\nde proteção, vulnerabilidade e probabilidade de bloqueio para analisar o desempenho do\r\nalgoritmo proposto.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_17_3_1468357396483.pdf'),
(62, 2, 2, 50, 1, 1, '2016-07-06 14:27:29', 'otimização de desempenho de sistema gis de monitoramento de sensores microprocessados com a google maps api', '2011-12-22', 'o objetivo deste trabalho é tratar problemas característicos de desempenho através de\r\notimização e quantificar as alterações destas características pós-otimização. uma abordagem\r\nestruturada e sistemática é utilizada para caracterizar elementos de desempenho,\r\nidentificar problemas potenciais através de testes, definir e implementar estratégias de\r\notimização e realizar uma análise comparativa pós-otimização em sistema computacional\r\nmashup chamado de sgt. sistema este que utiliza a google maps api para atribuir\r\ncaracterísticas de sistemas de informação geográfica (gis) ao monitoramento remoto de\r\nsensores microprocessados, cuja finalidade é aferir parâmetros elétricos de transformadores\r\nde energia em uma rede de distribuição.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_17_3_1468357438183.pdf'),
(63, 2, 2, 51, 1, 1, '2016-07-06 14:29:23', 'jogador de truco inteligente', '2011-12-16', 'este trabalho descreve o desenvolvimento de um agente inteligente capaz de jogar\r\ntruco, um jogo de cartas que apresenta características interessantes para serem abordadas\r\nem um trabalho de inteligência artificial, tais como domínio não determinístico, informações\r\nimperfeitas e informações incompletas. para o processo de tomada de decisão do\r\nagente foi empregada lógica fuzzy por meio da modelagem de controladores no matlab\r\npara cada situação onde fosse necessário tomar decisões. com o intuito de integrar a modelagem\r\ndo matlab com o jogo de truco em java, utilizou-se a ferramenta builder ja\r\npara exportar as funções do matlab para java de modo a utilizar seu poder computacional\r\ndurante a partida. os experimentos com jogadores humanos demonstraram que o nível de\r\njogo do agente inteligente é superior ao de um jogador que se considera iniciante no jogo\r\nde truco.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_17_4_1468357491632.pdf'),
(64, 2, 2, 52, 1, 1, '2016-07-06 14:31:16', 'implementação do protocolo tftp em erlang', '2011-12-16', 'o tftp (trivial file transfer protocol) é um protocolo de transferência de arquivos que\r\nfoi projetado para ser fácil de manusear. ele funciona sobre o udp e por isso implementa\r\ntodo o processamento de detecção e recuperação de perdas de informação ao nível da\r\naplicação. este artigo propõe a implementação do protocolo através do servidor e cliente\r\ntftp.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_17_5_1468357534983.pdf'),
(65, 2, 2, 53, 1, 1, '2016-07-06 14:32:48', 'carro robô em arduino com comunicação sem fio', '2011-12-16', 'esta monografia descreve o desenvolvimento de um carro robô utilizando o projeto\r\nde placa open source arduino. apresentando uma alternativa para o desenvolvimento de\r\nsistemas embarcados que não necessitem de muito conhecimento elétrico.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_17_6_1468357567049.pdf'),
(66, 2, 2, 54, 1, 1, '2016-07-06 14:34:15', 'desenvolvimento de um jogo de dominó para android', '2011-12-16', 'esta monografia descreve o desenvolvimento de um jogo de dominó para android,\r\num jogo popular no amazonas onde é jogado com regras específicas que o tornam mais\r\ninteressante.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_17_8_1468357696307.pdf'),
(67, 2, 2, 55, 1, 1, '2016-07-06 14:50:44', 'desenvolvimento de uma aplicação voip baseada no protocolo sip', '2011-12-15', 'este trabalho descreve o desenvolvimento de uma aplicação baseada no protocolo sip\r\npara comunicação voip. o sip tem como principal função realizar a sinalização entre as\r\npartes envolvidas antes de iniciar qualquer transação de dados de comunicação, ou seja, ele\r\nestabelece, configura ou encerra uma chamada. a aplicação realiza chamadas diretas entre\r\ndois clientes sip, baseando-se pelos seus respectivos endereços ip. ao final foram realizados\r\ntestes para medir a qualidade das chamadas a partir da experiência do usuário.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_17_10_1468357839637.pdf'),
(68, 2, 2, 56, 1, 1, '2016-07-06 14:54:53', 'comparação de desempenho da programação concorrente entre java e erlang utilizando intel mpi benchmarck', '2011-12-16', 'este trabalho apresenta um estudo sobre o desempenho da programação concorrente\r\nentre as linguagens java e erlang. ambas as linguagens são largamente utilizadas para\r\naplicações diversas e que em muitos dos casos trata frequentemente de situações de paralelismo\r\nmassivo, como as aplicações web que recebem diversas requisições de serviços em curto espaço de tempo. utilizou-se um conjuntos de testes disponibilizados pela intel. conhecido como imb – intel mpi benchmarck para manter um caráter idôneo na execução deste trabalho, visando avaliar o desempenho das linguagens. existe uma descrição do imb e uma descrição específica para cada benchmarck utilizado. estes benchmarcks foram projetados para avaliar o desempenho de máquinas, então os mesmos foram reescritos para avaliar o desempenho e o comportamento das linguagens.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_17_8_1468357722838.pdf'),
(69, 2, 2, 57, 1, 1, '2016-07-06 14:56:09', 'sistema web para aluguel de imóveis na cidade de manaus', '2010-12-07', 'o presente trabalho descreve a modelagem e a implementação de um\r\nsistema web voltado pra aluguel de imóveis na cidade de manaus. o sistema será\r\ndesenvolvido utilizando a linguagem php e a base de dados mysql. suas\r\ncaracterísticas serão semelhantes a um classificado tradicional, porém com todos\r\nos recursos que a internet pode proporcionar dando ao usuário mais opções de\r\nescolha como: imagens, localização, infra-estrutura do local, distância de pontos\r\nespecíficos. o sistema permitirá que o usuário faça o orçamento de anúncio e o\r\ncálculo do valor é feito no ato de sua inscrição. além da praticidade, um dos\r\nobjetivos principais é atender aos usuários que não conhecem a cidade,\r\noferecendo conforto, economia e maior segurança na hora de alugar um imóvel.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_17_11_1468357860262.pdf'),
(70, 2, 2, 58, 1, 1, '2016-07-06 14:57:53', 'integrando os frameworks cakephp e flex para aplicaçõoes web', '2010-12-07', 'o propósito deste trabalho é demonstrar através de um estudo de caso (uma aplicação\r\nreal) o uso e a integração de duas poderosas tecnologias altamente conhecidas pela maioria\r\ndos programadores orientados para aplicações web, cakephp e flex. essas tecnologias são\r\nconhecidas como frameworks. sendo que o primeiro, cakephp, aplica o padrão arquitetural\r\nmvc (model, view e controller) e o segundo, flex, aplica a tecnologia ria (rich internet\r\napplication). o estudo de caso implementado neste trabalho está baseado em um problema\r\nreal e conhecido pela est (escola superior de tecnologia).', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_17_11_1468357888854.pdf'),
(71, 2, 2, 59, 1, 1, '2016-07-12 20:16:47', 'Sistema web para gestão de um condomínio', '2016-07-07', 'Este trabalho apresenta o desenvolvimento de um sistema web para gestão de um \r\ncondomínio. o sistema foi desenvolvido na linguagem de programação php e com base \r\nde dados em mysql. o sistema e uma ferramenta de administração centralizadora, que \r\npermiti ao sindico e ao condômino um gerenciamento  online total, de qualquer local que \r\nse encontrar, basta acessar por senha, toda a estrutura que esta a sua disposição. Com este \r\nsistema  o  síndico  controla  os  condôminos  que  estão  inadimplentes  e  faz  uma \r\ncomunicação  maior  com  os  mesmos. Também  neste  sistema  pode  o  condômino \r\nacompanhar os eventos que acontecem no condomínio, fazer reservas das áreas de lazer e \r\ndo salão de festas, bem como fazer a retirada do boleto da taxa condominial.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_16_16_1468354607525.pdf'),
(72, 2, 2, 41, 1, 1, '2016-07-12 20:22:05', 'removendo marcadores de texto em documentos monocrom  aticos envelhecidos', '2012-12-10', 'um texto em destaque e freq uentemente usado para enfatizar partes de um documento\r\npor alguma determinada razão. a cor e o tipo da caneta de texto (selecionador de texto) são\r\nde escolha pessoal do leitor e podem ser vistos ou analisado como apenas um "danicador"\r\ndo documento original. dependendo da cor utilizada no texto, esta pode afetar de maneiras\r\ndiferentes documentos monocromaticos com o fundo de papel branco e envelhecido. este\r\nprojeto tem por objetivo apresentar um algoritmo baseado no comparativo da analise\r\nestatstica dos histogramas entre a parte afetada e não afetada pelo selecionador de texto\r\nda imagem original, usando os metodos do calculo da media e do desvio padrão, com o\r\nintuito de remover o efeito do selecionador de texto em documentos monocromaticos que\r\nsofreram efeito de envelhecimento devido a situações naturais.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_16_22_1468354925309.pdf'),
(73, 2, 2, 25, 1, 1, '2016-07-12 20:24:47', 'websocket para aplicações web em erlang', '2011-12-16', 'este trabalho descreve a implementação em erlang do internet-draft websocket hixie 76 do html5. a descrição do protocolo, o processo de modelagem, definição da application programming interface e os resultados obtidos com os experimentos que demonstramo uso da api. a implementação foi estruturada em modulos que permitisse a maxima reutilizaçã de codigo por outros desenvolvedores. o sistema foi estruturado em processo com interfaces de passagem de mensagens bem denidas que podem ser extendidas para uso em outros protocolos semelhantes ao websocket.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_16_24_1468355087624.pdf'),
(74, 2, 2, 63, 1, 1, '2016-07-12 20:28:15', 'rede social geo educacional -geoeducar', '2013-12-09', 'o propósito deste trabalho e demostrar a utilização de conceitos moveis em softwares educacionais através do desenvolvimento de um aplicativo móvel que viabilize uma rede social utilizando georreferenciamento na área da educação para um público carente de aplicativos específicos que seriam os estudantes.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_16_28_1468355295013.pdf'),
(75, 2, 2, 29, 1, 1, '2016-07-12 20:47:20', 'uso do método de inspeção semi-ótica na prototipação de sistemas', '2012-12-04', 'este trabalho apresenta o desenvolvimento de um artefato de software que utiliza o modelo de processo de desenvolvimento por prototipação onde ha a adaptação deste para possibilitar a inclusão do estudo da interface através do uso do metodo de inspeção semiotica (mis), presente na engenharia semiótica, que visa mostrar o quanto a comunicação do designer para com o usuário e eciente e eficaz, medindo o nvel de comunicabilidade da interface. esta abordagem é necéssaria visto que a análise da comunicabilidade e realizada quando o artefato ja foi desenvolvido e esta sendo utilizado, como consequência as observações de falhas encontradas apenas documentadas e não aplicadas ao artefato por alguns fatores, como o econômico para a manutenção do software, por exemplo. além de que nas fases nais de prototipação a atenção e focada no quanto a interface permite ao usuário atingir seus objetivos, o que n~ao deveria ser a meta, pois nestas ha o incio da formação de um novo ciclo de trabalho, onde a prioridade s~ao as coletas ou modicações de requisitos.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\12_6_2016_16_47_1468356440047.pdf'),
(77, 2, 2, 66, 1, 1, '2016-07-13 20:55:19', 'Agentes evolutivos com personalidade para o jogo de dominó de 4 pontas', '2015-11-04', 'O jogo de dominó de 4 pontas ´ e uma versão jogada em manaus, que possui como mecânicas principais a contagem das numerações das peças e a ação de passar a vez. Entre as jogadas possíveis, existe no dominó amazonense uma ação conhecida como ”trancar o jogo”, onde jogadores por uma questão estratégica, ou até mesmo por falta de atenção ou habilidade, bloqueiam todas as jogadas futuras. Personagens não jogáveis (non-playable characters - npcs) podem usar movimentos inesperados como o ”trancamento de jogo” para gerar a percepção de personalidade, aumentando o envolvimento com o jogo. Nosso objetivo é evoluir heurísticas para agentes que seguem uma personalidade predefinida no jogo de dominó de 4 pontas. Foram feitas simulações em matlab para demonstrar que a nossa abordagem evolutiva pode gerar npcs com forte aderência às personalidades previamente definidas.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\13_6_2016_16_55_1468443319279.pdf'),
(78, 2, 2, 67, 2, 1, '2016-07-14 13:26:19', 'estudo comparativo entre algoritmos genéticos e pesquisa em vizinhança variável aplicados na otimização de redes ip', '2015-10-12', 'neste artigo, será apresentado um estudo comparativo entre os algoritmos gen éticos e a pesquisa em vizinhanc¸a variável aplicados na otimização de um ambiente controlado de redes ip. os algoritmos buscam atenderá várias requisições simultâneas, que possuem requisitos mínimos de largura de\r\nbanda, de modo que se simule o atendimento a índices de qualidade de serviço para aplicações multimídias. na proposta do algoritmo genético, utilizam-se operadores genéticos específicos com elitismo. na busca com a pesquisa em vizinhançaa variável, utiliza-se o vns básico aplicado ao cenário em estudo. nos resultados são discutidas as caracter´ ?sticas que favorecem ou prejudicam\r\na escolha de cada algoritmo a ser empregado nesse contexto.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\14_6_2016_9_26_1468502779227.pdf'),
(79, 2, 2, 65, 2, 1, '2016-07-14 18:20:20', 'Análise estatística de geradores pseudo-aleatórios baseados em autômatos celulares', '2015-11-04', 'Autômatos celulares são modelos de computação baseados em células que se auto-reproduzem. De acordo com a literatura, estes autômatos são estruturalmente simples, mas capazes de gerar padrões complexos, o que culmina na adoção dos mesmos para geração de números pseudo-aleatórios.Levando isto em consideração, este trabalho teve como objetivo analisar a qualidade estatística das sequências produzidas por geradores baseados nas versões elementares e totalísticas destes autômatos. Como resultado, foi possível constatar que este modelo de computação não se mostra adequado para geração de sequências pseudo-aleatórias, mostrando baixa qualidade estatística. Estes resultados impactam na não-indicação deste modelo para geração de sequências numéricas, pois podem comprometer as aplicações que os utilizam.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\14_6_2016_14_20_1468520420131.pdf'),
(80, 2, 2, 69, 2, 1, '2016-07-14 18:22:28', 'aplicação de um método multicritério para identificação e  avaliação de requisitos de software', '2015-11-04', 'devido  ao  constante  avanço  tecnológico,  a  identificação  de  requisitos,  que  satisfaçam  as  necessidades  dos  usuários,  se  tornou  ao  longo  dos  anos  um  dos  fatores fundamentais para o sucesso dos sistemas computacionais. é importante garantir que os requisitos  essenciais  para  o  desenvolvimento  de  sistemas  sejam  identificados corretamente visando diminuir o esforço no processo de elicitação. a elicitação ajuda a descobrir as necessidades dos usuários por meio de técnicas específicas.  a escolha de métodos  que  ajudam  na  elicitação  de  requisitos  pode  minimizar  os  erros  no desenvolvimento  de  sistemas.  este  artigo  apresenta  uma  proposta  para  selecionar  a técnica  mais  adequada  para  elicitação  de  requisitos  no  desenvolvimento  de  sistemas computacionais.  a  abordagem  proposta  é  baseada  no  uso  da  matriz  de  decisão utilizando o método electre i. para o uso do método foram especificados critérios, pesos e  alternativas,  de  modo  que  a  melhor  técnica  é  selecionada  a  partir  dos  cálculos  de concordância e discordância. os resultados obtidos podem contribuir para o processo \r\nde elicitação de requisitos necessário para o desenvolvimento de aplicações.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\14_6_2016_14_22_1468520548299.pdf'),
(81, 2, 2, 70, 2, 1, '2016-07-14 18:24:36', 'arquitetura para integração de sistemas independentes de  automação residencial através de webservices embarcados', '2015-11-04', 'a  automação  residencial  busca,  através  um  conjunto  dispositivos, possibilitar o gerenciamento remoto de recursos em uma residência tais como lâmpadas, janelas, sensores e outros. por meio de protocolos customizados, as diferentes soluções integram esses dispositivos para atuarem como um único sistema  de  automação  residencial  que,  embora  eficientes,  são  limitados quanto  sua  interação  com  sistemas/dispositivos  externos  à  solução.  neste cenário, o artigo apresenta uma arquitetura para casas inteligentes onde os dispositivos  são  tratados  como  sistemas  de  automação  independentes, conectados  à  uma  rede  local,  que  viabilizam  sua  operabilidade  através  de requisições http. a implementação desta arquitetura permitiu a integração dos  dispositivos  entre  si  e  com  outros  sistemas  independentes  como  os navegadores  web,  aplicativos  para  a  plataforma  android  os,  thingspeak (banco  de  dados  na  nuvem)  e  telegram  (mensageiro  baseado  em  nuvem). através  dos resultados, este estudo destaca a expansão dos limites quanto a integração  de  sistemas  de  automação  residencial  com  outras  ferramentas  e sistemas autônomos por meio de requisições http.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\14_6_2016_14_24_1468520676792.pdf'),
(82, 2, 2, 71, 2, 1, '2016-07-14 18:26:08', 'avaliação experimental de um modelo descritivo para  navegação em dispositivos móveis', '2015-11-04', 'a navegação é um aspecto que impacta diretamente na usabilidade. como  primeiro  passo  para  o  projeto  da  navegação  de  aplicativos  com qualidade,  padovani  et  al.  (2013)  propuseram  um  modelo  descritivo  para sistemas  de  dispositivos  móveis  que  permita  projetar  a  navegação  destes considerando seus aspectos específicos de interação. porém o modelo ainda não  havia  sido  analisado.  este  artigo  descreve  um  estudo  experimental  que avaliou este modelo junto a profissionais com experiência no desenvolvimento de  aplicativos.  como  resultado,  observou-se  que  a  nomenclatura  utilizada pelo  modelo  se  mostrou  confusa  e  uma  maior  exatidão  na  descrição  dos elementos do modelo é necessária.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\14_6_2016_14_26_1468520768316.pdf'),
(83, 2, 2, 72, 2, 1, '2016-07-14 18:27:45', 'avaliando a aplicação de modelos de interação de  engenharia de software e ihc no design de sistemas  interativos', '2015-11-04', 'a engenharia de software (es) e a interação humano-computador (ihc)  se  destacam  quanto  ao  foco  na  qualidade  do  desenvolvimento  de sistemas  interativos. enquanto es foca na qualidade do sistema, ihc foca na qualidade  da  interação  entre  os  usuários  e  o  sistema.  ambas  as  áreas propõem  diferentes  abordagens  para  o  design  de  sistemas  interativos, causando  incertezas  em  relação  a  melhor  abordagem  a  ser  adotada.   este artigo  relata  um  estudo  experimental  que  comparou dois modelos utilizados durante o design da interação: casos de uso e molic,  propostos pelas áreas de  es  e  ihc  respectivamente.  através  do  estudo,  foi  possível  analisar  a qualidade dos modelos e percepções dos designers em relação ao seu uso.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\14_6_2016_14_27_1468520865863.pdf'),
(84, 2, 2, 73, 2, 1, '2016-07-14 18:28:53', 'comparação de técnicas de aprendizado de máquina com  pré-processamento para decisão de profilaxia da tuberculose', '2015-11-04', 'a  tuberculose  é  um  problema  de  saúde  pública  mundial.  existem portadores de infecção latente, que podem evoluir futuramente com a doença. o  ministério  da  saúde  do  brasil  identificou  os  grupos  de  risco  de adoecimento  e  atribuiu  algumas  variáveis  que  auxiliam  a  equipe  médica  na decisão  de  tratar  esses  indivíduos  antes  que  adoeçam.  baseando-se  nesse contexto foi realizada a simulação de 305 casos clínicos, que posteriormente foram  submetidos  ao  processamento  de  cinco  algoritmos  de  aprendizado  de máquina  utilizando  a  ferramenta  weka,  obtendo  melhor  acurácia  o algoritmo j48 com a técnica de árvore de decisão.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\14_6_2016_14_28_1468520933638.pdf'),
(85, 2, 2, 74, 2, 1, '2016-07-14 18:30:38', 'desenvolvimento de um jogo educacional para smart  tv', '2015-11-04', 'presente nas casas e no dia a dia de milhares de brasileiros, os  aparelhos  de  televisão  evoluíram  consideravelmente  ao  longo  do  tempo com  a  integração  de  várias  tecnologias  que  tornaram  a  experiência  do usuário  muito  mais  interessante.  as  smart  tvs  permitem  que  o  usuário, através  do  sistema  operacional  da  televisão  e  do  controle  remoto,  tenham acesso à internet e seus conteúdo, além de ser possível baixar e instalar jogos e  aplicativos  disponíveis  na  loja  de  aplicativos  dos  fabricantes.  nesse contexto, este artigo busca explorar as smart tvs como ferramenta auxiliar no processo de ensino e aprendizagem através do desenvolvimento de um jogo educacional que estimule a prática da matemática nas escolas.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\14_6_2016_14_30_1468521038599.pdf'),
(86, 2, 2, 75, 2, 1, '2016-07-14 18:34:53', 'estudo comparativo entre algoritmos genéticos e pesquisa em vizinhança variável aplicados na otimização de redes ip', '2015-11-04', 'neste artigo, será apresentado um estudo comparativo entre os algoritmos genéticos e a pesquisa em vizinhança variável aplicados na otimização de um ambiente controlado de redes ip. os algoritmos buscam atender às várias requisições simultâneas, que possuem requisitos mínimos de largura de banda, de modo que se simule o atendimento a índices de qualidade de serviço para aplicações multimídias. na proposta do algoritmo genético, utilizam-se operadores genéticos específicos com elitismo. na busca com a pesquisa em vizinhança variável, utiliza-se o vns básico aplicado ao cenário em estudo. nos resultados são discutidas as características que favorecem ou prejudicam a escolha de cada algoritmo a ser empregado nesse contexto.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\14_6_2016_14_34_1468521293661.pdf'),
(87, 2, 2, 76, 2, 1, '2016-07-14 18:36:28', 'frameworkde tv digital interativa para o ensino de  trigonometria atravésde manipulação de objetos digitais de  aprendizagem', '2015-11-04', 'tendo em vista as dificuldades enfrentadas para construir ambientes de  aprendizagem, que  possibilite  o  reaproveitamento  e  apoio  de  material didático,associado a uma proposta pedagógica e um contexto específico, este trabalho  apresenta  um  framework  para  manipular  objetos  digitais  de aprendizagem, que fazem uso da tecnologia da tv digital interativa (tvdi). o  contexto  explorado  é  do  ensino  da  trigonometria  e  o  framework  foi desenvolvido sobre o middleware europeu mhp, apresentando resultados em ambiente  emulado  e  real  de  tv  digital,  mas  com  uma  arquitetura  para  ser adaptada  ao  middleware  brasileiro  ginga  com integração  à  aprendizagem ubíqua em ambientes virtuais.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\14_6_2016_14_36_1468521388806.pdf'),
(88, 2, 2, 77, 2, 1, '2016-07-14 18:38:31', 'medidas de mean opinion score baseada no modelo-e durante uma chamada voip', '2015-11-04', 'este artigo apresenta os resultados de medidas continuas mos durante uma chamada voip (voice over internet protocol). a qualidade de experiência (qoe) é periodicamente determinada por meio de uma versão modificada do modelo-e e registrada em um arquivo. ao final, os resultados obtidos são comparados por um software baseado em polqa (perceptual objective listening quality assess), e prova-se que as medições contínuas estão corretas e coerentes com a qualidade do sinal recebido, quando comparado ao sinal transmitido.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\14_6_2016_14_38_1468521511539.pdf'),
(89, 2, 2, 78, 2, 1, '2016-07-14 18:41:50', 'metric_cal.exe: una herramienta de apoyo a la enseñanza de  los temas de calidad en la ingeniería de software', '2015-11-04', 'la evaluación de la calidad es uno de los procesos en el ciclo de vida de desarrollo de software donde se deben planificar, organizar, dirigir y controlar una  serie  de  atividades;  con  el  objetivo  de  asegurar  que  el  producto  aporte  la calidad requerida y satisfaga las necesidades del cliente. en este trabajo se abordala  descripción  de  la  herramienta  metric_calc.exe  como  apoyo  al  proceso  de enseñanza  y  aprendizaje  de  los  temas  de  calidad  en  la  ingeniería  de  software, imprescindibles  en  el  proceso  de  formación  de  los  ingenieros  informáticos.se presenta  además,  la  valoración  de  los  resultados  con  la  aplicación  de  la herramienta donde todos los aspectos fueron evaluados de muy relevantes.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\14_6_2016_14_41_1468521710193.pdf'),
(90, 2, 2, 79, 2, 1, '2016-07-14 18:45:44', 'scrum game: um jogo de tabuleiro para auxiliar no aprendizado do framework scrum', '2015-11-04', 'no processo de ensino e aprendizagem de metodologias ágeis, a vivência prática deve estar inserida para que o aluno possa ter uma experiência melhor com a realidade. neste contexto, a utilização de jogos educacionais para contribuição do ensino torna-se uma boa oportunidade por oferecer ganho no aprendizado através da experimentação prática dos conceitos abordados. no entanto, há poucos jogos educativos que exploram os conceitos relacionados em metodologias ágeis e são poucos aqueles trabalham os conceitos de uma forma simples e intuitiva. desta forma, o objetivo deste trabalho é apresentar um jogo de tabuleiro para auxiliar no ensino e aprendizagem do framework scrum. assim, podendo auxiliar docentes com novas estratégias instrucionais em suas aulas. obtivemos resultados satisfatórios após os experimentos que realizamos, verificando a eficiência do jogo.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\14_6_2016_14_45_1468521944298.pdf'),
(91, 2, 2, 80, 2, 1, '2016-07-14 18:47:11', 'análise do framework oaeditor para o suporte à criação de  objetos de aprendizagem em plataforma de educação a  distância', '2015-11-04', 'este  trabalho  discute  o  desenvolvimento  de  ferramentas computacionais de apoio à criação de objetos de aprendizagem no contexto da educação a distância. a partir de revisão na literatura, verificou-se que a maioria  dos  recursos  encontrados  apresentam  problemas  do  ponto  de  vista pedagógico.  com  base  em  critérios  de  adaptabilidade,  foi  desenvolvido  o oaeditor,  um  framework  integrado  ao  ambiente  virtual  de  aprendizagem moodle da universidade federal da paraíba para dar suporte a professores no processo de construção de objetos de aprendizagem (oas) adaptativos.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\14_6_2016_14_47_1468522031113.pdf'),
(92, 2, 2, 81, 2, 1, '2016-07-14 18:51:17', 'uma abordagem baseada em redes neurais para a predicão de chuva em manaus, amazonas', '2015-11-04', 'o clima na cidade de manaus, amazonas é caracterizado por calor e umidade tipicamente equatoriais, responsáveis por um desconforto natural a seus habitantes. um dos principais agentes na regulação do clima local é a precipitação, sendo sua predição de apreciável importância, tratando-se porém, de um procedimento difícil, pois o clima da cidade é influenciado por diversos sistemas de precipitacão. este trabalho apresenta uma abordagem utilizando redes neurais artificiais de múltiplas camadas para prever a ocorrência de precipitações na cidade de manaus. foram utilizados dados climáticos de 1970 a 2014, provenientes de uma estação meteorológica automática. após testar um conjunto de 2500 redes neurais que endereçam este problema, foi possível identificar a rede com arquitetura 4   9   7   1 com melhor desempenho para este problema, capaz de prever precipitações com cerca de 70% de acerto.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\14_6_2016_14_51_1468522277160.pdf'),
(93, 2, 2, 82, 2, 1, '2016-07-14 18:55:42', 'uma abordagem meta-heurística para o problema do rebalanceamento estático em sistemas self-service de compartilhamento de bicicletas', '2015-11-04', 'o problema do rebalanceamento estático de bicicletas é motivado pela tarefa de reposicionar bicicletas entre estações em um sistema de compartilhamento de bicicletas. é uma generalização do problema do roteamento de veículos com coleta e entrega de um produto. demandas podem ser fracionadas em múltiplas visitas e estações podem servir como depósitos temporários. é um problema n p - difícil e métodos exatos na literatura não resolveram de modo consistente instâncias com mais de 60 estações. este trabalho apresenta uma abordagem heurística baseada no iterated local search combinado com o procedimento randomized variable neighborhood descent para a variante com um veículo. testes preliminares mostram resultados competitivos em relação a outras abordagens.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\14_6_2016_14_55_1468522542350.pdf');
INSERT INTO `obra` (`id`, `usuario`, `area`, `autor`, `tipo`, `instituicao`, `cadastradoEm`, `titulo`, `data`, `resumo`, `imagem`) VALUES
(94, 2, 2, 83, 2, 1, '2016-07-14 18:57:44', 'uso da prototipagem no processo de desenvolvimento de  um sistema de informação: um estudo de caso em uma ies', '2015-11-04', 'é de fundamental importância para toda e qualquer empresa utilizar ferramentas  tecnológicas  como  alternativa  para  aprimorar  a  eficiência  na realização de suas tarefas, bem como servir de apoio a tomada de decisões, a coordenação e o controle da empresa. neste contexto, este trabalho apresenta as  contribuições  do  uso  da  prototipagem  na  qualidade  de  processo  de desenvolvimento  de  software  aplicado  em  um  sistema  de  informação  para automação  da  disciplina  de  trabalho  de  conclusão  de  curso  em  uma instituição de ensino superior, como alternativa para minimizar os problemas no controle manual de documentos em papéis ou pela dependência de sistemas semiautomáticos, como excel, word, etc.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\14_6_2016_14_57_1468522664873.pdf'),
(95, 2, 2, 84, 2, 1, '2016-07-14 19:02:30', 'uso de un robot como herramienta para fortalecer la matemática en educación básica', '2015-11-04', 'este trabajo presenta los resultados iniciales de un proyecto que pretende utilizar la robótica educativa en la didáctica de la matemática. el proyecto consiste en utilizar una placa raspberry pi, un kit de robótica gopigo y el lenguaje de programación scratch, el propósito es hacer uso de estas tres herramientas como una propuesta para mejorar la práctica didáctica utilizando utilizando la tecnología. el raspberry pi puede proporcionar las posibilidades de usarlo para mejorar la práctica pedagógica de las matematicas empleando la robótica. los resultados iniciales presentados en este artículo involucran alumnos y profesores de educación básica de la especialidad de matemáticas. los participantes reconocen que el uso de la robótica educativa permite fácilmente crear propuestas tecnológicas propias. para que una práctica docente sea fortalecida es necesario que el profesor reconozca la importancia del uso de las nuevas tecnologias. esta propuesta debe ser capaz de despertar la motivación para el estudio de las matemáticas en los estudiantes.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\14_6_2016_15_2_1468522950459.pdf'),
(96, 2, 2, 85, 2, 1, '2016-07-14 19:03:54', 'utilização do aplicativo mobile foodclean desenvolvido para  auxiliar na aprendizagem de boas práticas de manipulação de  alimentos no curso técnico em cozinha', '2015-11-04', 'este  artigo  apresenta  o  aplicativo mobile foodclean, desenvolvido com base nas teorias de boas práticas de manipulação de alimentos do curso técnico em cozinha. é um jogo desenvolvido com o uso do motor de jogo unity 3d  utilizando  a  linguagem  javascript  de  programação.  o  aplicativo  tem por objetivo ajudar no desenvolvimento educacional do aluno do curso técnico em cozinha através de uma atividade sobre placas de cortes, um dos conteúdos ministrados  em  sala  de  aula.  esse  projeto  é  resultado  de  um  trabalho  de pesquisa  feito  dentro  da  disciplina  de  estágio  supervisionado  i  do  curso  de licenciatura em informática da universidade do estado do amazonas.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\14_6_2016_15_3_1468523034322.pdf'),
(97, 2, 2, 86, 2, 1, '2016-07-14 19:05:27', 'aplicação da metodologia ágil scrumpara elaboração  do projeto do aplicativo tôdebike! para incentivar a prática  de uso de bicicletas', '2015-11-04', 'diversas teorias de modelagem de software são experimentadas em contexto  acadêmico,  sejam  de  métodos  tradicionais  ou  dos  métodos  ágeis. este  artigo  apresenta  um  relato  de  experiência  de utilização  da metodologia ágil para modelagem e levantamento de requisitos, planejamento e desenvolvimento em uml do aplicativo  tô debike! que tem como objetivo ajudar  usuários  a  encontrarem  grupos  de  pessoas  para  pedaladas,  eventos relacionados  a  este  grupo,  melhor  percurso  para  um  ciclista,  além  de incentivar  as  pessoas  a  prática  de  uso  da  bicicleta.obteve-se  sucesso  na implantação do projeto e experiência com a aplicação da metodologia scrum.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\14_6_2016_15_5_1468523127522.pdf'),
(98, 2, 2, 87, 2, 1, '2016-07-14 20:26:33', 'carrinhos inteligentes - otimização de compras em  estabelecimentos comerciais via rfid.', '2015-11-04', 'atualmente os estabelecimentos comerciais apresentam um alto nível de automação no uso de sistemas computacionais para o fechamento da venda. porém, o modo que o cliente apresenta seus produtos aos operadores de caixa continuam o mesmo de antes desta automação comercial. este trabalho propõe construir  um registrador  das  compras  dos  clientes  de  forma  mais  rápida  e confiável. a metodologia utilizada é o uso de etiquetas rfid nos produtos e leitores instalados nos carrinhos. espera-se com a implantação deste sistema agilizar o processo de fechamento de venda por cliente, fazendo negócios.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\14_6_2016_16_26_1468527993740.pdf'),
(99, 2, 2, 88, 2, 1, '2016-07-14 20:27:52', 'combinação de técnicas e ferramentas computacionais  para ajudar no desenvolvimento daintegração lavourapecuária-floresta no amazonas', '2015-11-04', 'a  integração  lavoura-pecuária-floresta  tem  como  objetivo  a mudança no sistema de uso da terra, aliando o aumento da produtividade com a conservação de recursos naturais no processo de intensificação de uso das áreas  já  desmatadas.  no  entanto,  no  estado  do  amazonas, a logística é um entrave na difusão das tecnologias e conhecimentos,  dificultando o acesso a muitos pequenos agricultores e ocasionando planejamentos mal feitos, baixa produção e endividamentos. de forma a fortalecer as ações de transferência de  tecnologia  no  amazonas,  foi  proposto  e  está  em  desenvolvimento  um aplicativo para smartphones com google android, que faz uso de técnicas de aprendizado de máquina.', 'C:\\Users\\ENG COMPUTACAO\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\tenebris\\obras\\14_6_2016_16_27_1468528072293.pdf');

-- --------------------------------------------------------

--
-- Estrutura da tabela `obra_palavrachave`
--

CREATE TABLE IF NOT EXISTS `obra_palavrachave` (
  `obra` int(11) NOT NULL,
  `palavrachave` int(11) NOT NULL,
  PRIMARY KEY (`obra`,`palavrachave`),
  KEY `palavrachave` (`palavrachave`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `obra_palavrachave`
--

INSERT INTO `obra_palavrachave` (`obra`, `palavrachave`) VALUES
(37, 1),
(79, 1),
(41, 2),
(45, 2),
(56, 2),
(57, 2),
(59, 2),
(98, 2),
(42, 20),
(48, 20),
(52, 20),
(54, 20),
(55, 20),
(41, 26),
(40, 27),
(71, 27),
(77, 27);

-- --------------------------------------------------------

--
-- Estrutura da tabela `redebayesiana`
--

CREATE TABLE IF NOT EXISTS `redebayesiana` (
  `idObra` int(11) NOT NULL DEFAULT '0',
  `idUsuario` int(11) NOT NULL DEFAULT '0',
  `pchave` int(11) NOT NULL DEFAULT '0',
  `relevancia` int(11) DEFAULT NULL,
  `titulo` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`idObra`,`idUsuario`,`pchave`),
  KEY `pchave` (`pchave`),
  KEY `idUsuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `redebayesiana`
--

INSERT INTO `redebayesiana` (`idObra`, `idUsuario`, `pchave`, `relevancia`, `titulo`) VALUES
(36, 2, 21, 1, 'educação a distância: a aprender online'),
(36, 54, 21, 1, 'educação a distância: a aprender online'),
(40, 2, 19, 1, 'desenvolvimento de um sistema de pré-seleção de disciplina com princípios de heurística de usabilidade'),
(42, 2, 20, 1, 'analise de desempenho de algoritmos de programação distribuída em ooerlang com intel r mpi benchmarks e esqueletos algorítmicos em ooerlang'),
(45, 2, 2, 1, 'filtros de imagens para ios'),
(45, 54, 2, 0, 'filtros de imagens para ios'),
(45, 56, 2, 0, 'filtros de imagens para ios'),
(45, 57, 2, 1, 'filtros de imagens para ios'),
(45, 61, 2, 1, 'filtros de imagens para ios'),
(45, 62, 2, 1, 'filtros de imagens para ios'),
(45, 66, 2, 1, 'filtros de imagens para ios'),
(45, 77, 2, 0, 'filtros de imagens para ios'),
(48, 2, 20, 1, 'análise comparativa de algoritmos que computam transformada discreta de fourier e hartley'),
(48, 57, 20, 1, 'análise comparativa de algoritmos que computam transformada discreta de fourier e hartley'),
(48, 61, 20, 1, 'análise comparativa de algoritmos que computam transformada discreta de fourier e hartley'),
(48, 62, 20, 1, 'análise comparativa de algoritmos que computam transformada discreta de fourier e hartley'),
(48, 63, 20, 1, 'análise comparativa de algoritmos que computam transformada discreta de fourier e hartley'),
(48, 67, 20, 1, 'análise comparativa de algoritmos que computam transformada discreta de fourier e hartley'),
(48, 78, 20, 0, 'análise comparativa de algoritmos que computam transformada discreta de fourier e hartley'),
(54, 2, 20, 1, 'implementação de suporte a subprogramas, módulos e pacotes da linguagem java para o compilador jaraki'),
(55, 2, 20, 1, 'implementação das estruturas de controle da linguagem java para o compilador jaraki'),
(56, 2, 2, 1, 'dominó adaptativo'),
(56, 61, 2, 0, 'dominó adaptativo'),
(56, 62, 2, 1, 'dominó adaptativo'),
(56, 69, 2, 1, 'dominó adaptativo'),
(57, 2, 2, 1, 'implementação do tratamento de variáveis e erros em erlang no compilador jaraki'),
(57, 55, 2, 1, 'implementação do tratamento de variáveis e erros em erlang no compilador jaraki'),
(57, 61, 2, 0, 'implementação do tratamento de variáveis e erros em erlang no compilador jaraki'),
(59, 2, 2, 1, 'esquema integrado de proteção compartilhada de caminho e restauração aplicado à redes ópticas transparentes'),
(59, 55, 2, 1, 'esquema integrado de proteção compartilhada de caminho e restauração aplicado à redes ópticas transparentes'),
(59, 57, 2, 1, 'esquema integrado de proteção compartilhada de caminho e restauração aplicado à redes ópticas transparentes'),
(59, 63, 2, 1, 'esquema integrado de proteção compartilhada de caminho e restauração aplicado à redes ópticas transparentes'),
(59, 65, 2, 0, 'esquema integrado de proteção compartilhada de caminho e restauração aplicado à redes ópticas transparentes'),
(59, 74, 2, 1, 'esquema integrado de proteção compartilhada de caminho e restauração aplicado à redes ópticas transparentes'),
(78, 54, 1, 0, 'estudo comparativo entre algoritmos genéticos e pesquisa em vizinhança variável aplicados na otimização de redes ip'),
(78, 62, 1, 0, 'estudo comparativo entre algoritmos genéticos e pesquisa em vizinhança variável aplicados na otimização de redes ip'),
(78, 66, 1, 0, 'estudo comparativo entre algoritmos genéticos e pesquisa em vizinhança variável aplicados na otimização de redes ip'),
(78, 74, 1, 1, 'estudo comparativo entre algoritmos genéticos e pesquisa em vizinhança variável aplicados na otimização de redes ip'),
(80, 2, 20, 1, 'aplicação de um método multicritério para identificação e  avaliação de requisitos de software'),
(80, 65, 20, 1, 'aplicação de um método multicritério para identificação e  avaliação de requisitos de software'),
(92, 2, 2, 1, 'uma abordagem baseada em redes neurais para a predicão de chuva em manaus, amazonas'),
(92, 54, 2, 1, 'uma abordagem baseada em redes neurais para a predicão de chuva em manaus, amazonas'),
(92, 54, 13, 1, 'uma abordagem baseada em redes neurais para a predicão de chuva em manaus, amazonas'),
(92, 65, 2, 1, 'uma abordagem baseada em redes neurais para a predicão de chuva em manaus, amazonas'),
(92, 65, 13, 1, 'uma abordagem baseada em redes neurais para a predicão de chuva em manaus, amazonas');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=201 ;

--
-- Extraindo dados da tabela `tag`
--

INSERT INTO `tag` (`id`, `nome`) VALUES
(44, 'a.i'),
(149, 'algoritmo'),
(147, 'algoritmos genéticos'),
(174, 'análise'),
(90, 'android'),
(83, 'aplicativo'),
(105, 'aplicativos'),
(140, 'apple'),
(142, 'arduino'),
(79, 'arte'),
(164, 'autômatos'),
(190, 'azul'),
(111, 'banco de dados'),
(144, 'c'),
(113, 'c#'),
(117, 'c++'),
(51, 'computação'),
(186, 'computação quântica'),
(67, 'correção ortográfica'),
(175, 'dados'),
(78, 'desenvolvimento'),
(110, 'desenvolvimento web'),
(122, 'educação'),
(177, 'esa'),
(71, 'estrutura de dados'),
(195, 'estudar'),
(150, 'estudo comparativo entre algoritmos'),
(53, 'fibra óptica'),
(48, 'filtragem'),
(138, 'fourier'),
(77, 'games'),
(197, 'ganhar dinheiros'),
(148, 'genetic algorithm'),
(159, 'genética'),
(151, 'genético'),
(152, 'genéticos'),
(169, 'himalaia'),
(106, 'html'),
(121, 'imagem'),
(50, 'informática'),
(49, 'inteligência artificial'),
(64, 'interação com usuário'),
(141, 'ios'),
(4, 'java'),
(115, 'javascript'),
(3, 'jdk'),
(58, 'jogo'),
(56, 'jogos'),
(170, 'jogos olímpicos'),
(191, 'lapiseira'),
(69, 'linguagem de programação'),
(192, 'mac'),
(163, 'manaus'),
(124, 'marcador'),
(60, 'matrizes'),
(92, 'meninos'),
(181, 'merge'),
(184, 'merge sort'),
(133, 'mobile'),
(155, 'neurais'),
(187, 'olimpíadas'),
(172, 'olímpicos'),
(89, 'ooerlang'),
(55, 'padrões de projeto'),
(2, 'paper'),
(109, 'php'),
(168, 'pi'),
(98, 'poo'),
(139, 'processamento'),
(68, 'processamento de imagem'),
(1, 'program'),
(131, 'protocolo'),
(59, 'python'),
(65, 'pyton'),
(165, 'quântica'),
(182, 'quick'),
(183, 'quick sort'),
(167, 'raspberry pi'),
(46, 'reconhecimento'),
(87, 'rede social'),
(127, 'redes'),
(146, 'redes neurais'),
(62, 'relacionamentos'),
(95, 'roberto lucena'),
(76, 'ruby'),
(129, 'salomao viad'),
(54, 'samsung'),
(178, 'saúde'),
(84, 'series'),
(63, 'teologia'),
(173, 'teste'),
(91, 'tijolo'),
(137, 'transformada de fourier'),
(74, 'tratamento de palavras'),
(179, 'turismo'),
(47, 'tv'),
(80, 'unity'),
(189, 'wall-e'),
(112, 'web'),
(73, 'web2py'),
(114, 'xamarin');

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
  `recommendation` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  KEY `area` (`area`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=80 ;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `login`, `nome`, `senha`, `area`, `cadastradoEm`, `recommendation`) VALUES
(2, 'thiago', 'thiagô', '202cb962ac59075b964b07152d234b70', 2, '2016-03-31 00:29:27', 2),
(54, 'Gi', NULL, '202cb962ac59075b964b07152d234b70', NULL, '2016-07-12 14:47:04', 1),
(55, 'daniel', NULL, '202cb962ac59075b964b07152d234b70', NULL, '2016-07-12 15:03:46', 1),
(56, 'arquelau', NULL, '202cb962ac59075b964b07152d234b70', NULL, '2016-07-12 15:23:01', 1),
(57, 'Eduardo', NULL, '202cb962ac59075b964b07152d234b70', NULL, '2016-07-12 15:58:45', 1),
(58, 'salomao', NULL, '2d1ef8f39d2c1590daf9a3737c8a931d', NULL, '2016-07-12 17:47:46', 1),
(60, 'Kazuo', NULL, '202cb962ac59075b964b07152d234b70', NULL, '2016-07-12 19:01:30', 1),
(61, 'netoolii', NULL, '2c958b8b032651742e860d52594c412c', 23, '2016-07-12 19:21:17', 1),
(62, 'luscas', NULL, '81dc9bdb52d04dc20036dbd8313ed055', NULL, '2016-07-12 19:50:30', 1),
(63, 'thaldral', NULL, '202cb962ac59075b964b07152d234b70', 23, '2016-07-13 14:28:20', 1),
(65, 'Leuow', NULL, '202cb962ac59075b964b07152d234b70', NULL, '2016-07-13 19:29:48', 1),
(66, 'Matheus Miranda Matos', NULL, 'a178e22bf614dd6f301af05dd977833d', 23, '2016-07-13 19:42:43', 1),
(67, 'rafaeladss', NULL, '888f57b0e788f7b2de3aa28c17078c4a', 23, '2016-07-13 20:00:44', 1),
(68, 'usuarioteste', NULL, '202cb962ac59075b964b07152d234b70', NULL, '2016-07-14 19:27:23', 1),
(69, 'JandersonLiraDM', NULL, '6909f816cf4298960ff414e9529a16f0', NULL, '2016-07-15 17:39:36', 1),
(70, 'novoUsuario1', NULL, 'c4ca4238a0b923820dcc509a6f75849b', NULL, '2016-07-18 18:22:01', 1),
(71, 'usuario2', NULL, 'c4ca4238a0b923820dcc509a6f75849b', NULL, '2016-07-18 18:22:55', 1),
(72, 'usuarioteste3', NULL, 'c4ca4238a0b923820dcc509a6f75849b', NULL, '2016-07-18 18:23:53', 1),
(73, 'usuÃ¡rioTeste4', NULL, 'c4ca4238a0b923820dcc509a6f75849b', NULL, '2016-07-18 18:24:21', 1),
(74, 'Fabio', NULL, '202cb962ac59075b964b07152d234b70', NULL, '2016-07-18 19:57:53', 1),
(76, 'testando', NULL, '202cb962ac59075b964b07152d234b70', NULL, '2016-07-21 03:35:36', 1),
(77, 'novoUsuario', NULL, '202cb962ac59075b964b07152d234b70', 28, '2016-08-26 03:11:31', 1),
(78, 'LetSteps', NULL, '202cb962ac59075b964b07152d234b70', 29, '2016-08-26 12:34:45', 1),
(79, 'usuer', NULL, '202cb962ac59075b964b07152d234b70', NULL, '2016-08-26 19:32:20', 1);

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
(54, 1),
(55, 1),
(56, 1),
(57, 1),
(58, 1),
(60, 1),
(61, 1),
(62, 1),
(63, 1),
(65, 1),
(66, 1),
(67, 1),
(68, 1),
(69, 1),
(71, 1),
(73, 1),
(74, 1),
(76, 1),
(77, 1),
(78, 1),
(79, 1),
(70, 2),
(77, 2),
(78, 2),
(2, 3),
(72, 3);

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
(54, 4),
(60, 4),
(62, 4),
(63, 4),
(67, 4),
(68, 4),
(74, 4),
(78, 4),
(2, 49),
(2, 50),
(2, 51),
(63, 51),
(55, 55),
(55, 56),
(56, 56),
(70, 56),
(71, 56),
(74, 56),
(60, 58),
(62, 58),
(72, 58),
(54, 59),
(62, 59),
(63, 59),
(66, 59),
(67, 59),
(68, 59),
(69, 59),
(78, 59),
(66, 68),
(67, 73),
(68, 78),
(69, 90),
(56, 105),
(56, 106),
(61, 109),
(61, 110),
(61, 111),
(65, 111),
(67, 111),
(61, 112),
(74, 112),
(61, 113),
(62, 113),
(65, 113),
(61, 114),
(61, 115),
(62, 117),
(63, 117),
(67, 117),
(63, 121),
(63, 122),
(63, 124),
(54, 127),
(65, 127),
(68, 127),
(65, 131),
(65, 133),
(66, 137),
(66, 138),
(66, 139),
(66, 140),
(66, 141),
(66, 142),
(67, 144),
(54, 146),
(67, 146),
(63, 147),
(63, 148),
(2, 149),
(63, 149),
(68, 149),
(63, 150),
(63, 151),
(63, 152),
(54, 155),
(68, 155),
(68, 159),
(68, 163),
(54, 164),
(63, 165),
(69, 167),
(69, 168),
(70, 169),
(76, 169),
(70, 170),
(70, 172),
(71, 173),
(71, 174),
(71, 175),
(72, 177),
(72, 178),
(72, 179),
(73, 181),
(73, 182),
(73, 183),
(73, 184),
(74, 186),
(76, 187),
(78, 187),
(76, 189),
(77, 190),
(77, 191),
(77, 192),
(78, 195),
(78, 197);

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
-- Limitadores para a tabela `obra_palavrachave`
--
ALTER TABLE `obra_palavrachave`
  ADD CONSTRAINT `obra_palavrachave_ibfk_1` FOREIGN KEY (`obra`) REFERENCES `obra` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `obra_palavrachave_ibfk_2` FOREIGN KEY (`palavrachave`) REFERENCES `idpalavrachave` (`idpchave`) ON UPDATE CASCADE;

--
-- Limitadores para a tabela `redebayesiana`
--
ALTER TABLE `redebayesiana`
  ADD CONSTRAINT `redebayesiana_ibfk_1` FOREIGN KEY (`idObra`) REFERENCES `obra` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `redebayesiana_ibfk_2` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `redebayesiana_ibfk_3` FOREIGN KEY (`pchave`) REFERENCES `idpalavrachave` (`idpchave`) ON DELETE CASCADE ON UPDATE CASCADE;

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
