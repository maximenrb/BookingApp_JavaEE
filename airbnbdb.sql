CREATE DATABASE IF NOT EXISTS `airbnbdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `airbnbdb`;

CREATE TABLE `user` (
  `mail` varchar(255) NOT NULL,
  `balance` double NOT NULL,
  `date` date NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `user` (`mail`, `balance`, `date`, `firstname`, `password`, `name`, `phone`, `type`) VALUES
('admin@admin.com', 0, '2021-03-10', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'admin', '01 23 45 67 89', 'Admin');