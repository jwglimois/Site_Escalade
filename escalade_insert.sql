-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mer. 18 déc. 2019 à 23:11
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


INSERT INTO `commentaire` (`id_commentaire`, `date_creation`, `message`, `id_site`, `id_user`) VALUES
(2, '2019-11-26', 'Trop bien!!', 2, 18),
(6, '2019-12-15', 'Est-ce que quelqu\'un a d\'autres TOPO concernant ce site?\'', 47, 18),
(8, '2019-12-15', 'Je vais organiser une sortie dans ce site. Envie de nous rejoindre?', 46, 18);


--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(22),
(22);


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
(60, 50, 15),
(61, 40, 16),
(62, 30, 16),
(63, 20, 17),
(64, 50, 17),
(65, 40, 16),
(66, 30, 16),
(67, 20, 17),
(68, 50, 17),
(69, 20, 18),
(70, 30, 18),
(71, 50, 19),
(72, 20, 19),
(73, 20, 16),
(74, 50, 16),
(75, 40, 17),
(76, 30, 17),
(77, 30, 18),
(78, 40, 18),
(79, 25, 19),
(80, 50, 19),
(186, 20, 26),
(187, 45, 26),
(188, 45, 26),
(189, 15, 26),
(214, 15, 33),
(215, 25, 33),
(216, 35, 33),
(217, 35, 33),
(218, 10, 34),
(219, 15, 34),
(220, 20, 34),
(221, 25, 34),
(232, 10, 35),
(233, 15, 35),
(234, 20, 35),
(235, 25, 35),
(236, 25, 36),
(237, 20, 36),
(238, 30, 36),
(239, 35, 36),
(240, 15, 37),
(241, 25, 37),
(242, 20, 37),
(243, 40, 37);


--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id_reservation`, `id_topo`, `id_user`) VALUES
(7, 8, 23),
(8, 6, 23),
(9, 5, 23),
(10, 7, 23);


--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id_role`, `type`) VALUES
(1, 'utilisateur'),
(2, 'membre');

-
--
-- Déchargement des données de la table `secteur`
--

INSERT INTO `secteur` (`id_secteur`, `nom_secteur`, `id_site`) VALUES
(3, 'Grand Nord', 2),
(9, 'Valle d\'Or', 2),
(18, 'Babylone', 46),
(19, 'Rouquette', 46),
(20, 'Sud', 47),
(21, 'Dune', 47),
(27, 'Terray', 54),
(28, 'Verdon ', 55),
(29, 'Buoux', 55),
(30, 'La Commère ', 56),
(31, 'Exilleps', 56);


-- Déchargement des données de la table `site`
--

INSERT INTO `site` (`id_site`, `nom_site`, `region`, `tag_active`) VALUES
(2, 'Arudy', '64_Pyrénées Atlantiques', 'oui'),
(46, 'Russan', '30_Gard', NULL),
(47, 'Le Joncas', '34_Hérault', 'oui'),
(54, 'Le Saussois', '89_Yonne', NULL),
(55, 'Les Eaux-Claires', '16_Charente', NULL),
(56, 'Crenans', '39_Jura', NULL);

-
--
-- Déchargement des données de la table `spring_session`
--

INSERT INTO `spring_session` (`PRIMARY_ID`, `SESSION_ID`, `CREATION_TIME`, `LAST_ACCESS_TIME`, `MAX_INACTIVE_INTERVAL`, `EXPIRY_TIME`, `PRINCIPAL_NAME`) VALUES
('f1c36c05-9bb0-4a73-975e-9538e3b49bad', '7de6a5b9-d8f8-4598-9d19-fa3d788657fd', 1576710186299, 1576710582532, 900, 1576711482532, NULL);

-
--
-- Déchargement des données de la table `spring_session_attributes`
--

INSERT INTO `spring_session_attributes` (`SESSION_PRIMARY_ID`, `ATTRIBUTE_NAME`, `ATTRIBUTE_BYTES`) VALUES
('f1c36c05-9bb0-4a73-975e-9538e3b49bad', 'idUserSession', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a65787000000001770400000001740002313878),
('f1c36c05-9bb0-4a73-975e-9538e3b49bad', 'infoSession', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000277040000000274000132740006566963746f7278);



--
-- Déchargement des données de la table `topo`
--

INSERT INTO `topo` (`id_topo`, `nom_topo`, `description`, `date_parution`, `statut`, `id_user`, `id_site`) VALUES
(5, 'Verghellu', 'C\'est la falaise parfaite pour une journée d\'escalade en famille. A deux pas d\'une petite route où les voitures sont rares et à l\'ombre des larici, vous testerez vos chaussons et votre technique sur les belles dalles du secteur central. Plus haut à droite, quelques jolies longueurs vous réapprendront à tirer sur les bras.', '2019-06-02', 'prete', 18, 47),
(6, 'Arve', 'Retrouvez dans ce topo-guide toutes les voies moderne de la vallée de l\'Arve (à l\'exception de celle de Chamonix). Plus de 1260 itinéraires de grandes voies sont à explorer dan ce topo tout en couleur avec pour la plupart des grandes voies, un tracé photo. Les dessins des autres sites, faits à la main, sont précis et esthétiques. Pour chaque site l\'accès est décrit par un texte et un schéma précis. Une sélection de sa voie en fonction du niveau, de la longueur ou des conditions peut-être effectuée par le grimpeur grâce au tableau récapitulatif présenté au début de l\'ouvrage.', '2017-01-11', 'attente', 19, 46),
(7, 'Rurey', 'Voilà un site majeur du Doubs qui a récemment vu le jour, puisque sa naissance remonte à début 2012. Non pas qu’il se soit produit quelque choc tectonique ou surrection géologique ayant fait apparaître cette falaise.', '2019-11-09', 'attente', 18, 47),
(8, 'Peace', 'La falaise de Peace est une petite jeune qui a récemment rejoint la longue liste des falaises du département grâce au travail du comité départemental FFME du Doubs. ', '2018-10-09', 'dispo', 19, 2);


--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_user`, `nom`, `prenom`, `email`, `password`, `id_role`, `role_id_role`) VALUES
(16, 'Dupond', 'Alex', 'ad@test.fr', '1234', 2, NULL),
(17, 'glimois', 'jia', 'jj@test.fr', '1234', 1, NULL),
(18, 'Gerard', 'Victor', 'vg@test.fr', '1234', 2, NULL),
(19, 'Verest', 'Cécilia', 'cv@test.fr', '81dc9bdb52d04dc20036dbd8313ed055', 1, NULL),
(21, 'Suroy', 'David', 'david@test.fr', 'd93591bdf7860e1e4ee2fca799911215', 1, NULL),
(22, 'Gate', 'Tom', 'tom@test.fr', 'b59c67bf196a4758191e42f76670ceba', 2, NULL),
(23, 'Macron', 'Pierre', 'pm@test.fr', '934b535800b1cba8f96a5d72f72f1611', 1, NULL);


--
-- Déchargement des données de la table `voie`
--

INSERT INTO `voie` (`id_voie`, `cotation`, `nom_voie`, `id_secteur`) VALUES
(5, '6b', 'Couenne', 3),
(6, '5c', 'Margo', 3),
(7, '7a', 'Blue', 9),
(14, '6c', 'Collias', 18),
(15, '8a', 'Baume', 19),
(16, '7a', 'Coco Bench', 20),
(17, '5a', 'Cashmire', 20),
(18, '7b', 'Ficus', 21),
(19, '6c', 'Bana', 21),
(26, '7c', 'Paragot', 27),
(33, '7a', 'Grand Toit', 28),
(34, '6c', 'Mirroir', 29),
(35, '9a', 'Dévers', 30),
(36, '8a', 'Les fourmis', 30),
(37, '5b', 'Montaigu', 31);

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
  ADD CONSTRAINT `FKiqjwftpb6o2kyu3k45xiemp6c` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id_user`),
  ADD CONSTRAINT `FKrxx2d19vx5ndessah3r5wit0c` FOREIGN KEY (`id_topo`) REFERENCES `topo` (`id_topo`);

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
-- Contraintes pour la table `topo`
--
ALTER TABLE `topo`
  ADD CONSTRAINT `FKq0u3kvcohifg7l9xe37aivo4i` FOREIGN KEY (`id_site`) REFERENCES `site` (`id_site`),
  ADD CONSTRAINT `FKsmygsg9hrrwl92yqgnnu41rkg` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id_user`);

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
