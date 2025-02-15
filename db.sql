DROP database study_case;
CREATE DATABASE study_case;
USE study_case;

CREATE TABLE category (
    category_name VARCHAR(255) PRIMARY KEY
);

CREATE TABLE product (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    product_price DOUBLE NOT NULL DEFAULT 0 CHECK (product_price >= 0),
    category_name VARCHAR(255),
    product_inventory INT DEFAULT 0,
    product_img VARCHAR(255),
    product_description TEXT,
    FOREIGN KEY (category_name)
        REFERENCES category (category_name)
);

CREATE TABLE customer (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) DEFAULT '123456',
    customer_name VARCHAR(255) NOT NULL,
    customer_email VARCHAR(255) UNIQUE NOT NULL,
    customer_phone VARCHAR(255) UNIQUE NOT NULL,
    customer_citizen VARCHAR(255) UNIQUE NOT NULL,
    customer_role VARCHAR(255) DEFAULT 'CUSTOMER'
);


CREATE TABLE review (
    review_id INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT,
    customer_id INT,
    review_content TEXT,
    FOREIGN KEY (product_id)
        REFERENCES product (product_id),
    FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id)
);

CREATE TABLE cart (
    customer_id INT,
    product_id INT,
    quantity INT DEFAULT 1,
    PRIMARY KEY (customer_id , product_id),
    FOREIGN KEY (product_id)
        REFERENCES product (product_id),
    FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id)
);

CREATE TABLE orders (
    customer_id INT,
    product_id INT,
    quantity INT DEFAULT 1,
    FOREIGN KEY (product_id)
        REFERENCES product (product_id),
    FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id)
);

DELIMITER //
CREATE TRIGGER orders
AFTER DELETE ON cart
FOR EACH ROW
BEGIN
INSERT INTO orders (customer_id, product_id, quantity) VALUES
(OLD.customer_id, OLD.product_id, OLD.quantity);
END//
DELIMITER ;

INSERT INTO customer (username, customer_name, customer_email, customer_phone, customer_citizen ) 
VALUES 
('phuc', 'Phúc', 'phuc@gmail.com', '1234567890', '123456789123'),
('tuan','Tuấn','tuan@gmail.com','092627212','001228822'),

INSERT INTO customer (username, customer_name, customer_email, customer_phone, customer_citizen, customer_role) 
VALUES ('phamvanman@gmail.com', 'Mẫn', 'phamvanman@gmail.com', '0123456789', '012345678999', 'ADMIN');

INSERT INTO category VALUES
('Model-G'),
('Model-F'),
('Model-T');

INSERT INTO product (product_id, product_name, product_price, category_name, product_inventory, product_img, product_description) VALUES
(1, 'M1 Abrams', 10000, 'Model-T', 500, 'm1_abrams.png', 'M1 Abrams là xe tăng chiến đấu chủ lực của Mỹ với giáp tiên tiến và hỏa lực mạnh. Được trang bị pháo 120mm và hệ thống điều khiển hỏa lực, hiệu quả trên chiến trường.'),
(2, 'Su-57', 15000, 'Model-F', 300, 'su-57.png', 'Su-57 là máy bay chiến đấu tàng hình thế hệ thứ năm của Nga, với khả năng siêu cơ động và vũ khí tiên tiến. Phù hợp cho nhiệm vụ không đối không và đất, công nghệ tàng hình.'),
(3, 'Leopard 2', 9000, 'Model-T', 450, 'leopard_2.png', 'Leopard 2 là xe tăng chiến đấu chủ lực của Đức, nổi bật với sự kết hợp giữa hỏa lực mạnh, giáp bảo vệ và tính cơ động. Hiệu quả trong các điều kiện chiến đấu khác nhau.'),
(4, 'Eurofighter Typhoon', 14000, 'Model-F', 250, 'eurofighter_typhoon.png', 'Eurofighter Typhoon là máy bay chiến đấu đa nhiệm của châu Âu, với khả năng tấn công và phòng thủ linh hoạt. Trang bị công nghệ tiên tiến cho các nhiệm vụ không chiến và mặt đất.'),
(5, 'HK416', 2500, 'Model-G', 800, 'hk416.png', 'HK416 là súng trường tấn công của Đức, nổi tiếng với độ chính xác cao và độ bền trong nhiều điều kiện khắc nghiệt. Có thiết kế thông minh, hoạt động ổn định và hiệu quả trong quân sự.'),
(6, 'FN SCAR', 2700, 'Model-G', 700, 'fn_scar.png', 'FN SCAR là súng trường tấn công hiện đại, được sử dụng rộng rãi bởi lực lượng đặc nhiệm toàn cầu. Với thiết kế modul và tùy chỉnh cao, cung cấp hiệu suất chiến đấu tối ưu.'),
(7, 'Merkava IV', 11000, 'Model-T', 500, 'merkava_iv.png', 'Merkava IV là xe tăng chiến đấu chủ lực của Israel, thiết kế để bảo vệ kíp lái với giáp tiên tiến và hỏa lực mạnh. Xe có khả năng chiến đấu cao và bảo vệ tốt trong các tình huống chiến tranh.'),
(8, 'Challenger 2', 10000, 'Model-T', 450, 'challenger_2.png', 'Challenger 2 là xe tăng chiến đấu chủ lực của Anh, nổi bật với độ bền và giáp bảo vệ dày đặc. Trang bị pháo 120mm và hệ thống điều khiển hỏa lực tiên tiến, phù hợp cho chiến đấu khắc nghiệt.'),
(9, 'F-22 Raptor', 18000, 'Model-F', 200, 'f-22_raptor.png', 'F-22 Raptor là máy bay chiến đấu tàng hình thế hệ thứ năm của Mỹ, nổi bật với khả năng tàng hình, siêu cơ động và hệ thống vũ khí tiên tiến. Thiết kế cho nhiệm vụ đa nhiệm trên không.'),
(10, 'FA-18 Hornet', 16000, 'Model-F', 250, 'fa-18_hornet.png', 'FA-18 Hornet là máy bay chiến đấu đa năng của Mỹ, nổi bật với khả năng tấn công mặt đất và không đối không. Trang bị hệ thống điện tử tiên tiến, hoạt động trên tàu sân bay và trong chiến đấu khác.'),
(11, 'M249 SAW', 2200, 'Model-G', 500, 'm249_saw.png', 'M249 SAW là súng máy hạng nhẹ của Mỹ, nổi bật với khả năng bắn liên tục và sức công phá ổn định. Cung cấp hỏa lực hỗ trợ trong các hoạt động quân sự và tác chiến bộ binh.'),
(12, 'Barrett M82', 3500, 'Model-G', 400, 'barrett_m82.png', 'Barrett M82 là súng bắn tỉa hạng nặng của Mỹ, nổi tiếng với khả năng bắn chính xác từ xa và sức công phá mạnh mẽ. Súng được sử dụng trong nhiệm vụ quân sự và phòng thủ đặc biệt, hiệu suất cao.');

INSERT INTO review (product_id,customer_id,review_content)
VALUES
(1,1,"Chất lượng tốt, độ hoàn thiện cao, lần sau sẽ mua thêm"),
(1,1,"Đánh giá chỉ mang tính chất nhận xu"),
(1,1,"Pằng pằng, ựa ựa!"),
(1,2,"oke đấy!"),
(1,2,"Súng bắn zom rất phê (zombie v4)"),
(2,1,"Súng bắn rất êm, thích hợp cho việc đi săn thú rừng"),
(2,2,"Thích hợp cho việc đi săn bắn trái phép"),
(3,1,"Súng tốt, nhẹ, lần sau sẽ ủng hộ thêm"),
(3,2,"Súng khá nặng, khó bắn, mọi người cân nhắc trước khi mua"),
(4,1,"Chủ shop nhiệt tình, thân thiện"),
(4,2,"Bắn tùm lum"),
(5,1,"bùm bùm"),
(6,2,"chíu chíu");
