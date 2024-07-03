-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 03 Jul 2024 pada 22.12
-- Versi server: 10.4.28-MariaDB
-- Versi PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `absensi`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `absensi`
--

CREATE TABLE `absensi` (
  `id` int(11) NOT NULL,
  `mahasiswa_id` int(11) DEFAULT NULL,
  `matakuliah_id` int(11) DEFAULT NULL,
  `tanggal` date NOT NULL,
  `hadir` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `absensi`
--

INSERT INTO `absensi` (`id`, `mahasiswa_id`, `matakuliah_id`, `tanggal`, `hadir`) VALUES
(21, 1, 4, '2024-06-25', 1),
(22, 1, 5, '2024-06-25', 1),
(23, 1, 1, '2024-06-25', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`id`, `nama`, `username`, `password`) VALUES
(1, 'Hakuze Phenom', 'phenom', '0b0c87460e64e5445705d4a725dcb39d');

-- --------------------------------------------------------

--
-- Struktur dari tabel `dosen`
--

CREATE TABLE `dosen` (
  `id` int(11) NOT NULL,
  `nim` varchar(20) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `dosen`
--

INSERT INTO `dosen` (`id`, `nim`, `nama`, `password`) VALUES
(1, '202310370311001', 'Christian Sri Kusuma Aditya, S.Kom., M.Kom', '76a2173be6393254e72ffa4d6df1030a'),
(2, '202310370311002', 'Mr Sofyan', '76a2173be6393254e72ffa4d6df1030a'),
(3, '202310370311003', 'Fera Putri Ayu Lestari, S.Kom.', '76a2173be6393254e72ffa4d6df1030a'),
(4, '202310370311004', 'Heni Vidia Sari, M.Kom.', '76a2173be6393254e72ffa4d6df1030a'),
(5, '202310370311005', 'Roykhanah, S.Pd', '76a2173be6393254e72ffa4d6df1030a'),
(6, '202310370311998', 'Pathfinder', 'd2f92a85cc02426fd3fc6cc8d9342936'),
(8, '202310370311999', 'Octane', '70f67b665209be33033365fd5ba29fe7');

-- --------------------------------------------------------

--
-- Struktur dari tabel `dosen_matakuliah`
--

CREATE TABLE `dosen_matakuliah` (
  `id` int(11) NOT NULL,
  `dosen_id` int(11) DEFAULT NULL,
  `matakuliah_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `dosen_matakuliah`
--

INSERT INTO `dosen_matakuliah` (`id`, `dosen_id`, `matakuliah_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 5, 6),
(5, 1, 5),
(6, 4, 4),
(7, 1, 8),
(13, 1, 14),
(14, 1, 7),
(15, 6, 15);

-- --------------------------------------------------------

--
-- Struktur dari tabel `log`
--

CREATE TABLE `log` (
  `id` int(11) NOT NULL,
  `username` varchar(155) DEFAULT NULL,
  `time_login` datetime DEFAULT NULL,
  `is_login` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `log`
--

INSERT INTO `log` (`id`, `username`, `time_login`, `is_login`) VALUES
(2, '202310370311483', '2024-06-16 17:19:30', 0),
(4, '202310370311483', '2024-06-16 17:35:37', 0),
(5, '202310370311483', '2024-06-16 17:35:42', 0),
(6, '202310370311483', '2024-06-16 17:35:44', 0),
(7, '202310370311483', '2024-06-16 17:36:18', 0),
(8, '202310370311483', '2024-06-16 17:36:29', 0),
(9, '202310370311483', '2024-06-16 17:39:33', 0),
(10, '202310370311483', '2024-06-16 17:39:36', 1),
(11, '202310370311483', '2024-06-16 17:50:45', 0),
(12, '202310370311483', '2024-06-16 17:50:50', 1),
(13, '202310370311483', '2024-06-16 17:59:56', 1),
(14, '202310370311483', '2024-06-22 15:59:20', 1),
(15, '202310370311483', '2024-06-22 15:59:37', 0),
(16, '202310370311483', '2024-06-22 15:59:40', 1),
(17, '202310370311483', '2024-06-22 16:03:14', 0),
(18, '202310370311001', '2024-06-22 16:04:07', 1),
(19, '202310370311001', '2024-06-22 16:04:57', 1),
(20, '202310370311001', '2024-06-22 16:05:30', 1),
(21, '202310370311001', '2024-06-22 16:14:48', 1),
(22, '202310370311483', '2024-06-22 16:16:20', 1),
(23, '202310370311483', '2024-06-22 16:16:59', 1),
(24, '202310370311483', '2024-06-22 16:17:42', 1),
(25, '202310370311483', '2024-06-22 16:18:21', 1),
(26, '202310370311483', '2024-06-22 16:19:55', 1),
(27, '202310370311483', '2024-06-22 16:21:26', 1),
(28, '202310370311483', '2024-06-22 16:21:42', 1),
(29, '202310370311483', '2024-06-22 17:46:43', 1),
(30, '202310370311483', '2024-06-22 18:57:13', 1),
(31, '202310370311483', '2024-06-22 19:25:28', 1),
(32, '202310370311483', '2024-06-22 19:26:19', 1),
(33, '202310370311483', '2024-06-22 19:26:36', 1),
(34, '202310370311483', '2024-06-22 19:26:57', 1),
(35, '202310370311483', '2024-06-22 20:42:03', 1),
(36, '202310370311483', '2024-06-22 21:01:52', 1),
(37, '202310370311483', '2024-06-22 21:02:59', 1),
(38, '202310370311483', '2024-06-22 21:16:25', 1),
(39, '202310370311483', '2024-06-22 21:17:26', 1),
(40, '202310370311483', '2024-06-22 21:18:07', 1),
(41, '202310370311483', '2024-06-22 21:26:39', 1),
(42, '202310370311483', '2024-06-22 21:27:30', 1),
(43, '202310370311483', '2024-06-22 21:28:50', 0),
(44, '202310370311483', '2024-06-22 21:28:53', 1),
(45, '202310370311232', '2024-06-22 21:29:15', 1),
(46, '202310370311121', '2024-06-22 21:29:32', 1),
(47, '202310370311121', '2024-06-22 21:33:06', 1),
(48, '202310370311121', '2024-06-22 21:33:47', 1),
(49, '202310370311121', '2024-06-22 21:35:15', 1),
(50, '202310370311121', '2024-06-22 21:35:41', 1),
(51, '202310370311121', '2024-06-22 21:36:37', 1),
(52, '202310370311121', '2024-06-22 21:37:08', 1),
(53, '202310370311121', '2024-06-22 21:40:27', 1),
(54, '202310370311121', '2024-06-22 21:41:03', 1),
(55, '202310370311121', '2024-06-22 21:41:30', 1),
(56, '202310370311121', '2024-06-22 21:48:59', 1),
(57, '202310370311121', '2024-06-22 21:50:41', 1),
(58, '202310370311121', '2024-06-22 21:51:22', 1),
(59, '202310370311121', '2024-06-22 21:51:44', 1),
(60, '202310370311121', '2024-06-22 21:52:13', 1),
(61, '202310370311121', '2024-06-22 21:53:15', 1),
(62, '202310370311121', '2024-06-22 21:54:45', 1),
(63, '202310370311121', '2024-06-22 22:05:41', 1),
(64, '202310370311121', '2024-06-22 22:07:00', 1),
(65, '202310370311121', '2024-06-23 00:35:56', 1),
(66, '202310370311483', '2024-06-23 00:37:09', 1),
(67, '202310370311483', '2024-06-23 00:37:44', 1),
(68, '202310370311483', '2024-06-23 00:37:55', 1),
(69, '202310370311483', '2024-06-23 00:47:29', 1),
(70, '202310370311483', '2024-06-23 00:48:10', 1),
(71, '202310370311483', '2024-06-23 00:48:42', 1),
(72, '202310370311483', '2024-06-23 00:49:06', 1),
(73, '202310370311483', '2024-06-23 00:49:18', 1),
(74, '202310370311483', '2024-06-23 00:49:42', 1),
(75, '202310370311483', '2024-06-23 00:53:58', 1),
(76, '202310370311483', '2024-06-23 17:32:03', 1),
(77, '202310370311483', '2024-06-23 19:08:37', 1),
(78, '202310370311483', '2024-06-23 19:10:23', 1),
(79, '202310370311483', '2024-06-23 19:11:35', 1),
(80, '202310370311483', '2024-06-23 19:13:39', 1),
(81, '202310370311483', '2024-06-23 19:14:57', 1),
(82, '202310370311483', '2024-06-23 19:21:17', 1),
(83, '202310370311483', '2024-06-23 19:22:51', 1),
(84, '202310370311483', '2024-06-23 19:24:20', 1),
(85, '202310370311483', '2024-06-23 19:24:43', 1),
(86, '202310370311483', '2024-06-23 19:27:47', 1),
(87, '202310370311483', '2024-06-23 19:28:13', 1),
(88, '202310370311483', '2024-06-23 19:29:22', 1),
(89, '202310370311483', '2024-06-23 19:29:55', 1),
(90, '202310370311483', '2024-06-23 19:32:28', 1),
(91, '202310370311483', '2024-06-23 19:33:02', 1),
(92, '202310370311483', '2024-06-23 19:33:23', 1),
(93, '202310370311483', '2024-06-23 19:34:53', 1),
(94, '202310370311483', '2024-06-23 19:41:21', 1),
(95, '202310370311483', '2024-06-23 19:41:35', 1),
(96, '202310370311483', '2024-06-23 19:43:31', 1),
(97, '202310370311483', '2024-06-23 19:43:59', 1),
(98, '202310370311483', '2024-06-23 19:44:14', 1),
(99, '202310370311483', '2024-06-23 21:43:47', 1),
(100, '202310370311483', '2024-06-23 21:44:28', 1),
(101, '202310370311483', '2024-06-23 21:51:59', 1),
(102, '202310370311483', '2024-06-23 21:53:41', 1),
(103, '202310370311483', '2024-06-23 21:56:21', 1),
(104, '202310370311483', '2024-06-23 22:01:31', 1),
(105, '202310370311483', '2024-06-23 22:01:56', 1),
(106, '202310370311483', '2024-06-23 22:02:40', 1),
(107, '202310370311483', '2024-06-23 22:04:01', 1),
(108, '202310370311483', '2024-06-23 22:04:55', 1),
(109, '202310370311483', '2024-06-23 22:05:49', 1),
(110, '202310370311483', '2024-06-23 23:39:29', 1),
(111, '202310370311483', '2024-06-23 23:39:49', 1),
(112, '202310370311483', '2024-06-23 23:45:44', 1),
(113, '202310370311483', '2024-06-23 23:48:26', 1),
(114, '202310370311483', '2024-06-23 23:56:27', 1),
(115, '202310370311483', '2024-06-24 06:44:59', 1),
(116, '202310370311483', '2024-06-24 06:45:43', 1),
(117, '202310370311483', '2024-06-24 06:46:18', 1),
(118, '202310370311483', '2024-06-24 06:47:02', 1),
(119, '202310370311483', '2024-06-24 06:47:41', 1),
(120, '202310370311483', '2024-06-24 06:48:18', 1),
(121, '202310370311483', '2024-06-24 06:48:23', 1),
(122, '202310370311483', '2024-06-24 06:49:40', 1),
(123, '202310370311483', '2024-06-24 06:50:09', 1),
(124, '202310370311483', '2024-06-24 06:51:48', 1),
(125, '202310370311483', '2024-06-24 06:52:55', 1),
(126, '202310370311483', '2024-06-24 06:54:20', 1),
(127, '202310370311483', '2024-06-25 02:05:19', 1),
(128, '202310370311121', '2024-06-25 02:07:17', 0),
(129, '202310370311121', '2024-06-25 02:07:18', 0),
(130, '202310370311121', '2024-06-25 02:07:21', 0),
(131, '202310370311121', '2024-06-25 02:07:25', 1),
(132, '202310370311483', '2024-06-25 02:29:27', 1),
(133, '202310370311483', '2024-06-25 02:29:45', 1),
(134, '202310370311483', '2024-06-25 04:03:32', 1),
(135, '202310370311483', '2024-06-25 04:09:32', 1),
(136, '202310370311483', '2024-06-25 04:32:05', 1),
(137, '202310370311483', '2024-06-25 08:06:20', 1),
(138, '202310370311483', '2024-07-02 15:32:36', 1),
(139, '202310370311483', '2024-07-02 15:33:19', 1),
(140, '202310370311483', '2024-07-02 15:52:26', 1),
(141, '202310370311483', '2024-07-02 15:52:43', 1),
(142, '202310370311483', '2024-07-02 15:53:40', 1),
(143, '202310370311483', '2024-07-02 17:53:11', 1),
(144, '202310370311483', '2024-07-02 17:57:14', 1),
(145, '202310370311483', '2024-07-02 19:17:26', 1),
(146, '202310370311483', '2024-07-02 19:23:40', 1),
(147, '202310370311483', '2024-07-02 19:23:48', 1),
(148, '202310370311483', '2024-07-02 19:24:13', 1),
(149, '202310370311483', '2024-07-02 19:25:30', 1),
(150, '202310370311483', '2024-07-03 11:58:48', 1),
(151, '202310370311483', '2024-07-03 11:59:49', 1),
(152, '202310370311483', '2024-07-03 12:00:40', 1),
(153, '202310370311483', '2024-07-03 12:02:12', 0),
(154, '202310370311483', '2024-07-03 12:02:40', 1),
(155, '202310370311483', '2024-07-03 22:10:11', 1),
(156, '202310370311483', '2024-07-03 22:10:26', 1),
(157, '202310370311483', '2024-07-03 22:31:35', 1),
(158, '202310370311483', '2024-07-03 22:32:52', 1),
(159, 'Hakuze Phenom', '2024-07-03 23:09:47', 1),
(160, 'Hakuze Phenom', '2024-07-03 23:10:11', 0),
(161, 'Hakuze Phenom', '2024-07-03 23:10:31', 0),
(162, 'Hakuze Phenom', '2024-07-03 23:11:19', 1),
(163, 'Hakuze Phenom', '2024-07-03 23:18:05', 1),
(164, 'Hakuze Phenom', '2024-07-03 23:18:49', 1),
(165, 'Hakuze Phenom', '2024-07-03 23:19:23', 1),
(166, 'Hakuze Phenom', '2024-07-04 00:20:43', 1),
(172, 'Hakuze Phenom', '2024-07-04 00:28:55', 1),
(173, 'Hakuze Phenom', '2024-07-04 00:30:04', 1),
(174, 'Hakuze Phenom', '2024-07-04 00:31:25', 1),
(175, 'Hakuze Phenom', '2024-07-04 00:32:37', 1),
(176, 'Hakuze Phenom', '2024-07-04 00:33:50', 1),
(177, 'Hakuze Phenom', '2024-07-04 00:35:23', 1),
(178, 'Hakuze Phenom', '2024-07-04 00:35:45', 1),
(179, 'Hakuze Phenom', '2024-07-04 00:41:08', 1),
(180, 'Hakuze Phenom', '2024-07-04 00:41:41', 1),
(194, 'Hakuze Phenom', '2024-07-04 00:57:20', 1),
(195, 'Hakuze Phenom', '2024-07-04 01:06:02', 1),
(196, 'Hakuze Phenom', '2024-07-04 01:06:14', 1),
(197, 'Hakuze Phenom', '2024-07-04 01:38:38', 1),
(198, 'Hakuze Phenom', '2024-07-04 01:39:03', 1),
(199, 'Hakuze Phenom', '2024-07-04 01:39:37', 1),
(200, 'Hakuze Phenom', '2024-07-04 01:40:02', 1),
(201, 'Hakuze Phenom', '2024-07-04 01:40:56', 1),
(202, 'Hakuze Phenom', '2024-07-04 01:41:12', 1),
(203, 'Hakuze Phenom', '2024-07-04 01:53:33', 1),
(204, 'Hakuze Phenom', '2024-07-04 01:54:28', 1),
(205, 'Hakuze Phenom', '2024-07-04 02:00:25', 1),
(206, 'Hakuze Phenom', '2024-07-04 02:01:19', 1),
(207, 'Hakuze Phenom', '2024-07-04 02:02:03', 1),
(208, 'Hakuze Phenom', '2024-07-04 02:02:47', 1),
(209, 'Hakuze Phenom', '2024-07-04 02:03:24', 1),
(210, 'Hakuze Phenom', '2024-07-04 02:03:40', 0),
(211, 'Hakuze Phenom', '2024-07-04 02:03:43', 1),
(212, 'Hakuze Phenom', '2024-07-04 02:03:54', 1),
(213, 'Hakuze Phenom', '2024-07-04 02:03:58', 0),
(214, 'Hakuze Phenom', '2024-07-04 02:04:00', 1),
(215, 'Hakuze Phenom', '2024-07-04 02:22:33', 0),
(216, 'Hakuze Phenom', '2024-07-04 02:22:36', 1),
(217, 'Hakuze Phenom', '2024-07-04 02:23:00', 1),
(218, 'Hakuze Phenom', '2024-07-04 02:23:54', 1),
(219, 'Hakuze Phenom', '2024-07-04 02:25:13', 1),
(220, 'Hakuze Phenom', '2024-07-04 02:26:28', 1),
(221, 'Hakuze Phenom', '2024-07-04 02:38:42', 1),
(222, 'Hakuze Phenom', '2024-07-04 02:39:15', 1),
(223, 'Hakuze Phenom', '2024-07-04 02:39:35', 1),
(224, 'Hakuze Phenom', '2024-07-04 02:39:54', 1),
(225, 'Hakuze Phenom', '2024-07-04 02:43:45', 1),
(226, 'Hakuze Phenom', '2024-07-04 02:49:45', 1),
(227, 'Hakuze Phenom', '2024-07-04 02:56:24', 1),
(228, 'Hakuze Phenom', '2024-07-04 02:57:16', 1),
(229, 'Hakuze Phenom', '2024-07-04 02:57:52', 1),
(230, 'Hakuze Phenom', '2024-07-04 03:00:08', 1),
(231, 'Hakuze Phenom', '2024-07-04 03:06:54', 1),
(232, 'Hakuze Phenom', '2024-07-04 03:07:53', 1),
(233, '202310370311482', '2024-07-04 03:09:09', 0),
(234, '202310370311121', '2024-07-04 03:09:28', 1),
(235, '202310370311121', '2024-07-04 03:09:49', 1),
(236, 'Hakuze Phenom', '2024-07-04 03:09:58', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `id` int(11) NOT NULL,
  `nim` varchar(20) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `mahasiswa`
--

INSERT INTO `mahasiswa` (`id`, `nim`, `nama`, `password`) VALUES
(1, '202310370311483', 'Hadrian Rakha Khosyi Javier', '76a2173be6393254e72ffa4d6df1030a'),
(2, '202310370311121', 'Sakamata Chloe', 'fe4f21f2df1109ac45a1ec89e42fe710'),
(3, '202310370311232', 'Mahasiswa57', '76a2173be6393254e72ffa4d6df1030a'),
(4, '202310370311705', 'Mahasiswa100', '76a2173be6393254e72ffa4d6df1030a'),
(5, '202310370311425', 'Mahasiswa38', '76a2173be6393254e72ffa4d6df1030a'),
(6, '202310370311642', 'Mahasiswa88', '76a2173be6393254e72ffa4d6df1030a'),
(7, '202310370311470', 'Mahasiswa67', '76a2173be6393254e72ffa4d6df1030a'),
(8, '202310370311007', 'Mahasiswa46', '76a2173be6393254e72ffa4d6df1030a'),
(9, '202310370311856', 'Mahasiswa89', '76a2173be6393254e72ffa4d6df1030a'),
(10, '202310370311191', 'Mahasiswa6', '76a2173be6393254e72ffa4d6df1030a'),
(11, '202310370311836', 'Mahasiswa51', '76a2173be6393254e72ffa4d6df1030a'),
(12, '202310370311817', 'Mahasiswa63', '76a2173be6393254e72ffa4d6df1030a'),
(13, '202310370311385', 'Mahasiswa7', '76a2173be6393254e72ffa4d6df1030a'),
(14, '202310370311521', 'Mahasiswa99', '76a2173be6393254e72ffa4d6df1030a'),
(15, '202310370311875', 'Mahasiswa49', '76a2173be6393254e72ffa4d6df1030a'),
(16, '202310370311475', 'Mahasiswa100', '76a2173be6393254e72ffa4d6df1030a'),
(17, '202310370311148', 'Mahasiswa77', '76a2173be6393254e72ffa4d6df1030a'),
(18, '202310370311289', 'Mahasiswa12', '76a2173be6393254e72ffa4d6df1030a'),
(19, '202310370311752', 'Mahasiswa45', '76a2173be6393254e72ffa4d6df1030a'),
(20, '202310370311880', 'Mahasiswa39', '76a2173be6393254e72ffa4d6df1030a'),
(21, '202310370311402', 'Mahasiswa2', '76a2173be6393254e72ffa4d6df1030a'),
(22, '202310370311518', 'Mahasiswa25', '76a2173be6393254e72ffa4d6df1030a'),
(23, '202310370311370', 'Mahasiswa31', '76a2173be6393254e72ffa4d6df1030a'),
(24, '202310370311918', 'Mahasiswa3', '76a2173be6393254e72ffa4d6df1030a'),
(25, '202310370311379', 'Mahasiswa25', '76a2173be6393254e72ffa4d6df1030a'),
(26, '202310370311952', 'Mahasiswa56', '76a2173be6393254e72ffa4d6df1030a'),
(27, '202310370311228', 'Mahasiswa69', '76a2173be6393254e72ffa4d6df1030a'),
(28, '202310370311599', 'Mahasiswa54', '76a2173be6393254e72ffa4d6df1030a'),
(29, '202310370311670', 'Mahasiswa16', '76a2173be6393254e72ffa4d6df1030a'),
(30, '202310370311062', 'Mahasiswa81', '76a2173be6393254e72ffa4d6df1030a'),
(31, '202310370311115', 'Mahasiswa19', '76a2173be6393254e72ffa4d6df1030a'),
(32, '202310370311050', 'Mahasiswa28', '76a2173be6393254e72ffa4d6df1030a'),
(33, '202310370311692', 'Mahasiswa62', '76a2173be6393254e72ffa4d6df1030a'),
(34, '202310370311846', 'Mahasiswa32', '76a2173be6393254e72ffa4d6df1030a'),
(35, '202310370311424', 'Mahasiswa17', '76a2173be6393254e72ffa4d6df1030a'),
(36, '202310370311375', 'Mahasiswa14', '76a2173be6393254e72ffa4d6df1030a'),
(37, '202310370311865', 'Mahasiswa47', '76a2173be6393254e72ffa4d6df1030a'),
(38, '202310370311555', 'Mahasiswa23', '76a2173be6393254e72ffa4d6df1030a'),
(39, '202310370311719', 'Mahasiswa57', '76a2173be6393254e72ffa4d6df1030a'),
(40, '202310370311482', 'Mona Hoshinova', '630623a9900dec6ef92ebe62dd64d994'),
(41, '202310370311164', 'Mahasiswa6', '76a2173be6393254e72ffa4d6df1030a'),
(42, '202310370311645', 'Mahasiswa96', '76a2173be6393254e72ffa4d6df1030a'),
(43, '202310370311318', 'Mahasiswa11', '76a2173be6393254e72ffa4d6df1030a'),
(44, '202310370311763', 'Mahasiswa46', '76a2173be6393254e72ffa4d6df1030a'),
(45, '202310370311467', 'Mahasiswa5', '76a2173be6393254e72ffa4d6df1030a'),
(46, '202310370311484', 'Mahasiswa2', '76a2173be6393254e72ffa4d6df1030a'),
(47, '202310370311167', 'Mahasiswa72', '76a2173be6393254e72ffa4d6df1030a'),
(48, '202310370311712', 'Mahasiswa35', '76a2173be6393254e72ffa4d6df1030a'),
(49, '202310370311181', 'Mahasiswa30', '76a2173be6393254e72ffa4d6df1030a'),
(50, '202310370311324', 'Mahasiswa83', '76a2173be6393254e72ffa4d6df1030a'),
(51, '202310370311696', 'Ayunda Risu', '5f200d2bd7ad9a7652cfde4aeb8a8fbc');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mahasiswa_matakuliah`
--

CREATE TABLE `mahasiswa_matakuliah` (
  `id` int(11) NOT NULL,
  `mahasiswa_id` int(11) DEFAULT NULL,
  `matakuliah_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `mahasiswa_matakuliah`
--

INSERT INTO `mahasiswa_matakuliah` (`id`, `mahasiswa_id`, `matakuliah_id`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1),
(6, 6, 1),
(7, 7, 1),
(8, 8, 1),
(9, 9, 1),
(10, 10, 1),
(11, 11, 1),
(12, 12, 1),
(13, 13, 1),
(14, 14, 1),
(15, 15, 1),
(16, 16, 1),
(17, 17, 1),
(18, 18, 1),
(19, 19, 1),
(20, 20, 1),
(32, 21, 2),
(33, 22, 2),
(34, 23, 2),
(35, 24, 2),
(36, 25, 2),
(37, 26, 2),
(38, 27, 2),
(39, 28, 2),
(40, 29, 2),
(41, 30, 2),
(42, 31, 2),
(43, 32, 2),
(44, 33, 2),
(45, 34, 2),
(46, 35, 2),
(47, 36, 2),
(48, 37, 2),
(49, 38, 2),
(50, 39, 2),
(51, 40, 2),
(63, 41, 3),
(64, 42, 3),
(65, 43, 3),
(66, 44, 3),
(67, 45, 3),
(68, 46, 3),
(69, 47, 3),
(70, 48, 3),
(71, 49, 3),
(72, 50, 3),
(79, 2, 2),
(80, 3, 3),
(81, 4, 1),
(82, 5, 2),
(83, 6, 3),
(84, 7, 1),
(85, 8, 2),
(86, 9, 3),
(87, 10, 1),
(88, 11, 2),
(89, 12, 3),
(90, 13, 1),
(91, 14, 2),
(92, 15, 3),
(93, 16, 1),
(94, 17, 2),
(95, 18, 3),
(97, 20, 2),
(98, 21, 3),
(99, 22, 1),
(100, 23, 2),
(101, 24, 3),
(102, 25, 1),
(103, 26, 2),
(104, 27, 3),
(105, 28, 1),
(106, 29, 2),
(107, 30, 3),
(108, 1, 2),
(109, 1, 3),
(110, 1, 4),
(112, 1, 6),
(120, 2, 15);

-- --------------------------------------------------------

--
-- Struktur dari tabel `matakuliah`
--

CREATE TABLE `matakuliah` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `sks` int(11) NOT NULL,
  `kelas` varchar(16) NOT NULL,
  `ruang` varchar(30) NOT NULL,
  `jam` varchar(30) NOT NULL,
  `active` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `matakuliah`
--

INSERT INTO `matakuliah` (`id`, `nama`, `sks`, `kelas`, `ruang`, `jam`, `active`) VALUES
(1, 'PBO', 4, 'J', '605 (GKB IV)', 'Jumat 14.25 - 17.10 WIB', 1),
(2, 'Kalkulus', 4, 'J', '211 (GKB I)', 'Kamis 16.00 - 21.00 WIB', 0),
(3, 'Logika Komputasi', 2, 'J', '611 (GKB III)', 'Selasa 07.00 - 08.40 WIB', 0),
(4, 'Sistem Informasi', 2, 'J', '215 (GKB I)', 'Senin 08.40 - 10.20 WIB', 1),
(5, 'Penulisan Ilmiah', 2, 'J', '605 (GKB IV)', 'Jumat 12.45 - 14.25 WIB', 0),
(6, 'FLSP', 2, 'J', '401 (GKB II)', 'Senin 16.20 - 18.00 WIB', 0),
(7, 'game', 2, 'Z', '001 (GKB V)', 'Z', 0),
(8, 'game', 2, 'F', '001 (GKB VI)', 'Senin 18.20 - 18.30 WIB', 0),
(14, 'Sihir', 4, 'J', '069 (GKB X)', 'Minggu 00.00 - 09.15 WITA', 0),
(15, 'Peperangan', 2, 'J', '609 (GKB VI)', 'Rabu 12.15-14.00 WIB', 1);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `absensi`
--
ALTER TABLE `absensi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `mahasiswa_id` (`mahasiswa_id`),
  ADD KEY `matakuliah_id` (`matakuliah_id`);

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `dosen`
--
ALTER TABLE `dosen`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `dosen_matakuliah`
--
ALTER TABLE `dosen_matakuliah`
  ADD PRIMARY KEY (`id`),
  ADD KEY `dosen_id` (`dosen_id`),
  ADD KEY `matakuliah_id` (`matakuliah_id`);

--
-- Indeks untuk tabel `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `mahasiswa_matakuliah`
--
ALTER TABLE `mahasiswa_matakuliah`
  ADD PRIMARY KEY (`id`),
  ADD KEY `mahasiswa_id` (`mahasiswa_id`),
  ADD KEY `matakuliah_id` (`matakuliah_id`);

--
-- Indeks untuk tabel `matakuliah`
--
ALTER TABLE `matakuliah`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `absensi`
--
ALTER TABLE `absensi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT untuk tabel `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `dosen`
--
ALTER TABLE `dosen`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `dosen_matakuliah`
--
ALTER TABLE `dosen_matakuliah`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT untuk tabel `log`
--
ALTER TABLE `log`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=237;

--
-- AUTO_INCREMENT untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT untuk tabel `mahasiswa_matakuliah`
--
ALTER TABLE `mahasiswa_matakuliah`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=121;

--
-- AUTO_INCREMENT untuk tabel `matakuliah`
--
ALTER TABLE `matakuliah`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `absensi`
--
ALTER TABLE `absensi`
  ADD CONSTRAINT `absensi_ibfk_1` FOREIGN KEY (`mahasiswa_id`) REFERENCES `mahasiswa` (`id`),
  ADD CONSTRAINT `absensi_ibfk_2` FOREIGN KEY (`matakuliah_id`) REFERENCES `matakuliah` (`id`);

--
-- Ketidakleluasaan untuk tabel `dosen_matakuliah`
--
ALTER TABLE `dosen_matakuliah`
  ADD CONSTRAINT `dosen_matakuliah_ibfk_1` FOREIGN KEY (`dosen_id`) REFERENCES `dosen` (`id`),
  ADD CONSTRAINT `dosen_matakuliah_ibfk_2` FOREIGN KEY (`matakuliah_id`) REFERENCES `matakuliah` (`id`);

--
-- Ketidakleluasaan untuk tabel `mahasiswa_matakuliah`
--
ALTER TABLE `mahasiswa_matakuliah`
  ADD CONSTRAINT `mahasiswa_matakuliah_ibfk_1` FOREIGN KEY (`mahasiswa_id`) REFERENCES `mahasiswa` (`id`),
  ADD CONSTRAINT `mahasiswa_matakuliah_ibfk_2` FOREIGN KEY (`matakuliah_id`) REFERENCES `matakuliah` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
