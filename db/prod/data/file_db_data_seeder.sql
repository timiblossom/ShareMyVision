use file_db;

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,
                            'Rome Visit','romevisit','SMV group event', 
                            'http://img.sharemyvision.com/abc/Rome/a/castelsantangelo.thumbnail.jpg', 
                            'rome, travel, europe, italy',
                            1,
                            1,
                            'active',
                            true,
                            '2011-03-12 05:57:36',
                            '2010-03-12 00:57:35',
                            '2010-03-12 05:57:35',
                            0,
                            '2010-05-12 05:57:35');
                            
INSERT INTO `event` VALUES (2,
                            'Paris Visit',
                            'parisvisit',
                            'SMV group event', 
                            'http://img.sharemyvision.com/abc/Paris/a/ParisBridge.thumbnail.jpg', 
                            'paris, europe, france, french, travel',
                            1,
                            1,
                            'active',
                            true,
                            '2011-03-12 05:57:36',
                            '2010-05-12 00:57:35',
                            '2010-05-12 05:57:35',
                            0,
                            '2010-05-12 05:57:35');

/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;




LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,
                           'Castel Santagelo',
                           'castelsantagelo',
                           'Defense Castle of the Popes',
                           'rome, pope, vatican',
                           1,
                           1,
                           'active',
                           true,
                           640,
                           427,
                           'jpg',
                           65000,
                           NULL,
                           1.1,
                           1.1,
                           1.0, 
                           'Rome, Italy',
                           NULL,
                           'http://img.sharemyvision.com/abc/Rome/castelsantangelo.jpg',
                           'http://img.sharemyvision.com/abc/Rome/a/castelsantangelo.thumbnail.jpg',
                           'http://img.sharemyvision.com/abc/Rome/a/castelsantangelo.jpg',
                           'http://img.sharemyvision.com/abc/Rome/a/castelsantangelo.jpg',
                           false,
                           1,
                           1,
                           '2010-03-12 00:57:37',
                           '2010-03-12 05:57:37',
                           '2010-03-12 05:57:37',
                           0,
                           '2010-03-12 05:57:37');
                           
INSERT INTO `item` VALUES (2,
                           'Piazza Avona',
                           'piazzaavona',
                           'Piazza Avona in Rome',
                           'rome, piazza, avona, europe, travel',
                           1,
                           1,
                           'active', 
                           true,
                           640,
                           427,
                           'jpg',
                           66000,
                           NULL,
                           1.1,
                           1.1,
                           1.0, 
                           'Rome, Italy',
                           NULL,
                           'http://img.sharemyvision.com/abc/Rome/piazzanavona.jpg',
                           'http://img.sharemyvision.com/abc/Rome/a/piazzanavona.thumbnail.jpg',
                           'http://img.sharemyvision.com/abc/Rome/a/piazzanavona.jpg',
                           'http://img.sharemyvision.com/abc/Rome/a/piazzanavona.jpg',
                           false,
                           1,
                           1,
                           '2010-03-12 00:57:37',
                           '2010-03-12 05:57:37',
                           '2010-03-12 00:57:37',
                           0,
                           '2010-03-12 05:57:37');
                           
INSERT INTO `item` VALUES (3,'
                           Pantheon',
                           'pantheon',
                           'Ancient Pantheon of Rome',
                           'rome, pantheon, europe',
                           1,
                           1,
                           'active',
                           true,
                           640,
                           427,
                           'jpg',
                           72000, 
                           NULL,
                           1.1,
                           1.1,
                           1.0, 
                           'Rome, Italy',
                           NULL,
                           'http://img.sharemyvision.com/abc/Rome/Pantheon.jpg',
                           'http://img.sharemyvision.com/abc/Rome/a/Pantheon.thumbnail.jpg',
                           'http://img.sharemyvision.com/abc/Rome/a/Pantheon.jpg',
                           'http://img.sharemyvision.com/abc/Rome/a/Pantheon.jpg',
                           false,
                           1,
                           1,
                           '2010-03-12 00:57:37',
                           '2010-03-12 05:57:37',
                           '2010-03-12 00:57:37',
                           0,
                           '2010-03-12 05:57:37');
                           
INSERT INTO `item` VALUES (4,
                           'Fontana de Trevi',
                           'fontanadetrevi',
                           'Fontana de Trevi',
                           'rome, fontana, trevi, europe',
                           1,
                           1,
                           'active',
                           true,
                           640,
                           427,
                           'jpg',
                           75000,
                           NULL,
                           1.1,
                           1.1,
                           1.0, 
                           'Rome, Italy',
                           NULL,
                           'http://img.sharemyvision.com/abc/Rome/fontanadetrevi.jpg',
                           'http://img.sharemyvision.com/abc/Rome/a/fontanadetrevi.thumbnail.jpg',
                           'http://img.sharemyvision.com/abc/Rome/a/fontanadetrevi.jpg',
                           'http://img.sharemyvision.com/abc/Rome/a/fontanadetrevi.jpg',
                           false,
                           1,
                           1,
                           '2010-03-12 00:57:37',
                           '2010-03-12 05:57:37',
                           '2010-03-12 00:57:37',
                           0,
                           '2010-03-12 05:57:37');
                           
INSERT INTO `item` VALUES (5,
                           'Colosseum',
                           'colosseum',
                           'Colosseum',
                           'colosseum, rome, roman, gladiator',
                           1,
                           1,
                           'active',
                           true,
                           640,
                           427,
                           'jpg',
                           68000,
                           NULL,
                           1.1,
                           1.1,
                           1.0, 
                           'Rome, Italy',
                           NULL,
                           'http://img.sharemyvision.com/abc/Rome/colosseum.jpg',
                           'http://img.sharemyvision.com/abc/Rome/a/colosseum.thumbnail.jpg',
                           'http://img.sharemyvision.com/abc/Rome/a/colosseum.jpg',
                           'http://img.sharemyvision.com/abc/Rome/a/colosseum.jpg',
                           false,
                           1,
                           1,
                           '2010-03-12 00:57:37',
                           '2010-03-12 05:57:37',
                           '2010-03-12 00:57:37',
                           0,
                           '2010-03-12 05:57:37');
                           
INSERT INTO `item` VALUES (6,
                           'Annie in the Sink',
                           '',
                           '',
                           '',
                           1,
                           1,
                           'active',
                           true,
                           640,
                           427,
                           'jpg',
                           140000,
                           NULL,
                           1.1,
                           1.1,
                           1.0, 
                           'Rome, Italy',
                           NULL,
                           'http://img.sharemyvision.com/abc/Rome/AnnieInTheSink.jpg',
                           'http://img.sharemyvision.com/abc/Rome/a/AnnieInTheSink.thumbnail.jpg',
                           'http://img.sharemyvision.com/abc/Rome/a/AnnieInTheSink.jpg',
                           'http://img.sharemyvision.com/abc/Rome/a/AnnieInTheSink.jpg',
                           false,
                           1,
                           1,
                           '2010-03-12 00:57:37',
                           '2010-03-12 05:57:37',
                           '2010-03-12 00:57:37',
                           0,
                           '2010-03-12 05:57:37');


INSERT INTO `item` VALUES (7,
                           'Bridge of Paris',
                           'parisbridge','Bridge of Paris',
                           'paris,bridge, europe, travel',
                           1,
                           1,
                           'active',
                           true,
                           640,
                           427,
                           'jpg',
                           49000,
                           NULL,
                           1.1,
                           1.1,
                           1.0, 
                           'Paris, France',
                           NULL,
                           'http://img.sharemyvision.com/abc/Paris/ParisBridge.jpg',
                           'http://img.sharemyvision.com/abc/Paris/a/ParisBridge.thumbnail.jpg',
                           'http://img.sharemyvision.com/abc/Paris/a/ParisBridge.jpg',
                           'http://img.sharemyvision.com/abc/Paris/a/ParisBridge.jpg',
                           false,
                           1,
                           2,
                           '2010-03-12 00:57:37',
                           '2010-03-12 05:57:37',
                           '2010-03-12 00:57:37',
                           0,
                           '2010-03-12 05:57:37');
                           
INSERT INTO `item` VALUES (8,
                           'Notre Dame Catheral','notredamecatheral','Notre Dame Catheral',
                           'notre, dame, catheral, paris, church, christian, catholic',
                           1,
                           1,
                           'active',
                           true,
                           640,
                           427,
                           'jpg',
                           47000,
                           NULL,
                           1.1,
                           1.1,
                           1.0, 
                           'Paris, France',
                           NULL,
                           'http://img.sharemyvision.com/abc/Paris/NotreDameCatheral.jpg',
                           'http://img.sharemyvision.com/abc/Paris/a/NotreDameCatheral.thumbnail.jpg',
                           'http://img.sharemyvision.com/abc/Paris/NotreDameCatheral.jpg',
                           'http://img.sharemyvision.com/abc/Paris/NotreDameCatheral.jpg',
                           false,
                           1,
                           2,
                           '2010-03-12 00:57:37',
                           '2010-03-12 05:57:37',
                           '2010-03-12 00:57:37',
                           0,
                           '2010-03-12 05:57:37');
                           
INSERT INTO `item` VALUES (9,
                           'Musee de Louvre',
                           'louvre','Musee de Louvre',
                           'musee, museum, louvre, paris, art',
                           1,
                           1,
                           'active',
                           true,
                           640,
                           427,
                           'jpg',
                           56000,
                           NULL,
                           1.1,
                           1.1,
                           1.0, 
                           'Paris, France',NULL,
                           'http://img.sharemyvision.com/abc/Paris/MuseeDeLouvre.jpg',
                           'http://img.sharemyvision.com/abc/Paris/a/MuseeDeLouvre.thumbnail.jpg',
                           'http://img.sharemyvision.com/abc/Paris/a/MuseeDeLouvre.jpg',
                           'http://img.sharemyvision.com/abc/Paris/a/MuseeDeLouvre.jpg',
                           false,
                           1,
                           2,
                           '2010-03-12 00:57:37',
                           '2010-03-12 05:57:37',
                           '2010-03-12 00:57:37',
                           0,
                           '2010-03-12 05:57:37');
                           
INSERT INTO `item` VALUES (10,
                           'Eiffel Tower',
                           'eiffeltower',
                           'Eiffel Tower',
                           'eiffel, tower, paris, europe, attraction',
                           1,
                           1,
                           'active',
                           true,
                           640,
                           427,
                           'jpg',
                           40000,
                           NULL,
                           1.1,
                           1.1,
                           1.0, 
                           'Paris, France',
                           NULL,
                           'http://img.sharemyvision.com/abc/Paris/EiffelTower.jpg',
                           'http://img.sharemyvision.com/abc/Paris/a/EiffelTower.thumbnail.jpg',
                           'http://img.sharemyvision.com/abc/Paris/a/EiffelTower.jpg',
                           'http://img.sharemyvision.com/abc/Paris/a/EiffelTower.jpg',
                           false,
                           1,
                           2,
                           '2010-03-12 00:57:37',
                           '2010-03-12 05:57:37',
                           '2010-03-12 00:57:37',
                           0,
                           '2010-03-12 05:57:37');


/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `file_system` WRITE;
INSERT INTO `file_system` VALUES (1, 1, 'img1.sharemyvision.com', 'abc', '', '', 678777, 2, 10, '2010-03-12 05:57:37',0,'2010-03-12 00:57:37'); 

/*!40000 ALTER TABLE `file_system` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `file_policy` WRITE;
INSERT INTO `file_policy` VALUES (1, 1, 'num_thumbnails_for_width', '2', '2010-03-12 05:57:37', 0,'2010-03-12 00:57:37');
INSERT INTO `file_policy` VALUES (2, 1, 'width_1', '75', '2010-03-12 05:57:37', 0,'2010-03-12 00:57:37'); 
INSERT INTO `file_policy` VALUES (3, 1, 'width_2', '500', '2010-03-12 05:57:37', 0,'2010-03-12 00:57:37'); 
INSERT INTO `file_policy` VALUES (4, 1, 'num_thumbnails_for_height', '1', '2010-03-12 05:57:37', 0,'2010-03-12 00:57:37');
INSERT INTO `file_policy` VALUES (5, 1, 'height_1', '113', '2010-03-12 05:57:37', 0,'2010-03-12 00:57:37');

/*!40000 ALTER TABLE `file_policy` ENABLE KEYS */;
UNLOCK TABLES;
