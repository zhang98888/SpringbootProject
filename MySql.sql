/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : finalproject

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 26/11/2021 13:20:35
*/

USE finalProject;

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
                         `cart_id` int NOT NULL AUTO_INCREMENT,
                         `productId` int NULL DEFAULT NULL,
                         `user_id` int NULL DEFAULT NULL,
                         `cart_num` int NULL DEFAULT NULL,
                         `cart_time` datetime NULL DEFAULT NULL,
                         PRIMARY KEY (`cart_id`) USING BTREE,
                         INDEX `productId`(`productId`) USING BTREE,
                         INDEX `user_id`(`user_id`) USING BTREE,
                         CONSTRAINT `productId` FOREIGN KEY (`productId`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES (6, 3, 107, 4, '2021-11-17 15:46:28');
INSERT INTO `cart` VALUES (7, 2, 107, 11, '2021-11-17 15:46:40');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
                             `category_id` int NOT NULL,
                             `category_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `category_level` int NULL DEFAULT NULL,
                             `category_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `category_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 'Laptops', 1, NULL, NULL);
INSERT INTO `category` VALUES (2, 'Moniters', 1, NULL, NULL);
INSERT INTO `category` VALUES (3, 'Memory', 1, NULL, NULL);
INSERT INTO `category` VALUES (4, 'Networking Device', 1, NULL, NULL);
INSERT INTO `category` VALUES (5, 'Printers', 1, NULL, NULL);
INSERT INTO `category` VALUES (6, 'Components', 1, NULL, NULL);
INSERT INTO `category` VALUES (7, 'Computer Accessories', 1, NULL, NULL);
INSERT INTO `category` VALUES (8, 'HeadPhones', 1, NULL, NULL);

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
                               `departmentId` int NOT NULL,
                               `department_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                               PRIMARY KEY (`departmentId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, 'CS Department');
INSERT INTO `department` VALUES (2, 'Research and Development');
INSERT INTO `department` VALUES (3, 'Purchasing');
INSERT INTO `department` VALUES (4, 'Human Resource Management');
INSERT INTO `department` VALUES (5, 'Accounting and Finance');
INSERT INTO `department` VALUES (6, 'Production');

-- ----------------------------
-- Table structure for home_img
-- ----------------------------
DROP TABLE IF EXISTS `home_img`;
CREATE TABLE `home_img`  (
                             `img_id` int NOT NULL AUTO_INCREMENT,
                             `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `prod_id` int NULL DEFAULT NULL,
                             `category_id` int NULL DEFAULT NULL,
                             `index_type` int NULL DEFAULT NULL,
                             `img_bg_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `status` int NULL DEFAULT NULL,
                             `level` int NULL DEFAULT NULL,
                             PRIMARY KEY (`img_id`) USING BTREE,
                             INDEX `prod_id`(`prod_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of home_img
-- ----------------------------
INSERT INTO `home_img` VALUES (1, 'http://localhost:9000/images/afdf1aae221f46f0ac9b0320a67b0817', 5, 2, NULL, NULL, NULL, NULL);
INSERT INTO `home_img` VALUES (2, 'http://localhost:9000/images/9783d6908d5e44a7b4e1155ea85f5cb4', 7, 2, NULL, NULL, NULL, NULL);
INSERT INTO `home_img` VALUES (3, 'http://localhost:9000/images/c0eaf5eadd7c4abf8cdc7e63a89c5809', 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `home_img` VALUES (4, 'http://localhost:9000/images/ff34524a961647738a67d815ef9832ff', 3, 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
                           `order_id` int NOT NULL AUTO_INCREMENT,
                           `user_id` int NULL DEFAULT NULL,
                           `order_status` int NULL DEFAULT NULL,
                           `order_remark` int NULL DEFAULT NULL,
                           `product_id` int NULL DEFAULT NULL,
                           `cart_num` int NULL DEFAULT NULL,
                           `start_date` datetime NULL DEFAULT NULL,
                           `end_date` datetime NULL DEFAULT NULL,
                           `length` int NULL DEFAULT NULL,
                           `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           PRIMARY KEY (`order_id`) USING BTREE,
                           INDEX `good_id`(`product_id`) USING BTREE,
                           CONSTRAINT `good_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 116, 1, 1, 7, 1, '2021-11-14 22:52:38', NULL, 6, 'HUAWEI Display AD80HW 23.8');
INSERT INTO `orders` VALUES (2, 116, 4, 2, 7, 2, '2021-11-14 22:52:38', '2022-11-14 22:52:38', 12, 'HUAWEI Display AD80HW 23.8');
INSERT INTO `orders` VALUES (3, 116, 3, 2, 5, 1, '2021-11-14 22:52:38', '2022-11-16 22:07:33', 12, 'LG UltraFine 32UN880-B');
INSERT INTO `orders` VALUES (4, 116, 1, 2, 7, 2, '2021-11-15 16:44:46', NULL, 3, 'HUAWEI Display AD80HW 23.8');
INSERT INTO `orders` VALUES (5, 116, 1, 2, 5, 1, '2021-11-15 16:44:46', NULL, 3, 'LG UltraFine 32UN880-B');
INSERT INTO `orders` VALUES (6, 108, 1, 1, 7, 1, '2021-11-15 16:44:46', NULL, 6, 'HUAWEI Display AD80HW 23.8');
INSERT INTO `orders` VALUES (7, 107, 4, 2, 2, 1, '2021-11-17 15:06:36', '2022-11-17 15:24:48', 12, 'HP SPECTRE X360 14');
INSERT INTO `orders` VALUES (8, 107, 3, 2, 3, 1, '2021-11-17 15:26:37', '2022-11-17 15:30:51', 12, 'ASUS ROG ZEPHYRUS');
INSERT INTO `orders` VALUES (9, 107, 1, 2, 4, 1, '2021-11-17 15:43:59', NULL, 12, 'Lenovo yoga14s ');
INSERT INTO `orders` VALUES (10, 116, 4, 1, 5, 1, '2021-11-17 15:43:59', '2022-05-19 15:01:46', 6, 'LG UltraFine 32UN880-B');
INSERT INTO `orders` VALUES (11, 116, 2, 2, 7, 1, '2021-11-19 14:49:11', NULL, 6, 'HUAWEI Display AD80HW 23.8');
INSERT INTO `orders` VALUES (12, 108, 4, 2, 2, 1, '2021-11-23 16:15:56', '2022-11-23 16:21:01', 12, 'HP SPECTRE X360 14');
INSERT INTO `orders` VALUES (13, 108, 2, 2, 3, 1, '2021-11-23 16:15:56', NULL, 12, 'ASUS ROG ZEPHYRUS');
INSERT INTO `orders` VALUES (14, 108, 3, 1, 6, 1, '2021-11-23 16:23:12', '2022-11-23 16:24:05', 12, 'Dell UltraSharp 24');
INSERT INTO `orders` VALUES (15, 108, 4, 2, 7, 1, '2021-11-23 19:40:56', '2022-11-23 19:46:12', 12, 'HUAWEI Display AD80HW 23.8');
INSERT INTO `orders` VALUES (16, 108, 3, 1, 4, 1, '2021-11-23 19:48:24', '2022-11-23 19:49:26', 12, 'Lenovo yoga14s ');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
                            `product_id` int NOT NULL,
                            `category_id` int NULL DEFAULT NULL,
                            `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `rent_num` int NULL DEFAULT NULL,
                            `product_status` int NULL DEFAULT NULL,
                            `product_des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `product_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            PRIMARY KEY (`product_id`) USING BTREE,
                            INDEX `category_id`(`category_id`) USING BTREE,
                            CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 1, 'Apple MacBook Air', 600, 1, 'It need your leader to approve, if you have already have one, you need further approve.', '5');
INSERT INTO `product` VALUES (2, 1, 'HP SPECTRE X360 14', 1000, 1, 'If you have one laptop, you only need leader to approve. If you need more than two, you will need further approval.', '5');
INSERT INTO `product` VALUES (3, 1, 'ASUS ROG ZEPHYRUS', 500, 1, NULL, '5');
INSERT INTO `product` VALUES (4, 1, 'Lenovo yoga14s ', 20, 1, NULL, '5');
INSERT INTO `product` VALUES (5, 2, 'LG UltraFine 32UN880-B', 100, 1, 'Only could borrow one.', '5');
INSERT INTO `product` VALUES (6, 2, 'Dell UltraSharp 24', 100, 1, 'Only Borrow one', '5');
INSERT INTO `product` VALUES (7, 2, 'HUAWEI Display AD80HW 23.8', 100, 1, 'Enjoy the view on this 23.8” slim-bezel Full HD display featuring AMD FreeSync, fast response time and 75Hz refresh rate for leisure gaming.', '5');

-- ----------------------------
-- Table structure for product_picture
-- ----------------------------
DROP TABLE IF EXISTS `product_picture`;
CREATE TABLE `product_picture`  (
                                    `picture_id` int NOT NULL AUTO_INCREMENT,
                                    `product_id` int NULL DEFAULT NULL,
                                    `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `sort` int NULL DEFAULT 0,
                                    `is_main` int NULL DEFAULT NULL,
                                    PRIMARY KEY (`picture_id`) USING BTREE,
                                    INDEX `product_id`(`product_id`) USING BTREE,
                                    CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of product_picture
-- ----------------------------
INSERT INTO `product_picture` VALUES (1, 5, 'http://localhost:9000/images/afdf1aae221f46f0ac9b0320a67b0817', 1, 1);
INSERT INTO `product_picture` VALUES (2, 5, 'http://localhost:9000/images/b985504e247a4a3d80c0271eb99476f3', 0, 2);
INSERT INTO `product_picture` VALUES (3, 1, 'http://localhost:9000/images/c0eaf5eadd7c4abf8cdc7e63a89c5809', 1, 1);
INSERT INTO `product_picture` VALUES (4, 1, 'http://localhost:9000/images/ccf040d2cf9e4a4796c35e3242e8d592', 0, 2);
INSERT INTO `product_picture` VALUES (5, 2, 'http://localhost:9000/images/87184a69b18a4d148ebbcc5a70d1dc41', 1, 1);
INSERT INTO `product_picture` VALUES (6, 2, 'http://localhost:9000/images/28856189916b4a97b08c4d949742f946', 0, 2);
INSERT INTO `product_picture` VALUES (7, 3, 'http://localhost:9000/images/ff34524a961647738a67d815ef9832ff', 1, 1);
INSERT INTO `product_picture` VALUES (8, 3, 'http://localhost:9000/images/faef5ea3b7344b91a76adbe3259aee4c', 0, 2);
INSERT INTO `product_picture` VALUES (9, 4, 'http://localhost:9000/images/e57a329477664978aecbddc09697ea35', 1, 1);
INSERT INTO `product_picture` VALUES (10, 4, 'http://localhost:9000/images/f9e0e58b27a84740a883aa3a23fd8987', 0, 2);
INSERT INTO `product_picture` VALUES (11, 6, 'http://localhost:9000/images/1f39c962902f4815b528993403a6fac1', 1, 1);
INSERT INTO `product_picture` VALUES (12, 6, 'http://localhost:9000/images/0decdd354f2f4134a30ee24b9359d437', 0, 2);
INSERT INTO `product_picture` VALUES (13, 7, 'http://localhost:9000/images/9783d6908d5e44a7b4e1155ea85f5cb4', 0, 1);
INSERT INTO `product_picture` VALUES (14, 7, 'http://localhost:9000/images/0afb9eaafae847ce91d70f106990efcb', 0, 2);

-- ----------------------------
-- Table structure for sku
-- ----------------------------
DROP TABLE IF EXISTS `sku`;
CREATE TABLE `sku`  (
                        `sku_id` int NOT NULL,
                        `sku_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                        `sku_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                        `rent_level` int NULL DEFAULT NULL,
                        `stock` int NULL DEFAULT NULL,
                        `status` int NULL DEFAULT NULL,
                        PRIMARY KEY (`sku_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of sku
-- ----------------------------

-- ----------------------------
-- Table structure for user_login_his
-- ----------------------------
DROP TABLE IF EXISTS `user_login_his`;
CREATE TABLE `user_login_his`  (
                                   `ID` int NOT NULL,
                                   `AREA` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                   `COUNTRY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                   `USER_ID` int NULL DEFAULT NULL,
                                   `IP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                   `LOGIN_TIME` datetime NULL DEFAULT NULL,
                                   PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of user_login_his
-- ----------------------------

-- ----------------------------
-- Table structure for useraddress
-- ----------------------------
DROP TABLE IF EXISTS `useraddress`;
CREATE TABLE `useraddress`  (
                                `addrId` int NOT NULL AUTO_INCREMENT,
                                `userId` int NULL DEFAULT NULL,
                                `receiver_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `receiver_mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `postcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `default_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `create_time` datetime NULL DEFAULT NULL,
                                `update_time` datetime NULL DEFAULT NULL,
                                PRIMARY KEY (`addrId`) USING BTREE,
                                INDEX `userId`(`userId`) USING BTREE,
                                CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of useraddress
-- ----------------------------
INSERT INTO `useraddress` VALUES (2, 116, 'admin', '1111111', 'AA', 'AAAAAA', 'AA ABB', '1', '1', '2021-11-19 15:37:14', '2021-11-19 17:22:43');
INSERT INTO `useraddress` VALUES (3, 116, 'admin', '111111111', 'glasgow', 'University of Glasgow, Glasgow, G12 8QQ, Scotland', 'G12 8QQ', NULL, '2', '2021-11-19 17:38:06', '2021-11-19 17:38:19');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
                          `userId` int NOT NULL,
                          `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                          `departmentId` int NULL DEFAULT NULL,
                          `userImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                          `userPwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                          `user_sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                          `user_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                          `user_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                          `user_mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                          `user_birth` date NULL DEFAULT NULL,
                          `working_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                          `user_regtime` datetime NULL DEFAULT NULL,
                          `user_modtime` datetime NULL DEFAULT NULL,
                          PRIMARY KEY (`userId`) USING BTREE,
                          INDEX `departmentId`(`departmentId`) USING BTREE,
                          CONSTRAINT `departmentId` FOREIGN KEY (`departmentId`) REFERENCES `department` (`departmentId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (100, 'zhang100', 1, NULL, '6ca089ae978f1568f57265c9e09ba695c017cbc3', 'Female', 'zhang100@A.com', '1', NULL, NULL, 'Home', '2021-11-01 15:59:28', '2021-11-01 15:59:28');
INSERT INTO `users` VALUES (101, 'zhang101', 2, NULL, 'cae0c4333cb54806fdd3cd10ab4c24911053aa6b', 'Male', 'zhang101@AA.com', '1', NULL, NULL, 'Office', '2021-11-01 16:00:52', '2021-11-01 16:00:52');
INSERT INTO `users` VALUES (102, 'zhang102', 3, NULL, '7172d3cd251d429d87b791f48494c4f7ca9f9a68', 'Female', 'zhang102@AA.com', '1', NULL, NULL, 'Office', '2021-11-01 16:01:37', '2021-11-01 16:01:37');
INSERT INTO `users` VALUES (103, 'zhang103', 4, NULL, '63091e0ba024208cdbe9bf145436e3f94d2b1187', 'Female', 'zhang103@AA.com', '1', NULL, NULL, 'Home', '2021-11-01 16:01:59', '2021-11-01 16:01:59');
INSERT INTO `users` VALUES (104, 'zhang104', 5, NULL, '16cb86dba2cf9be4a11cc8e334d6958e620c98ef', 'Male', 'zhang104@AA.com', '1', NULL, NULL, 'Unknown', '2021-11-01 16:02:30', '2021-11-01 16:02:30');
INSERT INTO `users` VALUES (105, 'zhang105', 6, NULL, '0d955b2b44625bcabeb68fc160996b6b3609ecdb', 'Female', 'zhang105@AA.com', '1', NULL, NULL, 'Office', '2021-11-01 16:02:52', '2021-11-01 16:02:52');
INSERT INTO `users` VALUES (106, 'zhang106', 1, NULL, '99693cc692e7e192c75b2f87d6a7c2f2146bad97', 'Male', 'zhang106@AA.com', '2', NULL, NULL, 'Home', '2021-11-01 16:03:24', '2021-11-01 16:03:24');
INSERT INTO `users` VALUES (107, 'zhang107', 2, NULL, 'b30a5917291acdd1f837661e4a688127c0cb32f3', 'Female', 'zhang107@AA.com', '2', NULL, NULL, 'Unknown', '2021-11-01 16:03:52', '2021-11-01 16:03:52');
INSERT INTO `users` VALUES (108, 'zhang108', 3, NULL, 'ed60839b617df2473111aeac6f1d3ab803e9f74b', 'Female', 'zhang108@AA.com', '2', NULL, NULL, 'Home', '2021-11-01 16:04:21', '2021-11-01 16:04:21');
INSERT INTO `users` VALUES (109, 'zhang109', 4, NULL, 'bce6d9a06315a3e08a39ac35add0070459a5eae9', 'Male', 'zhang109@AA.com', '2', NULL, NULL, 'Office', '2021-11-01 16:04:43', '2021-11-01 16:04:43');
INSERT INTO `users` VALUES (110, 'zhang110', 5, NULL, 'b36892a19837a7aff93f996a24e1f8905eb2a457', 'Male', 'zhang110@AA.com', '2', NULL, NULL, 'Unknown', '2021-11-01 16:05:06', '2021-11-01 16:05:06');
INSERT INTO `users` VALUES (116, 'admin', 1, NULL, '7c4a8d09ca3762af61e59520943dc26494f8941b', 'Female', 'AA@Service.com', '111', NULL, NULL, 'Home', '2021-11-01 15:23:36', '2021-11-01 15:23:36');

SET FOREIGN_KEY_CHECKS = 1;


