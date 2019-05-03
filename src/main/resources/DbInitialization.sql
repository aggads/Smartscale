# ================== CREATE TABLE ========================

  CREATE TABLE IF NOT EXISTS plates (
          id int AUTO_INCREMENT,
          plate varchar(45),
          type varchar(45),
          quantity varchar(245),
          date_order Date,
          client int,
          PRIMARY KEY(id)
  );
  CREATE TABLE IF NOT EXISTS clients(
  	      id int AUTO_INCREMENT,
          name varchar(45),
          PRIMARY KEY(id)
  );

-- CREATE TABLE IF NOT EXISTS WeightString(
-- 	Id int AUTO_INCREMENT,
--         HX771 varchar(45),
--         PRIMARY KEY(id)
-- );
-- CREATE TABLE IF NOT EXISTS WeightTarget(
--         Id int AUTO_INCREMENT,
--         HX771 varchar(45),
--         target varchar(245)
--         PRIMARY KEY(id)
-- );
-- CREATE TABLE IF NOT EXISTS WeightTargetPlate(
--         Id int AUTO_INCREMENT,
--         HX771 varchar(45),
--         target varchar(245),
--         plate varchar(245),
--         PRIMARY KEY(id)
-- );

# ==================== ADD FOREIGN KEYs ========================

ALTER TABLE `springsql`.`plates` 
ADD INDEX `client_fk_idx` (`client` ASC) VISIBLE;
;
ALTER TABLE `springsql`.`plates` 
ADD CONSTRAINT `client_fk`
  FOREIGN KEY (`client`)
  REFERENCES `springsql`.`clients` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

# ========================= INSERT DATA ================================


SET FOREIGN_KEY_CHECKS=0;
INSERT INTO plates (type, quantity, plate, date_order, client)
VALUES 
        ('Sable', '5000', '9JAG121', '2018-04-21', 1),
        ('Gravier', '4000', '9JAG121', '2018-04-22', 2),
        ('Gravier', '80000', '1JEP350', '2018-04-22', 2);
        

INSERT INTO clients(name)
VALUES
        ('Lorenz'),
        ('Jean');

#=================== FETCH DATA (QUERRY) ====================================
# SELECT * FROM PlateMessage INNER JOIN OrderMessage ON PlateMessage.id = OrderMessage.id;
# SELECT o FROM PlateMessage p,OrderMessage o where p.id = o.id

#join column parent on child (@JoinColumn(name = "plate"))