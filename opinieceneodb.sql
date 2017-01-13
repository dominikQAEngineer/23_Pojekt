-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 08 Sty 2017, 23:41
-- Wersja serwera: 5.6.26
-- Wersja PHP: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `opinieceneodb`
--
CREATE DATABASE IF NOT EXISTS `opinieceneodb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci;
USE `opinieceneodb`;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `opinia_table`
--

CREATE TABLE IF NOT EXISTS `opinia_table` (
  `ID_OPINII` int(20) unsigned NOT NULL,
  `WADY_PRODUKTU` text COLLATE utf8mb4_polish_ci NOT NULL,
  `ZALETY_PRODUKTU` text COLLATE utf8mb4_polish_ci NOT NULL,
  `PODSUMOWANIE` text COLLATE utf8mb4_polish_ci NOT NULL,
  `OCENA` text COLLATE utf8mb4_polish_ci NOT NULL,
  `AUTOR` varchar(255) COLLATE utf8mb4_polish_ci NOT NULL,
  `DATA_WYSTAWIENIA` date NOT NULL,
  `ID_PRODUKTU` int(20) unsigned NOT NULL,
  `REKOMENDACJA` varchar(20) COLLATE utf8mb4_polish_ci NOT NULL,
  `OPINIA_PRZYDATNA` int(11) NOT NULL,
  `OPINIA_NIEPRZYDATNA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci COMMENT='Tabela przechowuje informacje na temat pojedynczej opinii wydanej przez danego uzytkownika do danego produktu';

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `produkt_table`
--

CREATE TABLE IF NOT EXISTS `produkt_table` (
  `ID_PRODUKTU` int(20) unsigned NOT NULL,
  `RODZAJ_PRODUKTU` varchar(255) COLLATE utf8mb4_polish_ci NOT NULL,
  `MARKA_PRODUKTU` varchar(255) COLLATE utf8mb4_polish_ci NOT NULL,
  `MODEL_PRODUKTU` varchar(255) COLLATE utf8mb4_polish_ci NOT NULL,
  `DODATKOWE_UWAGI` varchar(255) COLLATE utf8mb4_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `opinia_table`
--
ALTER TABLE `opinia_table`
  ADD PRIMARY KEY (`ID_OPINII`),
  ADD KEY `ID_PRODUKTU` (`ID_PRODUKTU`);

--
-- Indexes for table `produkt_table`
--
ALTER TABLE `produkt_table`
  ADD PRIMARY KEY (`ID_PRODUKTU`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `opinia_table`
--
ALTER TABLE `opinia_table`
  MODIFY `ID_OPINII` int(20) unsigned NOT NULL AUTO_INCREMENT;
--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `opinia_table`
--
ALTER TABLE `opinia_table`
  ADD CONSTRAINT `opinia_table_ibfk_1` FOREIGN KEY (`ID_PRODUKTU`) REFERENCES `produkt_table` (`ID_PRODUKTU`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
