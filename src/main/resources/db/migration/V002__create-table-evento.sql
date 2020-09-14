CREATE TABLE IF NOT EXISTS evento(
	id BIGINT NOT NULL AUTO_INCREMENT,
    conta_id BIGINT NOT NULL,
    nome VARCHAR(30),
    data_inicio DATETIME NOT NULL,
    data_fim DATETIME NOT NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (conta_id) REFERENCES conta(id)
);