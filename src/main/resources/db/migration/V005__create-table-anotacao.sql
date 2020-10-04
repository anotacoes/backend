CREATE TABLE IF NOT EXISTS anotacao(
	id BIGINT NOT NULL AUTO_INCREMENT,
    palestra_id BIGINT NOT NULL,
    conta_id BIGINT NOT NULL,
	texto VARCHAR(300) NOT NULL,
    
    PRIMARY KEY (id),
    
    FOREIGN KEY (palestra_id) REFERENCES palestra(id),
    FOREIGN KEY (conta_id) REFERENCES conta(id)
);