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
	'tech', 4),
	(DEFAULT, 'manager', 'Frank Bakke',
	'$2a$10$IjOmy7phDesVDYcxnTNJcOCEHb1/.OISWcpDCYpFyU./yENcGFkTK',
    'manager', 1);

INSERT INTO stage VALUES
    (DEFAULT, 'Storsalen', 1000, 10000),
    (DEFAULT, 'Klubben', 400, 5000),
    (DEFAULT, 'Knaus', 200, 7000),
    (DEFAULT, 'Selskapssiden', 50, 3000),
    (DEFAULT, 'Strossa', 150, 8000);

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

INSERT INTO requirement VALUES
	(DEFAULT, 'Miksebord', 'Miksebordet må være av god kvalitet (Midas XL200, el. tilsv.) og ha minimum 32 innganger med 8 aux. send, 4
		pre fader og 4 post fader. Alle innganger må i tillegg ha 4 bånds parametrisk equalizer og 48V phantommating.
		Miksebordet må ha insertmuligheter på alle kanaler, samt belysning.', 'Avtalt med Reidar');

INSERT INTO neededby VALUES
	(1, 1),
	(3, 1);
	
INSERT INTO artistinfo VALUES
	(DEFAULT, 4500, 20000, 'Spilt på Rockefeller tidligere. Utsolgt konsert.', 1),
	(DEFAULT, 2000, 30000, 'Spiller på Familien annenhver helg.', 2),
	(DEFAULT, 200000, 65000, 'Ikke holdt konsert i Norge tidligere.', 3),
	(DEFAULT, 145678, 54434, 'Spiller ofte på konsoller rundt om i landet.', 4),
	(DEFAULT, 5004549, 600000, 'Har ikke vært i Norge på mange år.', 5),
	(DEFAULT, 20000, 345, 'Spilte på en middels stor scene på Hovefestivalen 2016. Holder ellers konserter på utesteder.', 6);

INSERT INTO offer VALUES
	(DEFAULT, 'pending', 2),
	(DEFAULT, 'accepted', 2),
	(DEFAULT, 'pending', 2),
	(DEFAULT, 'pending', 2),
	(DEFAULT, 'pending', 2),
	(DEFAULT, 'pending', 2),
	(DEFAULT, 'pending', 2),
	(DEFAULT, 'declined', 2),
	(DEFAULT, 'accepted', 2),
	(DEFAULT, 'pending', 2),
	(DEFAULT, 'pending', 2);
	
INSERT INTO changedby VALUES
	('2004-10-19 10:23:54', 2, 1),
	('2004-10-19 10:23:54', 2, 2);

INSERT INTO email VALUES
	(DEFAULT, 'Tilbud om spillejobb', '1. Hei,\nVi ønsker herved å gi et tilbud til dere, \nbeskrivelse av konsertlokale, arrangement og lokale. \nHilsen\nAnsatt', 1),
	(DEFAULT, 'Tilbud om spillejobb', '2. Hei,\nVi ønsker herved å gi et tilbud til dere, \nbeskrivelse av konsertlokale, arrangement og lokale. \nHilsen\nAnsatt', 2),
	(DEFAULT, 'Tilbud om spillejobb', '3. Hei,\nVi ønsker herved å gi et tilbud til dere, \nbeskrivelse av konsertlokale, arrangement og lokale. \nHilsen\nAnsatt', 3),
	(DEFAULT, 'Tilbud om spillejobb', '4. Hei,\nVi ønsker herved å gi et tilbud til dere, \nbeskrivelse av konsertlokale, arrangement og lokale. \nHilsen\nAnsatt', 4),
	(DEFAULT, 'Tilbud om spillejobb', '5. Hei,\nVi ønsker herved å gi et tilbud til dere, \nbeskrivelse av konsertlokale, arrangement og lokale. \nHilsen\nAnsatt', 5),
	(DEFAULT, 'Tilbud om spillejobb', '6. Hei,\nVi ønsker herved å gi et tilbud til dere, \nbeskrivelse av konsertlokale, arrangement og lokale. \nHilsen\nAnsatt', 6),
	(DEFAULT, 'Tilbud om spillejobb', '7. Hei,\nVi ønsker herved å gi et tilbud til dere, \nbeskrivelse av konsertlokale, arrangement og lokale. \nHilsen\nAnsatt', 7),
	(DEFAULT, 'Tilbud om spillejobb', '8. Hei,\nVi ønsker herved å gi et tilbud til dere, \nbeskrivelse av konsertlokale, arrangement og lokale. \nHilsen\nAnsatt', 8),
	(DEFAULT, 'Tilbud om spillejobb', '9. Hei,\nVi ønsker herved å gi et tilbud til dere, \nbeskrivelse av konsertlokale, arrangement og lokale. \nHilsen\nAnsatt', 9),
	(DEFAULT, 'Tilbud om spillejobb', '10. Hei,\nVi ønsker herved å gi et tilbud til dere, \nbeskrivelse av konsertlokale, arrangement og lokale. \nHilsen\nAnsatt', 10),
	(DEFAULT, 'Tilbud om spillejobb', '11. Hei,\nVi ønsker herved å gi et tilbud til dere, \nbeskrivelse av konsertlokale, arrangement og lokale. \nHilsen\nAnsatt', 11);

INSERT INTO concert VALUES
	(DEFAULT, '2016-09-23', 5, 150, 326, 4, 1, 1),
	(DEFAULT, '2016-12-24', 6, 200, 37, 4, 2, 3),
	(DEFAULT, '2016-08-30', 4, 180, 260, 3, 3, 1),
	(DEFAULT, '2016-10-03', 6, 200, 0, 1, 4, 2),
	(DEFAULT, '2016-10-04', 3, 100, 47, 2, 5, 3),
	(DEFAULT, '2016-10-06', 4, 300, 0, 4, 6, 1),
	(DEFAULT, '2016-10-07', 5, 100, 0, 3, 7, 2),
	(DEFAULT, '2016-10-07', 6, 150, 0, 5, 8, 2),
	(DEFAULT, '2016-10-08', 4, 175, 459, 1, 9, 1),
	(DEFAULT, '2016-10-08', 4, 200, 0, 2, 10, 1),
	(DEFAULT, '2016-10-08', 4, 350, 0, 4, 11, 1);

INSERT INTO assigned VALUES
	(DEFAULT, 7, 'lyd', 4, 1),
	(DEFAULT, 5, 'rigging', 3, 1),
	(DEFAULT, 5, 'lys', 4, 2),
	(DEFAULT, 5, 'lyd', 4, 3),
	(DEFAULT, 5, 'lyd', 4, 4),
	(DEFAULT, 5, 'rigging', 4, 7),
	(DEFAULT, 8, 'lyd', 3, 2),
	(DEFAULT, 2, 'rigging', 2, 2);

INSERT INTO budgetPost VALUES
	(DEFAULT, 'Leie av utstyr', 4000, TRUE, 1),
	(DEFAULT, 'Sponsorinntekt - McDonalds', 7000, FALSE, 1);