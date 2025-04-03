-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 03 avr. 2025 à 19:58
-- Version du serveur : 9.1.0
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `easybee`
--
CREATE DATABASE IF NOT EXISTS `easybee_projet2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `easybee_projet2`;

-- --------------------------------------------------------

--
-- Structure de la table `bonlivraison`
--

DROP TABLE IF EXISTS `bonlivraison`;
CREATE TABLE IF NOT EXISTS `bonlivraison` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dateLivraison` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;

--
-- Déchargement des données de la table `bonlivraison`
--

INSERT INTO `bonlivraison` (`id`, `dateLivraison`) VALUES
(1, '0000-00-00'),
(2, '0000-00-00'),
(3, '0000-00-00');

-- --------------------------------------------------------

--
-- Structure de la table `caisse`
--

DROP TABLE IF EXISTS `caisse`;
CREATE TABLE IF NOT EXISTS `caisse` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nomCaisse` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '',
  `idParamCaisse` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idParamCaisse` (`idParamCaisse`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;

-- --------------------------------------------------------

--
-- Structure de la table `categoriesalarie`
--

DROP TABLE IF EXISTS `categoriesalarie`;
CREATE TABLE IF NOT EXISTS `categoriesalarie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nomCat` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;

--
-- Déchargement des données de la table `categoriesalarie`
--

INSERT INTO `categoriesalarie` (`id`, `nomCat`) VALUES
(1, 'vendeur'),
(2, 'preparateur');

-- --------------------------------------------------------

--
-- Structure de la table `catproduit`
--

DROP TABLE IF EXISTS `catproduit`;
CREATE TABLE IF NOT EXISTS `catproduit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nomCat` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;

-- --------------------------------------------------------

--
-- Structure de la table `cmdeapprodepot`
--

DROP TABLE IF EXISTS `cmdeapprodepot`;
CREATE TABLE IF NOT EXISTS `cmdeapprodepot` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dateCommande` date NOT NULL,
  `statutCommande` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '',
  `idCatSalarie` int NOT NULL,
  `nomCommande` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idCat` (`idCatSalarie`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;

--
-- Déchargement des données de la table `cmdeapprodepot`
--

INSERT INTO `cmdeapprodepot` (`id`, `dateCommande`, `statutCommande`, `idCatSalarie`, `nomCommande`) VALUES
(1, '2001-01-11', 'en attente', 1, 'Combinaison'),
(2, '0000-00-00', 'en attente', 2, 'Pots'),
(3, '0000-00-00', 'en attente', 1, 'Ruches');

-- --------------------------------------------------------

--
-- Structure de la table `detailcmd`
--

DROP TABLE IF EXISTS `detailcmd`;
CREATE TABLE IF NOT EXISTS `detailcmd` (
  `idProduit` int NOT NULL,
  `idBonLivraison` int NOT NULL,
  `qtePrepa` int NOT NULL,
  `qteRecu` int NOT NULL,
  `idCmdeApproDepot` int NOT NULL,
  PRIMARY KEY (`idProduit`,`idBonLivraison`),
  KEY `idBonLivraison` (`idBonLivraison`),
  KEY `idCmdeApproDepot` (`idCmdeApproDepot`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;

--
-- Déchargement des données de la table `detailcmd`
--

INSERT INTO `detailcmd` (`idProduit`, `idBonLivraison`, `qtePrepa`, `qteRecu`, `idCmdeApproDepot`) VALUES
(1, 1, 3, 3, 1),
(2, 2, 25, 25, 2),
(3, 3, 5, 5, 3);

-- --------------------------------------------------------

--
-- Structure de la table `detailproduit`
--

DROP TABLE IF EXISTS `detailproduit`;
CREATE TABLE IF NOT EXISTS `detailproduit` (
  `idProduit` int NOT NULL,
  `idCmdeApproDepot` int NOT NULL,
  `qteCmde` int NOT NULL,
  PRIMARY KEY (`idProduit`,`idCmdeApproDepot`),
  KEY `idCmdeApproDepot` (`idCmdeApproDepot`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;

--
-- Déchargement des données de la table `detailproduit`
--

INSERT INTO `detailproduit` (`idProduit`, `idCmdeApproDepot`, `qteCmde`) VALUES
(1, 1, 25),
(2, 2, 30),
(3, 3, 35);

-- --------------------------------------------------------

--
-- Structure de la table `operationcaisse`
--

DROP TABLE IF EXISTS `operationcaisse`;
CREATE TABLE IF NOT EXISTS `operationcaisse` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dateOperation` date NOT NULL,
  `heureOperation` time NOT NULL,
  `totalPaiement` int NOT NULL,
  `idCaisse` int NOT NULL,
  `idCat` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idCaisse` (`idCaisse`),
  KEY `idCat` (`idCat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;

-- --------------------------------------------------------

--
-- Structure de la table `parametrecaisse`
--

DROP TABLE IF EXISTS `parametrecaisse`;
CREATE TABLE IF NOT EXISTS `parametrecaisse` (
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codeProduit` int NOT NULL,
  `stockMag` int NOT NULL,
  `stockMiniMag` int NOT NULL,
  `designationProduit` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '',
  `prixPdt` float NOT NULL,
  `stockEntrepot` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `codeProduit`, `stockMag`, `stockMiniMag`, `designationProduit`, `prixPdt`, `stockEntrepot`) VALUES
(1, 1, 0, 0, 'Combinaison', 35.8, 50),
(2, 2, 3, 0, 'Pots', 0.75, 50),
(3, 3, 5, 0, 'Ruches', 25.5, 50);

-- --------------------------------------------------------

--
-- Structure de la table `salarie`
--

DROP TABLE IF EXISTS `salarie`;
CREATE TABLE IF NOT EXISTS `salarie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `matriculeSalarie` int NOT NULL,
  `nomSalarie` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '',
  `prenomSalarie` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '',
  `idCat` int NOT NULL,
  `identifiant` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '',
  `motDePasse` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `idCat` (`idCat`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;

--
-- Déchargement des données de la table `salarie`
--

INSERT INTO `salarie` (`id`, `matriculeSalarie`, `nomSalarie`, `prenomSalarie`, `idCat`, `identifiant`, `motDePasse`) VALUES
(1, 1, 'adminVendeur', 'adminVendeur', 1, 'adminVendeur', 'adminVendeur'),
(2, 2, 'adminPrepa', 'adminPrepa', 2, 'adminPrepa', 'adminPrepa');

-- --------------------------------------------------------

--
-- Structure de la table `tarificationproduit`
--

DROP TABLE IF EXISTS `tarificationproduit`;
CREATE TABLE IF NOT EXISTS `tarificationproduit` (
  `idProduit` int NOT NULL,
  `idOpeCaisse` int NOT NULL,
  `tauxTVA` float NOT NULL,
  `prixUnitaire` float NOT NULL,
  PRIMARY KEY (`idProduit`,`idOpeCaisse`),
  KEY `idOpeCaisse` (`idOpeCaisse`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `caisse`
--
ALTER TABLE `caisse`
  ADD CONSTRAINT `caisse_ibfk_1` FOREIGN KEY (`idParamCaisse`) REFERENCES `parametrecaisse` (`id`);

--
-- Contraintes pour la table `cmdeapprodepot`
--
ALTER TABLE `cmdeapprodepot`
  ADD CONSTRAINT `cmdeapprodepot_ibfk_1` FOREIGN KEY (`idCatSalarie`) REFERENCES `categoriesalarie` (`id`);

--
-- Contraintes pour la table `detailcmd`
--
ALTER TABLE `detailcmd`
  ADD CONSTRAINT `detailcmd_ibfk_1` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`id`),
  ADD CONSTRAINT `detailcmd_ibfk_2` FOREIGN KEY (`idBonLivraison`) REFERENCES `bonlivraison` (`id`),
  ADD CONSTRAINT `detailcmd_ibfk_3` FOREIGN KEY (`idCmdeApproDepot`) REFERENCES `cmdeapprodepot` (`id`);

--
-- Contraintes pour la table `detailproduit`
--
ALTER TABLE `detailproduit`
  ADD CONSTRAINT `detailproduit_ibfk_1` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`id`),
  ADD CONSTRAINT `detailproduit_ibfk_2` FOREIGN KEY (`idCmdeApproDepot`) REFERENCES `cmdeapprodepot` (`id`);

--
-- Contraintes pour la table `operationcaisse`
--
ALTER TABLE `operationcaisse`
  ADD CONSTRAINT `operationcaisse_ibfk_1` FOREIGN KEY (`idCaisse`) REFERENCES `caisse` (`id`),
  ADD CONSTRAINT `operationcaisse_ibfk_2` FOREIGN KEY (`idCat`) REFERENCES `categoriesalarie` (`id`);

--
-- Contraintes pour la table `salarie`
--
ALTER TABLE `salarie`
  ADD CONSTRAINT `salarie_ibfk_1` FOREIGN KEY (`idCat`) REFERENCES `categoriesalarie` (`id`);

--
-- Contraintes pour la table `tarificationproduit`
--
ALTER TABLE `tarificationproduit`
  ADD CONSTRAINT `tarificationproduit_ibfk_1` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`id`),
  ADD CONSTRAINT `tarificationproduit_ibfk_2` FOREIGN KEY (`idOpeCaisse`) REFERENCES `operationcaisse` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
