CREATE TABLE IF NOT EXISTS palestra(
	id BIGINT NOT NULL AUTO_INCREMENT,
    evento_id BIGINT NOT NULL,
    conta_id BIGINT NOT NULL,
    palestrante_id BIGINT NOT NULL,
    nome VARCHAR(30),
    data DATETIME NOT NULL,
    
    PRIMARY KEY (id),
    
    FOREIGN KEY (evento_id) REFERENCES evento(id),
    FOREIGN KEY (conta_id) REFERENCES conta(id),
    FOREIGN KEY (palestrante_id) REFERENCES palestrante(id)
);