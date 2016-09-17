INSERT INTO paygrade VALUES
    (DEFAULT, 150),
    (DEFAULT, 200),
    (DEFAULT, 250),
    (DEFAULT, 300);

INSERT INTO employee VALUES
    (DEFAULT, 'acme', 'Wile E. Coyote', 
	'$2a$10$IjOmy7phDesVDYcxnTNJcOCEHb1/.OISWcpDCYpFyU./yENcGFkTK', 
	'administrator', 3),
    (DEFAULT, 'meepMeep', 'Road Runner', 
	'$2a$10$8xeq0y1bwjXRCPtyb0FZ8e/0ZgPOvoZGxANw8kU3Ad2aMLWyKKDyO', 
	'booker', 2),
    (DEFAULT, 'greenCarrot', 'Bugs Bunny', 
	'$2a$10$YACSS2P754s2l9euPn8IneYKcudp/jM9qpHWRZENhHn6krvcB6Nfu', 
	'organizer', 1),
    (DEFAULT, 'mars', 'Marvin Martian', 
	'$2a$10$IjOmy7phDesVDYcxnTNJcOCEHb1/.OISWcpDCYpFyU./yENcGFkTK', 
	'tech', 4);

INSERT INTO stage VALUES
    (DEFAULT, 'Storsalen', 1000),
    (DEFAULT, 'Klubben', 400),
    (DEFAULT, 'Knaus', 200),
    (DEFAULT, 'Selskapssiden', 50),
    (DEFAULT, 'Strossa', 150);

INSERT INTO artist VALUES
    (DEFAULT, 'Michigan J. Frog', 'prosjektarbeidartist@mail.com', 3000, 
	'classical', 3000),
    (DEFAULT, 'Deathgore', 'prosjektarbeidartist2@mail.com', 4000, 
	'metal', 3000),
    (DEFAULT, 'Money$$', 'prosjektarbeidartist3@mail.com', 10000, 
	'rap', 5000),
    (DEFAULT, 'Super Mario', 'prosjektarbeidartist4@mail.com', 2000, 
	'electronic', 1000),
    (DEFAULT, 'Bob Marley', 'prosjektarbeidartist5@mail.com', 20000, 
	'reggae', 4000),
    (DEFAULT, 'Guy with guitar', 'prosjektarbeidartist6@mail.com', 7000, 
	'country', 2000);
	
INSERT INTO artistinfo VALUES
	(DEFAULT, 4500, 20000, 'Spilt på Rockefeller tidligere. Utsolgt konsert.', 1),
	(DEFAULT, 2000, 30000, 'Spiller på Familien annenhver helg.', 2),
	(DEFAULT, 200000, 65000, 'Ikke holdt konsert i Norge tidligere.', 3),
	(DEFAULT, 145678, 54434, 'Spiller ofte på konsoller rundt om i landet.', 4),
	(DEFAULT, 5004549, 600000, 'Har ikke vært i Norge på mange år.', 5),
	(DEFAULT, 20000, 345, 'Spilte på en middels stor scene på Hovefestivalen 2016. Holder ellers konserter på utesteder.', 6);

INSERT INTO offer VALUES
	(DEFAULT, 'pending', 2, NULL);

INSERT INTO email VALUES
	(DEFAULT, 'Tilbud om spillejobb', 'Hei,\nVi ønsker herved å gi et tilbud til dere, blablabla', 1);

INSERT INTO event VALUES
	(DEFAULT, '2016-09-23', 5, 150, NULL, 4, 1, 1);

INSERT INTO assigned VALUES
	(DEFAULT, 7, 4, 1),
	(DEFAULT, 5, 3, 1);

INSERT INTO budgetPost VALUES
	(DEFAULT, 'Leie av utstyr', 4000, TRUE, 1),
	(DEFAULT, 'Sponsorinntekt - McDonalds', 7000, FALSE, 1);