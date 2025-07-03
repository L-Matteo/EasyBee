-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 03 juil. 2025 à 16:38
-- Version du serveur : 8.2.0
-- Version de PHP : 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `easybee_java`
--

--
-- Déchargement des données de la table `categoriesalarie`
--

INSERT INTO `categoriesalarie` (`id`, `nomCat`) VALUES
(1, 'vendeur'),
(2, 'preparateur');

--
-- Déchargement des données de la table `cmdeapprodepot`
--

INSERT INTO `cmdeapprodepot` (`id`, `dateCommande`, `statutCommande`, `idCatSalarie`, `nomCommande`, `descriptionErreur`) VALUES
(14, '2025-06-11', 'Erreur', 1, 'Commande n°14', 'La quanité de pôt reçu n\'est pas suffisante. \nIl manque 5 pôts'),
(17, '2025-06-12', 'livrée', 1, 'Commande n°17', 'qdksndkjqsdqs'),
(18, '2025-06-12', 'en cours de livraison', 1, 'Commande n°18', NULL),
(19, '2025-06-19', 'livrée', 1, 'Commande n°19', ''),
(20, '2025-07-02', 'livrée', 1, 'Commande n°20', NULL);

--
-- Déchargement des données de la table `detailproduit`
--

INSERT INTO `detailproduit` (`idProduit`, `idCmdeApproDepot`, `qteCmde`) VALUES
(4, 14, 40),
(4, 17, 10),
(4, 19, 10),
(5, 14, 25),
(5, 17, 20),
(5, 19, 10),
(6, 19, 10),
(6, 20, 10),
(7, 20, 10),
(8, 14, 10),
(10, 18, 10),
(12, 14, 1),
(20, 18, 18),
(20, 20, 10),
(22, 18, 2);

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `codeProduit`, `stockMag`, `stockMiniMag`, `designationProduit`, `prixPdt`, `stockEntrepot`) VALUES
(4, 1001, 50, 20, 'Pot en verre 250g', 0.5, 100),
(5, 1002, 40, 20, 'Pot en verre 500g', 0.6, 80),
(6, 1003, 100, 30, 'Couvercle doré pour pot', 0.1, 200),
(7, 1004, 200, 50, 'Étiquettes personnalisables', 0.15, 500),
(8, 1005, 10, 2, 'Ruche Dadant 10 cadres', 85, 15),
(9, 1006, 5, 1, 'Ruche Dadant 20 cadres', 110, 10),
(10, 1007, 60, 20, 'Cadres montés avec fil', 1.2, 100),
(11, 1008, 70, 30, 'Feuilles de cire gaufrée', 0.9, 120),
(12, 1009, 15, 5, 'Enfumoir inox', 25, 30),
(13, 1010, 20, 5, 'Lève-cadres', 8, 40),
(14, 1011, 25, 10, 'Brosse à abeilles', 4.5, 50),
(15, 1012, 4, 2, 'Extracteur manuel 4 cadres', 190, 6),
(16, 1013, 10, 3, 'Couteau à désoperculer', 15, 15),
(17, 1014, 20, 5, 'Seau alimentaire 10L', 6, 30),
(18, 1015, 25, 10, 'Nourrissement sirop', 12, 40),
(19, 1016, 30, 10, 'Pâte candi', 8, 50),
(20, 1017, 10, 5, 'Traitement Varroa Apivar', 18, 20),
(21, 1018, 12, 3, 'Blouson apiculteur', 35, 20),
(22, 1019, 18, 5, 'Gants cuir + toile', 12, 30),
(23, 1020, 15, 5, 'Chapeau avec voile', 18, 25);

--
-- Déchargement des données de la table `salarie`
--

INSERT INTO `salarie` (`id`, `matriculeSalarie`, `nomSalarie`, `prenomSalarie`, `idCat`, `identifiant`, `motDePasse`) VALUES
(1, 1, 'adminVendeur', 'adminVendeur', 1, 'adminVendeur', 'adminVendeur'),
(2, 2, 'adminPrepa', 'adminPrepa', 2, 'adminPrepa', 'adminPrepa');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
