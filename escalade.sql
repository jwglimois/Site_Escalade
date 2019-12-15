-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 15 déc. 2019 à 01:23
-- Version du serveur :  5.7.21
-- Version de PHP :  7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `escalade`
--

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
CREATE TABLE IF NOT EXISTS `commentaire` (
  `id_commentaire` int(10) NOT NULL AUTO_INCREMENT,
  `date_creation` date NOT NULL,
  `message` text COLLATE utf8_unicode_ci NOT NULL,
  `id_site` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_commentaire`),
  KEY `FKkgndecm5i0gjb9q0vr7c7qpir` (`id_site`),
  KEY `FKcs6u1s906e16k4ewworhyqbtk` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `commentaire`
--

INSERT INTO `commentaire` (`id_commentaire`, `date_creation`, `message`, `id_site`, `id_user`) VALUES
(2, '2019-11-26', 'Je recommande fortement ce site. Il est super!!', 2, 18);

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(22),
(22);

-- --------------------------------------------------------

--
-- Structure de la table `longueur`
--

DROP TABLE IF EXISTS `longueur`;
CREATE TABLE IF NOT EXISTS `longueur` (
  `id_longueur` int(11) NOT NULL AUTO_INCREMENT,
  `hauteur` int(11) DEFAULT NULL,
  `id_voie` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_longueur`),
  KEY `FKsntp125c2624k8psbmjo0daru` (`id_voie`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `longueur`
--

INSERT INTO `longueur` (`id_longueur`, `hauteur`, `id_voie`) VALUES
(14, 30, 5),
(15, 30, 5),
(16, 30, 5),
(17, 20, 6),
(18, 20, 6),
(19, 20, 6),
(20, 20, 6),
(21, 10, 7),
(57, 20, 14),
(58, 40, 14),
(59, 30, 15),
(60, 50, 15);

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `id_reservation` int(11) NOT NULL AUTO_INCREMENT,
  `id_topo` int(10) NOT NULL,
  `id_emprunteur` int(10) NOT NULL,
  PRIMARY KEY (`id_reservation`),
  KEY `fk_topo` (`id_topo`),
  KEY `fk_emprunteur` (`id_emprunteur`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id_reservation`, `id_topo`, `id_emprunteur`) VALUES
(1, 1, 3),
(2, 2, 3),
(3, 3, 1),
(4, 1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id_role` int(10) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id_role`, `type`) VALUES
(1, 'utilisateur'),
(2, 'membre');

-- --------------------------------------------------------

--
-- Structure de la table `secteur`
--

DROP TABLE IF EXISTS `secteur`;
CREATE TABLE IF NOT EXISTS `secteur` (
  `id_secteur` int(11) NOT NULL AUTO_INCREMENT,
  `nom_secteur` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `id_site` int(10) NOT NULL,
  PRIMARY KEY (`id_secteur`),
  KEY `FK7ayk0wx98i7qd6d1xstgrexdh` (`id_site`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `secteur`
--

INSERT INTO `secteur` (`id_secteur`, `nom_secteur`, `id_site`) VALUES
(3, 'Grand Nord', 2),
(9, 'Valle d\'Or', 2),
(18, 'Babylone', 46),
(19, 'Rouquette', 46);

-- --------------------------------------------------------

--
-- Structure de la table `site`
--

DROP TABLE IF EXISTS `site`;
CREATE TABLE IF NOT EXISTS `site` (
  `id_site` int(5) NOT NULL AUTO_INCREMENT,
  `nom_site` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `region` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_site`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `site`
--

INSERT INTO `site` (`id_site`, `nom_site`, `region`) VALUES
(2, 'Arudy', '64_Pyrénées-Atlantiques'),
(46, 'Russan', '30_Gard');

-- --------------------------------------------------------

--
-- Structure de la table `spring_session`
--

DROP TABLE IF EXISTS `spring_session`;
CREATE TABLE IF NOT EXISTS `spring_session` (
  `PRIMARY_ID` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `SESSION_ID` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

--
-- Déchargement des données de la table `spring_session`
--

INSERT INTO `spring_session` (`PRIMARY_ID`, `SESSION_ID`, `CREATION_TIME`, `LAST_ACCESS_TIME`, `MAX_INACTIVE_INTERVAL`, `EXPIRY_TIME`, `PRINCIPAL_NAME`) VALUES
('ad66ee5c-14f7-4864-bd00-5c8ce2ed0228', '5f16b28d-056e-463b-8a21-17bb36586526', 1576366819213, 1576372825971, 900, 1576373725971, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `spring_session_attributes`
--

DROP TABLE IF EXISTS `spring_session_attributes`;
CREATE TABLE IF NOT EXISTS `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

--
-- Déchargement des données de la table `spring_session_attributes`
--

INSERT INTO `spring_session_attributes` (`SESSION_PRIMARY_ID`, `ATTRIBUTE_NAME`, `ATTRIBUTE_BYTES`) VALUES
('ad66ee5c-14f7-4864-bd00-5c8ce2ed0228', 'infoSession', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000277040000000274000132740006566963746f7278),
('ad66ee5c-14f7-4864-bd00-5c8ce2ed0228', 'MY_SESSION_INFO', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000277040000000274000132740006566963746f7278);

-- --------------------------------------------------------

--
-- Structure de la table `topo`
--

DROP TABLE IF EXISTS `topo`;
CREATE TABLE IF NOT EXISTS `topo` (
  `id_topo` int(10) NOT NULL AUTO_INCREMENT,
  `nom_topo` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `description` text COLLATE utf8_unicode_ci NOT NULL,
  `date_parution` date NOT NULL,
  `statut` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `id_site` int(10) NOT NULL,
  `id_proprietaire` int(10) NOT NULL,
  PRIMARY KEY (`id_topo`),
  KEY `fk_topo_site` (`id_site`),
  KEY `fk_proprietaire` (`id_proprietaire`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `topo`
--

INSERT INTO `topo` (`id_topo`, `nom_topo`, `description`, `date_parution`, `statut`, `id_site`, `id_proprietaire`) VALUES
(1, 'Verghellu', 'C\'est la falaise parfaite pour une journée d\'escalade en famille. A deux pas d\'une petite route où les voitures sont rares et à l\'ombre des larici, vous testerez vos chaussons et votre technique sur les belles dalles du secteur central. Plus haut à droite, quelques jolies longueurs vous réapprendront à tirer sur les bras.', '2018-01-12', 'attente', 1, 1),
(2, 'Vallée de l\'Arve', 'Retrouvez dans ce topo-guide toutes les voies moderne de la vallée de l\'Arve (à l\'exception de celle de Chamonix). Plus de 1260 itinéraires de grandes voies sont à explorer dan ce topo tout en couleur avec pour la plupart des grandes voies, un tracé photo. Les dessins des autres sites, faits à la main, sont précis et esthétiques. Pour chaque site l\'accès est décrit par un texte et un schéma précis. Une sélection de sa voie en fonction du niveau, de la longueur ou des conditions peut-être effectuée par le grimpeur grâce au tableau récapitulatif présenté au début de l\'ouvrage.', '2017-10-02', 'prete', 1, 2),
(3, 'Fixin', 'L\'escalade à Fixin, au sud de Dijon se pratique idéalement au printemps, à l\'automne voir en hiver pour les moins frileux d\'entre nous. La vue depuis le haut des falaises sur les vignobles bourguignons est exceptionnelle. Les voies sportives sont très bien équipées pour une escalade plaisir dans des murs verticaux, présent en nombre sur le site. Cependant, vous y trouverez tous les profils: dalles, murs, dièdres, cheminées, dévers, fissures. La falaise est divisée en 14 secteurs regroupant près de 250 voies du 5a au 8c. ', '2018-07-23', 'prete', 2, 3),
(4, 'Arco', 'Bien qu’il s’agisse de l’une des zones les plus historiques et des lieux de naissance de l’escalade sportive, de nouvelles zones et de nouveaux itinéraires continuent d’être établis et développés à Arco, en Italie. Cette quatrième version actualisée en 2019 des sites de couennes sportives contient pas moins de 131 secteurs, dont neuf inédits. Il y en a pour toute une vie ! ', '2018-03-19', 'dispo', 2, 3);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `id_role` int(10) NOT NULL,
  `role_id_role` int(10) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `mail` (`email`),
  UNIQUE KEY `UKrma38wvnqfaf66vvmi57c71lo` (`email`),
  KEY `fk_role` (`id_role`),
  KEY `FKdn75sjxbld9wdj929y68a4hut` (`role_id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_user`, `nom`, `prenom`, `email`, `password`, `id_role`, `role_id_role`) VALUES
(16, 'Dupond', 'Alex', 'ad@test.fr', '1234', 2, NULL),
(17, 'glimois', 'jia', 'jj@test.fr', '1234', 1, NULL),
(18, 'Gerard', 'Victor', 'vg@test.fr', '1234', 2, NULL),
(19, 'Verest', 'Cécilia', 'cv@test.fr', '81dc9bdb52d04dc20036dbd8313ed055', 1, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `voie`
--

DROP TABLE IF EXISTS `voie`;
CREATE TABLE IF NOT EXISTS `voie` (
  `id_voie` int(11) NOT NULL AUTO_INCREMENT,
  `cotation` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nom_voie` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_secteur` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_voie`),
  KEY `FKbo23xms8n7a30fovqfoh50aw4` (`id_secteur`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `voie`
--

INSERT INTO `voie` (`id_voie`, `cotation`, `nom_voie`, `id_secteur`) VALUES
(5, '6b', 'Couenne', 3),
(6, '5c', 'Margo', 3),
(7, '7a', 'Blue', 9),
(14, '6c', 'Collias', 18),
(15, '8a', 'Baume', 19);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `FKcs6u1s906e16k4ewworhyqbtk` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id_user`),
  ADD CONSTRAINT `FKkgndecm5i0gjb9q0vr7c7qpir` FOREIGN KEY (`id_site`) REFERENCES `site` (`id_site`);

--
-- Contraintes pour la table `longueur`
--
ALTER TABLE `longueur`
  ADD CONSTRAINT `FKsntp125c2624k8psbmjo0daru` FOREIGN KEY (`id_voie`) REFERENCES `voie` (`id_voie`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `fk_topo` FOREIGN KEY (`id_topo`) REFERENCES `topo` (`id_topo`);

--
-- Contraintes pour la table `secteur`
--
ALTER TABLE `secteur`
  ADD CONSTRAINT `FK7ayk0wx98i7qd6d1xstgrexdh` FOREIGN KEY (`id_site`) REFERENCES `site` (`id_site`),
  ADD CONSTRAINT `secteur_ibfk_1` FOREIGN KEY (`id_site`) REFERENCES `site` (`id_site`);

--
-- Contraintes pour la table `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
  ADD CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE;

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `FKdn75sjxbld9wdj929y68a4hut` FOREIGN KEY (`role_id_role`) REFERENCES `role` (`id_role`),
  ADD CONSTRAINT `fk_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`);

--
-- Contraintes pour la table `voie`
--
ALTER TABLE `voie`
  ADD CONSTRAINT `FKbo23xms8n7a30fovqfoh50aw4` FOREIGN KEY (`id_secteur`) REFERENCES `secteur` (`id_secteur`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
