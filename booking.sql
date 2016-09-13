CREATE TABLE paygrade (
    paygradeID SERIAL,
    perHour INT NOT NULL,
    PRIMARY KEY(paygradeID));

CREATE TYPE role AS ENUM 
    ('tech', 'organizer', 'booker', 'administrator');

CREATE TABLE employee (
    employeeID SERIAL,
    userName TEXT NOT NULL,
    name TEXT NOT NULL,
    password TEXT NOT NULL,
    accountType ROLE NOT NULL,
    paygradeID INT REFERENCES paygrade(paygradeID),
    PRIMARY KEY(employeeID));

CREATE TABLE stage (
    stageID SERIAL,
    name TEXT,
    maxAudience INT NOT NULL,
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
    artistID INT NOT NULL REFERENCES artist(artistID),
    PRIMARY KEY(infoID));

CREATE TYPE offerState AS ENUM
    ('unfinished', 'pending', 'accepted', 
    'declined', 'sent', 'returned', 'archived');

CREATE TABLE offer (
    offerID SERIAL,
    emailSubject TEXT NOT NULL,
    emailBody TEXT NOT NULL,
    date DATE NOT NULL,
    state offerState NOT NULL,
    artistID INT NOT NULL REFERENCES artist(artistID),
    bookerID INT NOT NULL REFERENCES employee(employeeID),
    managerID INT REFERENCES employee(employeeID),
    stageID INT NOT NULL REFERENCES stage(stageID),
    PRIMARY KEY(offerID));

CREATE TABLE event (
    eventID SERIAL,
    startDate DATE NOT NULL,
    duration INT,
    ticketPrice INT,
    ticketsSold INT,
    artistID INT NOT NULL REFERENCES artist(artistID),
    offerID INT REFERENCES offer(offerID),
    stageID INT NOT NULL REFERENCES stage(stageID),
    PRIMARY KEY(eventID));

CREATE TABLE assigned (
    assignedID SERIAL,
    hours INT,
    employeeID INT REFERENCES employee(employeeID)
    ON DELETE CASCADE,
    eventID INT REFERENCES event(eventID)
    ON DELETE CASCADE,
    PRIMARY KEY(assignedID));
    
CREATE TABLE budgetPost (
    budgetPostID SERIAL,
    description TEXT NOT NULL,
    price INT NOT NULL,
	expense BOOLEAN NOT NULL,
    eventID INT REFERENCES event(eventID),
    PRIMARY KEY(budgetPostID));