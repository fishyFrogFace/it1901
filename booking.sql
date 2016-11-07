CREATE TABLE paygrade (
  paygradeID SERIAL,
  perHour INT NOT NULL,
  PRIMARY KEY(paygradeID));

CREATE TYPE role AS ENUM
('manager', 'tech', 'organizer', 'booker', 'administrator');

CREATE TABLE employee (
  employeeID SERIAL,
  userName TEXT NOT NULL,
  name TEXT NOT NULL,
  password TEXT NOT NULL,
  accountType ROLE NOT NULL,
  paygradeID INT REFERENCES paygrade(paygradeID)
  ON DELETE SET NULL,
  PRIMARY KEY(employeeID));

CREATE TABLE stage (
  stageID SERIAL,
  name TEXT NOT NULL,
  maxAudience INT NOT NULL,
  price INT NOT NULL,
  PRIMARY KEY(stageID));

CREATE TYPE musicGenre AS ENUM
('rap', 'pop', 'rock', 'blues', 'alternative',
  'country', 'electronic', 'dance', 'classical',
  'r&b/soul', 'reggae', 'jazz', 'metal');

CREATE TABLE artist (
  artistID SERIAL,
  name TEXT NOT NULL,
  email TEXT,
  fee INT,
  genre musicGenre NOT NULL,
  accomodationCost INT,
  PRIMARY KEY(artistID));

CREATE TABLE artistinfo (
  infoID SERIAL,
  spotify INT,
  albumsSold INT,
  concerts TEXT,
  artistID INT NOT NULL REFERENCES artist(artistID)
  ON DELETE CASCADE,
  PRIMARY KEY(infoID));

CREATE TYPE offerState AS ENUM
('unfinished', 'pending', 'declined',
  'accepted', 'sent', 'cancelled', 'booked');

CREATE TABLE offer (
  offerID SERIAL,
  state offerState NOT NULL,
  bookerID INT NOT NULL REFERENCES employee(employeeID),
  PRIMARY KEY(offerID));

CREATE TABLE changedby (
  timeChanged TIMESTAMP NOT NULL,
  offerID INT NOT NULL REFERENCES offer(offerID)
  ON DELETE CASCADE,
  employeeID INT REFERENCES employee(employeeID)
  ON DELETE CASCADE);

CREATE TABLE email (
  emailID SERIAL,
  emailSubject TEXT NOT NULL,
  emailBody TEXT NOT NULL,
  offerID INT NOT NULL REFERENCES offer(offerID)
  ON DELETE CASCADE);

CREATE TABLE concert (
  concertID SERIAL,
  startDate DATE NOT NULL,
  duration INT,
  ticketPrice INT,
  ticketsSold INT,
  artistID INT NOT NULL REFERENCES artist(artistID)
  ON DELETE CASCADE,
  offerID INT NOT NULL REFERENCES offer(offerID)
  ON DELETE CASCADE,
  stageID INT NOT NULL REFERENCES stage(stageID)
  ON DELETE CASCADE,
  PRIMARY KEY(concertID));
  
CREATE TABLE requirement (
  requirementID SERIAL,
  item TEXT NOT NULL,
  description TEXT NOT NULL,
  comment TEXT NOT NULL,
  PRIMARY KEY(requirementID));

CREATE TABLE needed (
  requirementID INT REFERENCES requirement(requirementID)
  ON DELETE CASCADE,
  concertID INT NOT NULL REFERENCES concert(concertID)
  ON DELETE CASCADE);

CREATE TYPE techType AS ENUM
('lyd', 'lys', 'rigging');

CREATE TABLE assigned (
  assignedID SERIAL,
  hours INT,
  type techType NOT NULL,
  employeeID INT REFERENCES employee(employeeID)
  ON DELETE CASCADE,
  concertID INT REFERENCES concert(concertID)
  ON DELETE CASCADE,
  PRIMARY KEY(assignedID));

CREATE TABLE budgetpost (
  budgetPostID SERIAL,
  description TEXT NOT NULL,
  price INT NOT NULL,
  expense BOOLEAN NOT NULL,
  concertID INT REFERENCES concert(concertID)
  ON DELETE CASCADE,
  PRIMARY KEY(budgetPostID));