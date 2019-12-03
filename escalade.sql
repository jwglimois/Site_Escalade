-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 03 déc. 2019 à 22:41
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
  `date_creation` datetime NOT NULL,
  `message` text COLLATE utf8_unicode_ci NOT NULL,
  `id_site` int(10) NOT NULL,
  `id_user` int(10) NOT NULL,
  PRIMARY KEY (`id_commentaire`),
  KEY `fk_site` (`id_site`),
  KEY `fk_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `commentaire`
--

INSERT INTO `commentaire` (`id_commentaire`, `date_creation`, `message`, `id_site`, `id_user`) VALUES
(1, '2019-11-25 00:00:00', 'Est-ce que quelqu\'un a d\'autres TOPO concernant ce site?', 1, 2),
(2, '2019-11-26 00:00:00', 'Je recommande fortement ce site. Il est super!!', 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `longueur`
--

DROP TABLE IF EXISTS `longueur`;
CREATE TABLE IF NOT EXISTS `longueur` (
  `id_longueur` int(10) NOT NULL AUTO_INCREMENT,
  `hauteur` int(10) NOT NULL,
  `id_voie` int(10) NOT NULL,
  PRIMARY KEY (`id_longueur`),
  KEY `fk_voie` (`id_voie`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `longueur`
--

INSERT INTO `longueur` (`id_longueur`, `hauteur`, `id_voie`) VALUES
(1, 10, 1),
(2, 20, 1),
(3, 30, 1),
(4, 40, 1),
(5, 20, 2),
(6, 30, 2),
(7, 30, 2),
(8, 50, 3),
(9, 40, 3),
(10, 30, 3),
(11, 20, 4),
(12, 30, 4),
(13, 40, 4),
(14, 30, 5),
(15, 30, 5),
(16, 30, 5),
(17, 20, 6),
(18, 20, 6),
(19, 20, 6),
(20, 20, 6);

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
  KEY `id_site` (`id_site`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `secteur`
--

INSERT INTO `secteur` (`id_secteur`, `nom_secteur`, `id_site`) VALUES
(1, 'Sud', 1),
(2, 'Dune', 1),
(3, 'Grand Nord', 2);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `site`
--

INSERT INTO `site` (`id_site`, `nom_site`, `region`) VALUES
(1, 'Le Joncas', '34_Hérault'),
(2, 'Arudy', '64_Pyrénées-Atlantiques');

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
  `mail` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `identifiant` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `id_role` int(10) NOT NULL,
  PRIMARY KEY (`id_user`),
  KEY `fk_user_role` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_user`, `nom`, `prenom`, `mail`, `identifiant`, `password`, `id_role`) VALUES
(1, 'Glimois', 'Jia-Wen', 'jwglimois@gmail.com', 'jwGlimois', '1234', 2),
(2, 'Dupond', 'Pierre', 'pierre_dupond@test.fr', 'pDupond', '1234', 1),
(3, 'David', 'Philippe', 'dp@test.fr', 'pDavid', '1234', 1);

-- --------------------------------------------------------

--
-- Structure de la table `voie`
--

DROP TABLE IF EXISTS `voie`;
CREATE TABLE IF NOT EXISTS `voie` (
  `id_voie` int(10) NOT NULL AUTO_INCREMENT,
  `nom_voie` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `cotation` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `id_secteur` int(10) NOT NULL,
  PRIMARY KEY (`id_voie`),
  KEY `fk_secteur` (`id_secteur`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `voie`
--

INSERT INTO `voie` (`id_voie`, `nom_voie`, `cotation`, `id_secteur`) VALUES
(1, 'Coco Bench', '7a', 1),
(2, 'Cashimire', '5a', 1),
(3, 'Ficus', '8a', 2),
(4, 'Bana', '6c', 2),
(5, 'Couenne', '6b', 3),
(6, 'Margo', '5c', 3);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `fk_site` FOREIGN KEY (`id_site`) REFERENCES `site` (`id_site`),
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id_user`);

--
-- Contraintes pour la table `longueur`
--
ALTER TABLE `longueur`
  ADD CONSTRAINT `fk_voie` FOREIGN KEY (`id_voie`) REFERENCES `voie` (`id_voie`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `fk_emprunteur` FOREIGN KEY (`id_emprunteur`) REFERENCES `utilisateur` (`id_user`),
  ADD CONSTRAINT `fk_topo` FOREIGN KEY (`id_topo`) REFERENCES `topo` (`id_topo`);

--
-- Contraintes pour la table `secteur`
--
ALTER TABLE `secteur`
  ADD CONSTRAINT `secteur_ibfk_1` FOREIGN KEY (`id_site`) REFERENCES `site` (`id_site`);

--
-- Contraintes pour la table `topo`
--
ALTER TABLE `topo`
  ADD CONSTRAINT `fk_proprietaire` FOREIGN KEY (`id_proprietaire`) REFERENCES `utilisateur` (`id_user`),
  ADD CONSTRAINT `fk_topo_site` FOREIGN KEY (`id_site`) REFERENCES `site` (`id_site`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `fk_user_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`);

--
-- Contraintes pour la table `voie`
--
ALTER TABLE `voie`
  ADD CONSTRAINT `fk_secteur` FOREIGN KEY (`id_secteur`) REFERENCES `secteur` (`id_secteur`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
