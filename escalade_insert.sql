-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 16 déc. 2019 à 11:08
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



INSERT INTO `commentaire` (`id_commentaire`, `date_creation`, `message`, `id_site`, `id_user`) VALUES
(2, '2019-11-26', 'Je recommande fortement ce site. Il est super!!', 2, 18),
(6, '2019-12-15', 'Est-ce que quelqu\'un a d\'autres TOPO concernant ce site?\'', 47, 18),
(8, '2019-12-15', 'Je vais organiser une sortie dans ce site. Envie de nous rejoindre?', 46, 18);



INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(22),
(22);



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
(80, 50, 19);



INSERT INTO `role` (`id_role`, `type`) VALUES
(1, 'utilisateur'),
(2, 'membre');



INSERT INTO `secteur` (`id_secteur`, `nom_secteur`, `id_site`) VALUES
(3, 'Grand Nord', 2),
(9, 'Valle d\'Or', 2),
(18, 'Babylone', 46),
(19, 'Rouquette', 46),
(20, 'Sud', 47),
(21, 'Dune', 47);



INSERT INTO `site` (`id_site`, `nom_site`, `region`) VALUES
(2, 'Arudy', '64_Pyrénées-Atlantiques'),
(46, 'Russan', '30_Gard'),
(47, 'Le Joncas', '34_Hérault');



INSERT INTO `topo` (`id_topo`, `nom_topo`, `description`, `date_parution`, `statut`, `id_user`, `id_site`) VALUES
(5, 'Verghellu', 'C\'est la falaise parfaite pour une journée d\'escalade en famille. A deux pas d\'une petite route où les voitures sont rares et à l\'ombre des larici, vous testerez vos chaussons et votre technique sur les belles dalles du secteur central. Plus haut à droite, quelques jolies longueurs vous réapprendront à tirer sur les bras.', '2019-06-02', 'dispo', 18, 47),
(6, 'Arve', 'Retrouvez dans ce topo-guide toutes les voies moderne de la vallée de l\'Arve (à l\'exception de celle de Chamonix). Plus de 1260 itinéraires de grandes voies sont à explorer dan ce topo tout en couleur avec pour la plupart des grandes voies, un tracé photo. Les dessins des autres sites, faits à la main, sont précis et esthétiques. Pour chaque site l\'accès est décrit par un texte et un schéma précis. Une sélection de sa voie en fonction du niveau, de la longueur ou des conditions peut-être effectuée par le grimpeur grâce au tableau récapitulatif présenté au début de l\'ouvrage.', '2017-01-11', 'dispo', 19, 46),
(7, 'test', 'test', '2019-11-09', 'dispo', 18, 47);



INSERT INTO `utilisateur` (`id_user`, `nom`, `prenom`, `email`, `password`, `id_role`, `role_id_role`) VALUES
(16, 'Dupond', 'Alex', 'ad@test.fr', '1234', 2, NULL),
(17, 'glimois', 'jia', 'jj@test.fr', '1234', 1, NULL),
(18, 'Gerard', 'Victor', 'vg@test.fr', '1234', 2, NULL),
(19, 'Verest', 'Cécilia', 'cv@test.fr', '81dc9bdb52d04dc20036dbd8313ed055', 1, NULL),
(21, 'test2', 'test1', 'test1@test.fr', 'd93591bdf7860e1e4ee2fca799911215', 1, NULL);



INSERT INTO `voie` (`id_voie`, `cotation`, `nom_voie`, `id_secteur`) VALUES
(5, '6b', 'Couenne', 3),
(6, '5c', 'Margo', 3),
(7, '7a', 'Blue', 9),
(14, '6c', 'Collias', 18),
(15, '8a', 'Baume', 19),
(16, '7a', 'Coco Bench', 20),
(17, '5a', 'Cashmire', 20),
(18, '7b', 'Ficus', 21),
(19, '6c', 'Bana', 21);



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
