-- writer table
CREATE TABLE writer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(255),
    bio varchar(255)
);

-- magazine table
CREATE TABLE magazine (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title varchar(255),
    publicationDate varchar(255)
);

-- writer_magazine junction table
CREATE TABLE writer_magazine (
    writerId INT,
    magazineId INT,
    PRIMARY KEY (writerId, magazineId),
    FOREIGN KEY (writerId) REFERENCES writer(id),
    FOREIGN KEY (magazineId) REFERENCES magazine(id)
);
