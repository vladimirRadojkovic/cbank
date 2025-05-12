-- Create necessary tables for the bank application

-- User table
CREATE TABLE IF NOT EXISTS Korisnik (
    korisnickoIme VARCHAR(50) PRIMARY KEY,
    lozinka VARCHAR(255) NOT NULL,
    aktivan BOOLEAN DEFAULT TRUE
);

-- User data table
CREATE TABLE IF NOT EXISTS KorisnikPodaci (
    korisnik VARCHAR(50) PRIMARY KEY,
    ime VARCHAR(50) NOT NULL,
    prezime VARCHAR(50) NOT NULL,
    jmbg VARCHAR(13) UNIQUE,
    adresa VARCHAR(255),
    mesto VARCHAR(50),
    brojTelefona VARCHAR(20),
    email VARCHAR(100),
    slika BLOB,
    operater VARCHAR(50),
    FOREIGN KEY (korisnik) REFERENCES Korisnik(korisnickoIme)
);

-- User roles table
CREATE TABLE IF NOT EXISTS KorisnikRoles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    korisnickoIme VARCHAR(50) NOT NULL,
    rola VARCHAR(20) NOT NULL,
    FOREIGN KEY (korisnickoIme) REFERENCES Korisnik(korisnickoIme)
);

-- Card table
CREATE TABLE IF NOT EXISTS Kartica (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tekuciRacun VARCHAR(50) NOT NULL,
    saldo DECIMAL(15,2) DEFAULT 0,
    ogranicenje DECIMAL(15,2),
    naziv VARCHAR(100),
    opis TEXT,
    rokVazenja DATE,
    korisnickoIme VARCHAR(50) NOT NULL,
    FOREIGN KEY (korisnickoIme) REFERENCES Korisnik(korisnickoIme)
);

-- Credits table
CREATE TABLE IF NOT EXISTS Krediti (
    vrstaKredita VARCHAR(50) PRIMARY KEY,
    nominalnaGodisnjaStopa DECIMAL(5,2) NOT NULL,
    valuta VARCHAR(3) DEFAULT 'RSD'
);

-- Payments table
CREATE TABLE IF NOT EXISTS Placanja (
    id INT AUTO_INCREMENT PRIMARY KEY,
    racunUplatioca VARCHAR(50) NOT NULL,
    nazivUplatioca VARCHAR(100) NOT NULL,
    adresaUplatioca VARCHAR(255),
    mestoUplatioca VARCHAR(50),
    racunPrimaoca VARCHAR(50) NOT NULL,
    nazivPrimaoca VARCHAR(100) NOT NULL,
    adresaPrimaoca VARCHAR(255),
    mestoPrimaoca VARCHAR(50),
    svrhaPlacanja TEXT,
    datum DATE NOT NULL,
    iznos DECIMAL(15,2) NOT NULL,
    sifraPlacanja VARCHAR(3),
    model VARCHAR(10),
    pozivNaBroj VARCHAR(50),
    idKartice INT,
    FOREIGN KEY (idKartice) REFERENCES Kartica(id)
);

-- Transfers table
CREATE TABLE IF NOT EXISTS Transferi (
    id INT AUTO_INCREMENT PRIMARY KEY,
    racunUplatioca VARCHAR(50) NOT NULL,
    racunPrimaoca VARCHAR(50) NOT NULL,
    iznos DECIMAL(15,2) NOT NULL,
    datum DATE NOT NULL,
    idKartice INT,
    FOREIGN KEY (idKartice) REFERENCES Kartica(id)
);

-- Insert admin user
INSERT INTO Korisnik (korisnickoIme, lozinka, aktivan) 
VALUES ('admin', 'admin123', TRUE);

INSERT INTO KorisnikPodaci (korisnik, ime, prezime, jmbg, adresa, mesto, brojTelefona, email)
VALUES ('admin', 'Admin', 'User', '1234567890123', 'Admin Address', 'Belgrade', '011-123-456', 'admin@bank.com');

INSERT INTO KorisnikRoles (korisnickoIme, rola)
VALUES ('admin', 'ROLE_ADMIN');

-- Insert test user
INSERT INTO Korisnik (korisnickoIme, lozinka, aktivan) 
VALUES ('user', 'user123', TRUE);

INSERT INTO KorisnikPodaci (korisnik, ime, prezime, jmbg, adresa, mesto, brojTelefona, email)
VALUES ('user', 'Regular', 'User', '1234567890456', 'User Address', 'Belgrade', '011-456-789', 'user@example.com');

INSERT INTO KorisnikRoles (korisnickoIme, rola)
VALUES ('user', 'ROLE_USER');

-- Insert sample credit types
INSERT INTO Krediti (vrstaKredita, nominalnaGodisnjaStopa, valuta)
VALUES 
('Housing Loan', 3.99, 'EUR'),
('Cash Loan', 8.95, 'RSD'),
('Car Loan', 5.50, 'EUR');

-- Insert user with dual roles (both ADMIN and USER)
INSERT INTO Korisnik (korisnickoIme, lozinka, aktivan) 
VALUES ('dualuser', 'dual123', TRUE);

INSERT INTO KorisnikPodaci (korisnik, ime, prezime, jmbg, adresa, mesto, brojTelefona, email)
VALUES ('dualuser', 'Dual', 'User', '1234567890789', 'Dual Address', 'Belgrade', '011-789-123', 'dual@example.com');

INSERT INTO KorisnikRoles (korisnickoIme, rola)
VALUES ('dualuser', 'ROLE_ADMIN');

INSERT INTO KorisnikRoles (korisnickoIme, rola)
VALUES ('dualuser', 'ROLE_USER');

-- Insert sample card for user
INSERT INTO Kartica (tekuciRacun, saldo, ogranicenje, naziv, opis, rokVazenja, korisnickoIme)
VALUES 
('123-4567-890', 100000.00, 500000.00, 'Main Card', 'Primary account card', '2026-12-31', 'user');

-- Insert sample card for dual user
INSERT INTO Kartica (tekuciRacun, saldo, ogranicenje, naziv, opis, rokVazenja, korisnickoIme)
VALUES 
('456-7890-123', 150000.00, 600000.00, 'Dual Card', 'Dual role account card', '2026-12-31', 'dualuser');

-- Insert sample transactions
INSERT INTO Placanja (racunUplatioca, nazivUplatioca, adresaUplatioca, mestoUplatioca, 
                      racunPrimaoca, nazivPrimaoca, adresaPrimaoca, mestoPrimaoca,
                      svrhaPlacanja, datum, iznos, sifraPlacanja, model, pozivNaBroj, idKartice)
VALUES 
('123-4567-890', 'Regular User', 'User Address', 'Belgrade',
 '987-6543-210', 'Electric Company', 'Company Address', 'Belgrade',
 'Monthly electricity bill', '2025-05-01', 5000.00, '221', '97', '12345678', 1);

INSERT INTO Transferi (racunUplatioca, racunPrimaoca, iznos, datum, idKartice)
VALUES 
('123-4567-890', '555-6666-777', 25000.00, '2025-05-05', 1); 